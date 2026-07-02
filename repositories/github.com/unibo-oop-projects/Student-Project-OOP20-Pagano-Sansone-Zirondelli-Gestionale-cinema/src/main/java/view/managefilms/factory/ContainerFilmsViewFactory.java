package view.managefilms.factory;

import java.awt.LayoutManager;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
    Factory to create components for container films GUI.
*/

public interface ContainerFilmsViewFactory {
    /**
        Creates personalized panel using specific layout manager.
        @param layout layout to used.
        @return panel
     */
    JPanel createPanel(LayoutManager layout);
    /**
        Creates personalized button using with specific text.
        @param text text to used
        @return panel
     */
    JButton createButtonWithText(String text);
    /**
        Creates personalized button with icon.
        @param icon icon to used.
        @return button
     */
    JButton createButtonWithIcon(Icon icon);

}
 