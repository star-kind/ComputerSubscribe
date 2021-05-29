import React, { Component } from 'react'
import './header-index.less'
import { Link } from 'react-router-dom'
import { withRouter } from 'react-router-dom'
import LoginOut from '@/components/login-out/login-out'

// 公共组件-header
class PublicHeader extends Component {
  componentDidMount() {
    console.log('PublicHeader component did mount')
    // console.log(this)
  }

  componentDidUpdate(prevProps, prevState) {
    console.log('PublicHeader componentDidUpdate.this')
    // console.log(
    //   '%c PublicHeader componentDidUpdate.prevProps',
    //   'color:blue',
    //   prevProps
    // )
    // console.log(
    //   '%c PublicHeader componentDidUpdate.prevState',
    //   'color:green',
    //   prevState
    // )
    // console.log('%c PublicHeader componentDidUpdate.this', 'color:red', this)
  }

  state = {
    // url collection
    urlArray: [
      { name: '--请选择地址--', url: '' },
      { name: '修改密码', url: this.user_urls.revamp_passwd_url },
      { name: '测试-1', url: '/test' },
      { name: '测试-2', url: '/test2' },
      { name: '测试-中继集合', url: '/testCollection' },
    ],
  }

  //事件触发
  changeSel = (e) => {
    // console.log(e.target)
    //触发onChange事件时,得到的值
    let optVal = e.target.value
    // console.log('optVal(url)', optVal)
    this.jumpByUrl(optVal)
  }

  //根据地址跳转
  jumpByUrl = (urlParam) => {
    if ((urlParam === null) | '') {
      console.log('地址为空')
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
                        <option key={item.name} value={item.url}>
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

export default withRouter(PublicHeader)
