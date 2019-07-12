package application.kh.bms.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.kh.bms.controller.BookSearchController;
import application.kh.bms.controller.RentalController;
//import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.BookModel;
import application.kh.bms.model.vo.RentalTable;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RentalListView implements Initializable {

	@FXML
	private TableView<RentalTable> tableView = new TableView<RentalTable>();
	@FXML
	private TableColumn<RentalTable, String> codeCol = new TableColumn<RentalTable, String>(); // 번호
	@FXML
	private TableColumn<RentalTable, String> nameCol = new TableColumn<RentalTable, String>(); // 도서명
	@FXML
	private TableColumn<RentalTable, String> authorCol = new TableColumn<RentalTable, String>(); // 저자
	@FXML
	private TableColumn<RentalTable, String> pubCol = new TableColumn<RentalTable, String>(); // 출판사
	@FXML
	private TableColumn<RentalTable, String> cateCol = new TableColumn<RentalTable, String>(); // 장르

	private BookSearchController bookSearchController = new BookSearchController();
	private List<RentalTable> rentalBooks = new ArrayList<RentalTable>();
	private RentalController rentalController = new RentalController();
	private List<BookModel> books = new ArrayList<BookModel>();

	@FXML
	private Button backBtn;
	@FXML
	private Button returnBookBtn = new Button();
	@FXML
	private Button okayBtn;

	static RentalTable model = new RentalTable();

	public ObservableList<RentalTable> bookList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		codeCol.setCellValueFactory(new PropertyValueFactory<RentalTable, String>("code"));
		nameCol.setCellValueFactory(new PropertyValueFactory<RentalTable, String>("bookName"));
		authorCol.setCellValueFactory(new PropertyValueFactory<RentalTable, String>("author"));
		pubCol.setCellValueFactory(new PropertyValueFactory<RentalTable, String>("publishingHouse"));
		cateCol.setCellValueFactory(new PropertyValueFactory<RentalTable, String>("category"));

		rentalBooks = bookSearchController.rentalTableLoad();

		for (int i = 0; i < rentalBooks.size(); i++) {
			bookList.add(rentalBooks.get(i));
		}
		tableView.setItems(bookList);

		returnBookBtn.setDisable(true);

		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RentalTable>() {
			public void changed(ObservableValue<? extends RentalTable> observable, RentalTable oldValue,
					RentalTable newValue) {
				returnBookBtn.setDisable(false);
				model = tableView.getSelectionModel().getSelectedItem();

				System.out.println("선택된 Item의 Index" + tableView.getSelectionModel().getSelectedIndex());
			}
		});

	}

	@FXML
	public void backToSearchView() {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/MainSearch.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("도서검색");
			newStage.show();

			Stage primaryStage = (Stage) backBtn.getScene().getWindow();
			primaryStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void returnBook() {
		int result = rentalController.delRentalBook(model.getCode());
		int result2 = rentalController.bookRentalUpdate("0", model.getCode());
		if (result == 1) {
			System.out.println("연체됐음");
			fail();
		}

		else if (result == 0) {
			if (result2 == 1) {
				succ();
			} else {
				fail();
			}
		}

		else {
			fail();

		}

		returnBookBtn.setDisable(true);

		bookList.clear();
		rentalBooks = bookSearchController.rentalTableLoad();
		for (int i = 0; i < rentalBooks.size(); i++) {
			bookList.add(rentalBooks.get(i));
		}
		tableView.setItems(bookList);

	}

	@FXML
	public void succConfirm() {
		Stage primaryStage = (Stage) okayBtn.getScene().getWindow();
		primaryStage.close();
	}

	@FXML
	public void failConfirm() {
		Stage primaryStage = (Stage) okayBtn.getScene().getWindow();
		primaryStage.close();
	}

	@FXML
	public void succ() {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/SuccPopup.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("완료");
			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void fail() {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/OverdueReturnPopup.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("반납 팝업");
			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
