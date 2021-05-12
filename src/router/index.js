import React, { lazy, Component, Suspense } from 'react'
import {
  Route,
  BrowserRouter,
  HashRouter,
  Switch,
  Redirect,
} from 'react-router-dom'

import { login_url, reg_url, profile_u_ordinary } from '@/api/constant-list'

// 路由表
// 首页
const Home = lazy(() => import('@/containers/home/home-index'))
const TestingCase = lazy(() =>
  import('@/components/testing/case1/testing-case')
)
const Test2 = lazy(() => import('@/components/testing/case2/test2'))
const Registry = lazy(() => import('@/containers/register/registry'))
const Logining = lazy(() => import('@/containers/login/logining'))
const RevampPwd = lazy(() => import('@/containers/revamp-pwd/revamp-pwd'))
const ProfileUserOrdinary = lazy(() =>
  import('@/containers/profile/user-ordinary/')
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
    console.log('IRouter component did mount')
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
      <div className='loading_div'>
        <h3>Loading...</h3>
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
                <Route exact path='/testingCase' component={TestingCase} />
                <Route exact path='/test2' component={Test2} />
                <Route exact path={reg_url} component={Registry} />
                <Route exact path={login_url} component={Logining} />
                <Route exact path='/revampPwd' component={RevampPwd} />
                <Route
                  exact
                  path={profile_u_ordinary}
                  component={ProfileUserOrdinary}
                />
                {/*  */}
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
