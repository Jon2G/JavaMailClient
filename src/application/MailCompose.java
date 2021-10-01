package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MailCompose {
	
	public final Stage primaryStage;
	
	public static void show() throws IOException {
		Stage primaryStage=new Stage();
		FXMLLoader loader=new FXMLLoader(MailView.class.getResource("MailCompose.fxml"));
		loader.setController(new MailCompose(primaryStage));
		BorderPane root =(BorderPane)loader.load();
		Scene scene = new Scene(root,250,400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	 @FXML
	 public TextField txtSubject;
	 @FXML
	 public TextField txtTo;  
	 @FXML
	 public TextArea txtContent;
	 
	public MailCompose(Stage primaryStage) {
		this.primaryStage=primaryStage;
	}
	 
	 @FXML
	 public void send() {
		 AppData.mailServer.send(txtTo.getText(),txtSubject.getText(),txtContent.getText());
		 primaryStage.close();
	 }
	
}
