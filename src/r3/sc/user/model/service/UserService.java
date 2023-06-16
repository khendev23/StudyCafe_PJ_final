package r3.sc.user.model.service;

import java.sql.Connection;
import java.util.List;

import static r3.sc.common.JdbcTemplate.close;
import static r3.sc.common.JdbcTemplate.commit;
import static r3.sc.common.JdbcTemplate.getConnection;
import static r3.sc.common.JdbcTemplate.rollback;

import r3.sc.user.model.dao.UserDao;
import r3.sc.user.model.dto.OrderList;
import r3.sc.user.model.dto.User;
import r3.sc.user.model.dto.UserDel;
import r3.sc.user.model.dto.UserLog;

public class UserService {
	
		private UserDao userDao = new UserDao();
	
		/**
	     * Service @윤아
	     * (회원가입) 입력받은 정보를 insert하는 메소드
	     * */
	    public int insertUser(User user) {
	        int result = 0;
	        Connection conn = getConnection();
	        try {
	            result = userDao.insertUser(conn, user);
	            commit(conn);
	        } catch ( Exception e) {
	            rollback(conn);
	            throw e;
	        } finally {
	            close(conn);
	        }
	        return result;
	    }
		
		/**
	     * UserService @경빈
	     * 회원으로 이용시 회원테이블 조회 후 result에 값대입
	     * @param userId
	     * @param userPw
	     * @return
	     */
	    public int userLogin(String userId, String userPw) {
	         int result = 0;
	         Connection conn = getConnection();
	          try {
	             result = userDao.userLogin(conn,userId, userPw);
	          } catch (Exception e) {
	
	             e.printStackTrace(); 
	          } 
	
	          return result;
	    }
	    
 
   
	  /**
	   * UserService
	   * 회원정보 수정
	   */
	  public int updateUser(String userId,String colName, Object newValue) {
	     
	      int result = 0;
	      Connection conn = getConnection();
	        try {
	           result = userDao.updateUser(conn, userId, colName, newValue);
	           commit(conn);
	        } catch (Exception e) {
	          rollback(conn);
	           e.printStackTrace(); 
	        } finally {
	           close(conn);
	        }
	       
	        return result;
	  }
	
		
		/**
	    * UserService
	    * @경빈
	    * 회원 퇴실
	    */
      public int userCheckOut(User user) {
         int result = 0;
             Connection conn = getConnection();
              try {
                 result = userDao.userCheckOut(conn,user);
                 commit(conn);
              } catch (Exception e) {
                 rollback(conn);
                 e.printStackTrace(); 
              }  finally {
               close(conn);
            }
              return result;
        }
      
      /**
       * UserService
       * @경빈
       * 가장 최신 유저로그정보 조회
       */
      public UserLog findUserSituation(String userId) {
         Connection conn = getConnection();
           UserLog userLog = userDao.findUserSituation(conn, userId);
           close(conn);
           return userLog;
      }
      
	   /**
	    * UserService
	    * @경빈
	    * 회원 탈퇴 
	    */
	   public int deleteUser(String userId) {
	       int result = 0;
	          Connection conn = getConnection();
	           try {
	              result = userDao.deleteUser(conn,userId);
	              commit(conn);
	           } catch (Exception e) {
	              rollback(conn);
	              e.printStackTrace(); 
	           } finally {
	            close(conn);
	         }
	           return result;
	     }

	    /**
		 * @성근
		 * 서비스
		 * 비회원으로 이용시 비회원 테이블 등록 
		 */
		public int insertNonUser(User user) {
			
			int result = 0;
			Connection conn = getConnection();
			try {
				result = userDao.insertNonUser(conn, user);
				commit(conn);
			} catch (Exception e) {
				rollback(conn);
				throw e;
			} finally {
				close(conn);
			}
			return result;
		}
	    
	  	/**
	  	 * UserService
	  	 * @성근
	  	 * (비회원) 핸드폰 중복검사
	  	 */
		public User findByPhoneNo(String phone) {
			Connection conn = getConnection();
		    User member = userDao.findByPhoneNo(conn, phone);
		    close(conn);
		    return member;
		}

		/**
	    * UserService
	    * 비회원 폰번호로 이용내역 조회 @성근
	    */
	   public UserLog findNonUserSituationByPhone(User user) {
	      Connection conn = getConnection();
	        UserLog userLog = userDao.findNonUserSituationByPhone(conn, user);
	        close(conn);
	        return userLog;
	   }
		
	   /**
		 * UserService
		 * @성근
		 * 당일권 주문 db등록
		 */
		public int orderDayPass(OrderList orderlist) {
			int result = 0;
		    Connection conn = getConnection();
		      try {
		         result = userDao.orderDayPass(conn, orderlist);
		         commit(conn);
		      } catch (Exception e) {
		        rollback(conn);
		         e.printStackTrace(); 
		      } finally {
		         close(conn);
		      }
		      return result;
		}

