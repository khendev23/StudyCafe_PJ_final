package r3.sc.user.model.dto;

import java.sql.Timestamp;

public class UserLog {

	private int userLogNum;
	private String userLogName;
	private String userLogId;
	private String userLogPhone;
	private Timestamp userLogCheckIn;
	private Timestamp userLogCheckOut;
	
	
	public UserLog() {
		super();
	}


	public UserLog(int userLogNum, String userLogName, String userLogId, String userLogPhone, Timestamp userLogCheckIn,
			Timestamp userLogCheckOut) {
		super();
		this.userLogNum = userLogNum;
		this.userLogName = userLogName;
		this.userLogId = userLogId;
		this.userLogPhone = userLogPhone;
		this.userLogCheckIn = userLogCheckIn;
		this.userLogCheckOut = userLogCheckOut;
	}


	public int getUserLogNum() {
		return userLogNum;
	}


	public void setUserLogNum(int userLogNum) {
		this.userLogNum = userLogNum;
	}


	public String getUserLogName() {
		return userLogName;
	}


	public void setUserLogName(String userLogName) {
		this.userLogName = userLogName;
	}


	public String getUserLogId() {
		return userLogId;
	}


	public void setUserLogId(String userLogId) {
		this.userLogId = userLogId;
	}


	public String getUserLogPhone() {
		return userLogPhone;
	}


	public void setUserLogPhone(String userLogPhone) {
		this.userLogPhone = userLogPhone;
	}


	public Timestamp getUserLogCheckIn() {
		return userLogCheckIn;
	}


	public void setUserLogCheckIn(Timestamp userLogCheckIn) {
		this.userLogCheckIn = userLogCheckIn;
	}


	public Timestamp getUserLogCheckOut() {
		return userLogCheckOut;
	}


	public void setUserLogCheckOut(Timestamp userLogCheckOut) {
		this.userLogCheckOut = userLogCheckOut;
	}
	
	
	
	
	
}
