package r3.sc.user.view;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import r3.sc.user.controller.UserController;
import r3.sc.user.model.dto.OrderList;
import r3.sc.user.model.dto.User;
import r3.sc.user.model.dto.UserDel;
import r3.sc.user.model.dto.UserLog;

public class UserMainMenu {

    private Scanner sc = new Scanner(System.in);

    private UserController userController = new UserController();

    /**
     * @윤아
     * ++++ 메인메뉴 ++++
     * */
    public void userMenu() {
    			
        String menu = "*------------ 스터디카페 메인 ------------*\n"
                    + "*------- 1인 1음료 주문은 필수 입니당 ------*\n"
                    + " 1. 회원 가입 \n"
                    + " 2. 회원으로 이용 \n"
                    + " 3. 비회원으로 이용 \n"
                    + " 4. 프로그램 종료 \n"
                    + " \n"
                    + " 5. 관리자 로그인 \n"
                    + "*--------------------------------------*\n"
                    + "* 번호 입력 --> ";


        while(true) {
            System.out.print(menu);
            String choice = sc.next();
            
            int result = 0;
            String userId = null;
            String userPw = null;
            User user = null;

            switch(choice) {
            case "1" : // 회원가입 (윤아)
                user = inputUser(); // 사용자가 직접 정보를 입력하는 메소드
                result = userController.insertUser(user); 
                displayResult("회원가입", result); // 회원가입 성공 / 실패 출력 메소드
                break;
                
            case "2" : // 회원으로 이용시 만들기 (경빈)
                userId = inputId();// 아이디 입력
                userPw = inputPw(); // 비밀번호 입력
                user = userController.findById(userId);
                result = userController.userLogin(userId, userPw); // 아이디 비밀번호 회원정보 조회 후 있으면 로그인
                displayResult("회원로그인", result); // 로그인 성공 / 실패 출력 메소드
                if(result > 0)
                displayUse(user); // 로그인 성공시 1번 서브메뉴 ( 회원 전용 메뉴 )
                break; 
                
            case "3" : // 비회원으로 이용 (성근)
                // 폰번호, 아이디, 이름 입력해서 user로 가져옴
                user = inputNonUser();
                // 비회원 메뉴 메뉴로
                nonUserMenu(user);
                break;
            	
            case "4" : return; // 프로그램 종료
            
            case "5" : 
            	userId = inputId();// 아이디 입력
                userPw = inputPw(); // 비밀번호 입력
                result = userController.managerLogin(userId, userPw); // 관리자 로그인 후 관리자 메뉴로 진입
                displayResult("관리자 로그인", result); // 로그인 성공 / 실패 메소드
                if(result > 0) {managerMenu();} // 로그인 성공시 관리자 메뉴
            	break;
            	
            default : System.out.println("********** 잘못 입력하셨습니다. **********");
            }
        }
    }

    /**
     * 메인
 	 * @윤아
 	 * 사용자가 입력하여 회원가입하는 메소드
 	 */
 	private User inputUser() {

	     User user = new User();
	     String pw;
	     String pw1;
	
	     System.out.println("* 회원 정보를 입력하세요. -----------------");
	     System.out.println("* 이름 입력 ----------------------------");
	     user.setUserName(sc.next());
	     
	     user.setUserId(checkIdDuplicate()); // 아이디 중복 조회 메소드
	
	     // 비밀번호 일치 / 불일치 확인
	     do {
	         System.out.println("* 비밀번호 입력 -------------------------");
	          pw = sc.next();
	         System.out.println("* 비밀번호 재입력 -----------------------");
	          pw1 = sc.next();
	
	         if (!pw.equals(pw1)) {
	             System.out.println("!! 비밀번호 불일치 . 다시 시도하세요. !!");
	         }

	     } while (!pw.equals(pw1));
	     
	     user.setUserPw(pw1); // 비밀번호 일치 확인 후 set.
	    
	     user.setUserPhone(checkphoneDuplicate());  // 휴대폰 번호 중복 조회 메소드
	     System.out.println("* 생년월일 입력 (yyyymmdd) --------------");
	     String _birthday = sc.next();
	     LocalDate localDate = LocalDate.parse(_birthday, DateTimeFormatter.ofPattern("yyyyMMdd"));
	     Date birthday = java.sql.Date.valueOf(localDate);
	     user.setUserBirthday(birthday);
	
	     return user;
	 }
 	
