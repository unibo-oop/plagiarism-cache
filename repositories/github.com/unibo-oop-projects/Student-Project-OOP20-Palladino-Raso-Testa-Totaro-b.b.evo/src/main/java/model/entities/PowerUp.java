package model.entities;

import java.util.HashMap;
import java.util.Map;

import controller.input.ComponentInputEmpty;
import controller.input.ControllerInput;
import model.physics.PwUpComponentPhysics;
import model.utilities.Boundaries;
import model.utilities.Position;
import model.utilities.PowerUpType;
import model.utilities.PowerUpUtilities;
import view.graphics.AdapterGraphics;
import view.graphics.PwUpComponentGraphics;

public class PowerUp extends GameObjectImpl {

    private static final long serialVersionUID = -3953133081190231594L;

    private final PowerUpType pwtype;

    private final long activeTime;
    private final double speedModifier;
    private final int lifeModifier;
    private final int damageModifier;

    private final String texturePath;

    private final Map<GameObject, Boundaries> hit = new HashMap<>();

    public PowerUp(final Position pos, final int height, final int width, final String texturePath) {
        super(pos, PowerUpUtilities.POWERUP_DROP_DIR, PowerUpUtilities.POWERUP_DROP_SPEED, height, width,  new PwUpComponentPhysics(),
                new ComponentInputEmpty(), new PwUpComponentGraphics(texturePath));
        this.pwtype = PowerUpType.randomPowerUpType();
        this.activeTime = (long) PowerUpType.valueOf(this.pwtype.toString()).getActiveTime();
        this.damageModifier = PowerUpType.valueOf(this.pwtype.toString()).getDamageModifier();
        this.lifeModifier = PowerUpType.valueOf(this.pwtype.toString()).getLifeModifier();
        this.speedModifier = PowerUpType.valueOf(this.pwtype.toString()).getSpeedModifier();
        this.texturePath = texturePath;
    }

    /**
     * getter for {@link PowerUpType} attribute.
     * @return which {@link PowerUpType} will be created
     */
    public PowerUpType getPowerUpType() {
        return this.pwtype;
    }

    /**
     * getter for the damage modifier.
     * @return the damage modifier 
     */
    public int getDamageModifier() {
        return damageModifier;
    }

    /**
     * getter for the life modifier. 
     * @return the life modifier
     */
    public int getLifeModifier() {
        return lifeModifier;
    }

    /**
     * getter for the speed modifier.
     * @return the speed modifier
     */
    public double getSpeedModifier() {
        return speedModifier;
    }

    /**
     * getter for the active time.
     * @return active time in seconds
     */
    public long getActiveTime() {
        return activeTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePhysics(final int timeElapsed, final GameBoardImpl world) {
        this.getComponentPhysics().update(timeElapsed, this, world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInput(final ControllerInput controller) {
        this.getComponentInput().update(this, controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGraphics(final AdapterGraphics graphicsAdapter) {
        this.getComponentGraphics().update(this, graphicsAdapter);
    }

    /**
     * getter for hit map.
     * @return map
     */
    public Map<GameObject, Boundaries> getHit() {
        return this.hit;
    }

    /**
     * getter for texturePath.
     * @return texturePath texture path.
     */
    public String getTexturePath() {
        return texturePath;
    }
}
