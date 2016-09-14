package pw.produ;

/**
 * @Title : ArgsNumbers.java
 * @author: Pro.DU
 * @date  : 2016年9月14日下午12:28:01
 * @bolg  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description: Demonstrates the number of args can changed!
 */
public class ArgsNumbers {

	public static double max(double... values) {
		double largest = Double.MIN_VALUE;
		for(double v: values) if(v > largest) largest = v;
		return largest;
	}
	
	public static void main(String[] args) {
		System.out.println(max(1.2,3.4,5.4,0,3.3,5.4,3,5));
		System.out.println(max(new double[]{1.2,3.4,5.4,0,3.3,5.4,3,5}));
		//上面两种写法是等价的
		
		
		//允许将一个数组传递给可变参数方法的最后一个参数    不过注意是printf  不是 print或者println
		System.out.printf("%d %s", new Object[]{new Integer(1), "widgets"});
	}

}
