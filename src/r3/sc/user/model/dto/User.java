package r3.sc.user.model.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
   private int userNo;// 회원번호
   private String userName; // 회원이름
   private String userId; // 아이디
   private String userPw; // 비밀번호
   private String userPhone; // 핸드폰 번호
   private Date userBirthday; // 생일
   private Timestamp userCheckIn; // 체크인 시간
   private Timestamp userCheckOut; // 체크아웃 시간
   
   public User() {
      super();
      // TODO Auto-generated constructor stub
   }
   
   public User(int userNo, String userName, String userId, String userPw, String userPhone, Date userBirthday,
         Timestamp userCheckIn, Timestamp userCheckOut) {
      super();
      this.userNo = userNo;
      this.userName = userName;
      this.userId = userId;
      this.userPw = userPw;
      this.userPhone = userPhone;
      this.userBirthday = userBirthday;
      this.userCheckIn = userCheckIn;
      this.userCheckOut = userCheckOut;
   }
   
   public int getUserNo() {
      return userNo;
   }
   public void setUserNo(int userNo) {
      this.userNo = userNo;
   }
   public String getUserName() {
      return userName;
   }
   public void setUserName(String userName) {
      this.userName = userName;
   }
   public String getUserId() {
      return userId;
   }
   public void setUserId(String userId) {
      this.userId = userId;
   }
   public String getUserPw() {
      return userPw;
   }
   public void setUserPw(String userPw) {
      this.userPw = userPw;
   }
   public String getUserPhone() {
      return userPhone;
   }
   public void setUserPhone(String userPhone) {
      this.userPhone = userPhone;
   }
   public Date getUserBirthday() {
      return userBirthday;
   }
   public void setUserBirthday(Date userBirthday) {
      this.userBirthday = userBirthday;
   }
   public Timestamp getUserCheckIn() {
      return userCheckIn;
   }
   public void setUserCheckIn(Timestamp userCheckIn) {
      this.userCheckIn = userCheckIn;
   }
   public Timestamp getUserCheckOut() {
      return userCheckOut;
   }
   public void setUserCheckOut(Timestamp userCheckOut) {
      this.userCheckOut = userCheckOut;
   }
   
   @Override
   public String toString() {
      return "User [userNo=" + userNo + ", userName=" + userName + ", userId=" + userId + ", userPw=" + userPw
            + ", userPhone=" + userPhone + ", userBirthday=" + userBirthday + ", userCheckIn=" + userCheckIn
            + ", userCheckOut=" + userCheckOut + "]";
   }
   
   
   
}