package aboidsim.view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import aboidsim.util.Pair;
import aboidsim.util.Vector;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * class used to draw all the entities in the simulation screen.
 *
 */
class DrawEntities {

    private static List<ImageView> images;

    /**
     * set the list of image view.
     *
     * @param list
     *            list of the level and the string representing the path of the
     *            image of each type of entity
     */
    static void setImages(final List<Pair<Integer, String>> list) {
        DrawEntities.images = new ArrayList<>();
        for (final Pair<Integer, String> p : list) {
            if (p.getX().intValue() == 0) {
                DrawEntities.images
                        .add(DrawEntities.createEntity(DrawEntities.class.getResourceAsStream("/boids/" + p.getY()),
                                SimulationScreen.BOID_HEIGHT, SimulationScreen.BOID_HEIGHT));
            } else {
                DrawEntities.images
                        .add(DrawEntities.createEntity(DrawEntities.class.getResourceAsStream("/boids/" + p.getY()),
                                SimulationScreen.BOID_WIDTH, SimulationScreen.BOID_HEIGHT));
            }
        }
    }

    /**
     *
     * @param inputStream
     *            the image to draw
     * @param width
     *            with of the image
     * @param height
     *            height of the image
     * @return
     */
    private static ImageView createEntity(final InputStream inputStream, final double width, final double height) {
        final ImageView boid = new ImageView(new Image(inputStream, width, height, false, false));
        return boid;
    }

    /**
     *
     * @param g
     *            graphics context used to draw entities
     * @param entities
     *            set of the position, the rotation and level of each entity to
     *            draw
     */
    void drawEntities(final GraphicsContext g, final Set<Pair<Pair<Vector, Double>, Integer>> entities) {

        g.clearRect(0, 0, SimulationScreen.WIDTH, SimulationScreen.HEIGHT);

        entities.stream().forEach(e -> {
            final ImageView image = DrawEntities.images.get(e.getY());
            image.setRotate(e.getX().getY());
            final SnapshotParameters param = new SnapshotParameters();
            param.setFill(javafx.scene.paint.Color.TRANSPARENT);
            final Image prova = image.snapshot(param, null);
            g.drawImage(prova, e.getX().getX().getX(), e.getX().getX().getY());
        });

    }

}
