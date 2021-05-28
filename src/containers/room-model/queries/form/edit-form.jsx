import React, { Component } from 'react'
import './edit-form.less'
import PropTypes from 'prop-types'
import { getValueFromLocal } from '@/api/common'
import { commonUtil } from '@/api/common2.js'

//管理员编辑机房数据
class EditForm extends Component {
  constructor(props) {
    super(props)
    console.info('%cEditForm Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cEditForm componentDidMount\n', 'color:red', this)
  }

  /**
   * 若欲利用 props 初始化 state , 最好在此处
   * @param {*} props
   * @param {*} state
   * @returns
   */
  static getDerivedStateFromProps(props, state) {
    console.log('getDerivedStateFromProps.props', props, 'state', state)
    let { tagetedSelectd } = props
    if (tagetedSelectd.id !== state.id) {
      return {
        id: tagetedSelectd.id,
        roomNum: tagetedSelectd.roomNum,
        totalSets: tagetedSelectd.totalSets,
        availableStatus: tagetedSelectd.availableStatus,
        adminNumOperated: tagetedSelectd.adminNumOperated,
        operatedTime: tagetedSelectd.operatedTime,
        location: tagetedSelectd.location,
        actAvailableQuantity: tagetedSelectd.actAvailableQuantity,
      }
    }
    //如果返回 null 则不更新任何内容.但注意在没有内容更新的情况下也一定要返回一个null值.不然会报错.
    return null
  }

  componentDidUpdate(prevProps, prevState) {
    console.log(
      '%c EditForm-prevProps\n',
      'color:blue',
      prevProps,
      'prevState\n',
      prevState
    )
    console.log('%c EditForm-this', 'color:green', this)
  }

  state = {
    id: '',
    roomNum: '',
    totalSets: '',
    availableStatus: '',
    adminNumOperated: '',
    operatedTime: '',
    location: '',
    actAvailableQuantity: '',
    //
    radioItemArr: [
      { name: '不可用', value: 0 },
      { name: '可使用', value: 1 },
    ],
    radioId: 'radioId-',
    //
    confirmMsg: '',
    confirmExhibit: true,
    //
    confirmData: {
      comment: '',
      instruct: null,
      method: '',
    },
  }

  static propTypes = {
    //被选中编辑的单份数据
    tagetedSelectd: PropTypes.object,
    receivedChildData: PropTypes.func,
  }

  //取消编辑,隐匿表单,显示表格
  hideFormExhibitTbl = () => {
    let instruct = {
      method: 'hideFormExhibitTbl',
      value: { form: 'none', tableAndPageInfo: 'block' },
    }
    //调用(上层组件)函数,传送数据
    this.props.receivedChildData(instruct)
  }

  //绑定on change事件,使input输入框能动态取值和赋值
  handleChange = (event) => {
    let { value, name } = event.target
    console.log('eventTargetValue', value, 'name', name)
    this.setState({
      [event.target.name]: value,
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
      alert(tokenObj.text)
      return
    } else {
      return tokenObj
    }
  }

  updatedTblRowData = (newdata) => {
    //传送新数据给上层组件
    this.props.receivedChildData({
      data: newdata,
      method: 'updatedTblRowData',
    })
  }

  validateSubmit = () => {
    let {
      roomNum,
      totalSets,
      availableStatus,
      location,
      actAvailableQuantity,
    } = this.state
    //
    let params = [
      { name: '机房房间号码', val: roomNum },
      { name: '机房中座位总数量', val: totalSets },
      { name: '机房可用状态', val: availableStatus },
      { name: '机房地址', val: location },
      { name: '机房中实际可用电脑数量', val: actAvailableQuantity },
    ]
    return commonUtil.verifyArrObj(params)
  }

  handleSubmit = (e) => {
    e.preventDefault()
    let ts = this
    //
    let res = ts.validateSubmit()
    if (!res.isVerify) {
      alert(res.hint)
      return
    }
    //
    let tokenObject = ts.validatedToken()
    let {
      id,
      roomNum,
      totalSets,
      availableStatus,
      location,
      actAvailableQuantity,
    } = ts.state
    //
    ts.gets(
      ts.interfaces.reviseRoomInfo,
      {
        roomNum: roomNum,
        totalSets: totalSets,
        availableStatus: availableStatus,
        location: location,
        actAvailableQuantity: actAvailableQuantity,
        id: id,
      },
      { token: tokenObject.text }
    ).then((res) => {
      console.info('%c response', ts.color(), res)
      if (res.data.code === 200) {
        //更新父组件的数据
        ts.updatedTblRowData(res.data.data)
        //
        ts.hideFormExhibitTbl()
      } else {
        alert(res.data.message)
      }
    })
  }

  render() {
    return (
      <div className='main-edit-form'>
        <div className='pack_wrapper'>
          <div className='form_pack'>
            <form className='mine_form' onSubmit={this.handleSubmit.bind(this)}>
              <div className='input_wrap_div'>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label htmlFor='id_room' className='labels_for_tag'>
                      机房ID:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <input
                      readOnly={true}
                      id='id_room'
                      className='input_tag'
                      onChange={this.handleChange.bind(this)}
                      name='id'
                      type='text'
                      defaultValue={this.state.id}
                    />
                  </div>
                </div>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label htmlFor='id_roomNum' className='labels_for_tag'>
                      机房编号:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <input
                      id='id_roomNum'
                      className='input_tag'
                      onChange={this.handleChange.bind(this)}
                      name='roomNum'
                      type='number'
                      defaultValue={this.state.roomNum}
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
                      type='text'
                      defaultValue={this.state.totalSets}
                    />
                  </div>
                </div>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label
                      htmlFor='id_actAvailableQuantity'
                      className='labels_for_tag'
                    >
                      实际可用座位数:
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
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label
                      htmlFor='id_adminNumOperated'
                      className='labels_for_tag'
                    >
                      最近编辑者:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <input
                      readOnly={true}
                      id='id_adminNumOperated'
                      className='input_tag'
                      onChange={this.handleChange.bind(this)}
                      name='adminNumOperated'
                      type='text'
                      defaultValue={this.state.adminNumOperated}
                    />
                  </div>
                </div>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label htmlFor='id_operatedTime' className='labels_for_tag'>
                      最近修改时间:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <p className='time_stamp_txt' id='id_operatedTime'>
                      {commonUtil.dateTimeFormat(this.state.operatedTime)}
                    </p>
                    <input
                      style={{ display: 'none' }}
                      readOnly={true}
                      className='input_tag'
                      type='text'
                      defaultValue={commonUtil.dateTimeFormat(
                        this.state.operatedTime
                      )}
                    />
                  </div>
                </div>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label htmlFor='id_location' className='labels_for_tag'>
                      机房地址:
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
                <div className='input_item_div' id='mine-radio-area'>
                  <div className='input_element'>
                    <label className='labels_for_tag'>机房可用状态:</label>
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
              </div>
              <div className='btn_div_items'>
                <div className='btn_input' id='cancel_revamp_item'>
                  <input
                    onClick={this.hideFormExhibitTbl}
                    className='input_btn_ele'
                    type='button'
                    value='返回列表'
                    id='cancel_revamp_id'
                  />
                </div>
                <div className='btn_input' id='submit_item'>
                  <input
                    className='input_btn_ele'
                    type='submit'
                    value='提交'
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
          <br />
          <br />
        </div>
      </div>
    )
  }
}

export default EditForm
