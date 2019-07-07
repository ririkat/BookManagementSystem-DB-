package application.kh.bms.view;

import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.BookModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
//	private LoadSave dao = LoadSave.getDao();
//	ArrayList<BookModel> books = dao.loadBook();
	@Override
	public void start(Stage primaryStage) {
//		books.add(new BookModel("B909","잠이", "너무", "온다", "스릴러"));
//		books.add(new BookModel("B809","살려줘", "제발", "죽겠어", "로맨스"));
//		books.add(new BookModel("P001","준영아", "어디갔니", "도와준다매", "범죄수사물"));
//		books.add(new BookModel("P002","현희언니", "환영해", "일찍왔구나", "로맨스소설"));
//		books.add(new BookModel("P003","현빈아", "저녁", "먹을거니", "판타지소설"));
//		books.add(new BookModel("P004","제발좀", "돼라", "집가고싶어", "추리소설"));
//		books.add(new BookModel("P005","해쉬맵", "추가했는데", "왜안돼", "스릴러"));
//		books.add(new BookModel("P006","배고파", "죽겠네", "김치찌개", "레시피"));
//		books.add(new BookModel("P007","현희언니의", "초콜릿", "존맛", "레시피"));
//		books.add(new BookModel("P008","코코넛", "초콜릿", "존맛", "매거진"));
//		books.add(new BookModel("P009","과제는", "언제하지", "힘들다", "연구서적"));
//		books.add(new BookModel("P010","철자를", "빠트리지", "맙시다", "사전"));
//		books.add(new BookModel("P011","양치질", "하고싶다", "쉬시야와라", "판타지소설"));
//		books.add(new BookModel("P012","제발", "살려줘", "과제좀하자", "연구서적"));
//		books.add(new BookModel("P013","잠도", "자고싶고", "밀린공부도좀", "연구서적"));
//		books.add(new BookModel("P014","sql언제공부해", "자바도해야돼", "끝내줘", "판타지소설"));
//		books.add(new BookModel("P015","sql과제", "하고", "언제해", "판타지소설"));
//		books.add(new BookModel("P016","자바과제", "싶다", "언제해", "판타지소설"));
//		books.add(new BookModel("P017","공부도", "제발", "언제해", "판타지소설"));
//		dao.saveBook(books);
		try {
			Parent root = FXMLLoader
					.load(getClass().getClassLoader().getResource("application/kh/bms/view/Login.fxml"));
			Scene scene = new Scene(root, 600, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("라바도서관");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
