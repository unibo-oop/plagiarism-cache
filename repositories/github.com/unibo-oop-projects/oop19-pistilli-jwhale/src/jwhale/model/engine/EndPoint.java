package jwhale.model.engine;
/**
 * API end points. It's used to perform internal logic.
 *
 */
public enum EndPoint {
    /**
     * End point to get daemon informations.
     */
    VERSION("/version"),
    /**
     * End point for container operations.
     */
    CONTAINER("/containers"),
    /**
     * End point to build an image from archive.
     */
    BUILD("/build"),
    /**
     * End point to image operations.
     */
    IMAGE("/images"),
    /**
     * End point to network operations.
     */
    NETWORK("/networks"),
    /**
     * End point to volume operations.
     */
    VOLUME("/volumes");

    private String endPoint;

    EndPoint(final String endPoint) {
        this.endPoint = endPoint;
    }
    /**
     * End point format according to API docs.
     * 
     * @return End point as string.
     */
    public String getURI() {
        return this.endPoint;
    }
}
