//package application.kh.bms.view;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//import application.kh.bms.controller.BookController;
//import application.kh.bms.controller.BookSearchController;
//import application.kh.bms.model.dao.LoadSave;
//import application.kh.bms.model.vo.BookModel;
//import application.kh.bms.model.vo.BookTable;
//import application.kh.bms.model.vo.SelectedBook;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.stage.Stage;
//
//public class AdminSearchView implements Initializable {
//   
//      
//      @FXML private Button viewAll;   //전체보기
//      
//      BookModel book = new BookModel();
//      ArrayList<BookModel> realBooks = null;
//      
//
//   
//   //어드민 서치 관리자 책 추가 수정 삭제 할 수 있는 화면
//   //
//
//   @FXML private Button btnRemove;
//   @FXML private Button btnbtnEdit;
//   @FXML private Button btnEdit, loodDetailBtn;
//   @FXML private Button btnAddBook;
//   @FXML private Button btnSelect, btnBack;   //조회
//   @FXML private Button btnAll;   //전체보기
//   
//   static BookTable model = new BookTable();
//
//   @FXML private TextField tfWord;
//
//   @FXML private ComboBox<String> comboBox;   //구분선택
//
//   @FXML private TableView<BookTable> tableView;
//   @FXML private TableColumn<BookTable, String> codeCol;   //번호
//   @FXML private TableColumn<BookTable, String> nameCol;   //도서명
//   @FXML private TableColumn<BookTable, String> authorCol;   //저자
//   @FXML private TableColumn<BookTable, String> pubCol;   //출판사
//   @FXML private TableColumn<BookTable, String> cateCol;   //장르
//   @FXML private TableColumn<BookTable, Boolean> rentalCol;//대여여부
//
//   BookSearchController bookSearchController = new BookSearchController();
//   BookController bookController = new BookController();
//   ArrayList<BookTable> books = new ArrayList<BookTable>();
//   private LoadSave dao = LoadSave.getDao();
//   private ArrayList<BookModel> temp = dao.loadBook();
//   
//   
//
//   
//
//   //테이블열선택
//   public int row=-1;
//
//   //콤보박스 list
//   private ObservableList<String> comboList = FXCollections.observableArrayList("도서명","저자","출판사","장르");
//
//   private static String tfsel;   //텍스트필드 저장용
//   private static String combosel;   //콤보박스 저장용
//   
//   
//   
//   
//   
//   public static BookTable selBook = new BookTable();
//
//   public static BookTable getSelBook() {
//      return selBook;
//   }
//
//   public static void setSelectUser(BookModel selectBook) {
//      AdminSearchView.selBook = selBook;
//   }
//   
//   
//   
//   
//   
//   
//   static {
//      tfsel = "";
//      combosel = "";
//   }
//
//   //전체조회 테이블용
//   public ObservableList<BookTable> bookList = FXCollections.observableArrayList();
//
//   //선택조회 테이블용
//   public ObservableList<BookTable> selectBookList = FXCollections.observableArrayList();
//
//   @Override
//   public void initialize(URL location, ResourceBundle resources) {
//
//            
//      //버튼 클릭했을 때 조회 되는거랑 // 수정되는 메소드 
//
//      codeCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("code"));
//      nameCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("bookName"));
//      authorCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("author"));
//      pubCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("publishingHouse"));
//      cateCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("category"));
//      rentalCol.setCellValueFactory(new PropertyValueFactory<BookTable, Boolean>("rental"));
//   
//      books = bookSearchController.bookTableLoad();
//
//      for(int i = 0; i < books.size(); i++) {
//         BookTable temp = books.get(i);
//         bookList.add(books.get(i));
//      }
//      tableView.setItems(bookList);
//
//      //      lookDetailBtn.setDisable(true);
//
//      comboBox.setItems(comboList);
//
//      
//
//
//      tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookTable>()
//      {
//         public void changed(ObservableValue<? extends BookTable> observable, BookTable oldValue, BookTable newValue)
//         {
//            System.out.println("dddd");
//            model = tableView.getSelectionModel().getSelectedItem();
//
//            System.out.println("선택된 Item의 Index" +  tableView.getSelectionModel().getSelectedIndex());
//            System.out.println("선택된 행의 도서코드 : " + model.getCode());
//         }
//      });
//   }
//   
//   @FXML
//   public void runRemove() {
//      
//      bookController.remove(tableView.getSelectionModel().getSelectedItem().getCode());
//
//   }
//   
//   @FXML
//   public void moveBack() {
//      try {
//         Stage newStage = new Stage();
//         Parent root = FXMLLoader
//               .load(getClass().getClassLoader().getResource("application/kh/bms/view/MainSearch.fxml"));
//         Scene scene = new Scene(root);
//         newStage.setScene(scene);
//         newStage.setTitle("도서 조회");
//         newStage.show();
//
//         Stage primaryStage = (Stage)btnBack.getScene().getWindow();
//         primaryStage.close();
//
//      } catch (IOException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//   }
//   
//   
//   @FXML
//   public void addBook()
//   {
//      try {
//         Stage newStage = new Stage();
//         Parent root = FXMLLoader
//               .load(getClass().getClassLoader().getResource("application/kh/bms/view/AddBook.fxml"));
//         Scene scene = new Scene(root);
//         newStage.setScene(scene);
//         newStage.setTitle("Book Management System");
//         newStage.show();
//
//         Stage primaryStage = (Stage)btnAddBook.getScene().getWindow();
//         primaryStage.close();
//
//      } catch (IOException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//   }
//   
//   
//   
//      
//   
//   @FXML
//   public void editBook()
//   {
//      
//      try {
//         System.out.println("받아왔니? : " + model.getCode());
//         //detailPageView.loadSelectedRow(model);
//         SelectedBook.selBook.setBookName(model.getBookName());
//         SelectedBook.selBook.setAuthor(model.getAuthor());
//         SelectedBook.selBook.setPublishingHouse(model.getPublishingHouse());
//         SelectedBook.selBook.setCategory(model.getCategory());
//         SelectedBook.selBook.setRental(model.getRental().get());
//         SelectedBook.selBook.setCode(model.getCode());
//         System.out.println("들어갔니? : " + SelectedBook.selBook.getCode());
//         Stage newStage = new Stage();
//         Parent root = FXMLLoader
//               .load(getClass().getClassLoader().getResource("application/kh/bms/view/BookUpdateView2.fxml"));
//         Scene scene = new Scene(root);
//         newStage.setScene(scene);
//         newStage.setTitle("Book Management System");
//         newStage.show();
//
//         Stage primaryStage = (Stage)btnEdit.getScene().getWindow();
//
//         primaryStage.close();
//         
//         
//         
//
//      } catch (IOException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//   }
//   
//   
//   
//
//   
//   //전체보기 버튼 클릭 액션 메소드
//   public void viewAll() {
//
//      bookList.clear();
//      for (int i = 0; i < books.size(); i++) {
//         bookList.add(books.get(i));
//      }
//      tableView.setItems(bookList);
//
//      tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookTable>()
//      {
//         public void changed(ObservableValue<? extends BookTable> observable, BookTable oldValue, BookTable newValue)
//         {
//            
//            model = tableView.getSelectionModel().getSelectedItem();
//
//            System.out.println("선택된 Item의 Index" +  tableView.getSelectionModel().getSelectedIndex());
//            System.out.println("선택된 행의 도서코드 : " + model.getCode());
//         }
//      });
//
//   }
//
//
//   //조회버튼 클릭 메소드
//   @FXML
//   public void openSelectView() {
//
//      if(comboBox.getValue()!=null && tfWord.getText()!=null) {
//
//         combosel = comboBox.getValue().toString();
//         tfsel = tfWord.getText().toString();
//
//         try {
//            Stage newStage = new Stage();
//            Parent root = FXMLLoader
//                  .load(getClass().getClassLoader().getResource("application/kh/bms/view/MainSearch.fxml"));
//            Scene scene = new Scene(root);
//            newStage.setScene(scene);
//            newStage.setTitle("선택조회");
//            newStage.show();
//
//            Stage primaryStage = (Stage)btnSelect.getScene().getWindow();
//            primaryStage.close();
//
//         } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//         }
//      }
//
//   }
//  
//    public void selectSearch() {
//
//      int j = 0;
//      switch(combosel) {
//      case "도서명" : 
//         for(j = 0; j < books.size(); j++) {
//            if (books.get(j).getBookName().contains(tfsel)) {
//               selectBookList.add(books.get(j));
//            }
//         }
//         break;
//      case "저자" :
//         for(j = 0; j < books.size(); j++) {
//            if (books.get(j).getAuthor().contains(tfsel)) {;
//            selectBookList.add(books.get(j));
//            }
//         }
//         break;
//      case "출판사" : 
//         for(j = 0; j < books.size(); j++) {
//            if (books.get(j).getPublishingHouse().contains(tfsel)) {
//               selectBookList.add(books.get(j));
//            }
//
//         }
//         break;
//      case "장르" : 
//         for(j = 0; j < books.size(); j++) {
//            if (books.get(j).getCategory().contains(tfsel)) {
//               selectBookList.add(books.get(j));
//            }
//         }
//         break;
//      }
//
//   }
//   
//   
//
//
//   public TextField getTfWord() {
//      return tfWord;
//   }
//
//   public void setTfWord(TextField tfWord) {
//      this.tfWord = tfWord;
//   }
//   
//}
//
//
//
//
//

