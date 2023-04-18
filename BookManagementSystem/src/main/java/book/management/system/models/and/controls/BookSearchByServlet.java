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
 * Servlet implementation class BookSearchByServlet
 */
@WebServlet("/BookSearchByServlet")
public class BookSearchByServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchType = request.getParameter("search_type");
		LibraryDataManager manager = new LibraryDataManager(request.getServletContext());
		HttpSession session = request.getSession(false);

		if (searchType.equals("book_id")) {

			int bookID = Integer.parseInt(request.getParameter("book_id"));
			List<Book> result = manager.getBookByBookID(bookID);
			session.setAttribute("SearchResult", result);

		} else if (searchType.equals("title")) {

			String bookTitle = request.getParameter("title");
			List<Book> result = manager.getBookByTitle(bookTitle);
			session.setAttribute("SearchResult", result);

		} else if (searchType.equals("author")) {

			String authorLN = request.getParameter("author_last_name");
			String authorFN = request.getParameter("author_first_name");
			List<Book> result = manager.getBookByAuthorName(authorLN, authorFN);
			session.setAttribute("SearchResult", result);

		} else if (searchType.equals("genre")) {
			
			String bookGenre = request.getParameter("genre");
			List<Book> result = manager.getBookByGenre(bookGenre);
			session.setAttribute("SearchResult", result);
			
		}
		RequestDispatcher rd = request.getRequestDispatcher("DisplayBook.jsp");
		rd.forward(request, response);
	}
}
