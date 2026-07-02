package it.unibo.pacman.view.mapeditor;

import java.util.Set;

import it.unibo.pacman.view.ViewableUI;

/**
 * Model the editor view.
 */
public interface EditorView extends ViewableUI {

    /**
     * 
     * @return the palette set
     */
    Set<SelectionTile> getPalette();

}
