package com.computer.subscribe.util.support;

import java.lang.reflect.Field;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import com.computer.subscribe.exception.AttributeNPException;

/**
 * 校验实体对象中指定的空值字段
 * 
 * @author user
 *
 */
public class ValidFieldNPExUtil {
	public static Logger log = Logger.getLogger(ValidFieldNPExUtil.class);

	String t = this.getClass() + "___\n";

	private static ValidFieldNPExUtil validEx;

	private static final Object LOCK = new Object();

	private ValidFieldNPExUtil() {
		System.err.println(t + "__ValidFieldNPEx__私有化构造器,防止被实例化");
	}

	/**
	 * 懒汉式之单例模式
	 * 
	 * @return
	 */
	public static ValidFieldNPExUtil getInstance() {
		if (validEx == null) {
			synchronized (LOCK) {// 决定是否锁住
				if (validEx == null) {
					validEx = new ValidFieldNPExUtil();
				}
			}
		}
		return validEx;
	}

	/**
	 * 整编集中
	 * 
	 * @param <T>
	 * @param cls
	 * @param specFields
	 */
	public <T> void centralize(T cls, String... specFields) {
		Field[] fieldArray = getFieldArray(cls);
		LinkedList<String> nullValueAttrList = getNullAttributeList(cls, fieldArray);
		judgeSpecFieldNullValue(nullValueAttrList, specFields);
	}

	/**
	 * 显示指定字段是否为空的字符串信息
	 * 
	 * @param nullValueAttrList
	 * @param specifyField      指定的属性字段名,必须要契合正确的属性名称
	 */
	public void judgeSpecFieldNullValue(LinkedList<String> nullValueAttrList,
			String... specifyField) {
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		// 分隔符,防止因字段名部分重复,而引起非预想的异常
		String separate = ";";

		for (String string : nullValueAttrList) {
			if (!"".equals(string)) {
				builder.append(string + separate);
			}
		}

		String allNullResStr = builder.toString();// 所有空值字段的信息
		System.out.println(
				t + "judgeSpecFieldNullValue..allNullResStr==" + allNullResStr);
		System.out.println(t + "judgeSpecFieldNullValue..allNullResStr--length=="
				+ allNullResStr.length());

		for (int i = 0; i < specifyField.length; i++) {
			if (allNullResStr.contains(specifyField[i] + separate)) {
				System.out.println(
						t + "judgeSpecFieldNullValue()show specify null field="
								+ specifyField[i]);
				builder2.append(specifyField[i] + "的值为空" + separate);
			}
		}

		String specResStr = builder2.toString();// 指定为空字段的信息
		System.err
				.println(t + "judgeSpecFieldNullValue---specResStr==" + specResStr);
		System.err.println(t + "judgeSpecFieldNullValue---specResStr..length=="
				+ specResStr.length());

		if (!"".equals(specResStr)) {// 抛异常
			throw new AttributeNPException(specResStr);
		}
	}

	/**
	 * 获取实体中属性为空值的属性名称集合
	 * 
	 * @param <T>
	 * @param cls
	 * @param filedArray
	 * @return
	 */
	public <T> LinkedList<String> getNullAttributeList(T cls, Field[] filedArray) {
		LinkedList<String> attrNamelist = new LinkedList<String>();

		for (Field field : filedArray) {
			// 设置可以访问私有属性
			field.setAccessible(true);

			try {
				// 获取当前属性名称
				String attributeName = field.getName();
				System.out.println(t + "getNullAttributeList---field..attributeName="
						+ attributeName);

				// 获取当前字段的值
				Object fieldValue = field.get(cls);
				System.err.println(
						t + "getNullAttributeList---field..Value=" + fieldValue);

				if (fieldValue == null) {// 若当前字段值为空,则添加当前字段名称入集合
					attrNamelist.add(attributeName);
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}

		for (String attrName : attrNamelist) {
			System.err.println(t + "getNullAttributeList---attrName==" + attrName);
		}
		return attrNamelist;
	}

	/**
	 * 获取实体内的属性数组
	 * 
	 * @param <T>
	 * @param cls
	 * @return
	 */
	public <T> Field[] getFieldArray(T cls) {
		// 获取class对象
		Class<?> clzObj = cls.getClass();
		// 获取当前对象所有属性,使用带declared的方法可以访问private属性
		Field[] fields = clzObj.getDeclaredFields();

		for (Field field : fields) {
			System.out.println(t + "getFieldArray---field=" + field
					+ "\t____field.attributeName=" + field.getName()
					+ "\t____fieldType=" + field.getType());
		}
		return fields;
	}
}
