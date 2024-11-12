

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


@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public signup() {
        super();
        
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u=request.getParameter("user");
		String p=request.getParameter("pass");
		String m=request.getParameter("mo");
		String cp=request.getParameter("cpass");
		String po = null;
		PrintWriter out=response.getWriter();
		if(p.equals(cp))
		{
			 po=p;
		}
		else
		{
			request.getRequestDispatcher("signup.html").include(request, response);
			out.print("<p color:'red'>password and confirm password should be same.</p>");
		}
		if(po==p)
		{
		try {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","akash");
		PreparedStatement pt=con.prepareStatement("insert into us values('"+u+"','"+po+"','"+m+"')");
		pt.executeUpdate();
		out.print("your sign-up process is complete you can login now.!");
		request.getRequestDispatcher("login.html").forward(request, response);
		
		con.close();
		}
		catch (Exception e) {
			System.out.print(e);
		}
		}
		
	}
}
