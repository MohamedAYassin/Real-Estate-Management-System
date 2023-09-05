

//LOGIN FRAME
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
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class MainFrame extends Frame implements ActionListener, WindowListener {

  private Image image;
  private Canvas canvas;
  private Connection conn;
  public TextField databasePathField = new TextField("jdbc:sqlserver://localhost;databaseName=#####");
  private TextField usernameField = new TextField("#####");
  private TextField passwordField = new TextField("#####");
  private Label upper = new Label("Database Path:");
  private Label center = new Label("Username:");
  private Label lower = new Label("Password:");
  private Label title = new Label("Real-estate Firm Database System Entry");
  private Label info = new Label("Show password");
  Button connectButton = new Button("Connect");
  Button registerButton = new Button("Register");
  Button reassignButton = new Button("Forgot Password");
  Checkbox showPasswordCheckBox = new Checkbox("");

  public MainFrame() {
    super("Real-estate Firm Database System");
    setLayout(null);
    try {
      image = ImageIO.read(getClass().getResource("background.jpg"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    canvas = new Canvas() {
      public void paint(Graphics graphics) {
        graphics.drawImage(image, 0, 0, null);
      }
    };

    canvas.setBounds(0, 0, 600, 60);
    add(canvas);

    add(title);
    title.setBounds(10, 28, 230, 25);
    add(canvas);
    add(upper);
    upper.setBounds(10, 62, 100, 25);
    add(center);
    center.setBounds(10, 92, 100, 25);
    add(lower);
    lower.setBounds(10, 122, 100, 25);
    add(info);
    info.setBounds(297, 147, 100, 25);

    add(showPasswordCheckBox);
    showPasswordCheckBox.setBounds(282, 147, 40, 25);

    add(databasePathField);
    databasePathField.setBounds(280, 62, 300, 25);
    add(usernameField);
    usernameField.setBounds(280, 92, 300, 25);
    add(passwordField);
    passwordField.setBounds(280, 122, 300, 25);
    passwordField.setEchoChar('*');

    add(connectButton);
    connectButton.setBounds(97, 185, 130, 30);
    add(registerButton);
    registerButton.setBounds(237, 185, 130, 30);
    add(reassignButton);
    reassignButton.setBounds(377, 185, 130, 30);

    setBounds(700, 300, 100, 100);
    setSize(600, 225);
    setResizable(false);
    setVisible(true);

    connectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          Class.forName("com.mysql.jdbc.Driver");
          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
          String url = databasePathField.getText();
          String username = usernameField.getText();
          String password = new String(passwordField.getText());
          conn = DriverManager.getConnection(url, username, password);
          OperationFrame operationframe = new OperationFrame(conn);
          operationframe.setVisible(true);
          MainFrame.this.dispose();
        } catch (SQLException ex) {
          Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
          Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });

    registerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        final String passthrough = databasePathField.getText();
        RegisterUser register = new RegisterUser(passthrough);
        register.setVisible(true);
      }
    });

    reassignButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        final String passthrough = databasePathField.getText();
        Captcha captcha = new Captcha(passthrough);
        captcha.setVisible(true);
      }
    });

    connectButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited");
      }
      public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered");
      }
    });

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    showPasswordCheckBox.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == showPasswordCheckBox) {
          if (showPasswordCheckBox.getState()) {
            passwordField.setEchoChar((char) 0);
          } else {
            passwordField.setEchoChar('*');
          }
        }
      }
    });
  }

  public static void main(String[] args) {
    MainFrame frame = new MainFrame();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == showPasswordCheckBox) {
      if (showPasswordCheckBox.getState()) {
        passwordField.setEchoChar((char) 0);
      } else {
        passwordField.setEchoChar('*');
      }
    }
  }

  @Override
  public void windowOpened(WindowEvent e) {

  }

  @Override
  public void windowClosing(WindowEvent e) {

  }

  @Override
  public void windowClosed(WindowEvent e) {

  }

  @Override
  public void windowIconified(WindowEvent e) {

  }

  @Override
  public void windowDeiconified(WindowEvent e) {

  }

  @Override
  public void windowActivated(WindowEvent e) {

  }

  @Override
  public void windowDeactivated(WindowEvent e) {

  }

}