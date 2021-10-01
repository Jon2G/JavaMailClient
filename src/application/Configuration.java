package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Configuration {

	
	public static void show(Stage primaryStage) throws IOException {	
		FXMLLoader loader=new FXMLLoader(Configuration.class.getResource("Configuration.fxml"));
		loader.setController(new Configuration(primaryStage));
		BorderPane root =(BorderPane)loader.load();
		Scene scene = new Scene(root,250,320);
		scene.getStylesheets().add(Configuration.class.getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	 private final Stage primaryStage;
	 

	 
	 @FXML
	 public TextField txtHost;
	 @FXML
	 public TextField txtType;  
	 @FXML
	 public TextField txtUser;
	 @FXML
	 public TextField txtPassword;
	 
	 public Configuration(Stage primaryStage) {
		 this.primaryStage=primaryStage;
	 }
	
	@FXML
	public void login() throws IOException {		
		AppData.Init(new MailServer(txtHost.getText(),txtType.getText(),txtUser.getText(),txtPassword.getText()));
		primaryStage.close();
		MailView.show(primaryStage);
	}
	
}
