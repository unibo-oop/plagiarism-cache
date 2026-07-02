package vg.model.entity.dynamicEntity.player;

import com.google.common.base.Optional;
import vg.utils.Direction;
import vg.utils.V2D;
import vg.utils.V2DUtility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TailImpl implements Tail {
    /**
     * List of all extremity point of segment.
     * */
    private List<V2D> coordinates;

    public static Tail emptyTail() {
        return new TailImpl();
    }
    private TailImpl() {
        this.coordinates = new ArrayList<>();
    }
    @Override
    public List<V2D> getCoordinates() {
        return Collections.unmodifiableList(this.coordinates);
    }

    @Override
    public void resetTail() {
        this.coordinates = new ArrayList<>();
    }

    @Override
    public Optional<V2D> getLastCoordinate() {
        if (this.coordinates.size() > 0) {
            return Optional.of(this.coordinates.get(this.coordinates.size() - 1));
        } else {
            return Optional.absent();
        }
    }

    @Override
    public void addPoint(final V2D point) {
        if (!this.coordinates.contains(point)) {
            this.coordinates.add(point);
        }
    }

    @Override
    public List<V2D> getVertex() {
        return V2DUtility.getVertex(this.coordinates);
    }

}
