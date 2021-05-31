import React, { Component } from 'react'
import './major.less'
// import PropTypes from 'prop-types'
import { getValueFromLocal } from '@/api/common'
import Table from '@/containers/joint-model/joint-queries/table/table'
import { connect } from 'react-redux'
import {
  jointQueryTbl,
  getTblHeadArr,
  getRoomList,
} from '@/redux/redux-drill/actions'
import PublicHeader2 from '@/components/header2/header2'
import Portals from '@/components/popup-window/portals/portals'
import PageArea from '@/containers/joint-model/joint-queries/page-area/PageArea'
import DetailForm from '@/containers/joint-model/joint-queries/detail-form/DetailForm'

/*
联合查询,一间机房本周内,所收到的预约申请列表集合
含(审核状态+审核教师工号+申请者学号+申请使用日+申请使用时段)
(申请者姓名+申请者邮箱+审核人姓名+审核人邮箱)
*/
class Major extends Component {
  constructor(props) {
    super(props)
    console.info('%cMajor Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cMajor componentDidMount\n', 'color:red', this)
    this.handleGetRoomList()
  }

  componentDidUpdate(prevProps, prevState) {
    console.log(
      '%c Major_componentDidUpdate_this',
      'color:red',
      this,
      'prevState',
      prevState,
      'prevProps',
      prevProps
    )
  }

  shouldComponentUpdate(nextProps, nextState) {
    console.log(
      '%c Major shouldComponentUpdate.nextProps',
      'color:green',
      nextProps,
      'nextState',
      nextState
    )
    return true
  }

  state = {
    whetherExhibit: true,
    message: '',
    //
    page: 1,
    limit: 6,
    roomNum: '',
    //
    pagination: {
      data: [],
      hasPrevious: false,
      hasNext: false,
      totalPages: 0,
      currentPage: 1,
      rows: 0,
    },
    //
    tblHeadArr: [
      '预约单ID',
      '用户名',
      '学号',
      '申请人学号',
      '预约发起时间',
      '申请使用日期',
      '审核时间',
      '电子邮箱',
      '审核者工号',
      '申请者角色',
      '申请机房房号',
      '申请时用时段',
      '预约单现状态',
      '操作',
    ],
  }

  static propTypes = {}

  //根据子组件的变化,父组件作出对应变化
  receivedChildData = (childData) => {
    console.log('%c receivedChildData_childData', this.color(), childData)
    switch (childData.classMethod) {
      case 'PageArea_JumpSpecifyPage':
        this.retrievePageByParam(childData)
        break

      case 'PageArea_jumpPreviousPage':
        this.handleGetPaginationByPage(childData.pageData.pagePrevious)
        break

      case 'PageArea_jumpNextPage':
        this.handleGetPaginationByPage(childData.pageData.pageNext)
        break

      case 'PageArea_jumpFirstPage':
        this.handleGetPaginationByPage(childData.pageData.pageFirst)
        break

      case 'PageArea_jumpLastPage':
        this.handleGetPaginationByPage(childData.pageData.pageLast)
        break

      case 'DetailForm_handleSubmits':
        this.modifiedSubscribe(childData.parameter)
        break

      default:
        break
    }
  }

  //即将被修改的预约--ending engineering
  modifiedSubscribe = (parameter) => {
    let { dataArrIndex } = this.props
    console.log(
      '%c modifiedSubscribe_parameter',
      this.color(),
      parameter,
      'dataArrIndex',
      dataArrIndex
    )
  }

  retrievePageByParam = (data) => {
    let { pageData } = data
    let { limit, page, roomNum } = this.state
    //
    if (
      pageData.limit === limit &&
      pageData.page === page &&
      pageData.roomNum === roomNum
    ) {
      this.setState({
        message: '当前页面已是想要查询的结果',
        whetherExhibit: !this.state.whetherExhibit,
      })
      return
    }
    this.handleGetPagination(pageData)
  }

  /**
   *
   * @returns
   */
  verifyToken = () => {
    let tokenObj = getValueFromLocal(this.store_key.token_key)
    console.log('tokenObj\n', tokenObj)
    //token
    if (tokenObj.code === -1) {
      this.setState({
        message: tokenObj.text,
        whetherExhibit: !this.state.whetherExhibit,
      })
      return
    } else {
      return tokenObj
    }
  }

  //获取机房编号数组
  handleGetRoomList = () => {
    let _this = this
    let token = _this.verifyToken()
    //
    _this
      .gets(_this.interfaces.retrieveRoomNumArr, {}, { token: token.text })
      .then((result) => {
        console.log('%c result', _this.getColor(), result)
        if (result.data.code === 200) {
          _this.setState(
            {
              roomNum: result.data.data[0],
            },
            () => {
              let { roomNum } = _this.state
              console.log('this_state_roomNum', roomNum)
              _this.handleGetPaginationInit(roomNum)
            }
          )
          _this.props.setRoomList(result.data.data)
        } else {
          _this.setState({
            message: result.data.message,
            whetherExhibit: !_this.state.whetherExhibit,
          })
        }
      })
      .catch((err) => {
        console.error(err)
      })
  }

