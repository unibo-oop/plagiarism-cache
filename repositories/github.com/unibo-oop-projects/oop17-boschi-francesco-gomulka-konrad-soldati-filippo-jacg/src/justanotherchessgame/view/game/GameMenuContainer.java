package justanotherchessgame.view.game;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import justanotherchessgame.model.Main;
import justanotherchessgame.util.ImageGenerator;
import justanotherchessgame.view.MenuContainer;
import justanotherchessgame.view.MenuLine;

/**
 * Class used to create the menu container in the game view.
 */
public class GameMenuContainer extends MenuContainer {

    /**
     * Class constructor.
     * @param items is th list of menu elements.
     */
    public GameMenuContainer(final MenuLine... items) {
        super(items);
        final Image img = ImageGenerator.generateImage("wood.jpg").getImage(); 
        this.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    @Override
    public final void resize() {
        this.getList().forEach(e -> {
            e.setEndX(Main.getStageWidth() * 0.25);
            this.setTranslateY(2);
        });
    }

}
