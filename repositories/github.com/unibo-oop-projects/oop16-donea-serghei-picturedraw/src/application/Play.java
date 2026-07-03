package application;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.SelectToolObserver;

public class Play {
    
    public static void main (String[] args) {
        try {
            UIManager.setLookAndFeel(
                   UIManager.getSystemLookAndFeelClassName());           
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        try {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new SelectToolObserver();
                }
            });
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