package application.kh.bms.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.kh.bms.controller.BookController;
import application.kh.bms.controller.BookSearchController;
import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.BookModel;
import application.kh.bms.model.vo.BookTable;
import application.kh.bms.model.vo.SelectedBook;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminSearchView implements Initializable {
   ////

   // 어드민 서치 관리자 책 추가 수정 삭제 할 수 있는 화면
   //

   // 어드민 서치 관리자 책 추가 수정 삭제 할 수 있는 화면
   //

   @FXML
   private Button btnEdit, loodDetailBtn;
   @FXML
   private Button btnAll; // 전체보기
   @FXML
   private Button btnRemove;
   @FXML
   private Button btnbtnEdit;
   @FXML
   private Button btnAddBook;
   @FXML
   private Button btnSelect; // 조회
   @FXML
   private Button viewAll; // 전체보기
   @FXML
   private Button btnBack;

   @FXML
   private TextField tfWord;

   @FXML
   private ComboBox<String> comboBox = new ComboBox<String>(); // 구분선택

   @FXML
   private TableView<BookTable> tableView;
   @FXML
   private TableColumn<BookTable, String> codeCol; // 번호
   @FXML
   private TableColumn<BookTable, String> nameCol; // 도서명
   @FXML
   private TableColumn<BookTable, String> authorCol; // 저자
   @FXML
   private TableColumn<BookTable, String> pubCol; // 출판사
   @FXML
   private TableColumn<BookTable, String> cateCol; // 장르
   @FXML
   private TableColumn<BookTable, Boolean> rentalCol;// 대여여부

   private LoadSave dao = LoadSave.getDao();
   private ArrayList<BookModel> temp = dao.loadBook();
   static BookTable model = new BookTable();

   BookModel book = new BookModel();
   BookSearchController bookSearchController = new BookSearchController();
   BookController bookController = new BookController();
   ArrayList<BookTable> books = new ArrayList<BookTable>();
   ArrayList<BookModel> realBooks = null;

   public static BookTable selBook = new BookTable();

   public static BookTable getSelBook() {
      return selBook;
   }

   public static void setSelectUser(BookModel selectBook) {
      AdminSearchView.selBook = selBook;
   }

   // 테이블열선택
   public int row = -1;

   // 콤보박스 list
   private ObservableList<String> comboList = FXCollections.observableArrayList("도서명", "저자", "출판사", "장르");

   private static String tfsel; // 텍스트필드 저장용
   private static String combosel; // 콤보박스 저장용
   static {
      tfsel = "";
      combosel = "";
   }

   // 전체조회 테이블용
   public ObservableList<BookTable> bookList = FXCollections.observableArrayList();

   // 선택조회 테이블용
   public ObservableList<BookTable> selectBookList = FXCollections.observableArrayList();

   @Override
   public void initialize(URL location, ResourceBundle resources) {

      // bookSearchController.bookTableSave(); //파일 저장 test용

      codeCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("code"));
      nameCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("bookName"));
      authorCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("author"));
      pubCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("publishingHouse"));
      cateCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("category"));
      rentalCol.setCellValueFactory(new PropertyValueFactory<BookTable, Boolean>("rental"));
      books = bookSearchController.bookTableLoad();
