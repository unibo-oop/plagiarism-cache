package rogue.model.creature;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents an enumeration for declaring monster types.
 * 
 * The first field keeps track of the {@link MonsterLife}. which contains its health points and how much experience he gives once defeated. 
 * The second field is the armor class of the monster.
 * The third field contains the minimum and maximum damage the monster can do.
 * The fourth field contains {@link Special} that the monster possesses. 
 * The fifth field contains the coins that the monster drops when it dies.
 * The sixth field contains the {@link PotionType} the monster drops when it dies.
 * The seventh field contains the probability that the object will be dropped.
 */

import javafx.util.Pair;
import rogue.model.items.potion.Potion;
import rogue.model.items.potion.PotionImpl;
import rogue.model.items.potion.PotionType;

public enum MonsterType {

   /** 
    * Air Elemental.
    */
    AIR_ELEMENTAL(new MonsterLife(ThreadLocalRandom.current().nextInt(4, 32 - 4), 20), 2, new Pair<>(4, 24),
            new SpecialImpl(true, true, false, false, false, false), 0, new PotionImpl(PotionType.POTION_III), 20),

   /** 
    * Bat. 
    */
    BAT(new MonsterLife(ThreadLocalRandom.current().nextInt(1, 8 - 1), 1), 3, new Pair<>(1, 2),
            new SpecialImpl(false, true, false, true, false, false), 0),

   /** 
    * Centaur.
    */
    CENTAUR(new MonsterLife(ThreadLocalRandom.current().nextInt(4, 32 - 4), 17), 4, new Pair<>(4, 32), new SpecialImpl(), 51, new PotionImpl(PotionType.POTION_III), 10),

   /** 
    * Dragon.
    */
    DRAGON(new MonsterLife(ThreadLocalRandom.current().nextInt(10, 80 - 10), 5000), -1, new Pair<>(4, 40), new SpecialImpl(true), 15_000, new PotionImpl(PotionType.POTION_V), 100),

   /** 
    * Emu. 
    */
    EMU(new MonsterLife(ThreadLocalRandom.current().nextInt(1, 8 - 1), 2), 7, new Pair<>(1, 2), new SpecialImpl(true), 0),

   /** 
    * Fire Elemental. 
    */
    FIRE_ELEMENTAL(new MonsterLife(ThreadLocalRandom.current().nextInt(8, 36 - 8), 80), 2, new Pair<>(3, 18), new SpecialImpl(true), 0, new PotionImpl(PotionType.POTION_III), 40),

   /** 
    * Griffin.
    */
    GRIFFIN(new MonsterLife(ThreadLocalRandom.current().nextInt(13, 104 - 13), 2000), 2, new Pair<>(5, 25),
             new SpecialImpl(true, true, false, false, false, false), 6000, new PotionImpl(PotionType.POTION_IV), 80),

   /** 
    * Hobgoblin. 
    */
    HOBGOBLIN(new MonsterLife(ThreadLocalRandom.current().nextInt(1, 8 - 1), 3), 5, new Pair<>(1, 8), new SpecialImpl(true), 9, new PotionImpl(PotionType.POTION_I), 30),

   /** 
    * Ice cube.
    */
    ICE_CUBE(new MonsterLife(ThreadLocalRandom.current().nextInt(1, 8 - 1), 5), 9, new Pair<>(2, 12), new SpecialImpl(), 15, new PotionImpl(PotionType.POTION_II), 60),

   /** 
    * Jabberwock. 
    */
    JABBERWOCK(new MonsterLife(ThreadLocalRandom.current().nextInt(15, 120 - 15), 3000), 6,  new Pair<>(3, 24),
            new SpecialImpl(false, true, false, false, false, false), 9000, new PotionImpl(PotionType.POTION_V), 60),

   /** 
    * Kobold.
    */
    KOBOLD(new MonsterLife(ThreadLocalRandom.current().nextInt(1, 8 - 1), 1), 6,  new Pair<>(2, 12), new SpecialImpl(true), 3),

   /** 
    * Lycanthrope. 
    */
    LYCANTHROPE(new MonsterLife(ThreadLocalRandom.current().nextInt(2, 16 - 2), 10), 3,  new Pair<>(2, 12), new SpecialImpl(true), 30, new PotionImpl(PotionType.CORRUPT_POTION_I), 30),

   /** 
    * Medusa. 
    */
    MEDUSA(new MonsterLife(ThreadLocalRandom.current().nextInt(8, 64 - 8), 200), 2,  new Pair<>(4, 32),
            new SpecialImpl(true, false, false, false, true, false), 600, new PotionImpl(PotionType.CORRUPT_POTION_I), 70),

   /** 
    * Nymph. 
    */
    NYMPH(new MonsterLife(ThreadLocalRandom.current().nextInt(3, 24 - 3), 37), 9,  new Pair<>(0, 0),
            new SpecialImpl(false, false, true, false, false, false), 111,  new PotionImpl(PotionType.POTION_V), 50),

