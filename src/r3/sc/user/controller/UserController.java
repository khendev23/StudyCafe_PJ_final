package r3.sc.user.controller;

import java.util.List;

import r3.sc.user.model.dto.OrderList;
import r3.sc.user.model.dto.User;
import r3.sc.user.model.dto.UserDel;
import r3.sc.user.model.dto.UserLog;
import r3.sc.user.model.service.UserService;

public class UserController {

	private UserService userService = new UserService();
	
	 /* 
     * Controller @윤아
    * (회원가입) 입력받은 정보를 insert하는 메소드
    * */
   public int insertUser(User user) {
       int result = 0;
       try {
           result = userService.insertUser(user);
       } catch ( Exception e ) {
            System.err.print("> 불편을 드려 죄송합니다. 윤아에게 문의주세요.");
           e.printStackTrace();
       }

       return result;
   }

	/**
     * @경빈
     * 컨트롤러
     * 회원으로 이용시 회원테이블 조회 후 result에 값대입
     */
    public int userLogin(String userId, String userPw) {
        int result = 0;
        try {
            result = userService.userLogin(userId, userPw);
        } catch (Exception e) {
            System.err.print("> 불편을 드려 죄송합니다. 경빈에게 문의주세요.");
            e.printStackTrace();
        }
        return result;
    }
    
	/**
	 * @성근
	 * 컨트롤러
	 * 비회원으로 이용시 비회원 테이블 등록 
	 */
	public int insertNonUser(User user) {
		
		int result = 0;
		try {
			result = userService.insertNonUser(user);
			
		} catch (Exception e) {
			System.err.println("> 불편을 드려 죄송합니다. 성근에게 문의해주세요");
			e.printStackTrace();
		}
		return result;
	}
 
	   /**
	    * Controller
	    * @성근
	    * 비회원 핸드폰번호 중복검사
	    * */
		public User findByPhoneNo(String phone) {
			return userService.findByPhoneNo(phone);
		}
		
		/** 
	    * UserController
	    * 비회원 폰번호로 이용내역 조회 @성근
	    * @param user
	    * @return
	    */
	   public UserLog findNonUserSituationByPhone(User user) {
	      UserLog userLog = null;
	      
	      try {
	            userLog = userService.findNonUserSituationByPhone(user);
	        } catch(Exception e) {
	            System.err.print("> 불편을 드려 죄송합니다. 성근에게 문의주세요.");
	            e.printStackTrace();
	        }
	       
	      return userLog;
	   }
	   
