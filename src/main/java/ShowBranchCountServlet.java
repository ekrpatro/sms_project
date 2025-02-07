import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ShowBranchCountServlet")
public class ShowBranchCountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sms_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head><title>Branch-wise Student Count</title></head>");
        out.println("<body>");
        out.println("<h2>Branch-wise Student Count</h2>");

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL query to get branch-wise count
            String query = "SELECT branch, COUNT(*) AS count FROM student_tbl GROUP BY branch";
            PreparedStatement ps = conn.prepareStatement(query);

            // Execute the query
            ResultSet rs = ps.executeQuery();

            // Display results in a table
            out.println("<table>");
            out.println("<tr><th>Branch</th><th>Count</th></tr>");

            while (rs.next()) {
                String branch = rs.getString("branch");
                int count = rs.getInt("count");
                out.println("<tr><td>" + branch + "</td><td>" + count + "</td></tr>");
            }

            out.println("</table>");

            // Close resources
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
            e.printStackTrace(out);
        }

        out.println("</body></html>");
    }
}
