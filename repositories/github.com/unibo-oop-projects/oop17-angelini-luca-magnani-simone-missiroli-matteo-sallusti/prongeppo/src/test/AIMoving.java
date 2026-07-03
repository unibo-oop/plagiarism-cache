package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import model.Ball.Combo;

import controller.AIImpl;
import controller.AIImpl.Difficult;
import controller.Input;
import model.Ball;
import model.BallImpl;
import model.Bar;
import model.BarImpl;
import view.GraphicElem;

/**
 * @author Simone
 * the Test used to check AIImpl.
 */
//CHECKSTYLE: MagicNumber OFF
// usato per evitare di dover specificare una var per ogni "Valore Importante"
public class AIMoving {
    private static final String BALLSTRING = "SAYANBALL.png";
    private static final String BARSTRING = "SWORDBAR.png";
    private static final String EQUALSSTRING = "position should be identical";
            /**
             * 
             */
            @Test
            public void testEasyMoving() {
                final List<Ball> list = Arrays.asList(new BallImpl(new GraphicElem(AIMoving.BALLSTRING, 20, 20)));
                final Bar bar = new BarImpl(new GraphicElem(AIMoving.BARSTRING, 15, 100));
                list.get(0).setPosition(new Point(300, 300));
                list.get(0).setSpeed(new Point(1, 1));
                bar.setSpeed(10);
                final Input facile = new AIImpl(Difficult.EASY, list, bar);
                Point pos;
                bar.setPosition(new Point(1, 1)); // valore <<
                pos = bar.getPosition();
                facile.moving();
                assertEquals(pos.getY() + bar.getSpeed(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                bar.setPosition(new Point(1, 800)); // valore >>
                pos = bar.getPosition();
                facile.moving();
                assertEquals(pos.getY() - bar.getSpeed(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                bar.setPosition(new Point(1, 245)); // valore limite superiore affinchè stia ferma 
                                                    //(dipende dal centro della barra e il centro della palla)
                pos = bar.getPosition();
                facile.moving();
                //PMD falsi positivi, come già chiesto sul forum
                assertEquals(pos.getY(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                bar.setPosition(new Point(1, 235)); // valore limite inferiore affinchè stia ferma
                pos = bar.getPosition();
                facile.moving();
                assertEquals(pos.getY(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
            }

            /**
             * 
             */
            @Test
            public void testMediumMoving() {
                final List<Ball> list = Arrays.asList(new BallImpl(new GraphicElem(AIMoving.BALLSTRING, 20, 20)));
                final Bar bar = new BarImpl(new GraphicElem(AIMoving.BARSTRING, 15, 100));
                list.get(0).setPosition(new Point(501, 300));
                list.get(0).setSpeed(new Point(-1, 1));
                bar.setSpeed(10);
                final Input medium = new AIImpl(Difficult.MEDIUM, list, bar);
                Point pos;
                bar.setPosition(new Point(0, 0)); // valore <<
                pos = bar.getPosition();
                medium.moving();
                assertEquals(pos.getY() + bar.getSpeed(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                bar.setPosition(new Point(0, 800)); // valore >>
                pos = bar.getPosition();
                medium.moving();
                assertEquals(pos.getY() - bar.getSpeed(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                bar.setPosition(new Point(0, 746)); // valore limite superiore affinchè stia ferma
                pos = bar.getPosition();
                medium.moving();
                assertEquals(pos.getY(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                bar.setPosition(new Point(0, 736)); // valore limite inferiore affinchè stia ferma
                pos = bar.getPosition();
                medium.moving();
                assertEquals(pos.getY(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
            }

            /**
             * 
             */
            @Test
            public void testHardMoving() {
                final List<Ball> ballList = new LinkedList<>();
                ballList.add(new BallImpl(new GraphicElem(AIMoving.BALLSTRING, 20, 20)));
                final Bar bar = new BarImpl(new GraphicElem(AIMoving.BARSTRING, 15, 100));
                ballList.get(0).setPosition(new Point(501, 300));
                ballList.get(0).setSpeed(new Point(-1, 1));
                bar.setSpeed(10);
                Input hard = new AIImpl(Difficult.HARD, ballList, bar);
                // prove monopalla -> deve lavorare come medium
                Point pos;
                bar.setPosition(new Point(0, 0)); // valore <<
                pos = bar.getPosition();
                hard.moving();
                assertEquals(pos.getY() + bar.getSpeed(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                bar.setPosition(new Point(0, 800)); // valore >>
                pos = bar.getPosition();
                hard.moving();
                assertEquals(pos.getY() - bar.getSpeed(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                bar.setPosition(new Point(0, 746)); // valore limite superiore affinchè stia ferma
                pos = bar.getPosition();
                hard.moving();
                assertEquals(pos.getY(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                bar.setPosition(new Point(0, 736)); // valore limite inferiore affinchè stia ferma
                pos = bar.getPosition();
                hard.moving();
                assertEquals(pos.getY(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                // prove bipalla senza Combo -> deve lavorare come medium decidendo la palla giusta
                ballList.add(new BallImpl(new GraphicElem(AIMoving.BALLSTRING, 20, 20)));
                hard = new AIImpl(Difficult.HARD, ballList, bar);
                ballList.get(1).setPosition(new Point(800, 0)); //piu lontana
                ballList.get(1).setSpeed(new Point(-3, 0));    // ma piu veloce
                bar.setPosition(new Point(0, 745)); // valore in cui starebbe fermo per la prima palla
                pos = bar.getPosition();
                hard.moving();
                assertEquals(pos.getY() - bar.getSpeed(), bar.getPosition().getY(), AIMoving.EQUALSSTRING); // si muove
                bar.setPosition(new Point(0, 800)); // valore >>
                pos = bar.getPosition();
                hard.moving();
                assertEquals(pos.getY() - bar.getSpeed(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                ballList.get(1).setSpeed(new Point(-1, 0));    // stessa velocità
                bar.setPosition(new Point(0, 746)); // valore limite superiore
                pos = bar.getPosition();
                hard.moving();
                assertEquals(pos.getY(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                bar.setPosition(new Point(0, 736)); // valore limite inferiore
                pos = bar.getPosition();
                hard.moving();
                assertEquals(pos.getY(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                // prove bipalla con combo zigzag -> deve lavorare come easymoving
                ballList.get(0).setCombo(Combo.ZIGZAG);
                bar.setPosition(new Point(1, 1)); // valore <<
                pos = bar.getPosition();
                hard.moving();
                assertEquals(pos.getY() + bar.getSpeed(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                bar.setPosition(new Point(1, 800)); // valore >>
                pos = bar.getPosition();
                hard.moving();
                assertEquals(pos.getY() - bar.getSpeed(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                bar.setPosition(new Point(1, 245)); // valore limite superiore affinchè stia ferma
                pos = bar.getPosition();
                hard.moving();
                assertEquals(pos.getY(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
                bar.setPosition(new Point(1, 235)); // valore limite inferiore affinchè stia ferma
                pos = bar.getPosition();
                hard.moving();
                assertEquals(pos.getY(), bar.getPosition().getY(), AIMoving.EQUALSSTRING);
    }

}
