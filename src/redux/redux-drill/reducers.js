/*
 * 管理状态数据的函数
 * 根据旧的state，删除新的state
 * 必须是纯函数
 */
import { combineReducers } from 'redux'
import {
  GET_TBL_HEAD_ARR,
  CONVEY_LAYER_ID,
  DELIVERY_DATA,
  JOINT_QUERY_TBL,
  GET_ROOM_LIST,
  CONVEY_INDEX_ITEM,
  CHECKOUT_TBL_FORM_DISPLAY,
  CONVEY_PAGE_ROOM_PARAM,
} from './actionTypes'

let defaultDeliverData = {
  defaultData: {
    realName: 'default_real_name',
    middleName: 'default_middle_name',
    nickName: 'default_nick_name',
  },
  pagination: {
    data: [],
    hasPrevious: false,
    hasNext: false,
    totalPages: 0,
    currentPage: 1,
    rows: 0,
  },
  tblHeadArr: [],
  roomList: [],
  indexItem: {
    item: {},
    index: '',
  },
  displays: {
    formDisPlay: 'none',
    tblAndPageDisPlay: 'inherit',
  },
  pageRoom: { page: '', limit: '', roomNum: '' },
}

/**
 *
 * @param {*} state
 * @param {*} actions
 * @returns
 */
const deliverDataReducer = (state = defaultDeliverData, actions = {}) => {
  switch (actions.type) {
    case CONVEY_PAGE_ROOM_PARAM:
      return { ...state, pageRoom: actions.pageRoom }

    case CHECKOUT_TBL_FORM_DISPLAY:
      return { ...state, displays: actions.displays }

    case DELIVERY_DATA:
      return {
        ...state,
        defaultData: actions.data,
      }

    case JOINT_QUERY_TBL:
      return {
        ...state,
        pagination: actions.pagination,
      }

    case GET_TBL_HEAD_ARR:
      return {
        ...state,
        tblHeadArr: actions.tblHeadArr,
      }

    case GET_ROOM_LIST:
      return {
        ...state,
        roomList: actions.roomList,
      }

    case CONVEY_INDEX_ITEM:
      return {
        ...state,
        indexItem: actions.indexItem,
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
