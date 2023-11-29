

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Registration")
public class Registration extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		try {
			
			String uname=request.getParameter("username");
			String uemail=request.getParameter("useremail");
			String upass=request.getParameter("userpassword");
			long unumber=Long.parseLong(request.getParameter("usernumber"));
			
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/birt","root","Megha@123");
			String check="select * from e3 where useremail=?";
			PreparedStatement cpre=con.prepareStatement(check);
			cpre.setString(1, uemail);
			ResultSet rs1=cpre.executeQuery();
			if(rs1.next()) {
				pw.println(" user already exist");
			}
			else {
				String str="insert into e3 values(?,?,?,?)";
				PreparedStatement ps=con.prepareStatement(str);
			
				ps.setString(1, uemail);
				ps.setString(2, uname);
				ps.setLong(3, unumber);
				ps.setString(4, upass);
				
				ps.execute();
				pw.println("success");
				response.sendRedirect("Fir.jsp");
			}
		   }
		catch(Exception e)
		{
			pw.println(e);
			e.printStackTrace(); 
		}
	}

}
