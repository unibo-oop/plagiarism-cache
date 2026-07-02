package org.hsm.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import org.hsm.controller.ControllerImpl;
import org.hsm.view.utility.EuroPanel;
import org.hsm.view.utility.EuroPanelImpl;
import org.hsm.view.utility.MyGUIFactory;

/**
 * The dialog for adding a plant in the greenhouse.
 *
 */
public class PlantAddDialog extends AbstractAddDialog {

    private static final int NUM_MAX_PLANT = 100;
    private static final int MAX_COST = 100;
    private static final int INSET = 3;
    private final JComboBox<Object> plantsList;
    private final EuroPanel euroPanel;
    private final JSpinner numberSpinner;

    /**
     * Create the dialog to add new plants.
     * 
     * @param frame
     *            the main frame of the app
     */
    public PlantAddDialog(final JFrame frame) {
        super(frame, "Add a plant", Dialog.ModalityType.APPLICATION_MODAL);
        // combo box
        final Set<String> set = ControllerImpl.getController().getDatabase().getDb().keySet();
        this.plantsList = new JComboBox<>(set.toArray());
        plantsList.setSelectedIndex(0);
        final JPanel northPanel = new JPanel(new FlowLayout());
        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(INSET, INSET, INSET, INSET);
        gbc.anchor = GridBagConstraints.LINE_START;
        // labels
        final JLabel typeLabel = new JLabel("Type :");
        centerPanel.add(typeLabel, gbc);
        ++gbc.gridy;
        final JLabel numLabel = new JLabel("Number :");
        centerPanel.add(numLabel, gbc);
        ++gbc.gridy;
        final JLabel costLabel = new JLabel("Cost (€) :");
        centerPanel.add(costLabel, gbc);
        gbc.gridy = 0;
        ++gbc.gridx;
        gbc.anchor = GridBagConstraints.LINE_END;
        centerPanel.add(this.plantsList, gbc);
        ++gbc.gridy;
        // spinner
        final SpinnerModel model = new SpinnerNumberModel(1, 1, NUM_MAX_PLANT, 1);
        this.numberSpinner = new JSpinner(model);
        centerPanel.add(this.numberSpinner, gbc);
        ++gbc.gridy;
        this.euroPanel = new EuroPanelImpl(MAX_COST);
        centerPanel.add(this.euroPanel.getComponent(), gbc);
        final JLabel label = new MyGUIFactory().createLabel("Choose the plant");
        northPanel.add(label);
        this.getJDialog().add(northPanel, BorderLayout.NORTH);
        this.getJDialog().add(centerPanel);
    }

    @Override
    protected void addAction() {
        final String choice = (String) this.plantsList.getSelectedItem();
        ControllerImpl.getController().addPlants(ControllerImpl.getController().getDatabase().getDb().get(choice),
                this.euroPanel.getValue(), ((SpinnerNumberModel) this.numberSpinner.getModel()).getNumber().intValue());
        getJDialog().dispose();
    }

}
