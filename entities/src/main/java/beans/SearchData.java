package beans;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import java.util.Date;

public class SearchData {
	private static final long serialVersionUID = 1L;
	@Column
	@NotEmpty
	private Integer id;
	@Column
	@NotEmpty
	private String title;
	@Column
	@NotEmpty
	private String title4menu;
	@Column
	@NotEmpty
	private String author;
	@Column
	@NotEmpty
	private Date date;
	@Column
	@NotEmpty
	private String item;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle4menu() {
		return title4menu;
	}

	public void setTitle4menu(String title4menu) {
		this.title4menu = title4menu;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
}

