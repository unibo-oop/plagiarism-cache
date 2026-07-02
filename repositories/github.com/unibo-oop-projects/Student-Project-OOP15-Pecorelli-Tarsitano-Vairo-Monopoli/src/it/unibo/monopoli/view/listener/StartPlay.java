package it.unibo.monopoli.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JFrame;

import it.unibo.monopoli.controller.Controller;
import it.unibo.monopoli.controller.EVersion;
import it.unibo.monopoli.model.mainunits.ClassicPawn;
import it.unibo.monopoli.view.C;
import it.unibo.monopoli.view.Dialog;
import it.unibo.monopoli.view.Go;
import it.unibo.monopoli.view.InPlay;
import it.unibo.monopoli.view.InPlayImpl;
import it.unibo.monopoli.view.Index;
import it.unibo.monopoli.view.InizializedPlayer;

/**
 * 
 * Inizialized controller and start play.
 *
 */
public class StartPlay implements ActionListener {
    private InPlay inPlay;

    private int computer;
    private EVersion version;

    /**
     * 
     */
    public StartPlay() {
        super();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        e.getSource();
        if (VersionSelected.getSelectedItem().equals(C.NOT_SELECTABLE_OPTION)) {
            new Dialog(new JFrame(), "Error", "Error! You have not selected the version");
        } else if (Go.getNumPlayers() <= 1) {
            new Dialog(new JFrame(), "Error", "Error! The minimum number of players is two");
        } else if (!(InizializedPlayer.isSave())) {
            new Dialog(new JFrame(), "Error", "Error! You must first save the player");
        } else {
            computer = 0;
            InizializedPlayer.getMap().entrySet().forEach(a -> {
                if (a.getValue().equals(false)) {
                    computer++;
                }
            });
            if (Go.getNumPlayers() == computer) {
                new Dialog(new JFrame(), "Error", "Error! Players can not be all kind of Computers");
            } else {
                final Index i = new Index();
                inPlay = new InPlayImpl(i);
                i.addInPlay(inPlay);
                final Controller contr = i.getController();
                contr.addView(inPlay);

                final Set<Entry<String, Boolean>> s = InizializedPlayer.getMap().entrySet();
                Iterator iter = s.iterator();
                for (int x = 0; x < InizializedPlayer.getMap().size(); x++) {

                    Entry<String, Boolean> f = (Entry<String, Boolean>) iter.next();
                    String name = f.getKey();
                    Boolean b = f.getValue();
                    contr.addPlayer(name, new ClassicPawn(x), b);
                    System.out.println("Contr: name: " + name + "id: " + x + "isman: " + b);

                }
                switch (VersionSelected.getSelectedItem()) {
                case "Classic":
                    version = EVersion.CLASSIC;
                    contr.initializedVersion(EVersion.CLASSIC);
                    break;
                case "Italian Version":
                    version = EVersion.ITALIAN_VERSION;
                    contr.initializedVersion(EVersion.ITALIAN_VERSION);
                    break;
                default:
                    break;
                }
                contr.addView(inPlay);
                i.build();
                contr.play(version);
                contr.endTurn();

            }
        }
    }

    public InPlay getInPlay() {
        return inPlay;
    }

}
