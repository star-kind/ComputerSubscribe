import React, { Component } from 'react'
import './test3.less'
import UserTable from '@/components/tables/users/user-table/table-index'
import PageInfo from '@/components/tables/page-info/page-info'
import { Link } from 'react-router-dom'
import { TopContext } from '@/api/context/contexts'

class Test3 extends Component {
  constructor(props) {
    super(props)
    console.info('Test3--Constructor')
  }

  componentDidMount() {
    console.log('Test3 component did mount')
    console.log(this)
  }

  state = {
    pagination: {
      data: [
        {
          userName: 'Rsrtorue',
          password: null,
          role: 2,
          userNum: 239915547,
          mailbox: '31348221540@msn.cn',
          phone: '19088340697',
          salt: null,
          id: 11,
        },
        {
          userName: 'wwwmmm',
          password: null,
          role: 1,
          userNum: 393690147,
          mailbox: '16345165@qq.com',
          phone: '19703215406',
          salt: null,
          id: 12,
        },
        {
          userName: 'skypt',
          password: null,
          role: 2,
          userNum: 1050048,
          mailbox: '165174714570@qq.com',
          phone: '18370273627',
          salt: null,
          id: 13,
        },
        {
          userName: 'senior',
          password: null,
          role: 1,
          userNum: 1050918,
          mailbox: '35174714570@qq.com',
          phone: '18370278627',
          salt: null,
          id: 14,
        },
        {
          userName: 'gazila',
          password: null,
          role: 2,
          userNum: 393606134700,
          mailbox: '506897004@qq.com',
          phone: '19703640870',
          salt: null,
          id: 15,
        },
        {
          userName: 'lion.pnay',
          password: null,
          role: 2,
          userNum: 105170048,
          mailbox: 'h8848@fox.com.cn',
          phone: '17123241037',
          salt: null,
          id: 16,
        },
        {
          userName: 'everaies.msi',
          password: null,
          role: 2,
          userNum: 385170048,
          mailbox: '51074714570@qq.com',
          phone: '13139273627',
          salt: null,
          id: 17,
        },
        {
          userName: 'victories.failed',
          password: null,
          role: 0,
          userNum: 393606924700,
          mailbox: '1517415415@qq.com.cn',
          phone: '15243606940',
          salt: null,
          id: 18,
        },
        {
          userName: 'smart.keyes',
          password: null,
          role: 1,
          userNum: 345177048,
          mailbox: '599874714570@qq.com',
          phone: '17839273627',
          salt: null,
          id: 19,
        },
        {
          userName: 'chm.nay',
          password: null,
          role: 1,
          userNum: 3999706924700,
          mailbox: 'spring@fox.com',
          phone: '18923041037',
          salt: null,
          id: 20,
        },
      ],
      hasPrevious: true,
      hasNext: true,
      totalPages: 6,
      currentPage: 2,
      rows: 10,
    },
    //
    thArr: ['用户名', '学号/工号', '帐号类型', '邮箱', '电话', '操作'],
    //
    showPageArea: 'inherit',
    targetPage: 15,
  }

  //子组件调用父函数
  hidePageScope = (instruct) => {
    //接收到子组件的值
    console.log('hidePageScope.instruct== ' + instruct)
    this.setState({
      showPageArea: instruct,
    })
  }

  render() {
    return (
      <div className='main_wrapper'>
        <div className='back_home_page'>
          <Link to={'/'}>返回首页</Link>
        </div>
        <div
          className='sec_wrapper'
          ref={(secondArea) => (this.secondDoc = secondArea)}
        >
          <UserTable
            hidePageArea={this.hidePageScope}
            thArray={this.state.thArr}
            pagination={this.state.pagination}
          ></UserTable>
        </div>
        <br />
        <div
          style={{ display: this.state.showPageArea }}
          className='surround_pageinfo'
        >
          <PageInfo
            pagination={this.state.pagination}
            targetPageNum={this.state.targetPage}
          ></PageInfo>
        </div>
        {/*  */}
        <TopContext.Consumer>
          {(value) => {
            console.log('TopContext.Consumer.value==\n', value)
          }}
        </TopContext.Consumer>
      </div>
    )
  }
}
export default Test3
