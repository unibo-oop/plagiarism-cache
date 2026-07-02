package view.menu;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser; 
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import controller.Controller;
import model.Court;
import model.Year;
import view.IView;

/**
 * 
 * Class which represents the main menu, used for the saving and loading of files, the exporting as excel files.
 * It's also used to enter in edit mode and change the table's types of view.
 *
 */

public class BaseMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = 6771132861200476380L;
    private final JFileChooser fileChooser = new JFileChooser();
    private JMenu refreshSearch;
    private static final String TOTAL = "Total";
    
    /**
     * Constructor of the menu.
     * @param frame The main frame of the program.
     */

    public BaseMenu(final IView frame) {
        super("Menu");
        JMenuItem menuItem = new JMenuItem("Open");
        menuItem.addActionListener(e -> {
            final int retVal = this.fileChooser.showOpenDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                Controller.getController().loadData(this.fileChooser.getSelectedFile());
            }
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Save");
        menuItem.addActionListener(e -> {
            final int retVal = this.fileChooser.showSaveDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                Controller.getController().saveData(this.fileChooser.getSelectedFile());
            }
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Edit");
        menuItem.addActionListener(e -> {
            frame.editMode(true);
        });
        this.add(menuItem);
        
        menuItem = new JMenuItem("Export as Excel file");
        menuItem.addActionListener(e -> {
            frame.exportData();
        });
        this.add(menuItem);
        
        final JMenu subMenu = new JMenu("Search");
        this.refreshSearch = subMenu;
        menuItem = new JMenuItem(TOTAL);
        menuItem.addActionListener(e -> {
            Controller.getController().searchBy(TOTAL);
        });
        subMenu.add(menuItem);
        this.getSearchValues().forEach((x, y) -> {
            final JMenu subSubMenu = new JMenu(x);
            y.forEach(z -> {
                final JMenuItem menuItem2 = new JMenuItem(z);
                menuItem2.addActionListener(e -> {
                    Controller.getController().searchBy(z);
                });
                subSubMenu.add(menuItem2);
            });
            subMenu.add(subSubMenu);
        });
        this.add(subMenu);
    }
    
    /**
     * Method which update the table's types and elements of view.
     */
    
    public void refreshSearchList() {
        this.remove(refreshSearch);
        this.refreshSearch = new JMenu("Search");
        final JMenuItem menuItem = new JMenuItem(TOTAL);
        menuItem.addActionListener(e -> {
            Controller.getController().searchBy(TOTAL);
        });
        refreshSearch.add(menuItem);
        this.getSearchValues().forEach((x, y) -> {
            final JMenu subSubMenu = new JMenu(x);
            y.forEach(z -> {
                final JMenuItem menuItem2 = new JMenuItem(z);
                menuItem2.addActionListener(e -> {
                    Controller.getController().searchBy(z);
                });
                subSubMenu.add(menuItem2);
            });
            refreshSearch.add(subSubMenu);
        });
        this.add(refreshSearch);
    }
    
    /**
     * Method which provides to the menu the search types and elements to create the list and to update them dynamically.
     * @return The map containing the string of the search types and his list of the elements.
     */
    
    public Map<String, List<String>> getSearchValues() {
        final Map<String, List<String>> returnValue = new HashMap<>();
        final List<String> courts = new ArrayList<>();
        final List<String> years = new ArrayList<>();
        for (int i = 0; i < Court.values().length; i++) {
            courts.add(Court.values()[i].getDef());
        }
        for (int i = 0; i < Year.values().length; i++) {
            years.add(Year.values()[i].getYear());
        }
        returnValue.put("By Year", years);
        returnValue.put("By Court", courts);
        returnValue.put("By Prof.", Controller.getController().getActiveProfessors());
        returnValue.put("By Teaching", Controller.getController().getActiveTeachings());
        returnValue.put("By Classroom", Controller.getController().getClassrooms());
        return returnValue;
    }

    
}
