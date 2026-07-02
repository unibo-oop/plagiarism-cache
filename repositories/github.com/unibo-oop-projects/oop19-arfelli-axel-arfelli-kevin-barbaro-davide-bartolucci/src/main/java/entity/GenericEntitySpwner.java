package entity;

public abstract class GenericEntitySpwner implements EntitySpawner {

    protected abstract void spawnEntity(Entity e);

    public final void spwanProjectile(final Projectile p) {
        spawnEntity(p);
    }

    public final void spwanActor(final Actor a) {
        spawnEntity(a);
    }

}
