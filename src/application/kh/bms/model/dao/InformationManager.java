package application.kh.bms.model.dao;

import application.kh.bms.model.vo.BookModel;
import application.kh.bms.model.vo.User;

public class InformationManager {
	private User nowUser = new User();
	private BookModel nowBook = new BookModel();
	private static InformationManager informationManager = new InformationManager();

	private InformationManager() {
	}

	public User getNowUser() {
		return nowUser;
	}

	public void setNowUser(User temp) {
		nowUser = temp;
	}
	
	public static InformationManager getInformationManager() {
		return informationManager;
	}
	
	public BookModel getNowBook() {
		return nowBook;
	}
	
	public void setNowBook(BookModel b) {
		nowBook = b;
	}
}
