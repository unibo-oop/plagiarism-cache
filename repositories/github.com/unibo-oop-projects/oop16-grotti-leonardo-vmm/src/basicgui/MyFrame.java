package basicgui;

import javax.swing.JFrame;

public class MyFrame extends JFrame implements MyFrameInterface {

  private static final long serialVersionUID = -662579466348691312L;

  /**
   * Costruttore della classe MyFrame.
   * @param title è il titolo del frame.
   * @param mainPanel è il pannello principale del frame.
   */
  public MyFrame(final String title, final MyPanel mainPanel) {
    super(title);
    frame();
    this.getContentPane().add(mainPanel);
  }

  public MyFrame(final String title) {
    super(title);
    frame();
  }

  public void run() {
    this.isVisible();
  }

  public MyPanel getMainPanel() {
    return (MyPanel) this.getContentPane().getComponent(0);
  }

  public void setPanel(final MyPanel mainPanel) {
    this.getContentPane().add(mainPanel);
  }

  private void frame() {
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(500, 400);
  }

}
