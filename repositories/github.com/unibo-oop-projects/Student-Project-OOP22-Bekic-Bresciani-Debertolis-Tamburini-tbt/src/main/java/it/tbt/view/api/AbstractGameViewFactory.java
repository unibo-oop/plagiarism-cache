package it.tbt.view.api;

/**
 * Interface which return Factories, each capable of returning Views of the graphical framework desired.
 * @see it.tbt.view.api.GameViewFactory
 */

public interface AbstractGameViewFactory {

    /**
     * Returns implementation of GameViewFactory where each View has JavaFx graphical components.
     * @return an instance of GameViewFactory with JavaFx graphical components.
     */
    GameViewFactory getJavaFxGameViewFactory();

    /**
     * Returns implementation of GameViewFactory where each View is rendered in Console(stdout).
     * @return an instance of GameViewFactory with Console graphical components.
     */
    GameViewFactory getConsoleGameViewFactory();

}
