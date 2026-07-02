package rogue.view;

import javafx.scene.image.Image;
import rogue.model.creature.Monster;
import rogue.model.creature.MonsterType;

public class MonsterImageGeneratorImpl implements MonsterImageGenerator {

    /**
     * @param monster
     *            the image of the monster 
     * @return the image of the requested item.
     */
    public Image getImage(final Monster monster) {
        /*
         * Monster Image
         */
        /*
         * Air Elemental
         */
        if (monster.getMonsterType().name().equals(MonsterType.AIR_ELEMENTAL.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Air_Elemental.png").toExternalForm(), 32, 32, false, true);
        }

        /*
         * Bat
         */
        if (monster.getMonsterType().name().equals(MonsterType.BAT.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Bat.png").toExternalForm(), 32, 32, false, true);
        }

        /*
         * Centaur
         */
        if (monster.getMonsterType().name().equals(MonsterType.CENTAUR.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Centaur.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Dragon
         */
        if (monster.getMonsterType().name().equals(MonsterType.DRAGON.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Dragon.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Emu
         */
        if (monster.getMonsterType().name().equals(MonsterType.EMU.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Emu.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Fire Elemental
         */
        if (monster.getMonsterType().name().equals(MonsterType.FIRE_ELEMENTAL.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Fire_Elemental.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Griffin
         */
        if (monster.getMonsterType().name().equals(MonsterType.GRIFFIN.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Griffin.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Hobgoglin
         */
        if (monster.getMonsterType().name().equals(MonsterType.HOBGOBLIN.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/HobGoblin.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Ice Cube
         */
        if (monster.getMonsterType().name().equals(MonsterType.ICE_CUBE.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Ice_Cube.png").toExternalForm(), 32, 32, false, true);
          }
        if (monster.getMonsterType().name().equals(MonsterType.JABBERWOCK.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Jabberwock.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Kobold
         */
        if (monster.getMonsterType().name().equals(MonsterType.KOBOLD.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Kobold.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Lycanthrope
         */
        if (monster.getMonsterType().name().equals(MonsterType.LYCANTHROPE.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Lycanthrope.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Medusa
         */
        if (monster.getMonsterType().name().equals(MonsterType.MEDUSA.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Medusa.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Nymph
         */
        if (monster.getMonsterType().name().equals(MonsterType.NYMPH.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Nymph.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Orc
         */
        if (monster.getMonsterType().name().equals(MonsterType.ORC.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Orc.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Quaqqa
         */
        if (monster.getMonsterType().name().equals(MonsterType.QUAQQA.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Quaqqa.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Ratfolk
         */
        if (monster.getMonsterType().name().equals(MonsterType.RATFOLK.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Ratfolk.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Snake
         */
        if (monster.getMonsterType().name().equals(MonsterType.SNAKE.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Snake.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Troll
         */
        if (monster.getMonsterType().name().equals(MonsterType.TROLL.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Troll.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Unicorn
         */
        if (monster.getMonsterType().name().equals(MonsterType.UNICORN.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Unicorn.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Vampire
         */
        if (monster.getMonsterType().name().equals(MonsterType.VAMPIRE.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Vampire.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Wraith
         */
        if (monster.getMonsterType().name().equals(MonsterType.WRAITH.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Wraith.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Xill
         */
        if (monster.getMonsterType().name().equals(MonsterType.XILL.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Xill.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Yeti
         */
        if (monster.getMonsterType().name().equals(MonsterType.YETI.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Yeti.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Zombie
         */
        if (monster.getMonsterType().name().equals(MonsterType.ZOMBIE.name())) {
            return new Image(ClassLoader.getSystemResource("images/monster/Zombie.png").toExternalForm(), 32, 32, false, true);
          }

        /*
         * Phanthom
         */
        return new Image(ClassLoader.getSystemResource("images/monster/Phantom.png").toExternalForm(), 32, 32, false, true);
    }
}
