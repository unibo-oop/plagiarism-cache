package unibo.exiled.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.MultiplierAttribute;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;


final class TestItem {

    private static final double DECREASE_HEALTH = 15.0;
    private static final double HEALING_ITEM_AMOUNT = 20.0;

    @Test
    void testPowerUpItemUsage() {
        final Player player = new PlayerImpl(0, 0, 0);
        final PowerUpItem powerUpItem = new PowerUpItem("Strength Boost", "Increases attack strength", 10.0,
                AttributeIdentifier.ATTACK);
        final double initialAttributeValue =
                ((MultiplierAttribute) player.getAttributes().get(AttributeIdentifier.ATTACK)).modifier();
        powerUpItem.use(player);
        final double newAttributeValue =
                ((MultiplierAttribute) player.getAttributes().get(AttributeIdentifier.ATTACK)).modifier();
        assertEquals(initialAttributeValue + 10.0, newAttributeValue);
        assertEquals(AttributeIdentifier.ATTACK, powerUpItem.getBoostedAttribute());
    }

    @Test
    void testHealingItemUsage() {
        final Player player = new PlayerImpl(0, 0, 0);
        final HealingItem healingItem = new HealingItem("Health Potion", "Restores health", HEALING_ITEM_AMOUNT);
        player.decreaseAttributeValue(AttributeIdentifier.HEALTH, DECREASE_HEALTH);
        final double initialHealth = player.getHealth();
        healingItem.use(player);
        final double newHealth = player.getHealth();
        //La vita iniziale è 100, la vita salirebbe a 105 ma essendoci il cap a 100 si ferma a 100
        assertEquals(initialHealth + DECREASE_HEALTH, newHealth);
        assertEquals(HEALING_ITEM_AMOUNT, healingItem.getAmount());
    }
}

