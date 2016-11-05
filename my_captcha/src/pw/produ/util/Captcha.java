package pw.produ.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.NumberUp;


public class Captcha {
	/**
	 * 无参构造方法，调用无参init方法对验证码对象进行初始化
	 */
	public Captcha(){
		init();
	}
	
	/**
	 * 有参构造方法，调用有参init方法对验证码对象进行初始化
	 * @param width		验证码的宽度
	 * @param height	验证码的高度
	 */
	public Captcha(int width, int height){
		init(width, height);
	}
	
	/**
	 * 设置验证码的背景颜色，里面调用的是矩形填充方法，专门用于绘制背景色
	 * @param c ----Color类中的颜色常量，常量值有以下这些：Color.BLACK 黑色  丨Color.BLUE 蓝色  丨Color.CYAN 青色  丨Color.DARK_GRAY 深灰色  丨Color.GRAY 灰色  丨Color.GREEN 绿色  丨Color.LIGHT_GRAY 浅灰色  丨Color.MAGENTA 洋红色  丨Color.ORANGE 桔黄色  丨Color.PINK 粉红色  丨Color.RED 红色  丨Color.WHITE 白色  丨Color.YELLOW 黄色  
	 */
	public void setBackgroundColor(Color c){
		this.BACKGROUND_COLOR = c;  //初始化背景色属性
		g2D.setColor(c);  // 设置画笔颜色
		g2D.fillRect(0, 0, WIDTH, HEIGHT);  //画填充矩形
	}
	
	/**
	 * 设置验证码的边框颜色
	 * @param c ----Color类中的颜色常量，
	 * 常量值有以下这些：Color.BLACK 黑色  丨Color.BLUE 蓝色  丨Color.CYAN 青色  丨Color.DARK_GRAY 深灰色  丨Color.GRAY 灰色  丨Color.GREEN 绿色  丨Color.LIGHT_GRAY 浅灰色  丨Color.MAGENTA 洋红色  丨Color.ORANGE 桔黄色  丨Color.PINK 粉红色  丨Color.RED 红色  丨Color.WHITE 白色  丨Color.YELLOW 黄  
	 */
	public void setBorderColor(Color c){
		g2D.setColor(c);  // 设置画笔颜色
		g2D.drawRect(0, 0, WIDTH-1, HEIGHT-1);  //画矩形
	}
	
	/**
	 * 设置单个字体颜色，并生成对应的字体。
	 * @param c 代表颜色的值 ，
	 * 常量值有以下这些：Color.BLACK 黑色  丨Color.BLUE 蓝色  丨Color.CYAN 青色  丨Color.DARK_GRAY 深灰色  丨Color.GRAY 灰色  丨Color.GREEN 绿色  丨Color.LIGHT_GRAY 浅灰色  丨Color.MAGENTA 洋红色  丨Color.ORANGE 桔黄色  丨Color.PINK 粉红色  丨Color.RED 红色  丨Color.WHITE 白色  丨Color.YELLOW 黄色  
	 */
	public void setFontColor(Color c){
		this.MY_FONT_COLOR = c;
	}
	
	/**
	 * 设置字体颜色是否随机
	 * @param b true代表随机字体颜色，false代表不随机
	 */
	public void setRandomColor(boolean b){
		this.RANDOM_COLOR_ABLE = b;
	}
	
	/**
	 * 根据编号获取颜色
	 * @param index 编号
	 * @return c   颜色
	 */
	private Color getColorByValue(int index){
		Color c = null;
		switch(index){
		case 1: c = Color.BLACK;
			break;
		case 2: c = Color.BLUE;
			break;
		case 3: c = Color.CYAN;
			break;
		case 4: c = Color.DARK_GRAY;
			break;
		case 5: c = Color.GRAY;
			break;
		case 6: c = Color.GREEN;
			break;
		case 7: c = Color.LIGHT_GRAY;
			break;
		case 8: c = Color.MAGENTA;
			break;
		case 9: c = Color.ORANGE;
			break;
		case 10: c = Color.PINK;
			break;
		case 11: c = Color.RED;
			break;
		case 12: c = Color.WHITE;
			break;
		case 13: c = Color.YELLOW;
			break;
		default:break;
		}
		return c;
	}
	
