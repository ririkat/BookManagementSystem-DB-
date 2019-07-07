//package application.kh.bms.view;
//
//import java.io.IOException;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.stage.Stage;
//
//public class AdminView {
//	
//	
//	@FXML
//	private Button btnEnd, btnToBook, btnToUser;
//	
//	@FXML
//	public void moveEnd() {
//		System.exit(0);
//	}
//	
//	@FXML
//	public void moveToBook() throws Exception{
//		//FXMLLoader loader=new FXMLLoader();
//		try {
//			Stage adminStage = new Stage();
//			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("application/kh/bms/view/MainSearch.fxml"));
//			Scene scene = new Scene(root);
//			adminStage.setScene(scene);
//			adminStage.setTitle("Book Mgm");
//			adminStage.show();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@FXML
//	public void moveToUser() {
//		
//	}
//	
////별리s 화면전환 로직
////		try {
////			Stage adminStage = new Stage();
////			Parent root = FXMLLoader
////					.load(getClass().getClassLoader().getResource("application/view/Register.fxml"));
////			Scene scene = new Scene(root);
////			adminStage.setScene(scene);
////			adminStage.setTitle("회원가입");
////			adminStage.show();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
//	
//
//}
