import React, { Component } from 'react'
import './DetailForm.less'
import PropTypes from 'prop-types'
import { connect } from 'react-redux'
import { actionsCollect } from '@/redux/redux-drill/actions'
import { commonUtil } from '@/api/common2'
import Portals from '@/components/popup-window/portals/portals'

class DetailForm extends Component {
  constructor(props) {
    super(props)
    console.info('%cDetailForm Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cDetailForm componentDidMount\n', 'color:red', this)
  }

  componentDidUpdate(prevProps, prevState) {
    console.log('%c DetailForm componentDidUpdate.this', 'color:red', this)
  }

  static getDerivedStateFromProps(nextProps, prevState) {
    console.log(
      'DetailFormGetDerivedStateFromPropsNextProps',
      nextProps,
      'prevState',
      prevState
    )
    let { rowData } = nextProps
    if (rowData.sid !== prevState.sid) {
      return {
        sid: rowData.sid,
        userName: rowData.userName,
        userNum: rowData.userNum,
        applicant: rowData.applicant,
        applicationStartTime: rowData.applicationStartTime,
        applyUseDate: rowData.applyUseDate,
        handleTime: rowData.handleTime,
        mailbox: rowData.mailbox,
        phone: rowData.phone,
        reviewer: rowData.reviewer,
        role: rowData.role,
        roomNum: rowData.roomNum,
        subscribeStatus: rowData.subscribeStatus,
        useInterval: rowData.useInterval,
      }
    }
    // 否则,对于state不进行任何操作
    return null
  }

  state = {
    sid: '',
    userName: '',
    userNum: '',
    applicant: '',
    applicationStartTime: '',
    applyUseDate: '',
    handleTime: '',
    mailbox: '',
    phone: '',
    reviewer: '',
    role: '',
    roomNum: '',
    subscribeStatus: '',
    useInterval: '',
    //
    whetherExhibit: true,
    msg: '',
  }

  static propTypes = {
    receivedChildData: PropTypes.func,
  }

  /**
   *
   * @returns
   */
  validateParams = () => {
    let { roomNum, subscribeStatus, useInterval } = this.state
    let parameters = [
      { name: '机房', val: roomNum },
      { name: '预约现审核状态', val: subscribeStatus },
      { name: '预约时段', val: useInterval },
    ]
    return commonUtil.verifyArrObj(parameters)
  }

  handleChanging = (e) => {
    // console.log('%c handleChanging.event', this.color(), e.target)
    this.setState({
      [e.target.name]: e.target.value,
    })
  }

  handleSubmits = (event) => {
    event.preventDefault()
    let ts = this
    let { roomNum, subscribeStatus, useInterval } = ts.props.rowData
    let stateParams = {
      roomNum: ts.state.roomNum,
      subscribeStatus: ts.state.subscribeStatus,
      useInterval: ts.state.useInterval,
    }
    //
    let resultObj = ts.validateParams()
    if (!resultObj.isVerify) {
      ts.setState({
        msg: resultObj.hint,
        whetherExhibit: !ts.state.whetherExhibit,
      })
      return
    }
    //
    if (
      stateParams.roomNum === roomNum &&
      stateParams.subscribeStatus === subscribeStatus &&
      stateParams.useInterval === useInterval
    ) {
      ts.setState({
        msg: '若想修改预约,请填写与之前不同的参数',
        whetherExhibit: !ts.state.whetherExhibit,
      })
      return
    }
    console.info('%c handleSubmits_stateParams', ts.color(), stateParams)
    //
    let data = {
      classMethod: 'DetailForm_handleSubmits',
      parameter: stateParams,
    }
    ts.props.receivedChildData(data)
  }

  //返回列表,隐匿表单,显示表格和页码
  backToList = () => {
    let data = {
      formDisPlay: 'none',
      tblAndPageDisPlay: 'block',
    }
    this.props.checkDisPlay(data)
  }

