package powpaw.weapon.controller.impl;

import java.util.Optional;
import java.util.Random;

import powpaw.player.controller.api.PlayerController;
import powpaw.player.model.api.Player;
import powpaw.weapon.controller.api.WeaponController;
import powpaw.weapon.model.api.Weapon;
import powpaw.weapon.model.impl.WeaponFactory;
import powpaw.weapon.view.api.WeaponRender;
import powpaw.weapon.view.impl.WeaponRenderImpl;

/**
 * Class WeaponControllerImpl class that controls the spawning, picking up, and
 * dropping of weapons for players in a game.
 * 
 * @author Giacomo Grassetti
 */
public final class WeaponControllerImpl implements WeaponController {

    private Weapon weapon;
    private final WeaponRender weaponRender;
    private final PlayerController playerController;
    private final Random rand = new Random();

    /**
     * This is a constructor for the WeaponControllerImp class that initializes the
     * playerController field with
     * the passed object, creating a new WeaponRender object. Spawn automatically
     * the first weapon.
     * 
     * @param playerController
     */
    public WeaponControllerImpl(final PlayerController playerController) {
        this.playerController = playerController;
        weaponRender = new WeaponRenderImpl();
        spownWeapons();
    }

    @Override
    public void pickWeapon() {
        playerController.getPlayerObservable().getPlayers().forEach(player -> {
            if (player.getHitbox().checkCollision(weapon.getHitbox().getShape())) {
                setWeaponToPlayer(player);
            }
            dropWeapon(player);
        });
    }

    /**
     * Method that sets a weapon to a player if the it doesn't already have a weapon
     * and the
     * weapon has not been picked up yet.
     * 
     * @param player Player in the game.
     * 
     */
    private void setWeaponToPlayer(final Player player) {
        if (player.getWeapon().isEmpty() && !this.weapon.isPicked()) {
            this.weapon.setPicked(true);
            player.setWeapon(Optional.of(weapon));
            player.increaseArmHitbox();
            this.weapon.addAttack(player.getPlayerStats());
            this.weapon.setVisible(false);
            this.weaponRender.getWeaponSprite().setVisible(false);
        }
    }

    /**
     * The function drops a player's weapon if it has no durability left and spawns
     * new weapons.
     * 
     * @param player Player in the game.
     */
    private void dropWeapon(final Player player) {
        if (player.getWeapon().isPresent() && player.getWeapon().get().getDurability() == 0) {
            player.setWeapon(Optional.empty());
            player.reduceArmHitbox();
            spownWeapons();
        }
    }

    /**
     * Method that spawns a new weapon and sets its visibility to true.
     */
    private void spownWeapons() {
        this.createNewWeapon();
        weapon.setPicked(false);
        weapon.setVisible(true);
        weaponRender.getWeaponSprite().setVisible(true);

    }

    /**
     * Method that creates a new weapon using a random index and sets it to the
     * weaponRender.
     */
    private void createNewWeapon() {
        final int weaponIndex = rand.nextInt(2);
        this.weapon = WeaponFactory.createWeapon(weaponIndex);
        this.weaponRender.setWeapon(weapon);
    }

    @Override
    public Weapon getWeapon() {
        return this.weapon;
    }

    @Override
    public WeaponRender getRender() {
        return this.weaponRender;
    }

}
