package application.kh.bms.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.kh.bms.controller.RentalController;
import application.kh.bms.model.dao.InformationManager;
//import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.BookModel;
import application.kh.bms.model.vo.SelectedBook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class DetailPageView implements Initializable {

	@FXML
	private Label bookCodeLab; // 도서코드 출력라벨
	@FXML
	private Label bookNameLab; // 도서명 출력라벨
	@FXML
	private Label authorLab; // 저자 출력라벨
	@FXML
	private Label pubLab; // 출판사 출력라벨
	@FXML
	private Label categoryLab; // 장르 출력라벨
	@FXML
	private Label rentalLab; // 대여여부 출력라벨

	@FXML
	private Button backBtn; // 뒤로가기 버튼
	@FXML
	private Button rentalBtn; // 대여하기 버튼

	@FXML
	private TextArea taCon;

	private RentalController rentalController = new RentalController();
	private InformationManager im = InformationManager.getInformationManager();

	private ArrayList<BookModel> books = new ArrayList<BookModel>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		bookCodeLab.setText(im.getNowBook().getCode());
		bookNameLab.setText(im.getNowBook().getBookName());
		authorLab.setText(im.getNowBook().getAuthor());
		pubLab.setText(im.getNowBook().getPublishingHouse());
		categoryLab.setText(im.getNowBook().getCategory());
		taCon.setText(im.getNowBook().getContent());

		if (im.getNowBook().isRental() == false) {
			rentalLab.setText("대여 가능");
			rentalBtn.setDisable(false);
		} else {
			rentalLab.setText("대여중");
			rentalBtn.setDisable(true);
		}

	}

	// 뒤로가기 버튼 메소드
	@FXML
	public void backToSearchView() {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/MainSearch.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("도서검색");
			newStage.show();

			Stage primaryStage = (Stage) backBtn.getScene().getWindow();
			primaryStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 대여하기 버튼 메소드
	@FXML
	public void decideRental() {
		rentalController.addRetalBook(im.getNowBook().getCode());

		rentalLab.setText("대여중");
		rentalBtn.setDisable(true);
		
		rentalController.bookRentalUpdate("1",im.getNowBook().getCode());

		// ---------------------------------------------
		backToSearchView();
		// ---------------------------------------------
	}

}
