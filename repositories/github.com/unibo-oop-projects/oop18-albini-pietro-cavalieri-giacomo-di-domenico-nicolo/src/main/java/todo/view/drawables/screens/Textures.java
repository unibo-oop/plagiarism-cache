package todo.view.drawables.screens;

public interface Textures {
    String FLOOR = "floor";
    String WALLS = "walls";
    String BELT_INPUT = "beltInput";
    String BELT_OUTPUT = "beltOutput";

    /**
     * Get the texture with the provided name for the current aspect ratio. The
     * texture has to be defined in the manifest.
     *
     * @param name the name of the texture to load
     * @return the texture
     */
    Texture getTexture(String name);

    /**
     * Dispose the loaded textures.
     */
    void dispose();
}
