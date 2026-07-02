package zombietsunami;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import zombietsunami.model.MapData;
import zombietsunami.model.mapmodel.api.GameMap;
import zombietsunami.model.mapmodel.impl.GameMapImpl;
import zombietsunami.model.personmodel.api.PersonsManager;
import zombietsunami.model.personmodel.impl.PersonsManagerImpl;
import zombietsunami.model.personmodel.impl.PersonImpl;
import zombietsunami.model.zombiemodel.api.Zombie;
import zombietsunami.model.zombiemodel.impl.ZombieImpl;

/**
 * Class that tests the funcionalities of PersonManager.
 */
class TestPersonManager {

    private final PersonsManager personsManager = new PersonsManagerImpl();
    private final GameMap gameMap = new GameMapImpl();
    private final Zombie zombie = new ZombieImpl();

    /**
     * Checks if the list of Person are setted.
     */
    @Test
    void checkPersonListSetted() {
        personsManager.setPersonFromMap(gameMap.getLoadedPersonList(),
                gameMap.getScreenTilePos(MapData.getMaxWorldRow(), MapData.getMaxWorldCol(),
                        MapData.getTitSize(), zombie.getX(), zombie.getY(),
                        this.zombie.getScreenX(), this.zombie.getScreenY()),
                zombie.getStrength());

        assertNotEquals(personsManager.getPersonList().size(), 0);
    }

    /**
     * Tests if the Person are added correctly to the list.
     */
    @Test
    void testAddPerson() {
        final int personListSize = personsManager.getPersonList().size();
        personsManager.addPerson(new PersonImpl());

        assertNotEquals(personListSize, personsManager.getPersonList());
    }

    /**
     * Tests if the Person are removed correctly from the list.
     */
    @Test
    void testRemovePerson() {
        personsManager.addPerson(new PersonImpl());
        personsManager.removePersonFromList(0);
        assertEquals(personsManager.getPersonList().get(0), null);
    }

    /**
     * Tests if GetPersonList() return correctly.
     */
    @Test
    void testGetPersonList() {
        assertNotEquals(personsManager.getPersonList(), null);
    }
}
