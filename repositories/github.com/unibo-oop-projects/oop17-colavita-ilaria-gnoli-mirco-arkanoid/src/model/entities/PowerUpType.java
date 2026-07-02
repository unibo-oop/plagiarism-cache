/**
 * 
 */
package model.entities;

import model.IAdvancedGame;

/**
 * Enumerazione per gestire le tipologie di power-up ed i loro effetti.
 * 
 * @author Gnoli Mirco
 *
 */
public enum PowerUpType {
    /**
     * Rende tutte le palline di fuoco.
     */
    FIRE_BALL() {
        @Override
        public void effect(final IAdvancedGame game) {
            for (Ball ball : game.getBalls()) {
                ball.setType(BallType.FIRE_BALL);
                System.out.println(this.toString());
            }
        }
    },

    /**
     * Rende tutte le palline magnetiche con la barra.
     */
    MAGNET_BALL() {

        @Override
        public void effect(final IAdvancedGame game) {
            for (Ball ball : game.getBalls()) {
                ball.setType(BallType.MAGNET_BALL);
                System.out.println(this.toString());
            }

        }
    },

    /**
     * Triplica il numero delle palline presenti in gioco.
     */
    MULTIPLE_BALL() {
        @Override
        public void effect(final IAdvancedGame game) {
            game.addBalls();
            System.out.println(this.toString());
        }
    },

    /**
     * Aumenta la larghezza della barra.
     */
    BIG_BAR() {

        @Override
        public void effect(final IAdvancedGame game) {
            game.getBar().extendBar();
            System.out.println(this.toString());
        }
    },

    /**
     * Diminuisce la larghezza della barra.
     */
    LITTLE_BAR() {

        @Override
        public void effect(final IAdvancedGame game) {
            game.getBar().reduceBar();
            System.out.println(this.toString());
        }
    },

    /**
     * Rende la barra in grado di sparare proiettili.
     */
    LASER_BAR() {

        @Override
        public void effect(final IAdvancedGame game) {
            System.out.println(this.toString());     
        }
    },

    /**
     * Incrementa il numero delle vite rimaste di 1.
     */
    INCREASE_LIVES() {

        @Override
        public void effect(final IAdvancedGame game) {
            game.incLives();
            System.out.println(this.toString());

        }
    },

    /**
     * Inizia il livello successivo.
     */
    GO_TO_NEXT_LEVEL() {
        @Override
        public void effect(final IAdvancedGame game) {
            // TODO Auto-generated method stub
            System.out.println(this.toString());

        }
    };

    /**
     * Metodo per applicare al gioco l'effetto del power-up.
     * @param game - {@link IAdvancedGame}
     */
    public abstract void effect(IAdvancedGame game);

}
