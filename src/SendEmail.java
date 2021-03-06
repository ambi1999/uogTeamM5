/**
 * @version     1.0                 (current version number of program)
 * @since     25 Feb 2014
 * This program has been modified by xyz.
 */
// March 2014 - xyz - Modified the code to implement the  abc feature
//replace xyz with your name and abc with the name of feature

//ssdfsdf
import glos.IO;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	static BufferedReader input;

	static OutputStream output;

	static String from = "";
	static String password = "";
	static String[] to = { "ajayal@glos.ac.uk" }; // list of recipient email
	// addresses
	static String host = "";
	static String portformail = "";

	private static void sendFromGMail(String host, String port, String from,
			String pass, String[] to, String subject, String body) {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Email sent successfully");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {

		from = IO.readString("Enter User Name");
		password = IO.readString("Enter Password");
		to[0]= IO.readString("Enter recipient email");
		host = IO
				.readString("Enter host, For gmail enter smtp.gmail.com, For yahoo enter smtp.mail.yahoo.com");
		portformail = IO
				.readString("Enter port for mail. Enter 465. If this does not work then try 587");

		String subject = "TEST =" + "EMAIL ";
		String body = "KEEP CALM AND START PROGRAMMING" + new Date().getTime();
		sendFromGMail(host, portformail, from, password, to, subject, body);

	}
}
