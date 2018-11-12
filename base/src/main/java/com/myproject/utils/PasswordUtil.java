package com.myproject.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.Lists;

public class PasswordUtil {

	// 数字集合
	public static List<Character> NUMBER_SET = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

	// 小写字母集合
	public static List<Character> ENGLISH_SMALL_SET = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

	// 大写字母集合
	public static List<Character> ENGLISH_BIG_SET = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
			'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

	// 符号集合
	public static List<Character> SYMBOL_SET = Arrays.asList('~', '`', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')',
			'_', '-', '+', '=', '{', '}', '[', ']', '|', '\\', ':', ';', '"', '\'', '<', ',', '>', '.', '?', '/');

	// 密码集
	public static char[] PASSWORD_SET;

	// 密码集最后一个元素索引号
	public static int PASSWORD_SET_LAST_INDEX;

	// 密码已经完成测试次数
	public static long PASSWORD_TEST_TIMES = 0L;

	// 密码最后一次测试数据
	public static String PASSWORD_TEST_LAST_STRING;

	// 初始化常用密码集
	public static void initCommonPasswordSet() {
		List<Character> tempPasswordSet = Lists.newArrayList();
		tempPasswordSet.addAll(NUMBER_SET);
		tempPasswordSet.addAll(ENGLISH_SMALL_SET);
		tempPasswordSet.add('.');
		tempPasswordSet.add('_');
		tempPasswordSet.add(':');
		tempPasswordSet.add('/');
		PASSWORD_SET_LAST_INDEX = tempPasswordSet.size() - 1;
		PASSWORD_SET = ArrayUtils.toPrimitive(tempPasswordSet.toArray(new Character[tempPasswordSet.size()]));
	}

	// 密码集初始化
	public static void initPasswordSet(boolean needNumber, boolean needSmallEnglish, boolean needBigEnglish,
			boolean needSymbol) {
		List<Character> tempPasswordSet = Lists.newArrayList();
		if (needNumber) {
			tempPasswordSet.addAll(NUMBER_SET);
		}
		if (needSmallEnglish) {
			tempPasswordSet.addAll(ENGLISH_SMALL_SET);
		}
		if (needBigEnglish) {
			tempPasswordSet.addAll(ENGLISH_BIG_SET);
		}
		if (needSymbol) {
			tempPasswordSet.addAll(SYMBOL_SET);
		}
		PASSWORD_SET_LAST_INDEX = tempPasswordSet.size() - 1;
		PASSWORD_SET = ArrayUtils.toPrimitive(tempPasswordSet.toArray(new Character[tempPasswordSet.size()]));
	}

	// 获取下一个密码
	public static String calcNextPassword(String oldPassword) {
		PASSWORD_TEST_TIMES++;
		char[] charArray = oldPassword.toCharArray();
		if (isLastPasswordByLength(charArray)) {
			return initPasswordByLength(charArray.length + 1);
		}
		for (int i = charArray.length - 1; i >= 0; i--) {
			if (isLastElement(charArray[i])) {
				charArray[i] = becomeFirstElement();
				continue;
			} else {
				charArray[i] = calcNextElement(charArray[i]);
			}
			return String.valueOf(charArray);
		}
		return initPasswordByLength(charArray.length + 1);
	}

	// 判断在该长度上是否是最后一个密码
	private static boolean isLastPasswordByLength(char[] charArray) {
		for (int i = 0; i < charArray.length; i++) {
			if (!isLastElement(charArray[i])) {
				return false;
			}
		}
		return true;
	}

	// 判断是否是最后一个元素
	private static boolean isLastElement(char element) {
		if (PASSWORD_SET_LAST_INDEX == ArrayUtils.indexOf(PASSWORD_SET, element)) {
			return true;
		} else {
			return false;
		}
	}

	// 初始化给定长度下的密码
	private static String initPasswordByLength(int length) {
		char[] charArray = new char[length];
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = becomeFirstElement();
		}
		return String.valueOf(charArray);
	}

	// 成为第一个元素
	private static Character becomeFirstElement() {
		return PASSWORD_SET[0];
	}

	// 获取下一个元素
	private static Character calcNextElement(Character element) {
		return PASSWORD_SET[ArrayUtils.indexOf(PASSWORD_SET, element) + 1];
	}

}
