

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public login() {
        super();
           }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u=request.getParameter("user");
		String p=request.getParameter("pass");
		PrintWriter out=response.getWriter();
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system", "akash");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select username,password from us where username='"+u+"' and password='"+p+"'");
			int a=0;
			while(rs.next())
			{
				a=1;
			}
			if(a==1) {
			request.getRequestDispatcher("welcome.html").forward(request, response);	
			}
			else {
				request.getRequestDispatcher("login.html").include(request, response);
				out.print("wrong username or password");
			}
			con.close();
		} catch (SQLException e) {
			
			System.out.println(e);
		}
	}

}
