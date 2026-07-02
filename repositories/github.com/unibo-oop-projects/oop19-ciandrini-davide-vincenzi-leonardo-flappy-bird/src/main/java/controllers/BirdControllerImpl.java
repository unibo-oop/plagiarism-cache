package controllers;

import javafx.scene.shape.Rectangle;
import model.bird.Bird;
import model.bird.BirdImpl;
import view.BirdView;
import view.BirdViewImpl;

public class  BirdControllerImpl implements BirdController {

    /**
     * Screen height.
     */
    public static final int SCREEN_HEIGHT = 335;

    private final Bird bird;
    private final BirdView birdView;

    /**
     * This is the constructor method that initialize all classes of bird and set the bird view.
     */
    public BirdControllerImpl() {
        //Singleton implementation
        //bird = BirdImpl.getInstance();

        bird = new BirdImpl();
        birdView = new BirdViewImpl();
        this.initBirdView();
    }

    @Override
    public final void initBirdView() {
        birdView.setPosition(bird.getPosX(), bird.getPosY());
        birdView.setWidthHeight(bird.getHeightBird(), bird.getWidthBird());
        birdView.setImage(bird.getBirdImagePath());
    }

    @Override
    public final void birdMovement(final double n) {
        bird.birdUpdate(n);
        birdView.updatePosition(bird.getPosY());
    }

    @Override
    public final boolean floorCollision(final Rectangle r) {
        return r.getY() == SCREEN_HEIGHT - bird.getHeightBird();
    }

    @Override
    public final BirdView getBirdView() {
        return this.birdView;
    }

    @Override
    public final Bird getBirdModel() {
        return this.bird;
    }
}
