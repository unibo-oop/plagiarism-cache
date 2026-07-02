package fargoal.view.impl;

import java.awt.Color;
import java.awt.Font;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import fargoal.model.entity.player.api.Inventory;
import fargoal.model.interactable.pickupable.inside_chest.spell.api.SpellType;
import fargoal.view.api.Renderer;
import fargoal.view.api.View;

/**
 * Class that work to render infos about the Player's inventory.
 */
public class InventoryInformationRenderer implements Renderer {

    private static final int DIVISOR_WIDTH_SECOND_COLUMN = 16;
    private static final int CONSTANT_SIX = 6;
    private static final int CONSTANT_SEVEN = 7;
    private static final int CONSTANT_FIVE = 5;
    private static final int DIVISOR_WIDTH_VALUES = 32;
    private static final int DIVISOR_HEIGHT_LAST_COLUMN = 24;
    private static final int DIVISOR_WIDTH_LAST_COLUMN = 4;
    private static final int CONSTANT_TWENTYTHREE = 23;
    private static final int MULTIPLIER_WIDTH_LAST_COLUMN_VALUES = 31;
    private static final int MULTIPLIER_VALUE_TELEPORT = 11;
    private static final int MULTIPLIER_VALUE_SHIELD = 15;
    private static final int MULTIPLIER_VALUE_LIGHT = 19;
    private static final int FONT_HEIGHT_DIVISOR = 10;

    private SwingRendererBottom renderer;
    private final SwingView view;

    /**
     * Constructor that assigns to the local field view the
     * principal view value of the game.
     * 
     * @param view - the view of the game
     * @param inventory - the inventory this renderer takes information from
     */
    @SuppressFBWarnings(
        value = {"EI_EXPOSE_REP2"},
        justification = "The class needs to work on the same view as the one given"
            + "so if the one given changes the reference also needs to change"
    )
    public InventoryInformationRenderer(final View view, final Inventory inventory) {
        this.view = (SwingView) view;
        this.setRenderer(inventory);
    }

    /** {@inheritDoc} */
    @Override
    public void render() {
        renderer.render();
    }

