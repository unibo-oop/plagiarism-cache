package maingame.menu.guifactory;

import javax.swing.JLabel;

/**
 * Interfaccia GUIFactory.
 */
public interface GUIFactoryMenu {
    /**
     * Crea e ritorna una JLabel.
     *
     * @param text
     *            Testo visualizzato dalla JLabel
     *
     * @return JLabel
     */
    JLabel createLabel(String text);

}
