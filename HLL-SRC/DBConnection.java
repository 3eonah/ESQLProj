package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	private Connection con;
	// sql문장 실행
	private Statement st;
	// 실행 결과를 받아오는 객체
	private ResultSet rs;

	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diet-manage", "root", "1915247");
			st = con.createStatement();
		} catch (Exception e) {
			System.out.println("데이터베이스 연결 오류: " + e.getMessage());
		}
	}

	// 회원이 맞는지 검색
	public boolean isMem(String phone) {
		try {
			String SQL = "SELECT * FROM MEMBER WHERE PHONE= '" + phone + "'";
			// sql문장을 db에 연결
			rs = st.executeQuery(SQL); // result는 state문장을 실행한 것
			if (rs.next()) {
				// 데이터가 검색이 됐다면
				return true;
			}
		} catch (Exception e) {
			System.out.println("데이터베이스 검색 오류: " + e.getMessage());

		}
		// 검색이 되지 않았다면
		return false;
	}

	// 회원 목록 보기
	public void showMember() {
		try {

			String SQL = "SELECT * FROM MEMBER";
			rs = st.executeQuery(SQL);

			while(rs.next()) {
				String name=rs.getString("Mname");
				String age=rs.getString("age");
				System.out.println(age);
				
			}

		} catch (Exception e) {
			System.out.println("데이터베이스 검색 오류: " + e.getMessage());
		}

	}

	// 회원 추가
	public void insertMember(String name, String sex, int age, String phone, String type, String manager) {
		try {
			String SQL="INSERT INTO member VALUES ("+"'"+name+"',"+"'"+sex+"',"+age+",'"+phone+"',"
		+"'"+type+"',"+"'"+manager+"')";
			int updateCount=st.executeUpdate(SQL);
			new SuccessPopUp();

		} catch (Exception e) {
			System.out.println("회원 추가 실패: " + e.getMessage());
		}
	}

	// 회원 수정
	public void updateMember(String phone,String change, int col) {
		try {
			String SQL=null;
			switch(col) {
			case 0:
				SQL="UPDATE member SET Mname='"+change+"' WHERE phone='"+phone+"'";
				break;
				
			case 1:
				SQL="UPDATE member SET sex='"+change+"' WHERE phone='"+phone+"'";
				break;
				
			case 2:
				int age=Integer.parseInt(change);
				SQL="UPDATE member SET age="+age+" WHERE phone='"+phone+"'";
				break;
				
			case 3:
				SQL="UPDATE member SET phone='"+change+"' WHERE phone='"+phone+"'";
				break;
				
			case 4:
				SQL="UPDATE member SET type='"+change+"' WHERE phone='"+phone+"'";
				break;
				
			case 5:
				SQL="UPDATE member SET manager='"+change+"' WHERE phone='"+phone+"'";
				break;
			}
			int updateCount=st.executeUpdate(SQL);
			
		}
		catch(Exception e) {
			System.out.println("회원 정보 수정 실패: " + e.getMessage());
		}

	}
	
	//회원 검색
	public void searchMem(String name, String phone) {
		
	}
	
	// 영양 정보 수정
		public void updateFood(String food,String change, int col) {
			try {
				String SQL=null;
				switch(col) {
					
				case 0:
					int kcal=Integer.parseInt(change);
					SQL="UPDATE nutrition_info SET kcal='"+kcal+"' WHERE foodname='"+food+"'";
					break;
					
				case 1:
					double carb=Double.parseDouble(change);
					SQL="UPDATE nutrition_info SET carb="+carb+" WHERE foodname='"+food+"'";
					break;
					
				case 2:
					double sugar=Double.parseDouble(change);

					SQL="UPDATE nutrition_info SET sugar='"+sugar+"' WHERE foodname='"+food+"'";
					break;
					
				case 3:
					double protein=Double.parseDouble(change);
					SQL="UPDATE nutrition_info SET protein='"+protein+"' WHERE foodname='"+food+"'";
					break;
					
				case 4:
					double fat=Double.parseDouble(change);
					SQL="UPDATE nutrition_info SET fat='"+fat+"' WHERE foodname='"+food+"'";
					break;
				}
				int updateCount=st.executeUpdate(SQL);
				
			}
			catch(Exception e) {
				System.out.println("회원 정보 수정 실패: " + e.getMessage());
			}

		}
		
		public void insertFood(String name, int kcal, double carb, double sugar, double protein, double fat) {
			try {
				String SQL="INSERT INTO nutrition_info VALUES ("+"'"+name+"',"
			+kcal+","+carb+","+sugar+","+protein+","+fat+");";
				int updateCount=st.executeUpdate(SQL);
				new SuccessPopUp();

			} catch (Exception e) {
				System.out.println("회원 추가 실패: " + e.getMessage());
			}
		}
}
