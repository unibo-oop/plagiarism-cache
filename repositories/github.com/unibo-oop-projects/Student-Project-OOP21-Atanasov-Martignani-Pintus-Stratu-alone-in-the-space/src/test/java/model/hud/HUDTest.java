package model.hud;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.Test;
import utilities.EnumInt;
import utilities.PowerUpEnum;
import view.GameMap;
import view.GameMapImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 */
public final class HUDTest {

    private HUDLife lifeHUD;
    private HUDPoints pointsHUD;
    private HUDPowerUp powerUpHUD;
    private AnchorPane gamePane;
    private GameMap gameMap;

    /**
     * Constructor.
     */
    public HUDTest(){
        new JFXPanel();
        this.gamePane = new AnchorPane();
        this.gameMap = new GameMapImpl(EnumInt.WIDTH.getValue(), EnumInt.HEIGHT.getValue());
        this.pointsHUD = new HUDPointsImpl();
        this.lifeHUD = new HUDLifeImpl();
        this.powerUpHUD = new HUDPowerUpImpl(this.gameMap);
    }

    @Test
    public void TestLifePointsBehaviour() {
        /*
         * At the beginning of the game, life points are sets to 100.
         */
        assertTrue(this.lifeHUD.getLifePoints() == EnumInt.LIFE_POINTS.getValue());
        assertTrue(this.lifeHUD.getGameStatus());
        System.out.println("Starting life points: " + this.lifeHUD.getLifePoints());
        System.out.println("Starting status: " + this.lifeHUD.getGameStatus() + "\n");

        /*
         * Multiple hits taken, Life points goes to 1. Game status is still true.
         */
        this.lifeHUD.update(1);
        assertTrue(this.lifeHUD.getLifePoints() == EnumInt.ONE.getValue());
        assertTrue(this.lifeHUD.getGameStatus());
        System.out.println("Life points: " + this.lifeHUD.getLifePoints());
        System.out.println("Status(life down): " + this.lifeHUD.getGameStatus() + "\n");

        /*
         * The ship was repaired, the life points went up. Game status is still true.
         */
        this.lifeHUD.update(10);
        assertTrue(this.lifeHUD.getLifePoints() == EnumInt.TEN.getValue());
        assertTrue(this.lifeHUD.getGameStatus());
        System.out.println("Life points: " + this.lifeHUD.getLifePoints());
        System.out.println("Status(life up): " + this.lifeHUD.getGameStatus() + "\n");

        /*
         * The ship was destroyed, the life points are 0 and the gameStatus is now false.
         */
        this.lifeHUD.update(0);
        assertTrue(this.lifeHUD.getLifePoints() == EnumInt.ZERO.getValue());
        assertFalse(this.lifeHUD.getGameStatus());
        System.out.println("Ending life points: " + this.lifeHUD.getLifePoints());
        System.out.println("Ending status: " + this.lifeHUD.getGameStatus() + "\n");

        /*
         * END OF THE GAME!
         */
    }

    @Test
    public void testPowerUpBehaviour() {
        assertFalse(this.powerUpHUD.getStatus(0));
        System.out.println("PowerUp " + 0  + " is" + this.powerUpHUD.getStatus(0));

        /*
         * Activate the powerUp
         */
        this.powerUpHUD.showPowerUp(PowerUpEnum.WeaponDamage);
        assertTrue(this.powerUpHUD.getStatus(PowerUpEnum.WeaponDamage.ordinal()));
        System.out.println("\n" + PowerUpEnum.WeaponDamage.toString() + " is activated? "
                           + this.powerUpHUD.getStatus(PowerUpEnum.WeaponDamage.ordinal()));

        /*
         * Deactivate the powerUp
         */
        this.powerUpHUD.hidePowerUp(PowerUpEnum.WeaponDamage);
        assertFalse(this.powerUpHUD.getStatus(PowerUpEnum.WeaponDamage.ordinal()));
        System.out.println("\n" + PowerUpEnum.WeaponDamage.toString() + " is activated? "
                + this.powerUpHUD.getStatus(PowerUpEnum.WeaponDamage.ordinal()));
    }

    @Test
    public void testPointsBehaviour() {
        /*
         * At the beginning of the game, points are set to 0.
         */
        assertTrue(this.pointsHUD.getPoints() == EnumInt.ZERO.getValue());
        System.out.println("Starting points: " + this.pointsHUD.getPoints());

        /*
         * Points increments after killing enemies. Your points are now: 10.
         */
        this.pointsHUD.update(10);
        assertTrue(this.pointsHUD.getPoints() == EnumInt.TEN.getValue());
        System.out.println("Points up by 10: " + this.pointsHUD.getPoints());

    }
}
