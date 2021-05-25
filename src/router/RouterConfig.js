import { lazy } from 'react'
import { user_urls } from '@/api/constant-list'

//路由地址集合数组
const routesArr = [
  /*test*/
  {
    path: '/case5/ancestor',
    component: lazy(() => import('@/components/test/case5/ancestor/ancestor')),
  },
  {
    path: '/test4',
    component: lazy(() => import('@/components/test/case4/test4')),
  },
  {
    path: '/case3',
    component: lazy(() => import('@/components/test/case3/case3')),
  },
  {
    path: '/test2',
    component: lazy(() => import('@/components/test/case2/test2')),
  },
  {
    path: '/case1',
    component: lazy(() => import('@/components/test/case1/case1')),
  },
  {
    path: '/test',
    component: lazy(() => import('@/components/test/case/test')),
  },
  {
    path: '/testCollection',
    component: lazy(() =>
      import('@/components/test/collections/test-collection')
    ),
  },
  /*Home*/
  {
    path: '/',
    component: lazy(() => import('@/containers/home/home-index')),
  },
  /*user*/
  {
    path: user_urls.reg_url,
    component: lazy(() => import('@/containers/users-model/register/registry')),
  },
  {
    path: user_urls.login_url,
    component: lazy(() => import('@/containers/users-model/login/logining')),
  },
  {
    path: user_urls.revamp_passwd_url,
    component: lazy(() =>
      import('@/containers/users-model/revamp-pwd/revamp-pwd')
    ),
  },
  {
    path: user_urls.profile_ordinary,
    component: lazy(() =>
      import('@/containers/users-model/profile/user-ordinary/')
    ),
  },
  {
    path: user_urls.profile_administrator,
    component: lazy(() =>
      import('@/containers/users-model/profile/administrator/')
    ),
  },
  {
    path: user_urls.retrieve_list_url,
    component: lazy(() =>
      import('@/containers/users-model/user-list/retrieve/')
    ),
  },
  /*subscribe*/
  {
    path: user_urls.add_new_apply,
    component: lazy(() =>
      import('@/containers/subscribe-model/create/add-new-apply/add-new-apply')
    ),
  },
  {
    path: user_urls.query_by_room_major,
    component: lazy(() =>
      import(
        '@/containers/subscribe-model/retrieve/teacher/queries/major/major'
      )
    ),
  },
]

export default routesArr
