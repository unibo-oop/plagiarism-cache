package hotelmaster.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import hotelmaster.test.typesetmap.Animal;
import hotelmaster.test.typesetmap.BigCat;
import hotelmaster.test.typesetmap.Cat;
import hotelmaster.test.typesetmap.Dog;
import hotelmaster.utility.collections.TypeSetMap;

/**
 * Tests for TypeSetMap.
 */
public class TypeSetMapTest {

    /**
     * Adding tests.
     */
    @org.junit.Test
    public void adding() {
        final TypeSetMap<Animal> instance = TypeSetMap.create();
        assertTrue(instance.add(new Dog("mastiff", true)));
        assertTrue(instance.add(new Cat("persian")));
        assertTrue(instance.add(new Dog("chihuahua", false)));
        assertFalse(instance.add(new Dog("chihuahua", false)));
        assertTrue(instance.add(new Dog("pug", false)));
        assertTrue(instance.add(new BigCat("panther")));
    }

    /**
     * Removal tests.
     * 
     */
    @org.junit.Test
    public void removing() {
        final TypeSetMap<Animal> instance = TypeSetMap.create();
        assertFalse(instance.remove(new Cat("chartreux")));
        final Dog dog = new Dog("chihuahua", false);
        instance.add(dog);
        assertTrue(instance.remove(dog));
    }

    /**
     * Content tests.
     */
    @org.junit.Test
    public void content() {
        final Dog some = new Dog("mastiff", true);
        final Dog other = new Dog("pug", false);
        final TypeSetMap<Animal> instance = TypeSetMap.create();
        instance.add(some);
        instance.add(other);
        final Set<Dog> dogs = new HashSet<>();
        dogs.add(some);
        dogs.add(other);
        assertEquals(dogs, instance.get(Dog.class));
        final Set<Animal> animals = new HashSet<>();
        animals.add(some);
        final Cat another = new Cat("chartreux");
        animals.add(another);
        instance.add(another);
        assertTrue(instance.containsAll(animals));
    }

    /**
     * Reference tests.
     */
    @org.junit.Test
    public void references() {
        final TypeSetMap<Animal> instance = TypeSetMap.create();
        final Set<Dog> dogs = instance.get(Dog.class);
        final Dog dog = new Dog("golden retriever", true);
        dogs.add(dog);
        assertTrue(dogs.contains(dog));
        instance.remove(dog);
        assertFalse(dogs.contains(dog));
    }
}