	/**
	 * 自动确定字体颜色,Color.BLACK 黑色  丨Color.BLUE 蓝色  丨Color.CYAN 青色  丨Color.DARK_GRAY 深灰色  丨Color.GRAY 灰色  丨Color.GREEN 绿色  丨Color.LIGHT_GRAY 浅灰色  丨Color.MAGENTA 洋红色  丨Color.ORANGE 桔黄色  丨Color.PINK 粉红色  丨Color.RED 红色  丨Color.WHITE 白色  丨Color.YELLOW 黄色
	 */
	private void autoSetFont() {
		if(this.RANDOM_COLOR_ABLE) {
			int index = -1;
			int A = 0, B = 0;
			do {
				index = random.nextInt(13)+1;
				A = BACKGROUND_COLOR.getRGB();
				B = getColorByValue(index).getRGB();
			}while(A == B); //如果随机的颜色与背景色相同，就一直循环
			
			g2D.setColor(getColorByValue(index)); // 应用颜色
		}else {
			g2D.setColor(MY_FONT_COLOR); // 应用颜色
		}
		g2D.setFont(MY_FONT);
	}
	/**
	 * 设置验证码的类型，(0,0)代表读取型数字验证码,(0,1)代表读取型中文验证码,(0,2)代表读取型字母验证码,(0,3)代表读取型混合验证码(数字+中文+字母),(1,0)代表计算型数字验证码,(1,1)代表计算型中文验证码
	 * @param one 此参数填0或者1,0代表选择摘抄型的验证码，1代表计算型验证码
	 * @param two 此参数伴随one参数，若前者为0，则此参数范围为0~3，若前者为1，则测参数范围为0~1
	 */
	public void setType(int one, int two) {
		if((one == 0 && two >= 0 && two <=3) || (one == 1 && two >= 0 && two <= 1)) {
			this.TYPE_STAGE1 = one;
			this.TYPE_STAGE2 = two;
			if(this.TYPE_STAGE1*4 + TYPE_STAGE2 > 3) {
				setCharNumber(4);
				SET_CHAR_NUMBER = false;
			}
			else SET_CHAR_NUMBER = true;
		}
		/**
		 * 分析：只有两种情况合法,其他情况(都是非法)皆不改变验证码类型参数：
			1、参数1为0，参数2为0~3
			2、参数1为1，参数2为0或1
		 */
	}
	
	/**
	 * 设置字符数据是否旋转
	 * @param b  true代表开启旋转,false代表关闭旋转
	 */
	public void setCharRotate(boolean b){
		IS_ROTATE = b;
	}
	
	/**
	 * 根据设置的验证码类型创建验证码数据，用0~3代表直接读取型，4~5代表计算型
	 */
	public void createData(){
		switch(4*TYPE_STAGE1+TYPE_STAGE2){
			case 0: //数字字串型
				createStrData(DIGITAL_STRING);
				break;
			case 1: //中文字串型
				createStrData(CNCHAR_STRING);
				break;
			case 2: //字母字串型
				createStrData(ALPHABET_STRING);
				break;
			case 3: //前三者混合字串型
				MULTI_STRING = DIGITAL_STRING + CNCHAR_STRING + ALPHABET_STRING;
				createStrData(MULTI_STRING);
				break;
			case 4: //数字计算型
				createCalData(DIGITAL_STRING);
				break;
			case 5: //中文计算型
				createCalData(CNCHAR_STRING);
				break;
			default:break;
		}
	}
	
	/**
	 * 设置验证码中的字符个数，此方法只针对读取型验证码有效
	 * @param x 表示要设置的直接读取型验证码的字符个数，值应为非负数！设置负数无效！
	 */
	public void setCharNumber(int x) {
		if(x >= 0 && SET_CHAR_NUMBER) {
			CHAR_NUMBER = x;
			autoAdjustPosition();
		}
	}
	
	
	/**
	 * 设置验证码字符的默认字体的大小， 并通过设置的字体大小，自动设置合适的偏移量(即各个字符的位置)-----【此处设置：当字体的size的值为验证码像素高度值的1/2时，字体的像素高度差不多刚好为验证码的像素高度的1/3，此时看起来较为美观；
	 * 另外，一个字体的像素宽度大概为自身像素高度的2/3，这样字体的宽度也确定了，高度也确定了，再加上，在绘画单个字符时，是以字符的左下角的点作为绘画起点的，那么就可以确定各个字符之间的间隔，以及每个字符绘画时的横纵偏移量！！】
	 */
	private void setFontSize(){
			this.FONT_SIZE = HEIGHT/2;
			MY_FONT = new Font("宋体", Font.BOLD, this.FONT_SIZE);
			autoAdjustPosition();
	}
	
