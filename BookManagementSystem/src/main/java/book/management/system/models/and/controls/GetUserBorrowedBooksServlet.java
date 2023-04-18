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
 * Servlet implementation class GetUserBorrowedBooksServlet
 */
@WebServlet("/GetUserBorrowedBooksServlet")
public class GetUserBorrowedBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String)session.getAttribute("user");
		
		LibraryDataManager manager = new LibraryDataManager(request.getServletContext());
		List<Book> userBorrowedBooks = manager.getBorrowedBooksByUser(username);
		session.setAttribute("borrowBooks", userBorrowedBooks);
		
		RequestDispatcher rd = request.getRequestDispatcher("ReturnBookPage.jsp");
		rd.forward(request, response);
	}

}
