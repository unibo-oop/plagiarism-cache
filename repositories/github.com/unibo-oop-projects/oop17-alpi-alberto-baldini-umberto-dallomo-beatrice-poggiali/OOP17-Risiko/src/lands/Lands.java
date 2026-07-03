package lands;

import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JButton;

/**
 * 
 * this class represents the concept of all lands that are part of the map.
 * it includes the management of ownership(as players's colors) and
 * the registration of actual number of armies.
 *
 */
public interface Lands {

    /**
     *  this list contains the players colors, they're identified by index.
     *  the color at index 0 means that the owner isn't definied yet.
     */
    LinkedList<Color> COLORI = new LinkedList<>();

    /**
     *
     * @return the linked list of lands
     */
    LinkedList<MyJButton> getTERR();            //fare in modo che ritorni un oggetto immodificabile

    /**
     * 
     * my extension of JButton, represents the single land and 
     * its most important information.
     * only a constructor, setters and getters methods.
     */
    class MyJButton extends JButton { 

        /**
         * 
         */
        private static final long serialVersionUID = 5583043457357226819L;
        private final int[] confini;
        private final String nome;

        MyJButton(final String nome, final int[] conf) {
            this.setText("1");
            this.confini = conf;
            this.nome = nome;
            this.setOwner(0);
            this.setEnabled(true);

        }

        public void setArmies(final int n) {
            this.setText(String.valueOf(n));
        }

        public void setOwner(final int i) {
             this.setBackground(Lands.COLORI.get(i));
        }

        public int getArmies() {
            return Integer.parseInt(this.getText());
        }

        public Color getColor() {
            return this.getBackground();
        }

        public int getOwner() {
            return Lands.COLORI.indexOf(this.getColor());
        }

        public int[] getConfini() {
            return this.confini;
        }

        public String getNome() {
            return this.nome;
        }

    }
}
