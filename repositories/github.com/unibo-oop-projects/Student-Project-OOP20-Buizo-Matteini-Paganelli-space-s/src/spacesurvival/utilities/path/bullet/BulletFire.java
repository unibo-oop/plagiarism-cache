package spacesurvival.utilities.path.bullet;

import spacesurvival.utilities.path.MainFolder;

public final class BulletFire {
    /** 
     * Path of hud bullet.
     */
    public static final String TYPE_HUD = "/vertical";

    /** 
     * Path of fire hud bullet.
     */
    public static final String FIRE = MainFolder.BULLET + TYPE_HUD + "/fire.png";

    /** 
     * Path of ice hud bullet.
     */
    public static final String ICE = MainFolder.BULLET + TYPE_HUD + "/ice.png";

    /** 
     * Path of electric hud bullet.
     */
    public static final String ELECTRIC = MainFolder.BULLET + TYPE_HUD + "/electric.png";

    /** 
     * Path of fire normal bullet.
     */
    public static final String NORMAL = MainFolder.BULLET + TYPE_HUD + "/normal.png";

    private BulletFire() {

    }
}
