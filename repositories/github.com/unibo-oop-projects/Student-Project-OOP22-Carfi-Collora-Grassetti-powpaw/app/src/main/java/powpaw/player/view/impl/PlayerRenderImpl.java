package powpaw.player.view.impl;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import powpaw.player.model.api.Player;
import powpaw.player.view.api.PlayerRender;

/**
 * A class that implements the {@code PlayerRender} interface for rendering a
 * player character in a game.
 * 
 * @author Alessia Carfì, Giacomo Grassetti
 */
public final class PlayerRenderImpl implements PlayerRender {

    private static final int ANGLE = 180;

    private final Image idleSprite;
    private final Image attackSprite;
    private final Image hitSprite;
    private final Image dodgeSprite;
    private final Image swordSprite;
    private final Image hammerSprite;
    private final ImageView sprite;
    private final ImageView armSprite;
    private final Player player;

    /**
     * Constructor for creating a new {@code PlayerRenderImpl} object with a given
     * player object.
     * 
     * @param player the player object to be rendered
     */
    public PlayerRenderImpl(final Player player) {
        final int playerNum = player.getNumber();
        this.player = player;
        this.idleSprite = new Image("p" + playerNum + "_idle.png");
        this.attackSprite = new Image("p" + playerNum + "_attack.png");
        this.hitSprite = new Image("p" + playerNum + "_damage.png");
        this.dodgeSprite = new Image("p" + playerNum + "_dodge.png");
        this.swordSprite = new Image("swordPlayer.png");
        this.hammerSprite = new Image("hammerPlayer.png");
        this.armSprite = new ImageView();
        this.sprite = new ImageView(this.idleSprite);
        if (playerNum % 2 == 0) {
            this.rotate(sprite, ANGLE);
        }
    }

    @Override
    public ImageView getSprite() {
        return this.sprite;
    }

    @Override
    public ImageView getArmSprite() {
        return this.armSprite;
    }

    @Override
    public void renderPlayer() {
        switch (this.player.getState()) {
            case HIT:
                this.sprite.setImage(this.hitSprite);
                break;
            case DODGE:
                this.sprite.setImage(this.dodgeSprite);
                break;
            case WALK_RIGHT:
                rotate(this.sprite, 0);
                this.armSprite.setRotate(0);
                this.armSprite.setTranslateX(0);
                this.player.getArmHitbox().setRotate(0);
                this.player.getArmHitbox().setTranslateX(0);
                this.sprite.setImage(this.idleSprite);
                break;
            case WALK_LEFT:
                rotate(this.sprite, ANGLE);
                this.player.getArmHitbox().setTranslateX(-this.player.getArmHitbox().getWidth());
                this.armSprite.setRotate(ANGLE);
                this.armSprite.setTranslateX(-this.player.getArmHitbox().getWidth());
                this.sprite.setImage(this.idleSprite);
                break;
            case ATTACK:
                this.sprite.setImage(this.attackSprite);
                break;
            default:
                this.sprite.setImage(this.idleSprite);
                break;
        }
        this.sprite.setLayoutX(this.player.getPosition().getX());
        this.sprite.setLayoutY(this.player.getPosition().getY());
        this.sprite.setFitWidth(this.player.getWidth());
        this.sprite.setFitHeight(this.player.getHeight());
        setArmSprite();
    }

    private void setArmSprite() {
        if (player.getWeapon().isPresent()) {
            this.armSprite.setVisible(true);
            this.armSprite.setImage(this.player.getWeapon().get().getId() == 0 ? this.swordSprite
                    : this.hammerSprite);
        } else {
            this.armSprite.setVisible(false);
        }
        this.armSprite.setLayoutX(this.player.getArmHitbox().getX());
        this.armSprite.setLayoutY(this.player.getArmHitbox().getY());
        this.armSprite.setFitWidth(this.player.getArmHitbox().getWidth());
        this.armSprite.setFitHeight(this.player.getArmHitbox().getHeight());
    }

    /**
     * Rotates the given sprite around the Y axis by the given angle.
     * Credits to: https://gist.github.com/jewelsea/1436941
     * 
     * @param sprite the ImageView to rotate
     * @param angle  the angle in degrees to rotate the ImageView
     * @return the rotated ImageView
     */
    private ImageView rotate(final ImageView sprite, final int angle) {
        sprite.setTranslateZ(sprite.getBoundsInLocal().getWidth() / 2.0);
        sprite.setRotationAxis(Rotate.Y_AXIS);
        sprite.setRotate(angle);
        return sprite;
    }

}
