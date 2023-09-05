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
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class OperationFrame extends Frame implements ActionListener, WindowListener {
  private Image image;
  private Canvas canvas;
  Button exit = new Button("EXIT");
  Button clear = new Button("CLEAR");
  Button insert = new Button("INSERT");
  Button update = new Button("UPDATE");
  Button search = new Button("SEARCH");
  Button delete = new Button("DELETE");

  OperationFrame(final Connection conn) {

    super("Real-estate Firm Database System");
    setLayout(null);
    try {
      image = ImageIO.read(getClass().getResource("main.jpg"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    canvas = new Canvas() {
      public void paint(Graphics graphics) {
        graphics.drawImage(image, 0, 0, null);
      }
    };

    final TextField propertyID = new TextField("");
    Label propertyIDText = new Label("Property ID:");
    add(exit);
    exit.setBounds(690, 350, 150, 50);
    add(clear);
    clear.setBounds(690, 290, 150, 50);
    add(delete);
    delete.setBounds(490, 360, 150, 30);
    add(search);
    search.setBounds(340, 360, 150, 30);
    add(update);
    update.setBounds(190, 360, 150, 30);
    add(insert);
    insert.setBounds(40, 360, 150, 30);

    Label title1 = new Label("Real-estate Firm Database System");
    add(title1);
    title1.setBounds(30, 40, 200, 30);

    add(propertyID);
    propertyID.setBounds(160, 90, 270, 30);
    add(propertyIDText);
    propertyIDText.setBounds(30, 90, 150, 30);
    final TextField address = new TextField("");
    Label addressText = new Label("Address:");
    add(address);
    address.setBounds(160, 130, 270, 30);
    add(addressText);
    addressText.setBounds(30, 130, 270, 30);

    final TextField city = new TextField("");
    Label cityText = new Label("City:");
    add(city);
    city.setBounds(160, 170, 270, 30);
    add(cityText);
    cityText.setBounds(30, 170, 270, 30);

    final TextField state = new TextField("");
    Label stateText = new Label("State:");
    add(state);
    state.setBounds(160, 210, 270, 30);
    add(stateText);
    stateText.setBounds(30, 210, 270, 30);

    final TextField postcode = new TextField("");
    Label postcodeText = new Label("Postcode:");
    add(postcode);
    postcode.setBounds(160, 250, 270, 30);
    add(postcodeText);
    postcodeText.setBounds(30, 250, 270, 30);

    final TextField officenumber = new TextField("");
    Label officenumberText = new Label("Office Number:");
    add(officenumber);
    officenumber.setBounds(160, 290, 270, 30);
    add(officenumberText);
    officenumberText.setBounds(30, 290, 270, 30);

    final TextField ownername = new TextField("");
    Label ownernameText = new Label("Owner Name:");
    add(ownername);
    ownername.setBounds(550, 90, 270, 30);
    add(ownernameText);
    ownernameText.setBounds(460, 90, 270, 30);

    final TextField ownerID = new TextField("");
    Label ownerIDText = new Label("Owner ID:");
    add(ownerID);
    ownerID.setBounds(550, 130, 270, 30);
    add(ownerIDText);
    ownerIDText.setBounds(460, 130, 270, 30);

    final TextField percent = new TextField("");
    Label percentText = new Label("Percent Owned:");
    add(percent);
    percent.setBounds(550, 170, 270, 30);
    add(percentText);
    percentText.setBounds(460, 170, 270, 30);

    final TextField deleteowner = new TextField("");
    Label deleteownerText = new Label("Delete Owner:");
    add(deleteowner);
    deleteowner.setBounds(550, 210, 270, 30);
    add(deleteownerText);
    deleteownerText.setBounds(460, 210, 270, 30);
    Button deleteownerButton = new Button("DELETE OWNER");
    add(deleteownerButton);
    deleteownerButton.setBounds(460, 250, 360, 30);

    exit.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }

    });

    insert.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          int propertyIDS = Integer.parseInt(propertyID.getText());
          String addressS = address.getText();
          String cityS = city.getText();
          String stateS = state.getText();
          String postcodeS = postcode.getText();
          int officeNumber = Integer.parseInt(officenumber.getText());
          String ownerName = ownername.getText();
          int ownerIDS = Integer.parseInt(ownerID.getText());
          BigDecimal percentOwned = new BigDecimal(percent.getText());
          PreparedStatement stmt1 = conn.prepareStatement("SELECT COUNT(*) FROM Sales_Office WHERE Office_Number = ?");
          stmt1.setInt(1, officeNumber);
          ResultSet rs1 = stmt1.executeQuery();
          rs1.next();
          int officeCount = rs1.getInt(1);
          if (officeCount == 0) {
            PreparedStatement psSalesOffice = conn.prepareStatement("INSERT INTO Sales_Office VALUES (?, ?)");
            psSalesOffice.setInt(1, officeNumber);
            psSalesOffice.setString(2, addressS);
            psSalesOffice.executeUpdate();
          }
          PreparedStatement psProperty = conn.prepareStatement("INSERT INTO Property VALUES (?, ?, ?, ?, ?, ?)");
          psProperty.setInt(1, propertyIDS);
          psProperty.setString(2, addressS);
          psProperty.setString(3, cityS);
          psProperty.setString(4, stateS);
          psProperty.setString(5, postcodeS);
          psProperty.setInt(6, officeNumber);
          psProperty.executeUpdate();
          PreparedStatement stmt2 = conn.prepareStatement("SELECT COUNT(*) FROM Owner WHERE Owner_ID = ?");
          stmt2.setInt(1, ownerIDS);
          ResultSet rs2 = stmt2.executeQuery();
          rs2.next();
          int ownerCount = rs2.getInt(1);
          if (ownerCount == 0) {
            PreparedStatement psOwner = conn.prepareStatement("INSERT INTO Owner VALUES (?, ?)");
            psOwner.setInt(1, ownerIDS);
            psOwner.setString(2, ownerName);
            psOwner.executeUpdate();
          }
          PreparedStatement psPropertyOwner = conn.prepareStatement("INSERT INTO Property_Owner VALUES (?, ?, ?)");
          psPropertyOwner.setInt(1, propertyIDS);
          psPropertyOwner.setInt(2, ownerIDS);
          psPropertyOwner.setBigDecimal(3, percentOwned);
          psPropertyOwner.executeUpdate();
          System.out.println("Record inserted successfully.");
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }

      }
    });

    update.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          String dbType = conn.getMetaData().getDatabaseProductName();
          String updatePropertyOwner;
          int propertyIDS = Integer.parseInt(propertyID.getText());
          String addressS = address.getText();
          String cityS = city.getText();
          String stateS = state.getText();
          String postcodeS = postcode.getText();
          int officeNumber = Integer.parseInt(officenumber.getText());
          int ownerIDS = Integer.parseInt(ownerID.getText());
          String ownerName = ownername.getText();
          double percentOwned = Double.parseDouble(percent.getText());
          String checkProperty = "SELECT Property_ID FROM Property WHERE Property_ID = ?";
          PreparedStatement checkStmt = conn.prepareStatement(checkProperty);
          checkStmt.setInt(1, propertyIDS);
          ResultSet checkRs = checkStmt.executeQuery();
          if (!checkRs.next()) {
            System.out.println("Property with ID " + propertyIDS + " does not exist in the database.");
            return;
          }
          String updateProperty = "UPDATE Property SET Address = ?, City = ?, State = ?, Postcode = ?, Office_Number = ? WHERE Property_ID = ?";
          PreparedStatement pstmt = conn.prepareStatement(updateProperty);
          pstmt.setString(1, addressS);
          pstmt.setString(2, cityS);
          pstmt.setString(3, stateS);
          pstmt.setString(4, postcodeS);
          pstmt.setInt(5, officeNumber);
          pstmt.setInt(6, propertyIDS);
          pstmt.executeUpdate();
          String checkOwner = "SELECT Owner_ID FROM Owner WHERE Owner_ID = ?";
          pstmt = conn.prepareStatement(checkOwner);
          pstmt.setInt(1, ownerIDS);
          ResultSet rs = pstmt.executeQuery();
          if (!rs.next()) {
            String insertOwner = "INSERT INTO Owner (Owner_ID, Owner_Name) VALUES (?, ?)";
            pstmt = conn.prepareStatement(insertOwner);
            pstmt.setInt(1, ownerIDS);
            pstmt.setString(2, ownerName);
            pstmt.executeUpdate();
          }
          if (dbType.equals("MySQL")) {
            updatePropertyOwner = "UPDATE Property_Owner po JOIN Owner o ON po.Owner_ID = o.Owner_ID SET po.Owner_ID = ?, po.Percent_Owned = ? WHERE po.Property_ID = ?";
          } else if (dbType.equals("Microsoft SQL Server")) {
            updatePropertyOwner = "UPDATE Property_Owner SET Owner_ID = ?, Percent_Owned = ? FROM Property_Owner po JOIN Owner o ON po.Owner_ID = o.Owner_ID WHERE po.Property_ID = ?";
          } else {
            System.out.println("Database not supported.");
            return;
          }
          pstmt = conn.prepareStatement(updatePropertyOwner);
          pstmt.setInt(1, ownerIDS);
          pstmt.setDouble(2, percentOwned);
          pstmt.setInt(3, propertyIDS);
          pstmt.executeUpdate();
          System.out.println("Property updated successfully.");
        } catch (SQLException ex) {
          System.out.println("Error updating property: " + ex.getMessage());
        } catch (NumberFormatException ex) {
          System.out.println("Please enter valid numbers for Property ID, Office Number, Owner ID, and Percent Owned.");
        }
      }
    });

    search.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (propertyID.getText().equals("")) {
          System.out.println("EMPTY ERROR!");
        } else {
          int passing = Integer.parseInt(propertyID.getText());
          SearchResult searchresult = new SearchResult(conn, passing);
        }

      }
    });

    delete.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (propertyID.getText().equals("")) {
          System.out.println("EMPTY ERROR!");
        } else {
          int propertyId = Integer.parseInt(propertyID.getText());
          try {
            PreparedStatement stmt1 = conn.prepareStatement("UPDATE Property SET Office_Number = (SELECT MIN(Office_Number) FROM Sales_Office) WHERE Property_ID = ?");
            stmt1.setInt(1, propertyId);
            stmt1.executeUpdate();
            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Property_Owner WHERE Property_ID = ?");
            stmt2.setInt(1, propertyId);
            stmt2.executeUpdate();
            PreparedStatement stmt3 = conn.prepareStatement("DELETE FROM Property WHERE Property_ID = ?");
            stmt3.setInt(1, propertyId);
            int affectedRows = stmt3.executeUpdate();
            if (affectedRows > 0) {
              System.out.println("Record deleted successfully.");
            } else {
              System.out.println("Record not found or already deleted.");
            }
          } catch (SQLException ex) {
            System.out.println("Error deleting record: " + ex.getMessage());
          }
        }
      }
    });

    deleteownerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (deleteowner.getText().equals("")) {
          System.out.println("EMPTY ERROR!");
        } else {
          int ownerId = Integer.parseInt(deleteowner.getText());
          String sql = "DELETE FROM Owner WHERE Owner_ID = ?";
          try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ownerId);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
              System.out.println("Owner with ID " + ownerId + " was deleted successfully!");
            } else {
              System.out.println("No owner with ID " + ownerId + " found!");
            }
          } catch (SQLException ex) {
            System.out.println("Error deleting record: " + ex.getMessage());
          }
        }
      }
    });

    clear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        propertyID.selectAll();
        propertyID.setText("");
        address.selectAll();
        address.setText("");
        city.selectAll();
        city.setText("");
        state.selectAll();
        state.setText("");
        postcode.selectAll();
        postcode.setText("");
        officenumber.selectAll();
        officenumber.setText("");
        ownername.selectAll();
        ownername.setText("");
        ownerID.selectAll();
        ownerID.setText("");
        percent.selectAll();
        percent.setText("");
        deleteowner.selectAll();
        deleteowner.setText("");
      }
    });

    setBounds(700, 300, 100, 100);
    setSize(850, 420);
    setResizable(false);
    setVisible(true);

    canvas.setBounds(0, 0, 950, 600);
    add(canvas);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

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