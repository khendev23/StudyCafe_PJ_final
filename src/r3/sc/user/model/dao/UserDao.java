package r3.sc.user.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import r3.sc.user.model.dto.OrderList;
import r3.sc.user.model.dto.User;
import r3.sc.user.model.dto.UserDel;
import r3.sc.user.model.dto.UserLog;
import r3.sc.user.model.exception.UserException;

public class UserDao {

	private Properties prop = new Properties();
	
	
		public UserDao() {
			try {
				prop.load(new FileReader("resource/user-query.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	/* DAO @윤아
	   * (회원가입) 입력받은 정보를 insert하는 메소드
	   * */
	  public int insertUser(Connection conn, User user) {
	      String sql = prop.getProperty("insertUser");
	      int result = 0;

	      try (PreparedStatement pstmt = conn.prepareStatement(sql)){
	          pstmt.setString(1, user.getUserName());
	          pstmt.setString(2, user.getUserId());
	          pstmt.setString(3, user.getUserPw());
	          pstmt.setString(4, user.getUserPhone());
	          pstmt.setDate(5, user.getUserBirthday());

	          result = pstmt.executeUpdate();

	      } catch (SQLException e) {
	          throw new UserException("********** 회원가입 오류 **********",e);

	      }
	      return result;
	  }
	
	/**
     * UserDao @경빈
     * 회원으로 이용시 회원테이블 조회 후 result에 값대입
     */
    public int userLogin(Connection conn,String userId, String userPw) {

        String sql = prop.getProperty("loginUser");
        int result = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, userId);
            pstmt.setString(2, userPw);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
	  /**
	   * DAO @윤아
	   * (메인, 관리자 2번메뉴) 회원 id로 조회
	   */
	  public User findById(Connection conn, String id) {
	      User user = null;
	      String sql = "select * from sc_user where user_id = ?";
	
	      try (PreparedStatement pstmt = conn.prepareStatement(sql)){
	          pstmt.setString(1, id);
	          try(ResultSet rset = pstmt.executeQuery()){
	              if(rset.next())
	                  user = handleUserResultSet(rset);
	          }
	
	      } catch (SQLException e) {
	          e.printStackTrace();
	      } 
	      return user;
	
	  }
  
	  /**
	   * DAO
	   * @윤아
	   * 회원 조회 resultSet
	   * */
	  private User handleUserResultSet(ResultSet rset) throws SQLException {
	      User user = new User();
	      user.setUserNo(rset.getInt("user_no"));
	      user.setUserName(rset.getString("user_name"));
	      user.setUserId(rset.getString("user_id"));
	      user.setUserPw(rset.getString("user_pw"));
	      user.setUserPhone(rset.getString("user_phone"));
	      user.setUserBirthday(rset.getDate("user_birthday"));
	
	      return user;
	  }
	  

	/**
	 * @UserDao
	 * @경빈
	 * 회원정보 수정
 	 */
	  public int updateUser(Connection conn, String userId, String colName, Object newValue) throws SQLException {
		  String sql = prop.getProperty("updateUser");
		  sql = sql.replace("#", colName);
		  int result = 0;
   
		  try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			  pstmt.setObject(1, newValue);
			  pstmt.setString(2, userId);
			  result = pstmt.executeUpdate();
		  } catch (SQLException e) {
			  throw e;
		  } 
		  return result;
	  }


	  /**
	    * Dao
	    * 회원, 비회원 퇴실 시 로그테이블에 체크아웃 입력
	    * @경빈, @성근
	    */
	   public int userCheckOut(Connection conn, User user) {
	      int result = 0;
	      
	      if(user.getUserId().equals("Non-User")) {
	         String sql = "update sc_user_log set user_check_out = systimestamp where user_log_phone = ?";
	         try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
	                 
	                 pstmt.setString(1, user.getUserPhone());
	                 
	                 result = pstmt.executeUpdate();
	                 
	              } catch (SQLException e) {
	                 e.printStackTrace();
	              } 
	              return result;
	              
	      } else {
	         String sql = "update sc_user_log set user_check_out = systimestamp where user_log_id = ?";
	         try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
	            
	            pstmt.setString(1, user.getUserId());
	            
	            result = pstmt.executeUpdate();
	            
	         } catch (SQLException e) {
	            e.printStackTrace();
	         } 
	         return result;
	      }
	    }
	
	   /**
       * UserDao
       * @경빈
       * 가장 최신 유저로그 정보 조회 
       */
      public UserLog findUserSituation(Connection conn, String userId) {
         UserLog userLog = null;
            String sql = prop.getProperty("findUserSituation");

            try (PreparedStatement pstmt = conn.prepareStatement(sql)){
               
               
                pstmt.setString(1, userId);
                try(ResultSet rset = pstmt.executeQuery()){
                    if(rset.next())
                        userLog = handleUserSituationResultSet(rset);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } 
            return userLog;
      }
	   
	/**
     * UserDao
     * @경빈
     * 회원 탈퇴
     */
    public int deleteUser(Connection conn, String userId) {
    	String sql = prop.getProperty("deleteUser");
	    int result = 0;
	   
	    try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
	       pstmt.setString(1, userId);
	         
	       result = pstmt.executeUpdate();
	         
	    } catch (SQLException e) {
	       e.printStackTrace();
	    } 
	    return result;
    }
    
    /**
	 * DAO
	 * 비회원이용시 비회원 테이블로 등록
	 * @성근
	 */
	public int insertNonUser(Connection conn, User user) {
		String sql = prop.getProperty("insertNonUser");
		int result = 0;
		// 1. PreparedStatement 생성 & 미완성쿼리 값대입
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3, user.getUserPw());
			pstmt.setString(4, user.getUserPhone());
			
			result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			throw new UserException("비회원 이용 오류", e);
		}
	}
  
  	/**
  	 * DAO
  	 * @성근
  	 * (비회원) 핸드폰 중복검사
  	 */
	
	  public User findByPhoneNo(Connection conn, String phone) {
		  User user = null;
	      String sql = prop.getProperty("findByPhoneNo");
	
	      try (PreparedStatement pstmt = conn.prepareStatement(sql)){
	          pstmt.setString(1, phone);
	          try(ResultSet rset = pstmt.executeQuery()){
	              if(rset.next())
	                  user = handleUserResultSet(rset);
	          }
	
	      } catch (SQLException e) {
	          e.printStackTrace();
	      } 
	      return user;
	  }
	  
	  /** 
	    * UserDao
	    * 비회원 폰번호로 조회 메소드
	    * @성근
	    */
	   public UserLog findNonUserSituationByPhone(Connection conn, User user) {
	      UserLog userLog = null;
	         String sql = prop.getProperty("findNonUserSituationByPhone");

	         try (PreparedStatement pstmt = conn.prepareStatement(sql)){
	       
	            
	             pstmt.setString(1, user.getUserPhone());
	             try(ResultSet rset = pstmt.executeQuery()){
	                 if(rset.next())
	                     userLog = handleUserSituationResultSet(rset);
	             }

	         } catch (SQLException e) {
	             e.printStackTrace();
	         } 
	         return userLog;
	   }

	  /**
	   * Dao
	   * @성근
	   * 당일권 주문 db 등록
	   */
	public int orderDayPass(Connection conn, OrderList orderlist) {
		
		  String sql = prop.getProperty("orderDayPass");
		  int result = 0;

		  try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			  
			  pstmt.setString(1, orderlist.getUserId());
			  pstmt.setString(2, orderlist.getUserPhone());
			  pstmt.setString(3, orderlist.getOrderPassName());
			  pstmt.setInt(4, orderlist.getCharge());
			  pstmt.setInt(5, orderlist.getNonUserCharge());
			  
			  result = pstmt.executeUpdate();
			  
		  } catch (SQLException e) {
			  throw new UserException("당일권 주문 오류", e);
		  } 
		  return result;
	}

	/**
	 * Dao
	 * @성근
	 * 음료 주문 db등록
	 */
	public int orderBev(Connection conn, OrderList orderlist) {
		  String sql = prop.getProperty("orderBev");
		  int result = 0;

		  try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			  
			
			  pstmt.setString(1, orderlist.getOrderBevName());
			  pstmt.setInt(2, orderlist.getBevCharge());
			  pstmt.setString(3, orderlist.getUserPhone());
			  
			  result = pstmt.executeUpdate();
			  
		  } catch (SQLException e) {
			  throw new UserException("음료 주문 오류", e);
		  } 
		  return result;
	}
	
    
    /**
     * UserDao
     * @성근
     * 결제 Y 입력시 orderlist 테이블에 주문시간, 총금액 업데이트
     */

	public int updateTotalPay(Connection conn, OrderList orderlist) {
		String sql = prop.getProperty("updateTotalPay");
	    int result = 0;
	   
	    try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
	    	if(orderlist.getUserId().contains("Non")) {
	    		pstmt.setInt(1, (orderlist.getNonUserCharge()+orderlist.getBevCharge()));
	    	} else {
	    		pstmt.setInt(1, (orderlist.getCharge()+orderlist.getBevCharge()));
	    	}
	        pstmt.setString(2, orderlist.getUserPhone());
	         
	        result = pstmt.executeUpdate();
	         
	    } catch (SQLException e) {
	       e.printStackTrace();
	    } 
	    return result;
	}

	/**
	 * UserDao
     * @성근
     * 결제 N 입력시 orderlist 테이블에서 삭제
	 */
	public int deleteOrderList(Connection conn, OrderList orderlist) {
		String sql = prop.getProperty("deleteOrderList");
	    int result = 0;
	   
	    try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
	    	
	        pstmt.setString(1, orderlist.getUserPhone());
	         
	        result = pstmt.executeUpdate();
	         
	    } catch (SQLException e) {
	       e.printStackTrace();
	    } 
	    return result;
	}

	/**
	 * Dao
	 * @성근
	 * 결제시 로그테이블에 등록
	 */
	public int insertUserLog(Connection conn, User user) {
		String sql = prop.getProperty("insertUserLog");
	    int result = 0;
	   
	    try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
	    	
	        pstmt.setString(1, user.getUserName());
	        pstmt.setString(2, user.getUserId());
	        pstmt.setString(3, user.getUserPhone());
	         
	        result = pstmt.executeUpdate();
	         
	    } catch (SQLException e) {
	       e.printStackTrace();
	    } 
	    return result;
	}

	

	 /**
    * UserDao @성근
    * (관리자) 관리자 로그인 
    */
   public int managerLogin(Connection conn, String userId, String userPw) {
   	 String sql = prop.getProperty("loginUser");
        int result = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(sql))
        {
       	 if(!userId.equals("admin") || !userPw.equals("admin")) {
       		 return 0;
       	 }
       	 pstmt.setString(1, userId);
       	 pstmt.setString(2, userPw);
       	 
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
       	 throw new UserException("관리자 로그인 오류", e);
        }
        return result;
   }
   
	
	   /*
	   * DAO @윤아
	   * (관리자용) 1. 회원 전체 조회 메소드
	   */
	  public List<User> fineAllUser(Connection conn) {
	      List<User> userList = new ArrayList<>();
	      String sql = prop.getProperty("findAllUser");
	
	      try(PreparedStatement pstmt = conn.prepareStatement(sql)){
	
	          try(ResultSet rset = pstmt.executeQuery()){
	
	              while(rset.next()) {
	                  User user = handleUserResultSet(rset);
	                  userList.add(user);
	              }
	          }
	
	      } catch (SQLException e) {
	          throw new UserException("*** 전체 회원조회 오류 **",e);
	      }
	      return userList;
	  }
	  
	  /**
	   * DAO @윤아
	   * (관리자용) 회원 이름으로 조회
	   */
	  public List<User> findByName(Connection conn, String name) {
	  String sql = prop.getProperty("findByName");
	  List<User> userList = new ArrayList<>();
	
	   try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	      pstmt.setString(1, "%" + name + "%");
	      
	      try (ResultSet rset = pstmt.executeQuery();) {
	         
	         while (rset.next()) {
	            User user = handleUserResultSet(rset);
	            userList.add(user);
	         }
	      }
	   } catch (Exception e) {
	      throw new UserException("회원 이름 검색 오류", e);
	   }
	   return userList;
	   
	}
	  
	  /**
	   * DAO @윤아
	   * (관리자) 탈퇴한 회원을 조회하기 위한 메소드
	   * */
	public List<UserDel> findAllUserDel(Connection conn) {
	  List<UserDel> userList = new ArrayList<>();
	  String sql = prop.getProperty("findAllUserDel");
	
	  try (
	     PreparedStatement pstmt = conn.prepareStatement(sql);
	     ResultSet rset = pstmt.executeQuery();
	  ) {
	     
	     while (rset.next()) {
	        UserDel user = handleMemberDelResultSet(rset);
	        userList.add(user);
	     }
	  } catch (SQLException e) {
	     e.printStackTrace();
	  } 
	  return userList;
	}

	/**
	 * handleResultSet @윤아
	 * (관리자) 탈퇴멤버 ResultSet 메소드
	 * */
	private UserDel handleMemberDelResultSet(ResultSet rset) throws SQLException{
	   UserDel user = new UserDel();
	   user.setUserNo(rset.getInt("user_no"));
	   user.setUserName(rset.getString("user_name"));
	   user.setUserId(rset.getString("user_id"));
	   user.setUserPhone(rset.getString("user_phone"));
	   user.setUserBirthday(rset.getDate("user_birthday"));
	   user.setUserDelDate(rset.getTimestamp("user_del_date"));
	   return user;
	}
   
	/**
	 * Dao
	 * @성근
	 * (관리자) 총 이용내역 조회 ( 전체, 현재 이용자)
	 */
	public List<UserLog> findAllUserSituation(Connection conn) {
		List<UserLog> userList = new ArrayList<>();
    String sql = prop.getProperty("findAllUserSituation");

    try(PreparedStatement pstmt = conn.prepareStatement(sql)){

        try(ResultSet rset = pstmt.executeQuery()){

            while(rset.next()) {
                UserLog user = handleUserSituationResultSet(rset);
                userList.add(user);
            }
        }
	    } catch (SQLException e) {
	        throw new UserException("*** 전체 이용내역 조회 오류 **",e);
	    }
	    return userList;
	}
	
	/**
	 * DAO @성근
	 * 현재 이용자 조회 
	 * */
	public List<UserLog> findPresentUserSituation(Connection conn) {
	      
	      List<UserLog> userList = new ArrayList<>();
	       String sql = prop.getProperty("findPresentUserSituation");

	       try(PreparedStatement pstmt = conn.prepareStatement(sql)){

	           try(ResultSet rset = pstmt.executeQuery()){

	               while(rset.next()) {
	                   UserLog user = handleUserSituationResultSet(rset);
	                   userList.add(user);

	               }
	           }

	       } catch (SQLException e) {
	           throw new UserException("*** 현재 이용자 이용내역 조회 오류 **",e);
	       }

	       return userList;
	   }
	
	/**
	 * Dao
	 * @성근
	 * (관리자) ID로 이용내역 조회
	 * */
	public UserLog findUserSituationById(Connection conn, String id) {
		UserLog userLog = null;
	      String sql = prop.getProperty("findUserSituationById");

	      try (PreparedStatement pstmt = conn.prepareStatement(sql)){
	    	  if(id.equals("Non-User")) {
	    		  System.out.println("비회원은 조회할 수 없습니다.");
	    	  } 
	    	  
	          pstmt.setString(1, id);
	          try(ResultSet rset = pstmt.executeQuery()){
	              if(rset.next())
	                  userLog = handleUserSituationResultSet(rset);
	          }
	      } catch (SQLException e) {
	          e.printStackTrace();
	      } 
	      return userLog;
	}
	
	/**
	 * @성근
	 * (관리자) 이용내역 조회 handleResultSet
	 * */
	private UserLog handleUserSituationResultSet(ResultSet rset) throws SQLException {
		UserLog user = new UserLog();
	    user.setUserLogNum(rset.getInt("user_log_no"));
	    user.setUserLogName(rset.getString("user_log_name"));
	    user.setUserLogId(rset.getString("user_log_id"));
	    user.setUserLogPhone(rset.getString("user_log_phone"));
	    user.setUserLogCheckIn(rset.getTimestamp("user_check_in"));
	    user.setUserLogCheckOut(rset.getTimestamp("user_check_out"));

	    return user;
	}

	
	/**
	 * UserDao
	 * @윤아
	 * (관리자) 총 매출내역 조회
	 * */
	public OrderList findTotalSales(Connection conn) {
		OrderList order = new OrderList();
		String sql = prop.getProperty("findTotalSales");

		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			try(ResultSet rset = pstmt.executeQuery();){
				if(rset.next())
					 order.setTotalPay(rset.getInt("total_pay_sum"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return order;
	}
  
}
