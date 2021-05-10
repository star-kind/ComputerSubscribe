import React, { Component } from 'react'
import Model from '@/components/popup-window/model/model'
import PropTypes from 'prop-types'
import './portals.less'

/**
 * Portals:门户
 */
class Portals extends Component {
  componentDidMount() {
    console.log('PortalsComponentDidMount')
    console.log(this)
  }

  state = {
    showMsg: false,
  }

  static propTypes = {
    msg: PropTypes.string.isRequired,
  }

  handleMsgWindow() {
    this.setState({
      showMsg: !this.state.showMsg,
    })
  }

  render() {
    return (
      <div className='portal_view_lead'>
        <div id='app_root'>
          {this.state.showMsg === true ? (
            <Model
              isShow={this.state.showMsg}
              closeModel={this.handleMsgWindow.bind(this)}
              message={this.props.msg}
            />
          ) : null}
          <button
            className='portal_btn_claz'
            onClick={this.handleMsgWindow.bind(this)}
          >
            portals
          </button>
        </div>
        <div id='model_root'></div>
      </div>
    )
  }
}

export default Portals
