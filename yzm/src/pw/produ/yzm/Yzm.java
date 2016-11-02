package pw.produ.yzm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javassist.bytecode.analysis.Type;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @Title : Yzm.java
 * @author: Pro.DU
 * @date  : 2016年11月2日下午4:38:14
 * @blog  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description: 验证码
 */
public class Yzm extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 *  一、在内存中生成一张图片(纸)，没有设置背景颜色，画一个有颜色的填充的矩形，并且和纸的大小相同，覆盖上去，就有背景颜色了
			二、获取画笔对象，用来设置颜色、字体、字符串、画图形...
			三、先准备好验证码数据，随机生成几个字符，并将字符画到图片上
			四、画干扰线
			五、把内存中的图片输出到客户端上
		 */
		//在内存中生成一张图片
		int width = 150, height = 30;//验证码长和宽
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//获取画笔对象
		Graphics2D g = (Graphics2D) image.getGraphics();
		//设置画笔颜色为灰色
		g.setColor(Color.GRAY);
		//画填充的矩形
		g.fillRect(0, 0, width, height);
		//设置画笔颜色为蓝色
		g.setColor(Color.BLUE);
		//画边框
		g.drawRect(0, 0, width-1, height-1);//如果不减1，就看不见右下两个边框
		//设置字体
		g.setFont(new Font("宋体",Font.BOLD,20));
		//画字符
		g.setColor(Color.YELLOW);
		//准备数据，随机获取四个 字符
		String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		int x = 28, y = 20;
		for(int i = 0; i < 4; i++){
			//void rotate(double theta, double x, double y) 设置字符旋转  theta是弧度  , 弧度  = 角度*Math.PI/180;
			//此处获取正负30之间的角度
			int jiaodu = random.nextInt(60)-30;
			double hudu = jiaodu*Math.PI/180;
			g.rotate(hudu,x,y);//-----------------------旋转角度
			int index = random.nextInt(words.length());
			//返回指定下标位置的字符，随机获取下标
			
			char ch = words.charAt(index);
			//System.out.println("" + ch);
			g.drawString("" + ch, x, y);
			g.rotate(-hudu,x,y);//-----------------------角度还原
			x += 28;
		}
		//设置画笔颜色
		g.setColor(Color.BLUE);
		int x1, y1, x2, y2;
		for(int i = 0; i < 4; i++) {
			x1 = random.nextInt(width);
			y1 = random.nextInt(height);
			x2 = random.nextInt(width);
			y2 = random.nextInt(height);
			//画干扰线
			g.drawLine(x1, y1, x2, y2);
		}
		
		//输出到客户端
		ImageIO.write(image, "JPG", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
