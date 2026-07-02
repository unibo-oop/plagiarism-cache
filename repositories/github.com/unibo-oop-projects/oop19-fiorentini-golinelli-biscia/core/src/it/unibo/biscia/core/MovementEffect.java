package it.unibo.biscia.core;

import java.util.List;
import java.util.Optional;

interface MovementEffect {

    List<Cell> getCells();

    Optional<Direction> getDirection();
}
