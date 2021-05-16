import React, { Component } from 'react'
import PropTypes from 'prop-types'
import './table-index.less'
import EditUserRow from '@/components/tables/users/edit-user-row/edit-user-row'

class UserTable extends Component {
  constructor(props) {
    super(props)
    console.info('UserTable--Constructor')
  }

  componentDidMount() {
    console.log('Table Component Did Mount')
    console.log(this)
  }

  UNSAFE_componentWillReceiveProps(nextProps) {
    console.log(
      '%cUserTable Component Will Receive Props.nextProps',
      'color:brown'
    )
    console.info(nextProps)
    this.setState({
      pagination: nextProps.pagination,
    })
  }

  state = {
    //是否展示
    isExhibit: true,
    message: '',
    //全选标志
    entireChoosed: false,
    //全选数据集合
    dataCollects: [],
    //单项复选标志
    rowCheck: [],
    //单次点击复选框选中的表格行数据
    rowCheckItems: [],
    //展示或隐藏表单
    checkoutExhibitFrom: 'none',
    //被编辑的目标
    editTargetRow: {},
    //
    cssTblClass: {
      width: '80%',
      position: 'relative',
      left: '9%',
    },
  }

  static propTypes = {
    pagination: PropTypes.object,
    thArray: PropTypes.array,
    hidePageArea: PropTypes.func,
    showPageDiv: PropTypes.func,
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

  //展示表单,并隐匿表格
  showForm = (item, e) => {
    console.log('showForm--item\n', item)
    console.log('showForm--event\n', e)
    //
    this.setState({
      checkoutExhibitFrom: 'block',
      editTargetRow: item,
    })
    //调用(上层组件)函数
    this.props.hidePageArea('none')
  }

  //收到子组件指令,显示表格,隐匿表单
  showTbl = (instrtuction) => {
    console.log('showTbl.instrtuction=' + instrtuction)
    this.setState({
      checkoutExhibitFrom: instrtuction,
    })
  }

  //接收子组件指令,充当中继,继续向上层传值
  showInfoArea = (instrtuction) => {
    console.log('showInfoArea.instrtuction=' + instrtuction)
    this.props.showPageDiv(instrtuction)
  }

  //管理员不可被点击编辑
  checkPointerEvent = (value) => {
    console.log('checkPointerEvent--value==', value)
    let pointer = ''
    if (value === 0) {
      pointer = 'none'
    } else {
      pointer = 'inherit'
    }
    return pointer
  }

  getTextByRole = (role) => {
    var txt = ''
    switch (role) {
      case 0:
        txt = '管理员'
        break

      case 1:
        txt = '教师'
        break

      case 2:
        txt = '学生'
        break

      default:
        txt = 'undefined'
        break
    }
    return txt
  }

  //初始化循环动态生成的复选框选择状态值
  initialCheckbox = (value) => {
    let status = false
    if (value === null || value === undefined || value === false) {
      console.log('value==', value)
    } else {
      status = true
    }
    return status
  }

  render() {
    return (
      <div className='table_area'>
        <EditUserRow
          sendShowVal={this.showInfoArea}
          showTable={this.showTbl}
          showWrapper={this.state.checkoutExhibitFrom}
          row={this.state.editTargetRow}
          style={{ display: this.state.checkoutExhibitFrom }}
        ></EditUserRow>
        {/*  */}
        <div
          className='data_map_tbl'
          style={{
            display:
              this.state.checkoutExhibitFrom === 'none' ? 'block' : 'none',
          }}
        >
          <table className='tbl_class' style={this.state.cssTblClass}>
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
            <tbody ref={(f) => (this.isolated = f)}>
              {console.log(
                '%cThis.props.pagination\n',
                'color:teal',
                this.props.pagination
              )}
              {this.props.pagination.data.map((row, index) => {
                var tr = (
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
                    <td>{row.userName}</td>
                    <td>{row.userNum}</td>
                    <td>{this.getTextByRole(row.role)}</td>
                    <td>{row.mailbox}</td>
                    <td>{row.phone}</td>
                    <td>
                      <span className='sp_a'>
                        <li
                          style={{
                            pointerEvents: this.checkPointerEvent(row.role),
                          }}
                          onClick={this.showForm.bind(this, row)}
                        >
                          {row.role === 0 ? '不可编辑' : '编辑'}
                        </li>
                      </span>
                    </td>
                  </tr>
                )
                //
                return tr
              })}
            </tbody>
          </table>
        </div>
      </div>
    )
  }
}

export default UserTable
