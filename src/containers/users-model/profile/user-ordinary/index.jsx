import React, { Component } from 'react'
import './index.less'
import PublicHeader2 from '@/components/header2/header2'
import Portals2 from '@/components/popup-window/portals2/portals2'
import {
  verifyDataItemNull,
  getValueFromLocal,
  depositLocalStorage,
} from '@/api/common'
import axios from 'axios'

/**
 * 普通师生用户个人资料中心
 */
export default class ProfileUserOrdinary extends Component {
  constructor(props) {
    super(props)
    //
    this.state = {
      token: '',
      userName: '',
      userNum: '',
      phone: '',
      mailbox: '',
      role: '',
      //是否展示
      whetherExhibit: true,
      message: '',
      // style objects in tag
      switchPrepare: 'block',
      switchRevamp: 'none',
      inputReadOnly: true,
    }
  }

  componentDidMount() {
    console.log('ProfileUserOrdinary component did mount')
    console.log(this)
    //
    this.updateProfile()
  }

  //更新资料
  updateProfile = () => {
    let storeObj = getValueFromLocal(this.store_key.myself_key)
    let tokenObj = getValueFromLocal(this.store_key.token_key)
    console.log('storeObj\n', storeObj, 'tokenObj\n', tokenObj)
    //
    if (storeObj.code !== -1) {
      this.setState({
        userName: storeObj.text.userName,
        userNum: storeObj.text.userNum,
        phone: storeObj.text.phone,
        mailbox: storeObj.text.mailbox,
        role: storeObj.text.role === 1 ? '教师' : '学生',
        token: tokenObj.text,
      })
    } else {
      console.log('您尚未登录')
    }
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

  //准备修改资料,使R/S/C按钮组显示,使部分输入框转可编辑,隐匿prepare按钮
  prepareRevamp = () => {
    this.setState({
      switchRevamp: 'block',
      inputReadOnly: false,
      switchPrepare: 'none',
    })
  }

  //取消修改,使prepare按钮显示,使部分输入框转不可编辑,隐匿R/S/C按钮组
  cancelRevamp = () => {
    this.setState({
      switchRevamp: 'none',
      inputReadOnly: true,
      switchPrepare: 'block',
    })
    this.updateProfile()
  }

  handleSubmit = (event) => {
    //阻止默认事件
    event.preventDefault()
    //
    let ts = this
    let { userName, mailbox, phone } = ts.state
    let dataArr = [
      { name: '用户名称', val: userName },
      { name: '电子邮箱', val: mailbox },
      { name: '电话号码', val: phone },
    ]
    //
    let resObj = verifyDataItemNull(dataArr)
    console.log('resObj\n', resObj)
    if (!resObj.isValidate) {
      ts.setState({
        whetherExhibit: !ts.state.whetherExhibit,
        message: resObj.alertText,
      })
      return
    }
    //
    axios
      .get(ts.interfaces.modifyInfoByUser, {
        params: {
          userName: userName,
          phone: phone,
          mailbox: mailbox,
        },
        headers: { token: ts.state.token },
      })
      .then((resp) => {
        console.info('resp\n', resp)
        if (resp.data.code === 200) {
          depositLocalStorage(ts.store_key.myself_key, resp.data.data)
          //
          ts.setState({
            whetherExhibit: !ts.state.whetherExhibit,
            message: '资料修改成功',
          })
          ts.cancelRevamp()
        } else {
          console.info('resp.data.message\n', resp.data.message)
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
      <div className='pack_wrapper'>
        <PublicHeader2></PublicHeader2>
        <div className='form_pack'>
          <form className='mine_form' onSubmit={this.handleSubmit.bind(this)}>
            <div className='input_wrap_div'>
              <div className='input_item_div'>
                <div className='input_element'>
                  <label htmlFor='userName_id' className='labels_for_tag'>
                    用户名:
                  </label>
                </div>
                <div className='input_element inputs_side'>
                  <input
                    readOnly={this.state.inputReadOnly}
                    id='userName_id'
                    className='input_tag'
                    onChange={this.handleChange.bind(this)}
                    name='userName'
                    type='text'
                    defaultValue={this.state.userName}
                    placeholder='请输入用户名'
                  />
                </div>
              </div>
              <div className='input_item_div'>
                <div className='input_element'>
                  <label htmlFor='userNum_id' className='labels_for_tag'>
                    工号/学号:
                  </label>
                </div>
                <div className='input_element inputs_side'>
                  <input
                    readOnly={true}
                    id='userNum_id'
                    className='input_tag ban_edit_input'
                    onChange={this.handleChange.bind(this)}
                    name='userNum'
                    type='text'
                    defaultValue={this.state.userNum}
                  />
                </div>
              </div>
              <div className='input_item_div'>
                <div className='input_element'>
                  <label htmlFor='phone_id' className='labels_for_tag'>
                    电话:
                  </label>
                </div>
                <div className='input_element inputs_side'>
                  <input
                    readOnly={this.state.inputReadOnly}
                    id='phone_id'
                    className='input_tag'
                    onChange={this.handleChange.bind(this)}
                    name='phone'
                    type='text'
                    defaultValue={this.state.phone}
                    placeholder='请输入电话'
                  />
                </div>
              </div>
              <div className='input_item_div'>
                <div className='input_element'>
                  <label htmlFor='mailbox_id' className='labels_for_tag'>
                    邮箱:
                  </label>
                </div>
                <div className='input_element inputs_side'>
                  <input
                    readOnly={this.state.inputReadOnly}
                    id='mailbox_id'
                    className='input_tag'
                    onChange={this.handleChange.bind(this)}
                    name='mailbox'
                    type='text'
                    defaultValue={this.state.mailbox}
                    placeholder='请输入邮箱'
                  />
                </div>
              </div>
              <div className='input_item_div'>
                <div className='input_element'>
                  <label htmlFor='role_id' className='labels_for_tag'>
                    用户类型:
                  </label>
                </div>
                <div className='input_element inputs_side'>
                  <input
                    readOnly={true}
                    id='role_id'
                    className='input_tag ban_edit_input'
                    type='text'
                    defaultValue={this.state.role}
                  />
                </div>
              </div>
            </div>
            <div className='btn_div_items'>
              <div
                style={{ display: this.state.switchPrepare }}
                className='btn_input'
                id='btn_revamp_item'
              >
                <input
                  onClick={this.prepareRevamp}
                  className='input_btn_ele'
                  type='button'
                  value='修改资料'
                  id='btn_revamp_id'
                />
              </div>
              <div
                style={{ display: this.state.switchRevamp }}
                className='btn_input'
                id='cancel_revamp_item'
              >
                <input
                  onClick={this.cancelRevamp}
                  className='input_btn_ele'
                  type='button'
                  value='取消修改'
                  id='cancel_revamp_id'
                />
              </div>
              <div
                style={{ display: this.state.switchRevamp }}
                className='btn_input'
                id='reset_item'
              >
                <input
                  className='input_btn_ele'
                  type='reset'
                  value='Reset'
                  id='reset_id'
                />
              </div>
              <div
                style={{ display: this.state.switchRevamp }}
                className='btn_input'
                id='submit_item'
              >
                <input
                  className='input_btn_ele'
                  type='submit'
                  value='Submit'
                  id='submit_id'
                />
              </div>
            </div>
          </form>
        </div>
        <Portals2
          isExhibit={this.state.whetherExhibit}
          msg={this.state.message}
        ></Portals2>
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
      </div>
    )
  }
}
