package view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public interface FlappyBirdView {

    /**
     * Set screen size and call private method that set the game background.
     */
    void start();

    /**
     * Call end game class and show quit button.
     * @param score User's score to save
     */
    void endGame(int score);

    /**
     * Set view observer.
     * @param observer A class that implement {@link FlappyGameViewObserver}
     */
    void setObserver(FlappyGameViewObserver observer);

    /**
     * Set score in game view "score" label.
     * @param n New score.
     */
    void setScore(int n);

    /**
     * Returns main view node.
     * @return view's root node
     */
    Pane getRoot();

    /**
     * Add children node to root node.
     * @param n Node to be added
     */
    void addChildren(Node n);

    /**
     * Remove children node n from root node.
     * @param n node to be removed
     */
    void removeChildren(Node n);

    /**
     * Returns screen's height.
     * @return screenSize height
     */
    double getHeight();

    /**
     * Returns screen's width.
     * @return screenSize width
     */
    double getWidth();


}
