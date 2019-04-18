package com.myproject.algorithm.poj1004;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		float value = 0;
		for (int i = 0; i < 12; i++) {
			value += Float.valueOf(scanner.nextLine());
		}
		value /= 12;
		DecimalFormat df = new DecimalFormat(".00");
		String str = df.format(value);
		System.out.println("$" + str);
		scanner.close();
	}
}