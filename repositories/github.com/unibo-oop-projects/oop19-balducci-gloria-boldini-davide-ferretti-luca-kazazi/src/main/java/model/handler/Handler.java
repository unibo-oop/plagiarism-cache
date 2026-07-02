package model.handler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import model.ID;
import model.Portal;
import model.gameObject.GameObject;
import model.player.Player;

public class Handler implements HandlerInterface {

    private final List<GameObject> gameObjectList;

    /**
     * Constructor for Handler.
     */
    public Handler() {
        this.gameObjectList = new LinkedList<>();
    }

    @Override
    public List<GameObject> getGameObjectList() {
        return this.gameObjectList;
    }

    @Override
    public void removeGameObject(final GameObject toRemove) {
        final List<GameObject> toDeleteList = new ArrayList<>();
        gameObjectList.stream().filter(p -> p == toRemove).forEach(p -> {
            toDeleteList.add(p);
        });
        gameObjectList.removeAll(toDeleteList);
    }

    @Override
    public void addGameObject(final GameObject toAdd) {
        gameObjectList.add(toAdd);
    }

    @Override
    public Player getPlayer() {
        return gameObjectList.stream().filter(p -> p.getId() == ID.PLAYER).map(p -> (Player) p).findFirst().get();
    }

    @Override
    public List<GameObject> getEnemyList() {
        return gameObjectList.stream().filter(p -> p.getId() == ID.ENEMY).collect(Collectors.toList());
    }

    @Override
    public List<GameObject> getWallList() {
        return gameObjectList.stream().filter(p -> p.getId() == ID.WALL).collect(Collectors.toList());
    }

    @Override
    public List<GameObject> getOtherList() {
        return gameObjectList.stream()
                .filter(p -> p.getId() != ID.WALL && p.getId() != ID.PLAYER && p.getId() != ID.ENEMY)
                .collect(Collectors.toList());
    }

    @Override
    public List<Portal> getPortalList() {
        return gameObjectList.stream().filter(p -> p.getId() == ID.PORTAL).map(p -> (Portal) p)
                .collect(Collectors.toList());
    }

}