    private void setRenderer(final Inventory inventory) {
        renderer = new SwingRendererBottom(g2d -> {
            g2d.setFont(new Font("Arial", 
                        Font.BOLD, 
                        this.view.getInformationPanel().getBounds().height * 1 / FONT_HEIGHT_DIVISOR));
            g2d.setColor(Color.WHITE);
            g2d.drawString("ENCHANTED SWORD+",
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_SECOND_COLUMN,
                    this.view.getInformationPanel().getBounds().height / 3);
            g2d.drawString(String.valueOf(inventory.numberEnchantedWeapons()), 
                    this.view.getInformationPanel().getBounds().width 
                                * CONSTANT_SEVEN 
                                / DIVISOR_WIDTH_SECOND_COLUMN, 
                    this.view.getInformationPanel().getBounds().height / 3);
            g2d.drawString("BEACONS ",
                    this.view.getInformationPanel().getBounds().width / 2,
                    this.view.getInformationPanel().getBounds().height / CONSTANT_SIX);
            g2d.drawString(String.valueOf(inventory.numberBeacons()), 
                    this.view.getInformationPanel().getBounds().width 
                                * CONSTANT_TWENTYTHREE 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height / CONSTANT_SIX);
            g2d.drawString("MAGIC SACKS ",
                    this.view.getInformationPanel().getBounds().width / 2,
                    this.view.getInformationPanel().getBounds().height / 2);
            g2d.drawString(String.valueOf(inventory.numberMagicSacks()), 
                    this.view.getInformationPanel().getBounds().width 
                                * CONSTANT_TWENTYTHREE 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height / 2);
            g2d.drawString("HEALING POTIONS ",
                    this.view.getInformationPanel().getBounds().width / 2,
                    this.view.getInformationPanel().getBounds().height 
                                * CONSTANT_FIVE 
                                / CONSTANT_SIX);
            g2d.drawString(String.valueOf(inventory.numberHealingPotions()), 
                    this.view.getInformationPanel().getBounds().width 
                                * CONSTANT_TWENTYTHREE 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height 
                                * CONSTANT_FIVE 
                                / CONSTANT_SIX);
            if (inventory.numberInvisibilitySpells() > 0 
                        && !inventory.getSpellCasted().get(SpellType.INVISIBILITY.getName())) {
                g2d.setColor(Color.WHITE);
            } else if (inventory.getSpellCasted().get(SpellType.INVISIBILITY.getName())) {
                g2d.setColor(Color.CYAN);
            } else if (inventory.numberInvisibilitySpells() <= 0 
                        && !inventory.getSpellCasted().get(SpellType.INVISIBILITY.getName())) {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawString("INVISIBILITY", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_LAST_COLUMN, 
                    this.view.getInformationPanel().getBounds().height * 3 / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.drawString(String.valueOf(inventory.numberInvisibilitySpells()),
                    this.view.getInformationPanel().getBounds().width 
                                * MULTIPLIER_WIDTH_LAST_COLUMN_VALUES 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height * 3 / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.setColor(Color.BLACK);
            if (inventory.numberRegenerationSpell() > 0 
                        && !inventory.getSpellCasted().get(SpellType.REGENERATION.getName())) {
                g2d.setColor(Color.WHITE);
            } else if (inventory.getSpellCasted().get(SpellType.REGENERATION.getName())) {
                g2d.setColor(Color.CYAN);
            } else if (inventory.numberRegenerationSpell() <= 0 
                        && !inventory.getSpellCasted().get(SpellType.REGENERATION.getName())) {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawString("REGENERATION", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_LAST_COLUMN, 
                    this.view.getInformationPanel().getBounds().height 
                                * CONSTANT_SEVEN 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.drawString(String.valueOf(inventory.numberRegenerationSpell()),
                    this.view.getInformationPanel().getBounds().width 
                                * MULTIPLIER_WIDTH_LAST_COLUMN_VALUES 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height 
                                * CONSTANT_SEVEN 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.setColor(Color.BLACK);
            if (inventory.numberTeleportSpells() > 0) {
                g2d.setColor(Color.WHITE);
            } else {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawString("TELEPORT", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_LAST_COLUMN, 
                    this.view.getInformationPanel().getBounds().height 
                                * MULTIPLIER_VALUE_TELEPORT 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.drawString(String.valueOf(inventory.numberTeleportSpells()),
                    this.view.getInformationPanel().getBounds().width 
                                * MULTIPLIER_WIDTH_LAST_COLUMN_VALUES 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height 
                                * MULTIPLIER_VALUE_TELEPORT 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.setColor(Color.BLACK);
            if (inventory.numberShieldSpells() > 0 
                        && !inventory.getSpellCasted().get(SpellType.SHIELD.getName())) {
                g2d.setColor(Color.WHITE);
            } else if (inventory.getSpellCasted().get(SpellType.SHIELD.getName())) {
                g2d.setColor(Color.CYAN);
            } else if (inventory.numberShieldSpells() <= 0 
                        && !inventory.getSpellCasted().get(SpellType.SHIELD.getName())) {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawString("SHIELD", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_LAST_COLUMN, 
                    this.view.getInformationPanel().getBounds().height 
                                * MULTIPLIER_VALUE_SHIELD 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.drawString(String.valueOf(inventory.numberShieldSpells()),
                    this.view.getInformationPanel().getBounds().width 
                                * MULTIPLIER_WIDTH_LAST_COLUMN_VALUES 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height 
                                * MULTIPLIER_VALUE_SHIELD 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.setColor(Color.BLACK);
            if (inventory.numberLightSpells() 
                        > 0 && !inventory.getSpellCasted().get(SpellType.LIGHT.getName())) {
                g2d.setColor(Color.WHITE);
            } else if (inventory.getSpellCasted().get(SpellType.LIGHT.getName())) {
                g2d.setColor(Color.CYAN);
            } else if (inventory.numberLightSpells() <= 0 
                        && !inventory.getSpellCasted().get(SpellType.LIGHT.getName())) {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawString("LIGHT", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_LAST_COLUMN, 
                    this.view.getInformationPanel().getBounds().height 
                                * MULTIPLIER_VALUE_LIGHT 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.drawString(String.valueOf(inventory.numberLightSpells()),
                    this.view.getInformationPanel().getBounds().width 
                                * MULTIPLIER_WIDTH_LAST_COLUMN_VALUES 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height 
                                * MULTIPLIER_VALUE_LIGHT 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.setColor(Color.BLACK);
            if (inventory.numberDriftSpells() > 0 
                        && !inventory.getSpellCasted().get(SpellType.DRIFT.getName())) {
                g2d.setColor(Color.WHITE);
            } else if (inventory.getSpellCasted().get(SpellType.DRIFT.getName())) {
                g2d.setColor(Color.CYAN);
            } else if (inventory.numberDriftSpells() <= 0 
                        && !inventory.getSpellCasted().get(SpellType.DRIFT.getName())) {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawString("DRIFT", 
                    this.view.getInformationPanel().getBounds().width * 3 / DIVISOR_WIDTH_LAST_COLUMN, 
                    this.view.getInformationPanel().getBounds().height 
                                * CONSTANT_TWENTYTHREE 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
            g2d.drawString(String.valueOf(inventory.numberDriftSpells()),
                    this.view.getInformationPanel().getBounds().width 
                                * MULTIPLIER_WIDTH_LAST_COLUMN_VALUES 
                                / DIVISOR_WIDTH_VALUES, 
                    this.view.getInformationPanel().getBounds().height 
                                * CONSTANT_TWENTYTHREE 
                                / DIVISOR_HEIGHT_LAST_COLUMN);
        }, view);
    } 
}
