package fargoal.model.entity.monsters.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fargoal.commons.api.Position;
import fargoal.model.entity.monsters.api.Monster;
import fargoal.model.entity.monsters.api.MonsterType;
import fargoal.model.entity.player.api.Player;
import fargoal.model.manager.api.FloorManager;

/**
 * Class to allow monster to move, trying to reach
 * the player (if in range of visibility).
 */
public final class Ai {

    private static final Integer MAX_SPIDER_TRY = 100;
    private static final Integer MAX_CACHE_MONSTER = 5;
    private static final Integer LIST_SIZE = 3;
    private static final Integer MAX_DISTANCE = 10;
    private static final List<Integer> LIST = new ArrayList<>(List.of(-1, 0, 1));
    private static final Random RANDOM = new Random();

    private Ai() {

    }

    private static boolean isNeighbourTile(final Monster monster, final Position pos) {
        return monster.getFloorMap().isTile(pos.add(new Position(1, 0)))
                || monster.getFloorMap().isTile(pos.add(new Position(0, 1)))
                || monster.getFloorMap().isTile(pos.add(new Position(-1, 0)))
                || monster.getFloorMap().isTile(pos.add(new Position(0, -1)))
                || monster.getFloorMap().isTile(pos.add(new Position(1, 1)))
                || monster.getFloorMap().isTile(pos.add(new Position(-1, -1)))
                || monster.getFloorMap().isTile(pos.add(new Position(-1, 1)))
                || monster.getFloorMap().isTile(pos.add(new Position(1, -1))); 
    }

    private static boolean isInsideMap(final Monster monster, final Position pos) {
        return pos.x() < monster.getFloorMap().getSize().width()
                && pos.y() < monster.getFloorMap().getSize().height()
                && pos.x() >= 0
                && pos.y() >= 0
                && isNeighbourTile(monster, pos)
                && !monster.getPosition().equals(pos);
    }

