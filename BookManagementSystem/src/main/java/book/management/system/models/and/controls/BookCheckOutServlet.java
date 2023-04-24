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
 * Servlet implementation class BookCheckOutServlet
 */
@WebServlet("/BookCheckOutServlet")
public class BookCheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookCheckOutType = request.getParameter("check_out_type");
		HttpSession session = request.getSession(false);
		LibraryDataManager manager = new LibraryDataManager(request.getServletContext());
		Boolean wasBookCheckOut = false;

		if (bookCheckOutType.equals("book_id")) {
			int bookID = Integer.parseInt(request.getParameter("book_id"));
			String username = (String) session.getAttribute("user");

			if (manager.bookCheckOutByID(username, bookID)) {
				wasBookCheckOut = true;
			}
		} else if (bookCheckOutType.equals("book_info")) {
			String username = (String) session.getAttribute("user");
			String title = request.getParameter("title");
			String authorLN = request.getParameter("author_last_name");
			String authorFN = request.getParameter("author_first_name");
			String genre = request.getParameter("genre");
			int bookID = manager.getBookID(title, authorLN, authorFN, genre);
			
			if (manager.bookCheckOutByID(username, bookID)) {
				wasBookCheckOut = true;
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("BookCheckOutForm.jsp");
		if (wasBookCheckOut) {
			request.setAttribute("successMessage", "Book successfully checkout!");
		} else {
			request.setAttribute("errorMessage", "Book checkout failed!");
		}
		rd.forward(request, response);
	}

}
