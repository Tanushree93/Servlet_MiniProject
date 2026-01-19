package test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        User u = UserDao.getUserById(id);

        pw.print("<h2>Edit User</h2>");
        pw.print("<form action='UpdateServlet' method='post'>");

        pw.print("<input type='hidden' name='id' value='" + u.getId() + "'>");

        pw.print("Name: <input type='text' name='name' value='" + u.getName() + "'><br><br>");
        pw.print("Password: <input type='text' name='password' value='" + u.getPassword() + "'><br><br>");
        pw.print("Age: <input type='number' name='age' value='" + u.getAge() + "'><br><br>");
        pw.print("Email: <input type='email' name='email' value='" + u.getEmail() + "'><br><br>");
        pw.print("City: <input type='text' name='city' value='" + u.getCity() + "'><br><br>");

        pw.print("<input type='submit' value='Update'>");
        pw.print("</form>");
	}

}