	/**
	 * 设置验证码字符的字体大小，值为非负数，若输入负数则无效，并通过设置的字体大小，自动设置合适的偏移量
	 * @param size  字体大小，值当为正整数
	 */
	public void setFontSize(int size){
		if(size >= 0) {
			this.FONT_SIZE = size;
			MY_FONT = new Font("宋体", Font.BOLD, size);
			autoAdjustPosition();
		}
	}
	
	/**
	 * 自动根据验证码图片的长宽、字符个数设置每个字符的位置
	 */
	private void autoAdjustPosition() {
		X_OFFSET = X_DIST = (WIDTH-2*HEIGHT*CHAR_NUMBER/9)/(CHAR_NUMBER+1);
		Y_OFFSET = 2*HEIGHT/3;
	}
	
	/**
	 * 画单个验证码字符，里面分旋转和不旋转两种画法----（设置方法：rotate方法中一共有三个参数，最后两个参数代表的是单个字体对应的旋转中心，最好的旋转方式，自然是，每个字体，围绕自己的中心进行旋转，这样的话显示会更好一些，所以后面两个参数设置为X_OFFSET+FONT_SIZE/2, Y_OFFSET-FONT_SIZE/2，对应字体正中心）
	 * @return  返回画的字符的下标，如果是计算型验证码，则同时表示真实的数值
	 */
	private int drawChar(){
		autoSetFont();//设置字体之所以放在里面，是为了还能弄成每个字符颜色不同(大小粗细也可以不同)
		int jiaodu = random.nextInt(61)-30; //角度
		double myTheta = jiaodu*Math.PI/180;  //弧度
		int index = 0; //下标
		if(IS_ROTATE) {
			g2D.rotate(myTheta, X_OFFSET+FONT_SIZE/2, Y_OFFSET-FONT_SIZE/2);//后面两个参数代表旋转中心
			index = random.nextInt(WORDS.length());//返回指定下标位置的字符，随机获取下标
			char ch = WORDS.charAt(index); //根据下标获取字符
			ANSWER = ANSWER + ch; //更新验证码结果
			g2D.drawString("" + ch, X_OFFSET, Y_OFFSET);
			g2D.rotate(-myTheta, X_OFFSET+FONT_SIZE/2, Y_OFFSET-FONT_SIZE/2);//旋转还原
		}
		else {
			index = random.nextInt(WORDS.length());//返回指定下标位置的字符，随机获取下标
			char ch = WORDS.charAt(index); //根据下标获取字符
			ANSWER = ANSWER + ch; //更新验证码结果
			g2D.drawString("" + ch, X_OFFSET, Y_OFFSET);
		}
		return index;
	}
	
	/**
	 * 画计算型验证码的操作字符，里面分旋转和不旋转两种画法----（设置方法：rotate方法中一共有三个参数，最后两个参数代表的是单个字体对应的旋转中心，最好的旋转方式，自然是，每个字体，围绕自己的中心进行旋转，这样的话显示会更好一些，所以后面两个参数设置为X_OFFSET+FONT_SIZE/2, Y_OFFSET-FONT_SIZE/2，对应字体正中心）
	 * @param pos  要画的具体字符的下标
	 */
	private void drawOPChar(int pos){
		autoSetFont();//设置字体之所以放在里面，是为了还能弄成每个字符颜色不同(大小粗细也可以不同)
		int jiaodu = random.nextInt(61)-30; //角度
		double myTheta = jiaodu*Math.PI/180;  //弧度
		if(IS_ROTATE) {
			g2D.rotate(myTheta, X_OFFSET+FONT_SIZE/2, Y_OFFSET-FONT_SIZE/2);//后面两个参数代表旋转中心
			char ch = WORDS.charAt(pos); //根据下标获取字符
			ANSWER = ANSWER + ch; //更新验证码结果
			g2D.drawString("" + ch, X_OFFSET, Y_OFFSET);
			g2D.rotate(-myTheta, X_OFFSET+FONT_SIZE/2, Y_OFFSET-FONT_SIZE/2);//旋转还原
		}
		else {
			char ch = WORDS.charAt(pos); //根据下标获取字符
			ANSWER = ANSWER + ch; //更新验证码结果
			g2D.drawString("" + ch, X_OFFSET, Y_OFFSET);
		}
	}
	
