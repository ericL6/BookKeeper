package book.management.system.models.and.controls;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAdminRegisterServlet
 */
@WebServlet("/UserAdminRegisterServlet")
public class UserAdminRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("password_confirm");
		int admin = Integer.parseInt(request.getParameter("admin"));
		String path = "";
		if (admin == 1) {
			path = "AddOrRemoveAdminForm.jsp";
		} else {
			path = "UserRegisterForm.jsp";
		}
		if (!password.equals(confirmPassword)) {
			request.setAttribute("errorMessage", "password does not match, please try again.");
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		} else {
			LibraryDataManager manager = new LibraryDataManager(request.getServletContext());
			if (manager.addUser(username, password, admin) == true) {
				request.setAttribute("successMessage", "Account successfully created!");
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Username already exist, pick a different username.");
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
			}
		}
	}

}
