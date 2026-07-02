package view.hud;

import controller.collisionDetection.Collision;
import controller.collisionDetection.CollisionImpl;
import model.hud.HUDLife;
import model.hud.HUDLifeImpl;
import model.hud.HUDPoints;
import model.hud.HUDPointsImpl;
import model.hud.HUDPowerUp;
import model.hud.HUDPowerUpImpl;
import model.status.Status;
import utilities.EnumInt;
import utilities.PowerUpEnum;
import view.GameMap;

/**
 *
 */
public class HUDImpl implements HUDInterface {

    private HUDPointsImpl pointsHUD;
    private HUDLifeImpl livesHUD;
    private HUDPowerUp powerUpHUD;
    private Collision collision;
    private GameMap gameMap;
    private Status status;
    private boolean powerUp = false;

    /**
     * Game Container reference and HUD elements.
     *
     * @param gameMap
     */
    public HUDImpl(final GameMap gameMap) {
        this.gameMap = gameMap;
        this.generateHUD();
    }

    /**
     * Set status reference.
     *
     * @param status
     */
    public void setStatus(final Status status) {
        this.status = status;
    }

    private void generateHUD() {

        this.pointsHUD = new HUDPointsImpl();
        this.gameMap.getGameContainer().getChildren().add(this.pointsHUD);
        this.pointsHUD.setViewOrder(EnumInt.VIEW_ORDER.getValue());

        this.livesHUD = new HUDLifeImpl();
        livesHUD.setId("HealthPoints");
        this.gameMap.getGameContainer().getChildren().add(this.livesHUD);
        this.powerUpHUD = new HUDPowerUpImpl(this.gameMap);
        this.collision = new CollisionImpl(this.gameMap, this);
    }

    /**
     * Update HUD data.
     */
    public void update() {
        this.livesHUD.update(status.getLifePoints());
        this.pointsHUD.update(status.getPoints());
        if(this.status.hasPowerUp() && !this.powerUp) {
            this.powerUpHUD.showPowerUp(PowerUpEnum.WeaponDamage);
            this.powerUp = !this.powerUp;
        } else if (!this.status.hasPowerUp() && this.powerUp){
            this.powerUpHUD.hidePowerUp(PowerUpEnum.WeaponDamage);
            this.powerUp = !this.powerUp;
        }
    }

    @Override
    public boolean checkGameStatus() {
        return this.livesHUD.getGameStatus();
    }

    @Override
    public Collision getCollision() {
        return this.collision;
    }

    @Override
    public int checkPoints() {
        return this.pointsHUD.getPoints();
    }

    @Override
    public int checkLives() {
        return this.livesHUD.getLifePoints();
    }

    @Override
    public HUDPowerUp getPowerUpImpl() {
        return this.powerUpHUD;
    }

    @Override
    public HUDLife getLifeImpl() {
        return this.livesHUD;
    }

    @Override
    public HUDPoints getPointsImpl() {
        return this.pointsHUD;
    }


}
