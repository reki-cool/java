package cn.bms.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtils {

	private static String filepath;
	//使用类装载器来获取xml文件的地址。因为类装载器只装载一次，所以要想获得xml更新后的内容，只能先得到地址，在通过传统方法来读取文件
	static{
		filepath = XmlUtils.class.getClassLoader().getResource("users.xml").getPath();
	}
	
	public static Document getDocument() throws Exception{ //在工具类里，有异常就向上抛
		
		SAXReader reader = new SAXReader();
        Document document = reader.read(new File(filepath));
        return document;
	}
	
	public static void writeXml(Document document) throws IOException{
		
		//漂亮的格式写入
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");//控制fotmat用UTF-8的格式写入xml文件
		XMLWriter writer = new XMLWriter(new FileOutputStream(filepath), format );
        writer.write(document);
        writer.close();
	}
}

