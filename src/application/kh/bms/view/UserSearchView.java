package application.kh.bms.view;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.kh.bms.controller.UserDeleteController;
import application.kh.bms.controller.UserSearchController;
import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.User;
import application.kh.bms.model.vo.UserTable;
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

public class UserSearchView implements Initializable {
	@FXML
	private TableView<UserTable> table;

	@FXML
	private TableColumn<UserTable, String> id;
	@FXML
	private TableColumn<UserTable, String> name;
	@FXML
	private TableColumn<UserTable, String> addr;
	@FXML
	private TableColumn<UserTable, String> gender;
	@FXML
	private TableColumn<UserTable, String> phone;
	@FXML
	private TableColumn<UserTable, Boolean> adminCheck;
	@FXML
	private ComboBox searchType;
	@FXML
	private Button btnSearch, btnUpdate, btnEnroll, btnDelete, btnB, btnRefresh;
	@FXML
	private TextField tfKeyword;
	@FXML
	private Button btnBack;

	private UserSearchController userSearchController = new UserSearchController();
	private UserDeleteController dc = new UserDeleteController();
	private ArrayList<UserTable> users = new ArrayList<UserTable>();
	private UserUpdateView uuv = new UserUpdateView();

	private ObservableList<String> comboList = FXCollections.observableArrayList("ID", "name", "addr", "gender",
			"phone", "adminCheck");

	private ObservableList<UserTable> list = FXCollections.observableArrayList();
	private LoadSave dao = LoadSave.getDao();
	private ArrayList<User> temp = dao.loadUser();
	private static User selectUser;

	public static User getSelectUser() {
		return selectUser;
	}

	public static void setSelectUser(User selectUser) {
		UserSearchView.selectUser = selectUser;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id.setCellValueFactory(new PropertyValueFactory<UserTable, String>("id"));
		name.setCellValueFactory(new PropertyValueFactory<UserTable, String>("name"));
		addr.setCellValueFactory(new PropertyValueFactory<UserTable, String>("addr"));
		gender.setCellValueFactory(new PropertyValueFactory<UserTable, String>("gender"));
		phone.setCellValueFactory(new PropertyValueFactory<UserTable, String>("phone"));
		adminCheck.setCellValueFactory(new PropertyValueFactory<UserTable, Boolean>("adminCheck"));

		users = userSearchController.userTableLoad();
		for (int i = 0; i < users.size(); i++) {
			UserTable temp = users.get(i);
			System.out.println(temp.isaAminCheck());
			list.add(users.get(i));
		}

		table.setItems(list);
		searchType.setItems(comboList);

	}

	@FXML
	private void search() {
		// ArrayList<UserTable> tempUsers = userSearchController.userTableLoad();
		ArrayList<UserTable> tempUsers = new ArrayList<UserTable>();
		switch (searchType.getValue().toString()) {
		case "ID":
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getId().contains(tfKeyword.getText())) {
					tempUsers.add(users.get(i));
				}
			}
			break;
		case "name":
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getName().contains(tfKeyword.getText())) {
					tempUsers.add(users.get(i));

				}
			}
			break;
		case "addr":
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getAddr().contains(tfKeyword.getText())) {
					tempUsers.add(users.get(i));

				}
			}
			break;
		case "gender":
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getGender().equals(tfKeyword.getText())) {
					tempUsers.add(users.get(i));

				}
			}
			break;
		case "phone":
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getPhone().contains(tfKeyword.getText())) {
					tempUsers.add(users.get(i));

				}
			}
			break;

		}
		list.clear();
		for (int i = 0; i < tempUsers.size(); i++) {
			list.add(tempUsers.get(i));
		}
		table.setItems(list);
	}

	@FXML
	private void refresh() {
		tfKeyword.setText("");
		searchType.setValue("ID");
		list.clear();
		for (int i = 0; i < users.size(); i++) {
			list.add(users.get(i));
		}
		table.setItems(list);
	}

	@FXML
	public void update() {
		try {
			String id = String.valueOf(table.getSelectionModel().getSelectedItem().getId());
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).getId().equals(id)) {
					selectUser = temp.get(i);
				}
			}

			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/userUpdate.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("유저정보수정");
			newStage.show();

			Stage primaryStage = (Stage) btnUpdate.getScene().getWindow();
			primaryStage.close();

			// uuv.userInformationLoad(selectUser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void enroll() throws InvocationTargetException {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/UserEnroll.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("회원등록");
			newStage.show();

			Stage primaryStage = (Stage) btnEnroll.getScene().getWindow();
			primaryStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void backMove() {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/MainSearch.fxml"));
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
	public void delete() throws InvocationTargetException {
		String delId = String.valueOf(table.getSelectionModel().getSelectedItem().getId());

		dc.deleteUser(delId);

		list.clear();
		users = userSearchController.userTableLoad();
		for (int i = 0; i < users.size(); i++) {
			list.add(users.get(i));
		}
		table.setItems(list);

	}
}
