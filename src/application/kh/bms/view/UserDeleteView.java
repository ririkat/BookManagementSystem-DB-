package application.kh.bms.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.kh.bms.controller.UserDeleteController;
import application.kh.bms.model.dao.LoadSave;
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

	private LoadSave dao = LoadSave.getDao();
	private ArrayList<BookModel> books = dao.loadBook();
	private User user = UserSearchView.getSelectUser();
	private ArrayList<User> users = dao.loadUser();
	private UserDeleteController dc = new UserDeleteController();
	private Popup popup;

	@FXML
	public void deleteUser() {
		// 라벨... 수정하기
		checkIdLabel.setText("");
		checkPwLabel.setText("");

		String inputId = checkId.getText();// 탈퇴유저확인 시 입력한 텍스트필드에서 받아온 아이디 값
		String identifyPw = checkPw.getText();
		ArrayList<User> users = dao.loadUser();

		int check = dc.checkUser(inputId, identifyPw);
		if (check == -1) {
			//-----------------------------------------------
			fail();
			//-----------------------------------------------
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 뒤로가기
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	// -----------------------------------------------
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
	// -----------------------------------------------
}