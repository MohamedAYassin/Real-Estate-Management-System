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

public class CodeEntry extends Frame implements ActionListener, WindowListener {

  Button emailCodeButton = new Button("Verify Code");
  private Label info1 = new Label("Enter code sent to your Email Address:");
  private TextField codeInput = new TextField("");

  CodeEntry(final String verificationCode, final String pass2, final String passthrough, final String pass3) {
    super("Password Recovery");
    setLayout(null);
    add(info1);
    info1.setBounds(10, 50, 215, 30);
    add(codeInput);
    codeInput.setBounds(235, 50, 165, 30);
    add(emailCodeButton);
    emailCodeButton.setBounds(130, 100, 130, 30);

    System.out.println(verificationCode);

    emailCodeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        if (codeInput.getText().equals(verificationCode)) {
          try {
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String surl = passthrough;
            String susername = "masteruser";
            String spassword = "modernMAM";

            Connection conn = DriverManager.getConnection(surl, susername, spassword);
            String dbms = conn.getMetaData().getDatabaseProductName();
            String sql = "";
            if (dbms.equals("Microsoft SQL Server")) {
              sql = "ALTER LOGIN [" + pass3 + "] WITH PASSWORD = '" + pass2 + "';";
            } else if (dbms.equals("MySQL")) {
              sql = "ALTER USER '" + pass3 + "'@'%' IDENTIFIED BY '" + pass2 + "';";
            }
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("SUCCESS");
            dispose();

          } catch (SQLException ex) {
            Logger.getLogger(CodeEntry.class.getName()).log(Level.SEVERE, null, ex);
            codeInput.setText("ERROR");
          } catch (ClassNotFoundException ex) {
            Logger.getLogger(CodeEntry.class.getName()).log(Level.SEVERE, null, ex);
          }
        } else {

          codeInput.setText("ERROR");

        }
      }
    });

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        dispose();
      }
    });
    setBounds(700, 300, 100, 100);
    setSize(420, 150);
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

}