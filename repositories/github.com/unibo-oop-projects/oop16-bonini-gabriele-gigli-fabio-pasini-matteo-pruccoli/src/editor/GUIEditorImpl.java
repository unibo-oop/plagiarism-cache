package editor;

import static editor.model.ModelEditorImpl.getModelEditor;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import editor.guieditor.GUIFactoryEditor;
import editor.guieditor.GUIFactoryEditorImpl;
import editor.listener.ActionListenerMenuItem;
import editor.listener.MouseListenerCanvas;
import editor.listener.MouseListenerFrame;
import editor.listener.WindowsListenerFrame;
import editor.model.ModelEditorImpl.CMB;
import editor.model.ModelEditorImpl.Tool;
import maingame.game.Game;
import maingame.level.LevelImpl;
import maingame.level.tile.TileImpl;
/**
 * Classe GUIEditor, crea editor di livelli.
 *
 */
public class GUIEditorImpl implements GUIEditor {

    private static GUIEditor guiEditor = new GUIEditorImpl();

    /**
     * Action command e stringa di visualizzazione del menu new.
     */
    public static final String MENU_COMMAND_NEW = "New";
    /**
     * Action command e stringa di visualizzazione del menu load.
     */
    public static final String MENU_COMMAND_LOAD = "Load";
    /**
     * Action command e stringa di visualizzazione del menu save.
     */
    public static final String MENU_COMMAND_SAVE = "Save";
    /**
     * Action command e stringa di visualizzazione del menu undo.
     */
    public static final String MENU_COMMAND_UNDO = "Undo";
    /**
     * Action command e stringa di visualizzazione del menu redo.
     */
    public static final String MENU_COMMAND_REDO = "Redo";
    /**
     * Action command e stringa di visualizzazione del menu select all.
     */
    public static final String MENU_COMMAND_SELECT_ALL = "Select all";
    /**
     * Action command e stringa di visualizzazione del menu clear.
     */
    public static final String RDB_COMMAND_CLEAR = "Clear";
    /**
     * Action command e stringa di visualizzazione del menu draw.
     */
    public static final String RDB_COMMAND_DRAW = "Draw";

    private Canvas mappa;
    private JPanel main;

    private JMenuItem menuItemNew;
    private JMenuItem menuItemLoad;
    private JMenuItem menuItemSave;
    private JMenuItem menuItemUndo;
    private JMenuItem menuItemRedo;
    private JMenuItem menuItemSelectAll;
    private JRadioButtonMenuItem draw;
    private JRadioButtonMenuItem clear;

    private JSlider scrollVelocity;
    private JSlider lum;
    private JComboBox<String> cmbTile;
    private JComboBox<String> cmbMob;
    private JComboBox<String> cmbObject;
    private JCheckBox chkBox;
    private GUIFactoryEditor factory;

    private JFrame frame;
    private final ActionListener actionListener = new ActionListenerMenuItem();

    /**
     * @return Ritorna un oggetto statico di tipo GUIEditor.
     */
    public static GUIEditor getGUIEditor() {
        return guiEditor;
    }

    @Override
    public void open() {
        frame = new JFrame();
        factory = new GUIFactoryEditorImpl();
        mappa = new Canvas();
        main = new JPanel();
        frame.setResizable(false);
        frame.setVisible(true);
        this.addComponentsToFrame();
        getModelEditor().setUsingTool(Tool.DRAW);
        frame.add(main);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getModelEditor().setLumos(lum.getValue());
        getModelEditor().setVelocityScroll(scrollVelocity.getValue());
        this.setMyListener();
    }

    @Override
    public void setSelectedCMB(final CMB cmb) {
        getModelEditor().setDeselectCMB(false);
        switch (cmb) {
        case TILE:
            this.setCmbIndex(CMB.ITEM, -1);
            this.setCmbIndex(CMB.MOB, -1);
            break;
        case MOB:
            this.setCmbIndex(CMB.TILE, -1);
            this.setCmbIndex(CMB.ITEM, -1);
            break;
        case ITEM:
            this.setCmbIndex(CMB.TILE, -1);
            this.setCmbIndex(CMB.MOB, -1);
            break;
        default:
            break;
        }
        getModelEditor().setDeselectCMB(true);

    }

    @Override
    public void setCmbIndex(final CMB cmb, final int index) {
        switch (cmb) {
        case MOB:
            this.cmbMob.setSelectedIndex(index);
            break;
        case ITEM:
            this.cmbObject.setSelectedIndex(index);
            break;
        case TILE:
            this.cmbTile.setSelectedIndex(index);
            break;
        default:
            break;
        }
    }

    @Override
    public void deselect() {
        enableCmb(CMB.MOB, true);
        enableCmb(CMB.ITEM, true);
        enableCmb(CMB.TILE, true);
        getModelEditor().setAllSelected(false);
    }

