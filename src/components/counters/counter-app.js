/**
 * 将UI组件包装成容器组件，这样在产生的新组件中就可以使用react-redux中的API
 * */
import { connect } from 'react-redux'
import { increment, decrement } from '@/redux/test-redux/actions'
import Counter from '@/components/counters/counters-index'

/**
 * 将特定的state数据映射成标签属性传递给UI组件Counter
 * redux在调用此函数时，传入了store.getState()的值
 * 在reducer中我们定义了一个状态名为count，这里的state就是传递的count
 * */
const mapStateToProps = (state) => ({
  count: state,
})

/**
 * 将包含dispatch函数调用语句的函数映射成函数属性传递给UI组件Counter
 * redux在调用此函数时，传入了store.dispatch
 * */
const mapDispatchToProps = (dispatch) => ({
  increment: (number) => {
    dispatch(increment(number))
  },
  decrement: (number) => {
    dispatch(decrement(number))
  },
})

//connect方法：将组件和数据(方法)进行连接
export default connect(mapStateToProps, mapDispatchToProps)(Counter)
