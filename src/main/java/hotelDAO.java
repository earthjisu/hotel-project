import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class hotelDAO {
	private Statement stmt;  
	private Connection conn; 
	
	public ArrayList<hotelDTO>listAvail(int room_type, String checkin_date, String checkout_date, int b_people){
		ArrayList<hotelDTO> list=new ArrayList<hotelDTO>(); 
		try {
			connDB();
			String query="select * from roominfo a,roomType b where a.room_type=b.room_type and a.room_type=? and a.avail_people >= ? and room_number not in "
					+ "(select room_number from booking "
					+ "where (checkin_date between ? and ? or checkout_date between ? and ?) "
					+ "or (checkin_date <= ? and checkout_date >= ?))";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setInt(1, room_type);
			psmt.setInt(2, b_people);
			psmt.setString(3, checkin_date);
			psmt.setString(4, checkout_date);
			psmt.setString(5, checkin_date);
			psmt.setString(6, checkout_date);
			psmt.setString(7, checkin_date);
			psmt.setString(8, checkout_date);
			psmt.executeQuery();
			ResultSet rs=psmt.executeQuery();  
			while(rs.next()) {
				int room_number=rs.getInt("room_number");
				String room_name=rs.getString("room_name");
				String type_name=rs.getString("type_name");
				int avail_people2=rs.getInt("avail_people");
				int price2=rs.getInt("price");
//				System.out.println(room_name+type_name+avail_people+price);
				hotelDTO dto=new hotelDTO();
				dto.setRoom_name(room_name);
				dto.setType_name(type_name);
				dto.setAvail_people(avail_people2);
				dto.setPrice(price2);
				dto.setRoom_number(room_number);
				list.add(dto);	
			}
			rs.close();
			psmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<hotelDTO>listBook(int room_type, int booked_people, String checkin, String checkout){
		ArrayList<hotelDTO> list=new ArrayList<hotelDTO>();
		try {
			connDB();
			String query="select a.room_name,b.booking_number,b.room_number,b.booked_people,"
					+ "to_char(b.checkin_date,'yyyy-mm-dd') checkin_date,to_char(b.checkout_date,'yyyy-mm-dd') checkout_date,"
					+ "b.total_price,b.booking_person,b.mobile,a.avail_people "
					+ "from roominfo a, booking b "
					+ "where a.room_type=? and b.booked_people >= ? "
					+ "and (b.checkin_date between ? and ? or b.checkout_date between ? and ? "
					+ "or (b.checkin_date <= ? and b.checkout_date >= ?)) "
 					+ "and a.room_number=b.room_number ";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setInt(1, room_type);
			psmt.setInt(2, booked_people);
			psmt.setString(3, checkin);
			psmt.setString(4, checkout);
			psmt.setString(5, checkin);
			psmt.setString(6, checkout);
			psmt.setString(7, checkin);
			psmt.setString(8, checkout);
			psmt.executeQuery();
			ResultSet rs=psmt.executeQuery(); 

			while(rs.next()) {
				int avail_people=rs.getInt("avail_people");
				String room_name=rs.getString("room_name");
				int booking_number=rs.getInt("booking_number");
				int room_number=rs.getInt("room_number");
				int booked_People=rs.getInt("booked_people");
				String checkin_date2=rs.getString("checkin_date");
				String checkout_date2=rs.getString("checkout_date");
				int total_price=rs.getInt("total_price");
				String booking_person=rs.getString("booking_person");
				String mobile=rs.getString("mobile");
				System.out.println(booking_number+booking_person+mobile);
				hotelDTO dto=new hotelDTO();
				dto.setAvail_people(avail_people);
				dto.setRoom_name(room_name);
				dto.setBooking_number(booking_number);
				dto.setRoom_number(room_number);
				dto.setBooked_people(booked_People);
				dto.setCheckin_date(checkin_date2);
				dto.setCheckout_date(checkout_date2);
				dto.setTotal_price(total_price);
				dto.setBooking_person(booking_person);
				dto.setMobile(mobile);
				list.add(dto);
				
			}
			rs.close();
			psmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addBook(hotelDTO dto) {
		try {
			connDB();
			String query="insert into booking values(book_seq.nextval,?,?,?,?,?,?,?)";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setInt(1, dto.getRoom_number());
			psmt.setInt(2, dto.getBooked_people());
			psmt.setInt(3, dto.getTotal_price());
			psmt.setString(4, dto.getBooking_person());
			psmt.setString(5, dto.getMobile());
			psmt.setString(6, dto.getCheckin_date());
			psmt.setString(7, dto.getCheckout_date());
			psmt.executeUpdate();
			psmt.close();
			conn.close();	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateBook(hotelDTO dto) {
		try {
			connDB();
			String query="update booking set booked_people=?, booking_person=?, mobile=? where booking_number=?";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setInt(1,dto.getBooked_people());
			psmt.setString(2, dto.getBooking_person());
			psmt.setString(3, dto.getMobile());
			psmt.setInt(4, dto.getBooking_number());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBook(int booking_number) {
		try {
			connDB();
			String query="delete from booking where booking_number=?";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setInt(1, booking_number);
			psmt.executeUpdate();
			psmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void connDB() {
		String driver ="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String userid="ora_user";
		String passcode="human123";
		
		try {
			Class.forName(driver);
			this.conn=DriverManager.getConnection(url,userid,passcode);
			if(conn==null) {
				System.out.println("데이터베이스 접속 실패");
			}
		}catch(Exception e) {
			e.printStackTrace(); 
		}
	}
}
