package pw.produ;

import java.awt.EventQueue;

import javax.swing.JFrame;//swing类位于javax.swing包中，“javax”表示这个包是一个java的扩展包

/**
 * @Title : SimpleFrameTest.java
 * @author: Pro.DU
 * @date  : 2016年10月4日上午10:11:35
 * @bolg  : www.produ.pw 
 * @email : 2504621508@qq.com 
 * @Description:这是一个创建框架实例，在Java中，顶层窗口被称为框架，顶层窗口就是没有包含在其他窗口中的窗口。
 */
public class SimpleFrameTest {
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				SimpleFrame frame = new SimpleFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//定义一个用户关闭这个框架时的响应动作
				frame.setVisible(true);
				//默认情况下，框架是不显示的，这样设置是让框架显示出来
			}
		});
	}
}
/*
 * 在上面的main方法中创建了一个SimpleFrame的对象使它可见。
 */
class SimpleFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	/*
	 * 默认情况下，框架的大小为0x0，没有什么实际意义。
	 * 所以这里定义了一个JFrame的子类SimpleFrame，并通过它的构造器
	 * 将框架大小设置为300x200像素
	 */
	public SimpleFrame() {//构造器
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}

//----------知识点-------------
/* 所有的【Swing组件】必须由【事件分派线程】进行配置，
 * 【事件分派线程】将【鼠标点击】和【按键控制】转移到【用户接口组件】
 * 下面是【事件分派线程】中的【执行代码】的格式
 * EventQueue.invokeLater(new Runnable()
 * {
 * 		public void run()
 * 		{
 * 			statements(这里就写一些要运行的事件的说明，然后由事件分派线程来处理)
 * 		}
 * });
 * 这个执行代码就放在main方法中运行。
 */

/*
 * 在初始化语句结束后 ，main方法退出。
 * 【但是】，推出main方法，并【没有】终止程序，
 * 【终止的只是主线程】，事件分派线程保持程序处于【激活状态】，
 * 直到【关闭框架】或【调用System.exit】方法才终止程序。
 * 【我的理解】：事件分派线程是独立于主线程之外的另一个线程
 */
