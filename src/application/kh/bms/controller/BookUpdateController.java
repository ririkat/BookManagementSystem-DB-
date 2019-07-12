package application.kh.bms.controller;

import java.util.ArrayList;
import java.util.List;

//import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.service.BookModelService;
import application.kh.bms.model.vo.BookModel;

public class BookUpdateController {
	private BookModelService bs = new BookModelService();
	private List<BookModel> books = new ArrayList<BookModel>();
	private String thisCode = "";

	public void updateBook(String code, String bookName, String category, String author, String publishingHouse,
			String content) {
		books = bs.selectAll();
		BookModel b = new BookModel();
		b.setCode(code);
		b.setBookName(bookName);
		b.setCategory(category);
		b.setAuthor(author);
		b.setPublisgingHouse(publishingHouse);
		b.setContent(content);

		bs.updateBook(b);

	}

}