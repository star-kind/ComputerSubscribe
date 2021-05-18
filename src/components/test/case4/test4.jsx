import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import './test4.less'
import Portals from '@/components/popup-window/portals/portals'

class Test4 extends Component {
  constructor(props) {
    super(props)
    console.info('%cTest4--Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cTest4 component did mount\n', 'color:violet', this)
  }

  state = {
    isExhibit: true,
    msg: '',
  }

  clickEffect = () => {
    console.log('clickEffect')
    this.setState({
      isExhibit: !this.state.isExhibit,
      msg: 'OOKKOOKKOOKK',
    })
  }

  render() {
    return (
      <div className='mainTest4'>
        <div className='main-div'>
          <div className='second-div'>
            <div className='backhome-part'>
              <Link to={'/'}>首页</Link>
            </div>
            <div className='third-div'>
              <div className='button-div'>
                <button
                  className='btn-cli'
                  onClick={this.clickEffect.bind(this)}
                >
                  弹窗
                </button>
              </div>
            </div>
          </div>
        </div>
        {/*  */}
        <Portals
          isExhibit={this.state.isExhibit}
          msg={this.state.msg}
        ></Portals>
      </div>
    )
  }
}
export default Test4
