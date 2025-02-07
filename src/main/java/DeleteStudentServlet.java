

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
 * Servlet implementation class DeleteStudentServlet
 */
@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
	public static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String rno = request.getParameter("Rollno"); 
		String q = "DELETE FROM `student_tbl` WHERE rollno =?";
		Connection con=null;
		PreparedStatement ps=null;
		try {
		//Step-1 Load driver
		Class.forName("com.mysql.jdbc.Driver");
		//Step-2 Get the connection(url,user,password)
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SMS_db","root","");
		//Step-3 
		ps = con.prepareStatement(q);
		ps.setString(1, rno);
		//Step-4 Execute query
		int i = ps.executeUpdate();
		if(i>0)
			out.println( i + " Record  deleted");
		ps.close();
		con.close();
		}
		catch(Exception e) {
			try {
			out.println(e.toString());
			out.close();
			ps.close();
			con.close();
			}catch(Exception e1)
			{
			}
			
				
		}
			
		
		
}

}
