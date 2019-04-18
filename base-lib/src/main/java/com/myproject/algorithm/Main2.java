package com.myproject.algorithm;

public class Main2  {
	
	static boolean over = false;
	
	public static void main(String[] args) {
		Object obj = new Object();
		int[] a1 = new int[] { 1, 2, 3, 4 };if(over) {
			
		}else {
			try {
				obj.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int[] a2 = new int[] { 5, 6, 7, 8, 9 };
		new Thread() {
			@Override
			public void run() {
				synchronized (obj) {
					int i=0;
					while(true) {
						obj.notify();
						System.out.println(a1[i]);
						i++;
						if(i>=a1.length) {
							over= true;
							break;
						}
						if(over) {
							
						}else {
							try {
								obj.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				synchronized (obj) {
					int i=0;
					while(true) {
						obj.notify();
						System.out.println(a2[i]);
						i++;
						if(i>=a2.length) {
							over= true;
							break;
						}
						if(over) {
							
						}else {
							try {
								obj.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}.start();
	}
}