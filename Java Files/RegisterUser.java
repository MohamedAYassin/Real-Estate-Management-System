/*

C16 12300147 شنوده مكرم ابراهيم عبده
C11 12300414 محمد احمد محمد يسن
C2 12300155 مصطفي محمد خطاب سيد
C11 12200592 مروان ايمن عبد العزيز
C11 12300271 مايكل محب انيس قلد

*/


package awtproject;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

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

public class RegisterUser extends Frame implements ActionListener, WindowListener {
  private Connection conn;
  Button registerButton = new Button("Register");
  private Label info = new Label("New SQL Username:");
  private Label info2 = new Label("New SQL Password:");
  private TextField usernameField = new TextField("");
  private TextField passwordField = new TextField("");

  RegisterUser(final String passthrough) {

    super("Register New User");
    setLayout(null);
    add(info);
    info.setBounds(10, 38, 150, 25);
    add(info2);
    info2.setBounds(10, 108, 150, 25);
    add(usernameField);
    usernameField.setBounds(165, 38, 265, 25);
    add(passwordField);
    passwordField.setBounds(165, 108, 265, 25);
    add(registerButton);
    registerButton.setBounds(165, 157, 265, 35);

    registerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (usernameField.getText().equals("") || usernameField.getText().equals("ERROR") || passwordField.getText().equals("") || passwordField.getText().equals("ERROR")) {
          usernameField.setText("ERROR");
          passwordField.setText("ERROR");
        } else {
          try {
            String first = usernameField.getText();
            String second = passwordField.getText();
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = passthrough;
            String databaseName = url.substring(url.lastIndexOf("/") + 1);
            String username = "masteruser";
            String password = "modernMAM";
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String sql;
            if (url.startsWith("jdbc:mysql:")) {
              sql = "CREATE USER '" + first + "'@'%' IDENTIFIED BY '" + new String(second) + "'";
              stmt.executeUpdate(sql);
              sql = "GRANT SELECT, INSERT, UPDATE, DELETE ON " + databaseName + ".* TO  '" + first + "'@'%'";
              stmt.executeUpdate(sql);
            } else if (url.startsWith("jdbc:sqlserver:")) {
              sql = "CREATE LOGIN " + first + " WITH PASSWORD = '" + new String(second) + "', CHECK_POLICY = OFF, CHECK_EXPIRATION = OFF";
              stmt.executeUpdate(sql);
              sql = "EXEC sp_addsrvrolemember @loginame = '" + first + "', @rolename = 'sysadmin'";
            } else {
              throw new SQLException("Unsupported database type");
            }
            stmt.executeUpdate(sql);
            System.out.println("SUCCESS");
            dispose();
          } catch (SQLException ex) {
            Logger.getLogger(RegisterUser.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR");
          } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterUser.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR");
          }

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
    setSize(450, 205);
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