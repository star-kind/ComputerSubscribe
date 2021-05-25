# 机房预约系统前端部

## 架构

见**PACKAGE**

---

###### 取消启动时自动打开浏览器

进入 `node_modules/react-scripts/scripts/start.js`
注释掉 `openBrowser(urls.localUrlForBrowser);`

---

###### npm run eject

每次安装依赖,会刷新项目,致 `*/config.js`等配置文件还原,
若欲使自定义配置持久不变,运行如下:
`npm run eject / yarn run eject`
一般想要自己配置 webpack 的时候会用到 `npm run eject`, 比如自己配置 less 文件在项目中使用 less

---

###### react-hot-loader

**作用:不用刷新页面即可更改 Component 的状态**

---

babel-loader 8.x 对应 babel-core 7.x
babel-loader 7.x 对应 babel-core 6.x

---

###### babelrc:

- 先执行 plugins 的配置项,后执行 presets 的配置项
- 配置项执行顺序区别
  - plugins 按照声明顺序执行
  - presets 按照声明逆序执行

---

###### jsx 简单理解: 遇到 <> 就解析为 html，遇到{}就解析为 js

---

**业务**

- 管理员之间不可互相修改

---

**技术**

- 跨组件(祖先=>(...后代))之间通信推荐使用 context,取代 props
- 子孙组件向祖先组件传值,暂时只能逐层向上递传
- react 中 input 标签使用属性 value 的话，一直改会一直循环触发 onChange 事件,应该用 defaultValue

---

**小结**

- 用户表格相关组件合计 4 个,可复用性/可读性较差,嵌套凌乱,属于难以再复制的手工制品,不建议后续借鉴
