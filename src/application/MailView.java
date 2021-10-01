package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MailView {

public static void show(Stage primaryStage) throws IOException {
	FXMLLoader loader=new FXMLLoader(MailView.class.getResource("MailView.fxml"));
	loader.setController(new MailView(primaryStage));
	BorderPane root =(BorderPane)loader.load();
	Scene scene = new Scene(root,700,500);
	primaryStage.setScene(scene);
	primaryStage.close();
	primaryStage.show();
}

@FXML
public ListView listView;
public final Stage stage;

public MailView(Stage stage) {
	
this.stage=stage;
}

@FXML
public void get() {
	 ObservableList oblist = FXCollections.observableList(AppData.mailServer.receive());
	listView.setItems(oblist);
	
}

@FXML 
public void send() {
	try {
		MailCompose.show();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
	
}
