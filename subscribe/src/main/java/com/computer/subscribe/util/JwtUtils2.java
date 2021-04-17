package com.computer.subscribe.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class JwtUtils2 {
	public static Logger logger = Logger.getLogger(JwtUtils2.class);

	private static JwtUtils2 jwtUtils2;

	private static final Object LOCK = new Object();

	private JwtUtils2() {
		System.err.println(this.getClass() + "__JwtUtils2_私有化构造器,防止被实例化");
	}

	/**
	 * 懒汉式之单例模式
	 * 
	 * @return
	 */
	public static JwtUtils2 getInstance() {
		if (jwtUtils2 == null) {
			synchronized (LOCK) {// 决定是否锁住
				if (jwtUtils2 == null) {
					jwtUtils2 = new JwtUtils2();
				}
			}
		}
		return jwtUtils2;
	}

	/**
	 * 密钥
	 */
	private static final String SECRET = "xxxx-xxx-xxxx";
	/**
	 * 默认字段key:expression
	 */
	private static final String EXP = "expression";
	/**
	 * 默认字段key:payload
	 */
	private static final String PAYLOAD = "payload";

	/**
	 * 加密数据
	 * 
	 * @param object  加密数据
	 * @param maxTime 有效期（毫秒数）
	 * @param <T>
	 * @return
	 */
	public <T> String encode(T object, long maxTime) {
		try {
			final JWTSigner signer = new JWTSigner(SECRET);

			final Map<String, Object> data = new HashMap<>(10);

			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(object);

			data.put(PAYLOAD, jsonString);
			data.put(EXP, System.currentTimeMillis() + maxTime);

			String sign = signer.sign(data);
			System.out.println(this.getClass() + "..encode.sign=" + sign);

			return sign;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 数据解密
	 * 
	 * @param jwt    解密数据
	 * @param tClass 解密类型
	 * @param <T>
	 * @return
	 */
	public <T> T decode(String jwt, Class<T> tClass) {
		final JWTVerifier jwtVerifier = new JWTVerifier(SECRET);

		try {
			final Map<String, Object> data = jwtVerifier.verify(jwt);

			// 判断数据是否超时或者符合标准
			if (data.containsKey(EXP) && data.containsKey(PAYLOAD)) {

				long exp = (long) data.get(EXP);
				long currentTimeMillis = System.currentTimeMillis();

				if (exp > currentTimeMillis) {
					String json = (String) data.get(PAYLOAD);
					ObjectMapper objectMapper = new ObjectMapper();

					T value = objectMapper.readValue(json, tClass);
					System.err.println(this.getClass() + "..decode.value=" + value);

					return value;
				}
			}
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解密token,取出userId
	 * 
	 * @param <T>
	 * @param jwt
	 * @param tClass
	 * @return
	 */
	public <T> T updateDecode(String jwt, Class<T> tClass) {
		final JWTVerifier jwtVerifier = new JWTVerifier(SECRET);

		try {
			final Map<String, Object> data = jwtVerifier.verify(jwt);

			String json = (String) data.get(PAYLOAD);
			ObjectMapper objectMapper = new ObjectMapper();

			T value = objectMapper.readValue(json, tClass);
			System.err.println(this.getClass() + "..updateDecode.value=" + value);

			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
