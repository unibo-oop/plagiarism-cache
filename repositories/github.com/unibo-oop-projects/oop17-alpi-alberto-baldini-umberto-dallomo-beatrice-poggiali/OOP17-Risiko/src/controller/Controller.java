package controller;

import javax.swing.JButton;

import bonuscards.CardsManager;
import bonuscards.CardsManagerImpl;
import model.Actions;
import players.CircList;
import players.Player;
import players.PlayerImpl;
import hud.HUD;
import hud.SetUpHud;
import lands.Lands;
import lands.LandsImpl;
import lands.Lands.MyJButton;
import view.GUI;

/**
 * 
 * this class controls and instance the major entities of the application.
 */
public final class Controller {

       private static Player actualPlayer;
       private static CircList<PlayerImpl> players;
       private static Lands lands;
       private static Controller istanza;
       private static Actions turn;
       private static CardsManager carteBonus;

       private Controller(final CircList<PlayerImpl> giocatori) {
           players = giocatori;
           lands = LandsImpl.getLandsImpl();
           actualPlayer = players.get(0);
           new GUI(lands.getTERR());
           carteBonus = CardsManagerImpl.getCardsManager(players.size());
           turn = model.ActionsImpl.getActions(lands.getTERR());
       }

       /**
        * 
        * @param players is a list with the players data,
        * @return the Controller instance, Singleton pattern.
        */
       public static Controller getController(final CircList<PlayerImpl> players) {
           if (istanza == null) {
               istanza = new Controller(players);
           }

           return istanza;
       }

       /**
        * 
        * @param button
        * MYJButton will be passed to the function that changed its parameter, like armies or owner.
        */
        public static void passButton(final MyJButton button) {
            turn.switchCase(button);
       }

        /**
         * static method used to set a new turn.
         */
        public static void nextPlayer() {
            actualPlayer = players.next();
            SetUpHud.updateHUD(actualPlayer);
            SetUpHud.updateDice(new int[] {0, 0, 0}, new int[] {0, 0, 0});
            turn.newTurn(carteBonus.newTurn(getPlayers().indexOf(getActualPlayer())));
        }

        /**
         * 
         * @return the list of players;
         */
        public static CircList<PlayerImpl> getPlayers() {
            return players;
        }

        /**
         * 
         * @return the actual players;
         */
        public static Player getActualPlayer() {
            return actualPlayer;
        }

        /**
         * calls draw method in class CardsManagerImpl.
         */
        public static void draw() {
            carteBonus.draw();
        }

        /**
         * class a method that moves armies in class Actions.
         */
        public static void moveArmies() {
            turn.setIntentMove(); //metodo spostamento in Actions
        }

        /**
         * @return the move armies button to model.
         */
        public static JButton getMoveArmies() {
            return HUD.getSpostamento();
        }

        /**
         * @return the end of turn button to model.
         */
        public static JButton getFineTurno() {
            return HUD.getFineturno();
        }

        /**
         * 
         * @param att array of attack's dices.
         * @param dif array of defense's dices.
         */
        public static void updateDices(final int[] att, final int[] dif) {
           SetUpHud.updateDice(att, dif);
        }

        /**
         * 
         * @param s will be printed comandi's lin on HUD.
         */
        public static void updateComandi(final String s) {
            HUD.getComandilbl().setText(s);
        }

        /**
         * 
         * @param s will update Obbiettivi's line in HUD.
         */
        public static void updateObbiettivi(final String s) {
            HUD.getObbiettivilbl().setText(s);
        }

        /**
         * 
         * @param s will update leftarmies's line in HUD.
         */
        public static void updateLeftarmies(final String s) {
            HUD.getNumarmatelbl().setText(s);
        }
}
