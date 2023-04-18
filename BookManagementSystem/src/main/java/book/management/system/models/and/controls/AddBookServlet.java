package book.management.system.models.and.controls;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String authorLN = request.getParameter("author_last_name");
		String authorFN = request.getParameter("author_first_name");
		String genre = request.getParameter("genre");
		
		LibraryDataManager manager = new LibraryDataManager(request.getServletContext());
		if (manager.addBook(title, authorLN, authorFN, genre)) {
			request.setAttribute("wasBookAdded", "Book successfully added!");
			RequestDispatcher rd = request.getRequestDispatcher("AddNewBookForm.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("wasBookAdded", "Book already exist!");
			RequestDispatcher rd = request.getRequestDispatcher("AddNewBookForm.jsp");
			rd.forward(request, response);
		}
	}

}
