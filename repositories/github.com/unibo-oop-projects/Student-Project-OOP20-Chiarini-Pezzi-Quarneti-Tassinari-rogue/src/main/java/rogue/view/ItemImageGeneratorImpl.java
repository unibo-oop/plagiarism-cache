package rogue.view;

import javafx.scene.image.Image;
import rogue.model.items.Item;
import rogue.model.items.armor.Armor;
import rogue.model.items.armor.ArmorType;
import rogue.model.items.food.Food;
import rogue.model.items.food.FoodType;
import rogue.model.items.potion.Potion;
import rogue.model.items.rings.Ring;
import rogue.model.items.scroll.Scroll;
import rogue.model.items.weapons.BaseWeapon;
import rogue.model.items.weapons.WeaponType;

/**
 * An implementation for an {@link ItemImageGenerator}.
 *
 */
public class ItemImageGeneratorImpl implements ItemImageGenerator {

    /**
     * Get the corresponding of the given item.
     * @param item to get image of.
     * @return the image of the requested item.
     */
    public Image getImage(final Item item) {
        if (item instanceof Food) {
            /*
             * Food item
             */
            /*
             * BREAD
             */
            if (((Food) item).getFood() == FoodType.BREAD) {
                return new Image(ClassLoader.getSystemResource("images/food/breadIcon.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * CAKE
             */
            if (((Food) item).getFood() == FoodType.CAKE) {
                return new Image(ClassLoader.getSystemResource("images/food/cakeIcon.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * SOUP
             */
            if (((Food) item).getFood() == FoodType.SOUP) {
                return new Image(ClassLoader.getSystemResource("images/food/soupIcon.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * HAMBURGER
             */
            if (((Food) item).getFood() == FoodType.HAMBURGER) {
                return new Image(ClassLoader.getSystemResource("images/food/hamburgerIcon.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * CHEESE
             */
            if (((Food) item).getFood() == FoodType.CHEESE) {
                return new Image(ClassLoader.getSystemResource("images/food/cheeseIcon.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * STEAK
             */
            if (((Food) item).getFood() == FoodType.STEAK) {
                return new Image(ClassLoader.getSystemResource("images/food/steakIcon.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * APPLE
             */
            if (((Food) item).getFood() == FoodType.APPLE) {
                return new Image(ClassLoader.getSystemResource("images/food/appleIcon.png").toExternalForm(), 32, 32, false, true);
            }
        }
        if (item instanceof Potion) {
            /*
             * Potion item
             */
            /*
             * Check for max potion
             */
            if (((Potion) item).getHpValue() == 100) {
                return new Image(ClassLoader.getSystemResource("images/potion/potionMaxIcon.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * if not max health return the normal potion image
             */
            return new Image(ClassLoader.getSystemResource("images/potion/potionIcon.png").toExternalForm(), 32, 32, false, true);
        }
        if (item instanceof Scroll) {
            /*
             * Scroll item, all scrolls have the same icon
             */
            return new Image(ClassLoader.getSystemResource("images/scroll/scrollIcon.png").toExternalForm(), 32, 32, false, true);
        }
        if (item instanceof Armor) {
            /*
             * Armor item
             */
            /*
             * Leather Armor
             */
           if (((Armor) item).getArmorType().name().equals(ArmorType.LEATHER.name())) {
                 return new Image(ClassLoader.getSystemResource("images/armor/Leather_Armor.png").toExternalForm(), 32, 32, false, true);
             }
           /*
            * Ring Mail
            */
            if (((Armor) item).getArmorType().name().equals(ArmorType.RING_MAIL.name())) {
                return new Image(ClassLoader.getSystemResource("images/armor/Ring_Mail.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Studded Leather
             */
            if (((Armor) item).getArmorType().name().equals(ArmorType.STUDDED_LEATHER.name())) {
                return new Image(ClassLoader.getSystemResource("images/armor/Studded_Leather.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Scale Mail
             */
            if (((Armor) item).getArmorType().name().equals(ArmorType.SCALE_MAIL.name())) {
                return new Image(ClassLoader.getSystemResource("images/armor/Scale_Mail.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Chain Mail
             */
            if (((Armor) item).getArmorType().name().equals(ArmorType.CHAIN_MAIL.name())) {
                return new Image(ClassLoader.getSystemResource("images/armor/Chain_Mail.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Splint Mail
             */
            if (((Armor) item).getArmorType().name().equals(ArmorType.SPLINT_MAIL.name())) {
                return new Image(ClassLoader.getSystemResource("images/armor/Splint_Mail.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Banded Mail
             */
            if (((Armor) item).getArmorType().name().equals(ArmorType.BANDED_MAIL.name())) {
                return new Image(ClassLoader.getSystemResource("images/armor/Banded_Mail.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Plate Mail
             */
            if (((Armor) item).getArmorType().name().equals(ArmorType.PLATE_MAIL.name())) {
                return new Image(ClassLoader.getSystemResource("images/armor/Plate_Mail.png").toExternalForm(), 32, 32, false, true);
            }
        }
        if (item instanceof BaseWeapon) {
            /*
             * Weapon item
             */
            /*
             * Mace
             */
            if (((BaseWeapon) item).toString().equals(new BaseWeapon(WeaponType.MACE).toString())) {
                return new Image(ClassLoader.getSystemResource("images/weapon/Mace.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Long Sword
             */
            if (((BaseWeapon) item).toString().equals(new BaseWeapon(WeaponType.LONG_SWORD).toString())) {
                return new Image(ClassLoader.getSystemResource("images/weapon/Long_Sword.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Short Bow
             */
            if (((BaseWeapon) item).toString().equals(new BaseWeapon(WeaponType.SHORT_BOW).toString())) {
                return new Image(ClassLoader.getSystemResource("images/weapon/Bow.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Arrow
             */
            if (((BaseWeapon) item).toString().equals(new BaseWeapon(WeaponType.ARROW).toString())) {
                return new Image(ClassLoader.getSystemResource("images/weapon/Arrow.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Dagger
             */
            if (((BaseWeapon) item).toString().equals(new BaseWeapon(WeaponType.DAGGER).toString())) {
                return new Image(ClassLoader.getSystemResource("images/weapon/Dagger.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Two Hand Sword
             */
            if (((BaseWeapon) item).toString().equals(new BaseWeapon(WeaponType.TWO_HAND_SWORD).toString())) {
                return new Image(ClassLoader.getSystemResource("images/weapon/Two_Hand_Sword.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Dart
             */
            if (((BaseWeapon) item).toString().equals(new BaseWeapon(WeaponType.DART).toString())) {
                return new Image(ClassLoader.getSystemResource("images/weapon/Dart.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Crossbow
             */
            if (((BaseWeapon) item).toString().equals(new BaseWeapon(WeaponType.CROSSBOW).toString())) {
                return new Image(ClassLoader.getSystemResource("images/weapon/Crossbow.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Shuriken
             */
            if (((BaseWeapon) item).toString().equals(new BaseWeapon(WeaponType.SHURIKEN).toString())) {
                return new Image(ClassLoader.getSystemResource("images/weapon/Shuriken.png").toExternalForm(), 32, 32, false, true);
            }
            /*
             * Spear
             */
            if (((BaseWeapon) item).toString().equals(new BaseWeapon(WeaponType.SPEAR).toString())) {
                return new Image(ClassLoader.getSystemResource("images/weapon/Spear.png").toExternalForm(), 32, 32, false, true);
            }

        }
        if (item instanceof Ring) {
            /*
             * Ring item
             */
            return new Image(ClassLoader.getSystemResource("images/ring/ringIcon.png").toExternalForm(), 32, 32, false, true);
        }
        return new Image(ClassLoader.getSystemResource("images/emptyIcon.png").toExternalForm(), 32, 32, false, true);
    }
}
