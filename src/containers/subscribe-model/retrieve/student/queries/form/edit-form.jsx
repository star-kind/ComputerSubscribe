import React, { Component } from 'react'
import './edit-form.less'
import PropTypes from 'prop-types'
import { getValueFromLocal } from '@/api/common'
import Portals2 from '@/components/popup-window/portals2/portals2'
import ConfirmIndex from '@/components/popup-window/confirm/confirm-index/confirm-index'
import { commonUtil } from '@/api/common2.js'

//学生决定是否撤回自己的预约申请
class EditForm extends Component {
  constructor(props) {
    super(props)
    console.info('%cEditForm Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cEditForm componentDidMount\n', 'color:red', this)
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
    //
    this.setRowDataToForm()
  }

  state = {
    applicant: '',
    applicationStartTime: '',
    applyUseDate: '',
    handleTime: '',
    id: '',
    reviewer: '',
    roomNum: '',
    subscribeStatus: '',
    useInterval: '',
    //
    radioId: 'radioId-',
    //
    whetherExhibit: true,
    message: '',
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
    //即将被审核处理的预约单
    tagetSubscibe: PropTypes.object,
    receivedChildData: PropTypes.func,
  }

  //将表格中某一行数据赋予表单
  setRowDataToForm = () => {
    let { tagetSubscibe } = this.props
    if (tagetSubscibe.applicant !== this.state.applicant) {
      this.setState({
        applicant: tagetSubscibe.applicant,
      })
    } else if (
      tagetSubscibe.applicationStartTime !== this.state.applicationStartTime
    ) {
      this.setState({
        applicationStartTime: tagetSubscibe.applicationStartTime,
      })
    } else if (tagetSubscibe.roomNum !== this.state.roomNum) {
      this.setState({
        roomNum: tagetSubscibe.roomNum,
      })
    } else if (tagetSubscibe.useInterval !== this.state.useInterval) {
      this.setState({
        useInterval: tagetSubscibe.useInterval,
      })
    } else if (tagetSubscibe.applyUseDate !== this.state.applyUseDate) {
      this.setState({
        applyUseDate: tagetSubscibe.applyUseDate,
      })
    } else if (tagetSubscibe.id !== this.state.id) {
      this.setState({
        id: tagetSubscibe.id,
      })
    } else if (tagetSubscibe.subscribeStatus !== this.state.subscribeStatus) {
      this.setState({
        subscribeStatus: tagetSubscibe.subscribeStatus,
      })
    }
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
    console.log('event.target\n', event.target)
    //
    this.setState({
      [event.target.name]: event.target.value,
    })
  }

