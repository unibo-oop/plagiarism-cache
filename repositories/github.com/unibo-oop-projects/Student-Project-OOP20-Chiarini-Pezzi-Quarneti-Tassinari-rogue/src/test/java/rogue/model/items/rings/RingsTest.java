package rogue.model.items.rings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import rogue.model.creature.Player;
import rogue.model.creature.PlayerFactoryImpl;
import rogue.model.items.armor.ArmorImpl;
import rogue.model.items.armor.ArmorType;
import rogue.model.items.weapons.BaseWeapon;
import rogue.model.items.weapons.IncreaseAccuracy;
import rogue.model.items.weapons.IncreaseDamage;
import rogue.model.items.weapons.WeaponType;

public class RingsTest {

    private final Player player = new PlayerFactoryImpl().create();
    private static final int LEATHER_AC = 8;
    private static final int CHAIN_MAIL_AC = 5;

    @Before
    public void initTest() {
        assertEquals(new ArmorImpl(ArmorType.LEATHER), player.getEquipment().getArmor());
        assertEquals(new BaseWeapon(WeaponType.MACE), player.getEquipment().getWeapon());
    }

    @Test
    public void testBasic1() {
        // Improves weapon accuracy
        final Ring ring = new RingImpl(RingType.DEXTERITY);
        assertTrue(ring.use(player));
        assertEquals(new IncreaseAccuracy(new BaseWeapon(WeaponType.MACE)), player.getEquipment().getWeapon());
        // detaches the ring
        ring.stopUsing(player);
        // the weapon is back to normal state
        assertEquals(new BaseWeapon(WeaponType.MACE), player.getEquipment().getWeapon());
    }

    @Test
    public void testBasic2() {
        // Improves armor AC
        final Ring ring = new RingImpl(RingType.PROTECTION);
        assertTrue(ring.use(player));
        assertEquals(LEATHER_AC - 2, player.getEquipment().getArmor().getAC());
        // detaches the ring
        ring.stopUsing(player);
        // the armor is back to normal state
        assertEquals(8, player.getEquipment().getArmor().getAC());
    }

    @Test
    public void testChangeWeapon() {
        // Improves weapon accuracy
        final Ring ring = new RingImpl(RingType.DEXTERITY);
        assertTrue(ring.use(player));
        // change the weapon currently in use, so i expect the dexterity ring is now applied to arrow 
        player.getEquipment().setWeapon(new BaseWeapon(WeaponType.ARROW));
        assertEquals(new IncreaseAccuracy(new BaseWeapon(WeaponType.ARROW)), player.getEquipment().getWeapon());
        assertEquals(new ArmorImpl(ArmorType.LEATHER), player.getEquipment().getArmor());
        ring.stopUsing(player);
        assertEquals(new BaseWeapon(WeaponType.ARROW), player.getEquipment().getWeapon());
        assertEquals(new ArmorImpl(ArmorType.LEATHER), player.getEquipment().getArmor());
    }

    @Test
    public void testChangeWeapon1() {
        // Improves armor AC
        final Ring ring = new RingImpl(RingType.PROTECTION);
        assertTrue(ring.use(player));
        assertEquals(new BaseWeapon(WeaponType.MACE), player.getEquipment().getWeapon());
        assertEquals(LEATHER_AC - 2, player.getEquipment().getArmor().getAC());
        // change the weapon does't affect the ring behavior
        player.getEquipment().setWeapon(new BaseWeapon(WeaponType.ARROW));
        assertEquals(new BaseWeapon(WeaponType.ARROW), player.getEquipment().getWeapon());
        assertEquals(LEATHER_AC - 2, player.getEquipment().getArmor().getAC());
        ring.stopUsing(player);
        assertEquals(LEATHER_AC, player.getEquipment().getArmor().getAC());
    }

