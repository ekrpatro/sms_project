

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowStudentServlet
 */
@WebServlet("/ShowStudentServlet")
public class ShowStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String url="jdbc:mysql://localhost:3306/sms_db";
		String user="root";
		String pass="";
		try
		{	//step1:load the driver
			Class.forName("com.mysql.jdbc.Driver");
			//step2:establish a connection
			Connection con=DriverManager.getConnection(url,user,pass);
			String Query="Select * from student_tbl";
			//step3:Create statement
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(Query);
			//String tbl="<table style=' border:1px solid-black; border-collapse: collapse;'>";
			String tbl="<table style=' border-collapse: collapse;'>";
			tbl=tbl+"<tr><th style='border: 1px solid black;'>Sno</th><th style='border: 1px solid black;'>Rollno</th><th style='border: 1px solid black;'>Name</th><th style='border: 1px solid black;'>Branch</th></tr>";
			while(rs.next())
			{
				String row="<tr>";
				int sno = rs.getInt("sno");
				row = row+"<td style='border: 1px solid black;'>"+sno+"</td>";
				String rollno=rs.getString("rollno");
				row = row+"<td style='border: 1px solid black;'>"+rollno+"</td>";
				String name=rs.getString("name");
				row = row+"<td style='border: 1px solid black;'>"+name+"</td>";
				String branch=rs.getString("branch");
				row = row+"<td style='border: 1px solid black;'>"+branch+"</td></tr>";
				tbl = tbl + row;		
				
				
			}
			tbl = tbl + "</table>";
			PrintWriter pw=response.getWriter();
			pw.println(tbl);
			//step 4:Close connections
			pw.close();
			st.close();
			rs.close();
			con.close();
			
		}catch(Exception e) 
		{
			e.printStackTrace();
			
		}
		
	}

}
