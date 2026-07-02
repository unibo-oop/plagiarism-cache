package org.hsm.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.hsm.controller.ControllerImpl;
import org.hsm.view.utility.EuroPanel;
import org.hsm.view.utility.EuroPanelImpl;
import org.hsm.view.utility.GUIFactory;
import org.hsm.view.utility.MyGUIFactory;
import org.hsm.view.utility.Utilities;

/**
 * This dialog can be used to create a new greenhouse.
 *
 */
public class GreenhouseCreateDialog extends AbstractAddDialog {

    private static final int START_SIZE = 20;
    private static final int MAX_SIZE = 10000;
    private static final int MAX_COST = 5000000;
    private static final int INSET = 5;
    private static final int TXT_DIM = 21;
    private static final int SPINNER_TXT_DIM = 19;
    private final JLabel pictureLabel;
    private final JTextField nameField;
    private final JSpinner spinnerSize;
    private final ButtonGroup group;
    private final EuroPanel euroPanel;

    /**
     * Create the dialog to make a new Greenhouse.
     * 
     * @param frame
     *            the main frame of the app
     */
    public GreenhouseCreateDialog(final JFrame frame) {
        super(frame, "Create new Greenhouse", Dialog.ModalityType.APPLICATION_MODAL);
        final GUIFactory factory = new MyGUIFactory();
        // picture and labels
        this.pictureLabel = new JLabel("", new ImageIcon(this.getClass().getResource("/linear.jpg")), JLabel.CENTER);
        this.nameField = new JTextField(TXT_DIM);
        final JPanel panelUp = new JPanel();
        panelUp.add(factory.createLabel("Insert information about the new Greenhouse"));
        final JLabel name = new JLabel("Greenhouse Name : ");
        final JLabel type = new JLabel("Greenhouse Type : ");
        final JLabel size = new JLabel("Greenhouse Size (m2) : ");
        final JLabel costLabel = new JLabel("Greenhouse Cost (€) :");
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(INSET, INSET, INSET, INSET);
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(name, gbc);
        ++gbc.gridx;
        panel.add(nameField, gbc);
        gbc.gridx = 0;
        ++gbc.gridy;
        panel.add(size, gbc);
        this.spinnerSize = factory.createSpinner(SPINNER_TXT_DIM, new SpinnerNumberModel(START_SIZE, 1, MAX_SIZE, 1));
        ++gbc.gridx;
        panel.add(this.spinnerSize, gbc);
        gbc.gridx = 0;
        ++gbc.gridy;
        this.euroPanel = new EuroPanelImpl(MAX_COST);
        panel.add(costLabel, gbc);
        ++gbc.gridx;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(this.euroPanel.getComponent(), gbc);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(type, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        // radio buttons
        final JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
        this.group = new ButtonGroup();
        for (final String elem : ControllerImpl.getController().getGreenhouseTypes()) {
            final JRadioButton button = new JRadioButton(elem);
            button.setActionCommand(elem);
            if (button.getActionCommand().equals("Linear")) {
                button.setSelected(true);
            }
            button.addActionListener(new AdapterImageHandler());
            this.group.add(button);
            panelButtons.add(button);
        }
        panel.add(panelButtons, gbc);
        ++gbc.gridx;
        panel.add(this.pictureLabel, gbc);
        this.getJDialog().getContentPane().add(panelUp, BorderLayout.NORTH);
        this.getJDialog().getContentPane().add(panel);
    }

    private class AdapterImageHandler implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent e) {
            final JRadioButton button = (JRadioButton) e.getSource();
            pictureLabel.setIcon(Utilities.getStructIcon(button.getActionCommand()));
        }
    }

    @Override
    protected void addAction() {
        final SpinnerNumberModel model = (SpinnerNumberModel) this.spinnerSize.getModel();
        ControllerImpl.getController().createGreenhouse(this.nameField.getText(),
                this.group.getSelection().getActionCommand(), this.euroPanel.getValue(), model.getNumber().intValue());
        getJDialog().dispose();
    }

}
