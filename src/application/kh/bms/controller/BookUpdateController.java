package application.kh.bms.controller;

import java.util.ArrayList;

import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.BookModel;

public class BookUpdateController {
   private LoadSave dao = LoadSave.getDao();
   private ArrayList<BookModel> books = null;
   private String thisCode = "";

   public void updateBook(String code, String bookName, String category, String author, String publishingHouse) {
      ArrayList<BookModel> books = dao.loadBook();
      BookModel nowBook = null;
      System.out.println(code);
      int num = 0;
      for (int i = 0; i < books.size(); i++) {
         if (books.get(i).getCode().equals(code)) {
            System.out.println("具具具具");
            books.get(i).setCode(code);
            books.get(i).setBookName(bookName);
            books.get(i).setCategory(category);
            books.get(i).setAuthor(author);
            books.get(i).setPublisgingHouse(publishingHouse);
            System.out.println(i);
            System.out.println(code);
            System.out.println(bookName);
            System.out.println(category);
            System.out.println(author);
            System.out.println(publishingHouse);
            System.out.println(books.get(i).getBookName());
            System.out.println(books.get(i).getPublishingHouse());
            num = i;
            break;
            
         }
      }
      dao.saveBook(books);
      System.out.println(books.get(num).getPublishingHouse());
   }


}