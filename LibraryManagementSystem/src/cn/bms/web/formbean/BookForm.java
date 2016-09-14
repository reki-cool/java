package cn.bms.web.formbean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class BookForm {

	//从表单传过来的数据都是字符串
	private String bkNum; //条形码
	private String bkName; //书名
	private String auName; //作者
	private String auInfo; //作者简介
	private String bkPub; //出版社
	private String bkPubTime; //出版时间
	private String bkPrice; //单价
	private String bkInfo; //书籍简介
	private String bkLang; //语种
	private String bkTag; //标签
	private String typeName; //类别
//	private String bkPop; //人气
	private String bkInTime; //入库时间
	private String bkInv; //库存
	
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

	public String getBkPrice() {
		return bkPrice;
	}

	public void setBkPrice(String bkPrice) {
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

//	public String getBkPop() {
//		return bkPop;
//	}
//
//	public void setBkPop(String bkPop) {
//		this.bkPop = bkPop;
//	}

	public String getBkInTime() {
		return bkInTime;
	}

	public void setBkInTime(String bkInTime) {
		this.bkInTime = bkInTime;
	}

	public String getBkInv() {
		return bkInv;
	}

	public void setBkInv(String bkInv) {
		this.bkInv = bkInv;
	}



	private Map errors = new HashMap();

	public Map getErrors() {
		return errors;
	}

	public void setErrors(Map errors) {
		this.errors = errors;
	}
	
	//校验规则
	//1.除了作者简介、书籍简介、入库时间，人气，所有属性都不能为空

	public boolean validate(){
		boolean isOK = true;
		
		//1.图书编号
		if(this.bkNum == null || this.bkNum.trim().equals("")){
			isOK = false;
			errors.put("bkNum", "图书编号不能为空");
		}
		
		//2.图书书名
		if(this.bkName == null || this.bkName.trim().equals("")){
			isOK = false;
			errors.put("bkName", "图书书名不能为空");
		}
		
		//3.作者
		if(this.auName == null || this.auName.trim().equals("")){
			isOK = false;
			errors.put("auName", "作者不能为空");
		}
		
		//4.出版社
		if(this.bkPub == null || this.bkPub.trim().equals("")){
			isOK = false;
			errors.put("bkPub", "出版社不能为空");
		}
		
		//5.出版社时间不能为空
		if(this.bkPubTime == null || this.bkPubTime.trim().equals("")){
			isOK = false;
			errors.put("bkPubTime", "出版社时间不能为空");
		}
		else{
			try{
				 DateLocaleConverter dlc = new DateLocaleConverter(); //判断是否能转化为相应格式日期的类，不能转化的时候就会抛异常
				 dlc.convert(this.bkPubTime, "yyyy-MM-dd");
			 }catch(Exception e){
				 isOK = false;
				 errors.put("bkPubTime", "日期格式不对!请输入yyyy-MM-dd的日期格式");
			 }
		}
		
		//6.单价不能为空
		if(this.bkPrice == null || this.bkPrice.trim().equals("")){
			isOK = false;
			errors.put("bkPrice", "单价不能为空");
		}
		
		//7.语种不能为空
		if(this.bkLang == null || this.bkLang.trim().equals("")){
			isOK = false;
			errors.put("bkLang", "语种不能为空");
		}
		
		//8.标签不能为空
		if(this.bkTag == null || this.bkTag.trim().equals("")){
			isOK = false;
			errors.put("bkTag", "标签不能为空");
		}
		
		//9.类别不能为空
		if(this.typeName == null || this.typeName.trim().equals("")){
			isOK = false;
			errors.put("typeName", "书的类别不能为空");
		}
		
//		//10.人气不能为空
//		if(this.bkPop == null || this.bkPop.trim().equals("")){
//			isOK = false;
//			errors.put("bkPop", "人气不能为空");
//		}
		
//		//11.入库时间不能为空
//		if(this.bkInTime == null || this.bkInTime.trim().equals("")){
//			isOK = false;
//			errors.put("bkInTime", "入库时间不能为空");
//		}
//		else{
//			try{
//				 DateLocaleConverter dlc = new DateLocaleConverter(); //判断是否能转化为相应格式日期的类，不能转化的时候就会抛异常
//				 dlc.convert(this.bkInTime, "yyyy-MM-dd");
//			 }catch(Exception e){
//				 isOK = false;
//				 errors.put("bkInTime", "日期格式不对!");
//			 }
//		}
		
		//12.库存不能为空
		if(this.bkInv == null || this.bkInv.trim().equals("")){
			isOK = false;
			errors.put("bkInv", "库存不能为空");
		}
		
		return isOK;
	}
}
