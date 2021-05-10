/**
 * redux最核心的管理对象：store
 * */

import { createStore } from 'redux';
import reducer from './reducer';

//根据指定的reducer函数，产生一个store对象
//store对象内部管理数据的新状态，状态数据的初始值为reducer的返回值
const store = createStore(reducer);
console.log(store);

export default store;