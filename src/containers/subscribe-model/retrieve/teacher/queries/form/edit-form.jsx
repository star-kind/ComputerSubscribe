import React, { Component } from 'react'
import './edit-form.less'
import PropTypes from 'prop-types'
import { getValueFromLocal } from '@/api/common'
import Portals2 from '@/components/popup-window/portals2/portals2'

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
    radioItemArr: [
      { name: '审核中', value: 0 },
      { name: '批准预约', value: 0 },
      { name: '驳回预约', value: 2 },
    ],
    //
    radioId: 'radioId-',
    //
    whetherExhibit: true,
    message: '',
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

  handleChangeRadioValue = (e) => {
    console.log('%c handleChangeRadioValue.event', this.getColor(), e)
    this.setState({
      subscribeStatus: e.target.value,
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

  handleSubmit = (event) => {
    let ts = this
    //阻止默认事件
    event.preventDefault()
    // console.log('event-target\n', event.target)
    /*
    [Query(key=status, description=申请单新的审核状态), Query(key=subscribeID, description=申请单ID)]
    */
    let params = {
      id: ts.state.id,
      status: ts.state.subscribeStatus,
    }
    //
    if (params.status === '') {
      ts.setState({
        message: '请选择如何处理这份预约申请',
        whetherExhibit: !ts.state.whetherExhibit,
      })
      return
    }
    //
    let tokenObject = this.validatedToken()
    console.log(
      '%c params\n',
      ts.getColor(),
      params,
      'tokenObject',
      tokenObject
    )
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
                    <input
                      readOnly={true}
                      id='id_applicationStartTime'
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
                    <input
                      readOnly={true}
                      id='id_applyUseDate'
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
                    <label className='labels_for_tag'>处理决定:</label>
                  </div>
                  <div className='input_element inputs_side'>
                    <div className='radios_div' id='id_subscribeStatus'>
                      {this.state.radioItemArr.map((element, index) => {
                        return (
                          <div key={index} className='radio_item_div'>
                            <input
                              id={this.state.radioId + index}
                              onChange={(e) => this.handleChangeRadioValue(e)}
                              type='radio'
                              name='subscribeStatus'
                              defaultValue={element.value}
                            />
                            <span>
                              <label
                                className='label_radio'
                                htmlFor={this.state.radioId + index}
                              >
                                {element.name}
                              </label>
                            </span>
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
                    value='取消编辑'
                    id='cancel_revamp_id'
                  />
                </div>
                <div className='btn_input' id='reset_item'>
                  <input
                    className='input_btn_ele'
                    type='reset'
                    value='复位'
                    id='reset_id'
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
        </div>
        <Portals2
          isExhibit={this.state.whetherExhibit}
          msg={this.state.message}
        ></Portals2>
      </div>
    )
  }
}
export default EditForm
