import React, { Component } from 'react'
import PropTypes from 'prop-types';
import './counters.less';

//UI组件,主要负责页面显示
/*
功能: 点击“加号”按钮, 数字加上下拉框中所选中的数字大小; 点击“减号”按钮, 数字减去下拉框中所选中的数字大小; 点击“increment if odd”按钮, 只有当数字是奇数的时候才做加运算; 点击“increment async”按钮的时候异步做加运算
*/
class Counter extends Component {
  constructor(props) {
    super(props);
    this.numberRef = React.createRef();
  }

  componentDidMount () {
    console.log('Counter component did mount')
    console.log(this);
  }

  static propTypes = {
    count: PropTypes.number.isRequired,
    increment: PropTypes.func.isRequired,
    decrement: PropTypes.func.isRequired,
  };

  //increment:增量
  increment = () => {
    const number = this.numberRef.current.value * 1;
    this.props.increment(number);
  };

  //decrement:递减
  decrement = () => {
    const number = this.numberRef.current.value * 1;
    this.props.decrement(number);
  };

  incrementIfOdd = () => {
    const count = this.props.count;
    const number = this.numberRef.current.value * 1;
    if (count % 2 === 1) {
      this.props.increment(number);
    }
  };

  incrementAsync = () => {
    const number = this.numberRef.current.value * 1;
    setTimeout(() => {
      this.props.increment(number);
    }, 1000);

  };

  render () {
    const count = this.props.count;
    console.log("render--" + count);
    // 
    var content = <div className="mine_counter_div">
      <div className="time_p_div" id="time_p_id">
        <p>click {count} time</p>
      </div>
      <div className="select_div tag_div">
        <select ref={this.numberRef} className="mine_select">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
        </select>
      </div>
      <div className="buttons_div tag_div">
        <button onClick={this.increment} className="mine_btn_item">+</button>

        <button onClick={this.decrement} className="mine_btn_item">-</button>

        <button onClick={this.incrementIfOdd} className="mine_btn_item">increment if odd</button>

        <button onClick={this.incrementAsync} className="mine_btn_item">increment async</button>
      </div>
    </div>;
    // 
    return (content);
  }
}

export default Counter;