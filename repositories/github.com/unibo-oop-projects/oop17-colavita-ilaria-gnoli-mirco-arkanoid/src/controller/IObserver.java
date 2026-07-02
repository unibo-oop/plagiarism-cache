package controller;

import java.util.List;

import model.entities.IEntity;

public interface IObserver {
    void updateScoreLives(int score, int lives);

    void updateAllEntities(List<IEntity> entities);

    /*
    void addEntity(IEntity ent);

    void removeEntity(IEntity ent);
    */

}
