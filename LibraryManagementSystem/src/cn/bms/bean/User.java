package cn.bms.bean;

import java.util.Date;

//保存用户数据的bean
public class User {

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
}

