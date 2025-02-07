import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertStudentServlet")
public class InsertStudentServlet extends HttpServlet 
{
    private static final String DB_URL = "jdbc:mysql:"	+ "//localhost:3306/sms_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out=null;
        Connection con=null;
        PreparedStatement pst=null;
        response.setContentType("text/html");
        out = response.getWriter();
       
        String[] rnos = request.getParameterValues("rno");
        String[] names = request.getParameterValues("name");
        String[] branches = request.getParameterValues("branch");
        int rec_count=rnos.length;   
        //out.println("<h3>"+rec_count + " Records received!</h3>");

        
        try 
        {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                String query = "INSERT INTO student_tbl( rollno, name, branch) VALUES (?,?,?)";
                pst = con.prepareStatement(query);
                for (int i = 0; i <rec_count; i++) 
                {
                    
                    pst.setString(1, rnos[i]);
                    pst.setString(2, names[i]);
                    pst.setString(3, branches[i]);
                    pst.executeUpdate();
                }

                out.println("<h3>"+rec_count + " Records inserted successfully!</h3>");
               
				out.println("<br><a href='./DynamicRecords.html'>Click here to continue</a>");
				// response.sendRedirect("./AddRemoveForm.html");
				out.println("<hr><br><a href='./ShowBranchCount.html'>ShowBatchCount</a>");
				
        } 
        catch (Exception e) 
        {
                e.printStackTrace();
                out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
        finally 
        {
        	try
        	{
        		out.close();
        		pst.close();
        		con.close();
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        }
        
    }
}


