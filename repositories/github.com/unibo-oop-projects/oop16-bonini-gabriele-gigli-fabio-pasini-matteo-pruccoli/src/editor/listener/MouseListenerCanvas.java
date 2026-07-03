package editor.listener;

import static editor.GUIEditorImpl.getGUIEditor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import editor.Editor;
import editor.EditorImpl;
import editor.model.ModelEditor;
import editor.model.ModelEditorImpl;
import editor.model.ModelEditorImpl.CMB;
import editor.model.ModelEditorImpl.Tool;
import util.Vector2Impl;

/**
 * Mouse listener per canvas.
 *
 */
public class MouseListenerCanvas extends MouseAdapter {
    private final Editor editor = EditorImpl.getEditor();
    private final ModelEditor model;
    private boolean dragging;

    /**
     * Cotruttore che inizializza model.
     */
    public MouseListenerCanvas() {
        this.model = ModelEditorImpl.getModelEditor();
    }
    @Override
    public void mouseClicked(final MouseEvent e) {
        // tasto dx o rotella
        if (e.getButton() == 3 || e.getButton() == 2) {
            this.toolsAction();
        }
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        // coordinate e quale btn premuto
        model.setMouseCoordinate(new Vector2Impl<>(e.getX(), e.getY()));
        model.setMouseStartCoordinate(model.getMouseCoordinate());
        model.setPlayerCoordinate(new Vector2Impl<Integer>(editor.getPlayer().getX(), editor.getPlayer().getY()));
        model.setButton(e.getButton());
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        // tenere aggiornate coordinate mouse
        model.setMouseCoordinate(new Vector2Impl<>(e.getX(), e.getY()));
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        /* quando clicco e muovo mouse */
        model.setMouseCoordinate(new Vector2Impl<>(e.getX(), e.getY()));

        if ((model.getButton() == 3 || model.getButton() == 2) && ((model.getUsingTool() == Tool.DRAW
            && (getGUIEditor().getCmbIndex(CMB.MOB) != -1) || (getGUIEditor().getCmbIndex(CMB.ITEM) != -1) 
            || (getGUIEditor().getCmbIndex(CMB.TILE) != -1)) || model.getUsingTool() == Tool.CLEAR)) {
            dragging = true;
            this.toolsAction();
        } else if (model.getButton() == 1) {
            editor.mapMovement(model.getMouseCoordinate(), model.getMouseSartCoordinate(),
            model.getPlayerCoordinate(), model.getVelocityScroll());
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        if (dragging) {
            editor.updateHistory();
            dragging = false;
        }
    }

    private void toolsAction() {
        final CMB typeCmb = model.getSelectedCMB();
        editor.toolsActions(model.getUsingTool(), model.getMouseCoordinate(), typeCmb,
                           (typeCmb == CMB.MOB) ? getGUIEditor().getCmbIndex(CMB.MOB)
                           : ((typeCmb == CMB.TILE) ? getGUIEditor().getCmbIndex(CMB.TILE)
                           : getGUIEditor().getCmbIndex(CMB.ITEM)), dragging);
    }
}
