package javawulf.view;

/**
 * Each element of this enum contains a relative path that can be used to reach
 * an image of a specific *View* element.
 * Images are stored in /resources directory
 */
public enum ImagePath {
    /** <img src="../../../resources/tiles/wall.png"> Wall tile. */
    WALL_TILE("/tiles/wall.png"),
    /** <img src="../../../resources/tiles/room.png"> Room tile. */
    ROOM_TILE("/tiles/room.png"),
    /** <img src="../../../resources/tiles/central_room.png"> Central room tile. */
    CENTRAL_ROOM_TILE("/tiles/central_room.png"),
    /** <img src="../../../resources/tiles/corridor.png"> Corridor tile. */
    CORRIDOR_TILE("/tiles/corridor.png"),
    /** <img src="../../../resources/tiles/portal.png"> Portal tile. */
    PORTAL_TILE("/tiles/portal.png"),
    /** <img src="../../../resources/player/player_up.png"> Player up. */
    PLAYER_UP("/player/player_up.png"),
    /** <img src="../../../resources/player/player_down.png"> Player down. */
    PLAYER_DOWN("/player/player_down.png"),
    /** <img src="../../../resources/player/player_left.png"> Player left. */
    PLAYER_LEFT("/player/player_left.png"),
    /** <img src="../../../resources/player/player_right.png"> Player right. */
    PLAYER_RIGHT("/player/player_right.png"),
    /** <img src="../../../resources/player/sword.png"> Sword. */
    SWORD("/player/sword.png"),
    /** <img src="../../../resources/player/greatsword.png"> Greatsword. */
    GREATSWORD("/player/greatsword.png"),
    /** <img src="../../../resources/player/health.png"> Health. */
    HEALTH("/player/health.png"),
    /** <img src="../../../resources/player/recoverable_health.png"> Recoverable health. */
    MAX_HEALTH("/player/recoverable_health.png"),
    /** <img src="../../../resources/player/shield.png"> Shield. */
    SHIELD("/player/shield.png"),
    /** <img src="../../../resources/enemies/pawn/pawn_up.png"> Pawn up. */
    PAWN_UP("/enemies/pawn/pawn_up.png"),
    /** <img src="../../../resources/enemies/pawn/pawn_down.png"> Pawn down. */
    PAWN_DOWN("/enemies/pawn/pawn_down.png"),
    /** <img src="../../../resources/enemies/pawn/pawn_left.png"> Pawn left. */
    PAWN_LEFT("/enemies/pawn/pawn_left.png"),
    /** <img src="../../../resources/enemies/pawn/pawn_right.png"> Pawn right. */
    PAWN_RIGHT("/enemies/pawn/pawn_right.png"),
    /** <img src="../../../resources/items/cure_max.png"> Max Cure. */
    CURE_MAX("/items/cure_max.png"),
    /** <img src="../../../resources/items/cure.png"> Cure. */
    CURE("/items/cure.png"),
    /** <img src="../../../resources/items/extra_heart.png"> Extra heart. */
    EXTRA_HEART("/items/extra_heart.png"),
    /** <img src="../../../resources/items/great_sword.png"> Greatsword. */
    GREAT_SWORD("/items/great_sword.png"),
    /** <img src="../../../resources/items/shield.png"> Shield. */
    SHIELD_ITEM("/items/shield.png"),
    /** <img src="../../../resources/items/amulet_piece.png"> Amulet Piece. */
    AMULET_PIECE("/items/amulet_piece.png"),
    /** <img src="../../../resources/powerUps/powerUpStrength.png"> Power Up Strength. */
    POWERUP_STRENGTH("/powerUps/powerUpStrength.png"),
    /** <img src="../../../resources/powerUps/powerUpInvincibility.png"> Power Up Invincibility. */
    POWERUP_INVINCIBILITY("/powerUps/powerUpInvincibility.png"),
    /** <img src="../../../resources/powerUps/powerUpDoublePoints.png"> Power Up Double points. */
    POWERUP_DOUBLEPOINTS("/powerUps/powerUpDoublePoints.png"),
    /** <img src="../../../resources/powerUps/powerUpSpeed.png"> Power Up Speed. */
    POWERUP_SPEED("/powerUps/powerUpSpeed.png");


    private final String path;

    ImagePath(final String path) {
        this.path = path;
    }

    /**
     * 
     * @return the path of the image related to the element.
     */
    public String getPath() {
        return this.path;
    }
}
