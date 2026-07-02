package controllers;

import controllers.utilities.TubeMapImpl;
import model.tube.TubeDown;
import model.tube.TubeUp;
import util.Pair;
import controllers.utilities.TubeMap;
import view.TubeView;
import view.TubeViewImpl;

/**
 * Tube controller implementation.
 */
public class TubeControllerImpl implements TubeController {

    private final TubeUp tubeUp;
    private final TubeDown tubeDown;
    private final TubeMap tubeMap;
    private final TubeView tubeView;

    /**
     * This is the constructor method that initializes all classes of tubes, create and add the first pair of tubes' rectangles to the treeMap.
     * @param controller FlappyBirdController
     */
    public TubeControllerImpl(final FlappyBirdController controller) {
        tubeUp = new TubeUp("top.png");
        tubeDown = new TubeDown("bottom.png");
        tubeMap = new TubeMapImpl(controller);
        tubeView = new TubeViewImpl();
        tubeMap.addToMap(createTubePair());
    }

    private Pair<?, ?> createTubePair() {

        final TubeUp tubeUpCopy = tubeUp.copy();
        final TubeDown tubeDownCopy = tubeDown.copy();
        tubeUpCopy.setY();
        tubeDownCopy.setY(tubeUpCopy.getPosY());

        tubeView.createRectangles();

        tubeView.setTubeUpDimension(tubeUpCopy.getWidth(), tubeUpCopy.getHeight());
        tubeView.setTubeDownDimension(tubeDownCopy.getWidth(), tubeDownCopy.getHeight());

        tubeView.setTubeUpPosition(tubeUpCopy.getPosX(), tubeUpCopy.getPosY() - tubeUpCopy.getHeight());
        tubeView.setTubeDownPosition(tubeDownCopy.getPosX(), tubeDownCopy.getPosY());

        tubeView.seTubeUpImage(tubeUp.getTubeImagePath());
        tubeView.seTubeDownImage(tubeDown.getTubeImagePath());

        return tubeView.getRectangles();
    }

    @Override
    public final void addTube() {
        tubeMap.addToMap(createTubePair());
        tubeMap.printPairTube(tubeMap.getLastValue());
    }

    @Override
    public final void scrollTubes() {
        this.tubeMap.scrollTubePair();
        this.tubeMap.checkWindowEnd();
    }

    @Override
    public final TubeMap getTubeMap() {
        return this.tubeMap;
    }



}
