import React, { Component } from 'react'
import Model from '@/components/popup-window/model/model'
import PropTypes from 'prop-types'
import './portals2.less'

/**
 * Portals:门户
 */
class Portals2 extends Component {
  componentDidMount() {
    console.log('Portals2 ComponentDidMount')
    console.log(this)
  }

  state = {
    showMsgCont: false,
  }

  static propTypes = {
    msg: PropTypes.string.isRequired,
    isExhibit: PropTypes.bool.isRequired,
  }

  handleMsgWindow() {
    this.setState({
      showMsgCont: !this.state.showMsgCont,
    })
  }

  render() {
    return (
      <div className='portal_view_lead'>
        <div id='app_root'>
          {this.state.showMsgCont === this.props.isExhibit ? (
            <Model
              isShow={this.state.showMsgCont}
              closeModel={this.handleMsgWindow.bind(this)}
              message={this.props.msg}
            />
          ) : null}
        </div>
        <div id='model_root'></div>
      </div>
    )
  }
}

export default Portals2
