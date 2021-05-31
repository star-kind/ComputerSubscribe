import React, { Component } from 'react'
import PublicHeader from '@/components/header/header-index'
import { Link } from 'react-router-dom'
import axios from 'axios'
import Portals2 from '@/components/popup-window/portals2/portals2'
import './registry-index.less'
import { verifyDataRegex, verifyDataItemNull } from '@/api/common'

export default class Registry extends Component {
  componentDidMount() {
    console.log('Registry component did mount')
    console.log(this)
  }

  state = {
    roleTypeArray: [
      { name: '--请选择帐号类型--', code: -1 },
      { name: '管理员', code: 0 },
      { name: '教师', code: 1 },
      { name: '学生', code: 2 },
    ],
    //注册资料
    userName: '',
    userNum: '',
    phone: '',
    mailbox: '',
    password: '',
    previousPassword: '',
    role: undefined,
    //是否展示
    whetherExhibit: true,
    message: '',
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
  verifyNull = () => {
    let {
      userName,
      userNum,
      phone,
      mailbox,
      previousPassword,
      password,
      role,
    } = this.state
    //
    let dataArr = [
      { name: '用户名称', val: userName },
      { name: '工号或者学号', val: userNum },
      { name: '电话号码', val: phone },
      { name: '邮箱地址', val: mailbox },
      { name: '密码', val: previousPassword },
      { name: '确认密码', val: password },
      { name: '用户角色', val: role },
    ]
    return verifyDataItemNull(dataArr)
  }

  /**
   *
   * @returns
   */
  checkRegex = () => {
    let data = {
      userName: this.state.userName,
      userNum: this.state.userNum,
      phone: this.state.phone,
      mailbox: this.state.mailbox,
      previousPassword: this.state.previousPassword,
      password: this.state.password,
      role: this.state.role,
    }
    return verifyDataRegex(data)
  }

  //提交注册资料
  handleSubmit = (event) => {
    //阻止默认事件
    event.preventDefault()
    //
    let ts = this
    let res = ts.verifyNull()
    if (!res.isValidate) {
      this.setState({
        whetherExhibit: !this.state.whetherExhibit,
        message: res.alertText,
      })
      return
    }
    //
    let regex = ts.checkRegex()
    if (!regex.isValidate) {
      this.setState({
        whetherExhibit: !ts.state.whetherExhibit,
        message: regex.alertText,
      })
      return
    }
    //封装对象
    let data = {
      userName: ts.state.userName,
      userNum: ts.state.userNum,
      phone: ts.state.phone,
      mailbox: ts.state.mailbox,
      password: ts.state.password,
      role: ts.state.role,
    }
    console.log('%cdata>>>>>', ts.color(), data)
    //
    axios
      .get(this.interfaces.registry, {
        params: {
          userName: data.userName,
          userNum: data.userNum,
          phone: data.phone,
          mailbox: data.mailbox,
          password: data.password,
          role: data.role,
        },
      })
      .then((resp) => {
        console.info('resp\n', resp)
        if (resp.data.code === 200) {
          ts.setState({
            whetherExhibit: !ts.state.whetherExhibit,
            message: '注册成功,即将前往登录页',
          })
          //
          setTimeout(() => {
            this.props.history.push(this.user_urls.login_url)
          }, 5 * 1000)
        } else {
          console.log('resp.data.message\n', resp.data.message)
          this.setState({
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
    let substance = (
      <div className='main_container'>
        <PublicHeader></PublicHeader>
        <br />
        <br />
        <div className='regist_container'>
          <br />
          <br />
          <div className='mine_regist_div'>
            <form
              className='mine_regist_form'
              onSubmit={this.handleSubmit.bind(this)}
            >
              <div className='form_body'>
                <div className='inputs_item'>
                  <input
                    onChange={this.handleChange.bind(this)}
                    name='userName'
                    defaultValue={this.state.userName}
                    type='text'
                    id='mine_username'
                    placeholder='请输入用户名'
                    maxLength='20'
                  ></input>
                </div>
                <div className='inputs_item'>
                  <input
                    onChange={this.handleChange.bind(this)}
                    defaultValue={this.state.phone}
                    name='phone'
                    type='text'
                    id='mine_phone'
                    placeholder='请输入电话'
                    maxLength='11'
                  ></input>
                </div>
                <div className='inputs_item'>
                  <input
                    onChange={this.handleChange.bind(this)}
                    defaultValue={this.state.mailbox}
                    name='mailbox'
                    type='text'
                    id='mine_mailbox'
                    placeholder='请输入邮箱'
                    maxLength='80'
                  ></input>
                </div>
                <div className='inputs_item'>
                  <input
                    onChange={this.handleChange.bind(this)}
                    defaultValue={this.state.userNum}
                    name='userNum'
                    type='text'
                    id='mine_usernum'
                    placeholder='请输入工号或者学号'
                    maxLength='26'
                  ></input>
                </div>
                <div className='inputs_item'>
                  <label htmlFor='mine_select_role' className='label_role'>
                    帐号类型:
                  </label>
                  <select
                    name='role'
                    id='mine_select_role'
                    onChange={this.handleChange.bind(this)}
                  >
                    {this.state.roleTypeArray.map((item) => {
                      return (
                        <option value={item.code} key={item.name}>
                          {item.name}
                        </option>
                      )
                    })}
                  </select>
                </div>

                <div className='inputs_item'>
                  <input
                    onChange={this.handleChange.bind(this)}
                    defaultValue={this.state.previousPassword}
                    name='previousPassword'
                    type='password'
                    id='pre_mine_password'
                    placeholder='请输入密码'
                    maxLength='16'
                  ></input>
                </div>
                <div className='inputs_item'>
                  <input
                    onChange={this.handleChange.bind(this)}
                    defaultValue={this.state.password}
                    name='password'
                    type='password'
                    id='mine_password'
                    placeholder='请再次输入确认密码'
                    maxLength='16'
                  ></input>
                </div>
              </div>
              <div
                className='mine_input_btns'
                style={{ left: '0%', bottom: '-2em' }}
              >
                <div className='item_input_btn' id='reset_item'>
                  <input id='reset_id' type='reset' value='复位'></input>
                </div>
                <div className='item_input_btn' id='submit_item'>
                  <input id='submit_id' type='submit' value='注册'></input>
                </div>
              </div>
            </form>
          </div>
        </div>
        <div className='tip_of_user'>
          <p>
            已有帐号? 前往 <Link to={this.user_urls.login_url}>登录</Link>
          </p>
        </div>
        {/*弹窗提示组件*/}
        <Portals2
          isExhibit={this.state.whetherExhibit}
          msg={this.state.message}
        ></Portals2>
        <br />
        <br />
        <br />
        <br />
      </div>
    )
    return substance
  }
}
