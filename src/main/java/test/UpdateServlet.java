package test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
		
		try {
           
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String city = request.getParameter("city");

           
            User u = new User();
            u.setId(id);
            u.setName(name);
            u.setAge(age);
            u.setPassword(password);
            u.setEmail(email);
            u.setCity(city);

           
            int status = UserDao.update(u);

            if (status > 0) {
                pw.println("<h2>User Updated Successfully!</h2>");
            } else {
                pw.println("<h2>Update Failed!</h2>");
            }

         
            pw.println("<a href='ViewServlet'>Go Back to User List</a>");

        } catch (Exception e) {
        	System.out.println(e);
        		//pw.println("<h2>Error: " + e.getMessage() + "</h2>");
        		//pw.println("<a href='ViewServlet'>Go Back to User List</a>");
        }
    }
}
	
