package application.kh.bms.controller;

import java.util.ArrayList;
import java.util.List;

import application.kh.bms.model.service.BookModelService;
import application.kh.bms.model.service.RentalService;
import application.kh.bms.model.vo.BookModel;
import application.kh.bms.model.vo.Rental;

public class BookController {
	// �ٲ㺼���~??

	int count = 0;
	BookModel book = new BookModel();
	List<BookModel> books = new ArrayList<BookModel>();
	List<BookModel> realBooks = null;
	private BookModelService bs = new BookModelService();
	private RentalService rs = new RentalService();

	public List<BookModel> getBooks() {
		books = bs.selectAll();
		return books;
	}

	public void setBooks(List<BookModel> books) {
		this.books = books;
	}

	public boolean checkCode(String code) {
		boolean result = true; // true : �ߺ��� ���, �߰� ����
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getCode().equals(code)) {
				result = false; // ������ ������, true -> false
				break; // false : �ߺ��� ���� -> �߰� �Ұ���.
			}
		}
		return result; // �� ���� ��� �Ҹ� �� ����!!
	}

	public boolean addBook(BookModel newBook) {
//		books = dao.loadBook();
		boolean isSucc = false;
		if (checkCode(newBook.getCode())) {
			int result = bs.addBook(newBook);
			if (result > 0) {
				isSucc = true;
			}
		} else {
			System.out.println("�ߺ�");
			isSucc = false;
		}
		return isSucc;
	}

	public void remove(String bookCode) {
		realBooks = bs.selectAll();
		ArrayList<Rental> temp = rs.selectBookCode(bookCode);
		if (temp.size() == 0) {
			bs.deleteBook(bookCode);
		}
	}
	
	public BookModel loadBook(String code) {
		BookModel b = bs.oneBookSelect(code);
		return b;
	}

}
