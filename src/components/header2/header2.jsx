import React, { Component } from 'react'
import './header2.less'
import { Link } from 'react-router-dom'
import { withRouter } from 'react-router-dom'
import { reg_url, login_url, store_key } from '@/api/constant-list'
import { getValueFromLocal } from '@/api/common'

//公共组件-header-2
//对不同类型的用户显示不同的header
class PublicHeader2 extends Component {
  constructor(props) {
    super(props)
    //
    var storeObj = getValueFromLocal(store_key)
    console.log('storeObj\n', storeObj)
    //
    var urlArrObj = []
    switch (storeObj.text.role) {
      case 0:
        urlArrObj = [
          { name: '--请选择地址--', url: '' },
          { name: '管理员页面', url: '/#super-administrator' },
        ]
        break

      case 1:
        urlArrObj = [
          { name: '--请选择地址--', url: '' },
          { name: '教师页面', url: '/#teacher' },
        ]
        break

      case 2:
        urlArrObj = [
          { name: '--请选择地址--', url: '' },
          { name: '学生页面', url: '/#student' },
        ]
        break

      default:
        urlArrObj = [
          { name: '--请选择地址--', url: '' },
          { name: 'Null页面', url: '/void' },
        ]
        break
    }
    //
    console.log('urlArrObj\n', urlArrObj)
    this.state = {
      urlArray: urlArrObj,
    }
  }

  componentDidMount() {
    console.log('PublicHeader2 component did mount')
    console.log(this)
  }

  //事件触发
  changeSel = (e) => {
    console.log(e.target)
    //触发onChange事件时,得到的值
    var optVal = e.target.value
    console.log('optVal(url)', optVal)
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
    var content = (
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
                    <Link to={login_url}>登录</Link>
                  </li>
                </div>
                <div className='outside_item'>
                  <li>
                    <Link to={reg_url}>注册</Link>
                  </li>
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
                      console.log(item)
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
    /** */
    return content
  }
}

export default withRouter(PublicHeader2)
