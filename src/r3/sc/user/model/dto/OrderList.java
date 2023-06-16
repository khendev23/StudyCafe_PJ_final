package r3.sc.user.model.dto;

import java.sql.Timestamp;

public class OrderList {

	private int orderNum;
	private String userId;
	private String userPhone;
	private String orderPassName;
	private String orderBevName;
	private int charge;
	private int nonUserCharge;
	private int bevCharge;
	private int totalPay;
	private Timestamp orderTime;
	
	
	public OrderList() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public OrderList(int orderNum, String userId, String userPhone, String orderPassName, String orderBevName,
			int charge, int nonUserCharge, int bevCharge, int totalPay, Timestamp orderTime) {
		super();
		this.orderNum = orderNum;
		this.userId = userId;
		this.userPhone = userPhone;
		this.orderPassName = orderPassName;
		this.orderBevName = orderBevName;
		this.charge = charge;
		this.nonUserCharge = nonUserCharge;
		this.bevCharge = bevCharge;
		this.totalPay = totalPay;
		this.orderTime = orderTime;
	}



	public int getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getOrderPassName() {
		return orderPassName;
	}


	public void setOrderPassName(String orderPassName) {
		this.orderPassName = orderPassName;
	}


	public String getOrderBevName() {
		return orderBevName;
	}


	public void setOrderBevName(String orderBevName) {
		this.orderBevName = orderBevName;
	}


	public int getCharge() {
		return charge;
	}


	public void setCharge(int charge) {
		this.charge = charge;
	}


	public int getNonUserCharge() {
		return nonUserCharge;
	}


	public void setNonUserCharge(int nonUserCharge) {
		this.nonUserCharge = nonUserCharge;
	}





	public int getBevCharge() {
		return bevCharge;
	}





	public void setBevCharge(int bevCharge) {
		this.bevCharge = bevCharge;
	}



	public String getUserPhone() {
		return userPhone;
	}



	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}




	public int getTotalPay() {
		return totalPay;
	}




	public void setTotalPay(int totalPay) {
		this.totalPay = totalPay;
	}



	public Timestamp getOrderTime() {
		return orderTime;
	}



	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	
}
