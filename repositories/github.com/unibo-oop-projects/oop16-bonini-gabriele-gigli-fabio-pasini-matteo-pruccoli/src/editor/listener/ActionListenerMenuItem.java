package editor.listener;

import static editor.GUIEditorImpl.MENU_COMMAND_LOAD;
import static editor.GUIEditorImpl.MENU_COMMAND_NEW;
import static editor.GUIEditorImpl.MENU_COMMAND_REDO;
import static editor.GUIEditorImpl.MENU_COMMAND_SAVE;
import static editor.GUIEditorImpl.MENU_COMMAND_SELECT_ALL;
import static editor.GUIEditorImpl.MENU_COMMAND_UNDO;
import static editor.GUIEditorImpl.RDB_COMMAND_CLEAR;
import static editor.GUIEditorImpl.RDB_COMMAND_DRAW;
import static editor.GUIEditorImpl.getGUIEditor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import editor.Editor;
import editor.EditorImpl;
import editor.model.ModelEditor;
import editor.model.ModelEditorImpl;
import editor.model.ModelEditorImpl.CMB;
import editor.model.ModelEditorImpl.Tool;

/**
 * Listener per i menuItem.
 *
 */
public class ActionListenerMenuItem implements ActionListener {

    private final Editor editor = EditorImpl.getEditor();
    private final ModelEditor model;
    /**
     * Costruttore che inzializza il model.
     */
    public ActionListenerMenuItem() {
        this.model = ModelEditorImpl.getModelEditor();
    }
    @Override
    public void actionPerformed(final ActionEvent e) {

        switch (e.getActionCommand()) {

        case MENU_COMMAND_LOAD:
            this.load();
            break;
        case MENU_COMMAND_SAVE:
            editor.confirmSave();
            break;

        case MENU_COMMAND_NEW:
            this.newLevel();
            break;
        case MENU_COMMAND_UNDO:
            editor.undo();
            getGUIEditor().enableMenuItemRedo(true);
            break;
        case MENU_COMMAND_REDO:
            editor.redo();
            getGUIEditor().enableMenuItemUndo(true);
            break;
        case MENU_COMMAND_SELECT_ALL:
            this.selectAll();
            break;
        case RDB_COMMAND_CLEAR:
            model.setUsingTool(Tool.CLEAR);
            getGUIEditor().setCmbIndex(CMB.MOB, -1);
            getGUIEditor().setCmbIndex(CMB.ITEM, -1);
            getGUIEditor().setCmbIndex(CMB.TILE, -1);
            break;
        case RDB_COMMAND_DRAW:
            model.setUsingTool(Tool.DRAW);
            getGUIEditor().setCmbIndex(CMB.TILE, 0);
            break;
        default:
            break;
        }

    }
    private void enableComponents() {
        getGUIEditor().enableCmb(CMB.MOB, true);
        getGUIEditor().setCmbIndex(CMB.TILE, -1);
        getGUIEditor().enableCmb(CMB.ITEM, true);
        getGUIEditor().setCmbIndex(CMB.ITEM, -1);
        getGUIEditor().enableCmb(CMB.TILE, true);
        getGUIEditor().setCmbIndex(CMB.MOB, -1);
        getGUIEditor().enableMenuItemLoad(true);
        getGUIEditor().enableMenuItemNew(true);
        getGUIEditor().enablemenuItemSave(true);
        getGUIEditor().enableMenuItemSelectAll(true);
        getGUIEditor().enableRdbTool(Tool.DRAW, true);
        getGUIEditor().enableRdbTool(Tool.CLEAR, true);
    }

    private void load() {
        final JFileChooser j = new JFileChooser();
        j.setFileFilter(new FileNameExtensionFilter("Image files", "png"));
        final int input = j.showOpenDialog(null);
        if (input == JFileChooser.APPROVE_OPTION) {
            editor.loadLevel(j.getSelectedFile());
            enableComponents();
        }
        model.setModifiedAfterSave(false);
        getGUIEditor().setSelectedCMB(CMB.TILE);
        getGUIEditor().setCmbIndex(CMB.TILE, 0);
        getGUIEditor().setDraw();
    }

    private void newLevel() {
        if (model.isModifiedAfterSave()) {
            final int input = JOptionPane.showConfirmDialog(null, "Salvare le modifiche apportate?", "Salvataggio modifiche",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (input == JOptionPane.OK_OPTION) {
                editor.confirmSave();
                this.createNewLevel();
            } else if (input == JOptionPane.NO_OPTION) {
                this.createNewLevel();
            }
        } else {
            this.createNewLevel();
        }
        model.setModifiedAfterSave(false);
        getGUIEditor().setSelectedCMB(CMB.TILE);
        getGUIEditor().setCmbIndex(CMB.TILE, 0);
        getGUIEditor().setDraw();
    }

    private void createNewLevel() {
        final int value = 100;
        final int minimum = 50;
        final int maximum = 1000;
        final int stepSize = 1;
        final JPanel myPanel = new JPanel();
        final JSpinner width = new JSpinner(new SpinnerNumberModel(value, minimum, maximum, stepSize));
        final JSpinner height = new JSpinner(new SpinnerNumberModel(value, minimum, maximum, stepSize));
        myPanel.add(new JLabel("Width"));
        myPanel.add(width);
        myPanel.add(new JLabel("Height"));
        myPanel.add(height);
        final int input = JOptionPane.showConfirmDialog(null, myPanel, "Creazione livello", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (input == JOptionPane.OK_OPTION) {
            editor.newLevel(new Dimension((int) width.getValue(), (int) height.getValue()));
            enableComponents();
        }
    }

    private void selectAll() {
        model.setAllSelected(true);
        getGUIEditor().setCmbIndex(CMB.MOB, -1);
        getGUIEditor().setCmbIndex(CMB.TILE, -1);
        getGUIEditor().enableCmb(CMB.MOB, false);
        getGUIEditor().enableCmb(CMB.ITEM, false);
    }

}
