import React, { Component } from 'react'
import ReactDOM from 'react-dom'
import PropTypes from 'prop-types'
import './modal.less'

/**
 * 插槽组件
 */
class Modal extends Component {
  constructor(props) {
    super(props)
    //
    this.el = document.createElement('div')
    this.modalRoot = document.getElementById('modal_root')
    // console.log('%c ModalRoot', this.getColor(), this.modalRoot)
  }

  componentDidMount() {
    console.log('MessageContentModalComponentDidMount')
    //将插入弹窗的div放入到兄弟节点“modal_root”中
    this.modalRoot.appendChild(this.el)
    console.log(this)
  }

  state = {
    cssMessage: {
      paddingLeft: '8px',
      margin: '0',
    },
  }

  static propTypes = {
    message: PropTypes.string.isRequired,
    closeModal: PropTypes.func.isRequired,
    isShow: PropTypes.bool.isRequired,
  }

  //清理工作
  componentWillUnmount() {
    this.modalRoot.removeChild(this.el)
  }

  msgWindowCont = (props) => {
    return (
      <div className='cover'>
        <div className='message_div'>
          <p style={this.state.cssMessage}>{props.message}</p>
        </div>
        <div className='close_btn_hint'>
          <button onClick={props.closeModal}>关闭</button>
        </div>
      </div>
    )
  }

  render() {
    //将创建好的弹窗插入到创建的div元素中
    return ReactDOM.createPortal(this.msgWindowCont(this.props), this.el)
  }
}

export default Modal
