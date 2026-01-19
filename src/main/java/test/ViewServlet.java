package test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        // Pagination
        String pid = request.getParameter("page");
        int pageid = 1;
        if (pid != null) {
            try {
                pageid = Integer.parseInt(pid);
            } catch (NumberFormatException e) {
                pageid = 1;
            }
        }

        int pagelimit = 5; // users per page
        int start = (pageid - 1) * pagelimit;

        // Get list of users from DAO
        List<User> list = UserDao.getRecords(start, pagelimit);

        pw.print("<h1>User List - Page " + pageid + "</h1>");
        pw.print("<table border='1' cellpadding='5' width='70%'>");
        pw.print("<tr><th>ID</th><th>Name</th><th>Age</th><th>City</th><th>Email</th><th>Actions</th></tr>");

        for (User u : list) {
            pw.print("<tr>");
            pw.print("<td>" + u.getId() + "</td>");
            pw.print("<td>" + u.getName() + "</td>");
            pw.print("<td>" + u.getAge() + "</td>");
            pw.print("<td>" + u.getCity() + "</td>");
            pw.print("<td>" + u.getEmail() + "</td>");
            pw.print("<td><a href='EditServlet?id=" + u.getId() + "'>Edit</a> | "
                   + "<a href='DeleteServlet?id=" + u.getId() + "'>Delete</a></td>");
            pw.print("</tr>");
        }

        pw.print("</table>");

        // Pagination links (simple example)
        pw.print("<br>");
        for (int i = 1; i <= 3; i++) {
            pw.print("<a href='ViewServlet?page=" + i + "'>" + i + "</a> ");
        }

        pw.close();
    }
}
