package view.renderer;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.balllaucher.BallLauncher;
import model.world.WorldSettings;
import view.ViewSettings;

/**
 * Class used to render the BallLauncher.
 */
public class BallLauncherRenderer {

    /**
     * @param launcher the {@link BallLauncher} to be rendered
     * @return the {@link Node}s that represents the BallLauncher in JavaFX
     */
    public final List<Node> render(final BallLauncher launcher) {
        //create tube
        final double tubeWidth = launcher.getWidth() * ViewSettings.SCALE;
        final double tubeHeigth = launcher.getHeight() * ViewSettings.SCALE;
        final Rectangle tube = new Rectangle(tubeWidth, tubeHeigth);
        tube.setX(-tubeWidth / 2);
        tube.setY(WorldSettings.WORLD_HEIGHT * ViewSettings.SCALE - tubeHeigth / 2);
        final double angle = Math.atan2(launcher.getPosition().getRight() - WorldSettings.WORLD_HEIGHT, -launcher.getPosition().getLeft());
        tube.setRotate(Math.toDegrees(Math.PI / 2 - angle));
        tube.setTranslateX(-Math.cos(-angle) * tubeHeigth / 2);
        tube.setTranslateY(-Math.sin(-angle) * tubeHeigth / 2);
        //create base
        final double baseRadius = tubeHeigth / 2;
        final Circle base = new Circle(0, WorldSettings.WORLD_HEIGHT * ViewSettings.SCALE, baseRadius);
        return List.of(tube, base);
    }

}
