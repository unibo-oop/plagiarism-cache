package editor.listener;

import static editor.GUIEditorImpl.getGUIEditor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import editor.EditorImpl;
import editor.model.ModelEditorImpl.CMB;
import editor.model.ModelEditor;
import editor.model.ModelEditorImpl;

/**
 *  Listener per il frame.
 */
public class MouseListenerFrame extends MouseAdapter {

    private final ModelEditor model;
    /**
     * Costruttore che inzializza model.
     */
    public MouseListenerFrame() {
        this.model = ModelEditorImpl.getModelEditor();
    }
    @Override
    public void mouseClicked(final MouseEvent e) {
          if (EditorImpl.getEditor().getLevel() != null) {
              getGUIEditor().enableCmb(CMB.MOB, true);
              getGUIEditor().enableCmb(CMB.ITEM, true);
              getGUIEditor().enableCmb(CMB.TILE, true);
              getGUIEditor().setCmbIndex(CMB.TILE, 0);
              model.setAllSelected(false);
          }
    }
}
