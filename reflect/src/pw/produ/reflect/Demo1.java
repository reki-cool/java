package pw.produ.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * @Title : Demo1.java
 * @author: Pro.DU
 * @date : 2016年10月19日上午11:04:37
 * @blog : www.produ.pw
 * @email : 2504621508@qq.com
 * @Description: 了解反射
 */
public class Demo1 {
	//-----------------------------------------------------------------------------------
	/**
	 * 三种方式获取class对象
	 * 
	 * @throws Exception
	 */
	@Test
	public void run() throws Exception {
		// 类名
		Class clazz = Person.class;
		// 通过实例
		Class clazz2 = new Person().getClass();
		// Class.forName
		Class clazz3 = Class.forName("pw.produ.reflect.Person"); // 这个需要抛出 异常
	}
//-----------------------------------------------------------------------------------
	/**
	 * 获取构造方法的对象
	 * 
	 * @throws Exception
	 */
	@Test
	public void run2() throws Exception {
		// 获取Person的Class对象
		Class clazz = Class.forName("pw.produ.reflect.Person");
		
		/*
		clazz.getConstructors(); // 获取所有的构造器对象

		// 创建实例，下面这种写法相当于调用了无参数的构造方法
		Person p = (Person) clazz.newInstance();  
		
		*/
		
		//获取有参数的构造器【对象】
		Constructor c = clazz.getConstructor(int.class,String.class);
		
		//创建实例，这种就是调用有参的构造方法
		Person p2 = (Person) c.newInstance(1,"Pro.Du");
		
		//打印测试
		System.out.println(p2.getName());
	}
//-----------------------------------------------------------------------------------
	/**
	 * 获取属性的对象
	 * @throws Exception
	 */
	@Test
	public void run3() throws Exception {
		//获取Person的Class对象
		Class clazz = Class.forName("pw.produ.reflect.Person");
		//声明实例
		Person p = (Person) clazz.newInstance();
		
		/*
		//获取属性
		clazz.getField("name"); // 获取公有的name属性，返回的是name这个属性的对象
*/		
		Field name = clazz.getDeclaredField("name"); // 公有和私有的属性都能获取，返回的是name这个属性的对象
		
		name.setAccessible(true); // 设置该属性对象的操作属性（即是否可以改变这个属性对象）
		
		name.set(p, "Pro.Du"); // 意思是将 p 对象的 name 属性的值设置为“Pro.Du”
		
		System.out.println(name.get(p));
	}
//-----------------------------------------------------------------------------------
	/**
	 * 获取方法的对象
	 * @throws Exception
	 */
	@Test
	public void run4() throws Exception{
		//获取Person的Class对象
		Class clazz = Class.forName("pw.produ.reflect.Person");
		//声明实例
		Person p = (Person)clazz.newInstance();
		/*
		clazz.getMethod(name, parameterTypes)//获取公有的方法  返回的是方法对象
		*/
		Method m = clazz.getDeclaredMethod("setName", String.class);//公有和私有的方法都能获取   返回的是方法对象
		
		m.setAccessible(true); //设置操作属性
		
		m.invoke(p,	"Pro.Du"); // 调用这个方法，相当于p.setName("Pro.Du");   记住这个invoke方法！！
		
		System.out.println(p.getName());
	}
}
