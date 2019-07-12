package application.kh.bms.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.kh.bms.controller.UserDeleteController;
//import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.BookModel;
import application.kh.bms.model.vo.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class UserDeleteView implements Initializable {

	@FXML
	private TextField checkId, checkPw;

	@FXML
	private Button dropBtn, btnBack, okayBtn;

	@FXML
	private Label checkIdLabel, checkPwLabel;

	private User user = UserSearchView.getSelectUser();
	private UserDeleteController dc = new UserDeleteController();
	private Popup popup;

	@FXML
	public void deleteUser() {
		checkIdLabel.setText("");
		checkPwLabel.setText("");

		String inputId = checkId.getText();
		String identifyPw = checkPw.getText();

		int check = dc.checkUser(inputId, identifyPw);
		if (check == -1) {
			fail();
		} else {
			dc.deleteUser(inputId);
			try {
				Stage newStage = new Stage();
				Parent root = FXMLLoader
						.load(getClass().getClassLoader().getResource("application/kh/bms/view/Login.fxml"));
				Scene scene = new Scene(root);
				newStage.setScene(scene);
				newStage.setTitle("로그인");
				newStage.show();

				Stage primaryStage = (Stage) dropBtn.getScene().getWindow();
				primaryStage.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void back() {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/MainSearch.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("로그인");
			newStage.show();

			Stage primaryStage = (Stage) btnBack.getScene().getWindow();
			primaryStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void fail() {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/UserDeleteFail.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("완료");
			newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void confirm() {
		Stage primaryStage = (Stage) okayBtn.getScene().getWindow();
		primaryStage.close();
	}
}