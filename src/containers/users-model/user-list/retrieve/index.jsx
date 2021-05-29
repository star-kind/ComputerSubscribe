import React, { Component } from 'react'
import PublicHeader2 from '@/components/header2/header2'
import { getValueFromLocal } from '@/api/common'
import './index.less'
import UserTable from '@/components/tables/users/user-table/table-index'
import PageInfo from '@/components/tables/users/page-info/page-info'

//管理员查看用户成员列表
class RetrieveList extends Component {
  constructor(props) {
    super(props)
    console.info('%cRetrieveList--Constructor', 'color:red', this)
    this.state = {
      //表首数组
      thArr: ['用户名', '学号/工号', '帐号类型', '邮箱', '电话', '操作'],
      //是否隐藏/展示页码区域
      showPageArea: '',
      //页码
      targetPage: 1,
      tblRow: 3,
      //
      pagination: {
        data: [],
        hasPrevious: false,
        hasNext: false,
        totalPages: 0,
        currentPage: 1,
        rows: 0,
      },
    }
  }

  componentDidMount() {
    console.log('%cRetrieveList component did mount', 'color:blue', this)
    this.handleGetUsersList()
  }

  //联结子组件之桥梁,供数据物流之途
  bridge = (data) => {
    console.log('%cbridge.data', this.color(), data)
    //
    switch (data.method) {
      case 'showInfoArea':
        this.exhibitPageDiv(data.instruct)
        break

      case 'showForm':
        this.hidePageScope(data.instruct)
        break

      default:
        break
    }
  }

  //隐藏PageScope
  hidePageScope = (instruct) => {
    console.log('%chidePageScope.instruct', this.color(), instruct)
    this.setState({
      showPageArea: instruct,
    })
  }

  //展示PageDiv
  exhibitPageDiv = (instruct) => {
    console.log('%cexhibitPageDiv.instruct=' + instruct, 'color:red')
    this.setState({
      showPageArea: instruct,
    })
  }

  //接收来自子组件的数据
  receiveInfo = (data) => {
    if (data.targetOrder !== undefined) {
      console.log('%c jumpByPageNum-data\n', 'color:red', data.targetOrder)
      this.jumpByPageNum(data)
    } else if (data.nextOnePage !== undefined) {
      console.log('%c jumpNextPage-data\n', 'color:teal', data)
      this.jumpNextPage(data)
    } else if (data.previousOnePage !== undefined) {
      console.log('%c jumpPreviousPage-data\n', 'color:green', data)
      this.jumpPreviousPage(data)
    }
  }

  //根据页码+每页行数查看
  jumpByPageNum = (data) => {
    //若跳转页码目标大于总页,警示,中断
    let { totalPages } = this.state.pagination
    if (data.targetOrder > totalPages) {
      alert('跳转页数不得大于总页数')
      return
    } else if (data.targetOrder <= 0) {
      alert('跳转页数不得小于零或等于零')
      return
    } else {
      this.setState({
        targetPage: data.targetOrder,
        tblRow: data.defineRowNum,
      })
      this.handleGetUsersList2(data.targetOrder, data.defineRowNum)
    }
  }

  //前往下页
  jumpNextPage = (data) => {
    let { hasNext, totalPages } = this.state.pagination
    console.log('%chasNext,totalPages', this.getColor(), hasNext, totalPages)
    //
    let { pagination } = this.state
    let { nextOnePage, defineRowNum } = data
    //
    if (!hasNext) {
      alert('已是尾页')
      return
    } else if (nextOnePage > totalPages) {
      pagination.hasNext = false
      this.setState({
        pagination,
      })
      alert('没有下一页了')
      return
    } else {
      pagination.currentPage = nextOnePage
      this.setState({
        pagination,
        targetPage: nextOnePage,
      })
      this.handleGetUsersList2(nextOnePage, defineRowNum)
    }
  }

  //前往上页
  jumpPreviousPage = (data) => {
    let { hasPrevious } = this.state.pagination
    let { pagination } = this.state
    let { previousOnePage, defineRowNum } = data
    console.log(
      '%c hasPrevious,previousOnePage',
      this.getColor(),
      hasPrevious,
      previousOnePage
    )
    if (previousOnePage < 1) {
      pagination.hasPrevious = false
      this.setState({
        pagination,
      })
      alert('已经是第一页')
      return
    } else {
      pagination.currentPage = previousOnePage
      this.setState({
        pagination,
        targetPage: previousOnePage,
      })
      this.handleGetUsersList2(previousOnePage, defineRowNum)
    }
  }

  /**
   *
   * @param {*} targetPage
   * @param {*} tblRow
   * @returns
   */
  handleGetUsersList2 = (targetPage, tblRow) => {
    //防止使用箭头函数this会指向我们不希望它所指向的对象
    const ts = this
    let tokenObj = getValueFromLocal(ts.store_key.token_key)
    console.log('tokenObj\n', tokenObj)
    //token
    if (tokenObj.code === -1) {
      alert(tokenObj.text)
      return
    }
    //
    ts.gets(
      ts.interfaces.getMember,
      {
        pageOrder: targetPage,
        rows: tblRow,
      },
      { token: tokenObj.text }
    ).then((res) => {
      console.log('RESPONSE\n', res)
      if (res.data.code === 200) {
        ts.setState({
          pagination: res.data.data,
        })
      } else {
        console.log('res.data.message', res.data.message)
        alert(res.data.message)
      }
    })
  }

  handleGetUsersList = () => {
    //先存一下this,以防使用箭头函数this会指向我们不希望它所指向的对象
    const ts = this
    let tokenObj = getValueFromLocal(ts.store_key.token_key)
    console.log('tokenObj\n', tokenObj)
    //token
    if (tokenObj.code === -1) {
      alert(tokenObj.text)
      return
    }
    //
    ts.gets(
      ts.interfaces.getMember,
      {
        pageOrder: ts.state.targetPage,
        rows: ts.state.tblRow,
      },
      { token: tokenObj.text }
    ).then((res) => {
      console.log('RESPONSE\n', res)
      if (res.data.code === 200) {
        console.info('res.data.data\n', res.data.data)
        ts.setState({
          pagination: res.data.data,
        })
      } else {
        console.log('res.data.message', res.data.message)
        alert(res.data.message)
      }
    })
  }

  render() {
    return (
      <div className='main_wrapper'>
        <div className='pub_head'>
          <PublicHeader2></PublicHeader2>
        </div>
        <div className='master_context'>
          <div className='user_tabl'>
            <UserTable
              bridge={this.bridge}
              thArray={this.state.thArr}
              pagination={this.state.pagination}
            ></UserTable>
          </div>
          <div
            style={{ display: this.state.showPageArea }}
            className='surround_pageinfo'
          >
            <PageInfo
              receiveData={this.receiveInfo}
              pagination={this.state.pagination}
              targetPageNum={this.state.targetPage}
            ></PageInfo>
          </div>
        </div>
      </div>
    )
  }
}
export default RetrieveList
