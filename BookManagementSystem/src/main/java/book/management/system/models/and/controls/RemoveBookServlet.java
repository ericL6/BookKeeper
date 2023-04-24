package book.management.system.models.and.controls;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveBookServlet
 */
@WebServlet("/RemoveBookServlet")
public class RemoveBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String removeBookBy = request.getParameter("remove_book_by");
		LibraryDataManager manager = new LibraryDataManager(request.getServletContext());
		Boolean wasBookRemoved = false;
		
		if (removeBookBy.equals("book_id")) {
			int bookID = Integer.parseInt(request.getParameter("book_id"));
			
			if (manager.removeBookByID(bookID)) {
				wasBookRemoved = true;
			} 
		} else if (removeBookBy.equals("book_info")) {
			String title = request.getParameter("title");
			String authorLN = request.getParameter("author_last_name");
			String authorFN = request.getParameter("author_first_name");
			String genre = request.getParameter("genre");
			
			if (manager.removeBookByInfo(title, authorLN, authorFN, genre)) {
				wasBookRemoved = true;
			} 
		}
		RequestDispatcher rd = request.getRequestDispatcher("RemoveBookForm.jsp");
		if (wasBookRemoved) {
			request.setAttribute("wasBookRemoved", "Book was successfully removed!");
		} else {
			request.setAttribute("wasBookRemoved", "Book does not exist for removal!");

		}
		rd.forward(request, response);
	}
}
