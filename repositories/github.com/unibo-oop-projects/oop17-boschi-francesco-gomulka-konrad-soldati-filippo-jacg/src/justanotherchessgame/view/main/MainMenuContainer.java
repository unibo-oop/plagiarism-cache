package justanotherchessgame.view.main;

import justanotherchessgame.model.Main;
import justanotherchessgame.view.MenuContainer;
import justanotherchessgame.view.MenuLine;

/**
 * Class used to create the menu container in the main view.
 */
public class MainMenuContainer extends MenuContainer {
    private final int marker;

    /**
     * Class constructor.
     * @param marker is a parameter used in the resize function because there are different type of menu inside the main view.
     * @param items is the list of menu elements.
     */
    public MainMenuContainer(final int marker, final MenuLine... items) {
        super(items);
        this.marker = marker;
    }

    @Override
    public final void resize() {
        //there are 3 types of container, and for each type the resize values are different.
        this.getList().forEach(e -> {
            if (this.marker == 1) {
                e.setEndX(Main.getStageWidth() * 0.2);
                this.setTranslateX(Main.getStageWidth() * 0.15);
                this.setTranslateY(Main.getStageHeight() * 0.4);
            } else if (this.marker == 2) {
                e.setEndX(Main.getStageWidth() * 0.15);
                this.setTranslateX(Main.getStageWidth() * 0.38);
                this.setTranslateY(Main.getStageHeight() * 0.4);
            } else {
                e.setEndX(Main.getStageWidth() * 0.15);
                this.setTranslateX(Main.getStageWidth() * 0.56);
                this.setTranslateY(Main.getStageHeight() * 0.4);
            }
        });

    }

}
