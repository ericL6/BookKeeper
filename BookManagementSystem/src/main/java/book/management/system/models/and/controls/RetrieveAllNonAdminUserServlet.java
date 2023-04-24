package book.management.system.models.and.controls;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RetrieveAllNonAdminUserServlet
 */
@WebServlet("/RetrieveAllNonAdminUserServlet")
public class RetrieveAllNonAdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibraryDataManager manager = new LibraryDataManager(request.getServletContext());
		List<String> userList = manager.getUsers();
		
		HttpSession session = request.getSession(false);
		session.setAttribute("userlist", userList);
		
		RequestDispatcher rd = request.getRequestDispatcher("DisplayUsers.jsp");
		rd.forward(request, response);
	}

}