    @Test
    public void testChangeArmor() {
        // Improves weapon accuracy
        final Ring ring = new RingImpl(RingType.DEXTERITY);
        assertTrue(ring.use(player));
        // change the armor -> does not produce any effects!
        player.getEquipment().setArmor(new ArmorImpl(ArmorType.CHAIN_MAIL));
        assertEquals(new IncreaseAccuracy(new BaseWeapon(WeaponType.MACE)), player.getEquipment().getWeapon());
        assertEquals(new ArmorImpl(ArmorType.CHAIN_MAIL), player.getEquipment().getArmor());
        ring.stopUsing(player);
        assertEquals(new BaseWeapon(WeaponType.MACE), player.getEquipment().getWeapon());
        assertEquals(new ArmorImpl(ArmorType.CHAIN_MAIL), player.getEquipment().getArmor());
    }

    @Test
    public void testChangeArmor1() {
        // Improves armor AC
        final Ring ring = new RingImpl(RingType.PROTECTION);
        assertTrue(ring.use(player));
        assertEquals(LEATHER_AC - 2, player.getEquipment().getArmor().getAC());
        // change the armor currently in use, so i expect the protection ring is now applied to the new armor 
        player.getEquipment().setArmor(new ArmorImpl(ArmorType.CHAIN_MAIL));
        assertEquals(CHAIN_MAIL_AC - 2, player.getEquipment().getArmor().getAC());
    }

    @Test
    public void testReplace() {
        final Ring ring = new RingImpl(RingType.DEXTERITY);
        assertTrue(ring.use(player));
        assertEquals(new IncreaseAccuracy(new BaseWeapon(WeaponType.MACE)), player.getEquipment().getWeapon());
        assertEquals(new ArmorImpl(ArmorType.LEATHER), player.getEquipment().getArmor());
        // create a new increase damage ring
        final Ring ring2 = new RingImpl(RingType.INCREASE_DAMAGE);
        // the dexterity ring is already applied. Is impossible to wear a new ring until first detaching the dexterity one.
        assertFalse(ring2.use(player));
        // Indeed all remained the same
        assertEquals(new IncreaseAccuracy(new BaseWeapon(WeaponType.MACE)), player.getEquipment().getWeapon());
        assertEquals(new ArmorImpl(ArmorType.LEATHER), player.getEquipment().getArmor());
        // detaching the dexterity ring
        ring.stopUsing(player);
        // now the increase damage ring can be worn
        assertTrue(ring2.use(player));
        assertEquals(new IncreaseDamage(new BaseWeapon(WeaponType.MACE)), player.getEquipment().getWeapon());
        assertEquals(new ArmorImpl(ArmorType.LEATHER), player.getEquipment().getArmor());
    }

    @Test
    public void anotherTest() {
        final Ring ring = new RingImpl(RingType.DEXTERITY);
        player.getEquipment().setWeapon(new BaseWeapon(WeaponType.SHURIKEN));
        player.getEquipment().setArmor(new ArmorImpl(ArmorType.SPLINT_MAIL));
        assertTrue(ring.use(player));
        assertEquals(new IncreaseAccuracy(new BaseWeapon(WeaponType.SHURIKEN)), player.getEquipment().getWeapon());
        assertEquals(new ArmorImpl(ArmorType.SPLINT_MAIL), player.getEquipment().getArmor());
        player.getEquipment().setWeapon(new BaseWeapon(WeaponType.SHORT_BOW));
        player.getEquipment().setWeapon(new BaseWeapon(WeaponType.DAGGER));
        assertEquals(new IncreaseAccuracy(new BaseWeapon(WeaponType.DAGGER)), player.getEquipment().getWeapon());
        player.getEquipment().setArmor(new ArmorImpl(ArmorType.PLATE_MAIL));
        assertEquals(new ArmorImpl(ArmorType.PLATE_MAIL), player.getEquipment().getArmor());
        ring.stopUsing(player);
        assertEquals(new ArmorImpl(ArmorType.PLATE_MAIL), player.getEquipment().getArmor());
        assertEquals(new BaseWeapon(WeaponType.DAGGER), player.getEquipment().getWeapon());
    }

}