    /**
     * Method that actualy moves the monster towards the
     * player, or otherwise randomly.
     * 
     * @param monster - the monster to be moved
     * @param player - the player to be reached
     * @param floorManager - the manager containing all the information needed
     */
    public static void move(final Monster monster, final Player player, final FloorManager floorManager) {
        List<Position> possibleDirections = Stream.of(new Position(-1, -1), new Position(0, -1), new Position(1, -1),
                new Position(-1, 0), new Position(1, 0),
                new Position(1, 1), new Position(0, 1), new Position(-1, 1))
                .map(p -> p.add(monster.getPosition()))
                .collect(Collectors.toList());

        final List<Position> positionList = new ArrayList<>();
        floorManager.getMonsters().forEach(p -> positionList.add(p.getPosition()));

        final int xDistance = Math.abs(monster.getPosition().x() - player.getPosition().x());
        final int yDistance = Math.abs(monster.getPosition().y() - player.getPosition().y());
        boolean xMonsterBigger = false;
        boolean yMonsterBigger = false;
        Position pos;
        boolean check = false;
        if (monster.getPosition().x() >= player.getPosition().x()) {
            xMonsterBigger = true;
        }
        if (monster.getPosition().y() >= player.getPosition().y()) {
            yMonsterBigger = true;
        }

        //rimuovo le posizioni deglia altri mostri e del player
        possibleDirections.removeAll(positionList);
        if (possibleDirections.stream()
                .anyMatch(p -> p.equals(player.getPosition()))) {
                    possibleDirections.remove(player.getPosition());
                }

        //se è un ragno...
        if (monster.getMonsterType().equals(MonsterType.SPIDER)) {
            //solo se è lontano dal player tiene conto della cache
            if (xDistance > MAX_DISTANCE || yDistance > MAX_DISTANCE) {
                possibleDirections.removeAll(monster.getLastPositions());
            }
            possibleDirections = possibleDirections.stream()
                    .filter(p -> isInsideMap(monster, p))
                    .collect(Collectors.toList());
        //altrimenti...
        } else {
            //solo se è lontano dal player tiene conto della cache
            if (xDistance > MAX_DISTANCE || yDistance > MAX_DISTANCE) {
                possibleDirections.removeAll(monster.getLastPositions());
            }
            possibleDirections = possibleDirections.stream()
                    .filter(p -> monster.getFloorMap().isTile(p))
                    .collect(Collectors.toList());
        }
        //se è vuota, la ririempio senza togliere le posizioni della cache
        if (possibleDirections.isEmpty()) {
            possibleDirections = Stream.of(new Position(-1, -1), new Position(0, -1), new Position(1, -1),
                    new Position(-1, 0), new Position(1, 0),
                    new Position(1, 1), new Position(0, 1), new Position(-1, 1))
                    .map(p -> p.add(monster.getPosition()))
                    .filter(p -> monster.getFloorMap().isTile(p))
                    .collect(Collectors.toList());
            possibleDirections.removeAll(positionList);
            if (possibleDirections.stream()
                .anyMatch(p -> p.equals(player.getPosition()))) {
                    possibleDirections.remove(player.getPosition());
                }
        }
        //controllo se il mostro vede il player
        if (monster.getMonsterType().equals(MonsterType.SPIDER)) {
            if (xDistance < MAX_DISTANCE && yDistance < MAX_DISTANCE && floorManager.getPlayer().isVisible()) {
                if (xDistance >= yDistance) {
                    if (xMonsterBigger) {
                        pos = monster.getPosition().decreaseX();
                        if (possibleDirections.contains(pos)) {
                            check = true;
                            monster.setPosition(pos);
                            //aggiorno la cache delle posizioni del mostro
                            if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                                monster.removeLastPosition();
                                monster.addFirstPosition(pos);
                            } else {
                                monster.addFirstPosition(pos);
                            }
                        }
                    } else {
                        pos = monster.getPosition().increaseX();
                        if (possibleDirections.contains(pos)) {
                            check = true;
                            monster.setPosition(pos);
                            if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                                monster.removeLastPosition();
                                monster.addFirstPosition(pos);
                            } else {
                                monster.addFirstPosition(pos);
                            }
                        }
                    }
                } else {
                    if (yMonsterBigger) {
                        pos = monster.getPosition().decreaseY();
                        if (possibleDirections.contains(pos)) {
                            check = true;
                            monster.setPosition(pos);
                            if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                                monster.removeLastPosition();
                                monster.addFirstPosition(pos);
                            } else {
                                monster.addFirstPosition(pos);
                            }
                        }
                    } else {
                        pos = monster.getPosition().increaseY();
                        if (possibleDirections.contains(pos)) {
                            check = true;
                            monster.setPosition(pos);
                            if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                                monster.removeLastPosition();
                                monster.addFirstPosition(pos);
                            } else {
                                monster.addFirstPosition(pos);
                            }
                        }
                    }
                }
            }
            if (!check) {
                var max = 0;
                do {
                    max++;
                    pos = monster.getPosition()
                            .add(new Position(LIST.get(RANDOM.nextInt(LIST_SIZE)), LIST.get(RANDOM.nextInt(LIST_SIZE))));
                } while (max < MAX_SPIDER_TRY 
                        && (isInsideMap(monster, pos) 
                                || !positionList.contains(pos) 
                                || !monster.getLastPositions().contains(pos)));
                monster.setPosition(pos);
            }
        } else if (xDistance < MAX_DISTANCE && yDistance < MAX_DISTANCE && floorManager.getPlayer().isVisible()) {
            //nel caso sia piu lontano nelle ascisse
            if (xDistance >= yDistance) {
                //controllo se l'ascissa del mostro è maggiore di quella del player
                if (xMonsterBigger) {
                    pos = monster.getPosition().decreaseX();
                    if (possibleDirections.contains(pos)) {
                        monster.setPosition(pos);
                        check = true;
                        if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                            monster.removeLastPosition();
                            monster.addFirstPosition(pos);
                        } else {
                            monster.addFirstPosition(pos);
                        }
                        //se la posizione è un muro, allora controllo se mi posso muovere nelle ordinate
                    } else if (yMonsterBigger) {
                        pos = monster.getPosition().decreaseY();
                        if (possibleDirections.contains(pos)) {
                            monster.setPosition(pos);
                            check = true;
                            if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                                monster.removeLastPosition();
                                monster.addFirstPosition(pos);
                            } else {
                                monster.addFirstPosition(pos);
                            }
                        }
                    } else {
                        pos = monster.getPosition().increaseY();
                        if (possibleDirections.contains(pos)) {
                            monster.setPosition(pos);
                            check = true;
                            if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                                monster.removeLastPosition();
                                monster.addFirstPosition(pos);
                            } else {
                                monster.addFirstPosition(pos);
                            }
                        }
                    }
                //se invece è minore
                } else {
                    pos = monster.getPosition().increaseX();
                    if (possibleDirections.contains(pos)) {
                        monster.setPosition(pos);
                        check = true;
                        if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                            monster.removeLastPosition();
                            monster.addFirstPosition(pos);
                        } else {
                            monster.addFirstPosition(pos);
                        }
                        //se la posizione è un muro, allora controllo se mi posso muovere nelle ordinate
                    } else if (yMonsterBigger) {
                        pos = monster.getPosition().decreaseY();
                        if (possibleDirections.contains(pos)) {
                            monster.setPosition(pos);
                            check = true;
                            if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                                monster.removeLastPosition();
                                monster.addFirstPosition(pos);
                            } else {
                                monster.addFirstPosition(pos);
                            }
                        }
                    } else {
                        pos = monster.getPosition().increaseY();
                        if (possibleDirections.contains(pos)) {
                            monster.setPosition(pos);
                            check = true;
                            if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                                monster.removeLastPosition();
                                monster.addFirstPosition(pos);
                            } else {
                                monster.addFirstPosition(pos);
                            }
                        }
                    }
                }
            //se invece è piu lontano nelle ordinate
            } else {
                //controllo se l'ordinata del mostro è maggiore di quella del player
                if (yMonsterBigger) {
                    pos = monster.getPosition().decreaseY();
                    if (possibleDirections.contains(pos)) {
                        monster.setPosition(pos);
                        check = true;
                        if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                            monster.removeLastPosition();
                            monster.addFirstPosition(pos);
                        } else {
                            monster.addFirstPosition(pos);
                        }
                        //se la posizione è un muro, allora controllo se mi posso muovere nelle ascisse
                    } else if (xMonsterBigger) {
                        pos = monster.getPosition().decreaseX();
                        if (possibleDirections.contains(pos)) {
                            monster.setPosition(pos);
                            check = true;
                            if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                                monster.removeLastPosition();
                                monster.addFirstPosition(pos);
                            } else {
                                monster.addFirstPosition(pos);
                            }
                        }
                    } else {
                        pos = monster.getPosition().increaseX();
                        if (possibleDirections.contains(pos)) {
                            monster.setPosition(pos);
                            check = true;
                            if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                                monster.removeLastPosition();
                                monster.addFirstPosition(pos);
                            } else {
                                monster.addFirstPosition(pos);
                            }
                        }
                    }
                //se invece è minore
                } else {
                    pos = monster.getPosition().increaseY();
                    if (possibleDirections.contains(pos)) {
                        monster.setPosition(pos);
                        check = true;
                        if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                            monster.removeLastPosition();
                            monster.addFirstPosition(pos);
                        } else {
                            monster.addFirstPosition(pos);
                        }
                        //se la posizione è un muro, allora controllo se mi posso muovere nelle ascisse
                    } else if (xMonsterBigger) {
                        pos = monster.getPosition().decreaseX();
                        if (possibleDirections.contains(pos)) {
                            monster.setPosition(pos);
                            check = true;
                            if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                                monster.removeLastPosition();
                                monster.addFirstPosition(pos);
                            } else {
                                monster.addFirstPosition(pos);
                            }
                        }
                    } else {
                        pos = monster.getPosition().increaseX();
                        if (possibleDirections.contains(pos)) {
                            monster.setPosition(pos);
                            check = true;
                            if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                                monster.removeLastPosition();
                                monster.addFirstPosition(pos);
                            } else {
                                monster.addFirstPosition(pos);
                            }
                        }
                    }
                }
            }
        }
        if (!check && !possibleDirections.isEmpty()) {
            pos = possibleDirections.get(RANDOM.nextInt(possibleDirections.size()));
            monster.setPosition(pos);
            if (monster.getLastPositions().size() == MAX_CACHE_MONSTER) {
                monster.removeLastPosition();
                monster.addFirstPosition(pos);
            } else {
                monster.addFirstPosition(pos);
            }
        }
    }
}
