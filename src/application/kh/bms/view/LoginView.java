package application.kh.bms.view;

import java.io.IOException;

import application.kh.bms.controller.LoginController;
//import application.kh.bms.model.dao.LoadSave;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginView {

	@FXML
	private TextField id;
	@FXML
	private TextField password;
	@FXML
	private Button loginButton;
	@FXML
	private Button regiButton;
	@FXML
	private Label lMessage;

	@FXML
	public void login() {
		lMessage.setStyle("-fx-text-fill: red");
		String loginId = id.getText();
		String loginPw = password.getText();

		LoginController lc = new LoginController();
		int check = lc.checkLogin(loginId, loginPw);

		switch (check) {
		case 1:
			lMessage.setText("ID를 잘못입력하셨습니다.");
			break;
		case 2:
			lMessage.setText("PW를 잘못입력하셨습니다.");
			break;
		case 3:
			lMessage.setStyle("-fx-text-fill: green");
			lMessage.setText("로그인성공!");
			try {
				Stage newStage = new Stage();
				Parent root = FXMLLoader
						.load(getClass().getClassLoader().getResource("application/kh/bms/view/MainSearch.fxml"));
				Scene scene = new Scene(root);
				newStage.setScene(scene);
				newStage.setTitle("도서조회");
				newStage.show();

				Stage primaryStage = (Stage) loginButton.getScene().getWindow();
				primaryStage.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	@FXML
	public void register() {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/Register.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("회원가입");
			newStage.show();

			Stage primaryStage = (Stage) regiButton.getScene().getWindow();
			primaryStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}