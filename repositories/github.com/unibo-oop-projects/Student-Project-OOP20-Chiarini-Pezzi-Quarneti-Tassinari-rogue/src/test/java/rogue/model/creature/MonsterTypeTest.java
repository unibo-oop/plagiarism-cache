package rogue.model.creature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MonsterTypeTest {

    private static final int IPOTETICAL_DAMAGE = 999;

    private static final int AIR_ELEMENTAL_EXPERIENCE = 20;
    private static final int AIR_ELEMENTAL_MONEY = 0;
    private static final int AIR_ELEMENTAL_ITEM_CHANGE = 20;
    private static final int AIR_ELEMENTAL_HP_MIN = 4;
    private static final int AIR_ELEMENTAL_HP_MAX = 32;
    private static final int AIR_ELEMENTAL_DAMAGE_MIN = 4;
    private static final int AIR_ELEMENTAL_DAMAGE_MAX = 24;
    private static final int AIR_ELEMENTAL_AC = 2;

    private static final int BAT_EXPERIENCE = 1;
    private static final int BAT_MONEY = 0;
    private static final int BAT_ITEM_CHANGE = 0;
    private static final int BAT_HP_MIN = 1;
    private static final int BAT_HP_MAX = 8;
    private static final int BAT_DAMAGE_MIN = 1;
    private static final int BAT_DAMAGE_MAX = 2;
    private static final int BAT_AC = 3;

    private static final int CENTAUR_EXPERIENCE = 17;
    private static final int CENTAUR_MONEY = 51;
    private static final int CENTAUR_ITEM_CHANGE = 10;
    private static final int CENTAUR_HP_MIN = 4;
    private static final int CENTAUR_HP_MAX = 32;
    private static final int CENTAUR_DAMAGE_MIN = 4;
    private static final int CENTAUR_DAMAGE_MAX = 32;
    private static final int CENTAUR_AC = 4;

    private static final int DRAGON_EXPERIENCE = 5000;
    private static final int DRAGON_MONEY = 15_000;
    private static final int DRAGON_ITEM_CHANGE = 100;
    private static final int DRAGON_HP_MIN = 10;
    private static final int DRAGON_HP_MAX = 100;
    private static final int DRAGON_DAMAGE_MIN = 4;
    private static final int DRAGON_DAMAGE_MAX = 40;
    private static final int DRAGON_AC = -1;

    private static final int EMU_EXPERIENCE = 2;
    private static final int EMU_MONEY = 0;
    private static final int EMU_ITEM_CHANGE = 0;
    private static final int EMU_HP_MIN = 1;
    private static final int EMU_HP_MAX = 8;
    private static final int EMU_DAMAGE_MIN = 1;
    private static final int EMU_DAMAGE_MAX = 2;
    private static final int EMU_AC = 7;

    private static final int FIRE_ELEMENTAL_EXPERIENCE = 80;
    private static final int FIRE_ELEMENTAL_MONEY = 0;
    private static final int FIRE_ELEMENTAL_ITEM_CHANGE = 40;
    private static final int FIRE_ELEMENTAL_HP_MIN = 8;
    private static final int FIRE_ELEMENTAL_HP_MAX = 64;
    private static final int FIRE_ELEMENTAL_DAMAGE_MIN = 3;
    private static final int FIRE_ELEMENTAL_DAMAGE_MAX = 18;
    private static final int FIRE_ELEMENTAL_AC = 2;

    private static final int GRIFFIN_EXPERIENCE = 2000;
    private static final int GRIFFIN_MONEY = 6000;
    private static final int GRIFFIN_ITEM_CHANGE = 80;
    private static final int GRIFFIN_HP_MIN = 13;
    private static final int GRIFFIN_HP_MAX = 104;
    private static final int GRIFFIN_DAMAGE_MIN = 5;
    private static final int GRIFFIN_DAMAGE_MAX = 25;
    private static final int GRIFFIN_AC = 2;

    private static final int HOBGOBLIN_EXPERIENCE = 3;
    private static final int HOBGOBLIN_MONEY = 9;
    private static final int HOBGOBLIN_ITEM_CHANGE = 30;
    private static final int HOBGOBLIN_HP_MIN = 1;
    private static final int HOBGOBLIN_HP_MAX = 8;
    private static final int HOBGOBLIN_DAMAGE_MIN = 1;
    private static final int HOBGOBLIN_DAMAGE_MAX = 8;
    private static final int HOBGOBLIN_AC = 5;

    private static final int ICE_CUBE_EXPERIENCE = 5;
    private static final int ICE_CUBE_MONEY = 15;
    private static final int ICE_CUBE_ITEM_CHANGE = 60;
    private static final int ICE_CUBE_HP_MIN = 1;
    private static final int ICE_CUBE_HP_MAX = 8;
    private static final int ICE_CUBE_DAMAGE_MIN = 2;
    private static final int ICE_CUBE_DAMAGE_MAX = 12;
    private static final int ICE_CUBE_AC = 9;

    private static final int JABBERWOCK_EXPERIENCE = 3000;
    private static final int JABBERWOCK_MONEY = 9000;
    private static final int JABBERWOCK_ITEM_CHANGE = 60;
    private static final int JABBERWOCK_HP_MIN = 15;
    private static final int JABBERWOCK_HP_MAX = 120;
    private static final int JABBERWOCK_DAMAGE_MIN = 3;
    private static final int JABBERWOCK_DAMAGE_MAX = 24;
    private static final int JABBERWOCK_AC = 6;

    private static final int KOBOLD_EXPERIENCE = 1;
    private static final int KOBOLD_MONEY = 3;
    private static final int KOBOLD_ITEM_CHANGE = 0;
    private static final int KOBOLD_HP_MIN = 1;
    private static final int KOBOLD_HP_MAX = 16;
    private static final int KOBOLD_DAMAGE_MIN = 2;
    private static final int KOBOLD_DAMAGE_MAX = 12;
    private static final int KOBOLD_AC = 6;

    private static final int LYCANTHROPE_EXPERIENCE = 10;
    private static final int LYCANTHROPE_MONEY = 30;
    private static final int LYCANTHROPE_ITEM_CHANGE = 30;
    private static final int LYCANTHROPE_HP_MIN = 2;
    private static final int LYCANTHROPE_HP_MAX = 16;
    private static final int LYCANTHROPE_DAMAGE_MIN = 2;
    private static final int LYCANTHROPE_DAMAGE_MAX = 12;
    private static final int LYCANTHROPE_AC = 3;

    private static final int MEDUSA_EXPERIENCE = 200;
    private static final int MEDUSA_MONEY = 600;
    private static final int MEDUSA_ITEM_CHANGE = 70;
    private static final int MEDUSA_HP_MIN = 8;
    private static final int MEDUSA_HP_MAX = 64;
    private static final int MEDUSA_DAMAGE_MIN = 4;
    private static final int MEDUSA_DAMAGE_MAX = 32;
    private static final int MEDUSA_AC = 2;

    private static final int NYMPH_EXPERIENCE = 37;
    private static final int NYMPH_MONEY = 111;
    private static final int NYMPH_ITEM_CHANGE = 50;
    private static final int NYMPH_HP_MIN = 3;
    private static final int NYMPH_HP_MAX = 24;
    private static final int NYMPH_DAMAGE_MIN = 0;
    private static final int NYMPH_DAMAGE_MAX = 0;
    private static final int NYMPH_AC = 9;

    private static final int ORC_EXPERIENCE = 5;
    private static final int ORC_MONEY = 500;
    private static final int ORC_ITEM_CHANGE = 0;
    private static final int ORC_HP_MIN = 1;
    private static final int ORC_HP_MAX = 8;
    private static final int ORC_DAMAGE_MIN = 1;
    private static final int ORC_DAMAGE_MAX = 8;
    private static final int ORC_AC = 6;

    private static final int PHANTHOM_EXPERIENCE = 120;
    private static final int PHANTHOM_MONEY = 0;
    private static final int PHANTHOM_ITEM_CHANGE = 0;
    private static final int PHANTHOM_HP_MIN = 8;
    private static final int PHANTHOM_HP_MAX = 64;
    private static final int PHANTHOM_DAMAGE_MIN = 4;
    private static final int PHANTHOM_DAMAGE_MAX = 16;
    private static final int PHANTHOM_AC = 3;

    private static final int QUAQQA_EXPERIENCE = 15;
    private static final int QUAQQA_MONEY = 0;
    private static final int QUAQQA_ITEM_CHANGE = 0;
    private static final int QUAQQA_HP_MIN = 3;
    private static final int QUAQQA_HP_MAX = 24;
    private static final int QUAQQA_DAMAGE_MIN = 2;
    private static final int QUAQQA_DAMAGE_MAX = 10;
    private static final int QUAQQA_AC = 3;

    private static final int RATFOLK_EXPERIENCE = 9;
    private static final int RATFOLK_MONEY = 27;
    private static final int RATFOLK_ITEM_CHANGE = 60;
    private static final int RATFOLK_HP_MIN = 2;
    private static final int RATFOLK_HP_MAX = 16;
    private static final int RATFOLK_DAMAGE_MIN = 1;
    private static final int RATFOLK_DAMAGE_MAX = 6;
    private static final int RATFOLK_AC = 3;

    private static final int SNAKE_EXPERIENCE = 2;
    private static final int SNAKE_MONEY = 6;
    private static final int SNAKE_ITEM_CHANGE = 0;
    private static final int SNAKE_HP_MIN = 1;
    private static final int SNAKE_HP_MAX = 8;
    private static final int SNAKE_DAMAGE_MIN = 1;
    private static final int SNAKE_DAMAGE_MAX = 3;
    private static final int SNAKE_AC = 5;

    private static final int TROLL_EXPERIENCE = 120;
    private static final int TROLL_MONEY = 360;
    private static final int TROLL_ITEM_CHANGE = 70;
    private static final int TROLL_HP_MIN = 6;
    private static final int TROLL_HP_MAX = 48;
    private static final int TROLL_DAMAGE_MIN = 3;
    private static final int TROLL_DAMAGE_MAX = 24;
    private static final int TROLL_AC = 4;

    private static final int UNICORN_EXPERIENCE = 190;
    private static final int UNICORN_MONEY = 570;
    private static final int UNICORN_ITEM_CHANGE = 100;
    private static final int UNICORN_HP_MIN = 7;
    private static final int UNICORN_HP_MAX = 56;
    private static final int UNICORN_DAMAGE_MIN = 4;
    private static final int UNICORN_DAMAGE_MAX = 36;
    private static final int UNICORN_AC = 3;

    private static final int VAMPIRE_EXPERIENCE = 350;
    private static final int VAMPIRE_MONEY = 1050;
    private static final int VAMPIRE_ITEM_CHANGE = 100;
    private static final int VAMPIRE_HP_MIN = 8;
    private static final int VAMPIRE_HP_MAX = 64;
    private static final int VAMPIRE_DAMAGE_MIN = 1;
    private static final int VAMPIRE_DAMAGE_MAX = 10;
    private static final int VAMPIRE_AC = 1;

    private static final int WRAITH_EXPERIENCE = 55;
    private static final int WRAITH_MONEY = 0;
    private static final int WRAITH_ITEM_CHANGE = 60;
    private static final int WRAITH_HP_MIN = 5;
    private static final int WRAITH_HP_MAX = 40;
    private static final int WRAITH_DAMAGE_MIN = 1;
    private static final int WRAITH_DAMAGE_MAX = 6;
    private static final int WRAITH_AC = 4;

    private static final int XILL_EXPERIENCE = 50;
    private static final int XILL_MONEY = 150;
    private static final int XILL_ITEM_CHANGE = 20;
    private static final int XILL_HP_MIN = 9;
    private static final int XILL_HP_MAX = 72;
    private static final int XILL_DAMAGE_MIN = 3;
    private static final int XILL_DAMAGE_MAX = 24;
    private static final int XILL_AC = 2;

    private static final int YETI_EXPERIENCE = 100;
    private static final int YETI_MONEY = 900;
    private static final int YETI_ITEM_CHANGE = 40;
    private static final int YETI_HP_MIN = 4;
    private static final int YETI_HP_MAX = 32;
    private static final int YETI_DAMAGE_MIN = 2;
    private static final int YETI_DAMAGE_MAX = 12;
    private static final int YETI_AC = 6;

    private static final int ZOMBIE_EXPERIENCE = 6;
    private static final int ZOMBIE_MONEY = 16;
    private static final int ZOMBIE_ITEM_CHANGE = 50;
    private static final int ZOMBIE_HP_MIN = 3;
    private static final int ZOMBIE_HP_MAX = 24;
    private static final int ZOMBIE_DAMAGE_MIN = 2;
    private static final int ZOMBIE_DAMAGE_MAX = 16;
    private static final int ZOMBIE_AC = 8;

    @org.junit.Test
    public void testAirElemental() {
        // with default configs
        final MonsterImpl mon = new MonsterImpl(MonsterType.AIR_ELEMENTAL);

        assertEquals(AIR_ELEMENTAL_EXPERIENCE, mon.getLife().getExperience());
        assertEquals(AIR_ELEMENTAL_MONEY, mon.getMoney());
        //assertEquals("POTION_II", mon.getItem().g);
        assertEquals(AIR_ELEMENTAL_ITEM_CHANGE, mon.getItemChange());
        assertTrue(AIR_ELEMENTAL_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= AIR_ELEMENTAL_HP_MAX);
        assertTrue(AIR_ELEMENTAL_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= AIR_ELEMENTAL_DAMAGE_MAX);
        assertEquals(AIR_ELEMENTAL_AC, mon.getAC());

        assertFalse(mon.getSpecial().isDrainLife());
        assertFalse(mon.getSpecial().isFlyingRandom());
        assertTrue(mon.getSpecial().isFlying());
        assertFalse(mon.getSpecial().isGreedy());
        assertTrue(mon.getSpecial().isHostile());
        assertFalse(mon.getSpecial().isPoisonous());

        mon.getLife().hurt(IPOTETICAL_DAMAGE);
        assertTrue(mon.getLife().isDead());
    }

    @org.junit.Test
    public void testBat() {
        // with default configs
        final MonsterImpl mon = new MonsterImpl(MonsterType.BAT);

        assertEquals(BAT_EXPERIENCE, mon.getLife().getExperience());
        assertEquals(BAT_MONEY, mon.getMoney());
        assertEquals(BAT_ITEM_CHANGE, mon.getItemChange());
        assertTrue(BAT_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= BAT_HP_MAX);
        assertTrue(BAT_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= BAT_DAMAGE_MAX);
        assertEquals(BAT_AC, mon.getAC());

        assertFalse(mon.getSpecial().isDrainLife());
        assertTrue(mon.getSpecial().isFlyingRandom());
        assertTrue(mon.getSpecial().isFlying());
        assertFalse(mon.getSpecial().isGreedy());
        assertFalse(mon.getSpecial().isHostile());
        assertFalse(mon.getSpecial().isPoisonous());

        mon.getLife().hurt(IPOTETICAL_DAMAGE);
        assertTrue(mon.getLife().isDead());
    }

    @org.junit.Test
    public void testCentaur() {
        // with default configs
        final MonsterImpl mon = new MonsterImpl(MonsterType.CENTAUR);

        assertEquals(CENTAUR_EXPERIENCE, mon.getLife().getExperience());
        assertEquals(CENTAUR_MONEY, mon.getMoney());
        assertEquals(CENTAUR_ITEM_CHANGE, mon.getItemChange());
        assertTrue(CENTAUR_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= CENTAUR_HP_MAX);
        assertTrue(CENTAUR_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= CENTAUR_DAMAGE_MAX);
        assertEquals(CENTAUR_AC, mon.getAC());

        assertFalse(mon.getSpecial().isDrainLife());
        assertFalse(mon.getSpecial().isFlyingRandom());
        assertFalse(mon.getSpecial().isFlying());
        assertFalse(mon.getSpecial().isGreedy());
        assertFalse(mon.getSpecial().isHostile());
        assertFalse(mon.getSpecial().isPoisonous());

        mon.getLife().hurt(IPOTETICAL_DAMAGE);
        assertTrue(mon.getLife().isDead());
    }

    @org.junit.Test
    public void testDragon() {
        // with default configs
        final MonsterImpl mon = new MonsterImpl(MonsterType.DRAGON);

        assertEquals(DRAGON_EXPERIENCE, mon.getLife().getExperience());
        assertEquals(DRAGON_MONEY, mon.getMoney());
        assertEquals(DRAGON_ITEM_CHANGE, mon.getItemChange());
        assertTrue(DRAGON_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= DRAGON_HP_MAX);
        assertTrue(DRAGON_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= DRAGON_DAMAGE_MAX);
        assertEquals(DRAGON_AC, mon.getAC());

        assertFalse(mon.getSpecial().isDrainLife());
        assertFalse(mon.getSpecial().isFlyingRandom());
        assertFalse(mon.getSpecial().isFlying());
        assertFalse(mon.getSpecial().isGreedy());
        assertTrue(mon.getSpecial().isHostile());
        assertFalse(mon.getSpecial().isPoisonous());
 
        mon.getLife().hurt(IPOTETICAL_DAMAGE);
        assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testEmu() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.EMU);

            assertEquals(EMU_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(EMU_MONEY, mon.getMoney());
            assertEquals(EMU_ITEM_CHANGE, mon.getItemChange());
            assertTrue(EMU_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= EMU_HP_MAX);
            assertTrue(EMU_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= EMU_DAMAGE_MAX);
            assertEquals(EMU_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertTrue(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testFireElemental() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.FIRE_ELEMENTAL);

            assertEquals(FIRE_ELEMENTAL_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(FIRE_ELEMENTAL_MONEY, mon.getMoney());
            assertEquals(FIRE_ELEMENTAL_ITEM_CHANGE, mon.getItemChange());
            assertTrue(FIRE_ELEMENTAL_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= FIRE_ELEMENTAL_HP_MAX);
            assertTrue(FIRE_ELEMENTAL_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= FIRE_ELEMENTAL_DAMAGE_MAX);
            assertEquals(FIRE_ELEMENTAL_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertTrue(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());


            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testGriffin() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.GRIFFIN);

            assertEquals(GRIFFIN_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(GRIFFIN_MONEY, mon.getMoney());
            assertEquals(GRIFFIN_ITEM_CHANGE, mon.getItemChange());
            assertTrue(GRIFFIN_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= GRIFFIN_HP_MAX);
            assertTrue(GRIFFIN_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= GRIFFIN_DAMAGE_MAX);
            assertEquals(GRIFFIN_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertTrue(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertTrue(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testHobgoglin() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.HOBGOBLIN);

            assertEquals(HOBGOBLIN_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(HOBGOBLIN_MONEY, mon.getMoney());
            assertEquals(HOBGOBLIN_ITEM_CHANGE, mon.getItemChange());
            assertTrue(HOBGOBLIN_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= HOBGOBLIN_HP_MAX);
            assertTrue(HOBGOBLIN_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= HOBGOBLIN_DAMAGE_MAX);
            assertEquals(HOBGOBLIN_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertTrue(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testIceCube() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.ICE_CUBE);

            assertEquals(ICE_CUBE_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(ICE_CUBE_MONEY, mon.getMoney());
            assertEquals(ICE_CUBE_ITEM_CHANGE, mon.getItemChange());
            assertTrue(ICE_CUBE_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= ICE_CUBE_HP_MAX);
            assertTrue(ICE_CUBE_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= ICE_CUBE_DAMAGE_MAX);
            assertEquals(ICE_CUBE_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertFalse(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testJabberwock() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.JABBERWOCK);

            assertEquals(JABBERWOCK_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(JABBERWOCK_MONEY, mon.getMoney());
            assertEquals(JABBERWOCK_ITEM_CHANGE, mon.getItemChange());
            assertTrue(JABBERWOCK_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= JABBERWOCK_HP_MAX);
            assertTrue(JABBERWOCK_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= JABBERWOCK_DAMAGE_MAX);
            assertEquals(JABBERWOCK_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertTrue(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertFalse(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testKobold() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.KOBOLD);

            assertEquals(KOBOLD_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(KOBOLD_MONEY, mon.getMoney());
            assertEquals(KOBOLD_ITEM_CHANGE, mon.getItemChange());
            assertTrue(KOBOLD_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= KOBOLD_HP_MAX);
            assertTrue(KOBOLD_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= KOBOLD_DAMAGE_MAX);
            assertEquals(KOBOLD_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertTrue(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testLycanthrope() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.LYCANTHROPE);

            assertEquals(LYCANTHROPE_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(LYCANTHROPE_MONEY, mon.getMoney());
            assertEquals(LYCANTHROPE_ITEM_CHANGE, mon.getItemChange());
            assertTrue(LYCANTHROPE_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= LYCANTHROPE_HP_MAX);
            assertTrue(LYCANTHROPE_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= LYCANTHROPE_DAMAGE_MAX);
            assertEquals(LYCANTHROPE_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertTrue(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testMedusa() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.MEDUSA);

            assertEquals(MEDUSA_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(MEDUSA_MONEY, mon.getMoney());
            assertEquals(MEDUSA_ITEM_CHANGE, mon.getItemChange());
            assertTrue(MEDUSA_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= MEDUSA_HP_MAX);
            assertTrue(MEDUSA_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= MEDUSA_DAMAGE_MAX);
            assertEquals(MEDUSA_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertTrue(mon.getSpecial().isHostile());
            assertTrue(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testNymph() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.NYMPH);

            assertEquals(NYMPH_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(NYMPH_MONEY, mon.getMoney());
            assertEquals(NYMPH_ITEM_CHANGE, mon.getItemChange());
            assertTrue(NYMPH_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= NYMPH_HP_MAX);
            assertTrue(NYMPH_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= NYMPH_DAMAGE_MAX);
            assertEquals(NYMPH_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertTrue(mon.getSpecial().isGreedy());
            assertFalse(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testOrc() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.ORC);

            assertEquals(ORC_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(ORC_MONEY, mon.getMoney());
            assertEquals(ORC_ITEM_CHANGE, mon.getItemChange());
            assertTrue(ORC_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= ORC_HP_MAX);
            assertTrue(ORC_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= ORC_DAMAGE_MAX);
            assertEquals(ORC_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertTrue(mon.getSpecial().isGreedy());
            assertFalse(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testPhanthom() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.PHANTHOM);

            assertEquals(PHANTHOM_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(PHANTHOM_MONEY, mon.getMoney());
            assertEquals(PHANTHOM_ITEM_CHANGE, mon.getItemChange());
            assertTrue(PHANTHOM_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= PHANTHOM_HP_MAX);
            assertTrue(PHANTHOM_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= PHANTHOM_DAMAGE_MAX);
            assertEquals(PHANTHOM_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertTrue(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testQuaqqa() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.QUAQQA);

            assertEquals(QUAQQA_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(QUAQQA_MONEY, mon.getMoney());
            assertEquals(QUAQQA_ITEM_CHANGE, mon.getItemChange());
            assertTrue(QUAQQA_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= QUAQQA_HP_MAX);
            assertTrue(QUAQQA_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= QUAQQA_DAMAGE_MAX);
            assertEquals(QUAQQA_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertFalse(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testRatfolk() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.RATFOLK);

            assertEquals(RATFOLK_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(RATFOLK_MONEY, mon.getMoney());
            assertEquals(RATFOLK_ITEM_CHANGE, mon.getItemChange());
            assertTrue(RATFOLK_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= RATFOLK_HP_MAX);
            assertTrue(RATFOLK_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= RATFOLK_DAMAGE_MAX);
            assertEquals(RATFOLK_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertFalse(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testSnake() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.SNAKE);

            assertEquals(SNAKE_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(SNAKE_MONEY, mon.getMoney());
            assertEquals(SNAKE_ITEM_CHANGE, mon.getItemChange());
            assertTrue(SNAKE_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= SNAKE_HP_MAX);
            assertTrue(SNAKE_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= SNAKE_DAMAGE_MAX);
            assertEquals(SNAKE_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertTrue(mon.getSpecial().isHostile());
            assertTrue(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testTroll() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.TROLL);

            assertEquals(TROLL_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(TROLL_MONEY, mon.getMoney());
            assertEquals(TROLL_ITEM_CHANGE, mon.getItemChange());
            assertTrue(TROLL_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= TROLL_HP_MAX);
            assertTrue(TROLL_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= TROLL_DAMAGE_MAX);
            assertEquals(TROLL_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertTrue(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testUnicorn() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.UNICORN);

            assertEquals(UNICORN_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(UNICORN_MONEY, mon.getMoney());
            assertEquals(UNICORN_ITEM_CHANGE, mon.getItemChange());
            assertTrue(UNICORN_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= UNICORN_HP_MAX);
            assertTrue(UNICORN_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= UNICORN_DAMAGE_MAX);
            assertEquals(UNICORN_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertFalse(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testVampire() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.VAMPIRE);

            assertEquals(VAMPIRE_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(VAMPIRE_MONEY, mon.getMoney());
            assertEquals(VAMPIRE_ITEM_CHANGE, mon.getItemChange());
            assertTrue(VAMPIRE_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= VAMPIRE_HP_MAX);
            assertTrue(VAMPIRE_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= VAMPIRE_DAMAGE_MAX);
            assertEquals(VAMPIRE_AC, mon.getAC());

            assertTrue(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertTrue(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testWraith() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.WRAITH);

            assertEquals(WRAITH_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(WRAITH_MONEY, mon.getMoney());
            assertEquals(WRAITH_ITEM_CHANGE, mon.getItemChange());
            assertTrue(WRAITH_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= WRAITH_HP_MAX);
            assertTrue(WRAITH_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= WRAITH_DAMAGE_MAX);
            assertEquals(WRAITH_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertFalse(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testXill() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.XILL);

            assertEquals(XILL_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(XILL_MONEY, mon.getMoney());
            assertEquals(XILL_ITEM_CHANGE, mon.getItemChange());
            assertTrue(XILL_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= XILL_HP_MAX);
            assertTrue(XILL_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= XILL_DAMAGE_MAX);
            assertEquals(XILL_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertTrue(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testYeti() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.YETI);

            assertEquals(YETI_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(YETI_MONEY, mon.getMoney());
            assertEquals(YETI_ITEM_CHANGE, mon.getItemChange());
            assertTrue(YETI_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= YETI_HP_MAX);
            assertTrue(YETI_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= YETI_DAMAGE_MAX);
            assertEquals(YETI_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertFalse(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }

        @org.junit.Test
        public void testZombie() {
            // with default configs
            final MonsterImpl mon = new MonsterImpl(MonsterType.ZOMBIE);

            assertEquals(ZOMBIE_EXPERIENCE, mon.getLife().getExperience());
            assertEquals(ZOMBIE_MONEY, mon.getMoney());
            assertEquals(ZOMBIE_ITEM_CHANGE, mon.getItemChange());
            assertTrue(ZOMBIE_HP_MIN <= mon.getLife().getHealthPoints() && mon.getLife().getHealthPoints() <= ZOMBIE_HP_MAX);
            assertTrue(ZOMBIE_DAMAGE_MIN <= mon.attackDamage() && mon.attackDamage() <= ZOMBIE_DAMAGE_MAX);
            assertEquals(ZOMBIE_AC, mon.getAC());

            assertFalse(mon.getSpecial().isDrainLife());
            assertFalse(mon.getSpecial().isFlyingRandom());
            assertFalse(mon.getSpecial().isFlying());
            assertFalse(mon.getSpecial().isGreedy());
            assertTrue(mon.getSpecial().isHostile());
            assertFalse(mon.getSpecial().isPoisonous());

            mon.getLife().hurt(IPOTETICAL_DAMAGE);
            assertTrue(mon.getLife().isDead());
    }
}
