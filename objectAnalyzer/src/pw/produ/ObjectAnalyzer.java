package pw.produ;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * @Title : ObjectAnalyzer.java
 * @author: Pro.DU
 * @date  : 2016年9月18日下午8:28:37
 * @bolg  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description: 反射--对象分析     防止无限递归
 */
public class ObjectAnalyzer {
	private ArrayList<Object> visited = new ArrayList<Object>();
	//用来记录已经访问过的对象的数组列表
	/**
	 * Converts an object to a string representation that lista all fields
	 * @param obj an object
	 * @return a string with the object's class name and all field names and values
	 */
	public String toString(Object obj){
		if(obj == null)  return "null";
		if(visited.contains(obj)) return "...";
		visited.add(obj);
		Class cl = obj.getClass();
		if(cl == String.class) return (String)obj;
		if(cl.isArray()) {
			String r = cl.getComponentType() + "[]{";
			for(int i = 0; i < Array.getLength(obj); i++) {
				if(i > 0) r += ",";
				Object val = Array.get(obj, i);
				if(cl.getComponentType().isPrimitive()) r += val;
				else r += toString(val);
			}
			return r + "}";
		}
		
		String r = cl.getName();
		//inspect the fields of this class and all superclasses
		do {
			r += "{";
			Field[] fields = cl.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);
			//get the names and values of all fields
			for(Field f : fields) {
				if (!Modifier.isStatic(f.getModifiers())) {
					if(!r.endsWith("[")) r += ",";
					r += f.getName() + "=";
					try {
						Class t = f.getType();
						Object val = f.get(obj);//这里可能会抛出非法访问异常  所以要放在try/catch块中
						if(t.isPrimitive()) r += val;
						else r += toString(val);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			r += "]";
			cl = cl.getSuperclass();
		}
		while(cl != null);
		
		return r;
	}
}
