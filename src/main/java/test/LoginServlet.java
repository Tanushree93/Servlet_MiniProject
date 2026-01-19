package test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cruddemodb", "root", "siya");

            PreparedStatement ps = con.prepareStatement("select * from users where email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

//            if (rs.next()) {
//                response.sendRedirect("ViewServlet?page=1");
//            } else {
//                pw.println("<h3>Invalid email or password! <a href='login.html'>Try again</a></h3>");
//            }
            
            if (rs.next()) {
                pw.println("<h2>Login Successfully!</h2>");
                pw.println("<a href='ViewServlet'>Go to User List</a>");
            } else {
                pw.println("<h2>Invalid Email or Password</h2>");
                pw.println("<a href='login.html'>Try Again</a>");
            }
            con.close();
        } catch (Exception e) {
            pw.println(e);
        }
	}

}
