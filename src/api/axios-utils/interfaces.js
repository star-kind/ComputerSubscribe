//接口地址集合
let interfaces = {
  getMember: '/api/subscribe/UsersController/getMemberByOrderAction',

  retrieveProfile: '/api/subscribe/UsersController/retrieveProfileByMySelf',

  modifyByAdminMySelf:
    '/api/subscribe/UsersController/modifyByAdminMySelfAction',

  modifyInfoByUser: '/api/subscribe/UsersController/modifyInfoByUserAction',

  modifyUser: '/api/subscribe/UsersController/modifyUserAction',

  revisePassword: '/api/subscribe/UsersController/revisePasswordAction',

  login: '/api/subscribe/UsersController/loginAction',

  registry: '/api/subscribe/UsersController/registerAction',

  retrieveRoomNumArr:
    '/api/subscribe/ComputerRoomController/retrieveRoomNumArr',

  addNewApply: '/api/subscribe/SubscribeController/addNewApplyAction',

  retrieveAllSubscirbeOnWeek:
    '/api/subscribe/SubscribeController/retrieveAllSubscirbeOnWeek',
}

export default interfaces
