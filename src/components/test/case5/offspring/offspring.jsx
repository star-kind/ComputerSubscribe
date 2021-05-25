import React, { Component } from 'react'
import './offspring.less'
// import PropTypes from 'prop-types'
import { connect } from 'react-redux'

class OffSpring extends Component {
  constructor(props) {
    super(props)
    console.info('%cOffSpring--Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cOffSpring component did mount\n', 'color:red', this)
  }

  componentDidUpdate(prevProps, prevState) {
    console.log('%c OffSpring componentDidUpdate', 'color:blue', prevProps)
    console.log('%c OffSpring componentDidUpdate', 'color:green', prevState)
    console.log('%c OffSpring componentDidUpdate', 'color:red', this)
  }

  state = {}
  static propTypes = {}
  render() {
    return (
      <div className='main-offspring'>
        <div>
          <strong>OffSpring</strong>
          <p>{this.props.deliverDataReducer.data.realName}</p>
          <p>{this.props.deliverDataReducer.data.middleName}</p>
          <p>{this.props.deliverDataReducer.data.nickName}</p>
        </div>
      </div>
    )
  }
}

const mapStateToProps = (state) => {
  console.log('OffSpring.mapStateToProps.state', state)
  return state
}
export default connect(mapStateToProps)(OffSpring)
