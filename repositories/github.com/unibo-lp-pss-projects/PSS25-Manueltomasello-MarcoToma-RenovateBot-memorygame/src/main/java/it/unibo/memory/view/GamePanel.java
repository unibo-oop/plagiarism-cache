package it.unibo.memory.view;

import javax.swing.*;
import java.awt.*;
import it.unibo.memory.controller.GameController;
import it.unibo.memory.model.Board;
import it.unibo.memory.model.Card;

/**
 * Pannello che visualizza la griglia di carte del gioco.
 * Aggiorna graficamente i bottoni in base allo stato del modello.
 */
public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final Board board; 
    private final JButton[] buttons;

    public GamePanel(final Board board, final GameController controller) {
        this.board = board;

        final int totalCards = board.getSize();
        this.buttons = new JButton[totalCards];

        // Impostazione griglia dinamica basata sulla difficoltà
        this.setLayout(new GridLayout(
            board.getDifficulty().getRows(), 
            board.getDifficulty().getCols(), 
            5, 5
        ));
        
        this.setBackground(Color.DARK_GRAY);

        // Creazione bottoni e collegamento con il Controller
        for (int i = 0; i < totalCards; i++) {
            final int position = i;

            buttons[i] = new JButton("?");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
            buttons[i].setFocusPainted(false); 

            // Click: invia la posizione al controller e rinfresca la grafica
            buttons[i].addActionListener(e -> {
                controller.onCardClicked(position);
                updateDisplay(); 
            });

            this.add(buttons[i]);
        }
    }

    /**
     * Sincronizza lo stato visivo dei bottoni con i dati delle carte nel modello.
     */
    public void updateDisplay() {
        for (int i = 0; i < buttons.length; i++) {
            Card card = board.getCard(i);
            
            if (card.isFaceUp()) {
                // Se la carta è scoperta, mostra il simbolo (numero)
                buttons[i].setText(String.valueOf(card.getSymbol()));
                
                if (card.isMatched()) {
                    buttons[i].setBackground(Color.GREEN); // Coppia indovinata
                    buttons[i].setEnabled(false); 
                } else {
                    buttons[i].setBackground(Color.WHITE); // Carta girata temporaneamente
                    buttons[i].setEnabled(true);
                }
            } else {
                // Se la carta è coperta, mostra il punto di domanda
                buttons[i].setText("?");
                buttons[i].setBackground(null); 
                buttons[i].setEnabled(true);
            }
        }
    }
}