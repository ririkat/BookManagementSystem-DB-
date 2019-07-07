package application.kh.bms.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.kh.bms.controller.BookUpdateController;
import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.BookModel;
import application.kh.bms.model.vo.BookTable;
import application.kh.bms.model.vo.SelectedBook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookUpdateView implements Initializable {
   private BookUpdateController bookUpdateController = new BookUpdateController();
   ArrayList<BookModel> books = new ArrayList<BookModel>();

   @FXML
   private Button btnUpdate, btnBack;

   @FXML
   private TextField tfName, tfCategory, tfAuthor, tfCode, tfPublisher;
   

   @FXML
   private Label lID,lblCode;

     
    private LoadSave dao = LoadSave.getDao();
   
   private BookTable book = AdminSearchView.getSelBook();
   public static BookTable selBook = new BookTable();
   

   @FXML
   public void resetTextfield() {
      tfName.setText("");
      tfCategory.setText("");
      tfAuthor.setText("");
      tfPublisher.setText("");
   

   }

    public void moveBack() {
         try {
            Stage newStage = new Stage();
            Parent root = FXMLLoader
                  .load(getClass().getClassLoader().getResource("application/kh/bms/view/AdminSearch.fxml"));
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.setTitle("档辑包府");
            newStage.show();

            Stage primaryStage = (Stage) btnBack.getScene().getWindow();
             primaryStage.close();
         }
         catch (IOException e) {
            e.printStackTrace();
         }
      }
   


   
   @FXML
   private void updateBook() {
      
      bookUpdateController.updateBook(lblCode.getText(), tfName.getText(),tfCategory.getText(), tfAuthor.getText(),tfPublisher.getText() );
      
      try {
          Stage newStage = new Stage();
          Parent root = FXMLLoader
                .load(getClass().getClassLoader().getResource("application/kh/bms/view/AdminSearch.fxml"));
          Scene scene = new Scene(root);
          newStage.setScene(scene);
          newStage.setTitle("档辑包府");
          newStage.show();

          Stage primaryStage = (Stage) btnBack.getScene().getWindow();
           primaryStage.close();
       }
       catch (IOException e) {
          e.printStackTrace();
       }

   }
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      
      lblCode.setText(SelectedBook.selBook.getCode());
      tfName.setText(SelectedBook.selBook.getBookName());
      tfCategory.setText(SelectedBook.selBook.getCategory());
      tfAuthor.setText(SelectedBook.selBook.getAuthor());
      tfPublisher.setText(SelectedBook.selBook.getPublishingHouse());
      
   
   }
   

}









