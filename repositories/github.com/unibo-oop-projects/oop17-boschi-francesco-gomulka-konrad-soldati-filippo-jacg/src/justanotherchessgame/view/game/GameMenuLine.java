package justanotherchessgame.view.game;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import justanotherchessgame.model.Main;
import justanotherchessgame.view.MenuLine;

/**
 * Class used to create a menu element in the game view.
 */
public class GameMenuLine extends MenuLine {

    /**
     * Class constructor.
     * @param name is the displayed name of the element.
     */
    public GameMenuLine(final String name) {
        super(name);
    }

    @Override
    public final void resize() {
        this.getBg().setWidth(Main.getStageWidth() * 0.25);
        this.getBg().setHeight(Main.getStageHeight() * 0.04);
        this.getText().setFont(Font.font("November", FontWeight.SEMI_BOLD, Main.getStageHeight() * 0.025));
    }

}
