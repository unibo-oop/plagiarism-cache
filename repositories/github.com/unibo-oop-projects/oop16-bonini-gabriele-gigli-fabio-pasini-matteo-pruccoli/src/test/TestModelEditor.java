package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import editor.model.ModelEditor;
import editor.model.ModelEditorImpl;
import editor.model.ModelEditorImpl.CMB;
import editor.model.ModelEditorImpl.Tool;
import util.Vector2;
import util.Vector2Impl;

/**
 * Classe per testare ModelEditor.
 */
public class TestModelEditor {

    private final ModelEditor model = ModelEditorImpl.getModelEditor();

    /**
     * Testa la correttezza dei valori del model.
     */
    @Test
    public void testModelStatus() {
        final Vector2<Integer> playerCoordinate = new Vector2Impl<>(20, 50);
        //Verifico correttezza valori di default
        assertFalse(model.isSelected());
        assertFalse(model.isDeselectCMB());
        assertFalse(model.isExit());
        assertFalse(model.isModifiedAfterSave());
        assertFalse(model.isShowGrid());
        model.setUsingTool(Tool.DRAW);
        assertEquals(model.getUsingTool(), Tool.DRAW);
        model.setAllSelected(true);
        assertTrue(model.isSelected());
        model.setSelectedCMB(CMB.MOB);
        assertEquals(model.getSelectedCMB(), CMB.MOB);
        model.setPlayerCoordinate(playerCoordinate);
        assertEquals(model.getPlayerCoordinate(), playerCoordinate);
    }

    /**
     * Test per eccezioni.
     */
    @Test(expected = IllegalArgumentException.class) 
    public void testException() {
        model.setLumos(-1);
    }
}
