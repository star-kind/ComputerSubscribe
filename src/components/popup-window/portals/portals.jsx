import React, { Component } from 'react'
import Modal from '@/components/popup-window/modal/modal'
import PropTypes from 'prop-types'
import './portals.less'

/**
 * Portals:门户
 */
class Portals extends Component {
  constructor(props) {
    super(props)
    console.log('%cPortals.constructor', this.getColor(), this)
  }

  componentDidMount() {
    console.log('%cPortalsComponentDidMount', this.getColor(), this)
  }

  componentDidUpdate(prevProps, prevState) {
    console.log(
      '%c Portals.componentDidUpdate.prevProps',
      this.getColor(),
      prevProps
    )
    console.log(
      '%c Portals.componentDidUpdate.prevState',
      this.getColor(),
      prevState
    )
    console.log('%c Portals.componentDidUpdate.this', this.getColor(), this)
  }

  state = {
    // 是否展示,真-显示,假-隐藏
    whetherExhibit: false,
  }

  static propTypes = {
    isExhibit: PropTypes.bool.isRequired,
    msg: PropTypes.string.isRequired,
  }

  handleMsgWindow() {
    this.setState({
      whetherExhibit: !this.state.whetherExhibit,
    })
  }

  render() {
    return (
      <div className='portal_view_lead'>
        <div id='app_root'>
          {console.log(
            '%c props.isExhibit=',
            this.getColor(),
            this.props.isExhibit
          )}
          {this.state.whetherExhibit === this.props.isExhibit ? (
            <Modal
              isShow={this.state.whetherExhibit}
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

export default Portals
