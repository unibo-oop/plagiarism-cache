package justanotherchessgame.view.main;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import justanotherchessgame.model.Main;
import justanotherchessgame.view.MenuLine;

/**
 * Class used to create a menu element in the main view.
 */
public class MainMenuLine extends MenuLine {

    private final boolean flag;

    /**
     * Class constructor.
     * @param name is the displayed name of the element.
     * @param flag is a flag used to set different resize values depending on what kind of element is created.
     */
    public MainMenuLine(final String name, final boolean flag) {
        super(name);
        this.flag = flag;
    }

    @Override
    public final void resize() {
      //there are 2 types of lines, and for each type the resize values are different.
        if (this.flag) {
            this.getBg().setWidth(Main.getStageWidth() * 0.2);
            this.getBg().setHeight(Main.getStageHeight() * 0.04);
        } else {
            this.getBg().setWidth(Main.getStageWidth() * 0.15);
            this.getBg().setHeight(Main.getStageHeight() * 0.04);
        }
        this.getText().setFont(Font.font("November", FontWeight.SEMI_BOLD, Main.getStageHeight() * 0.025));
    }

}
