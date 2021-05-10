import React, { Component } from 'react';
import PublicHeader from '@/components/header/header-index';
import { Link } from 'react-router-dom';
// import axio from 'axios';
import './registry-index.less';

export default class Rigistry extends Component {
  componentDidMount () {
    console.log('Rigistry component did mount');
    console.log(this);
  }

  state = {
    login_url: '/#logining',
    roleTypeArray: [
      { name: '--请选择帐号类型--', code: null },
      { name: '管理员', code: 0 },
      { name: '教师', code: 1 },
      { name: '学生', code: 2 }
    ],
    //注册资料
    userName: '',
    userNum: '',
    phone: '',
    mailbox: '',
    password: '',
    previousPassword: '',
    role: null,
  }

  getRoleByChangeSel = (e) => {
    console.log(e.target);
    //触发onChange事件时,得到的值
    var roleOptVal = e.target.value;
    console.log("RoleOptVal(code)", roleOptVal);
    this.setState({
      role: roleOptVal
    });
  }

  //提交注册资料

  //校对注册资料正则及非空

  //弹窗提示组件

  //配置跨域发送请求

  render () {
    var substance = <div className="main_container">
      <PublicHeader></PublicHeader>
      {/*  */}
      <div className="regist_container">
        <div className="mine_regist_div">
          <form className="mine_regist_form">
            <div className="form_body">
              <div className="inputs_item">

                <input type="text" id="mine_username" placeholder="请输入用户名" maxLength="20"></input>
              </div>
              <div className="inputs_item">

                <input type="text" id="mine_phone" placeholder="请输入电话" maxLength="15"></input>
              </div>
              <div className="inputs_item">

                <input type="text" id="mine_mailbox" placeholder="请输入邮箱" maxLength="80"></input>
              </div>
              <div className="inputs_item">

                <input type="text" id="mine_usernum" placeholder="请输入工号或者学号" maxLength="30"></input>
              </div>

              <div className="inputs_item">
                <label for="mine_select_role" className="label_role">帐号类型:</label>
                <select id="mine_select_role" onChange={this.getRoleByChangeSel}>

                  {
                    this.state.roleTypeArray.map((item) => {
                      console.log(item);
                      return (<option value={item.code} key={item.name}> {item.name} </option>);
                    })
                  }

                </select>
              </div>

              <div className="inputs_item">

                <input type="password" id="pre_mine_password" placeholder="请输入密码" maxLength="16"></input>
              </div>
              <div className="inputs_item">

                <input type="password" id="mine_password" placeholder="请再次输入确认密码" maxLength="16"></input>
              </div>
            </div>
          </form>
        </div>
        <div className="mine_input_btns">
          <div className="item_input_btn" id="reset_item">

            <input type="reset" value="复位"></input>
          </div>
          <div className="item_input_btn" id="submit_item">

            <input type="submit" value="注册"></input>
          </div>
        </div>
      </div>
      <div className="tip_of_user">
        <p>已有帐号? 前往 <Link to={this.state.login_url}>登录</Link> </p>
      </div>
    </div>;
    return (substance);
  }
}