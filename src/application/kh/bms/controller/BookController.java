package application.kh.bms.controller;

import java.util.ArrayList;
import java.util.List;

import application.kh.bms.model.service.BookModelService;
import application.kh.bms.model.vo.BookModel;

public class BookController {
	//바꿔볼까요~??

	int count = 0;
	BookModel book = new BookModel();
	List<BookModel> books = new ArrayList<BookModel>();
	List<BookModel> realBooks = null;
	private BookModelService bs = new BookModelService();

	public List<BookModel> getBooks() {
		books = bs.selectAll();
		return books;
	}

	public void setBooks(List<BookModel> books) {
		this.books = books;
	}

	public boolean checkCode(String code) {
		boolean result = true; // true : 중복값 없어서, 추가 가능
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getCode().equals(code)) {
				result = false; // 같은게 있으면, true -> false
				break; // false : 중복값 존재 -> 추가 불가능.
			}
		}
		return result; // 다 돌린 결과 불린 값 리턴!!
	}

	public boolean addBook(BookModel newBook) {
//		books = dao.loadBook();
		boolean isSucc = false;
		if (checkCode(newBook.getCode())) {
			int result = bs.addBook(newBook);
			if(result>0) {
				isSucc = true;
			}
		} else {
			System.out.println("중복");
			isSucc = false;
		}
		return isSucc;
	}
	

	public void remove(String bookCode) {
		realBooks = bs.selectAll();
		BookModel b = new BookModel();
		if (realBooks != null) {
			for (int i = 0; i < realBooks.size(); i++) {
				if (realBooks.get(i).getCode().equals(bookCode)) {
					if (!realBooks.get(i).isRental()) {
						b = realBooks.get(i);
						bs.deleteBook(b.getCode());
						break;
					}
				}
			}
		}
	}
	
	

}
