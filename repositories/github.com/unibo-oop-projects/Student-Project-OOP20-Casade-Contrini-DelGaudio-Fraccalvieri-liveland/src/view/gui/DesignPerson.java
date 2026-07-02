package view.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JApplet;
import javax.swing.JPanel;

import model.gui.person.CircleImpl;

/**
 * 
 * Class that draws people by passing them the color, the radius and the position.
 *
 *
 */
public class DesignPerson extends JPanel {

        private static final long serialVersionUID = 1L;

        /**
         * 
         * @param x coordinate x
         * @param y coordinate y
         * @param radius circle radius
         * @return creation of the adult 
         */
        public static final CircleImpl createAdult(final int x, final int y, final int radius) {
            CircleImpl adult = new CircleImpl();
            adult.setColor(new Color(15,226,0));
            adult.setRadius(radius);
            adult.setX(x);
            adult.setY(y);
            return adult;
        }
        /**
         * 
         * @param x coordinate x
         * @param y coordinate y
         * @param radius circle radius
         * @return creation of the baby 
         */

        public static CircleImpl createBaby(final int x, final int y, final int radius) {
            CircleImpl baby = new CircleImpl();
            baby.setColor(new Color(192,0,250));
            baby.setRadius(radius);
            baby.setX(x);
            baby.setY(y);
            return baby;
        }
        /**
         * 
         * @param x coordinate x
         * @param y coordinate y
         * @param radius circle radius
         * @return creation of the pass
         */

        public static CircleImpl createPass(final int x, final int y, final int radius) {
            CircleImpl pass = new CircleImpl();
            pass.setColor(new Color(0,155,232));
            pass.setRadius(radius);
            pass.setX(x);
            pass.setY(y);
            return pass;
        }


}
