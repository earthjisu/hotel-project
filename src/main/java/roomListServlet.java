

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class roomListServlet
 */
@WebServlet("/roomList")
public class roomListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public roomListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		roomDAO dao=new roomDAO();
		String outstr="";
		ArrayList<roomDTO> list=dao.listRoom();
		for(int i=0;i<list.size();i++) {
			roomDTO data=new roomDTO();
			data=list.get(i);
			outstr+="<option value="+data.getRoom_number()+">"+data.getRoom_name()+" "+data.getType_name()+" "+
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
