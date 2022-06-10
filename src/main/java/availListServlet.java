

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class availListServlet
 */
@WebServlet("/availList")
public class availListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public availListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		hotelDAO dao=new hotelDAO();
		String outstr="";
		String checkin_date=request.getParameter("checkin_date");
		String checkout_date=request.getParameter("checkout_date");
		int b_people=Integer.parseInt(request.getParameter("b_people"));
//		String type_name=request.getParameter("type_name");
		int room_type=Integer.parseInt(request.getParameter("room_type"));
		ArrayList<hotelDTO> list=dao.listAvail(room_type,checkin_date, checkout_date, b_people);
		for(int i=0;i<list.size();i++) {
			hotelDTO data=new hotelDTO();
			data=list.get(i);
			outstr+="<option value='"+data.getRoom_number()+"'>"+data.getRoom_name()+" "+data.getType_name()+" "+
					data.getAvail_people()+"Έν "+data.getPrice()+"Ώψ"+"</option>";
		}
		response.getWriter().print(outstr);
		System.out.println(outstr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
