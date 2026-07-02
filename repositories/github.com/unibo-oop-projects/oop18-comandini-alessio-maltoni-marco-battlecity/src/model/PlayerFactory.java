package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import enums.Sprite;
import model.command.Command;
import model.common.Position;
import model.common.PositionImpl;
import model.entities.Tank;
import model.entities.TankImpl;
import model.entities.tankcomponents.BulletComponent;
import model.entities.tankcomponents.InputComponent;
import model.entities.tankcomponents.SpeedComponent;

/**
 * A factory who generate a player list.
 *
 */
public final class PlayerFactory {

    private static final int DEFAULT_LIFE = 2;
    private static final Position DEFAULT_FIRST_PLAYER_POSITION = new PositionImpl(8.0, 24.4);
    private static final Position DEFAULT_SECOND_PLAYER_POSITION = new PositionImpl(16.0, 24.4);
    private static final double DEFAULT_PLAYER_SPEED = 0.6;

    private PlayerFactory() {
    }

    /**
     * 
     * @param playersCommands a list of queue of commands input for the player.
     * @return the list of the implemented players.
     */
    public static List<Player> generate(final List<Queue<Command>> playersCommands) {
        final List<Player> players = new ArrayList<>();
        for (int i = 0; i < playersCommands.size(); i++) {
            final Queue<Command> pc = playersCommands.get(i);
            if (i == 0) { // FIRST PLAYER INIZIALIZATION
                players.add(generateFirstPlayer(pc));
            }
            if (i == 1) { // SECOND PLAYER INIZIALIZATION
                players.add(generateSecondPlayer(pc));
            }
        }
        return players;
    }

    private static Player generateFirstPlayer(final Queue<Command> playerCommand) {
        final Tank tank = new TankImpl(Players.FIRST.getSprite(), Players.FIRST.getPosition());
        tank.attach(new InputComponent(playerCommand, tank)).attach(new SpeedComponent(tank, DEFAULT_PLAYER_SPEED)).attach(new BulletComponent());
        return new PlayerImpl(DEFAULT_LIFE, tank);
    }

    private static Player generateSecondPlayer(final Queue<Command> playerCommand) {
        final Tank tank = new TankImpl(Players.SECOND.getSprite(), Players.SECOND.getPosition());
        tank.attach(new InputComponent(playerCommand, tank)).attach(new SpeedComponent(tank, DEFAULT_PLAYER_SPEED)).attach(new BulletComponent());
        return new PlayerImpl(DEFAULT_LIFE, tank);
    }

    private enum Players {
        FIRST(DEFAULT_FIRST_PLAYER_POSITION, Sprite.PLAYER_TANK_YELLOW),
        SECOND(DEFAULT_SECOND_PLAYER_POSITION, Sprite.PLAYER_TANK_GREEN);
        private Position position;
        private Sprite sprite;

        Players(final Position position, final Sprite sprite) {
            this.position = position;
            this.sprite = sprite;
        }

        Position getPosition() {
            return this.position;
        }

        Sprite getSprite() {
            return this.sprite;
        }

    }

}
