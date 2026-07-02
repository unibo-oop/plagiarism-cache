package controller.playerController;

import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.bullet.Bullet;
import model.gun.Gun;
import model.gun.GunFactory;
import model.ship.PlayerShip;
import model.ship.Ship;
import model.status.Status;
import utilities.EnumInt;
import utilities.InputCommands;
import utilities.PlayerGunValues;
import utilities.PlayerValues;

public class PlayerShipControllerImpl implements PlayerShipController {

    private PlayerShip playerShip;
    private Status playerStatus;
    private float fireRate;
    private float gunRechargeTime = 0;
    private boolean hasFired = false;
    private boolean hasPowerUp = false;
    private boolean activePowerUp = false;
    private long powerTime = 0;
    private int currentLevel;
    private int gunDamage = (int) PlayerGunValues.MAIN_GUN.getValueFromKey("DAMAGE");
    private Gun normalPlayerGun;
    private int exp;

    public PlayerShipControllerImpl() {
        this.exp = 0;
        this.currentLevel = 1;
    }

    public PlayerShipControllerImpl(Vec2 initialPos, Image sprite) {
        this();
        initialisePlayerShip(initialPos, sprite);
    }

    @Override
    public void initialisePlayerShip(Vec2 initialPos, Image sprite) {
        this.playerShip = new PlayerShip(initialPos,
                PlayerValues.MAIN_SHIP.getValueFromKey("MAXHEALTH"),
                PlayerValues.MAIN_SHIP.getValueFromKey("MAXSPEED"),
                PlayerValues.MAIN_SHIP.getValueFromKey("ROTATIONSPEED"));
        this.normalPlayerGun = GunFactory.playerGun(this.playerShip,
                (int)   PlayerGunValues.MAIN_GUN.getValueFromKey("DAMAGE"),
                PlayerGunValues.MAIN_GUN.getValueFromKey("MAXSPEED"),
                PlayerGunValues.MAIN_GUN.getValueFromKey("ACCELERATION"),
                PlayerGunValues.MAIN_GUN.getValueFromKey("ROTATIONSPEED"));
        this.fireRate = PlayerGunValues.MAIN_GUN.getValueFromKey("FIRERATE");
        this.playerShip.setGun(this.normalPlayerGun);
        this.playerShip.setSprite(sprite);
    }

    public Ship getPlayerShip() {
        return this.playerShip;
    }

    @Override
    public void thrust(InputCommands input) {
        playerShip.thrust(input);
    }
    @Override
    public void rotate(InputCommands input) {
        playerShip.rotate(input);
    }
    public void thrustReleased() {
        playerShip.thrustReleased();
    }

    @Override
    public void acquirePowerUp() {
        if(!hasPowerUp) {
            this.hasPowerUp = true;
            this.playerStatus.switchPowerUp();
        }
    }

    public ImageView update(long deltaTime) {
        long delta = 1_000_000_000L;
        if (this.hasFired) {
            this.gunRechargeTime += deltaTime;
            if (this.gunRechargeTime >= delta / fireRate) {
                this.hasFired = false;
                this.gunRechargeTime = 0;
            }
        }
        if (this.activePowerUp) {
            this.powerTime += deltaTime;
            if (this.powerTime >= EnumInt.POWER_UP_DURATION.getValue() * delta) {
                this.activePowerUp = false;
                this.powerTime = 0;
                this.endPowerUp();
            }
        }
        if(this.playerShip.getAcceleration() == 0)
            this.playerShip.decaySpeed();
        this.playerStatus.setLifePoints(this.playerShip.getHealth());
        return display();
    }

    @Override
    public Bullet shot() {
        Bullet bullet = null;
        if (!this.hasFired) {
            bullet = this.playerShip.shot();
            this.hasFired = true;
            this.gunRechargeTime = 0;
        }
        return bullet;
    }

    @Override
    public void activatePowerUp() {
        if (hasPowerUp && !activePowerUp) {
            this.hasPowerUp = false;
            this.playerStatus.switchPowerUp();
            this.fireRate *= 2;
            this.gunDamage *= 2;
            //bit jank, but it will have to do; original playergun is remembered in the class
            this.playerShip.setGun(GunFactory.playerGun(this.playerShip,
                    gunDamage,
                    PlayerGunValues.MAIN_GUN.getValueFromKey("MAXSPEED"),
                    PlayerGunValues.MAIN_GUN.getValueFromKey("ACCELERATION"),
                    PlayerGunValues.MAIN_GUN.getValueFromKey("ROTATIONSPEED")));
            this.activePowerUp = true;
        }
    }

    private void endPowerUp() {
        this.fireRate /= 2;
        this.gunDamage /= 2;
        this.playerShip.setGun(normalPlayerGun);
    }

    @Override
    public ImageView display() {
        return playerShip.getSprite();
    }

    @Override
    public void destroy() {
        this.playerShip.destroy();
    }
    public Status getStatus() {
        return this.playerStatus;
    }
    public void setStatus(Status status) {
        this.playerStatus = status;
    }


    public int getExp() {
        return this.exp;
    }

    public void setExp(int newPoints) {
        this.exp = newPoints;
        if (checkLevelUp()) {
            this.levelUp();
        }
    }

    @Override
    public void addExp(int value) {
        this.setExp(this.getExp()+value);
    }

    @Override
    public boolean isInPowerUp() {
        return this.activePowerUp;
    }

    /**
     * every level the player gains 5% MaxHP of the original MaxHP value and gets healed
     * every 3 levels, fire rate increases by 5% of the original value
     * every 5 levels, gun damage is increased by 5% of the original value
     */
    private void levelUp() {
        this.playerShip.setMaxHealth(this.playerShip.getMaxHealth() + 5 * (PlayerValues.MAIN_SHIP.getValueFromKey("MAXHEALTH")) / 100);
        this.playerShip.heal(50);

        if (this.currentLevel % 3 == 0) {
            this.fireRate += 5f * (PlayerGunValues.MAIN_GUN.getValueFromKey("FIRERATE")) / 100f;
        }

        if (this.currentLevel % 5 == 0) {
            this.gunDamage += 5 * (PlayerGunValues.MAIN_GUN.getValueFromKey("DAMAGE")) / 100;
            this.gunLevelUp(gunDamage);
        }
        this.setExp(this.getExp() - (EnumInt.EXP_REQUIRED.getValue() * (int) (Math.pow(2, this.currentLevel - 1))));
        this.currentLevel++;
    }

    /**
     * modify the playergun in player controller to keep the original intact while the ship is in powerup mode
     * @param newDamage the new damage amount of the bullets
     */
    private void gunLevelUp(int newDamage) {
        this.normalPlayerGun = GunFactory.playerGun(this.playerShip,
                (int)        PlayerGunValues.MAIN_GUN.getValueFromKey("DAMAGE") + newDamage,
                PlayerGunValues.MAIN_GUN.getValueFromKey("MAXSPEED"),
                PlayerGunValues.MAIN_GUN.getValueFromKey("ACCELERATION"),
                PlayerGunValues.MAIN_GUN.getValueFromKey("ROTATIONSPEED"));
        this.playerShip.setGun(normalPlayerGun);
    }

    /**
     * can the player level up
     * @return boolean
     */
    private boolean checkLevelUp() {
        return this.exp >= nextLevelUp();
    }

    /**
     * next level exp requirements that increase exponentially
     * @return exp required to level up
     */
    private int nextLevelUp() {
        return EnumInt.EXP_REQUIRED.getValue() * (int) ((Math.pow(2, this.currentLevel - 1)));
    }

}
