/*
 * 管理状态数据的函数
 * 根据旧的state，删除新的state
 * 必须是纯函数
 */
import { combineReducers } from 'redux'
import { CONVEY_LAYER_ID, DELIVERY_DATA } from './actionTypes'

let deliverDefaultData = {
  data: {
    realName: 'Default.realName',
    middleName: 'Default.middleName',
    nickName: 'Default.nickName',
  },
}
/**
 *
 * @param {*} state
 * @param {*} actions
 * @returns
 */
const deliverDataReducer = (state = deliverDefaultData, actions = {}) => {
  // console.log('Reducer.deliverDataReducer.state', state)
  // console.log('Reducer.deliverDataReducer.action', actions)
  //
  switch (actions.type) {
    case DELIVERY_DATA:
      return {
        ...state,
        data: actions.data,
      }

    default:
      return state
  }
}

/**
 *
 * @param {*} state
 * @param {*} action
 * @returns
 */
function conveyIDReducer(state = { id: 1 }, action) {
  // console.log('Reducer.conveyIDReducer.state', state)
  // console.log('Reducer.conveyIDReducer.action', action)
  //
  switch (action.type) {
    case CONVEY_LAYER_ID:
      return {
        ...state,
        id: action.id,
      }

    default:
      return state
  }
}

const rootReducer = combineReducers({
  conveyIDReducer,
  deliverDataReducer,
})

export default rootReducer
