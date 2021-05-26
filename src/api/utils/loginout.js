export function loginout() {
  console.log('login out==>退出登录')
  localStorage.clear()
  window.location.href = '/'
}