//
//      if (combosel == "" && tfsel == "") {
      for (int i = 0; i < books.size(); i++) {
         bookList.add(books.get(i));
      }
      tableView.setItems(bookList);
//
//      }

//      else {
//         tableView.setItems(selectBookList);
//      }

      comboBox.setItems(comboList);

      // -----------별리수정시작

      tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookTable>() {
         public void changed(ObservableValue<? extends BookTable> observable, BookTable oldValue,
               BookTable newValue) {
            model = tableView.getSelectionModel().getSelectedItem();

            if (model.getRental().getValue() == true) {
               btnEdit.setDisable(true);
            } else {
               btnEdit.setDisable(false);
            }
         }
      });
   }

   // 전체조회 하고, 조회된 테이블의 현재 책의 렌탈 값을 true인지, false인지 확인
   // 만약 true면 삭제 불가
   // false 면 어레이 리스트에서 제거
   // cf) nowBook은 코드값만 넘겨줌
   // 렌탈을 했으면 true

   @FXML
   public void runRemove() {

      bookController.remove(tableView.getSelectionModel().getSelectedItem().getCode());

      bookList.clear();
      books = bookSearchController.bookTableLoad();

      for (int i = 0; i < books.size(); i++) {
         bookList.add(books.get(i));
      }
      tableView.setItems(bookList);

   }

   @FXML
   public void moveBack() {
      try {
         Stage newStage = new Stage();
         Parent root = FXMLLoader
               .load(getClass().getClassLoader().getResource("application/kh/bms/view/MainSearch.fxml"));
         Scene scene = new Scene(root);
         newStage.setScene(scene);
         newStage.setTitle("도서 조회");
         newStage.show();

         Stage primaryStage = (Stage) btnBack.getScene().getWindow();
         primaryStage.close();

      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @FXML
   public void addBook() {
      try {
         Stage newStage = new Stage();
         Parent root = FXMLLoader
               .load(getClass().getClassLoader().getResource("application/kh/bms/view/AddBook.fxml"));
         Scene scene = new Scene(root);
         newStage.setScene(scene);
         newStage.setTitle("Book Management System");
         newStage.show();

         Stage primaryStage = (Stage) btnSelect.getScene().getWindow();
         primaryStage.close();

      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @FXML
   public void editBook() {

      try {

         System.out.println("받아왔니? : " + model.getCode());
         SelectedBook.selBook.setBookName(model.getBookName());
         SelectedBook.selBook.setAuthor(model.getAuthor());
         SelectedBook.selBook.setPublishingHouse(model.getPublishingHouse());
         SelectedBook.selBook.setCategory(model.getCategory());
         SelectedBook.selBook.setRental(model.getRental().get());
         SelectedBook.selBook.setCode(model.getCode());
         System.out.println("들어갔니? : " + SelectedBook.selBook.getCode());

         Stage newStage = new Stage();
         Parent root = FXMLLoader
               .load(getClass().getClassLoader().getResource("application/kh/bms/view/BookUpdateView2.fxml"));
         Scene scene = new Scene(root);
         newStage.setScene(scene);
         newStage.setTitle("Book Management System");
         newStage.show();

         Stage primaryStage = (Stage) btnEdit.getScene().getWindow();

         primaryStage.close();

      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

   // 전체보기 버튼 클릭 액션 메소드
   @FXML
   public void viewAll() {
   
      comboBox.setValue("선택");
      bookList.clear();
      books = bookSearchController.bookTableLoad();

      tfWord.setText("");
      
      for (int i = 0; i < books.size(); i++) {
         bookList.add(books.get(i));
      }
      tableView.setItems(bookList);

      
   }

   // 조회버튼 클릭 매소드
   @FXML
   public void selectSearch() {

      tfsel = tfWord.getText();
      System.out.println(tfsel);
      combosel = comboBox.getValue();
      System.out.println(combosel);

      if (tfsel.isEmpty() || (combosel == null||combosel.equals("선택"))) {
         System.out.println("검색조건을 입력해주세요");
      } else {    
         bookList.clear();
         int j = 0;
         switch (combosel) {
         case "도서명":
            for (j = 0; j < books.size(); j++) {
               if (books.get(j).getBookName().contains(tfsel)) {
                  bookList.add(books.get(j));
               }
            }
            break;
         case "저자":
            for (j = 0; j < books.size(); j++) {
               if (books.get(j).getAuthor().contains(tfsel)) {
                  ;
                  bookList.add(books.get(j));
               }
            }
            break;
         case "출판사":
            for (j = 0; j < books.size(); j++) {
               if (books.get(j).getPublishingHouse().contains(tfsel)) {
                  bookList.add(books.get(j));
               }

            }
            break;
         case "장르":
            for (j = 0; j < books.size(); j++) {
               if (books.get(j).getCategory().contains(tfsel)) {
                  bookList.add(books.get(j));
               }
            }
            break;
         }

         tableView.setItems(bookList);
      }

   }

   public TextField getTfWord() {
      return tfWord;
   }

   public void setTfWord(TextField tfWord) {
      this.tfWord = tfWord;
   }

}