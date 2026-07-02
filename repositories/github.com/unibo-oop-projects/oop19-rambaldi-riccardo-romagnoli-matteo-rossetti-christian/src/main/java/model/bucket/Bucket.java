package model.bucket;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.dynamics.Body;

public interface Bucket {

    /**
     * @return a list of positions that contains the vertices of the bucket.
     */
    List<Pair<Double, Double>> getPositions();

    /**
     * @return a {@link List} of the {@link Body}s of the bucket. 
     */
    List<Body> getPhysicalBody();

    /**
     * Method to check if the {@link Ball} has fallen in the bucket.
     * @return true if the ball fall in the bucket, false otherwise.
     */
    boolean slamDunk();

    /**
     * Method to move the bucket from one side of the stage to the other. If the bucket reach one to of the side, change its linear direction.
     */
    void update();
}
