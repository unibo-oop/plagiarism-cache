package model.cards;

import java.util.Set;

import com.google.common.collect.Sets;

import com.google.common.base.Preconditions;

import utilities.enumerations.CharacterCard;
import utilities.enumerations.RoomCard;
import utilities.enumerations.WeaponCard;

/**
 * Implementation of Solution interface.
 */
public final class SolutionImpl implements Solution {

    private static final long serialVersionUID = 1385620137344443291L;
    private final CharacterCard character;
    private final WeaponCard weapon;
    private final RoomCard room;

    /**
     * Creates a new SolutionImpl instance.
     * 
     * @param character
     *            character card
     * @param weapon
     *            weapon card
     * @param room
     *            room card
     */
    public SolutionImpl(final CharacterCard character, final WeaponCard weapon, final RoomCard room) {
        Preconditions.checkNotNull(character);
        Preconditions.checkNotNull(weapon);
        Preconditions.checkNotNull(room);
        this.character = character;
        this.weapon = weapon;
        this.room = room;
    }

    @Override
    public CharacterCard getCharacter() {
        return character;
    }

    @Override
    public WeaponCard getWeapon() {
        return weapon;
    }

    @Override
    public RoomCard getRoom() {
        return room;
    }

    @Override
    public Set<Card> getCards() {
        return Sets.newHashSet(this.character, this.weapon, this.room);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((character == null) ? 0 : character.hashCode());
        result = prime * result + ((room == null) ? 0 : room.hashCode());
        result = prime * result + ((weapon == null) ? 0 : weapon.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SolutionImpl other = (SolutionImpl) obj;
        if (character != other.character) {
            return false;
        }
        if (room != other.room) {
            return false;
        }
        return weapon == other.weapon;
    }

    @Override
    public String toString() {
        return "SolutionImpl [character=" + character + ", weapon=" + weapon + ", room=" + room + "]";
    }
}