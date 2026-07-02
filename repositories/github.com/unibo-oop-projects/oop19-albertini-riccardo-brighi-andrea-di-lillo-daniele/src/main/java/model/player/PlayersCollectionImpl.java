package model.player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayersCollectionImpl implements java.io.Serializable, PlayersCollection{

    private static final long serialVersionUID = 1L;
    private List<Player> players;
    private String currentPlayer;

    //fare un costruttore e un metodo che accettino una lista di player
    private PlayersCollectionImpl(){
        players = new ArrayList<>();
        currentPlayer = "";
    }

    public void modifyPlayersBestScore(final String namePlayer, final int bestScore){
        this.players.stream().filter(i -> i.getName().equals(namePlayer)).findAny().get().setNewBestScore(bestScore);
    }

    @Override
    public void addPlayer(final Player player){
        if(containsPlayer(player.getName()))
            throw new IllegalStateException();
        players.add(player);
    }

    @Override
    public void addPlayer(String name) {
        this.players.add(new PlayerImpl(name));
    }

    @Override
    public List<Player> getPlayers(){
        return this.players;
    }

    //Static Factory: gestirli con Optional
    static public PlayersCollection getLeaderboard()  {
        //TODO controllare eccezioni di FileInputStream
        try(final ObjectInputStream in = new ObjectInputStream(new FileInputStream("./fileLeaderboard.ser"))){
            PlayersCollection coll = (PlayersCollection)in.readObject();
            return coll;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File non trovato");
            return createNewSerialization();
        }
    }

    static private PlayersCollection createNewSerialization() {
        try(final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./fileLeaderboard.ser"))){
            PlayersCollectionImpl list = new PlayersCollectionImpl();
            out.writeObject(list);
            System.out.println("File creato");
            return list;
        }
        catch (IOException e1){
            System.out.println("ERRORE");
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public Player getBestPlayer() {
        return this.players.stream().max((o1, o2) -> o2.getBestScore() - o1.getBestScore()).get();
    }

    @Override
    public Player getPlayer(String name) {
        if(this.containsPlayer(name))
            return this.players.stream().filter(i -> i.getName().equals(name)).findAny().get();
        throw new IllegalStateException();
    }

    @Override
    public void modifyPlayerBestScore(String name, int score) {
        //TODO
    }

    @Override
    public Optional<Player> getCurrentPlayer() {
        if(this.currentPlayer.isEmpty())
            return Optional.empty();
        else {
            return Optional.of(getPlayer(currentPlayer));
        }
    }

    @Override
    public void setCurrentPlayer(String name) {
        this.currentPlayer = name;
    }

    @Override
    public boolean containsPlayer(String name) {
        if(players.stream().map(i -> i.getName()).filter(i -> i.equals(name)).count() > 0)
            return true;
        else
            return false;
    }

    @Override
    public void update() {
        //TODO serializzazione di se stesso
        try(final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./fileLeaderboard.ser"))){
            out.writeObject(this);
        }
        catch (IOException e1){
            System.out.println("ERRORE");
        }
    }

    @Override
    public List<Player> getPlayersInOrder() {
        return this.players.stream().sorted((o1, o2) -> o2.getBestScore() - o1.getBestScore()).collect(Collectors.toList());
    }

    public String toString(){
        //TODO
        String c = "";
        for(Player p : players){
            c += p.getName() + "-";
        }
        return c;
    }
}
