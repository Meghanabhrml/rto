

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
import javax.servlet.http.HttpSession;


@WebServlet("/first")
public class first extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		try
		{
		String vusername1=request.getParameter("vusername");
		String vpass1=request.getParameter("vpass");
		Connection cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/birt","root","Megha@123");
		 String check="select * from registration where username=? and password=?";
		 PreparedStatement pre=cn.prepareStatement(check);
		 pre.setString(1,vusername1);
		 pre.setString(2, vpass1);
		ResultSet rs=pre.executeQuery();
		if(rs.next()){
			
			HttpSession session=request.getSession();
			session.setAttribute("veusername", vusername1);
			session.setAttribute("vpass", vpass1);
			response.sendRedirect("Fir.jsp");
			
			//jstl : java standard tag library used in jsp-->i.e ${mention parameter name} 
					}
		else{
			pw.println("invalid credentials");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
