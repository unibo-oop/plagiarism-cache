package editor.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import editor.EditorImpl;
import editor.model.ModelEditor;
import editor.model.ModelEditorImpl;
/**
 * Listener per chiusura frame.
 */
public class WindowsListenerFrame extends WindowAdapter {

    private final ModelEditor model;
    /**
     * Costruttore che inizializza il model.
     */
    public WindowsListenerFrame() {
        this.model = ModelEditorImpl.getModelEditor();
    }

    @Override
    public void windowClosing(final WindowEvent e) {
        if (EditorImpl.getEditor().getLevel() != null && model.isModifiedAfterSave()) {
            final int input = JOptionPane.showConfirmDialog(null, "Salvare le modifiche apportate?", "Salvataggio modifiche",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (input == JOptionPane.OK_OPTION) {
                EditorImpl.getEditor().confirmSave();
            } else if (input == JOptionPane.NO_OPTION) {
                model.setExit(true);
            }
        } else {
            model.setExit(true);
        }
    }
}
