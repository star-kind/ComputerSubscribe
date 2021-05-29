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
    this.handleGetPagination()
    this.handleGetRoomList()
  }

  componentDidUpdate(prevProps, prevState) {
    // console.log(
    //   '%c Major componentDidUpdate.prevProps',
    //   'color:blue',
    //   prevProps
    // )
    // console.log(
    //   '%c Major componentDidUpdate.prevState',
    //   'color:green',
    //   prevState
    // )
    console.log('%c Major componentDidUpdate.this', 'color:red', this)
  }

  state = {
    whetherExhibit: true,
    message: '',
    //
    page: 1,
    limit: 6,
    roomNum: 161,
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
          // _this.setState({
          //   roomNumArr: result.data.data,
          // })
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

  handleGetPagination = () => {
    const ts = this
    let tokenObj = ts.verifyToken()
    //
    let { page, limit, roomNum } = ts.state
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
        // alert(res.data.message)
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
        <div className='TableDiv'>
          <Table></Table>
        </div>
        <div className='PageAreaDiv'>
          <PageArea></PageArea>
        </div>
        <div className='PortalsDiv'>
          <Portals
            isExhibit={this.state.whetherExhibit}
            msg={this.state.message}
          ></Portals>
        </div>
      </div>
    )
  }
}

/*
mapStateToProps：将需要的state的节点注入到与此视图数据相关的组件(props)上.也即用于建立 State 到 Props 的映射关系. 这个函数中要注入全部的models，你需要返回一个新的对象，挑选该组件所需要的models
*/
const mapStateToProps = (state) => {
  console.log('Major.mapStateToProps.state', state)
  return {
    pagination: state.deliverDataReducer.pagination,
    tblHeadArr: state.deliverDataReducer.tblHeadArr,
  }
}

/* 将需要绑定的响应事件或数据注入到组件上(props上) */
const mapDispatchToProps = {
  queryTbl: jointQueryTbl,
  setTblHeadArr: getTblHeadArr,
  setRoomList: getRoomList,
}

export default connect(mapStateToProps, mapDispatchToProps)(Major)
