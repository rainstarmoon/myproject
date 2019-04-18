package com.myproject.algorithm.huawei;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println(way02("2016-02-27", "2016-03-01"));
		String inputDateStr = "2020-12-22";
		String originalDateStr = "2016-12-22";
		long begin = System.currentTimeMillis();
		// 1百万次循环
		for (int i = 0; i < 1000000; i++) {
			// way01(inputDateStr, originalDateStr);
			way03(inputDateStr, originalDateStr);
			// System.out.println(way02(inputDateStr, originalDateStr));
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时" + (end - begin) + "毫秒");
	}

	public static long way01(String inputDateStr, String originalDateStr) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date inputDate = dateFormat.parse(inputDateStr);
		Date originalDate = dateFormat.parse(originalDateStr);
		long inputTime = inputDate.getTime();
		long originalTime = originalDate.getTime();
		return (inputTime - originalTime) / (1000 * 60 * 60 * 24);
	}

	public static long way02(String inputDateStr, String originalDateStr) {
		LocalDate inputDate = LocalDate.parse(inputDateStr);
		LocalDate originalDate = LocalDate.parse(originalDateStr);
		// return inputDate.toEpochDay() - originalDate.toEpochDay();
		return originalDate.until(inputDate, ChronoUnit.DAYS);
		// return ChronoUnit.DAYS.between(originalDate, inputDate);
	}

	public static long way03(String inputDateStr, String originalDateStr) {
		int inputYear = Integer.valueOf(inputDateStr.substring(0, 3));
		int originalYear = Integer.valueOf(originalDateStr.substring(0, 3));
		int num = 0;
		int k = 1;
		for (int i = originalYear; i <= inputYear; i = i + k) {
			if (i % 400 == 0 || (i % 100 != 0 && i % 4 == 0)) {
				num++;
				k = 4;
			}
		}
		int tmp = (inputYear - originalYear) * 365 + num;
		return tmp;
	}

}
