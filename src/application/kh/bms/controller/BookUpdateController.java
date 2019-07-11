package application.kh.bms.controller;

import java.util.ArrayList;
import java.util.List;

//import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.service.BookModelService;
import application.kh.bms.model.vo.BookModel;

public class BookUpdateController {
//   private LoadSave dao = LoadSave.getDao();
	private BookModelService bs = new BookModelService();
   private List<BookModel> books = new ArrayList<BookModel>();
   private String thisCode = "";

   public void updateBook(String code, String bookName, String category, String author, String publishingHouse, String content) {
      books = bs.selectAll();
      //BookModel nowBook = null;
//      System.out.println(code);
//      int num = 0;
//      for (int i = 0; i < books.size(); i++) {
//         if (books.get(i).getCode().equals(code)) {
//            System.out.println("具具具具");
//            books.get(i).setCode(code);
//            books.get(i).setBookName(bookName);
//            books.get(i).setCategory(category);
//            books.get(i).setAuthor(author);
//            books.get(i).setPublisgingHouse(publishingHouse);
////            System.out.println(i);
////            System.out.println(code);
////            System.out.println(bookName);
////            System.out.println(category);
////            System.out.println(author);
////            System.out.println(publishingHouse);
////            System.out.println(books.get(i).getBookName());
////            System.out.println(books.get(i).getPublishingHouse());
//            
//            num = i;
//            break;
//            
//         }
//      }
      BookModel b = new BookModel();
      b.setCode(code);
      b.setBookName(bookName);
      b.setCategory(category);
      b.setAuthor(author);
      b.setPublisgingHouse(publishingHouse);
      b.setContent(content);
      
      bs.updateBook(b);
   
//      b.setContent(content);
//      dao.saveBook(books);
//      System.out.println(books.get(num).getPublishingHouse());
   }


}