    @Override
    public void enableRdbTool(final Tool tool, final boolean enable) {
        switch (tool) {
        case DRAW:
            this.draw.setEnabled(enable);
            break;
        case CLEAR:
            this.clear.setEnabled(enable);
            break;
        default:
            break;
        }
    }

    @Override
    public void selectRDBTool(final Tool tool, final boolean enable) {
        switch (tool) {
        case DRAW:
            this.draw.setSelected(enable);
            getModelEditor().setUsingTool(Tool.DRAW);
            break;
        case CLEAR:
            this.clear.setSelected(enable);
            getModelEditor().setUsingTool(Tool.CLEAR);
            break;
        default:
            break;
        }
    }

    @Override
    public void enableMenuItemNew(final boolean enable) {
        menuItemNew.setEnabled(enable);
    }

    @Override
    public void enablemenuItemSave(final boolean enable) {
        menuItemSave.setEnabled(enable);

    }

    @Override
    public void enableMenuItemLoad(final boolean enable) {
        menuItemLoad.setEnabled(enable);

    }

    @Override
    public void enableMenuItemUndo(final boolean enable) {
        menuItemUndo.setEnabled(enable);

    }

    @Override
    public void enableMenuItemRedo(final boolean enable) {
        menuItemRedo.setEnabled(enable);

    }

    @Override
    public void enableMenuItemSelectAll(final boolean enable) {
        menuItemSelectAll.setEnabled(enable);
    }

    @Override
    public void enableCmb(final CMB cmb, final boolean enable) {
        switch (cmb) {
        case ITEM:
            cmbObject.setEnabled(enable);
            break;
        case TILE:
            cmbTile.setEnabled(enable);
            break;
        case MOB:
            cmbMob.setEnabled(enable);
        default:
            break;
        }
    }

    @Override
    public void addPlayer() {
        final ArrayList<String> options = new ArrayList<>();
        for (int i = 0; i < cmbMob.getItemCount(); i++) {
            options.add(cmbMob.getItemAt(i));
        }
        cmbMob.removeAllItems();
        cmbMob.addItem("Player");
        for (final String s : options) {
            cmbMob.addItem(s);
        }
        getModelEditor().setUsingTool(Tool.CLEAR);
        setCmbIndex(CMB.MOB, -1);
    }

    @Override
    public void removePlayer() {
        cmbMob.removeItemAt(0);
        setCmbIndex(CMB.MOB, -1);
        setCmbIndex(CMB.TILE, 0);
    }

    @Override
    public int getCmbIndex(final CMB cmb) {
        switch (cmb) {
        case MOB:
            return this.cmbMob.getSelectedIndex();
        case ITEM:
            return this.cmbObject.getSelectedIndex();
        case TILE:
            return this.cmbTile.getSelectedIndex();
        default:
            return -1;
        }
    }

    @Override
    public void disposeFrame() {
        frame.dispose();
    }

    @Override
    public Canvas getCanvas() {
        return this.mappa;
    }

    @Override
    public void setDraw() {
        if (guiEditor.getCmbIndex(CMB.ITEM) == -1 && guiEditor.getCmbIndex(CMB.TILE) == -1
            && guiEditor.getCmbIndex(CMB.MOB) == -1) {
            getModelEditor().setUsingTool(Tool.CLEAR);
        } else {
            getModelEditor().setUsingTool(Tool.DRAW);
            guiEditor.enableRdbTool(Tool.DRAW, true);
            guiEditor.selectRDBTool(Tool.DRAW, true);
        }
    }

    private JMenuBar createJMenuBar() {
        return factory.createJMenuBar(setFile(), setEdit(), setTool());
    }

