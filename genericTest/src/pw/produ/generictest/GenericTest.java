package pw.produ.generictest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

/**
 * @Title : GenericTest.java
 * @author: Pro.DU
 * @date : 2016年10月8日下午6:21:21
 * @bolg : www.produ.pw
 * @email : 2504621508@qq.com
 * @Description: 测试泛型集合以及集合的遍历
 */
public class GenericTest {

	@Test
	public void testList() {
		// List集合
		List<String> list = new ArrayList<String>();
		list.add("都");
		list.add("颜");
		list.add("汗");

		// List集合的循环遍历 有三种方式: ①普通for  ②增强for ③迭代器

		// ①普通for
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			System.out.println(str);
		}

		System.out.println("=======================");

		// ②增强for    增强for的格式为:for(类型 变量 : 集合){}
		for (String str : list) {
			System.out.println(str);
		}

		System.out.println("=======================");

		// ③迭代器
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	@Test
	public void testSet() {
		//Set集合
		Set<String> set = new HashSet<String>();
		set.add("-都");
		set.add("-颜");
		set.add("-汗");
		
		//Set集合的循环遍历有两种方式     ①增强for  ②迭代器
		
		//①增强for
		for(String str : set){
			System.out.println(str);
		}
		
		System.out.println("=======================");

		//②迭代器
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	@Test
	public void testMap() {
		//Map集合
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("都", 1); //put方法左边是"key",右边是"value"
		map.put("颜", 2);
		map.put("汗", 3);
		
		//Map集合的循环遍历有两种方式    ①获取key->找到对应的value值     ②获取key:value的关系(用entry来维护key和value)    ["entry" 是 "记录"的意思]
		
		//①获取key->找到对应的value值
		Set<String> keys = map.keySet();   //找到所有key构成的集合
		//然后使用增强for循环或者迭代器都可以(此处直接增强for为例)
		for(String key : keys){     //遍历key集合中每一个key,并获取对应的value
			Integer value = map.get(key);
			System.out.println(key + "  " + value);
		}

		System.out.println("=======================");
		
		//②获取key:value的关系(用entry来维护key和value)  [通过entry可以获取key和value]
		Set<Entry<String, Integer>> entrys = map.entrySet();  //获取关系(记录)集合
		//然后使用增强for循环或者迭代器都可以(此处直接增强for为例)
		for (Entry<String, Integer> entry : entrys) {
			System.out.println(entry.getKey() + "  " + entry.getValue());
		}
		
	}
}
