

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteBookServlet
 */
@WebServlet("/deleteBook")
public class deleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int booking_number=Integer.parseInt(request.getParameter("booking_number"));
		hotelDAO dao=new hotelDAO();
		dao.deleteBook(booking_number);
		String outstr="";
//		ArrayList<hotelDTO> list = dao.listBook();
//		for(int i=0;i<list.size();i++) {
//			hotelDTO data=new hotelDTO();
//			data=list.get(i);
//			outstr+="<option value="+data.getBooking_number()+">"+data.getRoom_name()+" "+data.getCheckin_date()+" "+data.getCheckout_date()+" "+data.getBooked_people()+"Έν "+
//					data.getBooking_person()+" "+data.getMobile()+" "+data.getTotal_price()+"Ώψ"+"</option>";
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
