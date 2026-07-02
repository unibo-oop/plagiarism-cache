package fargoal.controller.input.api;

import java.util.Optional;

import fargoal.commons.api.Position;
import fargoal.model.entity.player.api.Player;
import fargoal.model.interactable.api.Interactable;
import fargoal.model.manager.api.FloorManager;
import fargoal.model.manager.api.SceneManager;

/**
 * Class that refresh everything that the player does or interact with.
 */
public class PlayerInputComponent implements InputComponent {

    /**
     * Method that based on the inputs processed that he received, chooses what action
     * the player has to do.
     * 
     * @param sceneManager - the manager that has all the information needed, this needs to be a FloorManager
     * @param controller - to receive the processed inputs
     * @throws IllegalArgumentException if the given manager is not a {@link FloorManager}
     */
    @Override
    public void update(final SceneManager sceneManager, final InputController controller) {
        if (!(sceneManager instanceof FloorManager)) {
            throw new IllegalArgumentException("Expected a FloorManager");
        }
        final FloorManager manager = (FloorManager) sceneManager;
        final Player player = manager.getPlayer();

        if (controller.isInteracting()) {
            final Optional<Interactable> interacting = manager.getInteractables()
                    .stream()
                    .filter(element -> player.getPosition().equals(element.getPosition()))
                    .findAny();
            interacting.ifPresent(element -> element.interact(manager));
        } else if (controller.isUsingHealingPotion()) {
            if (player.getInventory().numberHealingPotions() > 0) {
                player.getInventory().useHealingPotion();
            }
        } else if (controller.isPlacingBeacon()) {
            if (player.getInventory().numberBeacons() > 0) {
                player.getInventory().useBeacon();
            }
        } else if (controller.isUsingDriftSpell()) {
            if (player.getInventory().numberDriftSpells() > 0) {
                player.getInventory().useDriftSpell();
            }
        } else if (controller.isUsingInvisibilitySpell()) {
            if (player.getInventory().numberInvisibilitySpells() > 0) {
                player.getInventory().useInvisibilitySpell();
            }
        } else if (controller.isUsingLightSpell()) {
            if (player.getInventory().numberLightSpells() > 0) {
                player.getInventory().useLightSpell();
            }
        } else  if (controller.isTurningLight()) {
            player.getInventory().turnLight();
        } else if (controller.isUsingRegenerationSpell()) {
            if (player.getInventory().numberRegenerationSpell() > 0) {
                player.getInventory().useRegenerationSpell();
            }
        } else if (controller.isUsingShieldSpell()) {
            if (player.getInventory().numberShieldSpells() > 0) {
                player.getInventory().useShieldSpell();
            }
        } else if (controller.isUsingTeleportSpell()) {
            if (player.getInventory().numberTeleportSpells() > 0) {
                player.getInventory().useTeleportSpell();
            }
        } else if (controller.isMoveUp() && controller.isMoveRight()) {
            final var pos = player.getPosition().add(new Position(1, -1));
            if (manager.getMonsters()
                    .stream()
                    .anyMatch(p -> p.getPosition().equals(pos))) {
                        for (final var monster : manager.getMonsters()) {
                            if (monster.getPosition().equals(pos)) {
                                monster.setIsFighting(true);
                                player.battle(monster);
                                break;
                            }
                        }
                    } else if (manager.getFloorMap().isTile(pos)) {
                        player.move(pos);
                    }
        } else if (controller.isMoveRight() && controller.isMoveDown()) {
            final var pos = player.getPosition().add(new Position(1, 1));
            if (manager.getMonsters()
                    .stream()
                    .anyMatch(p -> p.getPosition().equals(pos))) {
                        for (final var monster : manager.getMonsters()) {
                            if (monster.getPosition().equals(pos)) {
                                monster.setIsFighting(true);
                                player.battle(monster);
                                break;
                            }
                        }
                    } else if (manager.getFloorMap().isTile(pos)) {
                        player.move(pos);
                    }
        } else if (controller.isMoveDown() && controller.isMoveLeft()) {
            final var pos = player.getPosition().add(new Position(-1, 1));
            if (manager.getMonsters()
                    .stream()
                    .anyMatch(p -> p.getPosition().equals(pos))) {
                        for (final var monster : manager.getMonsters()) {
                            if (monster.getPosition().equals(pos)) {
                                monster.setIsFighting(true);
                                player.battle(monster);
                                break;
                            }
                        }
                    } else if (manager.getFloorMap().isTile(pos)) {
                        player.move(pos);
                    }
        } else if (controller.isMoveLeft() && controller.isMoveUp()) {
            final var pos = player.getPosition().add(new Position(-1, -1));
            if (manager.getMonsters()
                    .stream()
                    .anyMatch(p -> p.getPosition().equals(pos))) {
                        for (final var monster : manager.getMonsters()) {
                            if (monster.getPosition().equals(pos)) {
                                monster.setIsFighting(true);
                                player.battle(monster);
                                break;
                            }
                        }
                    } else if (manager.getFloorMap().isTile(pos)) {
                        player.move(pos);
                    }
        } else if (controller.isMoveDown()) {
            final var pos = player.getPosition().add(new Position(0, 1));
            if (manager.getMonsters()
                    .stream()
                    .anyMatch(p -> p.getPosition().equals(pos))) {
                        for (final var monster : manager.getMonsters()) {
                            if (monster.getPosition().equals(pos)) {
                                monster.setIsFighting(true);
                                player.battle(monster);
                                break;
                            }
                        }
                    } else if (manager.getFloorMap().isTile(pos)) {
                        player.move(pos);
                    }
        } else if (controller.isMoveUp()) {
            final var pos = player.getPosition().add(new Position(0, -1));
            if (manager.getMonsters()
                    .stream()
                    .anyMatch(p -> p.getPosition().equals(pos))) {
                        for (final var monster : manager.getMonsters()) {
                            if (monster.getPosition().equals(pos)) {
                                monster.setIsFighting(true);
                                player.battle(monster);
                                break;
                            }
                        }
                    } else if (manager.getFloorMap().isTile(pos)) {
                        player.move(pos);
                    }
        } else if (controller.isMoveLeft()) {
            final var pos = player.getPosition().add(new Position(-1, 0));
            if (manager.getMonsters()
                    .stream()
                    .anyMatch(p -> p.getPosition().equals(pos))) {
                        for (final var monster : manager.getMonsters()) {
                            if (monster.getPosition().equals(pos)) {
                                monster.setIsFighting(true);
                                player.battle(monster);
                                break;
                            }
                        }
                    } else if (manager.getFloorMap().isTile(pos)) {
                        player.move(pos);
                    }
        } else if (controller.isMoveRight()) {
            final var pos = player.getPosition().add(new Position(1, 0));
            if (manager.getMonsters()
                    .stream()
                    .anyMatch(p -> p.getPosition().equals(pos))) {
                        for (final var monster : manager.getMonsters()) {
                            if (monster.getPosition().equals(pos)) {
                                monster.setIsFighting(true);
                                player.battle(monster);
                                break;
                            }
                        }
                    } else if (manager.getFloorMap().isTile(pos)) {
                        player.move(pos);
                    }
        }

    }

}
