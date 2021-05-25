import React, { Component } from 'react'
import './case3.less'
// import PropTypes from 'prop-types'
import { connect } from 'react-redux'

class Case3 extends Component {
  constructor(props) {
    super(props)
    console.info('%cCase3--Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cCase3 component did mount\n', 'color:red', this)
  }

  state = {}
  static propTypes = {}

  render() {
    return (
      <div className='main-case3'>
        <div className='package-case3'>
          <div className='back-to-collect' style={{ textAlign: 'center' }}>
            <br />
            <br />
            <br />
            <br />
            <br />
            <div>
              <h1>TestCase_03.学生id=</h1>
              <h2>{this.props.conveyIDReducer.id}</h2>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

//取值,get from store(state)
//其返回值在props中
const mapStateToProps = (state) => {
  console.log('Case3.mapStateToProps', state)
  return state
}

export default connect(mapStateToProps)(Case3)
