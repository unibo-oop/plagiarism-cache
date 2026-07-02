package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.unibo.monopoli.controller.Controller;

/**
 * 
 * Right panel positioned in the Index Main.
 *
 */
public class East extends JPanel {

    private Map<String, PlayerGraphic> players;
    PlayerGraphic playerBank;

    /**
     * class constructor East.
     * 
     * @param controller
     *  Controller
     */
    public East(final Controller controller) {

        this.players = new HashMap<>();
        this.setLayout(new BorderLayout());

        playerBank = new PlayerGraphic(controller);
        playerBank.setLayout(new BorderLayout());
        playerBank.add(playerBank.buildBank(controller.getAllBoxes(), controller.getBank()), BorderLayout.CENTER);
        this.add(playerBank, BorderLayout.NORTH);

        final int j = InizializedPlayer.getMap().size();

        final JPanel panelPlayer = new JPanel();
        panelPlayer.setLayout(new GridLayout(j, 1));

        for (int i = 0; i < InizializedPlayer.getMap().size(); i++) {
            final PlayerGraphic graphic = new PlayerGraphic(controller);
            panelPlayer.add(graphic.build(controller.getAllBoxes(), controller.getPlayers(), i));
            this.players.put(controller.getPlayers().get(i).getName(), graphic);
        }
        this.add(new JScrollPane(panelPlayer), BorderLayout.CENTER);

    }

    /**
     * 
     * method that returns the map of players.
     * 
     * @return Map<String, PlayerGraphic>
     */
    public Map<String, PlayerGraphic> getMap() {
        return this.players;
    }

    /**
     * method that returns the panel created.
     * 
     * @return this
     */
    public JPanel build() {
        return this;
    }

    /**
     * @return the playerBank
     */
    public PlayerGraphic getPlayerBank() {
        return playerBank;
    }

    /**
     * @param playerBank
     *            the playerBank to set
     */
    public void setPlayerBank(final PlayerGraphic playerBank) {
        this.playerBank = playerBank;
    }

}
