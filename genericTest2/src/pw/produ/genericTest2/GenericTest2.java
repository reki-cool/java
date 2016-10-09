package pw.produ.genericTest2;

import java.util.Arrays;

import org.junit.Test;

/**
 * @Title : GenericTest2.java
 * @author: Pro.DU
 * @date : 2016年10月9日下午6:36:16
 * @bolg : www.produ.pw
 * @email : 2504621508@qq.com
 * @Description: 实验目的1:编写一个泛型方法,实现指定位置上数组元素的交换
 *               实验目的2:编写一个泛型方法,接收一个任意数组,并颠倒数组中的所有元素
 */
public class GenericTest2 {
	// ==============================实验1====================================
	/**
	 * 实验目的1:编写一个泛型方法,实现指定位置上数组元素的交换
	 */
	@Test
	public void run1() {
		// (针对int 型数组的元素交换)
		/*
		 * int[] arr = new int[] { 1, 2, 3, 4, 5, 6 }; change(arr, 1, 3);
		 * System.out.println(Arrays.toString(arr));
		 */

		// (针对String 型数组的元素交换)
		String[] strarr = new String[] { "aa", "bb", "cc", "dd" };
		change(strarr, 1, 3);
		System.out.println(Arrays.toString(strarr));

		/*
		 * 如果还要有其他类型数组的元素交换一个个写，效率很低 所以应该使用泛型来提高效率
		 * 写完泛型方法之后,上面的int数组传入到change方法中会报错,
		 * 是因为泛型方法的类型参数的必须是类类型,int是基础数据类型,所以换成Integer类型就不报错了
		 */

		Integer[] arr = new Integer[] { 1, 2, 3, 4, 5, 6 };
		change(arr, 1, 3);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 自定义泛型方法 交换arr数组中下标为i和j的两个元素(针对各种类型数组) 声明泛型 <T> 放在返回值之前， 然后就可以使用了
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	public <T> void change(T[] arr, int i, int j) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/**
	 * 交换strarr数组中下标为i和j的两个元素(针对String 型数组)
	 * 
	 * @param strarr
	 * @param i
	 * @param j
	 */
	/*
	 * private void change(String[] strarr, int i, int j) { String temp =
	 * strarr[j]; strarr[j] = strarr[i]; strarr[i] = temp; }
	 */
	/**
	 * 交换arr数组中下标为i和j的两个元素 (针对int 型数组)
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	/*
	 * private void change(int[] arr, int i, int j) { int temp = arr[j]; arr[j]
	 * = arr[i]; arr[i] = temp; }
	 */

	// =================================实验2====================================
	/**
	 * 实验目的2:编写一个泛型方法,接收一个任意数组,并颠倒数组中的所有元素
	 */
	@Test
	public void run2() {
		String[] arr = new String[] { "aa", "bb", "cc", "dd", "ee", "ff", "gg" };
		convert(arr);
		System.out.println(Arrays.toString(arr));
	}
	/**
	 * 自定义泛型方法,颠倒数组中所有的元素位置
	 * @param arr
	 */
	private <T> void convert(T[] arr) {
		for(int i = 0; i <= (arr.length-1)/2; i++) {
			T temp = arr[i];
			arr[i] = arr[arr.length-1-i];
			arr[arr.length-1-i] = temp;
		}
	}
}
