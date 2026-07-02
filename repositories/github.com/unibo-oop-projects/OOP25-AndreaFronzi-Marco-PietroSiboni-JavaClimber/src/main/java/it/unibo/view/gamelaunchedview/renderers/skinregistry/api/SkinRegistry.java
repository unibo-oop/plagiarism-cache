package it.unibo.view.gamelaunchedview.renderers.skinregistry.api;

/**
 * This interface rapresent a registry of the skin.
 */
@FunctionalInterface
public interface SkinRegistry {

    /**
     * Gets the {@link SkinSet} associated with the given skin name.
     *
     * @param skinName the name of the skin to retrieve
     * @return the SkinSet associated with the given skin name
     */
    SkinSet getSkinSet(String skinName);
}
