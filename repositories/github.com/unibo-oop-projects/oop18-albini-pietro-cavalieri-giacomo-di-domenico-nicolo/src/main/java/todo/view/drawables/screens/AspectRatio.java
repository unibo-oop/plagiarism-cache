package todo.view.drawables.screens;

/**
 * This enum represents a supported aspect ratio.
 */
public enum AspectRatio {
    /**
     * 16:9 aspect ratio.
     */
    SIXTEEN_NINTHS("assets/rooms/16-9"),
    /**
     * 16:10 aspect ratio.
     */
    SIXTEEN_TENTHS("assets/rooms/16-10"),
    /**
     * 21:9 aspect ratio.
     */
    TWENTYONE_NINTHS("assets/rooms/21-9");

    private final ResolutionManifest manifest;
    private final Textures textures;

    AspectRatio(final String aspectRatioDirectory) {
        this.manifest = new ResolutionManifestImpl(aspectRatioDirectory);
        this.textures = new TexturesImpl(aspectRatioDirectory, this.manifest);
    }

    /**
     * @return the manifest for this aspect ratio
     */
    public ResolutionManifest getManifest() {
        return this.manifest;
    }

    /**
     * @return the textures for this aspect ratio
     */
    public Textures getTextures() {
        return this.textures;
    }
}
