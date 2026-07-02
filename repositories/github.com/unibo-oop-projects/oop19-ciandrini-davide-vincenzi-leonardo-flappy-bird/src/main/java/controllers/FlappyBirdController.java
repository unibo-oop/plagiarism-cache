package controllers;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public interface FlappyBirdController {

    /**
     *
     * @return TubeController
     */
    TubeController getTubeController();

    /**
     *
     * @return BirdController
     */
    BirdController getBirdController();

    /**
     * Check collisions between Bird and floor and between Bird and Tubes.
     * @throws IOException IoException
     */
    void checkCollision() throws IOException;

    /**
     * Call {@link model.Score#incrementScore()} to update score
     * and call {@link view.FlappyBirdView#setScore(int)} to print score in the view.
     */
    void updateScore();

    /**
     * Return main view node from view.
     * @return view's root node
     */
    Pane getRoot();

    /**
     * Removes node from view's root node.
     * @param n node to be removed
     */
    void removeNode(Node n);

    /**
     * Adds node to view's root node.
     * @param n node to be added
     */
    void addNode(Node n);
}
