package model.Stanza;

import java.io.Serializable;

import model.Oggetti.Cibo;

public enum CommonPianta implements Serializable{
    FAGIOLI("Fagioli", "Pianta di fagioli, favorita da altissima illuminazione", 3, 6, 12, 4, 8, Luminosita.ALTISSIMA, Cibo.FAGIOLO),
    CECI("Cece", "Pianta di ceci,  favorita da luce spenta", 6, 6, 11, 3, 6, Luminosita.SPENTA, Cibo.CECE),
    MELO("Melo", "Pianta di mele,  favorita da media illuminazione", 2, 8, 10, 5, 6, Luminosita.MEDIA, Cibo.MELA),
    PERO("Pero", "Pianta di pere,  favorita da bassa illuminazione", 2, 7, 9, 4, 5, Luminosita.BASSA, Cibo.PERA),
    BANANO("Banano", "Pianta di banane,  favorita da alta illuminazione", 2, 10, 17, 8, 12, Luminosita.ALTA, Cibo.BANANA),
    JUNGLANS("Junglas", "Pianta di noci,  favorita da altissima illuminazione", 4, 7, 11, 6, 12, Luminosita.ALTISSIMA, Cibo.NOCE),
    ARACHIDE("Arachide", "Pianta di noccioline,  favorita da media illuminazione", 5, 8, 13, 8, 13, Luminosita.MEDIA, Cibo.NOCCIOLINA),
    ZUCCHE("Zucca", "Pianta di zucche,  favorita da luce spenta", 1, 5, 11, 5, 10, Luminosita.MEDIA, Cibo.ZUCCA),
    ZUCCHINE("Zucchina", "Pianta di zucchine,  favorita da media illuminazione", 4, 7, 11, 6, 12, Luminosita.MEDIA, Cibo.ZUCCHINA),
    MANDORLO("Mandorlo", "Pianta di mandorle, favorita dal alta illuminazione", 3, 6, 8, 3, 2, Luminosita.ALTA, Cibo.MANDORLA),
    POMODORI("Pomodori", "Pianta di pomodori, favorita da bassa illuminazione", 2, 7, 10, 4, 4, Luminosita.BASSA, Cibo.POMODORO);
    
    private static final long serialVersionUID = -8971683480567763112L;
    private final String nome;
    private final String descrizione;
    private final Integer prodotti;
    private final Integer oreMaturazione;
    private final Integer oreMarcitura;
    private final Integer oreAcqua;
    private final Integer oreMorte;
    private final Luminosita lux;
    private final Cibo cibo;
    
    private CommonPianta(final String nome, final String descrizione, final Integer prodotti, final Integer oreMaturazione, final Integer oreMarcitura, final Integer oreAcqua, final Integer oreMorte, final Luminosita lux, final Cibo cibo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prodotti = prodotti;
        this.oreMaturazione = oreMaturazione;
        this.oreMarcitura = oreMarcitura;
        this.oreAcqua = oreAcqua;
        this.oreMorte = oreMorte;
        this.lux = lux;
        this.cibo = cibo;
    }
    /**
     * Getter method for the name of the plant
     * @return the string value of the name of the plant
     */
    public String getNome() {
        return this.nome;
    }
    /**
     * Getter method for the description of the plant
     * @return the string value of the description of the plant
     */
    public String getDescrizione() {
        return this.descrizione;
    }
    /**
     * Getter method for the number of fruits given
     * @return the integer value of the amount of the fruits given
     */
    public Integer getProdotti() {
        return this.prodotti;
    }
    public String toString() {
        return "Nome: " + this.nome + " produce: " + prodotti;
    }
    /**
     * Getter method for the hours in which the plant will be riped
     * @return the hours needed by the plant to ripe
     */
    public Integer getOreMaturazione() {
        return this.oreMaturazione;
    }
    /**
     * Getter method for the hours after which the plant is to be watered
     * @return the hours after which the plant is to be watered
     */
    public Integer getOreAcqua() {
        return this.oreAcqua;
    }
    /**
     * Getter method for the hours after which the plant will die
     * @return the hours after which the plant will die
     */
    public Integer getOreMorte() {
        return this.oreMorte;
    }
    /**
     * Getter method for the hours after which the plant will rot
     * @return the hours after which the plant will rot
     */
    public Integer getOreMarcitura() {
        return this.oreMarcitura;
    }
    /**
     * Getter method for the lux needed by the plant to grow faster
     * @return the lux needed by the plant to grow faster
     */
    public Luminosita getLux() {
        return this.lux;
    }
    
    /**
     * Getter method for the Food associated
     * @return the food associated with the plant
     */
    public Cibo getCibo() {
        return this.cibo;
    }
}
