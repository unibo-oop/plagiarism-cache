package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Pair;
import uicontrollers.BattleController;

/**
 * 
 * Implementation of the scene dedicated to the battle.
 *
 */
public class BattleScene extends Scene {
    /**
     * @param root
     *            the parent used to load the window
     * 
     * @param bc
     *            the controller of the scene
     * 
     * @param p
     *            the pair containing the maximum number of columns and rows of the
     *            arena
     */
    public BattleScene(final Parent root, final BattleController bc, final Pair<Integer, Integer> p) {
        super(root);
        bc.initData(bc, p);
    }

}
