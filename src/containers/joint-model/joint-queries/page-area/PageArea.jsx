import React, { Component } from 'react'
import './PageArea.less'
import PropTypes from 'prop-types'
import Portals from '@/components/popup-window/portals/portals'
import { commonUtil } from '@/api/common2'
import { connect } from 'react-redux'
import { actionsCollect } from '@/redux/redux-drill/actions'

class PageArea extends Component {
  constructor(props) {
    super(props)
    console.info('%cPageArea Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cPageArea componentDidMount\n', 'color:red', this)
  }

  componentDidUpdate(prevProps, prevState) {
    console.log('%c PageArea componentDidUpdate.this', 'color:red', this)
  }

  state = {
    optionArray: [
      { name: '请选择每页展示行数', value: undefined },
      { name: '每页4行', value: 4 },
      { name: '每页6行', value: 6 },
      { name: '每页8行', value: 8 },
      { name: '每页10行', value: 10 },
    ],
    //
    page: '',
    limit: '',
    roomNum: '',
    //
    whetherExhibit: true,
    msg: '',
  }

  static propTypes = {
    receivedChildData: PropTypes.func,
  }

  handleChanging = (e) => {
    // console.log('%c handleChanging.event', this.color(), e.target)
    this.setState({
      [e.target.name]: e.target.value,
    })
  }

  /**
   * 检验参数
   * @returns
   */
  validateParams = () => {
    let { page, limit, roomNum } = this.state
    let paramsArr = [
      { name: '跳转页码', val: page },
      { name: '每页展示行数', val: limit },
      { name: '机房房间编号', val: roomNum },
    ]
    return commonUtil.verifyArrObj(paramsArr)
  }

  jumpSpecifyPage = () => {
    let { page, limit, roomNum } = this.state
    let { pagination } = this.props
    //
    let resObj = this.validateParams()
    if (!resObj.isVerify) {
      this.setState({
        msg: resObj.hint,
        whetherExhibit: !this.state.whetherExhibit,
      })
      return
    }
    //
    if (page > pagination.totalPages) {
      this.setState({
        msg: '跳转页码不得大于总页数',
        whetherExhibit: !this.state.whetherExhibit,
      })
      return
    }
    if (page < 1) {
      this.setState({
        msg: '跳转页码不得小于第一页',
        whetherExhibit: !this.state.whetherExhibit,
      })
      return
    }
    //
    let pageData = { page, limit, roomNum }
    console.log('page=', page, 'limit=', limit, 'roomNum=', roomNum)
    this.props.conveyPageParam(pageData)
    //
    let pageParam = {
      classMethod: 'PageArea_JumpSpecifyPage',
      pageData,
    }
    this.props.receivedChildData(pageParam)
  }

  jumpPreviousPage = () => {
    // let { page } = this.state
    let { currentPage, hasPrevious } = this.props.pagination
    if (!hasPrevious) {
      this.setState({
        msg: '无上一页了',
        whetherExhibit: !this.state.whetherExhibit,
      })
      return
    }
    //
    let pageParam = {
      classMethod: 'PageArea_jumpPreviousPage',
      pageData: { pagePrevious: currentPage - 1 },
    }
    this.props.receivedChildData(pageParam)
  }

  jumpNextPage = () => {
    let { currentPage, hasNext } = this.props.pagination
    if (!hasNext) {
      this.setState({
        msg: '无下一页了',
        whetherExhibit: !this.state.whetherExhibit,
      })
      return
    }
    //
    let pageParam = {
      classMethod: 'PageArea_jumpNextPage',
      pageData: { pageNext: currentPage + 1 },
    }
    this.props.receivedChildData(pageParam)
  }

  jumpFirstPage = () => {
    let pageParam = {
      classMethod: 'PageArea_jumpFirstPage',
      pageData: { pageFirst: 1 },
    }
    this.props.receivedChildData(pageParam)
  }

  jumpLastPage = () => {
    let { pagination } = this.props
    let pageParam = {
      classMethod: 'PageArea_jumpLastPage',
      pageData: { pageLast: pagination.totalPages },
    }
    this.props.receivedChildData(pageParam)
  }

  render() {
    return (
      <div className='main-PageArea'>
        <div className='major_page_area'>
          <div className='pages_list_unorder_div'>
            <ul>
              <li>
                共<b>{this.props.pagination.totalPages}</b>页
              </li>
              <li>
                第<b>{this.props.pagination.currentPage}</b>页
              </li>
              <li onClick={this.jumpPreviousPage}>上一页</li>
              <li onClick={this.jumpNextPage}>下一页</li>
              <li onClick={this.jumpFirstPage}>第一页</li>
              <li onClick={this.jumpLastPage}>最后页</li>
            </ul>
          </div>
          <div className='second_wrapper'>
            <div className='select_rows_div'>
              <select
                id='sele_room_id'
                name='roomNum'
                onChange={this.handleChanging.bind(this)}
              >
                <option value={''}>请选择机房房号</option>
                {this.props.roomList.map((item, index) => {
                  return (
                    <option key={index} value={item}>
                      {item}号机房
                    </option>
                  )
                })}
              </select>
            </div>
            <div className='select_rows_div'>
              <select
                id='sele_row_id'
                name='limit'
                onChange={this.handleChanging.bind(this)}
              >
                {this.state.optionArray.map((item, index) => {
                  return (
                    <option key={index} value={item.value}>
                      {item.name}
                    </option>
                  )
                })}
              </select>
            </div>
            <div className='decide_jump_div'>
              <div className='inputs_jump_div'>
                <span>
                  前往第
                  <input
                    type='number'
                    name='page'
                    onChange={this.handleChanging.bind(this)}
                  />
                  页
                </span>
              </div>
              <div className='clicked_decide_jump'>
                <button className='event_button' onClick={this.jumpSpecifyPage}>
                  跳转
                </button>
              </div>
            </div>
          </div>
        </div>
        <div className='PortalsDiv'>
          <Portals
            isExhibit={this.state.whetherExhibit}
            msg={this.state.msg}
          ></Portals>
        </div>
      </div>
    )
  }
}

/*mapStateToProps是取值方法，mapDispatchToProps是赋值方法*/
const mapStateToProps = (state) => {
  console.log('Table.mapStateToProps.state', state)
  return {
    pagination: state.deliverDataReducer.pagination,
    roomList: state.deliverDataReducer.roomList,
  }
}

const mapDispatchToProps = {
  conveyPageParam: actionsCollect.conveyPageRoomParam,
}

export default connect(mapStateToProps, mapDispatchToProps)(PageArea)
