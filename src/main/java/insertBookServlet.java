

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insertBookServlet
 */
@WebServlet("/insertBook")
public class insertBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		hotelDTO dto=new hotelDTO();
		dto.setRoom_number(Integer.parseInt(request.getParameter("room_number")));
		dto.setCheckin_date(request.getParameter("checkin_date"));
		dto.setCheckout_date(request.getParameter("checkout_date"));
		dto.setBooked_people(Integer.parseInt(request.getParameter("booked_people")));
		dto.setBooking_person(request.getParameter("booking_person"));
		dto.setMobile(request.getParameter("mobile"));
		dto.setTotal_price(Integer.parseInt(request.getParameter("total_price")));
		hotelDAO dao=new hotelDAO();
		dao.addBook(dto);
		
//		String outstr="";
//		ArrayList<hotelDTO> list = dao.listBook();
//		for(int i=0;i<list.size();i++) {
//			hotelDTO data=new hotelDTO();
//			data=list.get(i);
//			
//			outstr+="<option>"+data.getRoom_name()+" "+data.getCheckin_date()+" "+data.getCheckout_date()+" "+data.getBooked_people()+"Έν "+
//			data.getBooking_person()+" "+data.getMobile()+" "+data.getTotal_price()+"Ώψ"+"</option>";
//		}
//		response.getWriter().print(outstr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
