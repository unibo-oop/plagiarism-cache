package powpaw.weapon.view.impl;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import powpaw.map.model.impl.BlockImpl;
import powpaw.weapon.model.api.Weapon;
import powpaw.weapon.model.impl.WeaponImpl;
import powpaw.weapon.view.api.WeaponRender;

/**
 * Class that implement WeaponRender interface and is responsible for rendering
 * the weapon sprite image based on the current
 * weapon and its position.
 * 
 * @author Giacomo Grassetti
 */
public final class WeaponRenderImpl implements WeaponRender {

    private Weapon weapon;
    private List<BlockImpl> terrains;
    private final ImageView weaponSprite = new ImageView();

    /**
     * Setter for the terrains.
     * 
     * @param terrains An ArrayList of BlockImpl representing the terrains in a
     *                 game.
     */
    @Override
    public void setTerrains(final List<BlockImpl> terrains) {
        this.terrains = terrains;
    }

    @Override
    public List<BlockImpl> getTerrains() {
        return this.terrains;
    }

    @Override
    public ImageView getWeaponSprite() {
        return this.weaponSprite;
    }

    @Override
    public void setWeapon(final Weapon weapon) {
        this.weapon = weapon;
        setSpriteImage();
    }

    @Override
    public void render() {
        this.weaponSprite.setLayoutX(weapon.getPosition().getX());
        this.weaponSprite.setLayoutY(weapon.getPosition().getY());
        this.weaponSprite.setFitWidth(WeaponImpl.WIDTH);
        this.weaponSprite.setFitHeight(WeaponImpl.HEIGHT);
        setSpriteImage();
        this.weaponSprite.setVisible(this.weapon.isVisible());
    }

    /**
     * Setter for image of a weapon sprite based on the weapon ID.
     */
    private void setSpriteImage() {
        final Image image = this.weapon.getId() == 0 ? new Image("/swordPlayer.png")
                : new Image("/hammerPlayer.png");
        this.weaponSprite.setImage(image);
    }

}
