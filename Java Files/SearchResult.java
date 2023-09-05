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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
public class SearchResult extends Frame implements ActionListener, WindowListener {

  SearchResult(Connection conn, int passing) {
    super("Search Results");
    setLayout(null);
    Label propertyIDInput = new Label("");
    Label addressInput = new Label("");
    Label cityInput = new Label("");
    Label stateInput = new Label("");
    Label postcodeInput = new Label("");
    Label officeNumberInput = new Label("");
    Label ownerIDInput = new Label("");
    Label ownerNameInput = new Label("");
    Label percentInput = new Label("");
    add(propertyIDInput);
    propertyIDInput.setBounds(30, 30, 250, 30);
    add(addressInput);
    addressInput.setBounds(30, 60, 250, 30);
    add(cityInput);
    cityInput.setBounds(30, 90, 250, 30);
    add(stateInput);
    stateInput.setBounds(30, 120, 250, 30);
    add(postcodeInput);
    postcodeInput.setBounds(30, 150, 250, 30);
    add(officeNumberInput);
    officeNumberInput.setBounds(30, 180, 250, 30);
    add(ownerIDInput);
    ownerIDInput.setBounds(320, 30, 250, 30);
    add(ownerNameInput);
    ownerNameInput.setBounds(320, 60, 250, 30);
    add(percentInput);
    percentInput.setBounds(320, 90, 250, 30);

    try {
      int propertyId = passing;
      PreparedStatement stmt = conn.prepareStatement("SELECT Property.Property_ID, Address, City, State, Postcode, Property.Office_Number, Owner.Owner_ID, Owner_Name, Percent_Owned FROM Property JOIN Property_Owner ON Property.Property_ID = Property_Owner.Property_ID JOIN Owner ON Property_Owner.Owner_ID = Owner.Owner_ID WHERE Property.Property_ID = ?");
      stmt.setInt(1, propertyId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        propertyIDInput.setText("Property ID: " + Integer.toString(rs.getInt("Property_ID")));
        addressInput.setText("Address: " + rs.getString("Address"));
        cityInput.setText("City: " + rs.getString("City"));
        stateInput.setText("State: " + rs.getString("State"));
        postcodeInput.setText("Postcode: " + rs.getString("Postcode"));
        officeNumberInput.setText("Office Number: " + Integer.toString(rs.getInt("Office_Number")));
        ownerIDInput.setText("Owner ID: " + Integer.toString(rs.getInt("Owner_ID")));
        ownerNameInput.setText("Owner Name: " + rs.getString("Owner_Name"));
        percentInput.setText("Percent Owned: " + rs.getBigDecimal("Percent_Owned").toString() + "%");
      } else {
        propertyIDInput.setText("NOT FOUND");
        propertyIDInput.setBounds(195, 90, 250, 30);
        System.out.println("No matching record found.");

      }
    } catch (SQLException ex) {

      System.out.println("Error searching for record: " + ex.getMessage());

    }

    setBounds(700, 300, 100, 100);
    setSize(550, 220);
    setResizable(false);
    setVisible(true);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        dispose();
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