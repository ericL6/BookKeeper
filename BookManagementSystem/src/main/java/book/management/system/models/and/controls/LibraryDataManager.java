package book.management.system.models.and.controls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

public class LibraryDataManager {
	private String dbUrl;
	private String dbUser;
	private String dbPass;

	public LibraryDataManager(ServletContext context) {
		dbUrl = context.getInitParameter("dbUrl");
		dbUser = context.getInitParameter("dbUser");
		dbPass = context.getInitParameter("dbPass");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean addUser(String username, String password, int admin) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement("insert into users values(?, ?, ?)");) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setInt(3, admin);

			if (containsUser(username)) {
				return false;
			}

			int result = stmt.executeUpdate();
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public List<String> getUsers() {
		List<String> userList = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement("select * from users where admin=0");) {
			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					userList.add(rs.getString(1));
				}
				return userList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean removeUser(String username) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement("delete from user_book_checkout where username=?");
				PreparedStatement stmt2 = con.prepareStatement("delete from users where username=? and admin=0");) {
			stmt.setString(1, username);
			stmt.executeUpdate();
			stmt2.setString(1, username);
			stmt2.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Boolean containsUser(String username) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement("select * from users where username=?");) {
			stmt.setString(1, username);
			try (ResultSet rs = stmt.executeQuery();) {
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return true;
	}

	public boolean removeAdmin(String username) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement("delete from users where username=? and admin=1");) {
			stmt.setString(1, username);
			if (stmt.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Book> getAllBook() {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement("select * from books");) {
			try (ResultSet rs = stmt.executeQuery();) {
				return BookResultSetToList(rs);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public List<Book> getBookByBookID(int bookID) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement("select * from books where book_id=?");) {
			stmt.setInt(1, bookID);
			try (ResultSet rs = stmt.executeQuery();) {
				return BookResultSetToList(rs);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public List<Book> getBookByTitle(String title) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement("select * from books where title=?");) {
			stmt.setString(1, title);
			try (ResultSet rs = stmt.executeQuery();) {
				return BookResultSetToList(rs);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public List<Book> getBookByAuthorName(String authorLastName, String authorFirstName) {
		PreparedStatement stmt = null;
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);) {
			if (authorFirstName == null || authorFirstName.isEmpty() || authorFirstName.isBlank()) {
				stmt = con.prepareStatement("select * from books where author_last_name=?");
				stmt.setString(1, authorLastName);
			} else {
				stmt = con.prepareStatement("select * from books where author_last_name=? and author_first_name=?");
				stmt.setString(1, authorLastName);
				stmt.setString(2, authorFirstName);
			}
			try (ResultSet rs = stmt.executeQuery();) {
				return BookResultSetToList(rs);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public List<Book> getBookByGenre(String genre) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement("select * from books where genre=?");) {
			stmt.setString(1, genre);
			try (ResultSet rs = stmt.executeQuery();) {
				return BookResultSetToList(rs);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public List<Book> BookResultSetToList(ResultSet rs) {
		List<Book> list = new ArrayList<>();
		try {
			while (rs.next()) {
				list.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean containsBook(int BookID) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement("select * from books where book_id=?");) {
			stmt.setInt(1, BookID);
			try (ResultSet rs = stmt.executeQuery();) {
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean containsBook(String title, String authorLN, String authorFN, String genre) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement(
						"select * from books where title=? and author_last_name=? and author_first_name=? and genre=?");) {
			stmt.setString(1, title);
			stmt.setString(2, authorLN);
			stmt.setString(3, authorFN);
			stmt.setString(4, genre);
			try (ResultSet rs = stmt.executeQuery();) {
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getBookID(String title, String authorLN, String authorFN, String genre) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement(
						"select * from books where title=? and author_last_name=? and author_first_name=? and genre=?");) {
			stmt.setString(1, title);
			stmt.setString(2, authorLN);
			stmt.setString(3, authorFN);
			stmt.setString(4, genre);
			if (!containsBook(title, authorLN, authorFN, genre)) {
				return -1;
			}
			try (ResultSet rs = stmt.executeQuery();) {
				if (rs.next()) {
					return rs.getInt(1);
				} else {
					return -1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public boolean userHaveBook(String username, int bookID) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con
						.prepareStatement("select * from user_book_checkout where username=? and book_id=?");) {
			stmt.setString(1, username);
			stmt.setInt(2, bookID);
			try (ResultSet rs = stmt.executeQuery();) {
				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean bookCheckOutByID(String username, int bookID) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement("insert into user_book_checkout values(?, ?)");) {
			if (!containsBook(bookID)) {
				return false;
			}
			if (userHaveBook(username, bookID)) {
				return false;
			}

			stmt.setString(1, username);
			stmt.setInt(2, bookID);
			int result = stmt.executeUpdate();
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean bookCheckOutByInfo(String username, String title, String authorLN, String authorFN, String genre) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement("insert into user_book_checkout values(?, ?)");) {
			if (!containsBook(title, authorLN, authorFN, genre)) {
				return false;
			}
			int bookID = getBookID(title, authorLN, authorFN, genre);
			if (bookID == -1) {
				return false;
			}
			if (userHaveBook(username, bookID)) {
				return false;
			}

			stmt.setString(1, username);
			stmt.setInt(2, bookID);
			int result = stmt.executeUpdate();
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Book> getBorrowedBooksByUser(String username) {
		ArrayList<Book> booklist = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement("select * from user_book_checkout where username=?");
				PreparedStatement stmt2 = con.prepareStatement("select * from books where book_id=?");) {
			stmt.setString(1, username);

			try (ResultSet rs = stmt.executeQuery();) {
				while (rs.next()) {
					stmt2.setInt(1, rs.getInt(2));
					try (ResultSet rs2 = stmt2.executeQuery();) {
						if (rs2.next()) {
							booklist.add(new Book(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getString(4),
									rs2.getString(5)));
						}
					}
				}
				return booklist;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean returnBook(String username, int BookID) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con
						.prepareStatement("delete from user_book_checkout where username=? and book_id=?");) {
			stmt.setString(1, username);
			stmt.setInt(2, BookID);
			int result = stmt.executeUpdate();
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addBook(String title, String authorLN, String authorFN, String genre) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement(
						"insert into books (title, author_last_name, author_first_name, genre) values(?,?,?,?)");) {
			stmt.setString(1, title);
			stmt.setString(2, authorLN);
			stmt.setString(3, authorFN);
			stmt.setString(4, genre);

			if (containsBook(title, authorLN, authorFN, genre)) {
				return false;
			}

			int result = stmt.executeUpdate();
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeBookByID(int bookID) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement("delete from books where book_id=?");) {
			stmt.setInt(1, bookID);

			if (stmt.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeBookByInfo(String title, String authorLN, String authorFN, String genre) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				PreparedStatement stmt = con.prepareStatement(
						"delete from books where title=? and author_last_name=? and author_first_name=? and genre=?");) {
			stmt.setString(1, title);
			stmt.setString(2, authorLN);
			stmt.setString(3, authorFN);
			stmt.setString(4, genre);

			if (stmt.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
