import { CONVEY_LAYER_ID, DELIVERY_DATA } from './actionTypes'

/*Action: View 发出的通知，表示 State 应该要发生变化了*/

/**
 *
 * @param {*} data
 * @returns
 */
export const deliveryData = (data) => {
  console.log('actions.deliveryData', data)
  return {
    type: DELIVERY_DATA, //type可使用事件名称存储器中的,也可以不使用,而直接匹配reducer中的type
    data,
  }
}

/**
 * @param {*} id
 * @returns
 */
export function conveyLayerID(id) {
  console.log('actions.conveyLayerID', id)
  return {
    type: CONVEY_LAYER_ID,
    id,
  }
}
