import React, { Component } from 'react'
import PublicHeader2 from '@/components/header2/header2'
import Portals2 from '@/components/popup-window/portals2/portals2'
import { getValueFromLocal } from '@/api/common'
import './index.less'
import Table from '@/components/tables/table/'

//管理员查看用户成员列表
class RetrieveList extends Component {
  constructor(props) {
    super(props)
    console.info('RetrieveList--Constructor')
  }

  componentDidMount() {
    console.log('RetrieveList component did mount')
    console.log(this)
  }

  state = {
    //是否展示
    whetherExhibit: true,
    message: '',
    //
    tblData: null,
  }

  handleGetUsersList = () => {
    var tokenObj = getValueFromLocal(this.store_key.token_key)
    console.log('tokenObj\n', tokenObj)
    //
    this.gets(
      this.interfaces.getMember,
      {
        pageOrder: 2,
        rows: 10,
      },
      { token: tokenObj.text }
    ).then((res) => {
      console.log('res\n', res)
      if (res.data.code === 200) {
        console.info('res.data.data\n', res.data.data)
        //
        this.setState({
          tblData: res.data.data,
        })
      } else {
        console.log('res.data.message\n', res.data.message)
        this.setState({
          message: res.data.message,
          whetherExhibit: !this.state.whetherExhibit,
        })
      }
    })
  }

  render() {
    return (
      <div className='main_wrapper'>
        <PublicHeader2></PublicHeader2>
        <div className='master_context'>
          <div>
            {/*  */}
            <Table pagination={this.state.tblData}></Table>
          </div>
          <div>
            <button type='button' onClick={this.handleGetUsersList}>
              Click it
            </button>
          </div>
        </div>
        {/*  */}
        <Portals2
          msg={this.state.message}
          isExhibit={this.state.whetherExhibit}
        ></Portals2>
      </div>
    )
  }
}
export default RetrieveList
