package com.computer.subscribe.util.support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间处理相关工具类
 * 
 * @author user
 *
 */
public class DateTimeKits {
	private static DateTimeKits dtks;

	private static final Object LOCK = new Object();

	private DateTimeKits() {
		System.err.println(this.getClass() + "___DateTimeKits==>私有化构造器,防止被实例化");
	}

	/**
	 * 懒汉式之单例模式
	 * 
	 * @return
	 */
	public static DateTimeKits getInstance() {
		if (dtks == null) {
			synchronized (LOCK) {// 决定是否锁住
				if (dtks == null) {
					dtks = new DateTimeKits();
				}
			}
		}
		return dtks;
	}

	/**
	 * 获取当前日期的下周 1 到下周 5 的所有日期集合
	 * 
	 * @return
	 */
	public ArrayList<Date> getDateWeekList() {
		ArrayList<Date> dateList = new ArrayList<Date>();
		ArrayList<Calendar> calList = new ArrayList<Calendar>();

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal.setTime(date);
		cal2.setTime(date);

		// 获得当前日期是一个星期的第几天
		int dayNo = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println(this.getClass() + "__getDateWeekList.(第一天是星期天)今天是一个星期的第"
				+ dayNo + "天,即星期 " + (dayNo - 1));

		if (dayNo == 1) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
			cal2.add(Calendar.DAY_OF_MONTH, 7);
		} else {
			cal.add(Calendar.DAY_OF_MONTH, 1 - dayNo + 8);
			cal2.add(Calendar.DAY_OF_MONTH, 1 - dayNo + 14);
		}

		// 把起始日期加上
		dateList.add(cal.getTime());// 星期 1

		// 后续日期
		Calendar startCalendar = Calendar.getInstance();// 星期 2
		Calendar startCalendar2 = Calendar.getInstance();// 星期 3
		Calendar startCalendar3 = Calendar.getInstance();// 星期 4
		Calendar startCalendar4 = Calendar.getInstance();// 星期五

		// 校验此日期是否在指定日期之后
		boolean b = cal2.getTime().after(startCalendar.getTime());
		if (!b) {
			System.out.println(
					this.getClass() + "__getDateWeekList.晚于指定日期...b== " + b);
		}

		calList.add(startCalendar);
		calList.add(startCalendar2);
		calList.add(startCalendar3);
		calList.add(startCalendar4);

		int i = 1;
		for (Calendar c : calList) {
			// 设置起始日期
			c.setTime(cal.getTime());

			// 根据日历的规则，为给定的日历字段添加(正数)或减去(负数)指定的时间量
			c.add(Calendar.DAY_OF_MONTH, i++);
			dateList.add(c.getTime());
		}

