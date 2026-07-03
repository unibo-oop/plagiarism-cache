package view;

import javax.swing.ImageIcon;

import model.Character;
import model.Question;

/**
 * Espone i metodi per la visualizzazione dell'interfaccia grafica.
 *
 */
public interface GameView {

    /**
     * Mostra la View principale contenente la sezione per fare domande,i
     * personaggi rimasti al giocatore e all'avversario e il personaggio scelto
     * dal giocatore.
     */
    void showView();

    /**
     * Mostra i messaggi del gioco.
     * 
     * @param message
     *            il messaggio da mostrare
     */
    void showMessage(String message);

    /**
     * Mostra il messaggio che notifica la vittoria di uno dei due giocatori.
     * 
     * @param message
     *            il messaggio da mostrare
     * @param aiCharacter
     *            il personaggio avversario
     */
    void showWinMessage(String message, ImageIcon aiCharacter);

    /**
     * Mostra la domanda avversaria.
     * 
     * @param question
     *            la domanda dell'avversario
     */
    void showQuestion(Question question);

    /**
     * Mostra il tentativo di indovinare dell'avversario.
     * 
     * @param character
     *            il personaggio che l'avversario ha scelto
     */
    void showQuestion(Character character);


}