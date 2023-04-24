package book.management.system.models.and.controls;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int admin = Integer.parseInt(request.getParameter("user_or_admin"));
		LoginDAO loginBean = new LoginDAO(request.getServletContext());
		
		if (loginBean.logIn(username, password, admin) == false) {
			request.setAttribute("errorMessage", "Invalid username and/or password");
			RequestDispatcher rd = request.getRequestDispatcher("Index.jsp");
			rd.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loggedIn", true);
			session.setAttribute("user", username);
			
			if (admin == 0) {
				session.setAttribute("admin", false);				
				RequestDispatcher rd = request.getRequestDispatcher("UserHomePage.jsp");
				rd.forward(request, response);
			} else {
				session.setAttribute("admin", true);				
				RequestDispatcher rd = request.getRequestDispatcher("AdminHomePage.jsp");
				rd.forward(request, response);
			}
		}
	}
}
