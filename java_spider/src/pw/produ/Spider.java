package pw.produ;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
/**
 * http://www.yeetrack.com/?p=773
 * http://blog.csdn.net/caesardadi/article/details/8621595
 * @Title : Spider.java
 * @author: Pro.DU
 * @date  : 2016年9月21日上午7:59:42
 * @bolg  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description:
 */
public class Spider {

	public static void main(String[] args) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//创建一个CloseableHttpClient对象
		HttpGet httpget = new HttpGet("");
		//创建一个HttpGet对象
	}

}
