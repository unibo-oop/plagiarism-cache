package it.unibo.monopoli.model.cards;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.MoveUpTo;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.table.Start;

/**
 * This is the enum of all the Imprevisti's {@link Card}.
 *
 */
public enum ImprevistiCards {

    /**
     * CARD 1.
     */
    CARD1("VENITE PAGATI $50 PER I VOSTRI SERVIZI", 1, new ToBePaid(50)),
    /**
     * CARD 2.
     */
    CARD2("ANDATE FINO AL PARCO DELLA VITTORIA: SE PASSATE DAL 'VIA' RITIRATE $" + Start.getMuchToPick(), 2),
    /**
     * CARD 3.
     */
    CARD3("AVANZATE FINO ALLA STAZIONE PIÙ VICINA: SE È LIBERA POTETE ACQUISTARLA DALLA BANCA; SE È POSSEDUTA DA UN'ALTRO GIOCATORE, PAGATE AL PROPIETARIO IL DOPPIO DELL'AFFITTO CHE GLI SPETTA NORMALMENTE",
            3, MoveUpTo.theNearestStation()),
    /**
     * CARD 4.
     */
    CARD4("USCITE GRATIS DI PRIGIONE, SE CI SIETE. POTETE CONSERVARE QUESTA CARTA FINO AL MOMENTO DI SERVIRVENE", 4),
    /**
     * CARD 5.
     */
    CARD5("MULTA PER ECCESSO DI VELOCITÀ. PAGATE $20", 5),
    /**
     * CARD 6.
     */
    CARD6("LA BANCA VI PAGHERÀ UN DIVIDENDO DI $50", 6, new ToBePaid(50)),
    /**
     * CARD 7.
     */
    CARD7("ANDATE DIRETTAMENTE IN PRIGIONE E SENZA PASSARE DAL 'VIA'", 7),
    /**
     * CARD 8.
     */
    CARD8("ESEGUITE LAVORI DI MANUTENZIONE SU TUTTI I VOSTRI EDIFICI: PAGATE $25 PER OGNI CASA E $100 PER OGNI ALBERGO CHE POSSIEDETE", 8),
    /**
     * CARD 9.
     */
    CARD9("SIETE STATI PROMOSSI ALLA PRESIDENZA DEL CONSIGLIO DI AMMINISTRAZIONE: PAGATE $50 AD OGNI GIOCATORE", 9),
    /**
     * CARD 10.
     */
    CARD10("ANDATE AVANTI FINO AL 'VIA' E RITIRATE $" + Start.getMuchToPick(), 10),
    /**
     * CARD 11.
     */
    CARD11("ANDATE FINO ALLA STAZIONE NORD: SE PASSATE DAL 'VIA' RITIRATE " + Start.getMuchToPick(), 11),
    /**
     * CARD 12.
     */
    CARD12("AVANZATE FINO ALLA STAZIONE PIÙ VICINA: SE È LIBERA POTETE ACQUISTARLA DALLA BANCA; SE È POSSEDUTA DA UN'ALTRO GIOCATORE, PAGATE AL PROPIETARIO IL DOPPIO DELL'AFFITTO CHE GLI SPETTA NORMALMENTE",
            12, MoveUpTo.theNearestStation()),
    /**
     * CARD 13.
     */
    CARD13("ANDATE FINO A LARGO COLOMBO: SE PASSATE DAL VIA RITIRATE $" + Start.getMuchToPick(), 13),
    /**
     * CARD 14.
     */
    CARD14("ANDATE FINO A CORSO ATENEO: SE PASSATE DAL VIA RITIRATE $" + Start.getMuchToPick(), 14),
    /**
     * CARD 15.
     */
    CARD15("AVANZATE FINO ALLA STAZIONE PIÙ VICINA: SE È LIBERA POTETE ACQUISTARLA DALLA BANCA; SE È POSSEDUTA DA UN'ALTRO GIOCATORE, PAGATE AL PROPIETARIO 10 VOLTE IL NUMERO FATTO CON I DADI",
            15, MoveUpTo.theNearestStation()),
    /**
     * CARD 16.
     */
    CARD16("MATURANO LE CEDOLE DEI VOSTRI FONDI IMMOBILIARI: INCASSATE $150", 16, new ToBePaid(150));

    private final String description;
    private final int cardId;
    private final Optional<List<Action>> actions;

    ImprevistiCards(final String description, final int id, final Action... actions) {
        this.description = description;
        this.cardId = id;
        this.actions = Optional.of(Arrays.asList(actions));
    }

    /**
     * Returns {@link Card}'s ID.
     * 
     * @return {@link Card}'s ID
     */
    public int getID() {
        return this.cardId;
    }

    /**
     * Returns {@link Card}'s description.
     * 
     * @return {@link Card}'s description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns an {@link Optional} {@link List} of {@link Action}s that the {@link Card} have to do.
     * 
     * @return a {@link Optional} {@link List} of {@link Card}'s {@link Action}s
     */
    public Optional<List<Action>> getActions() {
        return this.actions;
    }
}