 	/**
     * @윤아
     * (회원가입) 아이디 중복 조회 메소드
     */
    private String checkIdDuplicate() {
        String id = null;
        do {
            if(id != null)
                System.out.printf("!! [%s]는 이미 사용중입니다. !!%n", id);
            System.out.println("* 아이디 입력 ---------------------*");
            id = sc.next();
        } while ( userController.findById(id) != null);
        return id;
    }
 	
 	/**
 	 * 메인
 	 * 로그인 성공/ 실패 여부 
 	 * */
 	private void displayResult(String name, int result) {
    	if(result > 0)
			System.out.println("* --> " + name + " 성공!");
		else 
			System.out.println("* --> " + name + " 실패!");
	}
 	
		/* @경빈
	     * 아이디 입력 메소드
	     * @return
	     */
	   private String inputId() {
		   System.out.println("* 아이디 입력 --------------------------");
	       return sc.next();
	   }
	    /* @경빈
	     * 비밀번호 입력 메소드
	     * @return
	     */
	   private String inputPw() {
		   System.out.println("* 비밀번호 입력 -------------------------");
	       return sc.next();
	   }
   
	   /**
	     * 메인
	     * @경빈
	     * @param userId
	     */
	    private void displayUse(User user) {
	       while(true) {
	          
	          String disPlayUser= ("*-------------환영합니당---------------*\n"
	                + "1. 좌석선택\n"
	                + "2. 퇴실하기\n"
	                + "3. 마이페이지\n"
	                + "4. 로그아웃\n"
	                + "* 번호 입력 --> ");
	          
	          int result = 0;
	          
	          String userId = user.getUserId();
	          
	          System.out.print(disPlayUser);
	          String choice = sc.next();
	          UserLog userLog = null;
	          
	          switch (choice) {
	          case "1" : //@성근
	             choiceDayPass(user);
	             break;
	          case "2" : 
	             userLog = userController.findUserSituation(userId);
	             if(userLog == null || ((userLog.getUserLogCheckOut() != null) && (userLog.getUserLogCheckIn() != null))) {
	                System.out.println("* 입실 후 이용해주세요");
	                
	             }else if (userLog.getUserLogCheckOut() == null && userLog.getUserLogCheckIn() != null) {
	                System.out.println("* 퇴실등록이 완료되었습니다!");
	             }
	             result = userController.userCheckOut(user); // 체크아웃등록
	             break;
	          case "3" : myPage(userId);
	          break;
	          case "4" : return;
	          
	          default: System.out.println("********** 잘못 입력하셨습니다. *********");
	          
	          }
	          break;
	       }
	   }
   
    /**
     * 메인
     * @성근
     * 당일권 선택 메뉴
     */
	    private void choiceDayPass(User user) {

	        while(true) {
	           
	           System.out.println("******************************* 당일권 선택 **********************************");
	           System.out.printf("%-3s %-10s    %-10s    %-10s\n", "번호", "당일권", "회원요금", "비회원요금");
	           System.out.printf("%-3s %-10s    %-10s    %-10s\n%-3s %-10s    %-10s    %-10s\n%-3s %-10s    %-10s    %-10s\n%-3s %-10s    %-10s    %-10s\n%-3s %-10s"
	                 , "1", "2시간 당일권", "5000원", "7000원", "2", "3시간 당일권", "6000원", "8000원", "3", "6시간 당일권", "9000원", "11000원", "4", "12시간 당일권", "13000원", "15000원"
	                 , "5", "돌아가기");
	           
	           System.out.println("**********************************************************************************************");
	           System.out.print("* 번호 입력 --> ");
	           String choice = sc.next();
	           
	           int result = 0;
	           OrderList orderlist = null;
	           int point = 0;
	           
	           switch(choice) {
	           case "1" : 
	              point = 1;
	              System.out.println("2시간 당일권 선택하셨습니다.");
	              break;
	           case "2" : 
	              point = 2;
	              System.out.println("3시간 당일권 선택하셨습니다.");
	              break;
	           case "3" :
	              point = 3;
	              System.out.println("6시간 당일권 선택하셨습니다.");
	              break;
	           case "4" :
	              point = 4;
	              System.out.println("12시간 당일권 선택하셨습니다.");
	              break;
	           case "0" : return;
	           
	           default : System.out.println("********** 잘못 입력하셨습니다. *********");
	           }
	           
	           if(choice.equals("1")||choice.equals("2")||choice.equals("3")||choice.equals("4")) {
	              orderlist = inputDayPassOrderList(user, point);
	              result = userController.orderDayPass(orderlist);
	              choiceBev(orderlist, user);
	              break;
	           }
	        }
	           
	    }
    
