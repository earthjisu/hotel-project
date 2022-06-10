

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateBookServlet
 */
@WebServlet("/updateBook")
public class updateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateBookServlet() {
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
		dto.setBooking_number(Integer.parseInt(request.getParameter("booking_number")));;
		dto.setBooked_people(Integer.parseInt(request.getParameter("booked_people")));
		dto.setBooking_person(request.getParameter("booking_person"));
		dto.setMobile(request.getParameter("mobile"));
		hotelDAO dao=new hotelDAO();
		dao.updateBook(dto);
		
//		String outstr="";
//		ArrayList<hotelDTO> list = dao.listBook();
//		for(int i=0;i<list.size();i++) {
//			hotelDTO data=new hotelDTO();
//			data=list.get(i);
//			
//			outstr+="<option>"+data.getRoom_name()+" "+data.getCheckin_date()+"~"+data.getCheckout_date()+" "+data.getBooked_people()+"Έν "+
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