		/**
		 * UserService
		 * @성근
		 * 음료 주문 db등록
		 */
		public int orderBev(OrderList orderlist) {
			int result = 0;
		    Connection conn = getConnection();
		      try {
		         result = userDao.orderBev(conn, orderlist);
		         commit(conn);
		      } catch (Exception e) {
		        rollback(conn);
		         e.printStackTrace(); 
		      } finally {
		         close(conn);
		      }
		      return result;
		}
	   
	   
	   /** 
	    * UserService
	    * @성근
	    * 결제 Y 입력시 orderlist 테이블에 주문시간, 총금액 업데이트
	    */
		public int updateTotalPay(OrderList orderlist) {
			int result = 0;
	        Connection conn = getConnection();
	         try {
	            result = userDao.updateTotalPay(conn,orderlist);
	            commit(conn);
	         } catch (Exception e) {
	            rollback(conn);
	            e.printStackTrace(); 
	         } finally {
	          close(conn);
	       }
	         return result;
		}

		/**
		 * UserService
		 * @성근
		 * 결제 N 입력시 orderlist 테이블에서 삭제
		 * */
		public int deleteOrderList(OrderList orderlist) {
			int result = 0;
	        Connection conn = getConnection();
	         try {
	            result = userDao.deleteOrderList(conn,orderlist);
	            commit(conn);
	         } catch (Exception e) {
	            rollback(conn);
	            e.printStackTrace(); 
	         } finally {
	          close(conn);
	         }
	         return result;
		}

		/**
		 * UserService
		 * @성근
		 * 결제시 로그 테이블에 등록
		 */
		public int insertUserLog(User user) {
			int result = 0;
	        Connection conn = getConnection();
	         try {
	            result = userDao.insertUserLog(conn, user);
	            commit(conn);
	         } catch (Exception e) {
	            rollback(conn);
	            e.printStackTrace(); 
	         } finally {
	          close(conn);
	         }
	         return result;
		}
	
		/**
		 * UserService @성근
		 * (관리자) 이용자 전체 조회 , 현재 이용자 조회 메소드
		 */
		public List<UserLog> findAllUserSituation() {
			Connection conn = getConnection();
	        List<UserLog> userList = userDao.findAllUserSituation(conn);
	        close(conn);
	        return userList;
		}
	
		/**
		 * UserService @성근
		 * (관리자) 이용자 아이디로 조회 메소드
		 */
		public UserLog findUserSituationById(String id) {
			Connection conn = getConnection();
	        UserLog userLog = userDao.findUserSituationById(conn, id);
	        close(conn);
	        return userLog;
		}
	
		/**
		* Service @성근
	    * (관리자) 현재 이용중인(체크인만 있는) 회원, 비회원 조회
	    */
	  public List<UserLog> findPresentUserSituation() {
	      Connection conn = getConnection();
	        List<UserLog> userList = userDao.findPresentUserSituation(conn);
	        close(conn);
	        return userList;
	   }
		
	
		 /**
	     * UserService @성근
	     * (관리자) 관리자 로그인
	     */
		public int managerLogin(String userId, String userPw) {
			int result = 0;
	        Connection conn = getConnection();
	         try {
	            result = userDao.managerLogin(conn,userId, userPw);
	         } catch (Exception e) {
	
	            e.printStackTrace(); 
	         } 
	         return result;
		}
	    
	    /**
	     * @윤아
	     * (관리자용) 1. 회원 전체 조회 메소드
	     * */
	    public List<User> findAllUser() {
	        Connection conn = getConnection();
	        List<User> userList = userDao.fineAllUser(conn);
	        close(conn);
	        return userList;
	    }
		
	    /**
	     * Service @윤아
	     * (관리자) 입력받은 아이디를 조회하기위한 메소드
	     * */
	    public User findByid(String id) {
	        Connection conn = getConnection();
	        User member = userDao.findById(conn, id);
	        close(conn);
	        return member;
	    }
	
	    /**
	     * Service @윤아
	     * (관리자) 입력받은 이름을 조회하기위한 메소드
	     * */
	   public List<User> findByName(String name) {
	      Connection conn = getConnection();
	      List<User> userList = userDao.findByName(conn, name);
	      return userList;
	   }
	   
	   /**
	    * Service @윤아
	    * (관리자) 탈퇴한 회원을 조회하기 위한 메소드
	    * */
	  public List<UserDel> findAllUserDel() {
	     Connection conn = getConnection();
	     List<UserDel> userList = userDao.findAllUserDel(conn);
	     close(conn);
	     return userList;
	  }
	  
	  /**
		 * UserService
		 * @윤아
		 * (관리자) 총 매출내역 조회
		 * */
		public OrderList findTotalSales() {
			Connection conn = getConnection();
			OrderList order = userDao.findTotalSales(conn);
			close(conn);
			return order;
		}
		
		   
    
}
