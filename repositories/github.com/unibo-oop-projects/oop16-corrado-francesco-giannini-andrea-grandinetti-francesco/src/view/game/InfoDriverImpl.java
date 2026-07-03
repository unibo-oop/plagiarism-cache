package view.game;

import java.util.Optional;

import utility.Driver;
import utility.TyreType;

/**
 * Implementation of InfoDriverRound: there are all info of driver to display.
 *
 */
public class InfoDriverImpl implements InfoDriver {

    private final Optional<String> name;
    private final Driver driver;
    private final TyreType tyre;
    private final int position;
    private final double tyreDecay;
    private final int shellPoint;
    private final boolean isUser;


    /**
     * Constructor.
     * @param name name of driver
     * @param driver driver's name
     * @param tyre type of tyre
     * @param position driver's position
     * @param tyreDecay decay of tyre
     * @param shellPoint car's shell point
     * @param isUser True if is User
     */
    public InfoDriverImpl(final Optional<String> name, final Driver driver, final TyreType tyre,
            final int position, final double tyreDecay, final int shellPoint, final boolean isUser) {
        this.name = name;
        this.driver = driver;
        this.tyre = tyre;
        this.position = position;
        this.tyreDecay = tyreDecay;
        this.shellPoint = shellPoint;
        this.isUser = isUser;
    }

    @Override
    public String getName() {
        if (isUser) {
            return this.name.get();
        } else {
            return "IA di " + this.driver.getSurname();
        }
    }

    @Override
    public String getDriverName() {
        return driver.toString();
    }

    @Override
    public String getTeam() {
        return driver.getTeam().toString();
    }

    @Override
    public String getTyreType() {
        return tyre.getName();
    }

    @Override
    public String getPosition() {
        return Integer.toString(position);
    }

    @Override
    public String getTyreDecay() {
        if (isUser) {
            final Integer n = new Double(tyreDecay).intValue();
            return n.toString();
        } else {
            return "??";
        }
    }

    @Override
    public String getShellPoint() {
        if (isUser) {
            return Integer.toString(shellPoint);
        } else {
            return "??";
        }
    }

}
