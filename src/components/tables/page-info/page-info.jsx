import React, { Component } from 'react'
import PropTypes from 'prop-types'
import './page-info.less'

//展示分页表信息,本组件只作展示,与后台交互在集约组件中实现
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
  }

  static propTypes = {
    pagination: PropTypes.object,
    targetPageNum: PropTypes.number,
  }

  //绑定on change事件,使input输入框能动态取值和赋值
  handleChange = (event) => {
    console.log('event\n', event)
    //
    this.setState({
      targetOrder: event.target.value,
    })
  }

  jumpTargetPage = () => {
    //如果目标页大于总页,警告
    let { totalPages } = this.props.pagination
    let { targetOrder } = this.state
    if (targetOrder > totalPages) {
      console.log('jumpTargetPage: 目标页超过总页数')
      return
    }
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

  render() {
    return (
      <div className='main_co_page'>
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
                value={this.state.targetOrder}
                onChange={this.handleChange.bind(this)}
              />
              页
            </span>
            <span>
              <button onClick={this.jumpTargetPage} className='jump_btn'>
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
