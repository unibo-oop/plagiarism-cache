package it.unibo.pacman.view;

import java.util.Set;

import it.unibo.pacman.controller.entities.Renderable;
/**
 * An interface that extends {@link ViewableUI}, used to render all the game object.
 */
public interface RenderableUI extends ViewableUI {
    /**
     * Calls render for every element of the view.
     * @param setView a set filled with the Views of every entity of the game
     */
    void render(Set<? extends Renderable> setView);
}
