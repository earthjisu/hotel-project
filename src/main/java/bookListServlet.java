

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class bookListServlet
 */
@WebServlet("/bookList")
public class bookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String checkin=request.getParameter("checkin_date");
		String checkout=request.getParameter("checkout_date");
		int room_type=Integer.parseInt(request.getParameter("room_type"));
		int booked_people=Integer.parseInt(request.getParameter("b_people"));
		System.out.println("loadServlet");
		hotelDAO dao=new hotelDAO();
		String outstr="";
		ArrayList<hotelDTO> list=dao.listBook(room_type, booked_people, checkin, checkout);
		for(int i=0;i<list.size();i++) {
			hotelDTO data=new hotelDTO();
			data=list.get(i);
			
			outstr+="<option name="+data.getAvail_people()+" value="+data.getBooking_number()+">"+data.getRoom_name()+" "+data.getCheckin_date()+" "+data.getCheckout_date()+" "+data.getBooked_people()+"Έν "+
			data.getBooking_person()+" "+data.getMobile()+" "+data.getTotal_price()+"Ώψ"+"</option>";
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
