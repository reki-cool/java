package pw.produ;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Title : CopyOfTest.java
 * @author: Pro.DU
 * @date  : 2016年9月19日下午10:37:07
 * @bolg  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description:两个扩展数组的方法，一个可用(goodCopyOf)，一个不可用(badCopyOf)
 */
public class CopyOfTest {
	public static void main(String[] args) {
		int[] a = {1, 2, 3};
		a = (int[]) goodCopyOf(a, 10);
		System.out.println(Arrays.toString(a));
		
		String[] b = {"Tom", "Dick", "Harry"};
		b = (String[]) goodCopyOf(b, 10);
		System.out.println(Arrays.toString(b));
		
		System.out.println("The following call will generate an exceprion.");
		b = (String[]) badCopyOf(b, 10);
	}
	
	public static Object[] badCopyOf(Object[] a, int newLength) //not userful 
	{
		Object[] newArray = new Object[newLength];
		System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLength));
		return newArray;
	}
	
	public static Object goodCopyOf(Object a, int newLength)
	{
		Class cl = a.getClass();
		if(!cl.isArray()) return null;//不是数组  返回null
		Class componentType = cl.getComponentType();
		int length = Array.getLength(a);
		Object newArray = Array.newInstance(componentType, newLength);
		System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
		return newArray;
	}
}
