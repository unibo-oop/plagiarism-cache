package model.command;

import model.entities.Tank;

public interface Command {

    void execute(Tank tank);

}
