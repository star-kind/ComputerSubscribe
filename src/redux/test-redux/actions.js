/**
 * n个用于创建action对象的工厂函数
 * */

import { INCREMENT, DECREMENT } from './constants';

export const increment = (number) => ({ type: INCREMENT, number });
export const decrement = (number) => ({ type: DECREMENT, number });