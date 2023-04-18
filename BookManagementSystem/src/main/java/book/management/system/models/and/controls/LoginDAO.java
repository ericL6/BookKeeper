package book.management.system.models.and.controls;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;


public class LoginDAO {
	private String dbUrl;
	private String dbUser;
	private String dbPass;
	
	public LoginDAO(ServletContext context) {
		dbUrl = context.getInitParameter("dbUrl");
		dbUser = context.getInitParameter("dbUser");
		dbPass = context.getInitParameter("dbPass");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean logIn(String username, String password, int admin) {
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			PreparedStatement stmt = con.prepareStatement("select * from users where username=? and password=? and admin=?");) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setInt(3, admin);
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
		return false;
	}
}
