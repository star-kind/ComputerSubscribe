import React, { Component } from 'react'
import './add-new-apply.less'
import { commonUtil } from '@/api/common2'
import PublicHeader2 from '@/components/header2/header2'

class AddNewApply extends Component {
  constructor(props) {
    super(props)
    console.info('%cAddNewApply--Constructor\n', 'color:brown', this)
    //获取机房列表信息,初始化机房选择列表
    //this.handleGetRoomList()
  }

  componentDidMount() {
    console.log('%cAddNewApply component did mount\n', 'color:red', this)
    this.getToken()
  }

  state = {
    token: '',
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
  }

  getToken = () => {
    var valObj = commonUtil.getValueFromLocal(this.store_key.token_key)
    console.log('%c valObj\n', valObj)
    if (valObj.code !== -1) {
      this.setState({
        token: valObj.text,
      })
    }
  }

  // 绑定 on select 事件
  handleSelect = (e) => {
    console.log(e.target)
    //触发onChange事件时,得到的值
    let optValue = e.target.value
    console.log('%c optValue', this.getColor(), optValue)
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

  handleSubmit = (event) => {
    let ts = this
    //阻止默认事件
    event.preventDefault()
    //
    let params = {
      applyUseDate: ts.state.applyUseDate,
      roomNum: ts.state.roomNum,
      useInterval: ts.state.useInterval,
    }
    console.info('%c params', this.getColor(), params)
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
                        {this.state.useIntervalArr.map((item) => {
                          return (
                            <option key={item.name} value={item.num}>
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
                        <option defaultValue='10'>10号机房</option>
                        <option defaultValue='12'>12号机房</option>
                        <option defaultValue='22'>22号机房</option>
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
