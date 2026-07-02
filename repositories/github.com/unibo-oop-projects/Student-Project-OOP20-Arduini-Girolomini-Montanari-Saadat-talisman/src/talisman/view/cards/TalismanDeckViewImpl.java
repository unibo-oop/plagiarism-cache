package talisman.view.cards;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import talisman.model.cards.Card;
/**
 * A class with a method that creates a view of a list of cards.
 * @author Abtin Saadat
 */
public class TalismanDeckViewImpl extends JPanel implements TalismanDeckView {
    private static final long serialVersionUID = 1L;
    private static final int MAX_SHOWN_CARDS = 2;
    private int index;
    private final List<TalismanCardView> cards;
    private final JPanel cardsPanel;

    public TalismanDeckViewImpl(final List<Card> deck) {
        this.index = 0;
        final LayoutManager layout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(layout);
        final JButton button = new JButton("<<");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                updateCards(index - 1);
            }
        });
        this.add(button);
        cards = new ArrayList<>();
        for (final Card card : deck) {
            cards.add(new TalismanCardViewImpl(card.getImagePath(), card.getText(),
                    card.getName()));
        }
        this.cardsPanel = new JPanel();
        this.add(this.cardsPanel);
        final JButton button2 = new JButton(">>");
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                updateCards(index + 1);
            }
        });
        this.add(button2);
        this.updateCards(0);
        this.setSize(this.cardsPanel.getPreferredSize());
    }

    private void updateCards(final int index) {
        this.index = Math.max(Math.min(index, this.cards.size() - MAX_SHOWN_CARDS), 0);
        this.cardsPanel.removeAll();
        final int lastIndex = Math.max(Math.min(this.cards.size(), this.index + MAX_SHOWN_CARDS), 0);
        for (int i = this.index; i < lastIndex; i++) {
            this.cardsPanel.add((TalismanCardViewImpl) this.cards.get(i));
        }
    }
}
