package pw.produ.vargs;

import org.junit.Test;

public class Demo1 {
	@Test
	public void run() {
		System.out.println(add(3, 4));
		System.out.println(add(1, 3));
		System.out.println(add(1, 3, 6));
	}

	/**
	 * 可变参数
	 * 
	 * @param nums
	 * @return
	 */
	public int add(int... nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		return sum;
	}

	/*
	 * 下面是普通的写法，在参数不确定的情况下，下面的方法不可行，所以需要一个可变参数的方法。
	 * 
	 * public int add(int a, int b) { return a + b; }
	 * 
	 * public int add(int a, int b, int c) { return a + b + c; }
	 */
}
