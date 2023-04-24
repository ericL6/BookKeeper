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
 * Servlet implementation class RemoveAdminServlet
 */
@WebServlet("/RemoveAdminServlet")
public class RemoveAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String currentUser = (String) session.getAttribute("user");
		String adminName = request.getParameter("username");
		if (currentUser.equals(adminName)) {
			request.setAttribute("wasAdminRemoved", "Cannot delete admin that is logged in!");
			RequestDispatcher rd = request.getRequestDispatcher("AddOrRemoveAdminForm.jsp");
			rd.forward(request, response);
		} else {
			LibraryDataManager manager = new LibraryDataManager(request.getServletContext());
			if (manager.removeAdmin(adminName)) {
				request.setAttribute("wasAdminRemoved", "Admin successfully removed!");
				RequestDispatcher rd = request.getRequestDispatcher("AddOrRemoveAdminForm.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("wasAdminRemoved", "Admin was unsuccessfully removed!");
				RequestDispatcher rd = request.getRequestDispatcher("AddOrRemoveAdminForm.jsp");
				rd.forward(request, response);
			}
		}
	}

}
