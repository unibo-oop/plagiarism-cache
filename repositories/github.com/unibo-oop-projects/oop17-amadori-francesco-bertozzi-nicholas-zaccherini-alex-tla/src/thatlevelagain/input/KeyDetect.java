package thatlevelagain.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import thatlevelagain.view.panel.GamePanel;

/**
 * 
 * detect keyboard event.
 */
public class KeyDetect implements KeyListener {

    private final GamePanel component;

/**
 * 
 * @param component
 *   where to detect key event
 */
    public KeyDetect(final GamePanel component) {
        this.component = component;
    }

   /*input tastiera*/
   @Override
   public void keyTyped(final KeyEvent e) { }

   @Override
   public final void keyPressed(final KeyEvent e) {
       try {
           component.getManager().keyPressed(e.getKeyCode());
       } catch (IOException e1) {
           e1.printStackTrace();
      }
   }

   @Override
   public final void keyReleased(final KeyEvent e) {
       component.getManager().keyReleased(e.getKeyCode());
   }
}
