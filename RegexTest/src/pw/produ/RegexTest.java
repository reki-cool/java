package pw.produ;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexTest {
	public static void main(String[] args) throws PatternSyntaxException{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter pattern: ");
		String patternString = in.nextLine();  
		//将键盘输入的内容读取出来存入模式串变量patternString中
		
		Pattern pattern = Pattern.compile(patternString);
		//首先用表示正则表达式(模式)的字符串创建一个Pattern对象，这里编译模式时可以设置一个或者多个标志。
		
		while(true) {
			System.out.print("Enter string to match: ");
			String input = in.nextLine();
			if(input == null || input.equals("")) return;
			Matcher matcher = pattern.matcher(input);
			//然后从这个模式pattern中获得一个Matcher，并调用它的matches方法
			//看下面
			if(matcher.matches()){
				System.out.println("Match");
				int g = matcher.groupCount();
				System.out.println("g = "+g);
				//Matcher对象调用groupCount方法，可以获取模式中全部群组的数量,这个数量是实际的群组数量.不包含0对应的整个表达式
				if(g > 0){
					for(int i = 0; i < input.length(); i++) {
						//Matcher对象调用start方法，获取对应捕获组的开始位置，调用end方法，获取对应捕获组的结束位置
						/**
						 * start()和end()方法的参数都是捕获组的编号
						 * 返回的分别是这个编号对应的捕获组的开始位置和结束位置
						 */
						
						//Print any empty groups
						for(int j = 1; j <= g; j++) {
							if(i == matcher.start(j) && i == matcher.end(j))
								System.out.print("()");
						}
						
						//Print ( for non-empty groups starting here
						for(int j = 1; j <= g; j++) {
							if(i == matcher.start(j) && i != matcher.end(j))
								System.out.print("(");
						}
						
						System.out.print(input.charAt(i));
						/**
						 * charAt()方法    参数是字符串中某个字符的位置(从0开始)   返回的是位置对应的字符
						 */
						
						//Print ) for non-empty groups ending here
						for(int j = 1; j <= g; j++) {
							if(i+1 != matcher.start(j) && i+1 == matcher.end(j)) 
								System.out.print(")");
						}
						
					}
					System.out.println();
					
					//----------------------下面是根据每一个捕获组编号获得对应的字符串
					
					for(int i = 0; i <= g; i++) {
						System.out.println("编号   " + i + " = " +  matcher.group(i));
					}
				}
			}
			else
				System.out.println("No match");
		}
	}
}

//=================================测试结果===========================================
/*
Enter pattern: ((\w+)(\d+(\w*{5})))7788
Enter string to match: aa357788
Match
g = 4
((aa3)(5))()7788
编号   0 = aa357788
编号   1 = aa35
编号   2 = aa3
编号   3 = 5
编号   4 = 
Enter string to match:
*/ 