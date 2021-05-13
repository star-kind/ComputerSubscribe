import axios from 'axios'

/**
 * 封装 get 请求[形参含header]
 * @param {*} url
 * @param {*} param
 * @param {*} header
 * @returns
 */
export function gets(url, param, header) {
  return new Promise((resolve, reject) => {
    axios
      .get(url, { params: param, headers: header })
      .then(
        (response) => {
          console.info('response\n', response)
          resolve(response)
        },
        (err) => {
          reject(err)
        }
      )
      .catch((error) => {
        reject(error)
      })
  })
}
