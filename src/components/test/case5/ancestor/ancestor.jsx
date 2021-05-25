import React, { Component } from 'react'
import './ancestor.less'
// import PropTypes from 'prop-types'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { deliveryData } from '@/redux/redux-drill/actions'
import OffSpring from '@/components/test/case5/offspring/offspring'

class Ancestor extends Component {
  constructor(props) {
    super(props)
    console.info('%cAncestor--Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cAncestor component did mount\n', 'color:red', this)
  }

  componentDidUpdate(prevProps, prevState) {
    console.log('%cAncestor componentDidUpdate', 'color:blue', prevProps)
    console.log('%cAncestor componentDidUpdate', 'color:green', prevState)
    console.log('%cAncestor componentDidUpdate', 'color:red', this)
  }

  state = {
    nickName: '',
    realName: '',
    middleName: '',
    //
    styleEffect: {
      height: '3rem',
      transform: 'translate(18rem, 3rem)',
      width: '10rem',
      fontSize: '26px',
    },
  }

  static propTypes = {}

  handleSendData = () => {
    let privateNames = {
      nickName: this.state.nickName,
      middleName: this.state.middleName,
      realName: this.state.realName,
    }
    this.props.sendData(privateNames)
  }

  //绑定 on change 事件,使input输入框能动态取值和赋值
  handleChange = (event) => {
    // console.log('event.target', event.target)
    // e.target.name代表当前输入Input框对应的Name,如email,realName
    // e.target.value代表当前输入的值
    this.setState({
      [event.target.name]: event.target.value,
    })
  }

  render() {
    return (
      <div className='main-ancestor'>
        <div>
          <div className='back-to-collect' style={{ textAlign: 'center' }}>
            <div style={{ marginTop: '2rem' }}>
              <Link to={'/testCollection'}>返回中继</Link>
            </div>
          </div>
          {/*  */}
          <div className='inputs-div'>
            <div className='input-tag'>
              <input
                name='nickName'
                onChange={this.handleChange.bind(this)}
                type='text'
                placeholder='nickName'
                defaultValue={this.state.nickName}
              />
            </div>
            <div className='input-tag'>
              <input
                name='realName'
                onChange={this.handleChange.bind(this)}
                type='text'
                placeholder='realName'
                defaultValue={this.state.realName}
              />
            </div>
            <div className='input-tag'>
              <input
                name='middleName'
                onChange={this.handleChange.bind(this)}
                type='text'
                placeholder='middleName'
                defaultValue={this.state.middleName}
              />
            </div>
          </div>
          <div className='button-div'>
            <button
              style={this.state.styleEffect}
              onClick={this.handleSendData}
            >
              clicking
            </button>
          </div>
          <div className='offspring_div'>
            <OffSpring></OffSpring>
          </div>
        </div>
      </div>
    )
  }
}

/*
mapStateToProps：将需要的state的节点注入到与此视图数据相关的组件(props)上.也即用于建立 State 到 Props 的映射关系. 这个函数中要注入全部的models，你需要返回一个新的对象，挑选该组件所需要的models
*/
const mapStateToProps = (state) => {
  console.log('Ancestor.mapStateToProps.state', state)
  return { data: state }
}

/* 将需要绑定的响应事件注入到组件上(props上) */
const mapDispatchToProps = (dispatch) => {
  return {
    sendData: (data) => {
      console.log('mapDispatchToProps.data', data)
      dispatch(deliveryData(data))
    },
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Ancestor)
