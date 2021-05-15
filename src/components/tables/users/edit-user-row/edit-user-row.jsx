import React, { Component } from 'react'
import './index.less'
import Portals2 from '@/components/popup-window/portals2/portals2'
import { commonUtil } from '@/api/common2'
import PropTypes from 'prop-types'

/**
 * 管理员编辑修改普通师生的用户资料
 */
export default class EditUserRow extends Component {
  constructor(props) {
    super(props)
    console.log('EditUserRow--Constructor\n', this)
  }

  componentDidMount() {
    console.log('EditUserRow Component Did Mount\n', this)
    this.getToken()
  }

  //父组件数据实时更新,子组件相应更新
  UNSAFE_componentWillReceiveProps(nextProps) {
    console.log(
      'EditUserRow Component Will Receive Props.nextProps\n',
      nextProps
    )
    //注:这里更新要用nextProps,否则不生效
    this.setState({
      id: nextProps.row.id,
      userName: nextProps.row.userName,
      userNum: nextProps.row.userNum,
      phone: nextProps.row.phone,
      mailbox: nextProps.row.mailbox,
      role: nextProps.row.role,
    })
  }

  state = {
    token: '',
    //是否展示
    isExhibit: true,
    msg: '',
    //考虑到实际情况,禁止修改帐号角色
    roleTypeArray: [
      { name: '--请选择帐号类型--', code: -1 },
      { name: '教师', code: 1 },
      { name: '学生', code: 2 },
    ],
    //
    id: -1,
    userName: '',
    userNum: '',
    phone: '',
    mailbox: '',
    role: 0,
    //
    exhibit: 'block',
  }

  static propTypes = {
    row: PropTypes.object,
    showWrapper: PropTypes.string,
    showTable: PropTypes.func,
  }

  getToken = () => {
    var valObj = commonUtil.getValueFromLocal(this.store_key.token_key)
    console.log('getToken--valObj\n', valObj)
    if (valObj.code !== -1) {
      this.setState({
        token: valObj.text,
      })
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

  handleSubmit = (event) => {
    //阻止默认事件
    event.preventDefault()
    console.log('event\n', event)
    var data = {
      id: this.state.id,
      userName: this.state.userName,
      phone: this.state.phone,
      mailbox: this.state.mailbox,
      role: this.state.role,
    }
    //如果帐户类型为管理员,警告,中断
    if (data.role === 0) {
      this.setState({
        isExhibit: !this.state.isExhibit,
        msg: '管理员的资料不可被编辑',
      })
    }
    //token
    if (this.state.token === '') {
      this.setState({
        isExhibit: !this.state.isExhibit,
        msg: '您的登录状态业已失效,请请重新登录',
      })
      return
    }
    // validate params whether or null
    var res = commonUtil.verifyDataNull(data)
    if (!res.isValidate) {
      this.setState({
        isExhibit: !this.state.isExhibit,
        msg: res.alertText,
      })
      return
    }
    console.log('data\n', data)
  }

  exhibitTblHideForm = () => {
    //调用上层组件函数,向上层组件传值
    this.props.showTable('none')
  }

  render() {
    return (
      <div className='pack_wrapper' style={{ display: this.props.showWrapper }}>
        <div className='form_pack'>
          <form className='mine_form' onSubmit={this.handleSubmit.bind(this)}>
            <div className='input_wrap_div'>
              {/*  */}
              <div className='input_item_div'>
                <div className='input_element'>
                  <label htmlFor='user_id' className='labels_for_tag'>
                    用户ID:
                  </label>
                </div>
                <div className='input_element inputs_side'>
                  <input
                    readOnly={true}
                    id='user_id'
                    className='input_tag ban_edit_input'
                    onChange={this.handleChange.bind(this)}
                    name='id'
                    type='text'
                    value={this.state.id}
                  />
                </div>
              </div>
              {/*  */}
              <div className='input_item_div'>
                <div className='input_element'>
                  <label htmlFor='userName_id' className='labels_for_tag'>
                    用户名:
                  </label>
                </div>
                <div className='input_element inputs_side'>
                  <input
                    id='userName_id'
                    className='input_tag'
                    onChange={this.handleChange.bind(this)}
                    name='userName'
                    type='text'
                    value={this.state.userName}
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
                    value={this.state.userNum}
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
                    id='phone_id'
                    className='input_tag'
                    onChange={this.handleChange.bind(this)}
                    name='phone'
                    type='text'
                    value={this.state.phone}
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
                    id='mailbox_id'
                    className='input_tag'
                    onChange={this.handleChange.bind(this)}
                    name='mailbox'
                    type='text'
                    value={this.state.mailbox}
                    placeholder='请输入邮箱'
                  />
                </div>
              </div>
              {/*  */}
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
                    name='role'
                    value={this.state.role === 1 ? '教师' : '学生'}
                    onChange={this.handleChange.bind(this)}
                  />
                </div>
              </div>
              {/*  */}
            </div>
            <div className='btn_div_items'>
              <div className='btn_input' id='cancel_revamp_item'>
                <input
                  onClick={this.exhibitTblHideForm}
                  className='input_btn_ele'
                  type='button'
                  value='取消编辑'
                  id='cancel_revamp_id'
                />
              </div>
              <div className='btn_input' id='reset_item'>
                <input
                  className='input_btn_ele'
                  type='reset'
                  value='复位'
                  id='reset_id'
                />
              </div>
              <div className='btn_input' id='submit_item'>
                <input
                  className='input_btn_ele'
                  type='submit'
                  value='提交修改'
                  id='submit_id'
                />
              </div>
            </div>
          </form>
        </div>
        <Portals2
          isExhibit={this.state.isExhibit}
          msg={this.state.msg}
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
