package view.menu;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import view.dialog.AddLessonDialog;
import view.dialog.AddTeachingDialog;

/**
 * 
 * Class which represents the menu which is used to show the dialogs used to add teachings and lessons to the program.
 *
 */

public class AddMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = 4790546772035194942L;
    
    /**
     * Constructor of the menu.
     * @param frame The main frame of the program.
     */
    
    public AddMenu(final JFrame frame) {
        super("Add");
        JMenuItem menuItem = new JMenuItem("Add teaching");
        menuItem.addActionListener(e -> {
            new AddTeachingDialog(frame);
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Add lesson");
        menuItem.addActionListener(e -> {
            new AddLessonDialog(frame);
        });
        this.add(menuItem);
    }

}
