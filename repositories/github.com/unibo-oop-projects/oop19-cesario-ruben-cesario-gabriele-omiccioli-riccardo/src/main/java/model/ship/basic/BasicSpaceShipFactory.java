package model.ship.basic;

import model.entity.EntityID;
import model.entity.EntityID.EntityIDCategory;
import model.ship.basic.BasicEnemyShipImpl.BasicEnemyShipTemplate;
import model.ship.basic.BasicSpaceShipImpl.BasicSpaceShipTemplate;
import model.weapon.basic.BasicWeaponFactory;
import model.weapon.basic.BasicWeaponFactory.BasicWeaponVariant;
import utilities.exception.IllegalInitializationException;
import utilities.math.Point2D;
import utilities.math.Vector2D;
import utilities.math.Vector2DImpl;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Models a BasicSpaceShip factory that makes building ship more user-friendly.
 */
public class BasicSpaceShipFactory {

    private static final Map<EntityID, BasicEnemyShipTemplate> ENEMYMODEL_TABLE = new EnumMap<>(EntityID.class);
    private static final Map<EntityID, BasicSpaceShipTemplate> PLAYERMODEL_TABLE = new EnumMap<>(EntityID.class);
    private static final BasicWeaponFactory WEAPON_FACTORY = new BasicWeaponFactory();

    private static final Vector2D INITIAL_SPEED = new Vector2DImpl(0, 0);
    private static final double INITIAL_ROTATION = 0;

    static {
        initializeData();
    };

    /**
     * Builds an EnemyShip out of the specified model at the given position.
     * @param modelID : model of the ship to be built
     * @param position : position where the ship will be built
     * @return an EnemyShip of the specified model at the given position.
     */
    public BasicEnemyShip buildEnemyShip(final EntityID modelID, final Point2D position) {
        return new BasicEnemyShipImpl(position, INITIAL_ROTATION, INITIAL_SPEED, 
                                  ENEMYMODEL_TABLE.get(EntityID.requireBelonging(modelID, EntityIDCategory.SPACESHIPS_BASIC)));
    }


    /**
     * Builds a PlayerShip out of the specified model at the given position.
     * @param modelID : model of the ship to be built
     * @param position : position where the ship will be built
     * @param playerName : name of the player in control of this ship
     * @return a PlayerShip out of the specified model at the given position.
     */
    public BasicPlayerShip buildPlayerShip(final EntityID modelID, final Point2D position, final String playerName) {
        return new BasicPlayerShipImpl(position, INITIAL_ROTATION, INITIAL_SPEED, 
                                   PLAYERMODEL_TABLE.get(EntityID.requireBelonging(modelID, EntityIDCategory.SPACESHIPS_BASIC)), playerName);
    }

    /**
     * Initialises the tables that map each EntityID to their model both for enemy ships and for player ships.
     */
    /*
     * CONDITIONS FOR PROPER INITIALIZATION OF A SHIP TEMPLATE:
     * o EffectiveMaxSpeed = min(PotentialMaxSpeed, maxSpeed) -----> maxSpeed < PotentialMaxSpeed = maxAcceleration / drag
     * o ownedProjectile.speed > ownedProjectile.radius + maxSpeed (careful when assigning weapons or the ship will be able to auto-shoot itself)
     */
    //TODO: @SuppressWarnings("checkstyle:magicnumber"), maybe modifying .checkstyle
    private static void initializeData() {
        ENEMYMODEL_TABLE.putAll(
                Set.<BasicEnemyShipTemplate>of(

                        /*BasicEnemyShipTemplate(modelID, maxHealth, maxSpeed, maxAcceleration, maxAngularSpeed, radius, drag, weapons, scorepointValue)*/
                        /*------------------------|ID modello               |HP  |Spd.|Acl. |Angular Spd. |R.|Drag |Weapons                                                              |Score*/
                        new BasicEnemyShipTemplate(EntityID.SPACESHIP_BASIC, 100, 0.5, 0.05, Math.PI / 50, 2, 0.04, List.of(WEAPON_FACTORY.build(BasicWeaponVariant.DEFAULT_WEAPON)),     100),
                        new BasicEnemyShipTemplate(EntityID.FIGHTER,         100, 0.3, 0.02, Math.PI / 60, 2, 0.04, List.of(WEAPON_FACTORY.build(BasicWeaponVariant.ENEMY_HEAVY_CANNON)), 110),
                        new BasicEnemyShipTemplate(EntityID.JUGGERNAUT,      200, 0.1, 0.01, Math.PI / 70, 2, 0.04, List.of(WEAPON_FACTORY.build(BasicWeaponVariant.ENEMY_CANNON)),        80),
                        new BasicEnemyShipTemplate(EntityID.CUTTER,          50,  0.4, 0.03, Math.PI / 50, 2, 0.04, List.of(WEAPON_FACTORY.build(BasicWeaponVariant.ENEMY_LIGHT_CANNON)), 150)
                        /*------------------------------------------------------------------------------------------------------------------------------*/

                ).stream().map((enemyModel) -> Map.entry(enemyModel.getModelID(), enemyModel))
                .collect(Collectors.toMap((entry) -> entry.getKey(), (entry) -> entry.getValue()))
        );
        if (!ENEMYMODEL_TABLE.keySet().containsAll(EntityIDCategory.getEntityIDs(EntityIDCategory.SPACESHIPS_BASIC))) {
            throw new IllegalInitializationException("BasicSpaceShipFactory.initializeData: the enemy-model table doesn't have a key for each possible BasicSpaceShip ID.");
        }

        PLAYERMODEL_TABLE.putAll(
                Set.<BasicSpaceShipTemplate>of(

                        /*BasicSpaceShipTemplate(modelID, maxHealth, maxSpeed, maxAcceleration, maxAngularSpeed, radius, drag, weapons)*/
                        /*------------------------|ID modello               |HP   |Spd. |Acl. |Angular Spd. |R.|Drag |Weapons */
                        new BasicSpaceShipTemplate(EntityID.SPACESHIP_BASIC, 1000, 1.2,  0.05, Math.PI / 50, 2, 0.04, List.of(WEAPON_FACTORY.build(BasicWeaponVariant.DEFAULT_WEAPON))),
                        new BasicSpaceShipTemplate(EntityID.FIGHTER,         700,  0.95, 0.04, Math.PI / 60, 2, 0.04, List.of(WEAPON_FACTORY.build(BasicWeaponVariant.PLAYER_HEAVY_CANNON))),
                        new BasicSpaceShipTemplate(EntityID.JUGGERNAUT,      1500, 0.55, 0.03, Math.PI / 70, 2, 0.05, List.of(WEAPON_FACTORY.build(BasicWeaponVariant.PLAYER_CANNON))),
                        new BasicSpaceShipTemplate(EntityID.CUTTER,          1000, 1.2,  0.05, Math.PI / 50, 2, 0.03, List.of(WEAPON_FACTORY.build(BasicWeaponVariant.PLAYER_LIGHT_CANNON)))
                        /*---------------------------------------------------------------------------*/

                ).stream().map((playerModel) -> Map.entry(playerModel.getModelID(), playerModel))
                .collect(Collectors.toMap((entry) -> entry.getKey(), (entry) -> entry.getValue()))
        );
        if (!PLAYERMODEL_TABLE.keySet().containsAll(EntityIDCategory.getEntityIDs(EntityIDCategory.SPACESHIPS_BASIC))) {
            throw new IllegalInitializationException("BasicSpaceShipFactory.initializaData: the player-model table doesn't have a key for each possible BasicSpaceShip ID.");
        }
    }
}
