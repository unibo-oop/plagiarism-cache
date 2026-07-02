package migglione.model.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * enum cardId define 24 cards whit their name,
 * five atrubute and the id used for the key map.
 */
public enum CardId {
    ANDER_BLEIDO(1, "ander_bleido", 6, 5, 7, 4, 7),
    CAPITAN_VESUVIO(2, "capitan_vesuvio", 7, 8, 5, 2, 5),
    ACQUAMAN(3, "acquaman", 7, 4, 7, 6, 8),
    IL_MAZZO_DI_CARTE(4, "il_mazzo_di_carte", 10, 10, 10, 10, 10),
    IL_DIA_BOLO(5, "il_dia-bolo", 9, 3, 5, 6, 3),
    IL_GRANDE_BARRAZA_MAGNO(6, "il_grande_barraza_magno", 8, 9, 8, 5, 3),
    IL_FALCHIERE(7, "il_falchiere", 6, 4, 2, 7, 9),
    WAQUALUIGI(8, "waqualuigi", 8, 3, 9, 5, 2),
    L(9, "l", 2, 2, 2, 10, 2),
    CALABRIA(10, "calabria", 1, 1, 1, 1, 1),
    ULISSE(11, "ulisse", 9, 9, 9, 7, 8),
    MAESTRO_ZANUS(12, "maestro_zanus", 7, 8, 8, 9, 3),
    IL_DIO_REY(13, "il_dio_rey", 8, 8, 8, 9, 9),
    LA_LEGA_DELLE_LEGGENDE(14, "la_lega_delle_leggende", 7, 8, 9, 6, 2),
    IL_GRANDE_DANIZAMBO_MAGNO(15, "il_grande_danizambo_magno", 2, 2, 2, 2, 2),
    LA_LOMGO(16, "la_lomgo", 2, 2, 9, 8, 10),
    GIORDANO_BRUNO(17, "giordano_bruno", 5, 5, 7, 6, 2),
    BRIGUS(18, "brigus", 3, 3, 3, 9, 9),
    GIOTARO_GIORGIO(19, "giotaro_giorgio", 4, 8, 4, 7, 4),
    LO_SGREVE(20, "lo_sgreve", 2, 2, 3, 4, 8),
    LA_CALLY(21, "la_cally", 6, 9, 2, 7, 2),
    IL_FIOSOFO(22, "il_filosofo", 4, 3, 5, 6, 5),
    LA_VELOCITA(23, "la_velocita", 2, 2, 2, 1, 10),
    GEPPETTO(24, "geppetto", 7, 7, 7, 7, 7);

    private final int id;
    private final Card card;

    /**
     * Constructor for define id, name and atribute of the card.
     * 
     * @param id is a key used for invoke the statistic of the card
     * @param name of the card
     * @param attk the atribute represent offensive power of the card
     * @param deff the atribute represent defence
     * @param strength the atribute ho represent hand to hand poewr of the card
     * @param intelligence the atribute rapresent intelligence of the card
     * @param stealth the atribute ho rapresent stealth
     */
    CardId(final int id, final String name, final int attk, final int deff, final int strength,
        final int intelligence, final int stealth) {
        this.id = id;
        this.card = new Card(name, attk, deff, strength, intelligence, stealth);
    }

    /**
     * Getter Method for return card.
     * 
     * @return card name and atribute
     */
    public Card getCardId() {
        return this.card;
    }

    /**
     * Getter for return number, 
     * for accsess to name and atribute of the card,
     * is used as key map or for replace it.
     * 
     * @return the id or key map number
     */
    public int getId() {
        return this.id;
    }

    /**
     * Builder for define all cards,
     * id become the key, put the name and atribute 
     * in list for to be used.
     * 
     * @return result, the map conteined all cards defined whit id as a key.
     */
    public static Map<Integer, Card> buildCardsMap() {
        final Map<Integer, Card> result = new HashMap<>();
        for (final CardId cardId : values()) {
            result.put(cardId.id, cardId.card);
        }
        return Collections.unmodifiableMap(result);
    }
}
