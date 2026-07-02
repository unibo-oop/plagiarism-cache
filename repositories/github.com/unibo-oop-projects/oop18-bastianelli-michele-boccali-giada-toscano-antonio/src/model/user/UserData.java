package model.user;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import enumerators.Level;
import enumerators.PlayerCharacter;

/**
 * User's data that need to be reminded.
 */

public class UserData implements Serializable, UserDataInterface {

    private static final transient long serialVersionUID = -884046000480293330L;

    private Level level;
    private int maxHeight;
    private int coin;
    private Set<PlayerCharacter> characters;
    private PlayerCharacter currentCharacter;

    public UserData() {
        this.level = Level.LEVEL_1;
        this.maxHeight = 0;
        this.coin = 0;
        this.characters = new HashSet<PlayerCharacter>();
        characters.add(PlayerCharacter.BIRD);
        currentCharacter = PlayerCharacter.BIRD;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(final Level level) {
        this.level = level;
    }

    @Override
    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public void setMaxHeight(final int MaxHeight) {
        this.maxHeight = MaxHeight;
    }

    @Override
    public HashSet<PlayerCharacter> getCharacters() {
        return new HashSet<>(characters);
    }

    @Override
    public PlayerCharacter getCurrentCharacter() {
        return currentCharacter;
    }

    @Override
    public void setCurrentCharacter(final PlayerCharacter currentCharacter) {
        this.currentCharacter = currentCharacter;
    }

    @Override
    public int getCoin() {
        return coin;
    }

    @Override
    public void setCoin(final int coin) {
        this.coin = coin;
    }

    @Override
    public void subtractCoin(final int coin) {
        if (coin >= 0 && coin <= this.coin) {
            this.coin -= coin;
        }
    }

    @Override
    public void addCoin(final int coin) {
        if (coin >= 0) {
            this.coin += coin;
        }
    }

    @Override
    public void setCharacters(final Set<PlayerCharacter> character) {
        this.characters = new HashSet<>(character);
    }

    @Override
    public void addCharacter(final PlayerCharacter character) {
        this.characters.add(character);
    }

    @Override
    public void deleteCharacter(final PlayerCharacter character) {
        this.characters.remove(character);
    }

    @Override
    public String toString() {
        return "level=" + level + ", MaxHeight=" + maxHeight + ", coin=" + coin + ", sprites=" + characters;
    }

}
