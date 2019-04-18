package com.myproject.algorithm.toutiao;

import java.math.BigInteger;
import java.util.Scanner;

public class Main_001 {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		int youhua = scanner.nextInt();
		
		long time1 = System.currentTimeMillis();

		BigInteger a = new BigInteger("5");
		BigInteger b = new BigInteger("10000000000");
		BigInteger after = b;
		BigInteger yi = new BigInteger("1");
		BigInteger count = new BigInteger("0");
		BigInteger before = new BigInteger("0");

		if (b.compareTo(a) > 0) {
			after = a;
		}

		// 优化
		BigInteger flag = new BigInteger("2");
		for (int i = 0; i < youhua; i++, flag = flag.add(yi)) {
			before = after;
			after = a.divide(flag);
			count = count.add(flag.subtract(yi).multiply(before.subtract(after)));
		}

		do {
			count = count.add(a.divide(after));
			after = after.subtract(yi);
		} while (!(after.compareTo(yi) == -1));

		long time = System.currentTimeMillis() - time1;
		System.out.println(time + " " + count);
	}

}
