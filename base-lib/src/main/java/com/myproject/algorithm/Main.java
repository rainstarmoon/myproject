package com.myproject.algorithm;

public class Main {
	// 开关
	volatile boolean sw = true;
	// 判断是否有数组已输出完
	volatile boolean over = false;

	public static void main(String[] args) {
		int[] a1 = new int[] { 1, 2, 3, 4 };
		int[] a2 = new int[] { 5, 6, 7, 8, 9 };
		Main test = new Main();
		test.process(a1, a2);
	}

	private void process(int[] a1, int[] a2) {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < a1.length; i++) {
				while (sw != true && over == false) {
					// 自旋；可以加睡眠时间
				}
				if (sw == true|| over == true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(a1[i]);
					sw = false;
				}
			}
			over = true;
		});
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < a2.length; i++) {
				while (sw != false && over == false) {
					// 自旋
				}
				if (sw == false || over == true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(a2[i]);
					sw = true;
				}
			}
			over = true;
		});
		t1.start();
		t2.start();
	}
}