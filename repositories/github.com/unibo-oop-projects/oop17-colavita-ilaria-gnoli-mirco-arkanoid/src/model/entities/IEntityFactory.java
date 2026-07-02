package model.entities;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaccia che definisce una factory di {@link IEntity}.
 * 
 * @author Gnoli Mirco
 *
 */
//sistemare documentazione enumerazione di mattoni
//valutare optional<Brick>
public interface IEntityFactory {

    /**
     * 
     * @return {@link Ball} con i parametri standard.
     */
    Ball standardBall();

    /**
     * @param b - {@link Ball} da cui farne delle copie aventi stessa posizione e velocità di partenza, ma differente direzione.
     * 
     * @return List formata da 2 Ball.
     */
    List<Ball> multipleBall(Ball b);

    /**
     * 
     * @return List di {@link Wall}, che delimitano il campo da gioco.
     */
    List<Wall> standardWalls();

    /**
     * 
     * @return List di {@link Brick} scelti in maniera random fra quelli disponibili in {@link EnumerazioneDeiMattoni}
     */
    List<Brick> randomBrickRow(); //sistemare documentazione qui

    /**
     * 
     * @return Map (indice riga, List di mattoni)
     */
    Map<Integer, List<Brick>> randomBrickMap();    //optional di brick se vogliamo fare delle figure?

    /**
     * 
     * @return {@link Bar}
     */
    Bar standardBar();

    /**
     * 
     * @param brickGenerator - {@link Brick} dalla cui rottura è generato il power up
     * @return Optional({@linkPowerUp}).
     */
    Optional<PowerUp> randomPowerUp(Brick brickGenerator);
}
