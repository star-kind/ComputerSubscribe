import React, { Component, Suspense } from 'react'
import { Route, BrowserRouter, HashRouter, Switch } from 'react-router-dom'
import './MineRouter.less'
import routesArr from './RouterConfig'
import { getValueFromLocal } from '@/api/common'
import { user_urls } from '@/api/constant-list'

//路由导航守卫
class MineRouter extends Component {
  constructor(props) {
    super(props)
    // console.info('%cMineRouter--Constructor\n', 'color:brown', this)
    console.info('MineRouter--Constructor')
  }

  componentDidMount() {
    // console.log('%cMineRouter component did mount\n', 'color:red', this)
    console.log('MineRouter component did mount')
  }

  componentWillUnmount() {
    console.log('MineRouter component Will Unmount')
  }

  state = {
    maxDuration: 1000,
  }

  //路由守卫拦截
  guardian = () => {
    console.info('%c this.props', this.getColor(), this.props)
    //
    const {
      history: { replace },
      location,
    } = this.props
    //
    let storeObj = getValueFromLocal(this.store_key.myself_key)
    console.log('storeObj\n', storeObj)
    //
    if (storeObj.code === -1 && location.pathname !== user_urls.reg_url) {
      console.log('您尚未登录或注册')
      replace(user_urls.login_url)
    } else {
      console.log('业已登录,批准放行')
    }
  }

  render() {
    const loading = (
      <div className='loading_div'>
        <h1>Loading...</h1>
      </div>
    )
    //
    return (
      <div className='main-MineRouter'>
        <div className='mine_routers_map'>
          <BrowserRouter>
            <HashRouter>
              <Suspense fallback={loading} maxDuration={this.state.maxDuration}>
                <Switch>
                  {routesArr.map((route, index) => {
                    return (
                      <Route
                        exact
                        key={index}
                        path={route.path}
                        component={route.component}
                      />
                    )
                  })}
                </Switch>
              </Suspense>
            </HashRouter>
          </BrowserRouter>
        </div>
      </div>
    )
  }
}

export default MineRouter
