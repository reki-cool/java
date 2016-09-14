package pw.produ;

/**
 * @author Pro.Du
 * @email  2504621508@qq.com
 * @date   2016年9月14日 下午12:08:14
 */
public class ArgsNumbers {

	public static double max(double... values) {
		double largest = Double.MIN_VALUE;
		for(double v: values) if(v > largest) largest = v;
		return largest;
	}
	
	public static void main(String[] args) {
		System.out.println(max(1.2,3.4,5.4,0,3.3,5.4,3,5));
	}

}
