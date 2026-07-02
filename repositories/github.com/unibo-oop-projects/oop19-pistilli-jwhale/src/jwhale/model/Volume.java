package jwhale.model;

import jwhale.model.engine.Operation;
import jwhale.model.engine.operations.object.ObjectOp;
import jwhale.model.engine.operations.object.VolumeRemove;
/**
 * It represents a volume instance.
 *
 */
public class Volume implements Item {
    private final String volumeName;
    private final String mountPoint;
    private final ObjectOp<VolumeRemove> removerConfig = new VolumeRemove();
    /**
     * Create an instance of a volume with specific mount point.
     * @param volumeName
     *          volume name.
     * @param mountPoint
     *          Mount point path.
     */
    public Volume(final String volumeName, final String mountPoint) {
        this.volumeName = volumeName;
        this.mountPoint = mountPoint;
    }
    @Override
    public final String getName() {
        return volumeName;
    }
    @Override
    public final String getFeature() {
        return mountPoint;
    }
    @Override
    public final Operation remove() {
        return removerConfig.target(volumeName)
                .configOperation();
    }

}
