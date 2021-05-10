import React, { Component } from 'react'
import ReactDOM from 'react-dom'
import PropTypes from 'prop-types'
import './model.less'

/**
 * 插槽组件
 */
class Model extends Component {
  constructor(props) {
    super(props)
    this.el = document.createElement('div')
    this.modelRoot = document.getElementById('model_root')
    console.log('modelRoot', this.modelRoot)
  }

  componentDidMount() {
    console.log('MessageContentModelComponentDidMount')
    //将插入弹窗的div放入到兄弟节点“model_root”中
    this.modelRoot.appendChild(this.el)
    console.log(this)
  }

  static propTypes = {
    message: PropTypes.string.isRequired,
    closeModel: PropTypes.func.isRequired,
    isShow: PropTypes.bool.isRequired,
  }

  //清理工作
  componentWillUnmount() {
    this.modelRoot.removeChild(this.el)
  }

  msgWindowCont = (props) => {
    return (
      <div className='cover'>
        <div className='message_div'>
          <p style={{ margin: '0' }}>{props.message}</p>
        </div>
        <div className='close_btn_hint'>
          <button onClick={props.closeModel}>关闭</button>
        </div>
      </div>
    )
  }

  render() {
    //将创建好的弹窗插入到创建的div元素中
    return ReactDOM.createPortal(this.msgWindowCont(this.props), this.el)
  }
}

export default Model
