package it.unibo.arkanoid.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibo.arkanoid.controller.Command;
import it.unibo.arkanoid.controller.MovePaddleCommand;
import it.unibo.arkanoid.view.controllers.SubViewController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Handle the input, like the movement of the mouse and the pressing of the
 * keys.
 *
 */
public class InputListener {

    private final SubViewController subViewController;
    private final List<Command> inputList;
    private double mouseXCoordinate;

    /**
     * Capture the movement of mouse from Scene.
     * 
     * @param subViewController
     *            where capture the movement of mouse.
     */
    public InputListener(final SubViewController subViewController) {
        this.inputList = new ArrayList<>();
        this.subViewController = subViewController;
    }

    /**
     * Catch input from the Scene. Like KeyEvent and MouseEvent.
     * 
     */
    public void action() {
        this.subViewController.getRoot().setOnMouseMoved(new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                final MovePaddleCommand commandPaddle = new MovePaddleCommand(event.getX());
                mouseXCoordinate = event.getX();
                inputList.add(commandPaddle);
            }

        });

    }

    /**
     * Getters for the X coordinate from Mouse. It'usefull for Paddle movement. Used to debug.
     * 
     * @return Double X coordinate from Mouse.
     */
    public Double getMouseXCoordinate() {
        return this.mouseXCoordinate;
    }

    /**
     * It's useful to clear the Input List.
     */
    public void clearList() {
        this.inputList.clear();
    }

    /**
     * Getter for defensive copy of Input List.
     * 
     * @return inputList of Input.
     */
    public List<Command> getInputList() {
        return Collections.unmodifiableList(this.inputList);
    }

}
