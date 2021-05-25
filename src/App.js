// import logo from './logo.svg';
// import './App.css';
import React from 'react'

import { Provider } from 'react-redux'
import { AppContainer } from 'react-hot-loader'

import MineRouter from './router/MineRouter'
import store from './redux/store'

console.log(store)

/**
 *
 * @returns
 */
function App() {
  const content = (
    <div className='app'>
      <Provider store={store}>
        <AppContainer>
          <MineRouter />
        </AppContainer>
      </Provider>
    </div>
  )
  //
  return content
}

export default App
