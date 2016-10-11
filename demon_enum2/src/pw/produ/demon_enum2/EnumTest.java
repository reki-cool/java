package pw.produ.demon_enum2;

import org.junit.Test;

/**
 * @Title : EnumTest.java
 * @author: Pro.DU
 * @date : 2016年10月11日下午3:48:02
 * @bolg : www.produ.pw
 * @email : 2504621508@qq.com
 * @Description:
 *  1.已知枚举对象，获取枚举对象的名称和下标。
 *  2.已知枚举对象的名称，获取枚举对象和下标。 
 *  3.已知枚举对象的下标，获取枚举对象和名称。
 */
public class EnumTest {
	/**
	 * 1.已知枚举对象，获取枚举对象的名称和下标。
	 */
	@Test
	public void run1() {
		//获取枚举对象
		Love girl = Love.GIRL;
		//获取名称
		System.out.println(girl.name());
		//获取下标值
		System.out.println(girl.ordinal());
	}
	/**
	 * 2.已知枚举对象的名称，获取枚举对象和下标。 
	 */
	@Test
	public void run2(){
		String name = "READ";
		//获取枚举对象
		Love read = Enum.valueOf(Love.class, name);//我觉得这里应该是用了反射
		//获取下标值
		System.out.println(read.ordinal());
		//=======================下面的也可行===========
		Love read2 = Love.valueOf(name);
		System.out.println(read2.ordinal());
	}
	/**
	 * 3.已知枚举对象的下标，获取枚举对象和名称。
	 */
	@Test
	public void run3(){
		int idx = 2;
		Love[] loves = Love.values();
		Love code = loves[idx];
		System.out.println(code.name());
	}
}

enum Love {
	GIRL, READ, CODING;
}