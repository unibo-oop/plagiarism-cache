package lands;

import java.util.LinkedList;

import controller.Controller;
import data.Colors;
import data.LandsData;

/**
 * 
 * implements Territori interface.
 *
 */
public final class LandsImpl implements Lands {

    private static LandsImpl istanza;
    /**
     * linked list of my extensions of JButton.
     */
    private static LinkedList<MyJButton> terr = new LinkedList<>();

    /**
     * public per farlo istanziare da tutti, alla fine andrà cambiato.
     */
    private LandsImpl() {
        for (int i = 0; i < Colors.NUMCOLORS; i++) {
            Lands.COLORI.add(Colors.values()[i].getColor());
        }

        for (int i = 0; i < LandsData.NUMTERR; i++) {
            terr.add(new MyJButton(LandsData.values()[i].getName(),
                    LandsData.values()[i].getConfini()));
        }

        terr.forEach(a -> {
            a.addActionListener(b ->  {
                Controller.passButton((MyJButton) b.getSource());
            });
        });
    }

    /**
     * 
     * @return this class' istance.
     */
    public static LandsImpl getLandsImpl() {
        if (istanza == null) {
            istanza = new LandsImpl();
        }

        return istanza;
    }

    @Override
    public LinkedList<MyJButton> getTERR() {
        // TODO Auto-generated method stub
        return terr;
    }
}
