package com.computer.subscribe.mapper;

import com.computer.subscribe.pojo.TComputerRoom;
import com.computer.subscribe.pojo.TComputerRoomExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TComputerRoomMapper {
	/**
	 * 获取所有{id,roomNum}的 Map List
	 * 
	 * @return
	 */
	List<Map<Integer, Integer>> selectIdAndRoomNumMap();

	/**
	 * 获取全部的机房编号,返回integer数组
	 * 
	 * @return
	 */
	Integer[] getRoomNumCollection();

	/**
	 * 计算id的总数
	 * 
	 * @return
	 */
	Integer selectCountAllComputerRoomByIDs();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_computer_room
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	int countByExample(TComputerRoomExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_computer_room
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	int deleteByExample(TComputerRoomExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_computer_room
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_computer_room
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	int insert(TComputerRoom record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_computer_room
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	int insertSelective(TComputerRoom record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_computer_room
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	List<TComputerRoom> selectByExample(TComputerRoomExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_computer_room
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	TComputerRoom selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_computer_room
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	int updateByExampleSelective(@Param("record") TComputerRoom record,
			@Param("example") TComputerRoomExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_computer_room
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	int updateByExample(@Param("record") TComputerRoom record,
			@Param("example") TComputerRoomExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_computer_room
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	int updateByPrimaryKeySelective(TComputerRoom record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table t_computer_room
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	int updateByPrimaryKey(TComputerRoom record);
}