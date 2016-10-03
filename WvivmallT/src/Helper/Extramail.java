package Helper;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Extramail {
	public static int SendingEmail(String user,String passw,String Email,String title,String Body) throws AddressException, MessagingException
	{
		try{
			 String host ="smtp.gmail.com";
	        String from =user;
	        String pass =passw; 
	        Properties props = System.getProperties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.user", from);
	        props.put("mail.smtp.password", pass);
	        props.put("mail.smtp.port", "25");
	        props.put("mail.smtp.auth", "true");
	        String[] to = {Email}; // To Email address
	        Session session = Session.getDefaultInstance(props, null);
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        InternetAddress[] toAddress = new InternetAddress[to.length];        
	         for( int i=0; i < to.length; i++ )
	         { 
	             toAddress[i] = new InternetAddress(to[i]);
	         }
	        ////System.out.println(Message.RecipientType.TO);
	        for( int j=0; j < toAddress.length; j++)
	        { 
	       	 message.addRecipient(Message.RecipientType.TO, toAddress[j]);
	        }
	        message.setSubject(title);
	        message.setContent(Body,"text/html");
	        Transport transport = session.getTransport("smtp");
	        transport.connect(host, from, pass);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	        return 0;
		}
		catch(Exception ex){
			return -1;
		}          
	}//end sendmail
}

