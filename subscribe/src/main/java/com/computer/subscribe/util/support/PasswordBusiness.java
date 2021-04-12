package com.computer.subscribe.util.support;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;

/**
 * 密码业务工具类
 * 
 * @author user
 *
 */
public class PasswordBusiness {
	private static PasswordBusiness pwdBusiness;

	private static final Object LOCK = new Object();

	private PasswordBusiness() {
	}

	/**
	 * 懒汉式之单例模式
	 * 
	 * @return
	 */
	public static PasswordBusiness getInstance() {
		if (pwdBusiness == null) {
			synchronized (LOCK) {// 决定是否锁住
				if (pwdBusiness == null) {
					pwdBusiness = new PasswordBusiness();
				}
			}
		}
		return pwdBusiness;
	}

	/**
	 * 提取盐
	 *
	 * @return
	 */
	public String extractSalt() {
		Random random = new Random();
		StringBuilder builder = new StringBuilder(16);
		builder.append(random.nextInt(99999999));
		int length = builder.length();
		if (length < 16) {
			for (int i = 0; i < 16 - length; i++) {
				int n = random.nextInt(9);
				builder.append(n + "");
			}
		}

		String salt = builder.toString();
		return salt;
	}

	/**
	 * 获取十六进制字符串形式的MD5摘要(digest)
	 *
	 * @param src
	 * @return
	 */
	private String md5Hex(String src) {
		MessageDigest md5 = null;

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		byte[] bys = md5.digest(src.getBytes());

		byte[] encode = new Hex().encode(bys);
		String digest = new String(encode);

		return digest;

	}

	/**
	 * MD5+盐,输入密码,生成并返回密文
	 *
	 * @param pwd
	 * @return
	 */
	public String generate(String pwd, String salt) {
		System.out.println("generate.pwd== " + pwd);

		// 撒盐,并在MD5hex方法内均匀搅拌
		String hex = md5Hex(salt + pwd);
		System.err.println("hex== " + hex);

		char[] cs = new char[48];
		// 再加密
		for (int i = 0; i < 48; i += 3) {
			cs[i] = hex.charAt(i / 3 * 2);
			cs[i + 1] = salt.charAt(i / 3);
			cs[i + 2] = hex.charAt(i / 3 * 2 + 1);
		}
		String txt = new String(cs);
		System.out.println("generate().TXT == " + txt);
		return txt;
	}

	/**
	 * 校验加盐后是否和原文一致,逆向解密
	 *
	 * @param password 前台密码
	 * @param text     表中原文
	 * @return
	 */
	public boolean verify(String password, String text) {
		char[] digest = new char[32];
		char[] saltStuff = new char[16];

		for (int i = 0; i < 48; i += 3) {
			digest[i / 3 * 2] = text.charAt(i);
			digest[i / 3 * 2 + 1] = text.charAt(i + 2);
			saltStuff[i / 3] = text.charAt(i + 1);
		}

		String salt = new String(saltStuff);
		System.err.println("verify().salt== " + salt);

		String forePwdTxt = md5Hex(salt + password);
		System.out.println("forePwdTxt== " + forePwdTxt);

		String digestString = new String(digest);
		System.err.println("digestString== " + digestString);

		return forePwdTxt.equals(digestString);
	}
}
