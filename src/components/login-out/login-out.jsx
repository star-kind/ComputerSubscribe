import React, { Component } from 'react'
import './login-out.less'
// import PropTypes from 'prop-types'
import ConfirmIndex from '@/components/popup-window/confirm/confirm-index/confirm-index'

//退出登录
class LoginOut extends Component {
  constructor(props) {
    super(props)
    console.info('%cLoginOut Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cLoginOut componentDidMount\n', 'color:red', this)
  }

  componentDidUpdate(prevProps, prevState) {
    console.log(
      '%c LoginOut componentDidUpdate.prevProps',
      'color:blue',
      prevProps
    )
    console.log(
      '%c LoginOut componentDidUpdate.prevState',
      'color:green',
      prevState
    )
    console.log('%c LoginOut componentDidUpdate.this', 'color:red', this)
    //
    this.listeningInstruct()
  }

  state = {
    msg: '',
    whetherExhibit: true,
    //
    confirmData: {
      comment: '',
      instruct: null,
      method: '',
    },
  }

  static propTypes = {}

  /**
   *
   * @param {*} data
   */
  receiveData = (data) => {
    console.log('%c(LoginOut)receiveData.data', this.color(), data)
    //
    this.setState({
      confirmData: data,
    })
  }

  /**
   * 实时监听指令
   */
  listeningInstruct = () => {
    let { instruct } = this.state.confirmData
    if (instruct === 1) {
      this.loginout()
    } else {
      console.log('%c listeningInstruct.instruct', this.color(), instruct)
    }
  }

  /**
   *
   */
  outLogin = () => {
    this.setState({
      msg: '确定退出登录吗?',
      whetherExhibit: !this.state.whetherExhibit,
    })
    console.log(
      '%c this.state.confirmData',
      this.color(),
      this.state.confirmData
    )
  }

  render() {
    return (
      <div className='main-login-out'>
        <div className='outLogin-item'>
          <li className='outLogin-li' onClick={this.outLogin.bind(this)}>
            退出登录
          </li>
        </div>
        <div className='ConfirmIndexDiv'>
          <ConfirmIndex
            whetherExhibit={this.state.whetherExhibit}
            msg={this.state.msg}
            receiveData={this.receiveData}
          ></ConfirmIndex>
        </div>
      </div>
    )
  }
}
export default LoginOut
