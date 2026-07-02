package org.hsm.view.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import org.hsm.controller.ControllerImpl;
import org.hsm.view.dialog.GreenhouseCreateDialog;
import org.hsm.view.utility.GUIFactory;
import org.hsm.view.utility.MyGUIFactory;

/**
 * The class which create the toolbar for the main frame.
 *
 */
public class ToolBar implements GUIComponent {

    private final JToolBar bar;
    private final JButton removeGreenhouseButton;
    private final JButton importDatabaseButton;
    private final JButton exportDatabaseButton;
    private final JButton newDatabaseButton;
    private final JButton saveGreenhouseButton;

    /**
     * Create a toolbar.
     * 
     * @param frame
     *            the main frame of the app
     */
    public ToolBar(final JFrame frame) {
        final GUIFactory factory = new MyGUIFactory();
        this.bar = new JToolBar("Toolbar");
        final JButton createGreenhouseButton = factory.createButton("Create Greenhouse",
                new ImageIcon(getClass().getResource("/new.png")));
        this.removeGreenhouseButton = factory.createButton("Remove Greenhouse",
                new ImageIcon(getClass().getResource("/delete.png")));
        final JButton openGreenhouseButton = factory.createButton("Open Greenhouse",
                new ImageIcon(getClass().getResource("/open.png")));
        this.saveGreenhouseButton = factory.createButton("Save Greenhouse",
                new ImageIcon(getClass().getResource("/save.png")));
        this.importDatabaseButton = factory.createButton("Import Database",
                new ImageIcon(getClass().getResource("/import.png")));
        this.exportDatabaseButton = factory.createButton("Export Database",
                new ImageIcon(getClass().getResource("/export.png")));
        this.newDatabaseButton = factory.createButton("New Database",
                new ImageIcon(getClass().getResource("/newDatabase.png")));
        createGreenhouseButton.addActionListener(e -> new GreenhouseCreateDialog(frame).start());
        this.saveGreenhouseButton.addActionListener(e -> ControllerImpl.getController().saveGreenhouse());
        openGreenhouseButton.addActionListener(e -> ControllerImpl.getController().loadGreenhouse());
        this.importDatabaseButton.addActionListener(e -> ControllerImpl.getController().loadDatabase());
        this.exportDatabaseButton.addActionListener(e -> ControllerImpl.getController().saveDatabase());
        this.removeGreenhouseButton.addActionListener(e -> ControllerImpl.getController().deleteGreenhouse());
        this.newDatabaseButton.addActionListener(e -> ControllerImpl.getController().newDatabase());
        this.bar.add(createGreenhouseButton);
        this.bar.add(openGreenhouseButton);
        this.bar.add(this.saveGreenhouseButton);
        this.bar.add(this.removeGreenhouseButton);
        this.bar.addSeparator();
        this.bar.add(this.newDatabaseButton);
        this.bar.add(this.importDatabaseButton);
        this.bar.add(this.exportDatabaseButton);
    }

    /**
     * Set the state of edit commands in the Toolbar.
     * 
     * @param state
     *            the state of the commands
     */
    public void setEditCommands(final boolean state) {
        this.exportDatabaseButton.setEnabled(state);
        this.importDatabaseButton.setEnabled(state);
        this.newDatabaseButton.setEnabled(state);
        this.removeGreenhouseButton.setEnabled(state);
        this.saveGreenhouseButton.setEnabled(state);
    }

    @Override
    public JComponent getComponent() {
        return this.bar;
    }

}
