package jwhale.model;

import jwhale.model.engine.Operation;
import jwhale.model.engine.operations.object.ImageDelete;
import jwhale.model.engine.operations.object.ObjectOp;
/**
 * Image representation.
 */
public class Image implements Item {
    private final String imageName;
    private final String imageVersion;
    private static final String LATEST = "latest";
    private final ObjectOp<ImageDelete> removerConfig = new ImageDelete();
    /**
     * Create an instance of an image with specific version.
     * @param imageName
     *          image name.
     * @param imageVersion
     *          image version.
     */
    public Image(final String imageName, final String imageVersion) {
        this.imageName = imageName;
        this.imageVersion = imageVersion;
    }
    /**
     * Create an instance of a latest version image.
     * @param imageName
     *          image name.
     */
    public Image(final String imageName) {
       this.imageName = imageName;
       this.imageVersion = LATEST;
    }
    @Override
    public final String getName() {
        return imageName;
    }
    @Override
    public final String getFeature() {
        return imageVersion;
    }
    @Override
    public final Operation remove() {
        return removerConfig.target("/" + imageName + ":" + imageVersion)
                .configOperation();
    }

}
