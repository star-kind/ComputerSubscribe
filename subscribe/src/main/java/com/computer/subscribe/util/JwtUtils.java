package com.computer.subscribe.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class JwtUtils {
	public static Logger logger = Logger.getLogger(JwtUtils.class);

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
			System.out.println(
					"\n" + this.getClass().getName() + ".sign=== " + sign + "\n");

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
					System.err.println("decode().value=== " + value);

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
			System.err.println("updateDecode().value=== " + value);

			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	public static void main(String[] args) {
//		JwtUtils util = new JwtUtils();
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("id", "ip4800");
//		map.put("num", "894go");
//		map.put("living", "ice.city.alias.winter");
//		// 加密生成令牌
//		String token = util.encode(map, 120 * 1000 * 1000);
//		System.err.println("token=== " + token);
//		// 解密
//		HashMap decode = util.decode(token, HashMap.class);
//		System.err.println("decode=== " + decode);
//		System.err.println("decode.GET=== " + decode.get("num"));
//		// 揭密
//		HashMap updateCode = util.updateDecode(token, HashMap.class);
//		System.err.println("updateCode=== " + updateCode);
//		System.err.println("updateCode.getKey=== " + updateCode.get("living"));
//	}

}
