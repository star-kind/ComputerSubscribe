import React, { Component } from 'react'
import './table.less'
// import PropTypes from 'prop-types'
import { connect } from 'react-redux'
import { conveyIndexItem, actionsCollect } from '@/redux/redux-drill/actions'

class Table extends Component {
  constructor(props) {
    super(props)
    console.info('%cTable Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cTable componentDidMount\n', 'color:red', this)
  }

  componentDidUpdate(prevProps, prevState) {
    console.log('%c Table componentDidUpdate.this', 'color:red', this)
  }

  state = {
    //全选标志
    entireChoosed: false,
    //全选数据集合
    dataCollects: [],
    //单项复选标志
    rowCheck: [],
    //单次点击复选框选中的表格行数据
    rowCheckItems: [],
    //被编辑的目标用户数据行
    editTargetRow: {},
  }

  static propTypes = {
    // receivedChildData: PropTypes.func,
  }

  /**
   * 把某一行信息发往表单中展示,其数组索引亦发往major组件
   * @param {*} index
   * @param {*} item
   * @param {*} e
   */
  exhibitInDetailForm = (index, item, e) => {
    console.info('%c index\n', this.color(), index)
    console.info('%c item\n', this.color(), item)
    console.info('%c event', this.color(), e)
    let data = {
      item,
      index,
    }
    let displayVal = {
      formDisPlay: 'block',
      tblAndPageDisPlay: 'none',
    }
    //
    this.props.setIndexItem(data)
    this.props.checkDisPlay(displayVal)
  }

  /**
   * 全选
   * @param {*} event
   */
  totalChecked = (event) => {
    var boxStatus = false
    var statusArr = []
    var entireUsers = []
    var data = this.props.pagination.data
    // console.log('event.target\n', event.target)
    if (event.target.checked) {
      //若全选,获取状态数组和数据行数组
      for (let index = 0; index < data.length; index++) {
        statusArr.push(true)
        entireUsers.push(data[index])
      }
      boxStatus = true
    } else {
      for (let index = 0; index < data.length; index++) {
        statusArr.push(false)
      }
      entireUsers = []
      boxStatus = false
    }
    console.log('entireUsers\n', entireUsers)
    //css效果
    this.setState({
      rowCheck: statusArr,
      entireChoosed: boxStatus,
      dataCollects: entireUsers,
    })
  }

  /**
   * 单选
   * @param {*} item
   * @param {*} index
   * @param {*} event
   */
  aloneChecked = (item, index, event) => {
    let { rowCheck, rowCheckItems } = this.state
    console.log('aloneChecked-item\n', item)
    console.log('aloneChecked-index\n', index)
    // console.log('aloneChecked-event\n', event)
    if (event.target.checked) {
      //被勾选栏状态
      rowCheck[index] = true
      //把单选选中的数据行填入array
      rowCheckItems.push(item)
    } else {
      rowCheck[index] = false
      //从数组中移除被取消选中的数据行项
      for (let i = 0; i < rowCheckItems.length; i++) {
        //当数据行数组中元素的id与参数数据id相等时
        if (rowCheckItems[i].id === item.id) {
          rowCheckItems.splice(i, 1)
          break
        }
      }
    }
    console.log('rowCheckItems\n', rowCheckItems)
    //
    this.setState({
      rowCheck,
      rowCheckItems,
    })
  }

  //初始化循环动态生成的复选框选择状态值
  initialCheckbox = (value) => {
    let status = false
    if (value === null || value === undefined || value === false) {
      // console.log('value==', value)
    } else {
      status = true
    }
    return status
  }

  render() {
    return (
      <div className='main-table'>
        <div className='include_content'>
          <div className='hint_head'>
            <h3>某间机房本周内,所收到的预约申请列表</h3>
          </div>
          <div className='data_map_tbl'>
            <table className='tbl_class'>
              <thead className='thead_area'>
                <tr className='firs_th_tr'>
                  <th>
                    <span>全选</span>
                    <span>
                      <input
                        checked={this.state.entireChoosed}
                        type='checkbox'
                        onChange={this.totalChecked.bind(this)}
                      />
                    </span>
                  </th>
                  {this.props.tblHeadArr.map((value, i) => {
                    return (
                      <th key={i} className='th_ar'>
                        {value}
                      </th>
                    )
                  })}
                </tr>
              </thead>
              <tbody>
                {this.props.pagination.data.map((row, index) => {
                  return (
                    <tr key={index} className='trs'>
                      <td>
                        <span>{index + 1}</span>
                        <span>
                          <input
                            type='checkbox'
                            onChange={this.aloneChecked.bind(this, row, index)}
                            checked={this.initialCheckbox(
                              this.state.rowCheck[index]
                            )}
                            value={row.id}
                          />
                        </span>
                      </td>
                      <td>{row.sid}</td>
                      <td>{row.userName}</td>
                      <td>{row.userNum}</td>
                      <td>{row.applicant}</td>
                      <td>{row.applicationStartTime}</td>
                      <td>{row.applyUseDate}</td>
                      <td>{row.handleTime}</td>
                      <td>{row.mailbox}</td>
                      <td>{row.reviewer}</td>
                      <td>{row.role}</td>
                      <td>{row.roomNum}</td>
                      <td>{row.useInterval}</td>
                      <td>{row.subscribeStatus}</td>
                      <td>
                        <span className='sp_a'>
                          <li
                            onClick={this.exhibitInDetailForm.bind(
                              this,
                              index,
                              row
                            )}
                          >
                            审查
                          </li>
                        </span>
                      </td>
                    </tr>
                  )
                })}
              </tbody>
            </table>
          </div>
        </div>
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
      </div>
    )
  }
}

/*mapStateToProps:取值方法,mapDispatchToProps:赋值方法*/
const mapStateToProps = (state) => {
  console.log('Table.mapStateToProps.state', state)
  return {
    pagination: state.deliverDataReducer.pagination,
    tblHeadArr: state.deliverDataReducer.tblHeadArr,
  }
}

const mapDispatchToProps = {
  setIndexItem: conveyIndexItem,
  checkDisPlay: actionsCollect.checkOutTblOrFormDisplay,
}

export default connect(mapStateToProps, mapDispatchToProps)(Table)
