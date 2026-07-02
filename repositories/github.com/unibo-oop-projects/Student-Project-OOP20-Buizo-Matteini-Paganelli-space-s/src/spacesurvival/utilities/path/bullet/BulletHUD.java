package spacesurvival.utilities.path.bullet;

import spacesurvival.utilities.path.MainFolder;

public final class BulletHUD {

    /** 
     * Path to type folder.
     */
    public static final String TYPE_HUD = "/oblique";

    /** 
     * Path of icon fire bullet in HUD.
     */
    public static final String FIRE = MainFolder.BULLET + TYPE_HUD + "/fire.png";

    /** 
     * Path of icon ice bullet in HUD.
     */
    public static final String ICE = MainFolder.BULLET + TYPE_HUD + "/ice.png";

    /** 
     * Path of icon electric bullet in HUD.
     */
    public static final String ELECTRIC = MainFolder.BULLET + TYPE_HUD + "/electric.png";

    /** 
     * Path of icon normal bullet in HUD.
     */
    public static final String NORMAL = MainFolder.BULLET + TYPE_HUD + "/normal.png";

    private BulletHUD() {

    }
}