    /**
     * 메인
     * @성근 
     * 주문리스트 당일권 데이터 추가 메소드
     */
    private OrderList inputDayPassOrderList(User user, int point) {
		OrderList orderlist = new OrderList();
		
		orderlist.setUserId(user.getUserId());
		orderlist.setUserPhone(user.getUserPhone());
		
		if(point == 1) {
			orderlist.setOrderPassName("2시간 당일권");
			orderlist.setCharge(5000);
			orderlist.setNonUserCharge(7000);
		} else if (point == 2) {
			orderlist.setOrderPassName("3시간 당일권");
			orderlist.setCharge(6000);
			orderlist.setNonUserCharge(8000);
		} else if (point == 3) {
			orderlist.setOrderPassName("6시간 당일권");
			orderlist.setCharge(9000);
			orderlist.setNonUserCharge(11000);
		} else {
			orderlist.setOrderPassName("12시간 당일권");
			orderlist.setCharge(13000);
			orderlist.setNonUserCharge(15000);
		}
		return orderlist;
	}
    
    /**
     * @윤아, @성근
     * 음료 선택 메소드
     * */
    private void choiceBev(OrderList orderlist, User user) {
        
        while(true) {
        
          System.out.println("******************************* 음료 선택 **********************************");
          System.out.printf("%-5s %-10s %-10s %-20s%n", "번호", "음료","회원요금","비회원요금");
          System.out.printf("%-5s %-10s %-10s %-20s%n%-5s %-10s %-10s %-20s%n%-5s %-10s %-10s %-20s%n%-5s %-10s %-10s %-20s%n"
                   ,"5", "아메리카노", " 2000원"," 2000원", "6", "아이스티", " 3000원"," 3000원", "7", "쿨커피믹스", " 3000원", " 3000원"
                   , "8", "초코라떼", " 4000원"," 4000원");
        
          System.out.println("**************************************************************************");
          System.out.print("* 번호 입력 --> ");
           
          String choice = sc.next();
          
          int result = 0;
          int point = 0;
          
          switch(choice) {
          case "5" : 
             point = 5;
             System.out.println("아메리카노 선택하셨습니다.");
             break;
          case "6" : 
             point = 6;
             System.out.println("아이스티 선택하셨습니다.");
             break;
          case "7" :
             point = 7;
             System.out.println("쿨커피믹스 선택하셨습니다.");
             break;
          case "8" :
             point = 8;
             System.out.println("초코라떼 선택하셨습니다.");
             break;
          case "0" : return;
          
          default : System.out.println("********** 잘못 입력하셨습니다. *********");
          
          }
          
          if(choice.equals("5")||choice.equals("6")||choice.equals("7")||choice.equals("8")) {
             
             inputBevOrderList(orderlist, point);
             result = userController.orderBev(orderlist);
             bill(orderlist, user);
             break;
          }
        }
     }
    
    /**
     * 메인
     * @성근 
     * 주문리스트 음료 추가 메소드
     */
	private void inputBevOrderList(OrderList orderlist, int point) {
		
		if(point ==5) {
			orderlist.setOrderBevName("아메리카노");
			orderlist.setBevCharge(2000);
			
		} else if(point ==6) {
			orderlist.setOrderBevName("아이스티");
			orderlist.setBevCharge(3000);
			
		} else if(point ==7) {
			orderlist.setOrderBevName("쿨커피믹스");
			orderlist.setBevCharge(3000);
			
		} else {
			orderlist.setOrderBevName("초코라떼");
			orderlist.setBevCharge(4000);
			
		}
	}
    

