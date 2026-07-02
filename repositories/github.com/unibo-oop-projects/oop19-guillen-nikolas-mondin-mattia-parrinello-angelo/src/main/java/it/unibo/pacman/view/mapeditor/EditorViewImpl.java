package it.unibo.pacman.view.mapeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.pacman.controller.mapeditor.MapEditorController;
import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.view.GUIFactory;
import it.unibo.pacman.view.ViewableUI;
import it.unibo.pacman.view.utilities.PacFont;

/**
 * Implements the editor view.
 */
public class EditorViewImpl implements EditorView {

    private static final String EDITOR_TITLE = "MAP EDITOR";
    private static final double HELP_SCALE = 0.6;
    private static final String HELP = "- Add walls and powerpills to the map.\n"
            + "- Remove them with the black tile of the palette or with right clicking on the mouse\n"
            + "- Empty spaces will be filled with normal pills.\n"
            + "- When you are done, save the map by clicking SAVE and give it a name!\n\n\n"
            + "ALERT: Make all the pills reachable by pacman!\n"
            + "SUGGESTION: Make all the paths large one tile\n\n\n";


    private final JFrame frame;

    private final GUIFactory gFactory;
    private final ViewableUI mainMenu;
    private final MapEditorController controller;
    private final GridView grid;

    private final Set<SelectionTile> paletteTiles;

    /**
     * Create an editor view.
     * 
     * @param mainMenu of the game
     * @param gFactory is the GUI factory
     * @param gridView is the grid of the editor
     * @param controller is the controller of the editor
     */
    public EditorViewImpl(final ViewableUI mainMenu, final GUIFactory gFactory, final GridView gridView, final MapEditorController controller) {
        this.mainMenu = mainMenu;
        this.gFactory = gFactory;
        this.controller = controller;
        this.grid = gridView;
        this.grid.setPreferredSize(new Dimension(controller.getWidth() * Tile.getSize(), controller.getHeight() * Tile.getSize()));
        this.grid.setBackground(Color.BLACK);
        this.paletteTiles = new HashSet<>();
        this.frame = gFactory.createFrame();
        frame.setTitle(EDITOR_TITLE);
        this.load();
        frame.pack();
    }

    private void load() {
        final JPanel mainPanel = this.gFactory.createJPanel(new BorderLayout(), Color.BLACK);
        this.loadPalette(mainPanel);
        this.loadButtons(mainPanel);
        mainPanel.add(this.grid, BorderLayout.CENTER);
        this.frame.add(mainPanel);
    }

    private void loadPalette(final JPanel mainPanel) {
        final JPanel northPalette = this.gFactory.createJPanel(new FlowLayout(), Color.BLACK);
        this.addSelectionTile(northPalette, new SelectionTile(controller, new Tile(EntityType.EMPTY)));
        this.addSelectionTile(northPalette, new SelectionTile(controller, new Tile(EntityType.WALL)));
        this.addSelectionTile(northPalette, new SelectionTile(controller, new Tile(EntityType.POWERPILL)));
        mainPanel.add(northPalette, BorderLayout.NORTH);
    }

    private void addSelectionTile(final JPanel northPalette, final SelectionTile selectionTile) {
        northPalette.add(selectionTile);
        this.paletteTiles.add(selectionTile);
    }

    private void loadButtons(final JPanel mainPanel) {
        final JPanel southButtons = this.gFactory.createJPanel(new FlowLayout(), Color.BLACK);
        final JButton jbSave = this.gFactory.createMenuButton("SAVE", e -> {
            this.chooseMapName();
        });
        final JButton jbHelp = this.gFactory.createMenuButton("HELP", e -> {
            this.showHelpDialog();
        });
        final JButton jbClose = this.gFactory.createMenuButton("MENU", e -> {
            this.close();
            this.mainMenu.show();
        });
        southButtons.add(jbClose);
        southButtons.add(jbHelp);
        southButtons.add(jbSave);
        mainPanel.add(southButtons, BorderLayout.SOUTH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<SelectionTile> getPalette() {
        return Collections.unmodifiableSet(this.paletteTiles);
    }

    private void chooseMapName() {
        final JFrame jf = this.gFactory.createFrame();
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final JPanel jp = this.gFactory.createJPanel(new FlowLayout(), Color.BLACK);
        final int maxChars = 10;
        final JTextField mapName = this.gFactory.createTextField("Map name", Color.BLACK, Color.YELLOW);
        final JButton ok = this.gFactory.createMenuButton("OK", e1 -> {
            if (mapName.getText().isEmpty() || mapName.getText().length() > maxChars) {
                JOptionPane.showMessageDialog(this.frame, "INSERT A VALID NAME (max " + maxChars + " characters)");
            } else {
                this.controller.saveMap(mapName.getText());
                this.close();
                jf.setVisible(false);
            }
        });
        jp.add(mapName);
        jp.add(ok);
        jf.add(jp);
        jf.pack();
        jf.setVisible(true);
    }

    private void showHelpDialog() {
        final JFrame jf = this.gFactory.createFrame();
        final JTextArea help = this.gFactory.createTextArea(Color.BLACK, Color.YELLOW, false);
        help.setFont(new PacFont(HELP_SCALE).getFont());
        help.setText(HELP);
        final JButton ok = this.gFactory.createMenuButton("OK", e1 -> {
            jf.setVisible(false);
        });
        jf.getContentPane().add(help, BorderLayout.CENTER);
        jf.getContentPane().add(ok, BorderLayout.SOUTH);
        jf.pack();
        jf.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        this.mainMenu.close();
        this.frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        this.frame.dispose();
        this.mainMenu.show();
    }

}
