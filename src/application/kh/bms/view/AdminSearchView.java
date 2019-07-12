
package application.kh.bms.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.kh.bms.controller.BookController;
import application.kh.bms.controller.BookSearchController;
import application.kh.bms.model.dao.InformationManager;
//import application.kh.bms.model.dao.LoadSave;
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

	static BookTable model = new BookTable();

	BookModel book = new BookModel();
	BookSearchController bookSearchController = new BookSearchController();
	BookController bookController = new BookController();
	List<BookTable> books = new ArrayList<BookTable>();
	List<BookModel> realBooks = null;
	private List<BookModel> temp = bookController.getBooks();
	private InformationManager im = InformationManager.getInformationManager();
	public static BookTable selBook = new BookTable();

	public static BookTable getSelBook() {
		return selBook;
	}

	public static void setSelectUser(BookModel selectBook) {
		AdminSearchView.selBook = selBook;
	}

	public int row = -1;

	private ObservableList<String> comboList = FXCollections.observableArrayList("도서명", "저자", "출판사", "장르");

	private static String tfsel; // 텍스트필드 저장용
	private static String combosel; // 콤보박스 저장용
	static {
		tfsel = "";
		combosel = "";
	}

	public ObservableList<BookTable> bookList = FXCollections.observableArrayList();

	public ObservableList<BookTable> selectBookList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		codeCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("code"));
		nameCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("bookName"));
		authorCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("author"));
		pubCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("publishingHouse"));
		cateCol.setCellValueFactory(new PropertyValueFactory<BookTable, String>("category"));
		rentalCol.setCellValueFactory(new PropertyValueFactory<BookTable, Boolean>("rental"));
		books = bookSearchController.bookTableLoad();
		for (int i = 0; i < books.size(); i++) {
			bookList.add(books.get(i));
		}
		tableView.setItems(bookList);

		comboBox.setItems(comboList);

		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookTable>() {
			public void changed(ObservableValue<? extends BookTable> observable, BookTable oldValue,
					BookTable newValue) {
				model = tableView.getSelectionModel().getSelectedItem();

				if (model.getRental().getValue()) {
					btnEdit.setDisable(true);
				} else {
					btnEdit.setDisable(false);
				}
			}
		});
	}

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
			e.printStackTrace();
		}
	}

	@FXML
	public void editBook() {

		try {

			im.setNowBook(bookController.loadBook(model.getCode()));

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
			e.printStackTrace();
		}

	}

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

	@FXML
	public void selectSearch() {

		tfsel = tfWord.getText();
		System.out.println(tfsel);
		combosel = comboBox.getValue();
		System.out.println(combosel);

		if (tfsel.isEmpty() || (combosel == null || combosel.equals("선택"))) {
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