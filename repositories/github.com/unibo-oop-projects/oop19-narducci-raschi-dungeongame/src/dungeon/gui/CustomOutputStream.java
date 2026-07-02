package dungeon.gui;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

/**
 * The Class CustomOutputStream.
 */
public final class CustomOutputStream extends OutputStream {

  /** The text area. */
  private final JTextArea textArea;

  /**
   * Instantiates a new custom output stream.
   *
   * @param textArea the text area
   */
  public CustomOutputStream(final JTextArea textArea) {
    this.textArea = textArea;
  }

  /**
   * 
   *
   * {@inheritDoc}
   */
  @Override
  public void write(final int b) throws IOException {
    textArea.append(String.valueOf((char) b));
    textArea.setCaretPosition(textArea.getDocument().getLength());
  }
}
