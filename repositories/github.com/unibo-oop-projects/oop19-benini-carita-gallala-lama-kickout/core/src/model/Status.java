package model;
/**
 * Defines the status of a fighter
 */
public enum Status {

    IDLE("idle"),
    WALK("walk"),
    JUMP("jump"),
    HIT("hit"),
    ATTACK1("attack1");
    /**
     * @return the name of the status
     */
    public String getString() {
        return this.string;
    }

    private final String string;
    private Status(final String string) {
        this.string = string;
    }
};

