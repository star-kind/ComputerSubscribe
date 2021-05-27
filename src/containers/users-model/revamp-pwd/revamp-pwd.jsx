import React, { Component } from 'react'
import PublicHeader from '@/components/header/header-index'
import axios from 'axios'
import Portals2 from '@/components/popup-window/portals2/portals2'
import { getValueFromLocal, verifyDataItemNull } from '@/api/common'
import './revamp-pwd.less'

export default class RevampPwd extends Component {
  constructor(props) {
    super(props)
    //
    this.state = {
      preNewPasswd: '',
      //新的密码
      newPasswd: '',
      //旧的密码
      oldPasswd: '',
      //token
      token: '',
      //是否展示
      whetherExhibit: true,
      message: '',
    }
  }

  //
  componentDidMount() {
    console.log('RevampPwd component did mount')
    console.log(this)
  }

  //绑定on change事件,使input输入框能动态取值和赋值
  handleChange = (event) => {
    console.log('event.target\n', event.target)
    // e.target.name代表当前输入Input框对应的Name,如email,realName
    // e.target.value代表当前输入的值
    this.setState({
      [event.target.name]: event.target.value,
    })
  }

  /**
   *
   * @returns
   */
  validatedNull = () => {
    let { oldPasswd, preNewPasswd, newPasswd } = this.state
    var dateArr = [
      { name: '原密码', val: oldPasswd },
      { name: '新密码', val: preNewPasswd },
      { name: '再确认的新密码', val: newPasswd },
    ]
    return verifyDataItemNull(dateArr)
  }

  /**
   *
   * @returns
   */
  checkPwdIsRight = () => {
    let { preNewPasswd, newPasswd, oldPasswd } = this.state
    let result = { isRight: true, hint: '' }
    //
    if (preNewPasswd !== newPasswd) {
      result.isRight = false
      result.hint = '两次输入的新密码不一致'
    } else if (oldPasswd === newPasswd) {
      result.isRight = false
      result.hint = '旧密码不能与新密码相同'
    }
    console.log('checkPwdIsRight.result\n', result)
    return result
  }

  handleSubmit = (event) => {
    let ts = this
    //阻止默认事件
    event.preventDefault()
    //
    let res = ts.validatedNull()
    if (!res.isValidate) {
      this.setState({
        whetherExhibit: !ts.state.whetherExhibit,
        message: res.alertText,
      })
      return
    }
    //
    let right = ts.checkPwdIsRight()
    if (!right.isRight) {
      ts.setState({
        whetherExhibit: !ts.state.whetherExhibit,
        message: right.hint,
      })
      return
    }
    //
    let data = {
      oldPasswd: ts.state.oldPasswd,
      preNewPasswd: ts.state.preNewPasswd,
      newPasswd: ts.state.newPasswd,
    }
    //
    var tokenObj = getValueFromLocal(ts.store_key.token_key)
    console.log('tokenObj\n', tokenObj)
    if (tokenObj.code === -1) {
      ts.setState({
        whetherExhibit: !ts.state.whetherExhibit,
        message: tokenObj.text,
      })
      return
    }
    //
    axios
      .get(ts.interfaces.revisePassword, {
        params: { newPasswd: data.newPasswd, oldPasswd: data.oldPasswd },
        headers: { token: tokenObj.text },
      })
      .then((resp) => {
        console.info('resp\n', resp)
        if (resp.data.code === 200) {
          ts.setState({
            whetherExhibit: !ts.state.whetherExhibit,
            message: '密码修改成功,即将前往登录页重新登录',
          })
          //
          setTimeout(() => {
            this.props.history.push(this.user_urls.login_url)
          }, 5 * 1000)
        } else {
          ts.setState({
            whetherExhibit: !ts.state.whetherExhibit,
            message: resp.data.message,
          })
        }
      })
      .catch((err) => {
        console.error(err)
      })
  }

  render() {
    return (
      <div className='main_container'>
        <PublicHeader></PublicHeader>
        {/*  */}
        <div className='regist_container'>
          <div className='mine_regist_div'>
            <form
              className='mine_regist_form'
              onSubmit={this.handleSubmit.bind(this)}
            >
              <div className='form_body'>
                <div className='inputs_item param_major_item'>
                  <input
                    onChange={this.handleChange.bind(this)}
                    defaultValue={this.state.oldPasswd}
                    name='oldPasswd'
                    type='password'
                    id='old_password'
                    placeholder='请输入旧密码'
                    maxLength='16'
                  />
                </div>
                <div className='inputs_item param_major_item'>
                  <input
                    onChange={this.handleChange.bind(this)}
                    defaultValue={this.state.preNewPasswd}
                    name='preNewPasswd'
                    type='password'
                    id='pre_new_password'
                    placeholder='请输入新密码'
                    maxLength='16'
                  />
                </div>
                <div className='inputs_item param_major_item'>
                  <input
                    onChange={this.handleChange.bind(this)}
                    defaultValue={this.state.newPasswd}
                    name='newPasswd'
                    type='password'
                    id='new_password'
                    placeholder='请再次输入确认密码'
                    maxLength='16'
                  />
                </div>
              </div>
              <div className='mine_input_btns'>
                <div className='item_input_btn' id='reset_item'>
                  <input type='reset' value='复位' />
                </div>
                <div className='item_input_btn' id='submit_item'>
                  <input type='submit' value='修改密码' />
                </div>
              </div>
            </form>
          </div>
        </div>
        {/*  */}
        <Portals2
          msg={this.state.message}
          isExhibit={this.state.whetherExhibit}
        ></Portals2>
      </div>
    )
  }
}
