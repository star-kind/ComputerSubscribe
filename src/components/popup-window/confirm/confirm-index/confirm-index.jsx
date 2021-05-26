import React, { Component } from 'react'
import './confirm-index.less'
import PropTypes from 'prop-types'
import ConfirmModal from '@/components/popup-window/confirm/confirm-modal/confirm-modal'

class ConfirmIndex extends Component {
  constructor(props) {
    super(props)
    console.info('%cConfirmIndex Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cConfirmIndex componentDidMount\n', 'color:red', this)
  }

  componentDidUpdate(prevProps, prevState) {
    console.log(
      '%c ConfirmIndex componentDidUpdate.prevProps',
      'color:blue',
      prevProps
    )
    console.log(
      '%c ConfirmIndex componentDidUpdate.prevState',
      'color:green',
      prevState
    )
    console.log('%c ConfirmIndex componentDidUpdate.this', 'color:red', this)
  }

  state = {
    // 是否展示,真-显示,假-隐藏
    whetherExhibit: false,
    //节点名称
    modalIdName: 'confirm-modal-root',
  }

  static propTypes = {
    whetherExhibit: PropTypes.bool.isRequired,
    msg: PropTypes.string.isRequired,
    //
    receiveData: PropTypes.func,
  }

  //控制modal元素的开关-显隐
  handleModalEle() {
    this.setState({
      whetherExhibit: !this.state.whetherExhibit,
    })
  }

  //取消确认
  cancelConfirm = () => {
    console.log('%c cancelConfirm', this.color(), 'ConfirmIndex-cancelConfirm')
    this.handleModalEle()
    //
    let data = {
      method: 'cancelConfirm',
      instruct: 0,
      comment: '取消确认',
    }
    this.sendDataToUp(data)
  }

  //同意确认
  confirmPass = () => {
    this.handleModalEle()
    console.log('%c confirmPass', this.color(), 'ConfirmIndex-confirmPass')
    //
    let data = {
      method: 'confirmPass',
      instruct: 1,
      comment: '确认同意',
    }
    this.sendDataToUp(data)
  }

  /**
   * 向父组件传值,0-取消,1-同意
   * @param {*} data
   */
  sendDataToUp = (data) => {
    console.log('%c sendDataToUp.data', this.color(), data)
    this.props.receiveData(data)
  }

  render() {
    return (
      <div className='mainConfirmIndex'>
        <div id='confirm_index_root'>
          {this.state.whetherExhibit === this.props.whetherExhibit ? (
            <ConfirmModal
              whetherExhibit={this.state.whetherExhibit}
              message={this.props.msg}
              confirmPass={this.confirmPass.bind(this)}
              cancelConfirm={this.cancelConfirm.bind(this)}
              modalIdName={this.state.modalIdName}
            />
          ) : null}
        </div>
        <div id={this.state.modalIdName}></div>
      </div>
    )
  }
}
export default ConfirmIndex
