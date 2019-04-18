package com.myproject.algorithm;

public class Main3 {

	public static void main(String[] args) {
		
		int[][] a = {
					{0, 0, 1, 1,1,1},
					{0, 0, 1, 1,1,1},
					{0, 0, 1, 1,1,1},
					{0, 1, 0, 0,1,1},
					{1, 1, 0, 0,1,1}
		};

		System.out.println(cout(a,5,6));
		
	}

	// 求最大边长的函数
	private static int cout(int[][] a, int m, int n) {
		int max = -1;
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (a[i][j] == 1) {
					int min = a[i - 1][j - 1];
					if (a[i - 1][j] < min) {
						min = a[i - 1][j];
					}
					if (a[i][j - 1] < min) {
						min = a[i][j - 1];
					}
					a[i][j] += min;
					if (max < a[i][j]) {
						max = a[i][j];
					}
				}
			}
		}
		return max;
	}
}