const logFontColorArr = [
  'color:springgreen',
  'color:slateblue',
  'color:sienna',
  'color:steelblue',
  'color:tomato',
  'color:turquoise',
  'color:red',
  'color:green',
  'color:blue',
  'color:violet',
  'color:brown',
  'color:blueviolet',
  'color:cadetblue',
  'color:chocolate',
  'color:coral',
  'color:cornflowerblue',
  'color:crimson',
  'color:darkblue',
  'color:teal',
  'color:darkcyan',
  'color:darkglodenrod',
  'color:darkmagenta',
  'color:darkorange',
  'color:darkorchid',
  'color:chartreuse',
  'color:yellowgreen',
]

/**
 *
 * @returns
 */
export function getColor() {
  let length = logFontColorArr.length
  //可均衡获取 0 到 length 的随机整数
  let index = Math.floor(Math.random() * length)
  let colorVal = logFontColorArr[index]
  // console.log('%cConsoleColor', colorVal, colorVal)
  return colorVal
}

/**
 *
 * @returns
 */
export function color() {
  return getColor()
}
