package entity;

public abstract class UUIDProjectile extends UUIDEntity implements Projectile {

    @Override
    public String toString() {
        return String.format("*Projectile*\n"
                + "UUID: %s\n", getUUID().toString());
    }
}