    /**
     * 메인
     * @성근
     * 당일권, 음료 고른 후 결제 메뉴
     */
	private void bill(OrderList orderlist, User user) {
	       
	       int result = 0;
	       int result2 = 0;
	       int result3 = 0;
	       
	      while(true) {
	    	  
	    	  System.out.println("********************************** 결제 ******************************************");
	    	  
	    	  if(orderlist.getUserId().contains("Non")) {
	    		  
	    		  System.out.printf("당일권 : %-10s/ 가격 : %-10s\n음료메뉴 : %-10s / 가격 : %-10s\n총금액 : %-10s\n", 
	    				  orderlist.getOrderPassName(), orderlist.getNonUserCharge(), 
	    				  orderlist.getOrderBevName(), orderlist.getBevCharge(), (orderlist.getNonUserCharge()+orderlist.getBevCharge()));
	    	  } else {
	    		  System.out.printf("당일권 : %-10s/ 가격 : %-10s\n음료메뉴 : %-10s / 가격 : %-10s\n총금액 : %-10s\n", 
	    				  orderlist.getOrderPassName(), orderlist.getCharge(), 
	    				  orderlist.getOrderBevName(), orderlist.getBevCharge(), (orderlist.getCharge()+orderlist.getBevCharge()));
	    	  }
	    	  
	    	  System.out.println("*******************************************************************************************");
	    	  System.out.println("> 결제 하시겠습니까?(Y/N)");
	    	  String pay = sc.next();
	    	  
	    	  switch(pay) {
	    	  case "Y" : 
//	          // 유저테이블에 생성
//	          result = userController.insertNonUser(user);
	    		  // orderlist의 지불금액 컬럼에 주문시간, 총금액 등록
	    		  result = userController.updateTotalPay(orderlist);
	    		  // user_log 테이블에 생성(check in 시간 포함, check out은 null)
	    		  result2 = userController.insertUserLog(user);
	    		  break;
	    	  case "N" : 
	    		  // orderlist에서 해당 회원 삭제
	    		  result = userController.deleteOrderList(orderlist);
	    		  break;
	    	  default : System.out.println("* 잘못 입력하셨습니다.");
	    	  }
	    	  
	    	  break;
	    	  
	      }
	       
	       
	   }

	
    /**
     * main
     * @경빈
     * 마이페이지
     */
    private void myPage(String userId) {
       String displayMypage =("*------------- 마이페이지 ---------------*\n"
                            + "1. 회원정보 수정\n"
                            + "2. 회원 탈퇴\n"
                            + "3. 이용 정보 조회\n"
                            + "4. 돌아가기\n"
                         + "* 번호 입력 --> ");
       System.out.println(displayMypage);
       int result = 0;
       String choice = sc.next();
       User user = null;
       UserLog userLog = null;
       
       switch(choice) {
       
       case "1" : 
          displayUpdate(userId);
          break;
       case "2" :
          displaydelete(userId);
          break;
       case "3" :
    	    userLog = userController.findUserSituation(userId);
            if(userLog != null)  
               displayUserInformation(userLog); // 로그인한 회원 이용정보 출력
            else
               System.out.println("* 이용 정보가 없습니다.");
       case "4" : 
          return;
          
       }
    }
    
    /**
     * 메인
     * @경빈
     * 회원으로 이용시 이용정보 조회 
     */
    private void displayUserInformation(UserLog userLog) {
    	 System.out.println("*----------------------------------------*");
         System.out.println("* 회원번호 : " + userLog.getUserLogId());
         System.out.println("* 이름 : " + userLog.getUserLogName());
         System.out.println("* 아이디 : " + userLog.getUserLogId());
         System.out.println("* 휴대폰번호 : " + userLog.getUserLogPhone());
         System.out.println("* 입실시간 : " + userLog.getUserLogCheckIn());
         System.out.println("* 퇴실시간 : " + userLog.getUserLogCheckOut());
   }
    
    /**
     * @경빈
     * 회원 정보 수정
     */
    private void displayUpdate(String userId) {
       String displayUpdate = "*---------- 회원 정보 수정 ------------*\n"
    		   				+ "1. 이름수정\n"
    		   				+ "2. 비밀번호수정\n"
    		   				+ "* 번호 입력 --> ";
       
       
       int result = 0;
       String colName = null;
       Object newValue = null;
       System.out.println(displayUpdate);
       
       String choice = sc.next();
       
       
       switch(choice) {
       
       case "1" : 
          System.out.println("* 변경하실 이름 :");
          colName = "user_name";
          newValue = sc.next();
          break;
       case "2" :
          System.out.println("* 수정하실 비밀번호 : ");
          colName = "user_pw";
          newValue = sc.next();
          break;
       }
       	 result = userController.updateUser(userId, colName, newValue);
         System.out.println(result > 0 ? "* 회원정보 수정 성공!" : "* 회원정보 수정 실패!");
    }
    
