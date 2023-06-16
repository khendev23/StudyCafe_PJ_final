package r3.sc.user.model.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class UserDel extends User{

   private Timestamp userDelDate;
   
   public UserDel() {
      super();
   }

   public UserDel(int userNo, String userName, String userId, String userPw, String userPhone, Date userBirthday,
         Timestamp userCheckIn, Timestamp userCheckOut, Timestamp userDelDate) {
      super(userNo, userName, userId, userPw, userPhone, userBirthday, userCheckIn, userCheckOut);
      this.userDelDate = userDelDate;
   }

   public Timestamp getUserDelDate() {
      return userDelDate;
   }

   public void setUserDelDate(Timestamp userDelDate) {
      this.userDelDate = userDelDate;
   }

   @Override
   public String toString() {
      return "UserDel [userDelDate=" + userDelDate + "]";
   }

   
}