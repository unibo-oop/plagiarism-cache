package org.hsm.view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.hsm.controller.ControllerImpl;
import org.hsm.view.dialog.OpenFileDialog;
import org.hsm.view.dialog.SaveFileDialog;
import org.hsm.view.tab.PlantsTab;

/**
 * The main frame of the application.
 *
 */
public class MainFrame implements View {

    private static final double PROPORTION = 1.3;
    private final JFrame frame;
    private final Tabs tab;
    private final MenuBar menubar;
    private final ToolBar toolbar;

    /**
     * Create the main frame.
     */
    public MainFrame() {
        this.setSystemLook();
        this.tab = new Tabs(this.frame);
        this.tab.setVisible(false);
        this.frame = new JFrame("Hydroponic System Manager");
        this.frame.setIconImage(new ImageIcon(this.getClass().getResource("/leaf.png")).getImage());
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                ControllerImpl.getController().exit();
            }
        });
        this.toolbar = new ToolBar(this.frame);
        this.toolbar.setEditCommands(false);
        this.menubar = new MenuBar(this.frame);
        this.menubar.setEditCommands(false);
        this.frame.setJMenuBar((JMenuBar) this.menubar.getComponent());
        this.frame.getContentPane().add(this.tab.getComponent());
        this.frame.getContentPane().add(toolbar.getComponent(), BorderLayout.PAGE_START);
    }

    @Override
    public void start() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) (screen.getWidth() / PROPORTION);
        final int sh = (int) (screen.getHeight() / PROPORTION);
        this.frame.setSize(sw, sh);
        this.frame.setLocationByPlatform(true);
        this.frame.setVisible(true);
    }

    @Override
    public JFrame getFrame() {
        return this.frame;
    }

    @Override
    public void setActive(final boolean status) {
        this.tab.setVisible(status);
        this.toolbar.setEditCommands(status);
        this.menubar.setEditCommands(status);
    }

    @Override
    public void insertGreenhouse(final String name, final double size, final double cost, final String typology,
            final double freeSpace, final double occupiedSpace, final int numberOfPlants, final double overCost) {
        this.tab.getGreenhouseTab().setGreenhouse(name, size, cost, typology, freeSpace, occupiedSpace, numberOfPlants,
                overCost);
        this.tab.getGreenhouseChartTab().clean();
        this.tab.getPlantsTab().clean();
        ((PlantsTab) this.tab.getPlantsTab()).setRowSelectionAllowed(true);
    }

    @Override
    public void insertModelPlant(final String name, final String botanicalName, final double ph,
            final double brightness, final int optimalGrowthTime, final int life, final double size,
            final double conductivity, final double temperature) {
        this.tab.getDatabaseTab().insertRow(name, botanicalName, ph, brightness, optimalGrowthTime, life, size,
                conductivity, temperature);
    }

    @Override
    public void removeSelectedModelPlant() {
        this.tab.getDatabaseTab().removeSelectedRow();
    }

    @Override
    public void insertNewPlant(final int id, final String name, final double cost, final double ph,
            final double brightness, final double conductivity, final double temperature) {
        this.tab.getPlantsTab().insertRow(id, name, cost, ph, brightness, conductivity, temperature);
    }

    @Override
    public void insertPlant(final int id, final String name, final double cost, final double ph,
            final double brightness, final double conductivity, final double temperature) {
        this.tab.getPlantsTab().updateRow(id, name, cost, ph, brightness, conductivity, temperature);
    }

    @Override
    public void removeSelectedPlant() {
        this.tab.getPlantsTab().removeSelectedRow();
    }

    @Override
    public Optional<String> saveGreenhouseDialog() {
        return new SaveFileDialog(this.frame, "HSM Document", "hsm").getPath();
    }

    @Override
    public Optional<String> loadGreenhouseDialog() {
        return new OpenFileDialog(this.frame, "HSM Document", "hsm").getPath();
    }

    @Override
    public Optional<String> exportDatabaseDialog() {
        return new SaveFileDialog(this.frame, "DHSM Document", "dhsm").getPath();
    }

    @Override
    public Optional<String> importDatabaseDialog() {
        return new OpenFileDialog(this.frame, "DHSM Document", "dhsm").getPath();
    }

    @Override
    public void cleanGreenhousePlants() {
        this.tab.getPlantsTab().clean();
    }

    @Override
    public void cleanDatabase() {
        this.tab.getDatabaseTab().clean();
    }

    @Override
    public String getSelectedBotanicalName() throws IllegalStateException {
        return this.tab.getDatabaseTab().getSelectedRowIdentifier();
    }

    @Override
    public int getSelectedIDPlant() throws IllegalStateException {
        return this.tab.getPlantsTab().getSelectedRowIdentifier();
    }

    private void setSystemLook() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            System.out.println("errore visualizzazione gui");
        }
    }

}
