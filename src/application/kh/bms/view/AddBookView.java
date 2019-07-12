package application.kh.bms.view;

import java.io.IOException;

import application.kh.bms.controller.BookController;
//import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.BookModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookView {

	boolean result;

	BookController bookController = new BookController();

	@FXML
	private TextField tfBookName, tfAuthor, tfCode, tfPublishingHouse, tfCategory;

	@FXML
	private Button btnBack, btnAdd, btnFail, btnSucc;

	@FXML
	private TextArea taCon = new TextArea();

	@FXML
	private Button okayBtn; // 반납하기 팝업창에서 확인 버튼

	@FXML
	public void duplicationControll() {

		String code = tfCode.getText();
		result = bookController.checkCode(code);
	}

	@FXML
	public void failConfirm() {

		Stage primaryStage = (Stage) btnFail.getScene().getWindow();
		primaryStage.close();
	}

	@FXML
	public void succConfirm() {

		Stage primaryStage = (Stage) btnSucc.getScene().getWindow();
		primaryStage.close();
	}

	@FXML
	public void confirm() {
		Stage primaryStage = (Stage) okayBtn.getScene().getWindow();
		primaryStage.close();
	}

	@FXML
	public void fail() {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/AddBookFail.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("완료");
			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void succ() {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/AddBookSucc.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("완료");
			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void addTest() {

		if (tfCode.getText().isEmpty() || tfBookName.getText().isEmpty() || tfAuthor.getText().isEmpty()
				|| tfPublishingHouse.getText().isEmpty() || tfCategory.getText().isEmpty()
				|| taCon.getText().isEmpty()) {
			fail();
		} else {
			if (bookController.addBook(new BookModel(tfCode.getText(), tfBookName.getText(), tfAuthor.getText(),
					tfPublishingHouse.getText(), tfCategory.getText(), taCon.getText()))) {
				succ();
				resetInput();
			} else {
				fail();
			}
		}

	}

	@FXML
	public void moveBack() {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/AdminSearch.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("도서관리");
			newStage.show();

			Stage primaryStage = (Stage) btnBack.getScene().getWindow();
			primaryStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void resetInput() {
		tfBookName.setText("");
		tfAuthor.setText("");
		tfCategory.setText("");
		tfCode.setText("");
		tfPublishingHouse.setText("");
		taCon.setText("");
	}

}
