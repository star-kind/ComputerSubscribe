import React, { Component } from 'react';
import PublicHeader from '@/components/header/header-index';
import './home-index.less';
var image_url = require("./../../../public/logo192.png").default;

export default class Home extends Component {

  componentDidMount () {
    console.log('Home component did mount')
    console.log(this);
  }

  //
  render () {
    var content = <div className="total_body">
      <PublicHeader></PublicHeader>
      {/*  */}
      <div className="main_area">
        <div className="welcome_word_div">
          <h4>欢迎各位师生来到机房预约管理系统</h4>
        </div>
        <div className="image_div">
          <img src={[image_url]} alt="null" className="home_image"></img>
        </div>
      </div>
    </div>;
    //
    return (content);
  }
}