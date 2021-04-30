package com.computer.subscribe.pojo.vo;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.ToString;

/**
 * value object of Subscribe and User
 * 
 * @author user
 *
 */
@Data
@ToString
public class SubscribeUserVO {
	/**
	 * 预约申单ID
	 */
	private Long sid;

	@NotNull(message = "申请者不能为空")
	private Long applicant;

	/**
	 * 审批者,一般是教师
	 */
	private Long reviewer;

	@Range(min = 0, max = 4)
	private Integer subscribeStatus;

	@Min(value = 0, message = "机房编号不得小于 0")
	@Max(value = 1000, message = "机房编号不得大于 1000")
	private Integer roomNum;

	private Date applicationStartTime;

	@Min(value = 0, message = "使用时段不得早于上午")
	@Max(value = 2, message = "使用时段不得晚于夜晚")
	private Integer useInterval;

	/**
	 * 操作更改之时间
	 */
	private Date handleTime;

	@Future(message = "申请使用机房的具体日期必须比当前晚")
	private Date applyUseDate;

	/**
	 * 用户ID
	 */
	private Integer uid;

	@Min(value = 1, message = "学号或工号不能小于1")
	@Max(value = Long.MAX_VALUE, message = "学号或工号不能大于" + Long.MAX_VALUE)
	private Long userNum;

	@Size(max = 19)
	private String userName;

	@Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "电话格式有误")
	private String phone;

	@Email(message = "邮箱格式不对")
	@Size(min = 7, max = 80)
	private String mailbox;

	@Max(value = 2)
	private Integer role;
}
