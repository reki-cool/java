package cn.bms.web.formbean;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import cn.bms.web.controller.*;
//处理表单的bean
public class RegisterForm {

	//因为表单传过来的数据都是字符串
	private String rdId; //读者Id
	private String rdCardId;  //借书证号,也是登录帐号
	private String rdPassword;  //密码
	private String rdName; //读者姓名
	private String rdSex;  //读者性别
	private String rdPhone;  //读者手机号
	private String rdCardNo;  //读者身份证号
	private String rdEmail;  //读者邮箱
	private String rdAddr;  //家庭住址
	private String rdCCdTime; //办卡日期
	
	public String getRdId() {
		return rdId;
	}

	public void setRdId(String rdId) {
		this.rdId = rdId;
	}

	public String getRdCardId() {
		return rdCardId;
	}

	public void setRdCardId(String rdCardId) {
		this.rdCardId = rdCardId;
	}

	public String getRdPassword() {
		return rdPassword;
	}

	public void setRdPassword(String rdPassword) {
		this.rdPassword = rdPassword;
	}

	public String getRdName() {
		return rdName;
	}

	public void setRdName(String rdName) {
		this.rdName = rdName;
	}

	public String getRdSex() {
		return rdSex;
	}

	public void setRdSex(String rdSex) {
		this.rdSex = rdSex;
	}

	public String getRdPhone() {
		return rdPhone;
	}

	public void setRdPhone(String rdPhone) {
		this.rdPhone = rdPhone;
	}

	public String getRdCardNo() {
		return rdCardNo;
	}

	public void setRdCardNo(String rdCardNo) {
		this.rdCardNo = rdCardNo;
	}

	public String getRdEmail() {
		return rdEmail;
	}

	public void setRdEmail(String rdEmail) {
		this.rdEmail = rdEmail;
	}

	public String getRdAddr() {
		return rdAddr;
	}

	public void setRdAddr(String rdAddr) {
		this.rdAddr = rdAddr;
	}

	public String getRdCCdTime() {
		return rdCCdTime;
	}

	public void setRdCCdTime(String rdCCdTime) {
		this.rdCCdTime = rdCCdTime;
	}

	
	
	private String verification;
	private static String verification2;

	public String getVerification2() {
		return verification2;
	}

	public void setVerification2(String verification2) {
		this.verification2 = verification2;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	private Map errors = new HashMap();

	public Map getErrors() {
		return errors;
	}

	public void setErrors(Map errors) {
		this.errors = errors;
	}

	

	//校验规则
	public boolean validate(){
		boolean isOK = true;
		
		//1.借书证号不能为空
		 if(this.rdCardId == null || this.rdCardId.trim().equals("")){
			 isOK = false;
			 errors.put("rdCardId", "借书证号不能为空!");
		 }
//		 else{
//			 if(!this.rdCardId.matches("[A-Za-z]{3,8}")){
//				 isOK = false;
//				 errors.put("rdCardId", "请输入3-8位的英文字母, 不能包含空格字符!");
//			 }
//		 }
		 
		 if(this.rdName == null || this.rdName.trim().equals("")){
			 isOK = false;
			 errors.put("rdName", "姓名不能为空!");
		 }
		 
		 if(this.rdPhone == null || this.rdPhone.trim().equals("")){
			 isOK = false;
			 errors.put("rdPhone", "手机号不能为空!");
		 }
		 
		 if(this.rdCardNo == null || this.rdCardNo.trim().equals("")){
			 isOK = false;
			 errors.put("rdCardNo", "身份证号不能为空!");
		 }
		 
		 if(this.rdPassword == null || this.rdPassword.trim().equals("")){
			 isOK = false;
			 errors.put("rdPassword", "密码不能为空!");
		 }
//		 else{
//			 if(!this.rdPassword.matches("\\d{3,8}")){
//				 isOK = false;
//				 errors.put("rdPassword", "请输入3-8位的数字, 不能包含空格字符!");
//			 }
//		 }
		
//		//3.确认密码不能为空，并且要和前面输入的一致
//		 if(this.password2 == null || this.password2.trim().equals("")){
//			 isOK = false;
//			 errors.put("password2", "密码不能为空!");
//		 }
//		 else{
//			 if(!this.password2.equals(this.password)){
//				 isOK = false;
//				 errors.put("password2", "两次输入的密码不一致!");
//			 }
//		 }
		 
//		//4.电子邮箱不能为空
//		 if(this.rdEmail == null || this.rdEmail.trim().equals("")){
//			 isOK = false;
//			 errors.put("rdEmail", "邮箱不能为空");
//		 }
//		 else{
//			 //邮箱一般的格式为：aaa@sina.com   aaa@sina.com.cn   936167732@qq.com
//			 // \\w+@\\w+(\\.\\w+)+     \\w:可以输入任意字符     +:表示出现1次或多次     \\.:转义字符'.'
//			 if(!this.email.matches("\\w+@\\w+(\\.\\w+)+")){
//				 isOK = false;
//				 errors.put("email", "邮箱格式不对!");
//			 }
//		 }
		 
//		//5.生日可以为空，不为空时，必须要是一个日期
//		 if(this.birthday != null && !this.birthday.trim().equals("")){
//			 try{
//				 DateLocaleConverter dlc = new DateLocaleConverter(); //判断是否能转化为相应格式日期的类，不能转化的时候就会抛异常
//				 dlc.convert(this.birthday, "yyyy-MM-dd");
//			 }catch(Exception e){
//				 isOK = false;
//				 errors.put("birthday", "日期格式不对!");
//			 }
//		 }
//		 
//		//6.昵称不可以为空，并且要是3-6位汉字
//		 if(this.nickname == null || this.nickname.trim().equals("")){
//			 isOK = false;
//			 errors.put("nickname", "昵称不能为空!");
//		 }
//		 else{
//			 if(!this.nickname.matches("[\u4e00-\u9fa5]{3,6}")){
//				 isOK = false;
//				 errors.put("nickname", "请输入3-6位的汉字，不能包含空格字符!");
//			 }
//		 }
//		 
//		 //7.判断用户输入的验证码是否和系统生成的一致
//		 if(this.verification.equals("") || this.verification == null){
//			 isOK = false;
//			 errors.put("verification", "请输入验证码!");
//		 }
//		 else{
//			 if(!this.verification.equals(this.verification2)){
//				 isOK = false;
//				 errors.put("verification", "验证码输入错误!");
//			 }
//		 }
		return isOK;
	}
}

