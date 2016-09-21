package pw.produ;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * ref : http://www.yeetrack.com/?p=773
 * @Title : Spider.java
 * @author: Pro.DU
 * @date  : 2016年9月21日上午10:08:06
 * @bolg  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description: 以get方式爬取一个简单的网页
 */
public class Spider {

	public static void main(String[] args) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//创建一个CloseableHttpClient对象
		HttpGet httpget = new HttpGet("http://www.produ.pw");
		//创建一个HttpGet对象
		try {
			CloseableHttpResponse response = httpclient.execute(httpget);
			//执行请求  获得服务器返回的响应
			HttpEntity entity = response.getEntity();
			//根据响应获取实体
			if(entity != null) {
				InputStream in = entity.getContent();
				byte[] b = new byte[100];
				int n = 0; 
				while((n=in.read(b, 0, 100)) != -1) {
					String s = new String(b, 0, n);
					System.out.print(s);
				}
			}
			response.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