  /**
   *
   * @param {*} interval
   * @returns
   */
  getInterval = (interval) => {
    let tip = ''
    switch (interval) {
      case 0:
        tip = '上午'
        break

      case 1:
        tip = '下午'
        break

      case 2:
        tip = '晚上'
        break

      default:
        break
    }
    return tip
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
        whetherExhibit: !this.state.whetherExhibit,
        message: tokenObj.text,
      })
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

  cancelSubscribe = (event) => {
    //阻止默认事件
    event.preventDefault()
    // console.log('event-target\n', event.target)
    this.setState({
      confirmMsg: '一旦撤销就不可恢复,确定撤销吗?',
      confirmExhibit: !this.state.confirmExhibit,
    })
    let { confirmData } = this.state
    console.log('%c confirmData', this.getColor(), confirmData)
  }

  /**
   *
   * @param {*} status
   * @returns
   */
  getStatus = (status) => {
    let tip = ''
    switch (status) {
      case 0:
        tip = '待审核'
        break

      case 1:
        tip = '已批准'
        break

      case 2:
        tip = '已驳回'
        break

      case 3:
        tip = '已自行撤回'
        break
      default:
        break
    }
    return tip
  }

  handleSubmit = (e) => {
    e.preventDefault()
    let ts = this
    let { id, subscribeStatus } = ts.state
    if (subscribeStatus === 3) {
      ts.setState({
        whetherExhibit: !ts.state.whetherExhibit,
        message: '此预约业已被您撤销,不可恢复',
      })
      return
    }
    //
    let tokenObject = ts.validatedToken()
    //studentCancelSubscribe
    ts.gets(
      ts.interfaces.studentCancelSubscribe,
      {
        subscribeID: id,
        status: 3,
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
        ts.setState({
          message: res.data.message,
          whetherExhibit: !ts.state.whetherExhibit,
        })
      }
    })
  }

  /**
   * 接收来自confirm组件返回的数据
   * @param {*} data
   * @returns
   */
  receiveConfirmData = (data) => {
    console.log('%creceiveData.data', this.color(), data)
    if (data.instruct === 1) {
      this.handleSubmit()
    } else {
      console.log('data.instruct==' + data.instruct)
    }
  }

  render() {
    return (
      <div className='main-edit-form'>
        <div className='pack_wrapper'>
          <div className='form_pack'>
            <form
              className='mine_form'
              onSubmit={this.cancelSubscribe.bind(this)}
            >
              <div className='input_wrap_div'>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label htmlFor='id_subscribe' className='labels_for_tag'>
                      预约单ID:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <input
                      readOnly={true}
                      id='id_subscribe'
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
                    <label htmlFor='id_applicant' className='labels_for_tag'>
                      申请人学号:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <input
                      readOnly={true}
                      id='id_applicant'
                      className='input_tag'
                      onChange={this.handleChange.bind(this)}
                      name='applicant'
                      type='text'
                      defaultValue={this.state.applicant}
                    />
                  </div>
                </div>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label
                      htmlFor='id_applicationStartTime'
                      className='labels_for_tag'
                    >
                      预约发起时间:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <p className='time_stamp_txt' id='id_applicationStartTime'>
                      {commonUtil.dateTimeFormat(
                        this.state.applicationStartTime
                      )}
                    </p>
                    <input
                      style={{ display: 'none' }}
                      readOnly={true}
                      className='input_tag'
                      onChange={this.handleChange.bind(this)}
                      name='applicationStartTime'
                      type='text'
                      defaultValue={this.state.applicationStartTime}
                    />
                  </div>
                </div>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label htmlFor='id_roomNum' className='labels_for_tag'>
                      申请机房房号:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <input
                      readOnly={true}
                      id='id_roomNum'
                      className='input_tag'
                      onChange={this.handleChange.bind(this)}
                      name='roomNum'
                      type='text'
                      defaultValue={this.state.roomNum}
                    />
                  </div>
                </div>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label htmlFor='id_useInterval' className='labels_for_tag'>
                      申请使用时段:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <input
                      readOnly={true}
                      id='id_useInterval'
                      className='input_tag'
                      onChange={this.handleChange.bind(this)}
                      name='useInterval'
                      type='text'
                      defaultValue={this.getInterval(this.state.useInterval)}
                    />
                  </div>
                </div>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label htmlFor='id_applyUseDate' className='labels_for_tag'>
                      申请使用日期:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <p className='time_stamp_txt' id='id_applyUseDate'>
                      {commonUtil.getTimeYearMonthDay(this.state.applyUseDate)}
                    </p>
                    <input
                      style={{ display: 'none' }}
                      readOnly={true}
                      className='input_tag'
                      onChange={this.handleChange.bind(this)}
                      name='applyUseDate'
                      type='text'
                      defaultValue={this.state.applyUseDate}
                    />
                  </div>
                </div>
                <div className='input_item_div'>
                  <div className='input_element'>
                    <label
                      className='labels_for_tag'
                      htmlFor='id_subscribeStatus'
                    >
                      预约状态:
                    </label>
                  </div>
                  <div className='input_element inputs_side'>
                    <input
                      readOnly={true}
                      id='id_subscribeStatus'
                      className='input_tag'
                      onChange={this.handleChange.bind(this)}
                      name='subscribeStatus'
                      type='text'
                      defaultValue={this.getStatus(this.state.subscribeStatus)}
                    />
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
                    value='撤回预约'
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
        <div className='ConfirmIndexDiv'>
          <ConfirmIndex
            whetherExhibit={this.state.confirmExhibit}
            msg={this.state.confirmMsg}
            receiveData={this.receiveConfirmData}
          ></ConfirmIndex>
        </div>
        <div className='Portals2Div'>
          <Portals2
            isExhibit={this.state.whetherExhibit}
            msg={this.state.message}
          ></Portals2>
        </div>
      </div>
    )
  }
}
export default EditForm
