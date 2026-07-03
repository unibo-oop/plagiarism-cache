package controller.player;

import java.util.ArrayList;
import java.util.List;

import utility.Driver;
import utility.TyreType;

/**
 * A class to reunion the common features between AI and user.
 * The package only scope is intended.
 */
class PlayerImpl implements Player {

    private final Driver driver;
    private final List<TyreType> tyreList;
    private boolean box;
    private boolean crash;

    PlayerImpl(final Driver driver) {
        this.driver = driver;
        this.tyreList = new ArrayList<>();
    }

    @Override
    public void goToBox() {
        if (this.box) {
            throw new IllegalStateException();
        }
        this.box = true;
    }

    @Override
    public void exitBox() {
        if (!this.box) {
            throw new IllegalStateException();
        }
        this.box = false;
    }

    @Override
    public boolean isInBox() {
        return this.box;
    }

    @Override
    public void retire() {
        this.crash = true;
    }

    @Override
    public boolean isRetired() {
        return this.crash;
    }

    @Override
    public Driver getDriver() {
        return this.driver;
    }

    @Override
    public void addStint(final TyreType tyre) {
        this.tyreList.add(tyre);
    }

    @Override
    public List<TyreType> getPlayerStints() {
        return this.tyreList;
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
        final PlayerImpl other = (PlayerImpl) obj;
        return driver == other.driver;
    }

}
