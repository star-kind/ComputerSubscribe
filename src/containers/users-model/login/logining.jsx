import React, { Component } from 'react'
import PublicHeader from '@/components/header/header-index'
import { Link } from 'react-router-dom'
import axios from 'axios'
import Portals2 from '@/components/popup-window/portals2/portals2'
import { verifyDataItemNull, depositLocalStorage } from '@/api/common'
import './logining.less'

export default class Logining extends Component {
  componentDidMount() {
    console.log('Logining component did mount', this)
    console.log()
  }

  componentWillUnmount() {
    console.log('Logining.componentWillUnmount', this)
  }

  state = {
    //是否展示
    whetherExhibit: true,
    message: '',
    //
    role: '',
    userNum: '',
    passwd: '',
    //
    roleTypeArray: [
      { name: '管理员', code: 0 },
      { name: '教师', code: 1 },
      { name: '学生', code: 2 },
    ],
  }

  /**
   *
   * @returns
   */
  validateSubmit = () => {
    let { userNum, passwd, role } = this.state
    let dataArr = [
      { name: '工号或者学号', val: userNum },
      { name: '密码', val: passwd },
      { name: '帐号角色', val: role },
    ]
    return verifyDataItemNull(dataArr)
  }

  handleSubmit = (event) => {
    //阻止默认事件
    event.preventDefault()
    let ts = this
    let { userNum, passwd, role } = ts.state
    //
    let resObj = ts.validateSubmit()
    if (!resObj.isValidate) {
      ts.setState({
        whetherExhibit: !ts.state.whetherExhibit,
        message: resObj.alertText,
      })
      return
    }
    //
    axios
      .get(ts.interfaces.login, {
        params: {
          userNum: userNum,
          passwd: passwd,
          role: role,
        },
      })
      .then((resp) => {
        console.info('resp\n', resp)
        if (resp.data.code === 200) {
          this.setState({
            whetherExhibit: !ts.state.whetherExhibit,
            message: '登录成功,即将前往个人中心',
          })
          //id存入LocalStorage
          depositLocalStorage(ts.store_key.myself_key, resp.data.data)
          //专门存入令牌
          depositLocalStorage(ts.store_key.token_key, resp.data.data.token)
          //如果是管理员,前往其专属页
          if (resp.data.data.role === 0) {
            setTimeout(() => {
              ts.props.history.push(ts.user_urls.profile_administrator)
            }, 5 * 1000)
          } else {
            setTimeout(() => {
              ts.props.history.push(ts.user_urls.profile_ordinary)
            }, 5 * 1000)
          }
        } else {
          console.log('resp.data.message\n', resp.data.message)
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

  //绑定on change事件,使input输入框能动态取值和赋值
  handleChange = (event) => {
    // console.log('event.target', event.target)
    this.setState({
      [event.target.name]: event.target.value,
    })
  }

  // 绑定 on select 事件
  handleChangeSelect = (e) => {
    // console.log(e.target)
    //触发onChange事件时,得到的值
    let roleOptVal = e.target.value
    console.log('RoleOptVal(code)', roleOptVal)
    this.setState({
      role: roleOptVal,
    })
  }

  render() {
    let content = (
      <div className='main_container'>
        <div className='public-header-area'>
          <PublicHeader></PublicHeader>
        </div>
        <br />
        <br />
        <br />
        <div className='regist_container'>
          <div className='mine_regist_div'>
            <form
              className='mine_regist_form'
              onSubmit={this.handleSubmit.bind(this)}
            >
              <div className='form_body'>
                <div className='choose_role'>
                  <div className='inputs_item choose_role_item'>
                    <label htmlFor='mine_select_role' className='label_role'>
                      帐号类型:
                    </label>
                    <select
                      id='mine_select_role'
                      name='role'
                      onChange={this.handleChangeSelect}
                    >
                      <option value={''}>--请选择帐号类型--</option>
                      {/*  */}
                      {this.state.roleTypeArray.map((item) => {
                        return (
                          <option value={item.code} key={item.name}>
                            {item.name}
                          </option>
                        )
                      })}
                    </select>
                  </div>
                </div>
                <div className='param_major'>
                  <div className='inputs_item param_major_item'>
                    <input
                      onChange={this.handleChange.bind(this)}
                      name='userNum'
                      defaultValue={this.state.userNum}
                      type='text'
                      id='mine_usernum'
                      placeholder='请输入工号或者学号'
                      maxLength='26'
                    />
                  </div>
                  <div className='inputs_item param_major_item'>
                    <input
                      onChange={this.handleChange.bind(this)}
                      name='passwd'
                      defaultValue={this.state.passwd}
                      type='password'
                      id='mine_password'
                      placeholder='请输入密码'
                      maxLength='16'
                    />
                  </div>
                </div>
              </div>
              <div className='mine_input_btns' style={{ left: '36%' }}>
                <div className='item_input_btn' id='reset_item'>
                  <input type='reset' value='复位' />
                </div>
                <div className='item_input_btn' id='submit_item'>
                  <input type='submit' value='登录' />
                </div>
              </div>
            </form>
          </div>
          <div className='tip_of_user'>
            <p>
              还没有帐号? 前往 <Link to={this.user_urls.reg_url}>注册</Link>
            </p>
          </div>
        </div>
        <div className='portal-2'>
          <Portals2
            isExhibit={this.state.whetherExhibit}
            msg={this.state.message}
          ></Portals2>
        </div>
      </div>
    )
    //
    return content
  }
}
