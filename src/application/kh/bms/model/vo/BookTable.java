package application.kh.bms.model.vo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookTable {

	private final SimpleStringProperty code;
	private final SimpleStringProperty bookName;
	private final SimpleStringProperty author;
	private final SimpleStringProperty publishingHouse;
	private final SimpleStringProperty category;
	private final SimpleBooleanProperty rental;
//	private final SimpleStringProperty rentalCheck;

	public BookTable() {
		this(null, null, null, null, null, false);
	}

	public BookTable(String code, String bookName, String author, String publishingHouse, String category,
			Boolean rental) {
		super();
		this.code = new SimpleStringProperty(code);
		this.bookName = new SimpleStringProperty(bookName);
		this.author = new SimpleStringProperty(author);
		this.publishingHouse = new SimpleStringProperty(publishingHouse);
		this.category = new SimpleStringProperty(category);
		this.rental = new SimpleBooleanProperty(rental);
	}

	public String getCode() {
		return code.get();
	}

	public void setCode(String code) {
		this.code.set(code);
	}

	public StringProperty codeProperty() {
		return code;
	}

	
	public String getBookName() {
		return bookName.get();
	}

	public void setBookName(String bookName) {
		this.bookName.set(bookName);
	}

	public StringProperty bookNameProperty() {
		return bookName;
	}

	
	public String getAuthor() {
		return author.get();
	}

	public void setAuthor(String author) {
		this.author.set(author);
	}

	public StringProperty authorProperty() {
		return author;
	}

	
	public String getPublishingHouse() {
		return publishingHouse.get();
	}

	public void setPublishingHouse(String publishingHouse) {
		this.publishingHouse.set(publishingHouse);
	}

	public StringProperty publishingHouseProperty() {
		return publishingHouse;
	}

	
	public String getCategory() {
		return category.get();
	}

	public void setCategory(String category) {
		this.category.set(category);
	}

	public StringProperty categoryProperty() {
		return category;
	}

	
	public boolean isRental() {
		return rental.get();
	}
	
	public void setRental(boolean rental) {
		this.rental.set(rental);
	}

	public SimpleBooleanProperty getRental() {
		return rental;
	}

	public BooleanProperty rentalProperty() {
		return rental;
	}

	@Override
	public String toString() {
		return "BookTable [code=" + code + ", bookName=" + bookName + ", author=" + author + ", publishingHouse="
				+ publishingHouse + ", category=" + category + ", rental=" + rental + "]";
	}

}
