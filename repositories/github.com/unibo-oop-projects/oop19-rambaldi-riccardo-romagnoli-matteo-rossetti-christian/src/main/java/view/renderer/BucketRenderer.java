package view.renderer;

import java.util.List;


import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import model.bucket.Bucket;
import view.ViewSettings;

/**
 * The BucketRenderer class render a bucket in the world.
 */
public class BucketRenderer {

    private static final int RIGHT_SIDE_UPPER = 0;
    private static final int RIGHT_SIDE_LOWER_LEFT = 1;
    private static final int RIGHT_SIDE_LOWER_RIGHT = 2;
    private static final int LEFT_SIDE_LOWER_LEFT = 3;
    private static final int LEFT_SIDE_LOWER_RIGHT = 4;
    private static final int LEFT_SIDE_UPPER = 5;

    /**
     * Method to render the bucket.
     * @param bucket.
     * @return a {@link List} containing the two rendered triangle of the {@link Bucket}.
     */
    public final List<Node> render(final Bucket bucket) {
        final Polygon triangle = new Polygon(bucket.getPositions().get(RIGHT_SIDE_UPPER).getLeft() * ViewSettings.SCALE, bucket.getPositions().get(RIGHT_SIDE_UPPER).getRight() * ViewSettings.SCALE, bucket.getPositions().get(RIGHT_SIDE_LOWER_LEFT).getLeft() * ViewSettings.SCALE,
                bucket.getPositions().get(RIGHT_SIDE_LOWER_LEFT).getRight() * ViewSettings.SCALE, bucket.getPositions().get(RIGHT_SIDE_LOWER_RIGHT).getLeft() * ViewSettings.SCALE, bucket.getPositions().get(RIGHT_SIDE_LOWER_RIGHT).getRight() * ViewSettings.SCALE);
        final Polygon triangle1 = new Polygon(bucket.getPositions().get(LEFT_SIDE_LOWER_LEFT).getLeft() * ViewSettings.SCALE, bucket.getPositions().get(LEFT_SIDE_LOWER_LEFT).getRight() * ViewSettings.SCALE, bucket.getPositions().get(LEFT_SIDE_LOWER_RIGHT).getLeft() * ViewSettings.SCALE,
                bucket.getPositions().get(LEFT_SIDE_LOWER_RIGHT).getRight() * ViewSettings.SCALE, bucket.getPositions().get(LEFT_SIDE_UPPER).getLeft() * ViewSettings.SCALE, bucket.getPositions().get(LEFT_SIDE_UPPER).getRight() * ViewSettings.SCALE);
        triangle.setFill(Color.ROYALBLUE);
        triangle1.setFill(Color.ROYALBLUE);
        return List.of(triangle, triangle1);
    }
}
