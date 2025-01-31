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

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement ps=null;
		response.setContentType("text/html"); // MIME type
		String rollno=request.getParameter("rollno");
		String name=request.getParameter("name");
		String branch=request.getParameter("branch");
		
		String query = "insert into student_tbl(rollno,name,branch) values(?,?,?)";
		PrintWriter pw = response.getWriter();
		//pw.println("Hello I Received Your input <br> Rollno: "+ rollno + "<br>Name : "+name+ "<br>Branch : "+branch);
		//pw.close();
		try
		{
			String url="jdbc:mysql://localhost:3306/sms_db";
			String db_user="root";
			String db_pass="";
			//step-1  Load the Driver
			Class.forName("com.mysql.jdbc.Driver");
			//step-2  Get Connection
			con = DriverManager.getConnection(url,db_user,db_pass);
			// step -3 
			ps=con.prepareStatement(query);
			ps.setString(1,rollno);
			ps.setString(2,name);
			ps.setString(3,branch);
			int r = ps.executeUpdate();
			if( r == 1)
			{
					
				pw.println("Record Inserted in Student_tbl successfully");
			}
		}
		catch(Exception e)
		{
			try
			{
				pw.println("Not inserted...");
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
		}
		finally
		{
			try
			{
				pw.close();
				ps.close();
				con.close();
			}
			catch(Exception e)
			{
			}
		}
		
	}

}
