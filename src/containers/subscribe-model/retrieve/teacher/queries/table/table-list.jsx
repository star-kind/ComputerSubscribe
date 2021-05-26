import React, { Component } from 'react'
import './table-list.less'
import PropTypes from 'prop-types'
import { commonUtil } from '@/api/common2.js'

class TableList extends Component {
  constructor(props) {
    super(props)
    console.info('%cTableList--Constructor\n', 'color:brown', this)
  }

  componentDidMount() {
    console.log('%cTableList component did mount\n', 'color:red', this)
  }

  componentDidUpdate(prevProps, prevState) {
    console.log(
      '%c TableList.componentDidUpdate prevProps',
      this.getColor(),
      prevProps
    )
    console.log(
      '%c TableList.componentDidUpdate prevState',
      this.getColor(),
      prevState
    )
    console.log('%c TableList.componentDidUpdate.this', this.getColor(), this)
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
    pagination: PropTypes.object,
    thArray: PropTypes.array,
    receivedChildData: PropTypes.func,
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
    console.log('event.target\n', event.target)
    //
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
    console.log('aloneChecked-event\n', event)
    //
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

  //在父组件使表单展示,使表格隐匿,并在表单上展示待编辑之数据
  showFormHideTable = (index, item, e) => {
    console.log('showFormHideTbl.index', index)
    console.log('showFormHideTbl.item', item)
    // console.log('showFormHideTbl.event', e)
    let data = { method: 'showFormHideTable', value: item, index: index }
    //调用(上层组件)函数,传送数据
    this.props.receivedChildData(data)
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

  render() {
    return (
      <div className='main-table-list'>
        <div>
          <div className='hint_head'>
            <h3>查看本周收到的全部机房预约申请</h3>
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
                  {this.props.thArray.map((value, i) => {
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
                    <tr key={row.id} className='trs'>
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
                      <td>{row.applicant}</td>
                      <td>{row.reviewer}</td>
                      <td>{this.getStatus(row.subscribeStatus)}</td>
                      <td>{row.roomNum}</td>
                      <td>
                        {commonUtil.dateTimeFormat(row.applicationStartTime)}
                      </td>
                      <td>{this.getInterval(row.useInterval)}</td>
                      <td>
                        {row.handleTime === null
                          ? ''
                          : commonUtil.dateTimeFormat(row.handleTime)}
                      </td>
                      <td>
                        {commonUtil.getTimeYearMonthDay(row.applyUseDate)}
                      </td>
                      <td>
                        <span className='sp_a'>
                          <li
                            onClick={this.showFormHideTable.bind(
                              this,
                              index,
                              row
                            )}
                          >
                            审批
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
      </div>
    )
  }
}
export default TableList
