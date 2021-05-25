import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import PublicHeader2 from '@/components/header2/header2'
import './test.less'

export default class Test extends Component {
  componentDidMount() {
    console.log('Test component did mount')
    console.log(this)
  }

  render() {
    return (
      <div className='testing_case_page'>
        <div>
          <PublicHeader2></PublicHeader2>
        </div>
        <div className='back_home_page'>
          <Link to={'/'}>返回首页</Link>
        </div>
      </div>
    )
  }
}
