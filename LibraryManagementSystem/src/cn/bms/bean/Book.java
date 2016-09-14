package cn.bms.bean;

import java.util.Date;

public class Book {
	private String bkId; //图书ID
	private String bkNum; //条形码
	private String bkName; //书名
	private String auName; //作者
	private String auInfo; //作者简介
	private String bkPub; //出版社
//	private Date bkPubTime; //出版时间
	private String bkPubTime;  //String类型的出版时间
	private int bkPrice; //单价
	private String bkInfo; //书籍简介
	private String bkLang; //语种
	private String bkTag; //标签
	private String typeName; //类别
	private int bkPop; //人气
//	private Date bkInTime; //入库时间
	private String bkInTime;  //String类型的入库时间
	private int bkInv; //库存
	private String auId; //作者ID
	private String typeId; //图书类别ID
	
	
	public String getBkId() {
		return bkId;
	}
	public void setBkId(String bkId) {
		this.bkId = bkId;
	}
	public String getBkNum() {
		return bkNum;
	}
	public void setBkNum(String bkNum) {
		this.bkNum = bkNum;
	}
	public String getBkName() {
		return bkName;
	}
	public void setBkName(String bkName) {
		this.bkName = bkName;
	}
	public String getAuName() {
		return auName;
	}
	public void setAuName(String auName) {
		this.auName = auName;
	}
	public String getAuInfo() {
		return auInfo;
	}
	public void setAuInfo(String auInfo) {
		this.auInfo = auInfo;
	}
	public String getBkPub() {
		return bkPub;
	}
	public void setBkPub(String bkPub) {
		this.bkPub = bkPub;
	}
	public String getBkPubTime() {
		return bkPubTime;
	}
	public void setBkPubTime(String bkPubTime) {
		this.bkPubTime = bkPubTime;
	}
	public int getBkPrice() {
		return bkPrice;
	}
	public void setBkPrice(int bkPrice) {
		this.bkPrice = bkPrice;
	}
	public String getBkInfo() {
		return bkInfo;
	}
	public void setBkInfo(String bkInfo) {
		this.bkInfo = bkInfo;
	}
	public String getBkLang() {
		return bkLang;
	}
	public void setBkLang(String bkLang) {
		this.bkLang = bkLang;
	}
	public String getBkTag() {
		return bkTag;
	}
	public void setBkTag(String bkTag) {
		this.bkTag = bkTag;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getBkPop() {
		return bkPop;
	}
	public void setBkPop(int bkPop) {
		this.bkPop = bkPop;
	}
	public String getBkInTime() {
		return bkInTime;
	}
	public void setBkInTime(String bkInTime) {
		this.bkInTime = bkInTime;
	}
	public int getBkInv() {
		return bkInv;
	}
	public void setBkInv(int bkInv) {
		this.bkInv = bkInv;
	}
	public String getAuId() {
		return auId;
	}
	public void setAuId(String auId) {
		this.auId = auId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
}
