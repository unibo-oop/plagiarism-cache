package view.menu;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import view.dialog.DeleteProfessorDialog;
import view.dialog.DeleteTeachingDialog;

/**
 * 
 * Class which is a menu used to show the dialogs used to delete teachings and professors.
 *
 */

public class DeleteMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = -5935578034798900068L;
    
    /**
     * Constructor of the menu.
     * @param frame The main frame of the program.
     */
    
    public DeleteMenu(final JFrame frame) {
        super("Delete");
        JMenuItem menuItem = new JMenuItem("Delete teaching");
        menuItem.addActionListener(e -> {
            new DeleteTeachingDialog(frame);
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Delete professor");
        menuItem.addActionListener(e -> {
            new DeleteProfessorDialog(frame);
        });
        this.add(menuItem);
    }

}
