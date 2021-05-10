import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import Portals2 from '@/components/popup-window/portals2/portals2'
import './test2.less'

export default class Test2 extends Component {
  componentDidMount() {
    console.log('Test2 component did mount')
    console.log(this)
  }

  state = {
    email: '',
    realName: '',
    //是否展示
    whetherExhibit: true,
  }

  //动态取值和赋值
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
    var data = {
      email: this.state.email,
      realName: this.state.realName,
    }
    //
    this.setState({
      whetherExhibit: !this.state.whetherExhibit,
    })
    //
    console.log('data\n', data)
  }

  render() {
    return (
      <div className='testing_case_page'>
        <div className='back_home_page'>
          <Link to={'/'}>返回首页</Link>
        </div>
        <div className='item_div_txt'>
          <p>跳出弹窗</p>
        </div>
        <div className='item_div_txt'>
          <form className='mine_form' onSubmit={this.handleSubmit.bind(this)}>
            <div className='inputs_div_item'>
              <input
                name='email'
                type='text'
                placeholder='input email please'
                value={this.state.email}
                onChange={this.handleChange.bind(this)}
              />
            </div>
            <div className='inputs_div_item'>
              <input
                name='realName'
                type='text'
                placeholder='input real name please'
                value={this.state.realName}
                onChange={this.handleChange.bind(this)}
              />
            </div>
            {/*  */}
            <div className='btn_div_item'>
              <input id='sub_id' type='submit' value='提交'></input>
            </div>
          </form>
        </div>
        {/*  */}
        <Portals2
          isExhibit={this.state.whetherExhibit}
          msg={'电邮:' + this.state.email + ', 名字:' + this.state.realName}
        ></Portals2>
      </div>
    )
  }
}
