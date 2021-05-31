export const commonUtil = {
  /**
   * 将用户数据存入local storage,同时设置键名
   * @param {*} key
   * @param {*} value
   */
  depositLocalStorage: function (key, value) {
    console.log('key:' + key, 'value\n', value)
    //将value转化为json字符串
    let jsonStr = JSON.stringify(value)
    // console.log('jsonStr\n', jsonStr)
    localStorage.setItem(key, jsonStr)
  },

  /**
   * 据键名将数据对象从local storage中提取出来
   * @param {*} key
   * @returns
   */
  getValueFromLocal: function (key) {
    let valueObj = { code: 1 }
    let jsonStr = localStorage.getItem(key)
    // console.log('jsonStr\n', jsonStr)
    if (jsonStr.trim().length < 1) {
      valueObj.text = '您的登录状态已过期,请重新登录'
      valueObj.code = -1
    } else {
      //将json字符串解析为数据对象
      valueObj.text = JSON.parse(jsonStr)
      // console.log('valueObj\n', valueObj)
    }
    return valueObj
  },

  /**
   * 正则校对注册资料
   * @param {*} data
   * @returns
   */
  verifyDataRegex: function (data) {
    let result = {
      isValidate: true,
      alertText: '',
    }

    if (data.password !== data.previousPassword) {
      result.isValidate = false
      result.alertText = '两次输入的密码不一致'
    } else if (data.password.trim().toString().length < 3) {
      result.isValidate = false
      result.alertText = '密码长度不得小于3'
    } else if (data.role < 0) {
      result.isValidate = false
      result.alertText = '请选择帐号类型'
    }

    // 验证手机号码
    // 验证规则：11位数字，以1开头
    let rulePhone = /^1\d{10}$/
    if (!rulePhone.test(data.phone)) {
      result.isValidate = false
      result.alertText = '电话号码不合规定'
    }
    //验证邮箱
    let ruleEmail = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/
    if (!ruleEmail.test(data.mailbox)) {
      result.isValidate = false
      result.alertText = '邮箱地址不合规定'
    }
    //验证工号/学号是否为纯数字
    let ruleUserNum = /^\d+$/
    if (!ruleUserNum.test(data.userNum)) {
      result.isValidate = false
      result.alertText = '工号或学号不合规定'
    }
    return result
  },

  /**
   * 将时间戳毫秒转化为本地格式时间,包括年月日时分秒
   * @param {*} time
   * @returns
   */
  dateTimeFormat: function (timeStamp) {
    let time = new Date(timeStamp)
    let standardTime = time.toLocaleString()
    console.log('standardTime=' + standardTime)
    return standardTime
  },

  /**
   * 将时间戳毫秒转化为年月日
   * @param {*} time
   * @returns
   */
  getTimeYearMonthDay: function (timeStamp) {
    let time = new Date(timeStamp)
    let timeYearMonthDay = time.getFullYear()
    timeYearMonthDay += '-'
    timeYearMonthDay += time.getMonth() + 1
    timeYearMonthDay += '-'
    timeYearMonthDay += time.getDate()
    console.log('timeYearMonthDay=' + timeYearMonthDay)
    return timeYearMonthDay
  },

  /**
   * 检验数据对象数组中哪一项为空
   * @param  {[type]} arr [description]
   * @return {[type]}     [只返回提示字符串,不返回处理过的数据]
   */
  verifyArrObj: function (arr) {
    let res = { hint: '', isVerify: true }
    console.info('verifyArrObj.arr', arr)
    //
    arr.map(function (item) {
      if (typeof item.val === 'string') {
        if (
          (item.val.trim() === '') |
          (item.val === null) |
          (item.val === undefined)
        ) {
          res.hint += item.name + ','
        }
      } else {
        if ((item.val === null) | (item.val === undefined)) {
          res.hint += item.name + ','
        }
      }
      return null
    })
    //
    if (res.hint.toString().length > 0) {
      res.isVerify = false
      res.hint = res.hint.substring(0, res.hint.length - 1) + ' 为空,请补全完毕'
    }
    //
    console.info('verifyArrObj.res', res)
    return res
  },
}
