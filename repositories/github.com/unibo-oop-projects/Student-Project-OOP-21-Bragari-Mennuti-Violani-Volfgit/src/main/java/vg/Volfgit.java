package vg;

import javafx.application.Application;
import vg.view.gameBoard.GameBoard;


public final class Volfgit {

    private Volfgit() { }

    public static void main(final String[] args) {

        // Launches the level
        Application.launch(GameBoard.class, args);

    }
}
