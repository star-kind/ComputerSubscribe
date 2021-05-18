import React, { Component } from 'react'
import './test-collection.less'
import { Link } from 'react-router-dom'

//测试页面集合中转
class TestCollection extends Component {
  constructor(props) {
    super(props)
    console.info('%cTestCollection--Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cTestCollection component did mount\n', 'color:violet', this)
  }

  state = {
    pagesArr: [{ name: '测试弹窗组件', url: '/test4' }],
    //
    cssLink: {
      textDecoration: 'none',
      color: 'white',
      fontWeight: 'bolder',
      cursor: 'pointer',
      position: 'relative',
      left: '16%',
    },
  }

  render() {
    return (
      <div className='mainTestCollection'>
        <div>
          <div className='link-list'>
            <div className='sec-div'>
              <div className='back-home'>
                <Link to={'/'}>返回首页</Link>
              </div>
              <div className='order-list-div'>
                <ol className='links-ol'>
                  {this.state.pagesArr.map((item, i) => {
                    return (
                      <li key={item.name}>
                        <Link
                          className='link-tags'
                          style={this.state.cssLink}
                          to={item.url}
                        >
                          {item.name}
                        </Link>
                      </li>
                    )
                  })}
                </ol>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}
export default TestCollection
