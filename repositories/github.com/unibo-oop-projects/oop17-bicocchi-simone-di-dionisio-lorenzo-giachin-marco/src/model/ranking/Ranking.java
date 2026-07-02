package model.ranking;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * class for manage the ranking of characters death.
 */
public class Ranking implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6043844631152928647L;
    private List<AbstractCharacter> characters;

    /**
     * constructor of this class.
     * 
     * @param list
     *            is the new list
     */
    public Ranking(final List<AbstractCharacter> list) {
        this.characters = list;
    }

    /**
     * 
     * @param character
     *            is the character to add
     */
    public void addCharacter(final AbstractCharacter character) {
        this.characters.add(character);
        Collections.sort(this.characters, new CharacterComparator());
    }

    @Override
    public String toString() {
        String text = "Ranking: \n";
        for (AbstractCharacter c : this.characters) {
            text += c.getFirst() + " età: " + c.getSecond() + "\n";
        }
        return text;
    }

    /**
     * 
     * @return list
     */
    public List<AbstractCharacter> getList() {
        return this.characters;
    }
}
