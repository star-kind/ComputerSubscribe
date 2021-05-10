import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Counter from '@/components/counters/counter-app';
import Portals from '@/components/popup-window/portals/portals';
import './testing-case.less';

export default class TestingCase extends Component {
  componentDidMount () {
    console.log('TestingCase component did mount');
    console.log(this);
  }

  render () {
    return (<div className="testing_case_page">
      <div className="back_home_page"> <Link to={'/'}>返回首页</Link> </div>
      <Counter className="counter_tag_local"></Counter>
      {/*  */}
      <Portals msg={'历历远山草,一水一口慵,野火吹不镜,春分吹雨神'}></Portals>
    </div>);
  }
}