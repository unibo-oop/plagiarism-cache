package fargoal.view.impl;

import java.awt.Color;
import java.awt.Font;

import fargoal.model.events.api.FloorEvent;
import fargoal.model.events.api.FloorEventListener;
import fargoal.model.events.impl.BattleEvent;
import fargoal.model.events.impl.BlessedEvent;
import fargoal.model.events.impl.FoundTrapEvent;
import fargoal.model.events.impl.MonsterEncounterEvent;
import fargoal.model.events.impl.MonsterStealGoldEvent;
import fargoal.model.events.impl.MonsterStealSpellEvent;
import fargoal.model.events.impl.PickUpGoldEvent;
import fargoal.model.events.impl.PickUpNewItemEvent;
import fargoal.model.events.impl.PickUpSword;
import fargoal.model.events.impl.PlacedABeacon;
import fargoal.model.events.impl.PlayerActionEvent;
import fargoal.model.events.impl.ReceiveAttackEvent;
import fargoal.model.events.impl.TurnLightOffEvent;
import fargoal.model.events.impl.TurnLightOnEvent;
import fargoal.model.events.impl.WalkOverEvent;
import fargoal.view.api.Renderer;
import fargoal.view.api.View;

/**
 * Class that work to display to screen the events that
 * happens to the player.
 */
public class RenderEventListener implements FloorEventListener, Renderer {
    private static final int FONT_DIVISOR_HEIGHT = 110;
    private static final int DIVISOR_WIDTH = 50;
    private static final int DIVISOR_HEIGHT = 55;
    private static final int MULTIPLIER_HEIGHT = 12;

    private String text;
    private final SwingRendererTop renderer;

    /**
     * Constructor to create the renderer for the events and set
     * the local field {@link #text}.
     * 
     * @param view - the general view of the game
     */
    public RenderEventListener(final View view) {
        this.text = " ";
        final SwingView swing = (SwingView) view;
        renderer = new SwingRendererTop(g2d -> {
            g2d.setFont(new Font("Arial", Font.BOLD, swing.getFrame().getBounds().height * 3 / FONT_DIVISOR_HEIGHT));
            g2d.setColor(Color.WHITE);
            g2d.drawString(this.text, 
                    swing.getEventPanel().getBounds().width / DIVISOR_WIDTH,
                    swing.getEventPanel().getBounds().height * MULTIPLIER_HEIGHT / DIVISOR_HEIGHT); 
        }, view);
    }

    /** {@inheritDoc} */
    @Override
    public void notifyEvent(final FloorEvent floorEvent) {
        if (floorEvent instanceof MonsterEncounterEvent) {
            final MonsterEncounterEvent ev = (MonsterEncounterEvent) floorEvent;
            text = "A " + ev.monsterEncountered().getTag();
        } else if (floorEvent instanceof FoundTrapEvent) {
            final FoundTrapEvent ev = (FoundTrapEvent) floorEvent;
            final String temp;
            temp = ev.typeOfTrap().getChestItemName();
            if (ev.hasLostMap()) {
                text = temp + ", the map has been lost!";
            } else {
                text = temp;
            }
        } else if (floorEvent instanceof PickUpGoldEvent) {
            final PickUpGoldEvent ev = (PickUpGoldEvent) floorEvent;
            text = "Found " + ev.goldFound() + " gold";
        } else if (floorEvent instanceof PickUpNewItemEvent) {
            final PickUpNewItemEvent ev = (PickUpNewItemEvent) floorEvent;
            text = "Found " + ev.pickedUpWhat().getChestItemName();
        } else if (floorEvent instanceof ReceiveAttackEvent) {
            final ReceiveAttackEvent ev = (ReceiveAttackEvent) floorEvent;
            text = "Attacked by " + ev.attackedFrom().getTag();
        } else if (floorEvent instanceof WalkOverEvent) {
            final WalkOverEvent ev = (WalkOverEvent) floorEvent;
            text = ev.getOnWhat().getTag();
        } else if (floorEvent instanceof MonsterStealSpellEvent) {
            final MonsterStealSpellEvent ev = (MonsterStealSpellEvent) floorEvent;
            text = ev.whoStole().getTag() + " stole " + ev.whatMonsterStole().getChestItemName();
        } else if (floorEvent instanceof MonsterStealGoldEvent) {
            final MonsterStealGoldEvent ev = (MonsterStealGoldEvent) floorEvent;
            text = ev.whoStole().getTag() + " stole " + ev.howMuchGold() + " gold coins";
        } else if (floorEvent instanceof PlayerActionEvent) {
            final PlayerActionEvent ev = (PlayerActionEvent) floorEvent;
            text = ev.whatPlayerUsed().getChestItemName() + " has been used";
        } else if (floorEvent instanceof PlacedABeacon) {
            final PlacedABeacon ev = (PlacedABeacon) floorEvent;
            text = ev.getWhatPlaced().getTag() + " placed";
        } else if (floorEvent instanceof PickUpSword) {
            final PickUpSword ev = (PickUpSword) floorEvent;
            text = ev.pickedUp().getTag() + " has finally been found!";
        } else if (floorEvent instanceof BattleEvent) {
            final BattleEvent ev = (BattleEvent) floorEvent;
            text = ev.getTextSound();
        } else if (floorEvent instanceof BlessedEvent) {
            final BlessedEvent ev = (BlessedEvent) floorEvent;
            text = ev.getBlessed();
        } else if (floorEvent instanceof TurnLightOnEvent) {
            final TurnLightOnEvent ev = (TurnLightOnEvent) floorEvent;
            text = ev.getTurnLightOn();
        } else if (floorEvent instanceof TurnLightOffEvent) {
            final TurnLightOffEvent ev = (TurnLightOffEvent) floorEvent;
            text = ev.getLightTurnOff();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void render() {
        this.renderer.render();
    }
}
