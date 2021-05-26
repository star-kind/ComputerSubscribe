import React, { Component } from 'react'
import ReactDOM from 'react-dom'
import './index.css'
import App from './App'
// import reportWebVitals from './reportWebVitals';
import MineRouter from './router/MineRouter'
import { gets } from './api/axios-utils/httputil'
import interfaces from './api/axios-utils/interfaces'
import { store_key, user_urls } from './api/constant-list'
import { getColor, color } from './api/utils/log-color'
import { loginout } from './api/utils/loginout'

if (module.hot) {
  module.hot.accept('./router/MineRouter', () => {
    ReactDOM.render(MineRouter)
  })
}

ReactDOM.render(<App />, document.getElementById('root'))

// reportWebVitals(console.log);
// 在 Component 的原型链上,绑定方法或对象
Component.prototype.gets = gets
Component.prototype.interfaces = interfaces
Component.prototype.store_key = store_key
Component.prototype.user_urls = user_urls
Component.prototype.getColor = getColor
Component.prototype.color = color
Component.prototype.loginout = loginout
