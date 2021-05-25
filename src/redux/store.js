import { createStore, applyMiddleware } from 'redux'
import rootReducer from './redux-drill/reducers'
import thunk from 'redux-thunk'

/*
该文件用于暴露一个store对象,整个应用只能有一个store对象
根据指定的reducer函数,产生一个store对象
store对象内部管理数据的新状态,状态数据的初始值为reducer的返回值
*/
const store = createStore(rootReducer, applyMiddleware(thunk))
export default store
