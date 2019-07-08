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
	private String code ; //å �ڵ� ��ȣ
	private String bookName ;  // ������
	private String author ; //����
	private String publishingHouse; //���ǻ�
	private String category ; // �帣
	private boolean rental; //�뿩����
	private String content;
	


	public BookModel() {
	}
	
	{
		rental = false;
	}

	public BookModel(String code, String bookName, String author, String publisgingHouse, String category,String content) {
		super();
		this.code = code;
		this.bookName = bookName;
		this.author = author;
		this.publishingHouse = publisgingHouse;
		this.category = category;
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
