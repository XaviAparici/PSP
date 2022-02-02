package Ejercicis;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ej_3 {

	public static void envioMail(String mensaje, String asunto, String email_remitente, String email_remitente_pass, String host_email, String port_email, String[] email_destino, String[] anexo) throws UnsupportedEncodingException, MessagingException {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host_email);
		props.put("mail.smtp.user", email_remitente);
		props.put("mail.smtp.clave", email_remitente_pass);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port_email);
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(email_remitente));
		
		for (int i = 0; i < email_destino.length; i++) {
			message.addRecipients(Message.RecipientType.TO, email_destino[i]);
		}
		
		message.setSubject(asunto);
		BodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setText(mensaje);
		BodyPart[] anexos = new BodyPart[anexo.length];
		
		for (int i = 0; i < anexo.length; i++) {
			BodyPart messageBodyPart2 = new MimeBodyPart();
			DataSource src= new FileDataSource(anexo[i]);
			String nombre = src.getName();
			messageBodyPart2.setDataHandler(new DataHandler(src));
			messageBodyPart2.setFileName(nombre);
			anexos[i] = messageBodyPart2;
		}
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart1);
		for (int i = 0; i < anexos.length; i++) {
			multipart.addBodyPart(anexos[i]);
		}
		message.setContent(multipart);
		Transport transport = session.getTransport("smtp");
		transport.connect(host_email, email_remitente, email_remitente_pass);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
		String[] destinatarios = {"xaviaparici2000@gmail.com","ericquintero2002@gmail.com"};
		String[] direcciones = {"C:\\Users\\DAM 2\\Pictures\\Saved Pictures\\imagen.jpg","C:\\Users\\DAM 2\\Pictures\\Saved Pictures\\a.txt"};
		envioMail("hola", "hola", "programacioserveisxavi@gmail.com", "Psp123456", "smtp.gmail.com", "587", destinatarios, direcciones);
	}

}
