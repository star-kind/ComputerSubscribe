import React, { Component } from 'react'
import './major.less'
import PublicHeader2 from '@/components/header2/header2'
import TableList from '@/containers/room-model/queries/table/table-list'
import EditForm from '@/containers/room-model/queries/form/edit-form'
import PageInfo from '@/components/tables/page-info/page-info'
import { getValueFromLocal } from '@/api/common'
import Portals2 from '@/components/popup-window/portals2/portals2'

/*
分页列表,获取全部机房信息,不限制帐号类型
*/
class Major extends Component {
  constructor(props) {
    super(props)
    console.info('%cMajor--Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cMajor component did mount\n', 'color:red', this)
    this.handleGetList()
  }

  //state或props更新后调用
  componentDidUpdate(prevProps, prevState) {
    console.log(
      '%c Major.componentDidUpdate prevProps',
      this.getColor(),
      prevProps
    )
    console.log(
      '%c Major.componentDidUpdate prevState',
      this.getColor(),
      prevState
    )
    console.log('%c Major.componentDidUpdate.this', this.getColor(), this)
    //
    this.storeCurrentPage()
  }

  state = {
    pagination: {
      data: [],
      hasPrevious: false,
      hasNext: false,
      totalPages: 0,
      currentPage: 1,
      rows: 0,
    },
    //表头
    thArr: [
      '机房房号',
      '座位总数',
      '是否开放使用',
      '最近编辑者的工号[管理员]',
      '上一次编辑机房的时间',
      '机房的地址位置',
      '可用座位数',
      '操作壹',
      '操作贰',
    ],
    //传给表单审核的机房数据(单份)
    tagetedSelectd: {
      id: '',
      roomNum: '',
      totalSets: '',
      availableStatus: '',
      adminNumOperated: '',
      operatedTime: '',
      location: '',
      actAvailableQuantity: '',
    },
    //页码
    targetPage: 0,
    limitRow: 0,
    //
    tblAndPageInfoDisplay: '',
    formDisplay: 'none',
    //是否展示
    whetherExhibit: true,
    message: '',
    //被选中编辑的数组中当前元素下标
    arrIndex: '',
  }

  /**
   * 保留刷新前页码到localStorage
   * 弃用本方法
   */
  storeCurrentPage = () => {
    let { currentPage } = this.state.pagination
    localStorage.setItem(this.store_key.curr_page_key, currentPage)
  }

  /**
   *
   * @returns
   */
  handleGetList = () => {
    const ts = this
    let tokenObj = getValueFromLocal(ts.store_key.token_key)
    console.log('%c tokenObj\n', ts.getColor(), tokenObj)
    //token
    if (tokenObj.code === -1) {
      ts.setState({
        whetherExhibit: !ts.state.whetherExhibit,
        message: tokenObj.text,
      })
      return
    }
    //
    console.log('%c handleGetList.limitRow\n', ts.getColor(), ts.state.limitRow)
    if (ts.state.targetPage === 0) {
      ts.state.targetPage = 1
    }
    if (ts.state.limitRow === 0) {
      ts.state.limitRow = 5
    }
    //
    ts.gets(
      ts.interfaces.getRoomListByPagin,
      {
        pageOrder: ts.state.targetPage,
        rows: ts.state.limitRow,
      },
      { token: tokenObj.text }
    ).then((res) => {
      console.info('response\n', res)
      if (res.data.code === 200) {
        ts.setState({
          pagination: res.data.data,
        })
      } else {
        console.log('res.data.message\n', res.data.message)
        ts.setState({
          message: res.data.message,
          whetherExhibit: !ts.state.whetherExhibit,
        })
      }
    })
  }

