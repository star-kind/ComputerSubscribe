/**
 * Action: View 发出的通知，表示 State 应该要发生变化了
 */
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

export const actionsCollect = {
  checkOutTblOrFormDisplay: (displays) => {
    return {
      type: CHECKOUT_TBL_FORM_DISPLAY,
      displays,
    }
  },
  conveyPageRoomParam: (pageRoom) => {
    return {
      type: CONVEY_PAGE_ROOM_PARAM,
      pageRoom,
    }
  },
}

/**
 *
 * @param {*} indexItem
 * @returns
 */
export const conveyIndexItem = (indexItem) => {
  console.log('Actions.ConveyIndexItem', indexItem)
  return {
    type: CONVEY_INDEX_ITEM,
    indexItem,
  }
}

/**
 *
 * @param {*} roomList
 * @returns
 */
export const getRoomList = (roomList) => {
  console.log('Actions.getRoomList', roomList)
  return {
    type: GET_ROOM_LIST,
    roomList,
  }
}

/**
 *
 * @param {*} tblHeadArr
 * @returns
 */
export const getTblHeadArr = (tblHeadArr) => {
  console.log('Actions.tblHeadArr', tblHeadArr)
  return {
    type: GET_TBL_HEAD_ARR,
    tblHeadArr,
  }
}

/**
 * 分页数据
 * @param {*} pagination
 * @returns
 */
export const jointQueryTbl = (pagination) => {
  console.log('actions.jointQueryTbl', pagination)
  return {
    type: JOINT_QUERY_TBL,
    pagination,
  }
}

/**
 *
 * @param {*} data
 * @returns
 */
export const deliveryData = (data) => {
  // console.log('actions.deliveryData', data)
  return {
    type: DELIVERY_DATA,
    data,
  }
}

/**
 * @param {*} id
 * @returns
 */
export function conveyLayerID(id) {
  // console.log('actions.conveyLayerID', id)
  // type可使用事件名称存储器中的,也可不用,而直接匹配reducer中的type
  return {
    type: CONVEY_LAYER_ID,
    id,
  }
}
