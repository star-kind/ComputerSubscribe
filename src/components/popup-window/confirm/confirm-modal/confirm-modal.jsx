import React, { Component } from 'react'
import './confirm-modal.less'
import PropTypes from 'prop-types'
import ReactDOM from 'react-dom'

class ConfirmModal extends Component {
  constructor(props) {
    super(props)
    console.info('%cConfirmModal Constructor\n', this.color(), this)
    //
    this.confirmModalEle = document.createElement('div')
    this.confirmModalRoot = document.getElementById(this.props.modalIdName)
  }

  componentDidMount() {
    //将插入弹窗的div放入到兄弟节点 confirmModalRoot 中
    this.confirmModalRoot.appendChild(this.confirmModalEle)
    console.log('%cConfirmModal componentDidMount\n', 'color:red', this)
  }

  componentDidUpdate(prevProps, prevState) {
    console.log(
      '%c ConfirmModal componentDidUpdate.prevProps',
      'color:blue',
      prevProps
    )
    console.log(
      '%c ConfirmModal componentDidUpdate.prevState',
      'color:green',
      prevState
    )
    console.log('%c ConfirmModal componentDidUpdate.this', 'color:red', this)
  }

  componentWillUnmount() {
    //清理工作,移除元素节点
    this.confirmModalRoot.removeChild(this.confirmModalEle)
  }

  state = {
    cssMsg: {
      paddingLeft: '8px',
      margin: '0',
    },
  }

  static propTypes = {
    message: PropTypes.string.isRequired,
    cancelConfirm: PropTypes.func.isRequired,
    confirmPass: PropTypes.func.isRequired,
    whetherExhibit: PropTypes.bool.isRequired,
    modalIdName: PropTypes.string.isRequired,
  }

  /**
   *
   * @param {*} props
   * @returns
   */
  getContents = (props) => {
    return (
      <div className='main-confirm-modal'>
        <div className='message_div'>
          <p style={this.state.cssMsg}>{props.message}</p>
        </div>
        <div className='close_buttons'>
          <div className='close_btn_hint' id='confirm_pass'>
            <button onClick={props.confirmPass}>确认</button>
          </div>
          <div className='close_btn_hint' id='close_hint'>
            <button onClick={props.cancelConfirm}>取消</button>
          </div>
        </div>
      </div>
    )
  }

  render() {
    //将创建好的弹窗插入到创建的div元素中
    return ReactDOM.createPortal(
      this.getContents(this.props),
      this.confirmModalEle
    )
  }
}
export default ConfirmModal
