package unibo.exiled.model.character.attributes;

import java.util.Map;

/**
 * The implementation of the attribute factory.
 */
public final class AttributeFactoryImpl implements AttributeFactory {

    private static final int BASE_HEALTH = 10;
    private static final double WHIRLER_MODIFIER = 1.1;
    private static final double AQUASHADE_MODIFIER = 1.3;
    private static final double WAVEBREAKER_MODIFIER = 1.1;

    private Map<AttributeIdentifier, Attribute> fromValues(final double health,
                                                           final double healthModifier,
                                                           final double attack,
                                                           final double defense,
                                                           final double healthCap) {
        return Map.of(AttributeIdentifier.HEALTH,
                new CombinedAttributeImpl(health, healthModifier),
                AttributeIdentifier.ATTACK, new MultiplierAttributeImpl(attack),
                AttributeIdentifier.DEFENSE, new MultiplierAttributeImpl(defense),
                AttributeIdentifier.HEALTHCAP, new AdditiveAttributeImpl(healthCap));
    }

    private Map<AttributeIdentifier, Attribute> fromValues(final double health, final double healthCap) {
        return this.fromValues(health, 1, 1, 1, healthCap);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createPlayerAttributes() {
        return this.fromValues(BASE_HEALTH * 10, BASE_HEALTH * 10);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createGoblinAttributes() {
        return fromValues(BASE_HEALTH, BASE_HEALTH);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createBrutusAttributes() {
        return fromValues(BASE_HEALTH * 2, BASE_HEALTH * 2);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createWhirlerAttributes() {
        return fromValues(BASE_HEALTH * 2, 1, WHIRLER_MODIFIER,
                WHIRLER_MODIFIER, BASE_HEALTH * 2);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createAquaShadeAttributes() {
        return fromValues(BASE_HEALTH * 2, 1, AQUASHADE_MODIFIER,
                AQUASHADE_MODIFIER, BASE_HEALTH * 2);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createWaveBreakerAttributes() {
        return fromValues(BASE_HEALTH * 2, 1, WAVEBREAKER_MODIFIER,
                WAVEBREAKER_MODIFIER, BASE_HEALTH * 2);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createBossAttributes() {
        return fromValues(BASE_HEALTH * BASE_HEALTH * 2, BASE_HEALTH * BASE_HEALTH * 2);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createMagnetaldoAttributes() {
        return fromValues(BASE_HEALTH, BASE_HEALTH);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createLeafyAttributes() {
        return fromValues(BASE_HEALTH * 2, BASE_HEALTH * 2);
    }
}
