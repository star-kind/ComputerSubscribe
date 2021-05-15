import React, { lazy, Component, Suspense } from 'react'
import {
  Route,
  BrowserRouter,
  HashRouter,
  Switch,
  Redirect,
} from 'react-router-dom'
import { user_urls } from '@/api/constant-list'

//路由表
//测试
const Test = lazy(() => import('@/components/test/case/test'))
const Test2 = lazy(() => import('@/components/test/case2/test2'))
const Test3 = lazy(() => import('@/components/test/case3/test3'))
//首页
const Home = lazy(() => import('@/containers/home/home-index'))
//用户模块
const Registry = lazy(() =>
  import('@/containers/users-model/register/registry')
)
const Logining = lazy(() => import('@/containers/users-model/login/logining'))
const RevampPwd = lazy(() =>
  import('@/containers/users-model/revamp-pwd/revamp-pwd')
)
const ProfileUserOrdinary = lazy(() =>
  import('@/containers/users-model/profile/user-ordinary/')
)
const ProfileAdministrator = lazy(() =>
  import('@/containers/users-model/profile/administrator/')
)
const RetrieveList = lazy(() =>
  import('@/containers/users-model/user-list/retrieve/')
)

/**
 * This class describes my router.
 *
 * @class      IRouter (name)
 */
export default class IRouter extends Component {
  constructor(props) {
    super(props)
    console.dir(this)
  }

  componentDidMount() {
    console.log('IRouter component did mount\n', user_urls)
  }

  /**
   * 这里用 redirect 进行首页自动跳转到 /home 路由下,
   * exact 意味着精确匹配,
   * 当为 / 时才跳转 /home,
   * 不是包含 / 就跳转到 /home
   *
   * Renders the object.
   *
   * @return     {<type>}  { description_of_the_return_value }
   */
  render() {
    var loadHint = (
      <div className='loading_div' style={{ textAlign: 'center' }}>
        <h2 style={{ marginTop: '2rem' }}>Loading...</h2>
      </div>
    )
    //
    var iRouter = (
      <div className='mine_routers_map'>
        <BrowserRouter>
          <HashRouter>
            <Suspense fallback={loadHint} maxDuration={1000}>
              <Switch>
                <Route exact path='/' component={Home} />
                <Route exact path='/test' component={Test} />
                <Route exact path='/test2' component={Test2} />
                <Route exact path='/test3' component={Test3} />

                <Route exact path={user_urls.reg_url} component={Registry} />

                <Route exact path={user_urls.login_url} component={Logining} />

                <Route
                  exact
                  path={user_urls.revamp_passwd_url}
                  component={RevampPwd}
                />

                <Route
                  exact
                  path={user_urls.profile_ordinary}
                  component={ProfileUserOrdinary}
                />

                <Route
                  exact
                  path={user_urls.retrieve_list_url}
                  component={RetrieveList}
                />

                <Route
                  exact
                  path={user_urls.profile_administrator}
                  component={ProfileAdministrator}
                />

                {/* 重定向 */}
                <Redirect exact from='/' to={Home} />
              </Switch>
            </Suspense>
          </HashRouter>
        </BrowserRouter>
      </div>
    )
    //
    return iRouter
  }
}
