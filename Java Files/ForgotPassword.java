/*

C16 12300147 شنوده مكرم ابراهيم عبده
C11 12300414 محمد احمد محمد يسن
C2 12300155 مصطفي محمد خطاب سيد
C11 12200592 مروان ايمن عبد العزيز
C11 12300271 مايكل محب انيس قلد

*/


package awtproject;

import java.util.*;
import java.util.Random;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ForgotPassword extends Frame implements ActionListener, WindowListener {

  Button emailButton = new Button("Send Recovery Email");
  private Label info1 = new Label("Email Address:");
  private Label info2 = new Label("SQL Username:");
  private Label info3 = new Label("New Password:");
  private TextField emailInput = new TextField("");
  private TextField usernameInput = new TextField("");
  private TextField passwordInput = new TextField("");

  ForgotPassword(final String passthrough) {
    super("Password Recovery");
    setLayout(null);

    add(emailButton);
    emailButton.setBounds(145, 200, 130, 30);
    add(info1);
    info1.setBounds(10, 50, 130, 30);
    add(info2);
    info2.setBounds(10, 100, 130, 30);
    add(info3);
    info3.setBounds(10, 150, 130, 30);

    add(emailInput);
    emailInput.setBounds(140, 50, 260, 30);
    add(usernameInput);
    usernameInput.setBounds(140, 100, 260, 30);
    add(passwordInput);
    passwordInput.setBounds(140, 150, 260, 30);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        dispose();
      }
    });
    emailButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (emailInput.getText().equals("") || emailInput.getText().equals("ERROR") || usernameInput.getText().equals("") || usernameInput.getText().equals("ERROR")) {
          emailInput.setText("ERROR");
          usernameInput.setText("ERROR");
        } else {
          final String gemail = "realestatemodernproject@gmail.com";
          final String gemailPassword = "rnxgdnydqkdqsvfo";
          String smtpServer = "smtp.gmail.com";
          int smtpPort = 587;
          String verificationCode = generateVerificationCode();
          String subject = "Password Reset Verification Code";
          String message = "Your verification code is: " + verificationCode;
          Properties props = new Properties();
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.starttls.enable", "true");
          props.put("mail.smtp.host", smtpServer);
          props.put("mail.smtp.port", smtpPort);
          Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(gemail, gemailPassword);
            }
          });
          Message msg = new MimeMessage(session);
          try {
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            msg.setFrom(new InternetAddress(gemail));
          } catch (MessagingException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ClassNotFoundException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
          }
          try {
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailInput.getText()));
          } catch (MessagingException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
          }
          try {
            msg.setSubject(subject);
          } catch (MessagingException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
          }
          try {
            msg.setText(message);
          } catch (MessagingException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
          }
          try {
            Transport.send(msg);
          } catch (MessagingException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
          }
          String pass2 = passwordInput.getText();
          String pass1 = passthrough;
          String pass3 = usernameInput.getText();
          dispose();
          CodeEntry codeentry = new CodeEntry(verificationCode, pass2, passthrough, pass3);
        }
      }
    });
    setBounds(700, 300, 100, 100);
    setSize(420, 250);
    setResizable(false);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void windowOpened(WindowEvent e) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void windowClosing(WindowEvent e) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void windowClosed(WindowEvent e) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void windowIconified(WindowEvent e) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void windowDeiconified(WindowEvent e) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void windowActivated(WindowEvent e) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void windowDeactivated(WindowEvent e) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  private static String generateVerificationCode() {
    int length = 6;
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random rng = new Random();
    char[] code = new char[length];
    for (int i = 0; i < length; i++) {
      code[i] = characters.charAt(rng.nextInt(characters.length()));
    }
    return new String(code);
  }
}