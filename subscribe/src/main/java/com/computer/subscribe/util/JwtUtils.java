package com.computer.subscribe.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;

/**
 * token加密/解密之工具类
 * 
 * @author user
 *
 */
public class JwtUtils {
	String t = this.getClass() + "____\n";

	public static Logger logger = Logger.getLogger(JwtUtils.class);

	private static JwtUtils jwtUtils;

	private static final Object LOCK = new Object();

	private JwtUtils() {
		System.err.println(t + "__JwtUtils_私有化构造器,防止被实例化");
	}

	/**
	 * 懒汉式之单例模式
	 * 
	 * @return
	 */
	public static JwtUtils getInstance() {
		if (jwtUtils == null) {
			synchronized (LOCK) {// 决定是否锁住
				if (jwtUtils == null) {
					jwtUtils = new JwtUtils();
				}
			}
		}
		return jwtUtils;
	}

	/**
	 * 密钥
	 */
	private static final String SECRET = "secret";
	/**
	 * 默认字段key:expire-到期
	 */
	private static final String EXP = "expire";
	/**
	 * 默认字段key:payload-有效载荷
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
			System.out.println(t + "__encode.sign=" + sign);

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
					System.err.println(t + "__decode().value=" + value);

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
	public <T> T crackDecode(String jwt, Class<T> tClass) {
		final JWTVerifier jwtVerifier = new JWTVerifier(SECRET);

		try {
			final Map<String, Object> data = jwtVerifier.verify(jwt);

			String json = (String) data.get(PAYLOAD);
			ObjectMapper objectMapper = new ObjectMapper();

			T value = objectMapper.readValue(json, tClass);
			System.err.println(t + "..crackDecode().value=" + value);
			return value;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	public static void main(String[] args) {
//		JwtUtils util = new JwtUtils();
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("id", 265);
//		map.put("userNum", 1316618515L);
//		map.put("living", "ice.city.alias.winter");
//		// 加密生成令牌
//		String token = util.encode(map, 120 * 1000 * 1000);
//		System.out.println("token=== " + token);
//		// 解密
//		HashMap decode = util.decode(token, HashMap.class);
//		System.out.println("decode=== " + decode);
//		System.out.println("decode.GET-userNum=== " + decode.get("userNum"));
//		// 揭密
//		HashMap crackcode = util.crackDecode(token, HashMap.class);
//		System.out.println("crackcode=== " + crackcode);
//		System.out.println("crackcode.getValueByKey=== " + crackcode.get("living"));
//	}

}
