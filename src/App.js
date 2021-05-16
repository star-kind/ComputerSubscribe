// import logo from './logo.svg';
// import './App.css';
import React from 'react'

import { Provider } from 'react-redux'
import { AppContainer } from 'react-hot-loader'

import IRouter from '@/router'
import store from '@/redux/test-redux/store'

console.log(store)

/**
 *
 * @returns
 */
function App() {
  var content = (
    <div className='app'>
      <Provider store={store}>
        <AppContainer>
          <IRouter />
        </AppContainer>
      </Provider>
    </div>
  )
  //
  return content
}

export default App
