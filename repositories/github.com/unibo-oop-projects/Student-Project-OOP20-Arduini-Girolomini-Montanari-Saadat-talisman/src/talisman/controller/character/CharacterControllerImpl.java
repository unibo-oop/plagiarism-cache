package talisman.controller.character;

import talisman.model.character.CharacterModelImpl;
import talisman.model.character.PlayerModelImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of the MVC controller for the players
 *
 * @author Enrico Maria Montanari
 */
public class CharacterControllerImpl implements CharactersController {

    private int currentPlayer;
    private int activePlayers;

    public CharacterControllerImpl(){

        currentPlayer = 0;
        activePlayers = 0;
    }

    @Override
    public PlayerModelImpl getCurrentPlayer() {

        return Players.getPlayer(currentPlayer);
    }

    @Override
    public void setCurrentPlayer(int index) {

        if (index >= activePlayers) currentPlayer = 0;
        else currentPlayer = index;
    }

    @Override
    public void addPlayer(CharacterModelImpl character) {

        Players.addPlayer(character, ++activePlayers);
    }

    @Override
    public void removePlayer(int index) {

        Players.removePlayer(index);
        activePlayers--;
    }

    @Override
    public PlayerModelImpl getCrownPlayer() {

        for (PlayerModelImpl player : Players.getPlayers()){

            if (player.hasCrown()) return player;
        }

        return null;
    }

    @Override
    public int getActivePlayers(){

        return activePlayers;
    }

    @Override
    public List<PlayerModelImpl> getPlayers(){

        return Players.getPlayers();
    }
}

/**
 * Utility class to store all registered players in the game
 *
 * @author Enrico Maria Montanari
 */
class Players {

    private static List<PlayerModelImpl> players = new ArrayList<>();
    private static int lastId = 0;

    static void addPlayer(CharacterModelImpl character, int activePlayers){

        players.add(new PlayerModelImpl(activePlayers, lastId++, character));
    }

    static PlayerModelImpl getPlayer(int index){

        return players.get(index);
    }

    static void removePlayer(int index){

        players.remove(index);
    }

    static List<PlayerModelImpl> getPlayers(){

        return List.copyOf(players);
    }
}