		int j = 1;
		for (Date date2 : dateList) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String string = format.format(date2);
			System.out.println(this.getClass() + "__getDateWeekList.今天这周的下个星期的星期 "
					+ j++ + "== " + string.toString());
		}
		return dateList;
	}

	/**
	 * 判断日期是否属于下一周的一到五,返真那就是属于,返假则否
	 * 
	 * @param list
	 * @param dateString
	 * @return
	 */
	public Boolean judgeIsBelong(ArrayList<Date> list, String dateString) {
		Boolean flag = null;

		for (int i = 0, j = 1; i < list.size(); i++, j++) {

			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

			String string = format1.format(list.get(i));
			System.err.println(
					this.getClass() + "__judgeIsBelong.集合中的日期___string==" + string);

			if (dateString.equals(string)) {
				System.out.println(
						this.getClass() + "__judgeIsBelong.这个日期属于下一周的周 " + j);
				flag = true;
				break;

			} else {
				flag = false;
				System.out.println(
						this.getClass() + "__judgeIsBelong.这个日期 不不不 属于下一周的周 " + j);

			}
		}

		System.out.println(this.getClass() + "__judgeIsBelong.flag==" + flag);
		return flag;
	}

	/**
	 * 判断步骤的中枢方法,返真那就是属于周一到周五,返假则否
	 * 
	 * @param dateString
	 * @return
	 */
	public Boolean judgeCentral(Date date) {
		System.err.println(this.getClass() + "__judgeCentral.date==" + date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String dateString = format.format(date);
		System.err.println(
				this.getClass() + "__judgeCentral.dateString==" + dateString);

		ArrayList<Date> list = getDateWeekList();
		Boolean isBelong = judgeIsBelong(list, dateString);

		System.err.println(this.getClass() + "__judgeCentral.isBelong==" + isBelong);
		return isBelong;
	}

	/**
	 * 判断日期是否是周末,是周末返真,不是周末返假
	 * 
	 * @param dateDate
	 * @return
	 */
	public Boolean judgeIsWeekend(Date dateDate) {
		Boolean isWeekend = null;
		// 日历对象
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateDate);

		int weekend = calendar.get(Calendar.DAY_OF_WEEK);

		// 开始判断
		if (weekend == Calendar.SATURDAY || weekend == Calendar.SUNDAY) {
			System.err.println(this.getClass() + "__judgeIsWeekend__IS a weekend");
			isWeekend = true;
		} else {
			System.err
					.println(this.getClass() + "__judgeIsWeekend__is NOT a weekend");
			isWeekend = false;
		}

		System.err.println(this.getClass() + "__judgeIsWeekend__return_isWeekend= "
				+ isWeekend);
		return isWeekend;
	}

	/**
	 * 一周七天坐标的日历数组
	 * 
	 * @param value
	 * @return
	 */
	public int[] getCalendarNumArray(int value) {
		int calendarNumArray[] = new int[2];

		switch (value) {
		case 1:
			calendarNumArray[0] = 0;
			calendarNumArray[1] = 6;
			break;

		case 2:
			calendarNumArray[0] = -1;
			calendarNumArray[1] = 5;
			break;

		case 3:
			calendarNumArray[0] = -2;
			calendarNumArray[1] = 4;
			break;

		case 4:
			calendarNumArray[0] = -3;
			calendarNumArray[1] = 3;
			break;

		case 5:
			calendarNumArray[0] = -4;
			calendarNumArray[1] = 2;
			break;

		case 6:
			calendarNumArray[0] = -5;
			calendarNumArray[1] = 1;
			break;

		case 7:
			calendarNumArray[0] = -6;
			calendarNumArray[1] = 0;
			break;
		}
		return calendarNumArray;
	}

	/**
	 * 获取本周的周一和星期天的两天日期的集合
	 * 
	 * @return
	 */
	public ArrayList<Date> getMonAndSunList() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<Date> dateList = new ArrayList<Date>();

		Calendar cal = Calendar.getInstance();// 星期一变量
		Calendar cal2 = Calendar.getInstance();// 星期天变量

		LocalDateTime localDateTime = LocalDateTime.now();
		String dateString = localDateTime.toLocalDate().toString();
		System.err.println(
				this.getClass() + "__getMonAndSunList.今天年月日: " + dateString);

		int value = localDateTime.getDayOfWeek().getValue();
		System.err.println(this.getClass() + "__getMonAndSunList.本星期的第几天: " + value);

		int[] calendarNumArray = getCalendarNumArray(value);

		try {
			Date date = format.parse(dateString);
			cal.setTime(date);
			cal2.setTime(date);

			cal.add(Calendar.DAY_OF_WEEK, calendarNumArray[0]);
			cal2.add(Calendar.DAY_OF_WEEK, calendarNumArray[1]);

			dateList.add(cal.getTime());
			dateList.add(cal2.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}

		int size = dateList.size();
		String day1 = format.format(dateList.get(0));
		String day7 = format.format(dateList.get(size - 1));

		System.err.println(this.getClass() + "__getMonAndSunList.本周星期一是" + day1
				+ ",本周星期天是" + day7);
		return dateList;
	}
}
