package testpackage;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javarogue.behaviourmodules.StatNames;
import javarogue.generation.LevelFactory;
import javarogue.generation.LevelFactoryImpl;
import javarogue.objects.GameObject;
import javarogue.objects.ObjectFactory;
import javarogue.objects.ObjectType;
import javarogue.playablecharacter.Inventory;
import javarogue.playablecharacter.InventoryImpl;
import javarogue.playablecharacter.PlayableCharacter;

public class InventoryTest {

	private static final int LEVEL_DEPTH = 5;
	private static final int HP_WITH_POTION = 110;
	
	private final Inventory inventoryTest = new InventoryImpl();
	private final ObjectFactory objectFactory = new ObjectFactory();
	
	@Test
	public void testInventoryInit() {
		LevelFactory fac = new LevelFactoryImpl(100);
		this.inventoryTest.addCharacter(new PlayableCharacter(fac.generate(LEVEL_DEPTH)));
		assertTrue(this.inventoryTest.getInventory().size() == 0);
		assertTrue(this.inventoryTest.getCharacter().getInventoryList().size() == 0);
	}
	
	@Test
	public void testPickUp() {
		LevelFactory fac = new LevelFactoryImpl(100);
		this.inventoryTest.addCharacter(new PlayableCharacter(fac.generate(LEVEL_DEPTH)));
		final GameObject objectOne = this.objectFactory.CreateObject(ObjectType.HEALTH_POTION, null);
		this.inventoryTest.loot(objectOne);
		final GameObject objectTwo = this.objectFactory.CreateObject(ObjectType.HEALTH_POTION, null);
		this.inventoryTest.loot(objectTwo);
		assertTrue(this.inventoryTest.getInventory().size() == 2);	
		assertTrue(this.inventoryTest.getCharacter().getInventoryList().size() == 2);
	}
	
	@Test
	public void testRemoval() {
		LevelFactory fac = new LevelFactoryImpl(100);
		this.inventoryTest.addCharacter(new PlayableCharacter(fac.generate(LEVEL_DEPTH)));
		final GameObject potion = this.objectFactory.CreateObject(ObjectType.HEALTH_POTION, null);
		this.inventoryTest.loot(potion);
		this.inventoryTest.equip(potion);
		assertTrue(this.inventoryTest.getInventory().isEmpty());
		assertTrue(this.inventoryTest.getCharacter().getInventoryList().isEmpty());
		assertTrue(this.inventoryTest.getCharacter().getStatistics().get(StatNames.HP) == HP_WITH_POTION);
	}
	
}
