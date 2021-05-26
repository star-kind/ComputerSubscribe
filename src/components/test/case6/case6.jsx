import React, { Component } from 'react'
import './case6.less'
// import PropTypes from 'prop-types'
import PublicHeader from '@/components/header/header-index'
import ConfirmIndex from '@/components/popup-window/confirm/confirm-index/confirm-index'

class Case6 extends Component {
  constructor(props) {
    super(props)
    console.info('%cCase6 Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cCase6 componentDidMount\n', 'color:red', this)
  }

  componentDidUpdate(prevProps, prevState) {
    console.log(
      '%c Case6 componentDidUpdate.prevProps',
      'color:blue',
      prevProps
    )
    console.log(
      '%c Case6 componentDidUpdate.prevState',
      'color:green',
      prevState
    )
    console.log('%c Case6 componentDidUpdate.this', 'color:red', this)
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
    console.log('%c receiveData.data', this.color(), data)
    //
    this.setState({
      confirmData: data,
    })
  }

  /**
   * 监听指令
   */
  listeningInstruct = () => {
    let { instruct } = this.state.confirmData
    if (instruct === 1) {
      this.loginout()
    } else {
      console.log('%cinstruct', this.color(), instruct)
    }
  }

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
      <div className='main-case6'>
        <div className='pub_head_div'>
          <PublicHeader></PublicHeader>
        </div>
        <div>
          <div className='outside-item'>
            <div>
              <button
                className='button-item'
                onClick={this.outLogin.bind(this)}
              >
                退出登录
              </button>
            </div>
          </div>
        </div>
        <div>
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
export default Case6
