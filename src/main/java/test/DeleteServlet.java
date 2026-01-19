package test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int id = Integer.parseInt(request.getParameter("id"));
//
//        int status = UserDao.delete(id);
//
//        if (status > 0)
//        	response.sendRedirect("ViewServlet");
//
//        else
//            response.getWriter().print("Delete Failed");
		
		String sid = request.getParameter("id");

        if (sid != null && !sid.isEmpty()) {
            int id = Integer.parseInt(sid);
            UserDao.delete(id);
        }

        // Redirect back to view page
        response.sendRedirect("ViewServlet?page=1");
	}

}
