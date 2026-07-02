package it.unibo.oop.crossline.game.actor.robot;

import java.util.Observer;
import it.unibo.oop.crossline.game.actor.Actor;
import it.unibo.oop.crossline.game.attributes.Armed;
import it.unibo.oop.crossline.game.attributes.Physical;

public interface Robot extends Actor, Armed {

    void addObserver(Observer observer);

    Physical getTarget();

}
