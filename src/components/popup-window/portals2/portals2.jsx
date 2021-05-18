import React, { Component } from 'react'
import Modal from '@/components/popup-window/modal/modal'
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
    // 是否展示,真-显示,假-隐藏
    isShowCont: false,
  }

  static propTypes = {
    msg: PropTypes.string.isRequired,
    isExhibit: PropTypes.bool.isRequired,
  }

  handleMsgWindow() {
    this.setState({
      isShowCont: !this.state.isShowCont,
    })
  }

  render() {
    return (
      <div className='portal_view_lead'>
        <div id='app_root'>
          {console.log(
            '%c Portals2.props.isExhibit',
            this.getColor(),
            this.props.isExhibit
          )}
          {console.log(
            '%c Portals2.state.isShowCont',
            this.getColor(),
            this.state.isShowCont
          )}
          {this.state.isShowCont === this.props.isExhibit ? (
            <Modal
              isShow={this.state.isShowCont}
              message={this.props.msg}
              closeModal={this.handleMsgWindow.bind(this)}
            />
          ) : null}
        </div>
        <div id='modal_root'></div>
      </div>
    )
  }
}

export default Portals2
