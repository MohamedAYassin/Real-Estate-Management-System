/*

C16 12300147 شنوده مكرم ابراهيم عبده
C11 12300414 محمد احمد محمد يسن
C2 12300155 مصطفي محمد خطاب سيد
C11 12200592 مروان ايمن عبد العزيز
C11 12300271 مايكل محب انيس قلد

*/

//CAPTCHA FRAME
package awtproject;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

public class Captcha extends Frame implements ActionListener, WindowListener {
  private Canvas canvas;
  private String text;
  Button regen = new Button("Regenerate");
  Button verify = new Button("Verify");
  TextField input = new TextField();
  Label caplabel = new Label();

  Captcha(final String passthrough) {
    super("reCAPTCHA Verification");
    setLayout(null);
    // START OF CAPTCHA CREATION
    Properties props = new Properties();
    props.put(Constants.KAPTCHA_PRODUCER_IMPL, "com.google.code.kaptcha.impl.DefaultKaptcha");
    props.put(Constants.KAPTCHA_BORDER, "no");
    props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "28");
    props.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "6");
    props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
    props.put(Constants.KAPTCHA_IMAGE_WIDTH, "250");
    props.put(Constants.KAPTCHA_IMAGE_HEIGHT, "80");
    Config config = new Config(props);
    final DefaultKaptcha kaptcha = new DefaultKaptcha();
    kaptcha.setConfig(config);
    text = kaptcha.createText();
    final Image image = kaptcha.createImage(text);

    canvas = new Canvas() {
      public void paint(Graphics graphics) {
        graphics.drawImage(image, 0, 0, null);
      }
    };
    canvas.setBounds(75, 30, 250, 80);
    add(canvas);
    // END OF CAPTCHA CREATION

    verify.setBounds(80, 120, 100, 30);
    regen.setBounds(200, 120, 100, 30);
    input.setBounds(130, 160, 150, 30);
    add(regen);
    add(verify);
    add(input);

    regen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        input.selectAll();
        input.setText("");
        remove(canvas);
        text = kaptcha.createText();
        final Image image = kaptcha.createImage(text);
        Canvas newCanvas = new Canvas() {
          public void paint(Graphics graphics) {
            graphics.drawImage(image, 0, 0, null);
          }
        };
        newCanvas.setBounds(75, 30, 250, 80);
        add(newCanvas);
        canvas = newCanvas;
        revalidate();
        repaint();
      }
    });

    verify.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String enteredText = input.getText();
        if (enteredText.equalsIgnoreCase(text)) {
          dispose();

          ForgotPassword forgotpassword = new ForgotPassword(passthrough);
          forgotpassword.setVisible(true);

        } else {
          FailedEntry failedentry = new FailedEntry();
          failedentry.setVisible(true);
        }
      }
    });

    setBounds(700, 300, 100, 100);
    setSize(400, 200);
    setResizable(false);
    setVisible(true);
    addWindowListener(this);
    regen.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void windowClosed(WindowEvent e) {

  }

  @Override
  public void windowOpened(WindowEvent e) {

  }

  @Override
  public void windowClosing(WindowEvent e) {
    dispose();
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