    /**
     * 메인
     * @경빈
     * 회원 탈퇴 메소드
     */
    private void displaydelete(String userId) {
       System.out.println("* 정말 탈퇴하시겠습니까? (Y/N) : \n"
                    + "* 입력 : ");
       String choice = sc.next();
       int result = 0;
       switch (choice) {
       case "Y" : 
          result = userController.deleteUser(userId);
          System.out.println("* 회원탈퇴가 완료되었습니다.");
          break;
       case "N" :
          return;
       
       default: System.out.println("* (Y/N)중 입력해주세요.");
       }
       
    }
    	

    /**
     * @성근
      * 비회원 접속 메소드
      */
    private User inputNonUser() {
       User user = new User();
       
       user.setUserName("비회원");
       user.setUserId("Non-User");
       user.setUserPw("-");
       
       System.out.println("* 휴대폰 번호 입력 ---------------------*");
         String phone = sc.next();
       user.setUserPhone(phone);
       
       return user;
    }
	
	/**
	 * 메인
	 * @성근
	 * 휴대폰번호 중복 검사 메소드
	 * */
	private String checkphoneDuplicate() {
        String phone = null;
        do {
            if(phone != null)
                System.out.printf("!! [%s]는 이미 사용중입니다. !!%n", phone);
            System.out.println("* 휴대폰 번호 입력 ----------------------");
            phone = sc.next();
        } while ( userController.findByPhoneNo(phone) != null);
        return phone;
    }

	 /**
	  * 메인
     * 비회원 메뉴 @성근
     */
	 /**
     * 메인
     * 비회원 메뉴 @성근
     */
    private void nonUserMenu(User user) {
       
       User searchPhone = null;
       int result = 0;
       UserLog userLog = null;
       
       while(true) {
          
          String menu = "*** 비회원 메뉴 ***\n"
                + "1. 좌석 선택\n"
                + "2. 퇴실\n"
                + "> 번호 입력 : ";
          
          String phone = user.getUserPhone();
          
          System.out.print(menu);
          String choice = sc.next();
          
          switch(choice) {
          case "1" : 
             choiceDayPass(user);
             break;
          case "2" : 
             // 퇴실시 로그테이블에서 가장 최근 해당 비회원의 정보 중 체크인 정보가 있다면 해당 비회원의 번호로 이용내역 조회 정보를 userLog에 담고
             userLog = userController.findNonUserSituationByPhone(user);
             // userLog가 null이라면(입실 전에 퇴실 입력시) 또는 전에 완료된 이용정보가 남아있을 시
             if(userLog ==null || (userLog.getUserLogCheckIn() != null && userLog.getUserLogCheckOut() !=null)) {
                System.out.println("* 입실 후 이용해주세요");
             }
             // 체크인이 널이 아니고 또는 체크아웃이 널 이라면 퇴실 진행(로그 테이블에 퇴실 입력)
             else if(userLog.getUserLogCheckIn() != null && userLog.getUserLogCheckOut() == null) {
                result = userController.userCheckOut(user);
                System.out.println("* 퇴실처리 되었습니다.");
             }
             
             break;
          default : System.out.println("잘못 입력하셨습니다.");   
          }
          
          break;
       }
      
   }
	
	   /*
	    * @윤아
	    * 관리자 로그인시 관리자용 서브메뉴
	    */
	  private void managerMenu() {
		  					  
	  String managerMenu  = "*------------ 관리자용 서비스 ------------*\n"
	                      + " 1. 회원 전체 조회 \n"
	                      + " 2. 회원 아이디로 조회 \n"
	                      + " 3. 회원 이름으로 조회 \n"
	                      + " 4. 탈퇴 회원 조회 \n"
	                      + " 5. 이용자 조회 \n"
	                      + " 6. 현재 매출 조회 \n"
	                      + " \n"
	                      + " 0. 메인으로 돌아가기 \n"
	                      + "*-------------------------------------*\n"
	                      + "* 번호 입력 --> ";
	
	
	      while(true) {
	
	          System.out.print(managerMenu);
	          String choice = sc.next();
	
	          List<User> userList = null;
	          User user = null;
	          int result = 0;
	          String id = null;
	          String name = null;
	          String pName = null; // 상품 이름
	
	          switch(choice) {
	          case "1" : // 1. 회원 전체 조회
	              userList = userController.findAllUser(); // 회원 전체 조회 메소드
	              displayUserList(userList); // UserList를 출력하는 메소드 ( n건 ) - 전체
	              break;
	          case "2" : // 회원 아이디로 조회
	             id = inputId();
	             user = userController.findById(id);
	             displayUser(user); // 1행의 User정보를 출력하는 메소드
	             break;
	          case "3" :  // 회원 이름으로 조회
	             name = inputName();
	             userList = userController.findByName(name);
	             displayUserList(userList); // UserList를 출력하는 메소드 ( n건 ) - 전체
	             break;
	          case "4" : // 탈퇴 회원 조회
	             displayUserDelList(userController.findAllUserDel()); // 탈퇴회원 조회, 출력 메소드
	             break;
	          case "5" : // 이용자 조회
	        	  searchUser();
	        	  break;
	          case "6" : // 총 매출 조회
	        	  TotalSalesList(userController.findTotalSales()); // 총 매출 출력, 조회 메소드
	             break;
	          case "0" : return;
	          default : System.out.println("************ 잘못 입력하셨습니다. ***********");
	          }
	      }
	  }
  
