package book.management.system.models.and.controls;

public class Book {
	private int id;
	private String title;
	private String authorLastName;
	private String authorFirstName;
	private String genre;

	public Book(int id, String title, String authorLastName, String authorFirstName, String genre) {
		this.id = id;
		this.title = title;
		this.authorLastName = authorLastName;
		this.authorFirstName = authorFirstName;
		this.genre = genre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	public String getAuthorFirstName() {
		return authorFirstName;
	}

	public void setAuthroFirstName(String authroFirstName) {
		this.authorFirstName = authroFirstName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
