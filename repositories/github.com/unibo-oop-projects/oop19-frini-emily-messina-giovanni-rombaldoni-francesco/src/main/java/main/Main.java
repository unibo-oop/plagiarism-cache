package main;

import com.formdev.flatlaf.FlatLightLaf;
import gui.*;
import javax.swing.UIManager;

/**
 * Starting the GUI.
 */

public class Main {
  /*
   * Load the look and feel theme.
   */
  public static void main(String[] args) {
    //<----------LOOK AND FEEL FOR THE GUI -------->
    try {
      UIManager.setLookAndFeel(new FlatLightLaf());
    } catch (Exception ex) {
      System.err.println("Failed to initialize LaF");
      }

//BuildAfterGui gui = new BuildAfterGui();
BuildPreGui gui = new BuildPreGui();

gui.start();
    }
    
}
