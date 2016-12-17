/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nk.businesslogic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author awarsyle
 */
public class WebMailService {
    public static void sendMail(HttpServletRequest request, String to, String subject, String template, HashMap<String, String> contents) throws IOException, MessagingException, NamingException {
        String body;
        body = new String(Files.readAllBytes(Paths.get(template)));
        
        for(String key : contents.keySet()) {
            body = body.replace("{{" + key + "}}", contents.get(key));
        }
        
        sendMail(request, to, subject, body, true);
    }
    
    
    public static void sendMail(HttpServletRequest request, String to, String subject, String body, boolean bodyIsHTML) throws MessagingException, NamingException {
        Context env = (Context)new InitialContext().lookup("java:comp/env");
        String username = (String)env.lookup("webmail-username");
        String password = (String)env.lookup("webmail-password");
        
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", 465);
        props.put("mail.smtps.auth", "true");
        props.put("mai.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);
        
       // create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {

            MimeMultipart multipart = new MimeMultipart("related");

            //add image
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html");
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            String image = request.getServletContext().getRealPath("/WEB-INF/res/noteskeepr.PNG");
            DataSource fds = new FileDataSource(image);

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            multipart.addBodyPart(messageBodyPart);
            
            message.setContent(multipart);
        } else {
            message.setText(body);
        }
        
        // address the message
        Address fromAddress = new InternetAddress("jameskongcprg352@gmail.com");
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        
        // send the message
        Transport transport = session.getTransport();
        transport.connect(username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