    private JPanel createOption() {
        final int nRows = 6;
        final int nCol = 1;
        final int minSliderLum = 0;
        final int maxSliderLum = 21;
        final int minSliderScroll = 2;
        final int maxSliderScroll = 10;
        final int defaultScrollValue = 5;
        final JPanel option = new JPanel(new GridLayout(nRows, nCol));
        lum = factory.createJSlider(minSliderLum, maxSliderLum, maxSliderLum);
        scrollVelocity = factory.createJSlider(minSliderScroll, maxSliderScroll, defaultScrollValue);

        option.add(factory.createPanel(new GridLayout(2, 1), new JLabel("Luminosità"), lum));

        option.add(factory.createPanel(new GridLayout(2, 1), new JLabel("Velocità scorrimento"), scrollVelocity));
        lum.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent e) {
                final JSlider lum = (JSlider) e.getSource();
                getModelEditor().setLumos(lum.getValue());
            }
        });
        scrollVelocity.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent e) {
                final JSlider vel = (JSlider) e.getSource();
                getModelEditor().setVelocityScroll(vel.getValue());
            }
        });

        chkBox = new JCheckBox("Grid", false);
        option.add(chkBox);
        final List<String> name = new ArrayList<>();
        TileImpl.getTiles().forEach(t -> name.add(t.getName()));
        cmbTile = factory.createComboBox(name);
        option.add(factory.createPanel(new FlowLayout(), new JLabel("Tiles:"), cmbTile));
        cmbTile.setEnabled(false);
        name.clear();
        LevelImpl.MOBS.forEach(t -> name.add(t.getName()));
        cmbMob = factory.createComboBox(name);
        option.add(factory.createPanel(new FlowLayout(), new JLabel("Mobs:"), cmbMob));
        cmbMob.setEnabled(false);
        name.clear();
        LevelImpl.ITEMS.forEach(t -> name.add(t.getName()));
        cmbObject = factory.createComboBox(name);
        option.add(factory.createPanel(new FlowLayout(), new JLabel("Items:"), cmbObject));
        cmbObject.setEnabled(false);
        return option;
    }

    private JMenu setFile() {
        menuItemNew = new JMenuItem(MENU_COMMAND_NEW);
        menuItemLoad = new JMenuItem(MENU_COMMAND_LOAD);
        menuItemSave = new JMenuItem(MENU_COMMAND_SAVE);
        menuItemNew.setActionCommand(MENU_COMMAND_NEW);
        menuItemLoad.setActionCommand(MENU_COMMAND_LOAD);
        menuItemSave.setActionCommand(MENU_COMMAND_SAVE);
        menuItemSave.setEnabled(false);
        return factory.createJMenu("File", menuItemNew, menuItemLoad, menuItemSave);

    }

    private JMenu setEdit() {
        menuItemUndo = new JMenuItem(MENU_COMMAND_UNDO);
        menuItemRedo = new JMenuItem(MENU_COMMAND_REDO);
        menuItemSelectAll = new JMenuItem(MENU_COMMAND_SELECT_ALL);
        menuItemUndo.setActionCommand(MENU_COMMAND_UNDO);
        menuItemRedo.setActionCommand(MENU_COMMAND_REDO);
        menuItemSelectAll.setActionCommand(MENU_COMMAND_SELECT_ALL);
        menuItemUndo.setEnabled(false);
        menuItemRedo.setEnabled(false);
        menuItemSelectAll.setEnabled(false);
        return factory.createJMenu("Edit", menuItemUndo, menuItemRedo, menuItemSelectAll);
    }

    private JMenu setTool() {
        final ButtonGroup group = new ButtonGroup();
        draw = new JRadioButtonMenuItem("Draw");
        draw.setSelected(true);
        clear = new JRadioButtonMenuItem("Clear");
        draw.setActionCommand(RDB_COMMAND_DRAW);
        clear.setActionCommand(RDB_COMMAND_CLEAR);
        group.add(clear);
        clear.setEnabled(false);
        group.add(draw);
        draw.setEnabled(false);
        return factory.createJMenu("Tools", draw, clear);
    }

    @Override
    public void reset() {
        getModelEditor().setAllSelected(false);
        getModelEditor().setExit(false);
        getModelEditor().setUsingTool(Tool.DRAW);
        getModelEditor().setDeselectCMB(true);
        getModelEditor().setModifiedAfterSave(false);
    }

    private void setMyListener() {
        final MouseListenerCanvas list = new MouseListenerCanvas();
        mappa.addMouseListener(list);
        mappa.addMouseMotionListener(list);
        frame.addWindowListener(new WindowsListenerFrame());
        frame.addMouseListener(new MouseListenerFrame());
        menuItemUndo.addActionListener(actionListener);
        menuItemRedo.addActionListener(actionListener);
        menuItemSelectAll.addActionListener(actionListener);
        draw.addActionListener(actionListener);
        clear.addActionListener(actionListener);
        menuItemNew.addActionListener(actionListener);
        menuItemLoad.addActionListener(actionListener);
        menuItemSave.addActionListener(actionListener);
        cmbMob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (getModelEditor().isDeselectCMB()) {
                    setSelectedCMB(CMB.MOB);
                    guiEditor.setDraw();
                    getModelEditor().setSelectedCMB(CMB.MOB);
                }
            }
        });
        cmbObject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (getModelEditor().isDeselectCMB()) {
                    setSelectedCMB(CMB.ITEM);
                    guiEditor.setDraw();
                    getModelEditor().setSelectedCMB(CMB.ITEM);
                }

            }
        });
        cmbTile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (getModelEditor().isDeselectCMB()) {
                    setSelectedCMB(CMB.TILE);
                    guiEditor.setDraw();
                    getModelEditor().setSelectedCMB(CMB.TILE);
                }
            }
        });
        chkBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent e) {
                final JCheckBox c = (JCheckBox) e.getSource();
                getModelEditor().setShoGrid(c.isSelected());
            }
        });
    }

    private void addComponentsToFrame() {
        frame.setJMenuBar(createJMenuBar());
        main.setLayout(new BorderLayout());
        mappa.setPreferredSize(new Dimension(Game.getGame().getWindowWidth(), Game.getGame().getWindowHeight()));
        mappa.setVisible(true);
        main.add(createOption(), BorderLayout.WEST);
        main.add(mappa, BorderLayout.EAST);
    }
}
