
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
	private Button btnAll; // ��ü����
	@FXML
	private Button btnRemove;
	@FXML
	private Button btnbtnEdit;
	@FXML
	private Button btnAddBook;
	@FXML
	private Button btnSelect; // ��ȸ
	@FXML
	private Button viewAll; // ��ü����
	@FXML
	private Button btnBack;

	@FXML
	private TextField tfWord;

	@FXML
	private ComboBox<String> comboBox = new ComboBox<String>(); // ���м���

	@FXML
	private TableView<BookTable> tableView;
	@FXML
	private TableColumn<BookTable, String> codeCol; // ��ȣ
	@FXML
	private TableColumn<BookTable, String> nameCol; // ������
	@FXML
	private TableColumn<BookTable, String> authorCol; // ����
	@FXML
	private TableColumn<BookTable, String> pubCol; // ���ǻ�
	@FXML
	private TableColumn<BookTable, String> cateCol; // �帣
	@FXML
	private TableColumn<BookTable, Boolean> rentalCol;// �뿩����

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

	private ObservableList<String> comboList = FXCollections.observableArrayList("������", "����", "���ǻ�", "�帣");

	private static String tfsel; // �ؽ�Ʈ�ʵ� �����
	private static String combosel; // �޺��ڽ� �����
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
			newStage.setTitle("���� ��ȸ");
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

		comboBox.setValue("����");
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

		if (tfsel.isEmpty() || (combosel == null || combosel.equals("����"))) {
			System.out.println("�˻������� �Է����ּ���");
		} else {
			bookList.clear();
			int j = 0;
			switch (combosel) {
			case "������":
				for (j = 0; j < books.size(); j++) {
					if (books.get(j).getBookName().contains(tfsel)) {
						bookList.add(books.get(j));
					}
				}
				break;
			case "����":
				for (j = 0; j < books.size(); j++) {
					if (books.get(j).getAuthor().contains(tfsel)) {
						;
						bookList.add(books.get(j));
					}
				}
				break;
			case "���ǻ�":
				for (j = 0; j < books.size(); j++) {
					if (books.get(j).getPublishingHouse().contains(tfsel)) {
						bookList.add(books.get(j));
					}

				}
				break;
			case "�帣":
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