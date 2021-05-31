import React, { Component } from 'react'
import PropTypes from 'prop-types'
import './page-info.less'
import Portals from '@/components/popup-window/portals/portals'

//展示表分页信息
class PageInfo extends Component {
  constructor(props) {
    super(props)
    console.info('PageInfo--Constructor')
  }

  componentDidMount() {
    console.log('PageInfo component did mount')
    console.log(this)
  }

  //这个钩子可进行父子组件之间更新数据，进行渲染
  componentDidUpdate(prevProps, prevState) {
    console.log(
      '%c PageInfo.componentDidUpdate.prevProps',
      this.getColor(),
      prevProps
    )
    console.log(
      '%c PageInfo.componentDidUpdate.prevState',
      this.getColor(),
      prevState
    )
    let { currentPage } = this.props.pagination
    //
    if (prevState.presentPage !== currentPage) {
      this.setState({
        presentPage: currentPage,
      })
    }
    console.log('%c PageInfo.componentDidUpdate.This', this.getColor(), this)
  }

  state = {
    //自设定每页展示行数
    rowsDefineArr: [
      { num: '每页展示行数' },
      { num: 3 },
      { num: 5 },
      { num: 8 },
      { num: 10 },
    ],
    //目标页
    targetOrder: this.props.targetPageNum,
    //决定展示的每页行数
    defineRowNum: '',
    //当前页
    presentPage: 1,
    //
    msg: '',
    whetherExhibit: true,
  }

  static propTypes = {
    pagination: PropTypes.object,
    targetPageNum: PropTypes.number,
    receiveData: PropTypes.func,
  }

  //绑定on change事件,使input输入框能动态取值和赋值
  handleChange = (event) => {
    console.log('page.info.handleChange.event', event)
    let val = event.target.value
    console.log('typeof val', typeof val)
    //
    val = Number(val)
    this.setState({
      targetOrder: val,
    })
  }

  //设置默认显示行数
  getDefaultDefineRow = () => {
    let { rowsDefineArr, defineRowNum } = this.state
    console.log('setDefaultDefineRow.defineRowNum', defineRowNum)
    //
    if (defineRowNum === '') {
      defineRowNum = rowsDefineArr[2].num
    }
    return defineRowNum
  }

  jumpSpecifyPage = () => {
    let { targetOrder } = this.state
    let { totalPages, currentPage } = this.props.pagination
    //默认展示行数
    let row = this.getDefaultDefineRow()
    //
    if (targetOrder < 1) {
      this.setState({
        whetherExhibit: !this.state.whetherExhibit,
        msg: '跳转页码不得小于第一页',
      })
      return
    } else if (targetOrder > totalPages) {
      this.setState({
        whetherExhibit: !this.state.whetherExhibit,
        msg: '跳转页数不得大于总页数',
      })
      return
    } else if (targetOrder === currentPage) {
      this.setState({
        whetherExhibit: !this.state.whetherExhibit,
        msg: '跳转页业已在当前页了',
      })
      return
    } else if (targetOrder === 0) {
      this.setState({
        whetherExhibit: !this.state.whetherExhibit,
        msg: '跳转页不得为空',
      })
      return
    }
    //发送跳转页码+每页展示行数给父组件
    let data = {
      targetOrder: targetOrder,
      //默认每页展示行数
      defineRowNum: row,
      method: 'jumpSpecifyPage',
    }
    this.props.receiveData(data)
  }

  //前往上一页
  jumpToPreviousPage = () => {
    //默认展示行数
    let rows = this.getDefaultDefineRow()
    //
    let { presentPage } = this.state
    let { hasPrevious } = this.props.pagination
    console.log(
      '%c jumpToPreviousPage-presentPage',
      this.getColor(),
      presentPage
    )
    //
    if (!hasPrevious) {
      this.setState({
        whetherExhibit: !this.state.whetherExhibit,
        msg: '已无上一页了',
      })
      return
    }
    //
    let data = {
      defineRowNum: rows,
      previousOnePage: presentPage - 1,
      method: 'jumpToPreviousPage',
    }
    this.props.receiveData(data)
  }

  //前往下一页
  jumpToNextPage = () => {
    //默认展示行数
    let rows = this.getDefaultDefineRow()
    //
    let { presentPage } = this.state
    let { hasNext } = this.props.pagination
    console.log(
      '%c presentPage',
      this.getColor(),
      presentPage,
      'hasNext',
      hasNext,
      'rows',
      rows
    )
    //
    if (!hasNext) {
      this.setState({
        whetherExhibit: !this.state.whetherExhibit,
        msg: '业已是尾页了',
      })
      return
    }
    //
    let data = {
      defineRowNum: rows,
      nextOnePage: presentPage + 1,
      method: 'jumpToNextPage',
    }
    this.props.receiveData(data)
  }

  //初始化 state.当前页,currentPage from props
  initializePresent = (currentPage) => {
    let present = 1
    if (currentPage < 1) {
      console.info('%c current-page<1=', this.getColor(), currentPage)
    } else if (currentPage === undefined) {
      console.info('%c undefined.current-page=', this.getColor(), currentPage)
    } else if (isNaN(currentPage)) {
      console.info('%c NaN.current-page=', this.getColor(), currentPage)
    } else {
      console.info('%c current-ordinary=', this.getColor(), currentPage)
      present = currentPage
    }
    this.setState({
      presentPage: present,
    })
  }

  render() {
    return (
      <div className='main_co_page'>
        <div className='con_div'>
          <select
            onChange={this.handleChange.bind(this)}
            name='defineRowNum'
            className='sele_page_order'
          >
            {this.state.rowsDefineArr.map((element, index) => {
              return (
                <option key={element.num} value={element.num}>
                  {index > 0 ? '每页' + element.num + '行' : element.num}
                </option>
              )
            })}
          </select>
        </div>
        {/*  */}
        <div className='con_div'>
          <p>
            共<strong>{this.props.pagination.totalPages}</strong>页
          </p>
        </div>
        <div className='con_div'>
          <p className='current_page'>
            第<strong>{this.state.presentPage}</strong>页
          </p>
        </div>
        <div className='con_div'>
          <p className='previous_page'>
            <li onClick={this.jumpToPreviousPage}>上一页</li>
          </p>
        </div>
        <div className='con_div'>
          <p className='next_page'>
            <li onClick={this.jumpToNextPage}>下一页</li>
          </p>
        </div>
        <div className='con_div' id='input_div'>
          <p>
            <span>
              转至第
              <input
                type='number'
                className='pageth_input'
                defaultValue={this.state.targetOrder}
                onChange={this.handleChange.bind(this)}
              />
              页
            </span>
            <span>
              <button onClick={this.jumpSpecifyPage} className='jump_btn'>
                前往
              </button>
            </span>
          </p>
        </div>
        <Portals
          isExhibit={this.state.whetherExhibit}
          msg={this.state.msg}
        ></Portals>
      </div>
    )
  }
}

export default PageInfo
