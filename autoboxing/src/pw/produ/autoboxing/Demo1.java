package pw.produ.autoboxing;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @Title : Demo1.java
 * @author: Pro.DU
 * @date  : 2016年10月17日下午8:23:52
 * @bolg  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description: 自动装箱和拆箱
 */
public class Demo1 {
	/**
	 * 这个是JDK5后的写法
	 */
	@Test
	public void run1() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		
		//自动装箱
		Integer i = 1;
		//自动拆箱
		int j = i;
	}
	//==============================================
	/**
	 * JDK5之前的写法
	 */
	public void run2() {
		List list = new ArrayList();
		list.add(new Integer(1));
		
		//装箱
		Integer i = new Integer(1);
		//拆箱
		int j = i.intValue();
	}
	/**
	 * 小结论:其实都是一样的意思,JDK5后的写法是自动装箱拆箱,JDK5之前的是手动装箱拆箱
	 */
	//==============================================
	/**
	 * 一个小测试
	 */
	@Test
	public void run3() {
		Integer n1 = 100;
		Integer n2 = 100;
		Integer n3 = new Integer(100);
		Integer n4 = new Integer(100);
		Integer n5 = 128;
		Integer n6 = 128;
		
		System.out.println(n1 == n2); // true 
		System.out.println(n3 == n4); // false
		System.out.println(n5 == n6); // false
		/**
		 * 针对上述结果,为什么是这样的???
		 * 
		 * 因为Integer缓存了[-128, 127]这个区间所有的常量值,换句话说,Integer有一个这样的区间常量池,它们都是基本类型int
		 * 那么100在这个常量池内,所以n1,n2直接用100这个常量值,他们就代表100这个数值
		 * n3,n4是分别申请自已的空间然后用100初始化并得到自己的引用,自然不等
		 * 超过127则不再是常量,必须申请空间进行自动装箱成包装类,也就是n5,n6的情况,产生各自不等的引用
		 * 我的理解:   n3,n4的写法和n5,n6的写法是等价的,前者是JDK5之前的手动装箱方式,后者是JDK5之后的自动装箱方式
		 */
	}
}
