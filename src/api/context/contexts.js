import React from 'react'

//跨组件通信之共享context池
export const TopContext = React.createContext({
  init: 'Initialization Context',
})
