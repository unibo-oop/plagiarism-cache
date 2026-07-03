package it.unibo.roguekong.model.game;

import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.entity.impl.EnemyImpl;
import it.unibo.roguekong.model.value.Position;

import java.util.List;

public interface Level {
    List<EnemyImpl> getEnemies();
    Player getPlayer();
    Position getSpawnPoint();
    Position getEndPoint();
    boolean isLevelComplete();
    void checkIfPlayerIsOnEndPoint();
    void init();
}
