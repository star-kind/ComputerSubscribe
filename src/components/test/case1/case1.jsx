import React, { Component } from 'react'
import './case1.less'
// import PropTypes from 'prop-types'
import { connect } from 'react-redux'
import { conveyLayerID } from '@/redux/redux-drill/actions'
import { Link } from 'react-router-dom'
import Case3 from '@/components/test/case3/case3'

class Case1 extends Component {
  constructor(props) {
    super(props)
    console.info('%cCase1--Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cCase1 component did mount\n', 'color:red', this)
  }

  state = {
    student: {
      id: 12000,
      nickName: 'Case1.Case1',
      gender: 1,
      grade: 4,
    },
    //
    styleEffect: {
      height: '3rem',
      transform: 'translate(18rem, 3rem)',
      width: '10rem',
      fontSize: '26px',
    },
  }

  static propTypes = {}

  dispatchData = () => {
    let { student } = this.state
    this.props.dispatch(conveyLayerID(student.id))
  }

  render() {
    return (
      <div className='main-case1'>
        <div className='back-to-collect' style={{ textAlign: 'center' }}>
          <br />
          <br />
          <br />
          <Link to={'/testCollection'}>返回中继</Link>
        </div>
        <div>
          <button style={this.state.styleEffect} onClick={this.dispatchData}>
            Clicking
          </button>
        </div>
        <div>
          <Case3></Case3>
        </div>
      </div>
    )
  }
}

export default connect()(Case1)
