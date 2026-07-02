package rogue.view;

import static rogue.model.creature.PlayerAttribute.HP;
import static rogue.model.creature.PlayerAttribute.MAX_HP;
import static rogue.model.creature.PlayerAttribute.COINS;
import static rogue.model.creature.PlayerAttribute.LEVEL;
import static rogue.model.creature.PlayerAttribute.STRENGTH;
import static rogue.model.creature.PlayerAttribute.EXPERIENCE;
import static rogue.model.creature.PlayerAttribute.FOOD;
import static rogue.model.creature.PlayerAttribute.WEAPON;
import static rogue.model.creature.PlayerAttribute.ARMOR;


import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import rogue.model.creature.PlayerAttribute;
import rogue.model.items.armor.Armor;
import rogue.model.items.weapons.Weapon;

/**
 * A simple class which controls the status bar where are displayed 
 * the player's life statistics.
 */
public final class StatusBarViewImpl implements StatusBarView {

    private final Map<PlayerAttribute, String> labelsMap = new EnumMap<>(PlayerAttribute.class);

    {
        labelsMap.put(MAX_HP, "#maxHp");
        labelsMap.put(HP, "#hp");
        labelsMap.put(COINS, "#gold");
        labelsMap.put(LEVEL, "#level");
        labelsMap.put(STRENGTH, "#strength");
        labelsMap.put(EXPERIENCE, "#experience");
        labelsMap.put(ARMOR, "#armor");
        labelsMap.put(WEAPON, "#weapon");
        labelsMap.put(FOOD, "#food");
    };

    private Parent root;
    private Scene scene;

    public StatusBarViewImpl() {
        try {
            this.root = FXMLLoader.load(ClassLoader.getSystemResource("layout/StatusBar.fxml"));
            this.scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateLabel(final PlayerAttribute attribute, final String value) {
        final var lblName = labelsMap.entrySet().stream()
            .filter(e -> e.getKey().equals(attribute))
            .map(e -> e.getValue())
            .findAny().get();
        final Label lbl = (Label) this.scene.lookup(lblName);
        lbl.setText(value);
    }

    @Override
    public void setLifeLabel(final PlayerAttribute attribute, final int value) {
        this.updateLabel(attribute, Integer.toString(value));
    }

    @Override
    public void setWeaponLabel(final Weapon weapon) {
        this.updateLabel(WEAPON, weapon.toString());
    }

    @Override
    public void setArmorLabel(final Armor armor) {
        this.updateLabel(ARMOR, armor.toString());
    }

    @Override
    public Node getNode() {
        return this.root;
    }

}
