//接口地址集合
var interfaces = {
  getMember: '/api/subscribe/UsersController/getMemberByOrderAction',

  retrieveProfile: '/api/subscribe/UsersController/retrieveProfileByMySelf',

  modifyByAdminMySelf:
    '/api/subscribe/UsersController/modifyByAdminMySelfAction',

  modifyInfoByUser: '/api/subscribe/UsersController/modifyInfoByUserAction',

  revisePassword: '/api/subscribe/UsersController/revisePasswordAction',

  login: '/api/subscribe/UsersController/loginAction',

  registry: '/api/subscribe/UsersController/registerAction',
}

export default interfaces
