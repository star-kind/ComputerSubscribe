import React from 'react';
import ReactDOM from 'react-dom';

import './index.css';
import App from './App';
// import reportWebVitals from './reportWebVitals';
import IRouter from '@/router';

if (module.hot) {
  module.hot.accept('./router', () => {
    console.log(this);
    ReactDOM.render(IRouter)
  });
}

ReactDOM.render(
  <App />,
  document.getElementById('root')
);

// reportWebVitals(console.log);