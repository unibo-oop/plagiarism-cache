package org.hsm.view.gui;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.net.URI;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.hsm.controller.ControllerImpl;
import org.hsm.view.dialog.GreenhouseCreateDialog;
import org.hsm.view.dialog.InfoDialog;
import org.hsm.view.dialog.PlantAddDialog;
import org.hsm.view.dialog.PlantCreateDialog;
import org.hsm.view.utility.Utilities;

/**
 * The MenuBar component for the main frame.
 *
 */
public class MenuBar implements GUIComponent {

    private static final String ERROR_MSG = "An error has occured!";
    private final JMenuBar bar;
    private final JMenu edit;
    private final JMenu chart;
    private final JMenuItem saveGreenhouse;
    private final JMenuItem removeGreenhouse;
    private final JMenuItem newDatabase;
    private final JMenuItem importDatabase;
    private final JMenuItem exportDatabase;

    /**
     * Create the MenuBar.
     * 
     * @param frame
     *            the MainFrame for the MenuBar
     */
    public MenuBar(final JFrame frame) {
        this.bar = new JMenuBar();
        // Menus
        final JMenu file = new JMenu("File");
        this.edit = new JMenu("Edit");
        this.chart = new JMenu("Charts");
        final JMenu information = new JMenu("Information");
        final JMenu tools = new JMenu("Tools");
        final JMenu help = new JMenu("Help");
        file.setMnemonic(KeyEvent.VK_F);
        this.edit.setMnemonic(KeyEvent.VK_E);
        this.chart.setMnemonic(KeyEvent.VK_C);
        information.setMnemonic(KeyEvent.VK_I);
        tools.setMnemonic(KeyEvent.VK_T);
        help.setMnemonic(KeyEvent.VK_H);
        // Menù File Item
        final JMenuItem newGreenhouse = new JMenuItem("New Greenhouse");
        newGreenhouse.addActionListener(e -> new GreenhouseCreateDialog(frame).start());
        file.add(newGreenhouse);
        final JMenuItem loadGreenhouse = new JMenuItem("Open Greenhouse");
        loadGreenhouse.addActionListener(e -> ControllerImpl.getController().loadGreenhouse());
        file.add(loadGreenhouse);
        this.saveGreenhouse = new JMenuItem("Save Greenhouse");
        this.saveGreenhouse.addActionListener(e -> ControllerImpl.getController().saveGreenhouse());
        file.add(this.saveGreenhouse);
        this.removeGreenhouse = new JMenuItem("Remove Greenhouse");
        this.removeGreenhouse.addActionListener(e -> ControllerImpl.getController().deleteGreenhouse());
        file.add(this.removeGreenhouse);
        file.addSeparator();
        this.newDatabase = new JMenuItem("New Database");
        this.newDatabase.addActionListener(e -> ControllerImpl.getController().newDatabase());
        file.add(this.newDatabase);
        this.importDatabase = new JMenuItem("Import Database");
        this.importDatabase.addActionListener(e -> ControllerImpl.getController().loadDatabase());
        file.add(this.importDatabase);
        this.exportDatabase = new JMenuItem("Export Database");
        this.exportDatabase.addActionListener(e -> ControllerImpl.getController().saveDatabase());
        file.add(this.exportDatabase);
        final JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> ControllerImpl.getController().exit());
        file.addSeparator();
        file.add(exit);
        // Menu Edit Item
        final JMenuItem addPlant = new JMenuItem("Add Plant");
        addPlant.addActionListener(e -> {
            if (ControllerImpl.getController().isDbEmpty()) {
                Utilities.errorMessage(frame, "The Database is empty or not loaded");
            } else {
                new PlantAddDialog(frame).start();
            }
        });
        this.edit.add(addPlant);
        final JMenuItem removePlant = new JMenuItem("Remove plant from Greenhouse");
        removePlant.addActionListener(e -> ControllerImpl.getController().delPlant());
        this.edit.add(removePlant);
        this.edit.addSeparator();
        final JMenuItem addModel = new JMenuItem("Create Plant");
        addModel.addActionListener(e -> new PlantCreateDialog(frame).start());
        this.edit.add(addModel);
        final JMenuItem removeDBPlant = new JMenuItem("Remove plant from Database");
        removeDBPlant.addActionListener(e -> ControllerImpl.getController().deleteDbPlant());
        this.edit.add(removeDBPlant);
        // Menu Chart Item
        final JMenuItem brightnessBarChart = new JMenuItem("Show Brightness Bar Chart");
        this.chart.add(brightnessBarChart);
        brightnessBarChart.addActionListener(e -> ControllerImpl.getController().showBrightnessBarChart());
        final JMenuItem phBarChart = new JMenuItem("Show Basicity Bar Chart");
        this.chart.add(phBarChart);
        phBarChart.addActionListener(e -> ControllerImpl.getController().showPhBarChart());
        final JMenuItem temperatureBarChart = new JMenuItem("Show Temperature Bar Chart");
        this.chart.add(temperatureBarChart);
        temperatureBarChart.addActionListener(e -> ControllerImpl.getController().showTemperatureBarChart());
        final JMenuItem conductivityBarChart = new JMenuItem("Show Conductivity Bar Chart");
        this.chart.add(conductivityBarChart);
        conductivityBarChart.addActionListener(e -> ControllerImpl.getController().showConductivityBarChart());
        this.chart.addSeparator();
        final JMenuItem brightnessLineChart = new JMenuItem("Show Brightness Line Chart");
        this.chart.add(brightnessLineChart);
        brightnessLineChart.addActionListener(e -> ControllerImpl.getController().showBrightnessLineChart());
        final JMenuItem phLineChart = new JMenuItem("Show Basicity Line Chart");
        phLineChart.addActionListener(e -> ControllerImpl.getController().showPhLineChart());
        this.chart.add(phLineChart);
        final JMenuItem temperatureLineChart = new JMenuItem("Show Temperature Line Chart");
        temperatureLineChart.addActionListener(e -> ControllerImpl.getController().showTemperatureLineChart());
        this.chart.add(temperatureLineChart);
        final JMenuItem conductivityLineChart = new JMenuItem("Show Conductivity Line Chart");
        conductivityLineChart.addActionListener(e -> ControllerImpl.getController().showConductivityLineChart());
        this.chart.add(conductivityLineChart);
        // Menu Information Item
        final JMenuItem whatsHydroponic = new JMenuItem("What is Hydroponic? (Wiki)");
        information.add(whatsHydroponic);
        whatsHydroponic.addActionListener(e -> {
            if (Desktop.isDesktopSupported()) {
                final Desktop desktop = Desktop.getDesktop();
                try {
                    final URI uri = new URI("https://en.wikipedia.org/wiki/Hydroponics");
                    desktop.browse(uri);
                } catch (final Exception ex) {
                    Utilities.errorMessage(frame, ERROR_MSG);
                }
            }
        });
        final JMenuItem growLight = new JMenuItem("Grow Light (Wiki)");
        information.add(growLight);
        growLight.addActionListener(e -> {
            if (Desktop.isDesktopSupported()) {
                final Desktop desktop = Desktop.getDesktop();
                try {
                    final URI uri = new URI("https://en.wikipedia.org/wiki/Grow_light");
                    desktop.browse(uri);
                } catch (final Exception ex) {
                    Utilities.errorMessage(frame, ERROR_MSG);
                }
            }
        });
        final JMenuItem buildhydro = new JMenuItem("Build your Hydroponic System");
        information.add(buildhydro);
        buildhydro.addActionListener(e -> {
            if (Desktop.isDesktopSupported()) {
                final Desktop desktop = Desktop.getDesktop();
                try {
                    final URI uri = new URI("http://homehydrosystems.com/index.html");
                    desktop.browse(uri);
                } catch (final Exception ex) {
                    Utilities.errorMessage(frame, ERROR_MSG);
                }
            }
        });
        final JMenuItem plantingGuide = new JMenuItem("Planting Guide");
        information.add(plantingGuide);
        plantingGuide.addActionListener(e -> {
            if (Desktop.isDesktopSupported()) {
                final Desktop desktop = Desktop.getDesktop();
                try {
                    final URI uri = new URI("http://www.ufseeds.com/Garden-Planting-Guide.html");
                    desktop.browse(uri);
                } catch (final Exception ex) {
                    Utilities.errorMessage(frame, ERROR_MSG);
                }
            }
        });
        // Menu Tools Item
        final JMenuItem nutrientSolutionCalc = new JMenuItem("Nutrient Solution Calculator");
        tools.add(nutrientSolutionCalc);
        nutrientSolutionCalc.addActionListener(e -> {
            if (Desktop.isDesktopSupported()) {
                final Desktop desktop = Desktop.getDesktop();
                try {
                    final URI uri = new URI("http://hydroponiacs.com/hydroponic-calculator/hydrocal.html");
                    desktop.browse(uri);
                } catch (final Exception ex) {
                    Utilities.errorMessage(frame, ERROR_MSG);
                }
            }
        });
        // Menu Help Item
        final JMenuItem loadTest = new JMenuItem("Load Example");
        final JMenuItem about = new JMenuItem("About Hydroponic System Manager");
        about.addActionListener(e -> new InfoDialog(frame).start());
        loadTest.addActionListener(e -> ControllerImpl.getController().applicationTest());
        help.add(loadTest);
        help.addSeparator();
        help.add(about);
        // Add menus in MenuBar
        this.bar.add(file);
        this.bar.add(edit);
        this.bar.add(chart);
        this.bar.add(information);
        this.bar.add(tools);
        this.bar.add(help);
    }

    /**
     * Set the state of the Edit commands in the Menù.
     * 
     * @param state
     *            the state of the commands
     */
    public void setEditCommands(final boolean state) {
        this.edit.setEnabled(state);
        this.exportDatabase.setEnabled(state);
        this.importDatabase.setEnabled(state);
        this.saveGreenhouse.setEnabled(state);
        this.newDatabase.setEnabled(state);
        this.removeGreenhouse.setEnabled(state);
        this.chart.setEnabled(state);
    }

    @Override
    public JComponent getComponent() {
        return this.bar;
    }

}
