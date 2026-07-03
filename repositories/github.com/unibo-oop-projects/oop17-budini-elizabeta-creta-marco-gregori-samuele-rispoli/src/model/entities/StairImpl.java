package model.entities;

import java.awt.Dimension;

public class StairImpl extends EntityImpl implements Stair{
    
    //TODO number not defined yet
    public static final int TRIGGER_HEIGHT = 2;  
    public static final int TRIGGER_WIDTH = 1;
    private Entity triggerL;
    private Entity triggerR;

    public StairImpl(Double x, Double y, Dimension dim) {
        super(x, y, dim);
        //TODO to complete, to insert dimension width-height
        triggerL = new EntityImpl(this.getHitbox().getCenterX()-this.getHitbox().width/2, y-TRIGGER_HEIGHT, new Dimension(TRIGGER_WIDTH, TRIGGER_HEIGHT));
        triggerR = new EntityImpl(this.getHitbox().getCenterX()+this.getHitbox().width/2-TRIGGER_WIDTH, y-TRIGGER_HEIGHT, new Dimension(TRIGGER_WIDTH, TRIGGER_HEIGHT));
    }

    public Entity getUpperTriggerL() {
        return new EntityImpl(this.getHitbox().getCenterX()-this.getHitbox().width/2, this.getY()-TRIGGER_HEIGHT, new Dimension(TRIGGER_WIDTH, TRIGGER_HEIGHT));
    }
    public Entity getUpperTriggerR() {
        return triggerR = new EntityImpl(this.getHitbox().getCenterX()+this.getHitbox().width/2-TRIGGER_WIDTH, this.getY()-TRIGGER_HEIGHT, new Dimension(TRIGGER_WIDTH, TRIGGER_HEIGHT));
    }
    

    public Entity getTriggerL() {
        return new EntityImpl(this.getHitbox().getCenterX()-this.getHitbox().width/2, this.getHitbox().getMaxY()-TRIGGER_HEIGHT, new Dimension(TRIGGER_WIDTH, TRIGGER_HEIGHT));
    }
    public Entity getTriggerR() {
        return triggerR = new EntityImpl(this.getHitbox().getCenterX()+this.getHitbox().width/2-TRIGGER_WIDTH, this.getHitbox().getMaxY()-TRIGGER_HEIGHT, new Dimension(TRIGGER_WIDTH, TRIGGER_HEIGHT));
    }
}
