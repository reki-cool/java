package pw.produ.forin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

/**
 * @Title : Demo1.java
 * @author: Pro.DU
 * @date : 2016年10月18日下午1:28:10
 * @blog : www.produ.pw
 * @email : 2504621508@qq.com
 * @Description: 增强for循环
 */
public class Demo1 {

	@Test
	public void run() {
		List<String> list = new ArrayList<String>();
		list.add("aa");
		list.add("bb");
		list.add("cc");

		System.out.println("=========================");

		// 使用增强for循环遍历
		for (String str : list) {
			System.out.println(str);
		}

		System.out.println("=========================");

		// 使用Iterator迭代器
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		/**
		 * 结论：底层基本是一样的，效率也基本是一样的。 而使用增强for可以少写一些代码，使用更方便。
		 */
	}

}
