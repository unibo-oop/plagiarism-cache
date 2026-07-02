package it.unibo.view.gamelaunchedview.renderers.skinregistry.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.view.gamelaunchedview.renderers.skinregistry.api.SkinRegistry;
import it.unibo.view.gamelaunchedview.renderers.skinregistry.api.SkinSet;
import it.unibo.view.utilities.SpriteEnum;

/**
 * Implementation of the {@link SkinRegistry} interface, responsible for managing and providing access to character skins.
 */
public class SkinRegistryImpl implements SkinRegistry {

    /**
     * A HashMap that stores the mapping between skin ids and their corresponding SkinSet instances.
     */
    private final Map<String, SkinSet> skins;

    /**
     * Constructor for the SkinRegistryImpl class. Initializes the skin registry and loads the available skins.
     */
    public SkinRegistryImpl() {
        this.skins = new HashMap<>();
        this.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SkinSet getSkinSet(final String skinName) {
        return this.skins.get(skinName);
    }

    /**
     * Loads the available skins into the registry. This method populates the skins HashMap with predefined skin sets.
     */
    private void load() {
        this.skins.put("s_basic", new SkinSetImpl(SpriteEnum.DOODLER_LEFT, SpriteEnum.DOODLER_RIGHT));
        this.skins.put("s_sub", new SkinSetImpl(SpriteEnum.SUB_LEFT, SpriteEnum.SUB_RIGHT));
        this.skins.put("s_basket", new SkinSetImpl(SpriteEnum.BASKET_LEFT, SpriteEnum.BASKET_RIGHT));
        this.skins.put("s_soccer", new SkinSetImpl(SpriteEnum.SOCCER_LEFT, SpriteEnum.SOCCER_RIGHT));
        this.skins.put("s_astro", new SkinSetImpl(SpriteEnum.ASTRO_LEFT, SpriteEnum.ASTRO_RIGHT));
        this.skins.put("s_ninja", new SkinSetImpl(SpriteEnum.NINJA_LEFT, SpriteEnum.NINJA_RIGHT));
        this.skins.put("s_bunny", new SkinSetImpl(SpriteEnum.BUNNY_LEFT, SpriteEnum.BUNNY_RIGHT));
        this.skins.put("s_frank", new SkinSetImpl(SpriteEnum.FRANK_LEFT, SpriteEnum.FRANK_RIGHT));
        this.skins.put("s_frozen", new SkinSetImpl(SpriteEnum.FROZEN_LEFT, SpriteEnum.FROZEN_RIGHT));
        this.skins.put("s_ghost", new SkinSetImpl(SpriteEnum.GHOST_LEFT, SpriteEnum.GHOST_RIGHT));
        this.skins.put("s_ice", new SkinSetImpl(SpriteEnum.ICE_LEFT, SpriteEnum.ICE_RIGHT));
        this.skins.put("s_jungle", new SkinSetImpl(SpriteEnum.JUNGLE_LEFT, SpriteEnum.JUNGLE_RIGHT));
    }
}
