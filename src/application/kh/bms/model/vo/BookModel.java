package application.kh.bms.model.vo;

import java.io.Serializable;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class BookModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2967084248634846898L;
	private String code ; //책 코드 번호
	private String bookName ;  // 도서명
	private String author ; //저자
	private String publishingHouse; //출판사
	private String category ; // 장르
	private boolean rental; //대여여부 
	


	public BookModel() {
	}
	
	{
		rental = false;
	}

	public BookModel(String code, String bookName, String author, String publisgingHouse, String category) {
		super();
		this.code = code;
		this.bookName = bookName;
		this.author = author;
		this.publishingHouse = publisgingHouse;
		this.category = category;
//		this.rental = rental;
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishingHouse() {
		return publishingHouse;
	}

	public void setPublisgingHouse(String publisgingHouse) {
		this.publishingHouse = publisgingHouse;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isRental() {
		return rental;
	}

	public void setRental(boolean rental) {
		this.rental = rental;
	}

}
