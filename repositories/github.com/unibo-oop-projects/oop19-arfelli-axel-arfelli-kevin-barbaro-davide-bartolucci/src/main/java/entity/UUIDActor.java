package entity;

public abstract class UUIDActor extends UUIDEntity implements Actor {

    @Override
    public String toString() {
        return String.format("*Actor*\nUUID: %s\n", getUUID().toString());
    }
}
