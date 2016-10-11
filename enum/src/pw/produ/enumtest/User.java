package pw.produ.enumtest;

public class User {

	private int id;
	private String name;
	//role表示身份
	private int role1;// int类型   (很久很久以前)
	private Role2 role2;// 对象类型(很久以前)
	private Role3 role3;// 枚举类型(现在)

	public void run() {
		User user = new User();
		user.id = 1;
		user.name = "智障";
		//user.role1 = Role1.BOSS;// 很久很久以前,可读性比较好,可以传入任意的类型(即在Role1中添加这种类型并使用).  [有漏洞,如果传入了100怎么办?]
		//user.role2 = Role2.BOSS;// 很久以前,可读性比较好,可以自己传入对象.  [缺点:Role2类中的内容书写麻烦!]
		user.role3 = Role3.BOSS;//枚举
	}
}

/**
 * @Description:现在的写法.  [枚举]  写法很简单
 */
enum Role3{//Role3的底层和Role2的底层是一模一样的,并且构造器也私有了!!!
	BOSS,HR,WORKER;// 这三个都是实例,直接按照Role3.BOSS的格式使用即可!!!
}

/**
 * @Description:在很久之前得写法.
 */
class Role2{
	private Role2(){}  // 构造器私有,意思也就是在外面不能使用new Role2()的形式来创建一个新的Role2的类对象
	public static final Role2 BOSS= new Role2();
	public static final Role2 HR= new Role2();
	public static final Role2 WORKER= new Role2();
	
}

/**
 * @Description:在很久很久之前的写法,用表示BOSS/HR/WORKER/...来表示0/1/2/...这些数值
 * 按照Role1.BOSS/Role1.HR/Role1.WORKER/...这样的形式来使用这些常量
 * 现在这种方式用的还是很多,因为大家比较容易接受!
 */
class Role1 {
	public static final int BOSS = 0;
	public static final int HR = 1;
	public static final int WORKER = 2;
}