package application.kh.bms.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.kh.bms.controller.UserUpdateController;
//import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserUpdateView implements Initializable {
	private UserUpdateController userUpdateController = new UserUpdateController();
	@FXML
	private Button btnUpdate = new Button(), btnBack, btnTest, btnAgree;
	@FXML
	private ComboBox comGender = new ComboBox<>();
	@FXML
	private CheckBox checkAgree;
	@FXML
	private TextField tfName = new TextField();
	@FXML
	private TextField tfAddr = new TextField();
	@FXML
	private TextField tfPhone = new TextField();
	@FXML
	private TextField tfID = new TextField();
	@FXML
	private PasswordField tfPw = new PasswordField();
	@FXML
	private Text tID = new Text(), tCheck;
	@FXML
	private Label lID;
	private ObservableList<String> list = FXCollections.observableArrayList("남자", "여자");

	private User user = UserSearchView.getSelectUser();

	private void resetTextfield() {
		tfID.setText("");
		tfPw.setText("");
		tfName.setText("");
		tfAddr.setText("");
		tfPhone.setText("");
		comGender.setPromptText("Gender");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user = UserSearchView.getSelectUser();
		lID.setText(user.getId());
		tfPw.setText(user.getPw());
		tfPw.setEditable(false);
		tfName.setText(user.getId());
		tfAddr.setText(user.getAddr());
		tfPhone.setText(user.getPhone());
		comGender.setItems(list);
		comGender.setValue(user.getGender());
		tCheck.setText("");
	}

	@FXML
	private void backMove() {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/userSearch.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("메인메뉴");
			newStage.show();

			Stage primaryStage = (Stage) btnBack.getScene().getWindow();
			primaryStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void updateUser() {
		System.out.println(comGender.getValue().toString());
		userUpdateController.updateUser(tID.getText(), tfName.getText(), tfAddr.getText(),
				comGender.getValue().toString(), tfPhone.getText());
		backMove();
	}

}