	/**
	 * 画计算型验证码的操作字符
	 * @param x 代表字串NO_OPS(数字表)或者字串CN_OPS(中文数字表)
	 * @return 返回操作是加、减还是乘（用0/1/2表示）
	 */
	private int drawDoubleChar(String[] x){
		int index = random.nextInt(x.length);//返回指定下标位置的字符，随机获取下标
		String str = x[index]; //根据下标获取字符
		WORDS = str;
		drawOPChar(0);
		X_OFFSET += X_DIST+2*HEIGHT/9; //确定下一个待画字符的横向偏移
		drawOPChar(1);
		return index;//返回操作编号
	}
	

	/**
	 * 生成读取型验证码数据
	 * @param x 当前要使用的字符数据表
	 */
	private void createStrData(String x){
		WORDS = x;  //初始化当前要使用的字符数据表
		ANSWER = ""; //初始化验证码结果
		for(int i = 0; i < CHAR_NUMBER; i++){//生成CHAR_NUMBER个字符
			drawChar();
			X_OFFSET += X_DIST+2*HEIGHT/9; //确定下一个待画字符的横向偏移
		}
	}
	
	/**
	 * 生成计算型验证码数据
	 * @param x 当前要使用的字符数据表，DIGITAL_STRING(数字的)或者CNCHAR_STRING(中文的)
	 */
	private void createCalData(String x) {
		WORDS = x;  //初始化当前要使用的字符数据表
		ANSWER = ""; //初始化验证码结果
		int OP,op1,op2,ans = 0;
		op1 = drawChar(); //生成第一个数字
		X_OFFSET += X_DIST+2*HEIGHT/9; //确定下一个待画字符的横向偏移
		if(x.equals(CNCHAR_STRING)) {
			OP = drawDoubleChar(CN_OPS);
		}
		else {
			OP = drawDoubleChar(NO_OPS);
		}
		X_OFFSET += X_DIST+2*HEIGHT/9; //确定下一个待画字符的横向偏移
		WORDS = x;  //初始化当前要使用的字符数据表
		op2 = drawChar(); //生成第二个数字
		switch(OP){
		case 0: 
			ans = op1+op2;
			break;
		case 1:
			ans = op1-op2;
			break;
		case 2:
			ans = op1*op2;
			break;
		default:break;
		}
		ANSWER = "" + ans;
	}
	
	/**
	 * 将生成好的验证码写到输出流中，并且返回验证码的答案
	 * @param out  待存此验证码的输出流
	 */
	public String exportImage(OutputStream out){
		try {
			ImageIO.write(BUFFIMG, "JPG", out);
		} catch (IOException e) {
			System.out.println("验证码导出错误！！");
		}
		return ANSWER;
	}
	
	/**
	 * 初始化参数：
	 * 根据默认的宽(150px)和高(30px)创建画布对象;
	 * 创建画笔对象;
	 * 给画布涂上默认的灰色背景色;
	 * 创建随机数对象;
	 * 设置字体；
	 */
	private void init(){
		//获取画布对象并初始化画布
		BUFFIMG = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		//获取该画布的画笔对象
		g2D = (Graphics2D) BUFFIMG.getGraphics();
		//设置画布的默认背景色为灰色
		g2D.setColor(Color.GRAY);  // 设置画笔颜色
		g2D.fillRect(0, 0, WIDTH, HEIGHT);  //画填充矩形
		//获取随机数对象
		random = new Random();
		setFontSize();
	}
	
