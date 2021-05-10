# 机房预约系统前端部

## 架构
- react-dom

###### 取消启动时自动打开浏览器
进入 `node_modules/react-scripts/scripts/start.js`
注释掉 `openBrowser(urls.localUrlForBrowser);`

-------------------

###### npm run eject

每次安装依赖,会刷新项目,致 `*/config.js`等配置文件还原,
若欲使自定义配置持久不变,运行如下:
`npm run eject / yarn run eject`
一般想要自己配置webpack的时候会用到 `npm run eject`, 比如自己配置less文件在项目中使用less

-------------------

###### react-hot-loader
**作用:不用刷新页面即可更改Component的状态**

-------------------

  babel-loader 8.x对应babel-core 7.x
  babel-loader 7.x对应babel-core 6.x

-------------------

###### babelrc:
+ 先执行 plugins 的配置项,后执行 presets 的配置项 
+ 配置项执行顺序区别
  - plugins 按照声明顺序执行
  - presets 按照声明逆序执行

-------------------

###### jsx简单理解: 遇到 <> 就解析为html，遇到{}就解析为js

-------------------

-------------------