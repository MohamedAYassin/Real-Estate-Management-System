
package awtproject;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class FailedEntry extends Frame implements ActionListener, WindowListener {

  Button ok = new Button("Retry");
  Label text = new Label("Captcha verification failed!");

  public FailedEntry() {

    super("Verification Failed!!!!!");
    setLayout(null);

    text.setBounds(100, 25, 150, 50);
    add(text);

    ok.setBounds(100, 75, 150, 50);
    add(ok);

    setBounds(700, 300, 100, 100);
    setSize(350, 150);
    setResizable(false);
    setVisible(true);

    ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        dispose();
      }
    });

  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void windowClosed(WindowEvent e) {
    dispose();
  }

  @Override
  public void windowOpened(WindowEvent e) {

  }

  @Override
  public void windowClosing(WindowEvent e) {

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