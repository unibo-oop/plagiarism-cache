package model.deck;

import static model.bonus.PowerBonusImpl.*;
import static model.deck.SimpleEffect.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 *
 */
public class DeckImpl implements Deck {

    private final List<Card> fieldCards = new ArrayList<>(3);
    private final List<Card> deckCards = new ArrayList<>();
/**
 * 
 */
    @Override
    public void createDeck() {
        //soldi
        deckCards.add(new IstantCard("Crollo Finanziario", 3, Arrays.asList(GLI_ALTRI_MOSTRI_PERDONO_2_SOLDI)));
        deckCards.add(new IstantCard("Imprenditore", 3, Arrays.asList(TU_GUADAGNI_5_SOLDI)));

        //vita
        deckCards.add(new IstantCard("Carneficina", 4, Arrays.asList(TUTTI_PERDONO_3_VITE)));
        deckCards.add(new IstantCard("Bomba a grappolo", 4, Arrays.asList(GLI_ALTRI_MOSTRI_PERDONO_2_VITE)));
        deckCards.add(new IstantCard("Birra", 2, Arrays.asList(TU_GUADAGNI_1_VITA)));
        deckCards.add(new IstantCard("Birra Doppio Malto", 3, Arrays.asList(TU_GUADAGNI_2_VITE)));
        deckCards.add(new IstantCard("Birra del dragone", 4, Arrays.asList(TU_GUADAGNI_3_VITE)));

        //punti
        deckCards.add(new IstantCard("Biscottino", 1, Arrays.asList(TU_GUADAGNI_1_PUNTO)));
        deckCards.add(new IstantCard("Cocchini", 2, Arrays.asList(TU_GUADAGNI_2_PUNTI)));
        deckCards.add(new IstantCard("CheeseCake", 4, Arrays.asList(TU_GUADAGNI_4_PUNTI)));
        deckCards.add(new IstantCard("Benefattore", 3, Arrays.asList(TUTTI_GUADAGNANO_2_PUNTI)));

        //composte
        deckCards.add(new IstantCard("Impavido", 4, Arrays.asList(TU_GUADAGNI_3_PUNTI, TU_PERDI_2_VITE)));
        deckCards.add(new IstantCard("Squallido", 4, Arrays.asList(GLI_ALTRI_MOSTRI_PERDONO_3_PUNTI, TU_PERDI_2_VITE)));
        deckCards.add(new IstantCard("Steroidi", 6, Arrays.asList(TU_GUADAGNI_2_PUNTI, TU_GUADAGNI_2_VITE, GLI_ALTRI_MOSTRI_PERDONO_2_SOLDI)));
        deckCards.add(new IstantCard("Potere degli Dei", 8, Arrays.asList(TU_GUADAGNI_4_VITE, TU_GUADAGNI_4_PUNTI, GLI_ALTRI_MOSTRI_PERDONO_3_VITE)));
        deckCards.add(new IstantCard("Succube", 4, Arrays.asList(TU_GUADAGNI_3_VITE, GLI_ALTRI_MOSTRI_GUADAGNANO_2_PUNTI)));
        deckCards.add(new IstantCard("Parassita", 3, Arrays.asList(TU_GUADAGNI_1_PUNTO, TU_GUADAGNI_1_VITA, GLI_ALTRI_MOSTRI_PERDONO_1_PUNTO, GLI_ALTRI_MOSTRI_PERDONO_1_VITA)));
        deckCards.add(new IstantCard("Investimento", 4, Arrays.asList(TU_GUADAGNI_5_SOLDI, GLI_ALTRI_MOSTRI_GUADAGNANO_2_PUNTI)));

        deckCards.add(new EquipmentCard("Armatura", 5, Collections.emptyList(), Arrays.asList(GUADAGNI_1_ARMATURA)));
        deckCards.add(new EquipmentCard("Manager", 5, Collections.emptyList(), Arrays.asList(COMPRI_CARTE_CON_UNO_SCONTO_DI_1)));
        deckCards.add(new EquipmentCard("Benedizione", 8, Collections.emptyList(), Arrays.asList(QUANDO_GUADAGNI_SOLDI_PRENDI_1_IN_PIU, AUMENTI_LA_VITA_MASSIMA_DI_1)));
        deckCards.add(new EquipmentCard("Il Primario", 4, Collections.emptyList(), Arrays.asList(AUMENTI_LA_VITA_MASSIMA_DI_1)));
        deckCards.add(new EquipmentCard("Jerry Scotti", 5, Collections.emptyList(), Arrays.asList(QUANDO_TU_GUADAGNI_PUNTI_GUADAGNI_1_IN_PIU)));
        deckCards.add(new EquipmentCard("Strozzino", 5, Arrays.asList(TU_GUADAGNI_5_SOLDI, GLI_ALTRI_MOSTRI_PERDONO_2_SOLDI), Arrays.asList(QUANDO_GUADAGNI_SOLDI_PRENDI_1_IN_PIU)));
        deckCards.add(new EquipmentCard("Refullatore", 4, Arrays.asList(TU_GUADAGNI_4_VITE), Arrays.asList(AUMENTI_LA_VITA_MASSIMA_DI_1)));

        Collections.shuffle(deckCards);

        for (int i = 0; i < 3; i++) {
            //put the first three cards of deck into fieldcards set
            fieldCards.add(deckCards.get(0));
            deckCards.remove(0);
        }
    }

    @Override
    public void addCard(final int index) {
        fieldCards.add(index, deckCards.get(0));
        deckCards.remove(0);
    }

    @Override
    public Card showCards() {
        return deckCards.get(0);
    }

    @Override
    public List<Card> showFieldCards() {
        return new ArrayList<Card>(fieldCards);
    }

    @Override
    public void removeCard(final int index) {
        this.fieldCards.remove(index);
        this.addCard(index);
    }

    @Override
    public List<Card> getDeckCards() {
        final ArrayList<Card> mazzocompleto = new ArrayList<>();
        mazzocompleto.addAll(deckCards);
        mazzocompleto.addAll(fieldCards);
        return mazzocompleto;
    }

}
