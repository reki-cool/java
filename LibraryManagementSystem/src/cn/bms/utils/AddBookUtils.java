package cn.bms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class AddBookUtils {

public static <T> T requestToForm(HttpServletRequest request, Class<T> beanClass){ //当bean传进来的时候泛型T的类型就确定了，然后返回相对应的类型 ，在那边调用这个方法的时候就不用强转了，这就是用泛型的好处
		
		try {
			T bean = beanClass.newInstance(); //newInstance()可以得到一个类的实例化对象
			
			Enumeration e = (Enumeration) request.getParameterNames(); //获得request里所有属性的名称  Enumeration接口是一个数据结构，用它是因为Enumeration定义了一个名为nextElement的方法，可以用来从含有多个元素的数据结构中得到的下一个元素。
			
			while(e.hasMoreElements()){ //hasMoreElements() 是Enumeration接口中的一个方法，用来判断e中是否还有元素，如果返回true，则表示至少还有一个元素。
				String name = (String) e.nextElement(); //得到request.getParameterNames()中的下一个元素
				String value = request.getParameter(name); //将所得元素的值赋给value
				
				BeanUtils.setProperty(bean, name, value); //BeanUtils是Java自带的类，可以把数据封装到bean中
			}
			
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 formbean
	 
	 private String bkNum; //条形码
	 private String bkName; //书名
	 private String auName; //作者
	 private String auInfo; //作者简介
	 private String bkPub; //出版社
	 private Date bkPubTime; //出版时间
	 private int bkPrice; //单价
	 private String bkInfo; //书籍简介
	 private String bkLang; //语种
	 private String bkTag; //标签
	 private String typeName; //类别
	 private int bkPop; //人气
	 private Date bkInTime; //入库时间
	 private int bkInv; //库存 
	 
	 userbean
	 
	 private String bkNum; //条形码
	 private String bkName; //书名
	 private String auName; //作者
	 private String auInfo; //作者简介
	 private String bkPub; //出版社
	 private Date bkPubTime; //出版时间
	 private int bkPrice; //单价
	 private String bkInfo; //书籍简介
	 private String bkLang; //语种
	 private String bkTag; //标签
	 private String typeName; //类别
	 private int bkPop; //人气
	 private Date bkInTime; //入库时间
	 private int bkInv; //库存
	 */
	
	public static void copyBean(Object dest, Object src){
		
		/*
		 当用到BeanUtils的populate、copyProperties方法或者getProperty,setProperty方法其实都会调用convert进行转换
		 但Converter只支持一些基本的类型，甚至连java.util.Date类型也不支持。而且它比较笨的一个地方是当遇到不认识的类型时，居然会抛出异常来。
		 这个时候就需要给类型注册转换器。比如：意思是所以需要转成Date类型的数据都要通过DateLocaleConverter这个转换器的处理。
		 ConvertUtils.register(new DateLocaleConverter(), Date.class);
		 */
		
		//因为在UserBean里的有些数据不是基本类型，例如生日 Date，所以要想把formBean中的数据全部复制到UserBean中，就要弄一个转换器来将formbean中的生日类型转为Date
		ConvertUtils.register(new Converter(){ //因为SUN公司提供的转换器有问题，所以要自己去实现Converter接口

			@Override
			public Object convert(Class type, Object value) {
				if(value == null){
					return null;
				}
				
				String str = (String) value;
				if(str.trim().equals("")){
					return null;
				}
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return df.parse(str); //当无法转换时抛运行时异常
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
			
		}, Date.class);
		
		try {
			BeanUtils.copyProperties(dest, src); //可以将src所代表的bean中的8中数据类型复制到dest所代表的bean中去
		} catch (Exception e) {
			throw new RuntimeException(e); //工具类有问题的时候异常通常都设为运行时异常
		}

	}
	
	//生成全世界唯一的ID
	public static String generateID(){
		return UUID.randomUUID().toString();//UUID是一个128位长的数字，一般用16进制表示。算法的核心思想是结合机器的网卡、当地时间、一个随即数来生成GUID。用它可以产生一个号称全球唯一的ID
	}
}
