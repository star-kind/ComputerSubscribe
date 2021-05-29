import React, { Component } from 'react'
import './header2.less'
import { Link } from 'react-router-dom'
import { withRouter } from 'react-router-dom'
import { getValueFromLocal } from '@/api/common'
import LoginOut from '@/components/login-out/login-out'

//公共组件-header-2
//对不同类型的用户显示不同的header
class PublicHeader2 extends Component {
  componentDidMount() {
    console.log('PublicHeader-2 component did mount')
    // console.log(this)
    this.initSelectUrl()
  }

  componentDidUpdate(prevProps, prevState) {
    console.log('PublicHeader2 component did mount')
    // console.log(
    //   '%cPublicHeader2 component did mount\n',
    //   this.color(),
    //   prevProps,
    //   'prevState\n',
    //   prevState,
    //   'This\n',
    //   this
    // )
  }

  state = {
    revampPassword: {
      name: '修改密码',
      url: this.user_urls.revamp_passwd_url,
    },
    //
    urlArray: [],
  }

  initSelectUrl = () => {
    var storeObj = getValueFromLocal(this.store_key.myself_key)
    console.log('%c storeObj', this.getColor(), storeObj)
    //
    let urlArrObj = []
    switch (storeObj.text.role) {
      case 0:
        urlArrObj = [
          { name: '--请选择地址--', url: '' },
          { name: '管理员页面', url: '#administrator' },
          { name: '用户列表', url: this.user_urls.retrieve_list_url },
          this.state.revampPassword,
          { name: '个人资料', url: this.user_urls.profile_administrator },
          { name: '新增机房', url: this.user_urls.add_new_room },
          { name: '机房列表', url: this.user_urls.query_room_list },
        ]
        break

      case 1:
        urlArrObj = [
          { name: '--请选择地址--', url: '' },
          { name: '教师页面', url: '#teacher' },
          this.state.revampPassword,
          {
            name: '本周预约申请列表',
            url: this.user_urls.query_week_teacher,
          },
          { name: '个人资料', url: this.user_urls.profile_ordinary },
          { name: '综合列表', url: this.user_urls.joint_query_major },
        ]
        break

      case 2:
        urlArrObj = [
          { name: '--请选择地址--', url: '' },
          { name: '学生页面', url: '#student' },
          this.state.revampPassword,
          { name: '申请机房预约', url: this.user_urls.add_new_apply },
          { name: '个人资料', url: this.user_urls.profile_ordinary },
          {
            name: '本周预约申请',
            url: this.user_urls.query_myself_student,
          },
        ]
        break

      default:
        break
    }
    //
    this.setState({
      urlArray: urlArrObj,
    })
  }

  //事件触发
  changeSel = (e) => {
    // console.log(e.target)
    // 触发onChange事件时,得到的值
    let optVal = e.target.value
    // console.log('optVal(url)', optVal)
    this.jumpByUrl(optVal)
  }

  //根据地址跳转
  jumpByUrl = (urlParam) => {
    if ((urlParam === null) | '') {
      console.log('地址参数为空')
      return
    }
    this.props.history.push(urlParam)
  }

  render() {
    let content = (
      <div className='total_header'>
        <div className='complete_header'>
          <div className='main_header_area'>
            <ol className='address_order_list'>
              <div className='out_list_item'>
                <div className='outside_item' id='home_address_id'>
                  <li>
                    <Link to={'/'}>首页</Link>
                  </li>
                </div>
                <div className='outside_item'>
                  <li>
                    <Link to={this.user_urls.login_url}>登录</Link>
                  </li>
                </div>
                <div className='outside_item'>
                  <li>
                    <Link to={this.user_urls.reg_url}>注册</Link>
                  </li>
                </div>
                {/* LoginOut */}
                <div className='outside_item' id='id_LoginOut'>
                  <LoginOut></LoginOut>
                </div>
              </div>
              <div className='select_options_div'>
                <li>
                  <label
                    htmlFor='select_opt_item'
                    className='label_address_collects'
                  >
                    地址列表
                  </label>
                  <select id='select_opt_item' onChange={this.changeSel}>
                    {this.state.urlArray.map((item) => {
                      return (
                        <option value={item.url} key={item.name}>
                          {item.name}
                        </option>
                      )
                    })}
                  </select>
                </li>
              </div>
            </ol>
          </div>
        </div>
      </div>
    )
    return content
  }
}

export default withRouter(PublicHeader2)
