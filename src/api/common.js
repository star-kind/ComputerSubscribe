/**
 * 将数据对象存入local storage,同时设置键名
 * @param {*} key
 * @param {*} value
 */
export function depositLocalStorage(key, value) {
  // 首先初始化清空localStorage
  localStorage.clear()
  //将value转化为json字符串
  var jsonStr = JSON.stringify(value)
  console.log('jsonStr\n', jsonStr)
  localStorage.setItem(key, jsonStr)
}

/**
 * 据键名将数据对象从local storage中提取出来
 * @param {*} key
 * @returns
 */
export function getValueFromLocal(key) {
  var valueObj = { code: 1 }
  var jsonStr = localStorage.getItem(key)
  console.log('jsonStr\n', jsonStr)

  if (jsonStr.trim().length < 1) {
    valueObj.text = '您的登录状态已过期,请重新登录'
    valueObj.code = -1
    return valueObj
  }

  //将json字符串解析为数据对象
  valueObj.text = JSON.parse(jsonStr)
  console.log('valueObj\n', valueObj)
  return valueObj
}

/**
 * 正则校对注册资料
 * @param {*} data
 * @returns
 */
export function verifyDataRegex(data) {
  var result = {
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
  var rulePhone = /^1\d{10}$/
  if (!rulePhone.test(data.phone)) {
    result.isValidate = false
    result.alertText = '电话号码不合规定'
  }
  //验证邮箱
  var ruleEmail = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/
  if (!ruleEmail.test(data.mailbox)) {
    result.isValidate = false
    result.alertText = '邮箱地址不合规定'
  }
  //验证工号/学号是否为纯数字
  var ruleUserNum = /^\d+$/
  if (!ruleUserNum.test(data.userNum)) {
    result.isValidate = false
    result.alertText = '工号或学号不合规定'
  }
  //
  return result
}

/**
 * 校验表单参数集合对象是否有空值
 * @param {*} data
 * @returns
 */
export function verifyDataNull(data) {
  var result = {
    isValidate: true,
    alertText: '',
  }

  var entriesArr = Object.entries(data)
  console.log('entriesArr\n', entriesArr)

  var hint = ''
  var objArr = []
  //校验各参数是否为空
  objArr = entriesArr.map((item) => {
    if (
      (item[1].trim() === '') |
      (item[1] === null) |
      (item[1] === undefined)
    ) {
      hint += item[0] + ','
      objArr.push(item[0])
    }
    return objArr
  })
  console.log('hint\n', hint)
  console.log('objArr\n', objArr)
  if (hint.toString().length > 0) {
    result.isValidate = false
    result.alertText = hint.substring(0, hint.length - 1) + '未输入,请填写完毕'
  }
  //
  return result
}
