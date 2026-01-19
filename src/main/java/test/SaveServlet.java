package test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SaveServlet
 */
@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw= response.getWriter();

        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String city=request.getParameter("city");

        System.out.println(id+" "+name+" "+password);

        User u=new User();
        
        u.setId(id);
        u.setName(name);
        u.setAge(age);
        u.setPassword (password);
        u.setEmail(email);
        u.setCity (city);
        
        int status=UserDao.save(u);

        if(status>0)
        {
        	pw.print("Record Inserted successfully");
        }
        else 
        {
        	pw.print("Unable to save the record");
        }
        pw.close();
        }
        
	}


