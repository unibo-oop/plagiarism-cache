package entity;

import java.util.concurrent.atomic.AtomicBoolean;

import javafx.util.Pair;

public class GenericProjectile extends UUIDProjectile {

    /**
     * It's define the type of Projectile.
     */
    protected static final String TYPE = "Generic-Projectile";
    /**
     * It's define the Damage of the projectile.
     */
    private static final int DAMAGE = 1;
    /**
     * It's define the Velocity of the projectile.
     */
    private static final int VELOCITY = 5;

    private AtomicBoolean alive = new AtomicBoolean();
    private final double angle;
    private final Actor father;
    private final BodyImpl body;
    private double velocity = VELOCITY;

    public GenericProjectile(final CollisionBoxInt box, final double angle, final Actor father, double velocity) {
        this.body = new BodyImpl(box);
        this.alive.set(true);
        this.angle = angle;
        this.father = father;
        this.velocity = velocity;
    }
    
    public GenericProjectile(final CollisionBoxInt box, final double angle, final Actor father) {
        this(box,angle,father,VELOCITY);
    }

    @Override
    public final void update() {
    	synchronized (this) {
    		if (this.alive.get()) {
                final Pair<Integer, Integer> nextpoint = getNextPoint(this.body.getCollisionBox().getX(), this.body.getCollisionBox().getY());
                if (!this.body.move(nextpoint.getKey(), nextpoint.getValue())) {
                	this.hit();
                }
            }
		}        
    }

    private Pair<Integer, Integer> getNextPoint(final int x, final int y) {
        return new Pair<Integer, Integer>((int)(x + (( Math.cos(Math.toRadians(this.angle)) * velocity))), (int) (y + ((int) Math.sin(Math.toRadians(this.angle))* velocity)));
    }

    @Override
    public final boolean isAlive() {
        return this.alive.get();
    }

    @Override
    public final String getType() {
        return GenericProjectile.TYPE;
    }


    @Override
    public final double getDirection() {
        return this.angle;
    }

    @Override
    public final void hit() {
    	this.alive.set(false);       
    }

    @Override
    public final int getDamage() {
        return GenericProjectile.DAMAGE;
    }

    @Override
    public final Actor getFather() {
        return this.father;
    }

    @Override
    public final VirtualBody getBody() {
        return this.body;
    }

}