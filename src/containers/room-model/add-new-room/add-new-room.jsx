import React, { Component } from 'react'
import './add-new-room.less'
// import PropTypes from 'prop-types'
import { getValueFromLocal } from '@/api/common'
import Portals2 from '@/components/popup-window/portals2/portals2'
import PublicHeader2 from '@/components/header2/header2'
import { commonUtil } from '@/api/common2.js'

class AddNewRoom extends Component {
  constructor(props) {
    super(props)
    console.info('%cAddNewRoom Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cAddNewRoom componentDidMount\n', 'color:red', this)
  }

  componentDidUpdate(prevProps, prevState) {
    console.log('%c AddNewRoom componentDidUpdate.this', 'color:red', this)
  }

  state = {
    //机房编号
    roomNum: '',
    //座位总数
    totalSets: '',
    //是否可用(0-不可用,1-可用)
    availableStatus: '',
    // 机房地点
    location: '',
    //实际可用机数
    actAvailableQuantity: '',
    //
    isExhibit: true,
    msg: '',
    //
    radioItemArr: [
      { name: '不可用', value: 0 },
      { name: '可使用', value: 1 },
    ],
    //
    radioId: 'radioId_',
  }

  static propTypes = {}

  //绑定on change事件,使input输入框能动态取值和赋值
  handleChange = (event) => {
    console.log('event.target\n', event.target)
    //
    this.setState({
      [event.target.name]: event.target.value,
    })
  }

  handleChangeRadio = (e) => {
    console.log('%c event.target.name', this.getColor(), e.target.name)
    this.setState({
      [e.target.name]: e.target.value,
    })
  }

  /**
   *
   * @returns
   */
  validatedToken = () => {
    let tokenObj = getValueFromLocal(this.store_key.token_key)
    console.log('%c tokenObj\n', this.getColor(), tokenObj)
    //token
    if (tokenObj.code === -1) {
      this.setState({
        isExhibit: !this.state.isExhibit,
        msg: tokenObj.text,
      })
      return
    } else {
      return tokenObj
    }
  }

  handleSubmit = (event) => {
    let ts = this
    let tokenObj = ts.validatedToken()
    let {
      roomNum,
      totalSets,
      availableStatus,
      location,
      actAvailableQuantity,
    } = ts.state
    //阻止默认事件
    event.preventDefault()
    //
    let params = [
      { name: '机房房间号码', val: roomNum },
      { name: '机房中座位总数量', val: totalSets },
      { name: '机房可用状态', val: availableStatus },
      { name: '机房地址', val: location },
      { name: '机房中实际可用电脑数量', val: actAvailableQuantity },
    ]
    //
    let res = commonUtil.verifyArrObj(params)
    if (!res.isVerify) {
      ts.setState({
        msg: res.hint,
        isExhibit: !ts.state.isExhibit,
      })
      return
    }
    //
    ts.gets(
      ts.interfaces.saveNewRoom,
      {
        roomNum: roomNum,
        totalSets: totalSets,
        availableStatus: availableStatus,
        location: location,
        actAvailableQuantity: actAvailableQuantity,
      },
      { token: tokenObj.text }
    ).then((res) => {
      console.info('handlesubmit response', res)
      if (res.data.code === 200) {
        ts.setState({
          isExhibit: !ts.state.isExhibit,
          msg: '添加一间新的机房成功',
        })
      } else {
        console.info('res.data.message', res.data.message)
        ts.setState({
          isExhibit: !ts.state.isExhibit,
          msg: res.data.message,
        })
      }
    })
  }

  render() {
    return (
      <div className='main-add-new-room'>
        <div className='header-div'>
          <PublicHeader2></PublicHeader2>
        </div>
        <div className='pack_wrapper'>
          <div className='form_pack'>
            <form
              className='mine_form'
              id='id_mine_form'
              onSubmit={this.handleSubmit.bind(this)}
            >
              <div className='label_of_form'>
                <label htmlFor='id_mine_form'>新增一间机房</label>
              </div>
              <div className='input_wrap_div'>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label htmlFor='id_roomNum' className='labels_for_tag'>
                      机房房号:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <input
                      id='id_roomNum'
                      className='input_tag'
                      onChange={this.handleChange.bind(this)}
                      name='roomNum'
                      type='number'
                      defaultValue={this.state}
                    />
                  </div>
                </div>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label htmlFor='id_totalSets' className='labels_for_tag'>
                      座位总数:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <input
                      id='id_totalSets'
                      className='input_tag'
                      onChange={this.handleChange.bind(this)}
                      name='totalSets'
                      type='number'
                      defaultValue={this.state}
                    />
                  </div>
                </div>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label htmlFor='id_location' className='labels_for_tag'>
                      地点:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <input
                      id='id_location'
                      className='input_tag'
                      onChange={this.handleChange.bind(this)}
                      name='location'
                      type='text'
                      defaultValue={this.state.location}
                    />
                  </div>
                </div>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label
                      htmlFor='id_actAvailableQuantity'
                      className='labels_for_tag'
                    >
                      实际可用机数:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <input
                      id='id_actAvailableQuantity'
                      className='input_tag'
                      onChange={this.handleChange.bind(this)}
                      name='actAvailableQuantity'
                      type='text'
                      defaultValue={this.state.actAvailableQuantity}
                    />
                  </div>
                </div>
              </div>
              <div className='input_item_div' id='mine-radio-area'>
                <div className='input_element'>
                  <label className='labels_for_tag'>机房是否可用:</label>
                </div>
                <div className='input_element inputs_side'>
                  <div className='radios_div' id='id_subscribeStatus'>
                    {this.state.radioItemArr.map((element, index) => {
                      return (
                        <div key={index} className='radio_item_div'>
                          <span>
                            <label
                              className='label_radio'
                              htmlFor={this.state.radioId + index}
                            >
                              {element.name}
                            </label>
                          </span>
                          <input
                            id={this.state.radioId + index}
                            onChange={(e) => this.handleChangeRadio(e)}
                            type='radio'
                            name='availableStatus'
                            defaultValue={element.value}
                          />
                        </div>
                      )
                    })}
                  </div>
                </div>
              </div>
              <div className='btn_div_items'>
                <div className='btn_input' id='reset_item'>
                  <input
                    className='input_btn_ele'
                    type='reset'
                    value='Reset'
                    id='reset_id'
                  />
                </div>
                <div className='btn_input' id='submit_item'>
                  <input
                    className='input_btn_ele'
                    type='submit'
                    value='Submit'
                    id='submit_id'
                  />
                </div>
              </div>
            </form>
          </div>
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
        </div>
        <div className='portals-div'>
          <Portals2
            isExhibit={this.state.isExhibit}
            msg={this.state.msg}
          ></Portals2>
        </div>
      </div>
    )
  }
}

export default AddNewRoom
