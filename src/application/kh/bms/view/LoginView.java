package application.kh.bms.view;


import java.io.IOException;

import application.kh.bms.controller.LoginController;
import application.kh.bms.model.dao.LoadSave;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginView{
	private LoadSave dao = LoadSave.getDao();
	
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
		int check = lc.checkLogin(loginId,loginPw);
		// 1-ID , 2-PW 3-LoginSuccess
		
		switch(check) {
		case 1:
			lMessage.setText("ID�� �߸��Է��ϼ̽��ϴ�.");
			break;
		case 2:
			lMessage.setText("PW�� �߸��Է��ϼ̽��ϴ�.");
			break;
		case 3:
			lMessage.setStyle("-fx-text-fill: green");
			lMessage.setText("�α��μ���!");
			dao.setNowUser(loginId);
			try {
				Stage newStage = new Stage();
				Parent root = FXMLLoader
						.load(getClass().getClassLoader().getResource("application/kh/bms/view/MainSearch.fxml"));
				Scene scene = new Scene(root);
				newStage.setScene(scene);
				newStage.setTitle("������ȸ");
				newStage.show();
				
				Stage primaryStage = (Stage)loginButton.getScene().getWindow();
				primaryStage.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
			newStage.setTitle("ȸ������");
			newStage.show();
			
			Stage primaryStage = (Stage) regiButton.getScene().getWindow();
			primaryStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}