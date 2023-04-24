package book.management.system.models.and.controls;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveUserServlet
 */
@WebServlet("/RemoveUserServlet")
public class RemoveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		LibraryDataManager manager = new LibraryDataManager(request.getServletContext());
		if (manager.removeUser(username)) {
			request.setAttribute("userRemoved", "User successfully removed!");
			RequestDispatcher rd = request.getRequestDispatcher("RetrieveAllNonAdminUserServlet");
			rd.forward(request, response);
		} else {
			request.setAttribute("userRemoved", "User was unsuccessfully removed!");
			RequestDispatcher rd = request.getRequestDispatcher("RetrieveAllNonAdminUserServlet");
			rd.forward(request, response);
		}
	}
}
