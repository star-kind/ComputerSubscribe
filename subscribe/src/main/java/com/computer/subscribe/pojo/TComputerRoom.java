package com.computer.subscribe.pojo;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.ToString;

@ToString
public class TComputerRoom {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column t_computer_room.id
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column t_computer_room.room_num
	 *
	 * @mbggenerated Sat Apr 17 17:02:03 CST 2021
	 */
	@NotNull(message = "机房编号不能为空")
	@Min(value = 0, message = "机房编号不得小于 0")
	private Integer roomNum;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column t_computer_room.total_sets
	 *
	 * @mbggenerated Sat Apr 17 17:02:03 CST 2021
	 */
	@NotNull(message = "机位总数不能为空")
	@Min(value = 0, message = "机位总数不得小于 0")
	@Max(value = 10000, message = "机位总数不得大于 10000*10000*10000")
	private Integer totalSets;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column t_computer_room.available_status
	 *
	 * @mbggenerated Sat Apr 17 17:02:03 CST 2021
	 */
	@Min(value = 0, message = "可用状态小于 0")
	@Max(value = 1, message = "可用状态不得大于 1")
	@NotNull(message = "可用状态不能为空")
	private Integer availableStatus;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column t_computer_room.admin_num_operated
	 *
	 * @mbggenerated Sat Apr 17 17:02:03 CST 2021
	 */
	@NotNull(message = "管理员工号不能为空")
	private Long adminNumOperated;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column t_computer_room.operated_time
	 *
	 * @mbggenerated Sat Apr 17 17:02:03 CST 2021
	 */
	private Date operatedTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column t_computer_room.location
	 *
	 * @mbggenerated Sat Apr 17 17:02:03 CST 2021
	 */
	@Size(min = 1, max = 120)
	@NotBlank(message = "地点位置不能为空")
	private String location;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column t_computer_room.act_available_quantity
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	@Min(value = 0, message = "实际可用电脑数量不得小于 0")
	@NotNull(message = "实际可用电脑数量不能为空")
	private Integer actAvailableQuantity;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column t_computer_room.id
	 *
	 * @return the value of t_computer_room.id
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column t_computer_room.id
	 *
	 * @param id the value for t_computer_room.id
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column t_computer_room.room_num
	 *
	 * @return the value of t_computer_room.room_num
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public Integer getRoomNum() {
		return roomNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column t_computer_room.room_num
	 *
	 * @param roomNum the value for t_computer_room.room_num
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column t_computer_room.total_sets
	 *
	 * @return the value of t_computer_room.total_sets
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public Integer getTotalSets() {
		return totalSets;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column t_computer_room.total_sets
	 *
	 * @param totalSets the value for t_computer_room.total_sets
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public void setTotalSets(Integer totalSets) {
		this.totalSets = totalSets;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column t_computer_room.available_status
	 *
	 * @return the value of t_computer_room.available_status
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public Integer getAvailableStatus() {
		return availableStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column t_computer_room.available_status
	 *
	 * @param availableStatus the value for t_computer_room.available_status
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public void setAvailableStatus(Integer availableStatus) {
		this.availableStatus = availableStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column t_computer_room.admin_num_operated
	 *
	 * @return the value of t_computer_room.admin_num_operated
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public Long getAdminNumOperated() {
		return adminNumOperated;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column t_computer_room.admin_num_operated
	 *
	 * @param adminNumOperated the value for t_computer_room.admin_num_operated
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public void setAdminNumOperated(Long adminNumOperated) {
		this.adminNumOperated = adminNumOperated;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column t_computer_room.operated_time
	 *
	 * @return the value of t_computer_room.operated_time
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public Date getOperatedTime() {
		return operatedTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column t_computer_room.operated_time
	 *
	 * @param operatedTime the value for t_computer_room.operated_time
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public void setOperatedTime(Date operatedTime) {
		this.operatedTime = operatedTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column t_computer_room.location
	 *
	 * @return the value of t_computer_room.location
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column t_computer_room.location
	 *
	 * @param location the value for t_computer_room.location
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public void setLocation(String location) {
		this.location = location == null ? null : location.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column t_computer_room.act_available_quantity
	 *
	 * @return the value of t_computer_room.act_available_quantity
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public Integer getActAvailableQuantity() {
		return actAvailableQuantity;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column t_computer_room.act_available_quantity
	 *
	 * @param actAvailableQuantity the value for
	 *                             t_computer_room.act_available_quantity
	 *
	 * @mbggenerated Sat Apr 17 17:50:00 CST 2021
	 */
	public void setActAvailableQuantity(Integer actAvailableQuantity) {
		this.actAvailableQuantity = actAvailableQuantity;
	}
}