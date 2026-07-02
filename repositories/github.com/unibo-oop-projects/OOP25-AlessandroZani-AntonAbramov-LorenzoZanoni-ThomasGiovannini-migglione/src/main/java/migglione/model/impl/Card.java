package migglione.model.impl;

/**
 * cards used in this game have a name and five atributes to choose during the game.
 */
public class Card {
    private final String name;
    private final int attk;
    private final int deff;
    private final int strength;
    private final int intelligence;
    private final int stealth;

    /**
     * Standard constructor fot define card name and the five atribute.
     * 
     * @param name name of the card 
     * @param attk the atribute represent offensive power of the card
     * @param deff the atribute represent defence 
     * @param strength the atribute ho represent hand to hand poewr of the card
     * @param intelligence the atribute rapresent intelligence of the card
     * @param stealth the atribute ho rapresent stealth
     */
    public Card(final String name, final int attk, final int deff,
        final int strength, final int intelligence, final int stealth) {
        this.name = name;
        this.attk = attk;
        this.deff = deff;
        this.strength = strength;
        this.intelligence = intelligence;
        this.stealth = stealth;
    }

    /**
     * Standard getter.
     * 
     * @return name of the card
     */
    public String getName() {
        return this.name;
    }

    /**
     * Standard getter.
     * 
     * @return attak atribute of the card
     */
    public int getAttk() {
        return this.attk;
    }

    /**
     * Standard getter.
     * 
     * @return atribute defence of the card
     */
    public int getDeff() {
        return this.deff;
    }

    /**
     * Standard getter.
     * 
     * @return atribute strength of the card
     */
    public int getStrength() {
        return this.strength;
    }

    /**
     * Standard getter.
     * 
     * @return atribute intelligence of the card
     */
    public int getIntelligence() {
        return this.intelligence;
    }

    /**
     * Standard getter.
     * 
     * @return atribute sthealth of the card
     */
    public int getStealth() {
        return this.stealth;
    }

    /**
     * Standard getter toString.
     * 
     * @return the card information, name and all atributes of the card
     */
    public String getCard() {
        return "Card= " 
        + "name: " + this.name
        + "attk: " + this.attk 
        + "deff: " + this.deff
        + "strength: " + this.strength
        + "intelligence: " + this.intelligence
        + "stealth: " + this.stealth;
    }
}
