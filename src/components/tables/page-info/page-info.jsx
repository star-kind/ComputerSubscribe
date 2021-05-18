import React, { Component } from 'react'
import PropTypes from 'prop-types'
import './page-info.less'

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

  //父子组件数据同步
  static getDerivedStateFromProps(nextProps, prevState) {
    console.log('nextProps', nextProps)
    console.log('prevState', prevState)
    let { currentPage } = nextProps.pagination
    let { targetPageNum } = nextProps

    // 当传入的 props 发生变化的时候，更新state
    if (currentPage !== prevState.presentPage) {
      return {
        presentPage: currentPage,
      }
    } else if (targetPageNum === prevState.targetOrder) {
      return {
        presentPage: targetPageNum,
      }
    }
    // 否则，对于state不进行任何操作
    return null
  }

  // UNSAFE_componentWillReceiveProps(nextProps) {
  //   this.initializePresent(nextProps.pagination.currentPage)
  // }

  state = {
    //自设定每页展示行数
    rowsDefineArr: [
      { num: '每页展示行数' },
      { num: 5 },
      { num: 8 },
      { num: 10 },
      { num: 12 },
    ],
    //目标页
    targetOrder: this.props.targetPageNum,
    //决定展示的每页行数
    defineRowNum: '',
    //当前页
    presentPage: 1,
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

  deliverData = () => {
    //发送跳转页码+每页展示行数给父组件
    let data = {
      targetOrder: this.state.targetOrder,
      //默认每页展示10行
      defineRowNum:
        this.state.defineRowNum === this.state.rowsDefineArr[0].num ||
        this.state.defineRowNum === ''
          ? this.state.rowsDefineArr[3].num
          : this.state.defineRowNum,
    }
    this.props.receiveData(data)
  }

  //前往上一页
  toPreviousPage = () => {
    let { presentPage } = this.state
    console.log('%c presentPage', this.getColor(), presentPage)
    let data = { previousOnePage: presentPage - 1 }
    this.props.receiveData(data)
  }

  //前往下一页
  toNextPage = () => {
    let { presentPage } = this.state
    console.log('%c presentPage', this.getColor(), presentPage)
    let data = { nextOnePage: presentPage + 1 }
    this.props.receiveData(data)
  }

  // 绑定 on select 事件
  handleChangeSelect = (e) => {
    console.log(e.target)
    //触发onChange事件时,得到的值
    var rowValue = e.target.value
    console.log('%cRowValue=', 'color:green', rowValue)
    this.setState({
      defineRowNum: rowValue,
    })
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
            onChange={this.handleChangeSelect}
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
            <li onClick={this.toPreviousPage}>上一页</li>
          </p>
        </div>
        <div className='con_div'>
          <p className='next_page'>
            <li onClick={this.toNextPage}>下一页</li>
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
              <button onClick={this.deliverData} className='jump_btn'>
                前往
              </button>
            </span>
          </p>
        </div>
      </div>
    )
  }
}
export default PageInfo
