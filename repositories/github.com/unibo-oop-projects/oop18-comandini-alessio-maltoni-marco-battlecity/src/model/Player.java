package model;

import java.util.Map;

import model.common.Counter;
import model.enemy.Enemy;
import model.entities.Tank;

public interface Player {

    Tank getTank();

    int getPoints();

    int getLife();

    void initializeTankPosition();

    Map<Enemy, Counter> getKilledTank();

}
