package com.myproject.algorithm.poj1005;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		for (int i = 1; i <= n; i++) {
			double x = scanner.nextFloat();
			double y = scanner.nextFloat();
			double s = Math.PI * (x * x + y * y);
			int count = 0;
			while (s > 0) {
				s = s - 100;
				++count;
			}
			System.out.println("Property " + i + ": This property will begin eroding in year " + count + ".");

		}
		System.out.println("END OF OUTPUT.");
	}

}
