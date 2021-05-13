import React, { Component } from 'react'
import PropTypes from 'prop-types'
import './index.less'

class Table extends Component {
  constructor(props) {
    super(props)
    console.info('Table--Constructor')
    this.state = {
      //全选标志
      entireChoosed: false,
      //
      userList: [],
      //单项复选框
      rowCheck: [],
    }
  }

  componentDidMount() {
    console.log('Table component did mount')
    console.log(this)
  }

  static propTypes = {
    pagination: PropTypes.object,
    thArray: PropTypes.array,
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
      //若全选,获取数组
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
    //效果
    this.setState({
      rowCheck: statusArr,
      entireChoosed: boxStatus,
      userList: entireUsers,
    })
  }

  aloneChecked = (event, row, i) => {
    console.log('event\n', event)
    // let { rowCheck } = this.state
    // let status = false
  }

  render() {
    return (
      <div className='table_area'>
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
                {/*  */}
                {this.props.thArray.map((value, i) => {
                  return (
                    <th key={i} className='th_ar'>
                      {value}
                    </th>
                  )
                })}
              </tr>
            </thead>
            <tbody ref={(rf) => (this.isolated = rf)}>
              {this.props.pagination.data.map((row, index) => {
                var tr = (
                  <tr key={row.id} className='trs'>
                    <td>
                      <span>{index + 1}</span>
                      <span>
                        <input
                          onChange={this.aloneChecked.bind(this, row, index)}
                          checked={this.state.rowCheck[index] || false}
                          type='checkbox'
                          value={row.id}
                        />
                      </span>
                    </td>
                    <td>{row.userName}</td>
                    <td>{row.userNum}</td>
                    <td>{row.role}</td>
                    <td>{row.mailbox}</td>
                    <td>{row.phone}</td>
                    <td>
                      <span className='sp_a'>
                        <a href={row.id}>编辑</a>
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

export default Table
