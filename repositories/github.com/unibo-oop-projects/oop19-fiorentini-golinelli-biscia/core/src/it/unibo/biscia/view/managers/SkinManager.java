package it.unibo.biscia.view.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for managing skins files.
 *
 */
public final class SkinManager {

    /**
     * Default skin.
     */
    public static final Asset<Skin> MAIN = new AssetImpl<>("skins/main.json", "main",
            new Skin(new ArrayList<>(Arrays.asList(FontManager.ARCADE)), "skins/main.atlas"));

    /**
     * A Skin asset info. NOTE: A Skin can be composed besides its json file, of
     * others resources too, in this case assets (maybe generated runtime like
     * bitmap fonts).
     */
    public static final class Skin {
        private final List<Asset<?>> resources;
        private final String atlasPath;

        private Skin(final List<Asset<?>> resources, final String atlasPath) {
            this.atlasPath = atlasPath;
            this.resources = resources;
        }

        public List<Asset<?>> getResources() {
            return Collections.unmodifiableList(resources);
        }

        public String getAtlasPath() {
            return atlasPath;
        }
    }

    private SkinManager() {
    }

}
