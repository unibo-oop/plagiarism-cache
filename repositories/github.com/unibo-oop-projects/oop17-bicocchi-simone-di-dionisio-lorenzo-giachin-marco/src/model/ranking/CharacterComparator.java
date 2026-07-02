package model.ranking;

import java.util.Comparator;

/**
 * a comparator of character's age.
 */
public class CharacterComparator implements Comparator<AbstractCharacter> {

    @Override
    public int compare(final AbstractCharacter character1, final AbstractCharacter character2) {
        return -Double.compare(character1.getSecond(), character2.getSecond());
    }

}
