package entity;

import java.util.UUID;

public abstract class UUIDEntity implements Entity {

    /** 
     * Creating a random UUID (Universally unique identifier).
     */ 
    private final UUID uuid = UUID.randomUUID();

    public final UUID getUUID() {
        return uuid;
    }

    @Override
    public final int hashCode() {
        return this.uuid.hashCode();
    }

    @Override
    public final String getID() {
        return uuid.toString();
    }

    public final boolean equals(final Object e) {
        if (e == null) {
            return false;
        }
        if (e instanceof UUIDEntity) {
            return this.uuid.equals(UUID.fromString(((UUIDEntity) e).getID()));
        }
        return false;
    }
}
