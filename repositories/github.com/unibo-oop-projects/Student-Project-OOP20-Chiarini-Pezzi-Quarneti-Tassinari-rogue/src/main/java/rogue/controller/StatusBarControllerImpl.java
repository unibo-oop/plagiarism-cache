package rogue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;

import rogue.model.creature.Player;
import rogue.model.creature.PlayerLife;
import rogue.model.events.EquipmentEvent;
import rogue.model.events.EventSubscriber;
import rogue.model.events.LifeEvent;
import rogue.view.StatusBarView;
import rogue.view.StatusBarViewImpl;

/**
 * The player controller.
 */
public final class StatusBarControllerImpl implements StatusBarController, EventSubscriber {

    private static final Logger LOG = LoggerFactory.getLogger(StatusBarControllerImpl.class);
    private final StatusBarView view;

    /**
     * Creates a new PlayerController.
     * @param player
     *          the {@link Player} entity
     */
    public StatusBarControllerImpl(final Player player) {
        this.view = new StatusBarViewImpl();
        player.getLife().register(this);
        player.getEquipment().register(this);
    }

    @Subscribe
    public void onLifeChange(final LifeEvent<PlayerLife> event) {
        LOG.info("Life changed " + event);
        event.getChanged().forEach(c -> view.setLifeLabel(c.getKey(), c.getValue()));
    }

    @Subscribe
    public void onEquipmentChange(final EquipmentEvent event) {
        LOG.info("Equipment changed " + event);
        view.setArmorLabel(event.getEquipment().getArmor());
        view.setWeaponLabel(event.getEquipment().getWeapon());
    }

    @Override
    public StatusBarView getView() {
        return this.view;
    }

}
