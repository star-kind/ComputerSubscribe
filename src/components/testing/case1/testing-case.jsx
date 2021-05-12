import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import PublicHeader2 from '@/components/header2/header2'
import './testing-case.less'

export default class TestingCase extends Component {
  componentDidMount() {
    console.log('TestingCase component did mount')
    console.log(this)
  }

  render() {
    return (
      <div className='testing_case_page'>
        <PublicHeader2></PublicHeader2>
        {/*  */}
        <div className='back_home_page'>
          <Link to={'/'}>返回首页</Link>
        </div>
      </div>
    )
  }
}
