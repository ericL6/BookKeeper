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
 * Servlet implementation class ReturnBookServlet
 */
@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("user");
		int bookID = Integer.parseInt(request.getParameter("book_id"));
		
		LibraryDataManager manager = new LibraryDataManager(request.getServletContext());
		if (manager.returnBook(username, bookID)) {
			request.setAttribute("bookReturnStatus", "Book successfully returned!");
			RequestDispatcher rd = request.getRequestDispatcher("GetUserBorrowedBooksServlet");
			rd.forward(request, response);
		} else {
			request.setAttribute("bookReturnStatus", "An error had occur, book was unsuccessfully returned!");
			RequestDispatcher rd = request.getRequestDispatcher("GetUserBorrowedBooksServlet");
			rd.forward(request, response);
		}
		
	}

}
