package application.kh.bms.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UpdateSuccessView {
	@FXML
	private Button btnAgree;

	private Parent root;
	private Stage subStage = new Stage();

	@FXML
	private void backMove() {
		Stage stage = (Stage) btnAgree.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void showUpdateSuccess() {
		try {
			root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/updateSuccess.fxml"));
			subStage.setScene(new Scene(root));
			subStage.setTitle("Update Success!");
			subStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
