package application.kh.bms.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.kh.bms.controller.UserUpdateController;
import application.kh.bms.model.dao.LoadSave;
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

	private LoadSave dao = LoadSave.getDao();

	private User user = UserSearchView.getSelectUser();

	private void resetTextfield() {
		tfID.setText("");
		tfPw.setText("");
		tfName.setText("");
		tfAddr.setText("");
		tfPhone.setText("");
		comGender.setPromptText("Gender");
	}

	// Combox의 초기값을 list로 설정, 회원 정보 수정창 로드시에 Data를 불러들임
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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

	// 뒤로가기 버튼 누를시 해당 플랫폼 종료
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
         }
         catch (IOException e) {
            e.printStackTrace();
         }
	}

	// 회원 정보 수정할 User의 정보를 Controller로부터 Load함
//	@FXML
//	public void userInformationLoad(User selUser) {
////		user = userUpdateController.loadData(id);
//		this.user=selUser;
//		System.out.println(user.getId());
//		System.out.println(user.getPw());
//		System.out.println(user.toString());
////		tfPw.setText(user.getPw());
//		tfName.setText(user.getName());
////		tfAddr.setText(user.getAddr());
////		comGender.setPromptText(user.getGender());0
////		tfPhone.setText(user.getPhone());
//	}

	// 회원 정보 수정시 설정된 PW길이 8~12자 체크 로직 수행후 True 면 유저 정보 업데이트
	// False 일시 패스워드 길이 에러 출력
	@FXML
	private void updateUser() {
		System.out.println(comGender.getValue().toString());
		userUpdateController.updateUser(tID.getText(), tfName.getText(), tfAddr.getText(),
				comGender.getValue().toString(), tfPhone.getText());

//		new UpdateSuccessView().showUpdateSuccess();

	}

	@FXML
	private void comboChanged(ActionEvent e) {
	}

}
