package model.command;

import model.entities.Tank;

public class FireCommand implements Command {

    @Override
    public void execute(final Tank tank) {
        tank.shoot();

    }

}