  /**
   * 上页,下页,首页,尾页
   * @param {*} page
   */
  handleGetPaginationByPage = (page) => {
    let ts = this
    let tokenObj = ts.verifyToken()
    let { limit, roomNum } = ts.state
    console.log('page=>', page, 'limit=>', limit, 'roomNum=>', roomNum)
    //
    ts.gets(
      ts.interfaces.retrieveSubscribes,
      {
        page: page,
        limit: limit,
        roomNum: roomNum,
      },
      { token: tokenObj.text }
    ).then((res) => {
      console.log('RESPONSE\n', res)
      if (res.data.code === 200) {
        ts.props.queryTbl(res.data.data)
        ts.props.setTblHeadArr(ts.state.tblHeadArr)
      } else {
        console.log('res.data.message', res.data.message)
        ts.setState({
          message: res.data.message,
          whetherExhibit: !this.state.whetherExhibit,
        })
      }
    })
  }

  handleGetPagination = (pageData) => {
    let ts = this
    let tokenObj = ts.verifyToken()
    let { page, limit, roomNum } = pageData
    console.log('page=>', page, 'limit=>', limit, 'roomNum=>', roomNum)
    //
    ts.gets(
      ts.interfaces.retrieveSubscribes,
      {
        page: page,
        limit: limit,
        roomNum: roomNum,
      },
      { token: tokenObj.text }
    ).then((res) => {
      console.log('RESPONSE\n', res)
      if (res.data.code === 200) {
        ts.props.queryTbl(res.data.data)
        ts.props.setTblHeadArr(ts.state.tblHeadArr)
      } else {
        console.log('res.data.message', res.data.message)
        ts.setState({
          message: res.data.message,
          whetherExhibit: !this.state.whetherExhibit,
        })
      }
    })
  }

  handleGetPaginationInit = (room) => {
    let ts = this
    let tokenObj = ts.verifyToken()
    let { page, limit } = ts.state
    console.log('%c handleGetPaginationInit_room', ts.color(), room)
    ts.gets(
      ts.interfaces.retrieveSubscribes,
      {
        page: page,
        limit: limit,
        roomNum: room,
      },
      { token: tokenObj.text }
    ).then((res) => {
      console.log('RESPONSE\n', res)
      if (res.data.code === 200) {
        ts.props.queryTbl(res.data.data)
        ts.props.setTblHeadArr(ts.state.tblHeadArr)
      } else {
        console.log('res.data.message', res.data.message)
        ts.setState({
          message: res.data.message,
          whetherExhibit: !this.state.whetherExhibit,
        })
      }
    })
  }

  render() {
    return (
      <div className='main-major'>
        <div className='PublicHeader2Div'>
          <PublicHeader2></PublicHeader2>
        </div>
        <div
          className='TableDiv'
          style={{ display: this.props.displays.tblAndPageDisPlay }}
        >
          <Table></Table>
        </div>
        <div
          className='DetailFormDiv'
          style={{ display: this.props.displays.formDisPlay }}
        >
          <DetailForm receivedChildData={this.receivedChildData}></DetailForm>
        </div>
        <div
          className='PageAreaDiv'
          style={{ display: this.props.displays.tblAndPageDisPlay }}
        >
          <PageArea receivedChildData={this.receivedChildData}></PageArea>
        </div>
        <div className='PortalsDiv'>
          <Portals
            isExhibit={this.state.whetherExhibit}
            msg={this.state.message}
          ></Portals>
        </div>
        <div>
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
        </div>
      </div>
    )
  }
}

/*mapStateToProps:取值方法,mapDispatchToProps:赋值方法*/
const mapStateToProps = (state) => {
  console.log('Major.mapStateToProps.state', state)
  return {
    pagination: state.deliverDataReducer.pagination,
    tblHeadArr: state.deliverDataReducer.tblHeadArr,
    displays: state.deliverDataReducer.displays,
    pageRoom: state.deliverDataReducer.pageRoom,
    roomList: state.deliverDataReducer.roomList,
    dataArrIndex: state.deliverDataReducer.indexItem.index,
  }
}

const mapDispatchToProps = {
  queryTbl: jointQueryTbl,
  setTblHeadArr: getTblHeadArr,
  setRoomList: getRoomList,
}

export default connect(mapStateToProps, mapDispatchToProps)(Major)
