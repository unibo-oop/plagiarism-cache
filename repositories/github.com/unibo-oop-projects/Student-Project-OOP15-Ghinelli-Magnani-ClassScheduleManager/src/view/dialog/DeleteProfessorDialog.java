package view.dialog;

import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import view.utility.DialogManager;

/**
 * 
 * Dialog used to delete a certain professor from the program.
 *
 */

public class DeleteProfessorDialog extends AbstractDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 754603152453800261L;
    
    /**
     * Constructor of the dialog.
     * @param frame The main frame of the program.
     */

    public DeleteProfessorDialog(final JFrame frame) {
        super(frame);
    }

    @Override
    public JPanel setFields() {
        final JPanel panelNord = new JPanel();
        panelNord.setLayout(new GridBagLayout());
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        DialogManager.getProfessorValues().forEach((x, y) -> {
            final JLabel label = new JLabel(x);
            cnst.anchor = GridBagConstraints.WEST;
            panelNord.add(label, cnst);
            final JComboBox<String> field = new JComboBox<>();
            y.forEach(z -> {
                field.addItem(z);
            });
            field.setEditable(false);
            super.getBoxList().add(field);
            cnst.anchor = GridBagConstraints.EAST;
            panelNord.add(field, cnst);
            cnst.gridy++;
        });
        return panelNord;
    }

    @Override
    public ActionListener setOkListener() {
        return e -> {
            final String value = super.getBoxList().get(0).getSelectedItem().toString();
            Controller.getController().deleteProfessor(value);
            this.setVisible(false);
        };
    }

}
