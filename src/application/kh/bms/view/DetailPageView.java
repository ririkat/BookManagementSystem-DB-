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
	private Label bookCodeLab; // �����ڵ� ��¶�
	@FXML
	private Label bookNameLab; // ������ ��¶�
	@FXML
	private Label authorLab; // ���� ��¶�
	@FXML
	private Label pubLab; // ���ǻ� ��¶�
	@FXML
	private Label categoryLab; // �帣 ��¶�
	@FXML
	private Label rentalLab; // �뿩���� ��¶�

	@FXML
	private Button backBtn; // �ڷΰ��� ��ư
	@FXML
	private Button rentalBtn; // �뿩�ϱ� ��ư

	@FXML
	private TextArea taCon;

	private RentalController rentalController = new RentalController();
	private InformationManager im = InformationManager.getInformationManager();

	private ArrayList<BookModel> books = new ArrayList<BookModel>();
//	private LoadSave dao = LoadSave.getDao();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		bookCodeLab.setText(im.getNowBook().getCode());
		bookNameLab.setText(im.getNowBook().getBookName());
		authorLab.setText(im.getNowBook().getAuthor());
		pubLab.setText(im.getNowBook().getPublishingHouse());
		categoryLab.setText(im.getNowBook().getCategory());
		taCon.setText(im.getNowBook().getContent());
//		System.out.println("�Ѱܹ��� �����ڵ� : " + im.getNowBook().getCode());

		if (im.getNowBook().isRental() == false) {
			rentalLab.setText("�뿩 ����");
			rentalBtn.setDisable(false);
		} else {
			rentalLab.setText("�뿩��");
			rentalBtn.setDisable(true);
		}

	}

	// �ڷΰ��� ��ư �޼ҵ�
	@FXML
	public void backToSearchView() {
		try {
			Stage newStage = new Stage();
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/MainSearch.fxml"));
			Scene scene = new Scene(root);
			newStage.setScene(scene);
			newStage.setTitle("�����˻�");
			newStage.show();

			Stage primaryStage = (Stage) backBtn.getScene().getWindow();
			primaryStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// �뿩�ϱ� ��ư �޼ҵ�
	@FXML
	public void decideRental() {
		rentalController.addRetalBook(im.getNowBook().getCode());

		rentalLab.setText("�뿩��");
		rentalBtn.setDisable(true);
		
		rentalController.bookRentalUpdate("1",im.getNowBook().getCode());

//		books = dao.loadBook();
//		for (int i = 0; i < books.size(); i++) {
//			if (books.get(i).getCode().equals(SelectedBook.selBook.getCode())) {
//				books.get(i).setRental(true);
//			}
//		}
//		dao.saveBook(books);
		// ---------------------------------------------
		backToSearchView();
		// ---------------------------------------------
	}

}
