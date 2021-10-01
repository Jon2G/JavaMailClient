package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;  
import javax.mail.Folder;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.NoSuchProviderException;  
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.pop3.POP3Store;

import javafx.scene.layout.BorderPane; 

public class MailServer {
	
 public final String pop3Host;
 public final String storeType;  
 public final String user;
 public final String password;
 public final Session emailSession;
 
 public MailServer(String pop3Host,String storeType,String user,String password) {
	 this.pop3Host=pop3Host;
	 this.storeType=storeType;
	 this.user=user;
	 this.password=password;
	 
	 Properties properties = new Properties();
	 properties.put("mail.pop3.host", pop3Host); 
	    properties.setProperty("mail.smtp.host", pop3Host);
	    properties.setProperty("mail.smtp.port", "25");
	    
	 this.emailSession=Session.getDefaultInstance(properties);  
	 
 }
	
public ArrayList<BorderPane> receive() {
	ArrayList<BorderPane> list=new ArrayList<BorderPane>();
	try {  
		   //1) get the session object  
		    
		   //2) create the POP3 store object and connect with the pop server  
		   POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);  
		   emailStore.connect(user, password);  
		  
		   //3) create the folder object and open it  
		   Folder emailFolder = emailStore.getFolder("INBOX");  
		   emailFolder.open(Folder.READ_ONLY);  
		  
		   //4) retrieve the messages from the folder in an array and print it  
		   Message[] messages = emailFolder.getMessages();  
		   for (int i = 0; i < messages.length; i++) {  
		    Message message = messages[i];  
		    list.add(MessageView.Render(message));
		   
		   }  
		  
		   //5) close the store and folder objects  
		   emailFolder.close(false);  
		   emailStore.close();  
		  
		  } catch (NoSuchProviderException e) {e.printStackTrace();}   
		  catch (MessagingException e) {e.printStackTrace();}  
		  catch (IOException e) {e.printStackTrace();}
	return list;
}

public void send(String to,String subject,String content) {


    try {
       // Create a default MimeMessage object.
       MimeMessage message = new MimeMessage(emailSession);

       // Set From: header field of the header.
       message.setFrom(new InternetAddress(user));

       // Set To: header field of the header.
       message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

       // Set Subject: header field
       message.setSubject(subject);

       // Now set the actual message
       message.setText(content);

       // Send message
       Transport.send(message);
       System.out.println("Sent message successfully....");
    } catch (MessagingException mex) {
       mex.printStackTrace();
    }
}
 
}