	  /*
	   * @윤아
	   * (관리자용) 1. 회원 전체 조회 메소드 , 3. 회원 이름으로 조회 메소드
	   */
	   
	  private void displayUserList(List<User> userList) {
	      if(userList.isEmpty()) {
	          System.out.println("*********** 조회된 결과가 없습니다. ***********");
	      } else {
	          System.out.println("*----------------------------------------------------------------------------------------------------------------------*");
	
	          System.out.printf("%-10s    %-10s    %-10s    %-10s    %-10s    %-10s\n"
	                  , "회원번호", "회원이름", "회원아이디", "회원비밀번호","휴대폰번호","회원생일");
	          System.out.println("*----------------------------------------------------------------------------------------------------------------------*");
	          for( User u : userList) {
	              System.out.printf("%-10s    %-10s    %-10s    %-10s    %-10s    %-10s\n",
	                      u.getUserNo(), u.getUserName(), u.getUserId(), u.getUserPw(),
	                      u.getUserPhone(), u.getUserBirthday());
	          }
	
	          System.out.println("*----------------------------------------------------------------------------------------------------------------------*");
	      }
	  }
  
	  /**
	   * @윤아
	   * (관리자용) 검색할 이름 입력 메소드
	   * */
	   private String inputName() {
	      System.out.println("* 이름 입력 ----------------------------");
	      return sc.next();
	   }
	  
  
	  /**
	   * @윤아
	   * (관리자용) 아이디로 조회 메소드
	   * - 1건의 조회 결과 출력 메소드
	   * */
		private void displayUser(User user) {
			
			if(user == null) {
			     System.out.println("*********** 조회된 회원이 없습니다. ***********");
			} else {
			   System.out.println("*----------------------------------------*");
			   System.out.println("* 회원번호 : " + user.getUserNo());
			   System.out.println("* 이름 : " + user.getUserName());
			   System.out.println("* 아이디 : " + user.getUserId());
			   System.out.println("* 휴대폰번호 : " + user.getUserPhone());
			   System.out.println("* 생년월일 : " + user.getUserBirthday());
		
			}
			   System.out.println("*----------------------------------------*");
		}
		
	 /**
	   * @윤아
	   * (관리자용) n행의 탈퇴회원정보를 출력하는 메소드
	   * */
	  private void displayUserDelList(List<UserDel> list) {
	     if(list.isEmpty()) {
	         System.out.println("********* 조회된 탈퇴회원이 없습니다. *********");
	     } else {
	        // 탈퇴 멤버가 있는 경우
	        System.out.println("*------------------------------------------------------------------------------------------------------------------*");

	            System.out.printf("%-10s    %-10s    %-10s    %-15s    %-15s    %-15s\n"
	                    , "회원번호", "회원이름", "회원아이디", "휴대폰번호","회원생일", "회원탈퇴 시각");
	            System.out.println("*------------------------------------------------------------------------------------------------------------------*");
	            for(UserDel u : list ) {
	                System.out.printf("%-10d    %-10s    %-10s    %-20s    %-20s    %-20s\n",
	                        u.getUserNo(), u.getUserName(), u.getUserId(), 
	                        u.getUserPhone(), u.getUserBirthday(), new SimpleDateFormat("yy-MM-dd HH:mm").format(u.getUserDelDate()));
	            }
	            System.out.println("*------------------------------------------------------------------------------------------------------------------*");
	     }
	  }
	  