  render() {
    return (
      <div className='main-DetailForm'>
        <div className='mine_form_div'>
          <div className='form_div'>
            <form
              className='mine_form'
              onSubmit={this.handleSubmits.bind(this)}
            >
              <div className='form_content'>
                <div className='info_of_form'>
                  <h4>预约单详细</h4>
                </div>
                <div className='inputs_params_div'>
                  <div className='inputs_parameter'>
                    <div className='lable_of_input param_unit'>
                      <label htmlFor='id_input_sid'>预约单ID:</label>
                    </div>
                    <div className='input_ele_div param_unit'>
                      <input
                        readOnly={true}
                        type='text'
                        name='sid'
                        defaultValue={this.state.sid}
                        id='id_input_sid'
                      ></input>
                    </div>
                  </div>
                  <div className='inputs_parameter'>
                    <div className='lable_of_input param_unit'>
                      <label htmlFor='id_input_userName'>用户名称:</label>
                    </div>
                    <div className='input_ele_div param_unit'>
                      <input
                        readOnly={true}
                        type='text'
                        name='userName'
                        defaultValue={this.state.userName}
                        id='id_input_userName'
                      ></input>
                    </div>
                  </div>
                  <div className='inputs_parameter'>
                    <div className='lable_of_input param_unit'>
                      <label htmlFor='id_input_userNum'>学号:</label>
                    </div>
                    <div className='input_ele_div param_unit'>
                      <input
                        readOnly={true}
                        type='text'
                        name='userNum'
                        defaultValue={this.state.userNum}
                        id='id_input_userNum'
                      ></input>
                    </div>
                  </div>
                  <div className='inputs_parameter'>
                    <div className='lable_of_input param_unit'>
                      <label htmlFor='id_input_applicant'>申请人学号:</label>
                    </div>
                    <div className='input_ele_div param_unit'>
                      <input
                        readOnly={true}
                        type='text'
                        name='applicant'
                        defaultValue={this.state.applicant}
                        id='id_input_applicant'
                      ></input>
                    </div>
                  </div>
                  <div className='inputs_parameter'>
                    <div className='lable_of_input param_unit'>
                      <label htmlFor='id_input_applicationStartTime'>
                        申请发起时间:
                      </label>
                    </div>
                    <div className='input_ele_div param_unit'>
                      <input
                        readOnly={true}
                        type='text'
                        name='applicationStartTime'
                        defaultValue={this.state.applicationStartTime}
                        id='id_input_applicationStartTime'
                      ></input>
                    </div>
                  </div>
                  <div className='inputs_parameter'>
                    <div className='lable_of_input param_unit'>
                      <label htmlFor='id_input_applyUseDate'>
                        申请使用日期:
                      </label>
                    </div>
                    <div className='input_ele_div param_unit'>
                      <input
                        readOnly={true}
                        type='text'
                        name='applyUseDate'
                        defaultValue={this.state.applyUseDate}
                        id='id_input_applyUseDate'
                      ></input>
                    </div>
                  </div>
                  <div className='inputs_parameter'>
                    <div className='lable_of_input param_unit'>
                      <label htmlFor='id_input_handleTime'>审核处理时间:</label>
                    </div>
                    <div className='input_ele_div param_unit'>
                      <input
                        readOnly={true}
                        type='text'
                        name='handleTime'
                        defaultValue={this.state.handleTime}
                        id='id_input_handleTime'
                      ></input>
                    </div>
                  </div>
                  <div className='inputs_parameter'>
                    <div className='lable_of_input param_unit'>
                      <label htmlFor='id_input_mailbox'>申请者邮箱:</label>
                    </div>
                    <div className='input_ele_div param_unit'>
                      <input
                        readOnly={true}
                        type='text'
                        name='mailbox'
                        defaultValue={this.state.mailbox}
                        id='id_input_mailbox'
                      ></input>
                    </div>
                  </div>
                  <div className='inputs_parameter'>
                    <div className='lable_of_input param_unit'>
                      <label htmlFor='id_input_reviewer'>审核者工号:</label>
                    </div>
                    <div className='input_ele_div param_unit'>
                      <input
                        readOnly={true}
                        type='text'
                        name='reviewer'
                        defaultValue={this.state.reviewer}
                        id='id_input_reviewer'
                      ></input>
                    </div>
                  </div>
                  <div className='inputs_parameter'>
                    <div className='lable_of_input param_unit'>
                      <label htmlFor='id_input_role'>申请者角色:</label>
                    </div>
                    <div className='input_ele_div param_unit'>
                      <input
                        readOnly={true}
                        type='text'
                        name='role'
                        defaultValue={this.state.role}
                        id='id_input_role'
                      ></input>
                    </div>
                  </div>
                  <div className='inputs_parameter'>
                    <div className='lable_of_input param_unit'>
                      <label htmlFor='id_input_role'>申请预约机房:</label>
                    </div>
                    <div className='input_ele_div param_unit'>
                      <input
                        onChange={this.handleChanging.bind(this)}
                        type='text'
                        name='roomNum'
                        defaultValue={this.state.roomNum}
                        id='id_input_roomNum'
                      ></input>
                    </div>
                  </div>
                  <div className='inputs_parameter'>
                    <div className='lable_of_input param_unit'>
                      <label htmlFor='id_input_useInterval'>
                        申请预约时段:
                      </label>
                    </div>
                    <div className='input_ele_div param_unit'>
                      <input
                        onChange={this.handleChanging.bind(this)}
                        type='text'
                        name='useInterval'
                        defaultValue={this.state.useInterval}
                        id='id_input_useInterval'
                      ></input>
                    </div>
                  </div>
                  <div className='inputs_parameter'>
                    <div className='lable_of_input param_unit'>
                      <label htmlFor='id_input_subscribeStatus'>
                        预约单现状态:
                      </label>
                    </div>
                    <div className='input_ele_div param_unit'>
                      <input
                        onChange={this.handleChanging.bind(this)}
                        type='text'
                        name='subscribeStatus'
                        defaultValue={this.state.subscribeStatus}
                        id='id_input_subscribeStatus'
                      ></input>
                    </div>
                  </div>
                </div>
                <div className='btns_div'>
                  <div className='include_inputs_div'>
                    <div className='reset_btn_div btn_single_div'>
                      <input
                        type='button'
                        value='返回列表'
                        id='id_back_to_list'
                        onClick={this.backToList}
                      ></input>
                    </div>
                    <div className='reset_btn_div btn_single_div'>
                      <input
                        type='reset'
                        value='Reset'
                        id='id_input_reset'
                      ></input>
                    </div>
                    <div className='submit_btn_div btn_single_div'>
                      <input
                        type='submit'
                        value='Submit'
                        id='id_input_submit'
                      ></input>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
        <div className='PortalsDiv'>
          <Portals
            isExhibit={this.state.whetherExhibit}
            msg={this.state.msg}
          ></Portals>
        </div>
      </div>
    )
  }
}

/*mapStateToProps:取值方法,mapDispatchToProps:赋值方法*/
const mapStateToProps = (state) => {
  console.log('%cDetailForm.mapStateToProps', 'color:brown', state)
  return {
    rowData: state.deliverDataReducer.indexItem.item,
  }
}

const mapDispatchToProps = {
  checkDisPlay: actionsCollect.checkOutTblOrFormDisplay,
}

export default connect(mapStateToProps, mapDispatchToProps)(DetailForm)
