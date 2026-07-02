package it.unibo.game.app.view.jswing.implementation;

import it.unibo.game.Pair;
import it.unibo.game.app.view.jswing.api.UIController;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * JDialog where player can insert is name and password anda save his points.
 */
public class SaveScore extends JDialog {

  private static final int COLS = 20;
  private static final int H = 150;
  private static final int W = 400;
  private static final int BORDER = 3;
  private JPanel panel = new JPanel(new GridBagLayout());
  private JTextField name = new JTextField(COLS);
  private JTextField password = new JTextField(COLS);
  private List<Pair<JLabel, JTextField>> fields = new ArrayList<>(
      List.of(new Pair<>(new JLabel("Insert name: "), this.name),
          new Pair<>(new JLabel("Insert password: "), this.password)));
  private JButton button = new JButton("OK");

  /**
   * constructor of class.
   * 
   * @param control UIController to call the method to save user's points
   */
  public SaveScore(final UIController control) {
    this.init(control);
    this.add(panel);
    this.setSize(W, H);
    this.setBounds(W / 2, W / 2, W, H);
    this.setVisible(true);
  }

  /**
   * method to add components to the JDialog.
   * 
   * @param control UIController to call the method to save user's points
   */
  private void init(final UIController control) {
    GridBagConstraints cnst = new GridBagConstraints();
    cnst.insets = new Insets(BORDER, BORDER, BORDER, BORDER);
    cnst.fill = GridBagConstraints.CENTER;

    for (cnst.gridy = 0, cnst.gridx = 0; cnst.gridy < 2; cnst.gridx = 0, cnst.gridy++) {
      panel.add(fields.get(cnst.gridy).getX(), cnst);
      cnst.gridx++;
      panel.add(fields.get(cnst.gridy).getY(), cnst);
    }

    button.addActionListener(e -> {
      if (name.getText().length() != 0 && password.getText().length() != 0) {
        control.updatePoints(name.getText(), password.getText());
        this.dispose();
      } else {
        JOptionPane.showMessageDialog(this, "Please insert name and password");
      }
    });
    cnst.gridx++;
    panel.add(button, cnst);
  }
}
