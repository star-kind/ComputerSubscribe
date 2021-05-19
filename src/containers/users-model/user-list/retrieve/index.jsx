import React, { Component } from 'react'
import PublicHeader2 from '@/components/header2/header2'
import Portals2 from '@/components/popup-window/portals2/portals2'
import { getValueFromLocal } from '@/api/common'
import './index.less'
import UserTable from '@/components/tables/users/user-table/table-index'
import PageInfo from '@/components/tables/page-info/page-info'

//管理员查看用户成员列表
class RetrieveList extends Component {
  constructor(props) {
    super(props)
    console.info('%cRetrieveList--Constructor', 'color:red', this)
    this.state = {
      //是否展示
      whetherExhibit: true,
      message: '',
      //
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

  //子组件调用父组件中的函数,接收到子组件的值
  hidePageScope = (instruct) => {
    console.log('hidePageScope.instruct', instruct)
    this.setState({
      showPageArea: instruct,
    })
  }

  //接收子孙组件发送的值
  exhibitPageDiv = (instruct) => {
    console.log('%cexhibitPageDiv.instruct=' + instruct, 'color:red')
    this.setState({
      showPageArea: instruct,
    })
  }

  //接收来自子组件的数据
  receiveInfo = (data) => {
    if (data.targetOrder !== undefined) {
      console.log('%c data-targetOrder\n', 'color:green', data.targetOrder)
      this.jumpByPageNum(data)
    } else if (data.nextOnePage !== undefined) {
      console.log('%c data-nextOnePage\n', 'color:green', data.nextOnePage)
      this.jumpNextPage(data)
    } else if (data.previousOnePage !== undefined) {
      console.log('%c data-previousPage\n', 'color:green', data.previousOnePage)
      this.jumpPreviousPage(data)
    }
    this.handleGetUsersList()
  }

  //根据页码+每页行数查看
  jumpByPageNum = (data) => {
    //若跳转页码目标大于总页,警示,中断
    let { totalPages } = this.state.pagination
    if (data.targetOrder > totalPages) {
      alert('跳转页数不得大于总页数')
      // this.setState({
      //   whetherExhibit: !this.state.whetherExhibit,
      //   message: '跳转页数不得大于总页数',
      // })
      return
    } else if (data.targetOrder <= 0) {
      alert('跳转页数不得小于零或等于零')
      // this.setState({
      //   whetherExhibit: !this.state.whetherExhibit,
      //   message: '跳转页数不得小于零或等于零',
      // })
      return
    } else {
      this.setState({
        targetPage: data.targetOrder,
        tblRow: data.defineRowNum,
      })
    }
  }

  //前往下页
  jumpNextPage = (data) => {
    let { hasNext, totalPages } = this.state.pagination
    console.log('%chasNext,totalPages', this.getColor(), hasNext, totalPages)
    //
    let { pagination } = this.state
    let { nextOnePage } = data
    //
    if (!hasNext) {
      alert('已是尾页')
      // this.setState({
      //   whetherExhibit: !this.state.whetherExhibit,
      //   message: '已是尾页',
      // })
      return
    } else if (nextOnePage > totalPages) {
      pagination.hasNext = false
      this.setState({
        pagination,
        // whetherExhibit: !this.state.whetherExhibit,
        // message: '没有下一页了',
      })
      alert('没有下一页了')
      return
    } else {
      pagination.currentPage = nextOnePage
      this.setState({
        pagination,
        targetPage: nextOnePage,
      })
    }
  }

  //前往上页
  jumpPreviousPage = (data) => {
    let { hasPrevious } = this.state.pagination
    let { pagination } = this.state
    let { previousOnePage } = data
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
        // whetherExhibit: !this.state.whetherExhibit,
        // message: '已经是第一页',
      })
      alert('已经是第一页')
      return
    } else {
      pagination.currentPage = previousOnePage
      this.setState({
        pagination,
        targetPage: previousOnePage,
      })
    }
  }

  handleGetUsersList = () => {
    //先存一下this,以防使用箭头函数this会指向我们不希望它所指向的对象
    const ts = this
    let tokenObj = getValueFromLocal(ts.store_key.token_key)
    console.log('tokenObj\n', tokenObj)
    //token
    if (tokenObj.code === -1) {
      ts.setState({
        isExhibit: !ts.state.isExhibit,
        msg: tokenObj.text,
      })
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
        // ts.setState({
        // message: res.data.message,
        // whetherExhibit: !ts.state.whetherExhibit,
        // })
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
              showPageDiv={this.exhibitPageDiv}
              hidePageArea={this.hidePageScope}
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
          {/*  */}
          <div className='portals02'>
            <Portals2
              msg={this.state.message}
              isExhibit={this.state.whetherExhibit}
            ></Portals2>
          </div>
        </div>
      </div>
    )
  }
}
export default RetrieveList