		/**
		 * userControll
		 * @경빈
		 * 회원정보 수정 메소드
		 */
		public int updateUser(String userId, String colName, Object newValue) {
			int result = 0;
			try {
				result = userService.updateUser(userId, colName, newValue);
			} catch (Exception e) {
				System.err.print("> 불편을 드려 죄송합니다. 경빈에게 문의주세요.");
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * UserController
		 * @경빈
		 * 유저테이블에 퇴실 등록
		 */
		public int userCheckOut(User user) {
			int result = 0;
			try {
				result = userService.userCheckOut(user);
			} catch (Exception e) {
				System.err.print("> 불편을 드려 죄송합니다. 경빈에게 문의주세요.");
				e.printStackTrace();
			}
			return result;
		}
		
	  /**
       * UserController
       * @경빈
       * 가장 최신 유저로그정보 조회
       */
      public UserLog findUserSituation(String userId) {
         return userService.findUserSituation(userId);
      }
	   
	   /**
	    * UserContoroller
	    * @경빈
	    * 회원탈퇴
	    */
	   public int deleteUser(String userId) {
	      int result = 0;
	        try {
	            result = userService.deleteUser(userId);
	        } catch (Exception e) {
	            System.err.print("> 불편을 드려 죄송합니다. 경빈에게 문의주세요.");
	            e.printStackTrace();
	        }
	        return result;
	   }

		
		/**
		 * UserController
		 * @성근
		 * 당일권 db 등록
		 */
		public int orderDayPass(OrderList orderlist) {
			
			 int result = 0;
		      try {
		         result = userService.orderDayPass(orderlist);
		      } catch (Exception e) {
		         System.err.print("> 불편을 드려 죄송합니다. 성근에게 문의주세요.");
		         e.printStackTrace();
		      }
		      return result;
		}
	
		/**
		 * UserController
		 * @성근
		 * 음료 주문 db 등록
		 */
		public int orderBev(OrderList orderlist) {
			
			int result = 0;
		      try {
		         result = userService.orderBev(orderlist);
		      } catch (Exception e) {
		         System.err.print("> 불편을 드려 죄송합니다. 성근에게 문의주세요.");
		         e.printStackTrace();
		      }
		      return result;
		}
	
		/**
		 * UserController
		 * @성근
		 * 결제 Y 입력시 orderlist 테이블에 주문시간, 총금액 업데이트
		 */
		public int updateTotalPay(OrderList orderlist) {
			int result = 0;
	        try {
	            result = userService.updateTotalPay(orderlist);
	        } catch (Exception e) {
	            System.err.print("> 불편을 드려 죄송합니다. 성근에게 문의주세요.");
	            e.printStackTrace();
	        }
	        return result;
		}
		
		/**
		 * UserController
		 * @성근
		 * 결제 N 입력시 orderlist 테이블에서 삭제
		 */
		public int deleteOrderList(OrderList orderlist) {
			int result = 0;
	        try {
	            result = userService.deleteOrderList(orderlist);
	        } catch (Exception e) {
	            System.err.print("> 불편을 드려 죄송합니다. 성근에게 문의주세요.");
	            e.printStackTrace();
	        }
	        return result;
		}
	
		/**
		 * UserController
		 * @성근
		 * 결제시 로그테이블에 등록
		 */
		public int insertUserLog(User user) {
			int result = 0;
	        try {
	            result = userService.insertUserLog(user);
	        } catch (Exception e) {
	            System.err.print("> 불편을 드려 죄송합니다. 성근에게 문의주세요.");
	            e.printStackTrace();
	        }
	        return result;
		}
		
		/**
		 * UserController
		 * @성근
		 * (관리자) 총 이용내역 조회
		 */
		public List<UserLog> findAllUserSituation() {
			List<UserLog> userList = null;
	        try {
	            userList = userService.findAllUserSituation();
	        } catch(Exception e) {
	            System.err.print("> 불편을 드려 죄송합니다. 성근에게 문의주세요.");
	            e.printStackTrace();
	        }
	        return userList;
		}
	
		public UserLog findUserSituationById(String id) {
		    return userService.findUserSituationById(id);
	    }
	
		/**
		* Controller @성근
	    * (관리자) 현재 이용중인(체크인만 있는) 회원, 비회원 조회
	    */
	   public List<UserLog> findPresentUserSituation() {
	      List<UserLog> userList = null;
	        try {
	            userList = userService.findPresentUserSituation();
	        } catch(Exception e) {
	            System.err.print("> 불편을 드려 죄송합니다. 성근에게 문의주세요.");
	            e.printStackTrace();
	        }
	        return userList;
	   }
		
	    /**
	     * Controller
	     * (관리자) 관리자 로그인
	     * @성근
	     */
	    public int managerLogin(String userId, String userPw) {
	    	int result = 0;
	        try {
	            result = userService.managerLogin(userId, userPw);
	        } catch (Exception e) {
	            System.err.print("> 불편을 드려 죄송합니다. 경빈에게 문의주세요.");
	            e.printStackTrace();
	        }
	        return result;
		}
		
		/*
	    * Controller @윤아
	    * (관리자용) 1. 회원 전체 조회 메소드
	    * */
	    public List<User> findAllUser() {
	        List<User> userList = null;
	        try {
	            userList = userService.findAllUser();
	        } catch(Exception e) {
	            System.err.print("> 불편을 드려 죄송합니다. 윤아에게 문의주세요.");
	            e.printStackTrace();
	        }
	        return userList;
	    }
		   
		   
	   /**
	     * Controller @윤아
	     * (관리자) 입력받은 아이디를 조회하기위한 메소드
	     * */
	    public User findById(String id) {
	        return userService.findByid(id);
	    }

	    /**
	     * Controller @윤아
	     * (관리자) 입력받은 이름 조회하기위한 메소드
	     * */
	   public List<User> findByName(String name) {
	       List<User> userList = null;
	           try {
	               userList = userService.findByName(name);
	           } catch(Exception e) {
	               System.err.print("> 불편을 드려 죄송합니다. 윤아에게 문의주세요.");
	               e.printStackTrace();
	           }
	           return userList;
	   }
	   
	   /**
	     * Controller @윤아
	     * (관리자) 탈퇴멤버를 조회하기 위한 메소드
	     * */
	   public List<UserDel> findAllUserDel() {
	      return userService.findAllUserDel();
	   }
	
		/**
		 * UserController
		 * @윤아
		 * (관리자) 총 매출내역 조회
		 * */
		public OrderList findTotalSales() {
			return userService.findTotalSales();
		}

	
	
}
