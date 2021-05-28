import React, { Component } from 'react'
import './table-list.less'
import PropTypes from 'prop-types'
import { commonUtil } from '@/api/common2.js'
import ConfirmIndex from '@/components/popup-window/confirm/confirm-index/confirm-index'
import Portals2 from '@/components/popup-window/portals2/portals2'

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
    //
    confirmMsg: '',
    confirmExhibit: true,
    //
    confirmData: {
      comment: '',
      instruct: null,
      method: '',
    },
    //可能会被删除的元素的下标
    deleteIndex: '',
    // 缓存将被删除机房的数据项
    deleteItem: {},
    //
    isExhibit: true,
    msg: '',
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

  //据机房id删除机房
  deleteRoomById = (index, item, e) => {
    this.setState({
      confirmMsg: '确定删除此间机房吗?',
      confirmExhibit: !this.state.confirmExhibit,
      // 缓存将被删除机房的数组下标
      deleteIndex: index,
      // 缓存将被删除机房的数据项
      deleteItem: item,
    })
    console.log(
      'deleteRoomById-index',
      this.color(),
      index,
      '\nitem>>>>>',
      item
    )
  }

  /**
   *
   * @returns
   */
  validatedToken = () => {
    let tokenObj = commonUtil.getValueFromLocal(this.store_key.token_key)
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

  handleDeleteRoom = () => {
    let ts = this
    let { deleteItem, deleteIndex } = ts.state
    console.info('%cdeleteItem', ts.color(), deleteItem)
    //
    let tokenObj = ts.validatedToken()
    ts.gets(
      ts.interfaces.deleteRoomByRoomId,
      {
        roomID: deleteItem.id,
      },
      { token: tokenObj.text }
    )
      .then((result) => {
        console.info('%c result', ts.color(), result)
        if (result.data.code === 200) {
          //从父组件的数据数组中删除此行
          let data = {
            comment: '从分页数据数组中删除此项',
            method: 'deleteRoomById',
            deleteIndex: deleteIndex,
          }
          this.props.receivedChildData(data)
        } else {
          console.infp(result.data.message)
          ts.setState({
            isExhibit: !ts.state.isExhibit,
            msg: result.data.message,
          })
        }
      })
      .catch((err) => {
        console.error('%c err', ts.color(), err)
      })
  }

  /**
   * 接收来自confirm组件返回的数据
   * @param {*} data
   * @returns
   */
  receiveConfirmData = (data) => {
    console.log('%c receiveConfirmData.data', this.color(), data)
    if (data.instruct === 1) {
      //删除机房
      this.handleDeleteRoom()
    } else {
      console.log('data.instruct==' + data.instruct)
    }
  }

  render() {
    return (
      <div className='main-table-list'>
        <div>
          <div className='hint_head'>
            <h3>查看全部的机房信息</h3>
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
                      <td>{row.roomNum}</td>
                      <td>{row.totalSets}</td>
                      <td>{row.availableStatus === 0 ? '关闭中' : '开放中'}</td>
                      <td>{row.adminNumOperated}</td>
                      <td>{commonUtil.dateTimeFormat(row.operatedTime)}</td>
                      <td>{row.location}</td>
                      <td>{row.actAvailableQuantity}</td>
                      <td>
                        <span className='sp_a'>
                          <li
                            onClick={this.showFormHideTable.bind(
                              this,
                              index,
                              row
                            )}
                          >
                            编辑
                          </li>
                        </span>
                      </td>
                      <td>
                        <span className='sp_a'>
                          <li
                            onClick={this.deleteRoomById.bind(this, index, row)}
                          >
                            删除
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
        <div className='ConfirmIndexDiv'>
          <ConfirmIndex
            whetherExhibit={this.state.confirmExhibit}
            msg={this.state.confirmMsg}
            receiveData={this.receiveConfirmData}
          ></ConfirmIndex>
        </div>
        <div className='Portals2Div'>
          <Portals2
            isExhibit={this.state.isExhibit}
            msg={this.state.msg}
          ></Portals2>
        </div>
      </div>
    )
  }
}
export default TableList
