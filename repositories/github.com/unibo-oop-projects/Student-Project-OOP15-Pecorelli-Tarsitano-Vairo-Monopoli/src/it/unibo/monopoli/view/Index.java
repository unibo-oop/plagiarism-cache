package it.unibo.monopoli.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.monopoli.controller.Actions;
import it.unibo.monopoli.controller.Controller;
import it.unibo.monopoli.controller.ControllerImpl;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.view.cards.IBoxGraphic;
import it.unibo.monopoli.view.cards.LandGraphic;

/**
 * 
 * main class for the game board.
 *
 */
public class Index {
    private static final int FIRST_USEFUL_POSITION = 28;
    private static final int LAST_USEFUL_POSITION = 11;

    private final Controller controller;
    private final List<JButton> buttonList;
    private East eastP;
    private int position = 0;
    private HashMap<Integer, IBoxGraphic> tessere;
    private InPlay inPlay;
    private String winners2;
    private String temp;

    /**
     * Builder.
     */
    public Index() {

        this.controller = new ControllerImpl();
        this.buttonList = new LinkedList<>();

    }

    /**
     * add InPlay.
     * 
     * @param in
     *            InPlay
     * 
     */
    public void addInPlay(final InPlay in) {
        this.inPlay = in;
    }

    /**
     * method that builds the panel to be inserted in the main frame.
     */
    public void build() {

        final MyFrame frame = new MyFrame("Monopoli", new BorderLayout(), new Dimension(1200, 720));
        ProvaTabellone tabellone = new ProvaTabellone(11, 11, this.controller);
        tessere = tabellone.getCardsGraphic();

        // JPanelMain South
        final JPanel southP = new JPanel();
        southP.setPreferredSize(new Dimension(frame.getWidth(), 65));
        southP.setLayout(new FlowLayout());
        final JButton rollDices = new JButton(Actions.ROLL_DICES.getText());
        final JButton endOfTurn = new JButton(Actions.END_OF_TURN.getText());
        final JButton buy = new JButton(Actions.BUY.getText());
        final JButton sell = new JButton(Actions.SELL.getText());
        final JButton build = new JButton(Actions.BUILD.getText());
        final JButton sellBuilding = new JButton(Actions.SELL_BUILDING.getText());
        final JButton mortgage = new JButton(Actions.MORTGAGE.getText());
        final JButton revoke = new JButton(Actions.REVOKE_MORTGAGE.getText());
        final JButton endGame = new JButton(Actions.END_OF_THE_GAME.getText());

        southP.add(rollDices);
        southP.add(endOfTurn);
        southP.add(buy);
        southP.add(sell);
        southP.add(build);
        southP.add(sellBuilding);
        southP.add(mortgage);
        southP.add(revoke);
        southP.add(endGame);

        buttonList.add(rollDices);
        buttonList.add(endOfTurn);
        buttonList.add(buy);
        buttonList.add(sell);
        buttonList.add(build);
        buttonList.add(sellBuilding);
        buttonList.add(mortgage);
        buttonList.add(revoke);
        buttonList.add(endGame);

        rollDices.setEnabled(true);
        endOfTurn.setEnabled(false);
        buy.setEnabled(false);
        sell.setEnabled(false);
        build.setEnabled(false);
        sellBuilding.setEnabled(false);
        mortgage.setEnabled(false);
        revoke.setEnabled(false);
        endGame.setEnabled(false);

        rollDices.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {

                final Player p = controller.getActualPlayer();
                tessere.get(controller.getActualPlayer().getPawn().getActualPos()).removePawn(p);

                final int prePos = controller.getActualPlayer().getPawn().getActualPos();
                final int pos = controller.toRollDices();
                int passi = pos - prePos;
                if (isPassedFromStartBox()) {
                    passi = passFromStart();
                }
                new Dialog(new JFrame(), "Roll Dieces", "Number: " + (passi));
                tessere.get(pos).addPawn(controller.getActualPlayer());
                final String contratto = controller.getActualBox().getName();
                new Dialog(new JFrame(), "Actual", "You are in box " + contratto);
                if (controller.getActualBox() instanceof Ownership
                        && !((Ownership) controller.getActualBox()).getOwner().equals(controller.getActualPlayer())
                        && !((Ownership) controller.getActualBox()).getOwner().equals(controller.getBank())) {
                    new Dialog(new JFrame(), "Pay",
                            "This Box is owned by Player "
                                    + ((Player) ((Ownership) controller.getActualBox()).getOwner()).getName()
                                    + " so you have to pay to him it's income value");
                }
                buttonList.forEach(b -> b.setEnabled(false));
                final List<Actions> l = inPlay.getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.getText().equals(but.getText())) {
                            but.setEnabled(true);
                            if (but.getText().equals(Actions.BUY.getText())) {
                                final int cost = ((Ownership) controller.getActualBox()).getContract().getCost();
                                new Dialog(new JFrame(), "", "Il contratto " + contratto + " ha un costo di " + cost);
                            }
                        }
                    });
                });
            }
        });

        endOfTurn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {

                updateInfoPlayer();

                controller.endTurn();
                new Dialog(new JFrame(), "Next Player", "Next Player is: " + controller.getActualPlayer().getName());

                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = inPlay.getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {

                        if (bu.getText().equals(but.getText())) {
                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        buy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.buyOwnership();

                final String nome = controller.getActualPlayer().getName();
                final String contratto = controller.getActualBox().getName();
                final int pos = controller.getActualBox().getID();
                final int cost = ((Ownership) controller.getActualBox()).getContract().getCost();
                new Dialog(new JFrame(), "Buy",
                        "" + nome + " hai comprato " + contratto + " in posizione " + pos + " al costo di: " + cost);
                updateInfoPlayer();
                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = inPlay.getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.getText().equals(but.getText())) {
                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        sell.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.sellOwnership();

                final String nome = controller.getActualPlayer().getName();
                final String contratto = controller.getActualBox().getName();
                final int pos = controller.getActualBox().getID();
                final int cost = ((Ownership) controller.getActualBox()).getContract().getCost();
                new Dialog(new JFrame(), "Sell",
                        "" + nome + " hai venduto " + contratto + " in posizione " + pos + " al costo di: " + cost);
                updateInfoPlayer();
                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = inPlay.getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {

                        if (bu.getText().equals(but.getText())) {

                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        build.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {

                final int pos = controller.getActualPlayer().getPawn().getActualPos();
                ((LandGraphic) tessere.get(pos)).addHouse(controller.getActualPlayer());

                controller.build();

                updateInfoPlayer();
                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = inPlay.getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.getText().equals(but.getText())) {
                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        sellBuilding.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final int pos = controller.getActualPlayer().getPawn().getActualPos();
                ((LandGraphic) tessere.get(pos)).removeHouse(controller.getActualPlayer());

                controller.sellBuilding();
                updateInfoPlayer();
                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = inPlay.getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.getText().equals(but.getText())) {
                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        mortgage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.mortgageOwnership();
                updateInfoPlayer();
                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = inPlay.getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {

                        if (bu.getText().equals(but.getText())) {

                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        revoke.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.revokeMortgageOwnership();
                updateInfoPlayer();
                buttonList.forEach(b -> b.setEnabled(false));
                List<Actions> l = inPlay.getButtons();
                l.forEach(bu -> {
                    buttonList.forEach(but -> {
                        if (bu.getText().equals(but.getText())) {
                            but.setEnabled(true);
                        }
                    });
                });
            }
        });

        endGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                Map<Player, Integer> winner;
                MyFrame winnerF = new MyFrame("Winner", new BorderLayout(), new Dimension(300, 300));
                winnerF.setLocation(300, 300);
                winner = controller.endGame();

                Set<Entry<Player, Integer>> setWinner = winner.entrySet();

                JPanel winnerEnd = new JPanel();
                winnerF.add(winnerEnd);

                JLabel winnerL = new JLabel();
                winnerEnd.add(winnerL);

                temp = "";

                setWinner.forEach(w -> {

                    winners2 = " Name: " + w.getKey().getName() + ", Money: " + w.getValue() + ";\n";

                    temp += winners2;
                    winnerEnd.add(new JLabel(winners2), BorderLayout.CENTER);

                });

                winnerF.setVisible(true);
                buttonList.forEach(b -> b.setEnabled(false));

            }
        });

        // Center
        final JPanel centerP = new JPanel();
        centerP.add(tabellone.initialize(), BorderLayout.CENTER);

        // East
        eastP = new East(this.controller);
        frame.getContentPane().add(eastP, BorderLayout.EAST);
        frame.getMainPanel().add(centerP, BorderLayout.CENTER);
        frame.getMainPanel().add(southP, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    /**
     * 
     * method that returns the controls.
     * 
     * @return Controller
     */
    public Controller getController() {
        return this.controller;

    }

    /**
     * method that updates all the information of the bank and players.
     * 
     */
    public void updateInfoPlayer() {
        eastP.playerBank.setLabelBank();
        for (int i = 0; i < eastP.getMap().size(); i++) {
            eastP.getMap().entrySet().forEach(p -> p.getValue().setLAbelContract());
        }

    }

    private boolean isPassedFromStartBox() {
        return controller.getActualPlayer().getPawn().getPreviousPos() >= FIRST_USEFUL_POSITION
                && controller.getActualPlayer().getPawn().getActualPos() <= LAST_USEFUL_POSITION;
    }

    private int passFromStart() {
        int prePos = controller.getActualPlayer().getPawn().getPreviousPos();
        int actPos = controller.getActualPlayer().getPawn().getActualPos();

        return (39 - prePos) + (actPos + 1);
    }

    /**
     * the computer communicates its position and this method moves his pawn.
     * 
     * @param p
     * Player
     */
    public void computerTurn(final Player p) {
        tessere.get(this.position).removePawn(p);
        position = p.getPawn().getActualPos();
        tessere.get(position).addPawn(p);
        updateInfoPlayer();
    }

    /**
     * This method saved the computer previous position.
     * 
     * @param pos
     * position
     *
     */
    public void prevPos(final int pos) {
        this.position = pos;
    }
/**
 * return panel Est.
 * @return East
 */
    public East getEast() {
        return this.eastP;
    }

}