	  /**
	   * @성근
	   * 관리자모드 - 이용자 조회 메뉴
	   */
	  private void searchUser() {
		  
		  String menu = "*------------ 이용내역 조회 ------------*\n"
				  	+ " 1. 이용내역 전체 조회 \n"
				  	+ " 2. 현재 이용자 이용내역 조회 \n"
				  	+ " 3. 회원 아이디로 이용내역 조회 \n"
				  	+ "\n"
				  	+ " 0. 관리자 메뉴로 돌아가기 \n"
				  	+  "*-------------------------------------*\n"
				  	+ "* 번호 입력 --> ";
		  
		  System.out.print(menu);
		  
		  String input = sc.next();
		  
		  List<UserLog> userList = null;
		  UserLog userLog = null;
		  String id = null;
		  
		  switch(input) {
		  
		  case "1" : 
			  userList = userController.findAllUserSituation(); // 이용내역 전체 조회 메소드
	          displayUserSituationList(userList); // UserList를 출력하는 메소드 ( n건 ) - 전체
			  break;
		  case "2" :
			  userList = userController.findPresentUserSituation(); // 현재 이용자 이용내역 조회 메소드
	          displayUserSituationList(userList); // UserList를 출력하는 메소드 ( n건 ) - 전체
			  break;
		  case "3" : 
			  id = inputId();
	          userLog = userController.findUserSituationById(id);
	          displayUserSituation(userLog); // 1행의 User정보를 출력하는 메소드
	          break;
		  case "0" : break;
		  default : System.out.println("* 잘못 입력하셨습니다.");
		  
		  }
	  }
	
	  /**
	   * 메인
	   * @윤아
	   * (관리자용) 총 매출 조회 메소드
	   * */
	  private void TotalSalesList(OrderList order) {
		if(order == null) {
			System.out.println("************ 조회된 내역이 없습니다. ***********");
		} else {
			System.out.println("*------------ 현재 총 매출 -------------*");
			System.out.printf("* ------> 총 %s원 입니다.\n", order.getTotalPay());
			System.out.println("*-------------------------------------*");
		}
		  
	  }
  
	  /**
	   * 메인
	   * @성근
	   * (관리자용) 이용자 정보 조회 ( 아이디로 조회 )
	   * */
	private void displayUserSituation(UserLog userLog) {

		if(userLog == null) {
		     System.out.println("*********** 조회된 회원이 없습니다. ***********");
		} else {
		   System.out.println("*----------------------------------------*");
		   System.out.println("* 회원번호 : " + userLog.getUserLogNum());
		   System.out.println("* 이름 : " + userLog.getUserLogName());
		   System.out.println("* 아이디 : " + userLog.getUserLogId());
		   System.out.println("* 휴대폰번호 : " + userLog.getUserLogPhone());
		   System.out.println("* 체크인 시각 : " + userLog.getUserLogCheckIn());
		   System.out.println("* 체크아웃 시각 : " + userLog.getUserLogCheckOut());
	
		}
		System.out.println("*----------------------------------------*");
		
	}

	/**
	 * @성근
	 * 이용자 조회 메소드
	 * (전체조회 , 현재 이용중인 이용자 조회 )
	 * */
	private void displayUserSituationList(List<UserLog> userList) {
		
		if(userList.isEmpty()) {
	         System.out.println("*********** 조회된 결과가 없습니다. ***********");
	     } else {
	         System.out.println("*---------------------------------------------------------------------------------------------------------------------*");

	         System.out.printf("%-10s    %-10s    %-10s    %-10s    %-30s    %-30s\n"
	                 , "회원번호", "회원이름", "회원아이디", "휴대폰번호", "체크인 시각", "체크아웃 시각");
	         System.out.println("*---------------------------------------------------------------------------------------------------------------------*");
	         for( UserLog u : userList) {
	             System.out.printf("%-10s    %-10s    %-10s    %-10s    %-30s    %-30s\n",
	                     u.getUserLogNum(), u.getUserLogName(), u.getUserLogId(), u.getUserLogPhone(), 
	                     u.getUserLogCheckIn(), u.getUserLogCheckOut());
	         }
	         System.out.println("*----------------------------------------------------------------------------------------------------------------------*");
	     }
	}

}