package powpaw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import javafx.geometry.Point2D;
import powpaw.player.model.api.Player;
import powpaw.player.model.impl.PlayerImpl;
import powpaw.weapon.model.api.Weapon;
import powpaw.weapon.model.impl.WeaponFactory;

class WeaponTest {

    private static final Point2D INIZIAL_POSITION = new Point2D(0, 0);
    private static final int DEBUG_DURABILITY = 1;
    private static final int WEAPOIN_ID_ZERO = 0;
    private static final int WEAPOIN_ID_ONE = 1;

    @Test
    void weaponOptionalTest() {
        final Player player = new PlayerImpl(INIZIAL_POSITION, WEAPOIN_ID_ZERO);
        final Weapon sword = WeaponFactory.createWeapon(WEAPOIN_ID_ZERO);
        player.setWeapon(Optional.of(sword));
        assertTrue(player.getWeapon().isPresent());
        final Weapon hammer = WeaponFactory.createWeapon(WEAPOIN_ID_ONE);
        player.setWeapon(Optional.of(hammer));
        assertTrue(player.getWeapon().isPresent());
    }

    @Test
    void weaponDurabilityTest() {
        final Weapon sword = WeaponFactory.createWeapon(WEAPOIN_ID_ZERO);
        final int maxDurability = sword.getDurability();
        for (int i = 1; i < maxDurability; i++) {
            sword.decrementDurability();
        }
        assertEquals(DEBUG_DURABILITY, sword.getDurability());
    }

    @Test
    void weaponFactoryTest() {
        final Weapon sword = WeaponFactory.createWeapon(WEAPOIN_ID_ZERO);
        assertEquals(WEAPOIN_ID_ZERO, sword.getId());
        final Weapon hammer = WeaponFactory.createWeapon(WEAPOIN_ID_ONE);
        assertEquals(WEAPOIN_ID_ONE, hammer.getId());
    }
}
