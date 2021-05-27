import React, { Component } from 'react'
import './add-new-apply.less'
import { commonUtil } from '@/api/common2'
import PublicHeader2 from '@/components/header2/header2'
import Portals2 from '@/components/popup-window/portals2/portals2'

class AddNewApply extends Component {
  constructor(props) {
    super(props)
    console.info('%cAddNewApply--Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cAddNewApply component did mount\n', 'color:red', this)
    //获取机房列表信息,初始化机房选择列表
    this.handleGetRoomList()
  }

  componentDidUpdate(previousProps, previousState) {
    console.log('%cAddNewApply componentDidUpdate\n', 'color:red', this)
  }

  state = {
    useIntervalArr: [
      { name: '--请选择时间段--', num: '' },
      { name: '上午', num: 0 },
      { name: '下午', num: 1 },
      { name: '晚上', num: 2 },
    ],
    //
    applyUseDate: null,
    roomNum: null,
    useInterval: null,
    //
    whetherExhibit: true,
    message: '',
    //
    roomNumArr: [],
  }

  //获取机房编号数组
  handleGetRoomList = () => {
    let _this = this
    let token = _this.getToken()
    //
    _this
      .gets(_this.interfaces.retrieveRoomNumArr, {}, { token: token })
      .then((result) => {
        console.log('%c result=', _this.getColor(), result)
        if (result.data.code === 200) {
          _this.setState({
            roomNumArr: result.data.data,
          })
        } else {
          _this.setState({
            message: result.data.message,
            whetherExhibit: !_this.state.whetherExhibit,
          })
        }
      })
      .catch((err) => {
        console.error(err)
      })
  }

  getToken = () => {
    var valObj = commonUtil.getValueFromLocal(this.store_key.token_key)
    console.log('%c valObj', this.getColor(), valObj)
    if (valObj.code !== -1) {
      return valObj.text
    }
    return null
  }

  // 绑定 on select 事件
  handleSelect = (e) => {
    //触发onChange事件时,得到的值
    let optValue = e.target.value
    console.log('%c optValue', this.color(), optValue)
    this.setState({
      [e.target.name]: optValue,
    })
  }

  //绑定on change事件,使input输入框能动态取值和赋值
  handleChange = (event) => {
    console.log('event.target', event.target)
    // e.target.name代表当前输入Input框对应的Name,如email,realName
    // e.target.value代表当前输入的值
    this.setState({
      [event.target.name]: event.target.value,
    })
  }

  // validateParams = (params) => {
  //   console.info('%c validateParams.params', this.getColor(), params)
  //   let { applyUseDate, useInterval, roomNum } = params
  //   let result = { warnTip: '', whether: false }
  //   //
  //   if (useInterval === null) {
  //     result.warnTip = '请选择时间段'
  //   } else if (applyUseDate === null) {
  //     result.warnTip = '请选择日期'
  //   } else if (roomNum === null || roomNum === '--请选择机房房号--') {
  //     result.warnTip = '请选择机房'
  //   } else {
  //     result.whether = true
  //   }
  //   //
  //   console.info('%c validateParams.result', this.getColor(), result)
  //   return result
  // }

  handleSubmit = (event) => {
    let ts = this
    let token = ts.getToken()
    let { applyUseDate, roomNum, useInterval } = ts.state
    //阻止默认事件
    event.preventDefault()
    //
    let parameters = [
      { name: '申请预约使用机房的日期', val: applyUseDate },
      { name: '申请预约使用机房的房间号', val: roomNum },
      { name: '申请预约使用机房的某个时段', val: useInterval },
    ]
    let res = commonUtil.verifyArrObj(parameters)
    if (!res.isVerify) {
      ts.setState({
        message: res.hint,
        whetherExhibit: !ts.state.whetherExhibit,
      })
      return
    }
    //
    ts.gets(
      ts.interfaces.addNewApply,
      {
        useInterval: useInterval,
        roomNum: roomNum,
        applyUseDate: applyUseDate,
      },
      { token: token }
    ).then((res) => {
      console.info('handlesubmit response', res)
      if (res.data.code === 200) {
        ts.setState({
          whetherExhibit: !ts.state.whetherExhibit,
          message: '已提交预约申请',
        })
      } else {
        console.info('handlesubmit res.data.message', res.data.message)
        ts.setState({
          whetherExhibit: !ts.state.whetherExhibit,
          message: res.data.message,
        })
      }
    })
  }

  render() {
    return (
      <div className='main-add-new-apply'>
        <PublicHeader2></PublicHeader2>
        <div>
          <div className='pack_wrapper'>
            <div className='form_pack'>
              <form
                className='mine_form'
                onSubmit={this.handleSubmit.bind(this)}
              >
                <div className='input_wrap_div'>
                  <div className='input_item_div'>
                    <div className='input_element'>
                      <label
                        htmlFor='id_useInterval'
                        className='labels_for_tag'
                      >
                        请选择时间段:
                      </label>
                    </div>
                    <div className='input_element inputs_side'>
                      <select
                        name='useInterval'
                        id='id_useInterval'
                        className='selects-tag'
                        onChange={this.handleSelect}
                      >
                        {this.state.useIntervalArr.map((item, index) => {
                          return (
                            <option key={index} value={item.num}>
                              {item.name}
                            </option>
                          )
                        })}
                      </select>
                    </div>
                  </div>
                  <div className='input_item_div'>
                    <div className='input_element'>
                      <label
                        htmlFor='id_applyUseDate'
                        className='labels_for_tag'
                      >
                        请选择日期:
                      </label>
                    </div>
                    <div className='input_element inputs_side'>
                      <input
                        id='id_applyUseDate'
                        className='input_tag'
                        onChange={this.handleChange.bind(this)}
                        name='applyUseDate'
                        type='date'
                        defaultValue={this.state.applyUseDate}
                      />
                    </div>
                  </div>
                  <div className='input_item_div'>
                    <div className='input_element'>
                      <label htmlFor='id_roomNum' className='labels_for_tag'>
                        请选择机房编号:
                      </label>
                    </div>
                    <div className='input_element inputs_side'>
                      <select
                        onChange={this.handleSelect}
                        name='roomNum'
                        id='id_roomNum'
                        className='selects-tag'
                      >
                        <option value={''}>--请选择机房房号--</option>
                        {this.state.roomNumArr.map((item, index) => {
                          return (
                            <option key={index} value={item}>
                              {item}号机房
                            </option>
                          )
                        })}
                      </select>
                    </div>
                  </div>
                </div>
                <div className='btn_div_items'>
                  <div className='btn_input' id='reset_item'>
                    <input
                      className='input_btn_ele'
                      type='reset'
                      defaultValue='Reset'
                      id='reset_id'
                    />
                  </div>
                  <div className='btn_input' id='submit_item'>
                    <input
                      className='input_btn_ele'
                      type='submit'
                      defaultValue='Submit'
                      id='submit_id'
                    />
                  </div>
                </div>
              </form>
            </div>
            <Portals2
              isExhibit={this.state.whetherExhibit}
              msg={this.state.message}
            ></Portals2>
            <br />
            <br />
            <br />
            <br />
            <br />
            <br />
          </div>
        </div>
      </div>
    )
  }
}
export default AddNewApply
