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

  //这个钩子可进行父子组件之间更新数据，进行渲染
  componentDidUpdate(prevProps, prevState) {
    console.log('%c PageInfo-prevProps', this.getColor(), prevProps)
    console.log('%c PageInfo-prevState', this.getColor(), prevState)
    console.log('%c PageInfo-This', this.getColor(), this)
    //
    let { targetPageNum } = this.props
    let { presentPage } = this.state
    //
    if (presentPage !== targetPageNum) {
      this.setState({
        presentPage: targetPageNum,
      })
    }
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

  /**
   *
   * @returns
   */
  getDefineRow = () => {
    let { rowsDefineArr, defineRowNum } = this.state
    let defineRow = ''
    console.log('defineRowNum===>', defineRowNum)
    //
    if (defineRowNum === '') {
      defineRow = rowsDefineArr[2].num
    } else {
      defineRow = defineRowNum
    }
    return defineRow
  }

  /**
   *
   */
  deliverData = () => {
    let defineRow = this.getDefineRow()
    //发送跳转页码+每页展示行数给父组件
    let data = {
      targetOrder: this.state.targetOrder,
      defineRowNum: defineRow,
    }
    this.props.receiveData(data)
  }

  //前往上一页
  toPreviousPage = () => {
    let defineRow = this.getDefineRow()
    let { presentPage } = this.state
    //
    let data = { previousOnePage: presentPage - 1, defineRowNum: defineRow }
    //
    console.log('%c toPreviousPage-data', this.getColor(), data)
    this.props.receiveData(data)
  }

  //前往下一页
  toNextPage = () => {
    let defineRow = this.getDefineRow()
    let { presentPage } = this.state
    //
    let data = { nextOnePage: presentPage + 1, defineRowNum: defineRow }
    //
    console.log('%c toNextPage-data', this.getColor(), data)
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
            className='sele_page_order'
            name='defineRowNum'
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
