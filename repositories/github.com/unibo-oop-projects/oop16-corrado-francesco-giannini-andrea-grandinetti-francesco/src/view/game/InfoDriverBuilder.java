package view.game;

import java.util.Objects;
import java.util.Optional;

import utility.Driver;
import utility.TyreType;

/**
 * Builder of InfoDriver.
 */
public class InfoDriverBuilder {

    private static final String ERROR = "must be positive!";
    private static final String POS_ERROR = "Position " + ERROR;
    private static final String DECAY_ERROR = "Decay " + ERROR;
    private static final String SHELL_ERROR = "Shell points " + ERROR;

    private Optional<String> name = Optional.empty();
    private Driver drv;
    private TyreType tyreType;
    private int pos;
    private double tyreDecay;
    private int shellPoint;
    private boolean user;

    /**
     * Setter.
     * @param name the name of the player 
     * @return the instance of InfoDriverBuilder currently used
     */
    public InfoDriverBuilder namePlayer(final Optional<String> name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }

    /**
     * Setter.
     * @param drv the drive used
     * @return the instance of InfoDriverBuilder currently used
     */
    public InfoDriverBuilder driver(final Driver drv) {
        Objects.requireNonNull(drv);
        this.drv = drv;
        return this;
    }

    /**
     * Setter.
     * @param tyre the tyre used
     * @return the instance of InfoDriverBuilder currently used
     */
    public InfoDriverBuilder tyre(final TyreType tyre) {
        Objects.requireNonNull(tyre);
        this.tyreType = tyre;
        return this;
    }

    /**
     * Setter.
     * @param pos the currents position of the player
     * @return the instance of InfoDriverBuilder currently used
     */
    public InfoDriverBuilder position(final int pos) {
        this.checkPos(pos);
        this.pos = pos;
        return this;
    }

    /**
     * Setter.
     * @param decay the current degrade of the tyres used by the driver
     * @return the instance of InfoDriverBuilder currently used
     */
    public InfoDriverBuilder deg(final double decay) {
        this.checkTyreDec(decay);
        this.tyreDecay = decay;
        return this;
    }

    /**
     * Setter.
     * @param shellPoints the current shellPoints that remains to the player
     * @return the instance of InfoDriverBuilder currently used
     */
    public InfoDriverBuilder shellPoints(final int shellPoints) {
        this.checkShellPoint(shellPoints);
        this.shellPoint = shellPoints;
        return this;
    }

    /**
     * Setter.
     * @param user if it's a user or not
     * @return the instance of InfoDriverBuilder currently used
     */
    public InfoDriverBuilder isUser(final boolean user) {
        Objects.requireNonNull(user);
        this.user = user;
        return this;
    }

    /**
     * Builder.
     * @return a new instance o InfoDriver based of the parameters passed
     */
    public InfoDriver build() {
        Objects.requireNonNull(this.name);
        Objects.requireNonNull(this.drv);
        Objects.requireNonNull(this.tyreType);
        Objects.requireNonNull(this.user);
        this.checkPos(this.pos);
        this.checkTyreDec(this.tyreDecay);
        this.checkShellPoint(this.shellPoint);
        return new InfoDriverImpl(name, drv, tyreType, pos, tyreDecay, shellPoint, user);
    }

    private void checkPos(final int pos) {
        if (pos <= 0) {
            throw new IllegalArgumentException(POS_ERROR);
        }
    }

    private void checkTyreDec(final double decay) {
        if (decay < 0) {
            throw new IllegalArgumentException(DECAY_ERROR);
        }
    }

    private void checkShellPoint(final int shellPoints) {
        if (shellPoints < 0) {
            throw new IllegalArgumentException(SHELL_ERROR);
        }
    }
}
