

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteRoomServlet
 */
@WebServlet("/deleteRoom")
public class deleteRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int room_number=Integer.parseInt(request.getParameter("room_number"));
		roomDAO dao=new roomDAO();
		dao.deleteRoom(room_number);
		String outstr="";
		ArrayList<roomDTO> list = dao.listRoom();
		for(int i=0;i<list.size();i++) {
			roomDTO data=new roomDTO();
			data=list.get(i);
			outstr+="<option value="+data.getRoom_number()+">"+data.getRoom_name()+" "+data.getRoom_type()+" "+
					data.getAvail_people()+"Έν "+data.getPrice()+"Ώψ"+"</option>";
		}
		response.getWriter().print(outstr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