	/**
	 * 自定义画布的宽和高，并调用init无参初始化方法
	 * @param width		验证码宽度参数，参数要求正整数，若设置为负数，则自动设置为150(px)
	 * @param height	验证码高度参数，参数要求正整数，若设置为负数，则自动设置为30(px)
	 */
	private void init(int width, int height){
		if(width >= 0) this.WIDTH = width;
		if(width >= 0) this.HEIGHT = height;
		init();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 随机数对象的引用
	 */
	private Random random = new Random();
	/**
	 * 画笔对象的引用
	 */
	private Graphics2D g2D; 
	/**
	 * 画布对象的引用
	 */
	private BufferedImage BUFFIMG; 
	/**
	 * 画布(验证码)的宽度
	 */
	private int WIDTH = 150; 
	/**
	 * 画布(验证码)的高度
	 */
	private int HEIGHT = 50; 
	/**
	 * 此变量代表当前正在使用的字符表
	 */
	private String WORDS;
	/**
	 * 随机选取数字用的数字表
	 */
	private static String DIGITAL_STRING = "0123456789";
	/**
	 * 随机选取字母用的字母表
	 */
	private static String ALPHABET_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 随机选取中文的文字表
	 */
	private static String CNCHAR_STRING = "零一二三四五六七八九";
	/**
	 * 数字+英文字母+数字中文混合字符表
	 */
	private static String MULTI_STRING;
	/**
	 * 中文计算型验证码操作随机表
	 */
	private String[] CN_OPS = new String[]{"加上", "减去", "乘以"};
	/**
	 * 数字计算型验证码操作随机表
	 */
	private String[] NO_OPS =  new String[]{"十", "—", "X"};
	/**
	 * 此参数选择范围为0~1，若为0，则代表验证码类型为直接读取数据的验证码，若为1，则代表验证码类型为需要通过数学计算得到结果的验证码；此参数默认值为0；
	 */
	private int TYPE_STAGE1 = 0; 
	/**
	 * 若TYPE_STAGE1为0，则此参数可选0~3，分别代表数字型、中文型、字母型、三者混合型；若TYPE_STAGE1为1，则此参数可选0~1,分别代表中文型、数字型；此参数默认值为0；
	 */
	private int TYPE_STAGE2 = 0; 
	/**
	 * 设置字符是否旋转,默认为否，若设置，则旋转角度默认为正负30度之间的角度
	 */
	private boolean IS_ROTATE = false;
	/**
	 * 此变量表示直接读取型验证码的字符个数,默认值为4
	 */
	private int CHAR_NUMBER = 4;
	/**
	 * 此变量表示字符的大小，默认值为20
	 */
	private int FONT_SIZE = 20;
	/**
	 * 此变量表示验证码当前要画字符的横向偏移距离
	 */
	private int X_OFFSET = 0;
	/**
	 * 此变量表示验证码字符的纵向偏移距离
	 */
	private int Y_OFFSET = 0;
	/**
	 * 此变量表示验证码各字符之间的横向间隔
	 */
	private int X_DIST = 0;
	/**
	 * 默认的字体样式
	 */
	private Font MY_FONT = new Font("宋体", Font.BOLD, 33);
	/**
	 * 表示验证码的结果
	 */
	private String ANSWER;
	/**
	 * 设置字符个数是否可变
	 */
	private boolean SET_CHAR_NUMBER = true;
	/**
	 * 字体的颜色， 默认黄色
	 */
	private Color MY_FONT_COLOR = Color.YELLOW;
	/**
	 * 背景的颜色， 默认灰色
	 */
	private Color BACKGROUND_COLOR = Color.YELLOW;
	/**
	 * 字体颜色是否随机设置,默认不随机
	 */
	private boolean RANDOM_COLOR_ABLE = false;
	
}

enum MyColor{
	BLACK(1),BLUE(2),CYAN(3),DARK_GRAY(4),GRAY(5),GREEN(6),LIGHT_GRAY(7),MAGENTA(8),ORANGE(9),PINK(10),RED(11),WHITE(12),YELLOW(13);
	private MyColor(int value){
		
	}
}
