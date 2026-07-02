package controllers.collision.collisionsPlayer;

import controllers.collision.collisionGeneral.Collisions;
import model.Door;
import model.ID;
import model.Lava;
import model.Portal;
import model.debuff.FireDebuff;
import model.debuff.FreezeDebuff;
import model.debuff.LoseTimeDebuff;
import model.debuff.SlowDownDebuff;
import model.enemy.Enemy;
import model.gameObject.GameObject;
import model.player.Player;
import model.powerup.GainTimePowerUP;
import model.powerup.InvisiblePowerUP;
import model.powerup.KnifePowerUP;
import model.powerup.LifeUpPowerUP;
import model.powerup.SpeedUPPowerUP;

public class CollisionsPlayer extends Collisions implements CollisionsPlayerInterface {

    @Override
    public void collisionsInGame(final Player obj1, final GameObject obj2) {
        if (obj2.getId().equals(ID.WALL)) {
            this.collisionsWall(obj1, obj2);
        } else if (obj2.getId().equals(ID.ENEMY)) {
            this.collisionsEnemy(obj1, (Enemy) obj2);
        } else if (obj2.getId().equals(ID.GAINTIMEPU)) {
            this.collisionsGainTime(obj1, (GainTimePowerUP) obj2);
        } else if (obj2.getId().equals(ID.INVISIBLEPU)) {
            this.collisionsInvisible(obj1, (InvisiblePowerUP) obj2);
        } else if (obj2.getId().equals(ID.KNIFEPU)) {
            this.collisionsKnife(obj1, (KnifePowerUP) obj2);
        } else if (obj2.getId().equals(ID.LIFEUPPU)) {
            this.lifeUp(obj1, (LifeUpPowerUP) obj2);
        } else if (obj2.getId().equals(ID.SPEEDUPPU)) {
            this.collisionsSpeedUp(obj1, (SpeedUPPowerUP) obj2);
        } else if (obj2.getId().equals(ID.FIREDB)) {
            this.collisionsFire(obj1, (FireDebuff) obj2);
        } else if (obj2.getId().equals(ID.FREEZEDB)) {
            this.collisionsFreeze(obj1, (FreezeDebuff) obj2);
        } else if (obj2.getId().equals(ID.SLOWDOWNDB)) {
            this.collisionsSlowDown(obj1, (SlowDownDebuff) obj2);
        } else if (obj2.getId().equals(ID.TIMEDOWNDB)) {
            this.collisionsTimeDown(obj1, (LoseTimeDebuff) obj2);
        } else if (obj2.getId().equals(ID.DOOR)) {
            this.collisionsDoor(obj1, (Door) obj2);
        } else if (obj2.getId().equals(ID.PORTAL)) {
            this.collisionPortal(obj1, (Portal) obj2);
        } else if (obj2.getId().equals(ID.LAVA)) {
            this.collisionLava(obj1, (Lava) obj2);
        }
    }

    private void collisionLava(final Player obj1, final Lava obj2) {
        if (this.checkCollisions(obj1, obj2) && obj1.isVisible()) {
            obj1.removeLife();
        }

    }

    private void collisionPortal(final Player obj1, final Portal obj2) {
        if (this.checkCollisions(obj1, obj2) && obj2.isVisible()) {
            obj2.effect(obj1);
        }
    }

    private void collisionsEnemy(final Player obj1, final Enemy obj2) {
        if (this.checkCollisions(obj1, obj2) && obj2.isAlive()) {
            if (this.checkCollisionsKnife(obj1, obj2) && !obj2.isDragon() && !obj2.isTower()) {
                obj1.setKnife(false);
                obj1.getActivePowerUpDebuff().getTimer().stop();
                obj1.removePowerUpDebuff();
                obj2.killLife();
            } else if (obj1.isVisible() && !obj1.hasKnife()) {
                if ((obj2.getRay() == null && !obj2.isTower())
                        || (obj2.getRay().isRayPresent() && obj2.getRay().isVisible())
                        || (obj2.isTower() && obj2.getRay().isRayPresent())) {

                    obj1.removeLife();
                }
            }
        }
        if (obj2.isTower() && this.checkCollisionsEnemyRectangle(obj1, obj2)) {
            this.collisionsWall(obj1, obj2);
        }
    }

    private boolean checkCollisionsKnife(final Player obj1, final Enemy obj2) {
        return obj1.getRectangle().intersects(obj2.getEnemyRectangle()) && obj1.hasKnife();
    }

    private void collisionsGainTime(final Player obj1, final GainTimePowerUP obj2) {
        if (this.checkCollisions(obj1, obj2) && obj2.isVisible()) {
            obj2.effect(obj1);
        }
    }

    private void collisionsInvisible(final Player obj1, final InvisiblePowerUP obj2) {
        if (this.checkCollisions(obj1, obj2) && obj2.isVisible()) {
            obj2.effect(obj1);
        }
    }

    private void collisionsKnife(final Player obj1, final KnifePowerUP obj2) {
        if (this.checkCollisions(obj1, obj2) && obj2.isVisible()) {
            obj2.effect(obj1);
        }
    }

    private void lifeUp(final Player obj1, final LifeUpPowerUP obj2) {
        if (this.checkCollisions(obj1, obj2) && obj2.isVisible()) {
            obj2.effect(obj1);
        }
    }

    private void collisionsSpeedUp(final Player obj1, final SpeedUPPowerUP obj2) {
        if (this.checkCollisions(obj1, obj2) && obj2.isVisible()) {
            obj2.effect(obj1);
        }
    }

    private void collisionsFire(final Player obj1, final FireDebuff obj2) {
        if (this.checkCollisions(obj1, obj2) && obj2.isVisible()) {
            obj2.effect(obj1);
        }
    }

    private void collisionsFreeze(final Player obj1, final FreezeDebuff obj2) {
        if (this.checkCollisions(obj1, obj2) && obj2.isVisible()) {
            obj2.effect(obj1);
        }
    }

    private void collisionsSlowDown(final Player obj1, final SlowDownDebuff obj2) {
        if (this.checkCollisions(obj1, obj2) && obj2.isVisible()) {
            obj2.effect(obj1);
        }
    }

    private void collisionsTimeDown(final Player obj1, final LoseTimeDebuff obj2) {
        if (this.checkCollisions(obj1, obj2) && obj2.isVisible()) {
            obj2.effect(obj1);
        }
    }

    private void collisionsDoor(final Player obj1, final Door obj2) {
        if (this.checkCollisions(obj1, obj2) && !obj2.isDoor()) {
            obj2.effect(obj1);
        }
    }
}
