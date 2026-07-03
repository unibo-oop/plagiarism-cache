package model.car;


import java.util.Objects;

import model.PositionImpl;
import model.RaceDirectionImpl;
import model.car.tyre.TTByDegrade;
import model.car.tyre.Tyre;
import model.car.tyre.TyreImpl;
import model.circuit.CircuitImpl;
import model.exception.EndRaceException;
import utility.Driver;
import utility.Pair;
import utility.Position;
import utility.TyreType;
/**
 * Implementation of Car interface.
 */
public class CarImpl implements Car {
    private static final int INITIAL_FRAME = 6;
    private static final int QUALI_LAP = 1;
    private static final int BOX_LAP = 5;
    private Position currentPos;
    private final Driver driver;
    private Tyre tyre;
    private int lapsLeft;
    private boolean inBox;
    private int turnInBox;
    private int carFrame;

    /**
     * Constructor.
     * @param driver the driver
     */
    public CarImpl(final Driver driver) {
        this.driver = driver;
        this.currentPos = new PositionImpl(RaceDirectionImpl.END_POS);
        this.inBox = false;
        this.turnInBox = 0;
        this.carFrame = INITIAL_FRAME;
        this.lapsLeft = QUALI_LAP;
    }

    @Override
    public void setPosition(final Position pos) {
        this.currentPos = new PositionImpl(pos);
    }

    @Override
    public void setLaps(final int laps) {
       this.lapsLeft = laps;
    }

    @Override 
    public void setInitialType(final TyreType tyre) {
        Objects.requireNonNull(tyre);
        this.tyre = new TyreImpl(tyre);
    }

    @Override
    public void tyreDeg() {
        this.tyre.tyreDeg();
    }

    @Override
    public void crash() {
        this.carFrame--;
        if (isRetired()) {
            this.lapsLeft = 0;
            this.currentPos = new PositionImpl(RaceDirectionImpl.END_POS);
        }
    }

    @Override
    public boolean isRetired() {
        return this.carFrame <= 0;
    }

    @Override
    public void resetCar(final int laps) {
        this.setLaps(laps);
        this.carFrame = INITIAL_FRAME;
    }

    @Override
    public Position move(final int x, final int y) {
       Objects.requireNonNull(this.tyre);
       this.currentPos.move(currentPos.getX() + x, y);
       this.tyre.tyreDeg();
       return this.currentPos;
    }

    @Override
    public void lapEnd() throws EndRaceException {
        this.lapsLeft--;
        if (this.lapsLeft <= 0) {
            this.currentPos = new PositionImpl(RaceDirectionImpl.END_POS);
            throw new EndRaceException();
        } else {
            currentPos.changeTrack(CircuitImpl.INITIAL_TRACK);
        }
    }

    @Override
    public void changeTrack() {
        currentPos.changeTrack(currentPos.getTrackNumber() + 1);
    }

    @Override
    public void box(final TyreType tyre) {
        Objects.requireNonNull(tyre);
        this.tyre.change(tyre);
        this.inBox = true;
        this.turnInBox = BOX_LAP; 
        this.setPosition(RaceDirectionImpl.BOX_POS);
    }

    @Override
    public boolean decLapsInBox() {
        if (inBox) {
            if (turnInBox > 0) {
                turnInBox--;
            }
            if (turnInBox == 0) {
                this.inBox = false;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isInBox() {
        return this.inBox;
    }

    @Override
    public void notOutOfBox() {
        this.inBox = true;
        this.turnInBox = 1;
    }

    @Override
    public Pair<Integer, Integer> getDiceNumber() {
        if (((Double) this.tyre.getDeg()).intValue() == 100) {
            return new Pair<>(1, 2);
        }
        final TTByDegrade tyre = this.tyre.getTyre();
        return new Pair<>(tyre.getMin(), tyre.getMax());
    }

    @Override
    public double getDeg() {
        return this.tyre.getDeg();
    }

    @Override
    public Position getPosition() {
        return this.currentPos;
    }

    @Override
    public Driver getDriver() {
        return this.driver;
    }

    @Override 
    public TyreType getTyreType() {
        return this.tyre.getTyreType();
    }

    @Override
    public int getLapsRemaining() {
        return this.lapsLeft;
    }

    @Override
    public int getCarFrame() {
        return this.carFrame;
    }

    @Override
    public String toString() {
        return this.driver.getName() 
               + " " + this.driver.getSurname() 
               + "\nTyre: " + this.tyre.getTyreType();
    }

   @Override
   public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((driver == null) ? 0 : driver.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarImpl other = (CarImpl) obj;
        return driver == other.driver;
    }
}
