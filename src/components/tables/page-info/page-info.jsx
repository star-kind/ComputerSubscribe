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

  state = {
    //目标页
    targetOrder: this.props.targetPageNum,
    //自设定每页展示行数
    rowsDefineArr: [
      { num: '每页展示行数' },
      { num: 5 },
      { num: 8 },
      { num: 10 },
      { num: 12 },
    ],
    //决定展示的每页行数
    defineRowNum: '',
  }

  static propTypes = {
    pagination: PropTypes.object,
    targetPageNum: PropTypes.number,
    receiveData: PropTypes.func,
  }

  //绑定on change事件,使input输入框能动态取值和赋值
  handleChange = (event) => {
    console.log('event\n', event)
    //
    this.setState({
      targetOrder: event.target.value,
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
    let { hasPrevious } = this.props.pagination
    if (!hasPrevious) {
      console.log('toPreviousPage: 已经是第一页')
      return
    }
  }

  //前往下一页
  toNextPage = () => {
    let { hasNext } = this.props.pagination
    if (!hasNext) {
      console.log('toNextPage: 已经是最后页')
      return
    }
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
            第
            <strong>
              {this.props.pagination.currentPage < 1
                ? 1
                : this.props.pagination.currentPage}
            </strong>
            页
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
