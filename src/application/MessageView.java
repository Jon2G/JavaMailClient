package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;  

public class MessageView {

	public static BorderPane Render(Message message) throws IOException {	
		FXMLLoader loader=new FXMLLoader(MessageView.class.getResource("MessageView.fxml"));
		MessageView controller=new MessageView();
		loader.setController(controller);
		BorderPane view= (BorderPane)loader.load();	
		controller.init(message);
		return view;
	}
		 
	 @FXML
	 public Label txtSubject;
	 @FXML
	 public Label txtFrom;  
	 @FXML
	 public Label txtContent;

	 
	 public MessageView() 
	 {
	 }
	
	public MessageView init(Message message)  {				
		try {
			txtSubject.setText(message.getSubject());
			txtFrom.setText(message.getFrom()[0].toString());
			txtContent.setText(message.getContent().toString());
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	
}
