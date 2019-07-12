package application.kh.bms.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.kh.bms.controller.BookUpdateController;
import application.kh.bms.model.dao.InformationManager;
//import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.BookModel;
import application.kh.bms.model.vo.BookTable;
import application.kh.bms.model.vo.SelectedBook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookUpdateView implements Initializable {
	private BookUpdateController bookUpdateController = new BookUpdateController();
	private ArrayList<BookModel> books = new ArrayList<BookModel>();
	private InformationManager im = InformationManager.getInformationManager();

	@FXML
	private Button btnUpdate, btnBack;

	@FXML
	private TextField tfName, tfCategory, tfAuthor, tfCode, tfPublisher;

	@FXML
	private TextArea taCon = new TextArea();

	@FXML
	private Label lID, lblCode;

	private BookTable book = AdminSearchView.getSelBook();
	public static BookTable selBook = new BookTable();

	@FXML
	public void resetTextfield() {
		tfName.setText("");
		tfCategory.setText("");
		tfAuthor.setText("");
		tfPublisher.setText("");
		taCon.setText("");

	}

	public void moveBack() {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/AdminSearch.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("档辑包府");
			newStage.show();

			Stage primaryStage = (Stage) btnBack.getScene().getWindow();
			primaryStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void updateBook() {

		bookUpdateController.updateBook(lblCode.getText(), tfName.getText(), tfCategory.getText(), tfAuthor.getText(),
				tfPublisher.getText(), taCon.getText());

		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/AdminSearch.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("档辑包府");
			newStage.show();

			Stage primaryStage = (Stage) btnBack.getScene().getWindow();
			primaryStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		lblCode.setText(im.getNowBook().getCode());
		tfName.setText(im.getNowBook().getBookName());
		tfCategory.setText(im.getNowBook().getCategory());
		tfAuthor.setText(im.getNowBook().getAuthor());
		tfPublisher.setText(im.getNowBook().getPublishingHouse());
		taCon.setText(im.getNowBook().getContent());

	}

}
