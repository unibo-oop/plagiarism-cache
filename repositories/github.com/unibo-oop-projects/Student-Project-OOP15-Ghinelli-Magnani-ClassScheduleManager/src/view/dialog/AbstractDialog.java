package view.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * Abstract class responsible of the creation of the dialogs in the programs.
 *
 */

public abstract class AbstractDialog extends JDialog implements IAddDeleteDialog {

    /**
     * 
     */
    private static final long serialVersionUID = -3695151833842350492L;
    private final JPanel panelSud = new JPanel();
    private final List<JComboBox<String>> boxList = new ArrayList<>();
    
    /**
     * Constructor of the class.
     * @param frame The main frame of the program, used for the position of the dialog.
     */
    
    public AbstractDialog(final JFrame frame) {
        super();
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.setLayout(new BorderLayout());
        final JPanel panelNord = this.setFields();
        JButton button = new JButton("Ok");
        button.addActionListener(this.setOkListener());
        this.panelSud.add(button);
        button = new JButton("Cancel");
        button.addActionListener(e -> {
            this.setVisible(false);
        });
        this.panelSud.add(button);
        this.add(panelNord, BorderLayout.NORTH);
        this.add(panelSud, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(frame);
        this.setVisible(true);
    }
    
    /**
     * Method which gives the list of the JComboBox of the dialog.
     * @return The list of JComboBox.
     */
    
    protected List<JComboBox<String>> getBoxList() {
        return this.boxList;
    }
    
    @Override
    public abstract JPanel setFields();
    
    @Override
    public abstract ActionListener setOkListener();
    
}
