package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

import java.awt.Dimension;

import org.junit.Test;

import editor.Editor;
import editor.EditorImpl;
import editor.GUIEditorImpl;
import editor.model.ModelEditorImpl;
import maingame.game.Game;
import util.Vector2Impl;

/**
 * Classe per testare l'editor di livello.
 */
public class TestEditor {

    /**
     * Testa la correttezza della cronologia dell'editor.
     */
    @Test
    public void testEditorHistory() {
        final Editor editor = EditorImpl.getEditor();
        int[] tiles;
        int[] mobsItems;
        Game.getGame().setEditor(true);
        GUIEditorImpl.getGUIEditor().open();
        editor.newLevel(new Dimension(100, 100));
        tiles = editor.getTiles();
        mobsItems = editor.getMobsItems();
        editor.toolsActions(ModelEditorImpl.Tool.DRAW, new Vector2Impl<Integer>(100, 100), ModelEditorImpl.CMB.TILE, 0,
                false);
        editor.undo();
        assertArrayEquals(tiles, editor.getTiles());
        assertArrayEquals(mobsItems, editor.getMobsItems());
        editor.toolsActions(ModelEditorImpl.Tool.DRAW, new Vector2Impl<Integer>(100, 100), ModelEditorImpl.CMB.TILE, 0,
                false);
        tiles = editor.getTiles();
        mobsItems = editor.getMobsItems();
        editor.undo();
        editor.redo();
        assertArrayEquals(tiles, editor.getTiles());
        assertArrayEquals(mobsItems, editor.getMobsItems());
        editor.toolsActions(ModelEditorImpl.Tool.DRAW, new Vector2Impl<Integer>(100, 100), ModelEditorImpl.CMB.TILE, 0,
                false);
        editor.toolsActions(ModelEditorImpl.Tool.DRAW, new Vector2Impl<Integer>(180, 180), ModelEditorImpl.CMB.TILE, 0,
                false);
        tiles = editor.getTiles();
        mobsItems = editor.getMobsItems();
        editor.undo();
        editor.toolsActions(ModelEditorImpl.Tool.DRAW, new Vector2Impl<Integer>(360, 360), ModelEditorImpl.CMB.TILE, 0,
                false);
        assertFalse(editor.getTilesHistory().contains(tiles));
        assertFalse(editor.getMobsItemsHistory().contains(mobsItems));
    }
}
