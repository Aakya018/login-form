

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/forgot")
public class forgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public forgot() {
        super();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u=request.getParameter("user");
		String m=request.getParameter("mo");
		PrintWriter out=response.getWriter();
		String p = null;
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","akash");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from us where username='"+u+"' and mobileno='"+m+"'");
			int a=0;
			while(rs.next())
			{
				a=1;
			 p=rs.getString(2);
			}
			if(a==1)
			{
				out.print("\n <a href='login'>click here to login</a>");
				out.print("your password is "+p);

			}
			else
			{
				request.getRequestDispatcher("forgot.html").include(request, response);
				out.print("incorrect details");
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
