package controller.input;

import model.Fighter;
import utility.KeyMapper;
import view.stage.FighterView;

/**
 * Controls the inputs given by the player
 */
public class InputController {

    private KeyMapper keyMap;
    private Fighter body;
    private FighterView view;

    /**
     *  Sets up the listener and sets the player's body
     * @param body
     * @param view
     * @param keyMap
     */
    public InputController(final Fighter body, final FighterView view, final KeyMapper keyMap) {
        this.keyMap = keyMap;
        this.body =  body;
        this.view = view;
        this.view.setListener(new KeyboardListener(this.body, this.view, this.keyMap));
        this.view.setBodyFighter(body);
    }
}
