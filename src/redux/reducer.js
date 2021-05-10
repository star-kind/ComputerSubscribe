/**
 * 管理状态数据的函数
 * 根据旧的state，删除新的state
 * 必须是纯函数
 * */
import { INCREMENT, DECREMENT } from './constants';

export default function count (state = 1, action) {
  console.info(action);
  console.info('state:', state);
  switch (action.type) {
    case INCREMENT:
      return state + action.number;
    case DECREMENT:
      return state - action.number;
    default:
      return state;
  }
}