  handleGetList2 = (pageNum, rows) => {
    //先存一下this,以防使用箭头函数this会指向我们不希望它所指向的对象
    const ts = this
    let tokenObj = getValueFromLocal(ts.store_key.token_key)
    console.log('%c tokenObj\n', ts.getColor(), tokenObj)
    //token
    if (tokenObj.code === -1) {
      ts.setState({
        whetherExhibit: !ts.state.whetherExhibit,
        message: tokenObj.text,
      })
      return
    }
    //
    console.log(
      '%c handleGetList2.pageNum\n',
      ts.getColor(),
      pageNum,
      'handleGetList2.rows\n',
      rows
    )
    //
    ts.gets(
      ts.interfaces.getRoomListByPagin,
      {
        pageOrder: pageNum,
        rows: rows,
      },
      { token: tokenObj.text }
    ).then((res) => {
      console.info('response\n', res)
      if (res.data.code === 200) {
        ts.setState({
          pagination: res.data.data,
        })
      } else {
        console.log('res.data.message\n', res.data.message)
        ts.setState({
          message: res.data.message,
          whetherExhibit: !ts.state.whetherExhibit,
        })
      }
    })
  }

  //集中接收子组件传递的数据
  receivedChildData = (childData) => {
    console.info('%c childData', this.getColor(), childData)
    switch (childData.method) {
      case 'showFormHideTable':
        this.showFormHideTable(childData)
        break

      case 'hideFormExhibitTbl':
        this.hideFormExhibitTbl(childData.value)
        break

      case 'jumpToPreviousPage':
        this.handleGetList2(childData.previousOnePage, childData.defineRowNum)
        break

      case 'jumpToNextPage':
        this.handleGetList2(childData.nextOnePage, childData.defineRowNum)
        break

      case 'jumpSpecifyPage':
        this.handleGetList2(childData.targetOrder, childData.defineRowNum)
        break

      case 'updatedTblRowData':
        this.updatedPaginByIndex(childData.data)
        break

      case 'deleteRoomById':
        this.deleteRoomById(childData)
        break

      default:
        break
    }
  }

  /**
   *
   * @param {*} params
   */
  deleteRoomById = (params) => {
    let { pagination } = this.state
    //deleteIndex
    pagination.data.splice(params.deleteIndex, 1)
    this.setState({
      pagination: pagination,
    })
  }

  //审批完预约后,根据索引更新pagination中的数据
  updatedPaginByIndex = (newRowData) => {
    let { pagination, arrIndex } = this.state
    if (arrIndex !== '') {
      pagination.data[arrIndex] = newRowData
      this.setState({
        pagination,
      })
    } else {
      console.info('%c updatedPaginByIndex.arrIndex', this.color(), arrIndex)
    }
  }

  //取消编辑,隐匿表单,显示表格
  hideFormExhibitTbl = (param) => {
    this.setState({
      tblAndPageInfoDisplay: param.tableAndPageInfo,
      formDisplay: param.form,
    })
  }

  //传送预约单数据给表单审核,显示表单,隐匿表格
  showFormHideTable = (data) => {
    this.setState({
      arrIndex: data.index,
      tagetedSelectd: data.value,
      tblAndPageInfoDisplay: 'none',
      formDisplay: 'block',
    })
  }

  render() {
    return (
      <div className='main-major'>
        <PublicHeader2></PublicHeader2>
        <div>
          <div
            className='invoke_table'
            style={{ display: this.state.tblAndPageInfoDisplay }}
          >
            <TableList
              pagination={this.state.pagination}
              thArray={this.state.thArr}
              receivedChildData={this.receivedChildData}
            ></TableList>
          </div>
          <div
            className='process_form'
            style={{ display: this.state.formDisplay }}
          >
            <EditForm
              tagetedSelectd={this.state.tagetedSelectd}
              receivedChildData={this.receivedChildData}
            ></EditForm>
          </div>
          <div
            className='page_info_div'
            style={{ display: this.state.tblAndPageInfoDisplay }}
          >
            <PageInfo
              pagination={this.state.pagination}
              targetPageNum={this.state.targetPage}
              receiveData={this.receivedChildData}
            ></PageInfo>
          </div>
        </div>
        <Portals2
          isExhibit={this.state.whetherExhibit}
          msg={this.state.message}
        ></Portals2>
      </div>
    )
  }
}
export default Major