   /** 
    * Orc.
    */
    ORC(new MonsterLife(ThreadLocalRandom.current().nextInt(1, 8 - 1), 5), 6,  new Pair<>(1, 8),
            new SpecialImpl(false, false, true, false, false, false), 500),

   /** 
    * Phanthom. 
    */
    PHANTHOM(new MonsterLife(ThreadLocalRandom.current().nextInt(8, 64 - 8), 120), 3,  new Pair<>(4, 16),
            new SpecialImpl(true, false, false, false, false, false), 0),

   /** 
    * Quaqqa.
    */
    QUAQQA(new MonsterLife(ThreadLocalRandom.current().nextInt(3, 24 - 3), 15), 3,  new Pair<>(2, 10),
            new SpecialImpl(), 0),

   /** 
    * Ratfolk.
    */
    RATFOLK(new MonsterLife(ThreadLocalRandom.current().nextInt(2, 16 - 2), 9), 3,  new Pair<>(1, 6), new SpecialImpl(), 27, new PotionImpl(PotionType.POTION_II), 60),

   /** 
    * Snake.
    */
    SNAKE(new MonsterLife(ThreadLocalRandom.current().nextInt(1, 8 - 1), 2), 5,  new Pair<>(1, 3),
            new SpecialImpl(true, false, false, false, true, false), 6),

   /** 
    * Troll. 
    */
    TROLL(new MonsterLife(ThreadLocalRandom.current().nextInt(6, 48 - 6), 120), 4,  new Pair<>(3, 24),
            new SpecialImpl(true), 360,  new PotionImpl(PotionType.POTION_IV), 70),

   /** 
    * Unicorn.
    */
    UNICORN(new MonsterLife(ThreadLocalRandom.current().nextInt(7, 56 - 7), 190), 3,  new Pair<>(4, 36),
            new SpecialImpl(), 570,  new PotionImpl(PotionType.CORRUPT_POTION_II), 100),

   /** 
    * Vampire.
    */ 
    VAMPIRE(new MonsterLife(ThreadLocalRandom.current().nextInt(8, 64 - 8), 350), 1,  new Pair<>(1, 10),
           new SpecialImpl(true, false, false, false, false, true), 1050,  new PotionImpl(PotionType.POTION_IV), 100),

   /** 
    * Wraith. 
    */
    WRAITH(new MonsterLife(ThreadLocalRandom.current().nextInt(5, 40 - 5), 55), 4,  new Pair<>(1, 6), new SpecialImpl(), 0,  new PotionImpl(PotionType.CORRUPT_POTION_I), 60),

   /** 
    * Xill. 
    */
    XILL(new MonsterLife(ThreadLocalRandom.current().nextInt(8, 72 - 8), 50), 2,  new Pair<>(3, 24),
            new SpecialImpl(true), 150,  new PotionImpl(PotionType.POTION_IV), 20),

   /** 
    * Yeti. 
    */
    YETI(new MonsterLife(ThreadLocalRandom.current().nextInt(4, 32 - 4), 100), 6,  new Pair<>(2, 12),
           new SpecialImpl(), 900,  new PotionImpl(PotionType.POTION_III), 40),

   /** 
    * Zombie.
    */
    ZOMBIE(new MonsterLife(ThreadLocalRandom.current().nextInt(3, 24 - 3), 6), 8,  new Pair<>(2, 16),
            new SpecialImpl(true, false, false, false, false, false), 16,  new PotionImpl(PotionType.CORRUPT_POTION_II), 50);


    private final MonsterLife life;
    private final int ac;
    private final Pair<Integer, Integer> damage;
    private final Special special;
    private final int money;
    private final Potion item;
    private final int itemChange;

    MonsterType(final MonsterLife life, final int ac, final Pair<Integer, Integer> damage, final SpecialImpl special, final int money,
        final Potion item, final int itemChange) {
        this.life = life;
        this.ac = ac;
        this.damage = damage;
        this.special = special;
        this.money = money;
        this.item = item;
        this.itemChange = itemChange;
    }

    MonsterType(final MonsterLife life, final int ac, final Pair<Integer, Integer> damage, final SpecialImpl special, final int money) {
        this.life = life;
        this.ac = ac;
        this.damage = damage;
        this.special = special;
        this.money = money;
        this.item = null;
        this.itemChange = 0;
    }

    protected MonsterLife getLife() {
        return this.life;
    }

    protected int getAC() {
        return this.ac;
    }

    protected Pair<Integer, Integer> getDamage() {
        return this.damage;
    }

    protected Special getSpecial() {
        return this.special;
    }

    protected int getMoney() {
        return this.money;
    }

    protected Potion getItem() {
        return this.item;
    }

    protected int getItemChange() {
        return this.itemChange;
    }

}
