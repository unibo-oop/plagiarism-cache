package unibo.exiled.model.character.attributes;

import java.util.Map;

/**
 * A factory of default attributes for characters.
 */
public interface AttributeFactory {
    /**
     * Creates the attributes of the player.
     *
     * @return A map of attributes with the overly specified values.
     */
    Map<AttributeIdentifier, Attribute> createPlayerAttributes();

    /**
     * Creates the attributes of the goblin, the weakest enemy.
     *
     * @return A map of attributes with the overly specified values.
     */
    Map<AttributeIdentifier, Attribute> createGoblinAttributes();

    /**
     * Creates the attributes of the brutus.
     *
     * @return A map of attributes with the overly specified values.
     */
    Map<AttributeIdentifier, Attribute> createBrutusAttributes();

    /**
     * Creates the attributes for the Whirler.
     *
     * @return A map of attributes with the overly specified values.
     */
    Map<AttributeIdentifier, Attribute> createWhirlerAttributes();

    /**
     * Creates the attributes for the Aquashade.
     *
     * @return A map of attributes with the overly specified values.
     */
    Map<AttributeIdentifier, Attribute> createAquaShadeAttributes();

    /**
     * Creates the attributes for the Aquashade.
     *
     * @return A map of attributes with the overly specified values.
     */
    Map<AttributeIdentifier, Attribute> createWaveBreakerAttributes();


    /**
     * Creates the attributes of the Boss.
     *
     * @return A map of attributes with the overly specified values.
     */
    Map<AttributeIdentifier, Attribute> createBossAttributes();

    /**
     * Creates the attributes of the Magnetaldo bolt enemy.
     *
     * @return The mapping of the attributes of Magnetaldo.
     */
    Map<AttributeIdentifier, Attribute> createMagnetaldoAttributes();

    /**
     * Creates the attributes of the enemy "Leafy".
     *
     * @return The mapping of the attributes of "Leafy".
     */
    Map<AttributeIdentifier, Attribute> createLeafyAttributes();
}
