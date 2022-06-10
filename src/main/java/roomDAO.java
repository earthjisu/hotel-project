import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class roomDAO {
	private Statement stmt;  
	private Connection conn; 
	
	public ArrayList<roomDTO>listRoom(){
		ArrayList<roomDTO> list=new ArrayList<roomDTO>(); 
		try {
			connDB();
			String query="select a.room_number, a.room_name,b.type_name,a.avail_people,a.price\r\n"
					+ "from roominfo a, roomType b\r\n"
					+ "where a.room_type=b.room_type";
			this.stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);  
			while(rs.next()) {
				int room_number=rs.getInt("room_number");
				String room_name=rs.getString("room_name");
	//			int room_type=rs.getInt("room_type");
				String type_name=rs.getString("type_name");
				int avail_people=rs.getInt("avail_people");
				int price=rs.getInt("price");
				System.out.println(room_number+room_name+avail_people+price);
				roomDTO dto=new roomDTO();
				dto.setRoom_number(room_number);
				dto.setRoom_name(room_name);
	//			dto.setRoom_type(room_type);
				dto.setAvail_people(avail_people);
				dto.setPrice(price);
				dto.setType_name(type_name);
				list.add(dto);	
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addRoom(roomDTO dto) {
		try {
			connDB();
			String query="insert into roominfo values(room_seq.nextval,?,?,?,?)";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setString(1, dto.getRoom_name());
			psmt.setInt(2, dto.getRoom_type());
			psmt.setInt(3, dto.getAvail_people());
			psmt.setInt(4, dto.getPrice());
			psmt.executeUpdate();
			psmt.close();
			conn.close();			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateRoom(roomDTO dto) {
		try {
			connDB();
			String query="update roominfo set room_name=?, room_type=?, avail_people=?, price=? where room_number=?";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setString(1, dto.getRoom_name());
			psmt.setInt(2, dto.getRoom_type());
			psmt.setInt(3, dto.getAvail_people());
			psmt.setInt(4, dto.getPrice());
			psmt.setInt(5, dto.getRoom_number());
			psmt.executeUpdate();
			psmt.close();
			conn.close();			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRoom(int room_number) {
		try {
			connDB();
			String query="delete from roominfo where room_number=?";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setInt(1, room_number);
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
