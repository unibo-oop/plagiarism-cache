package boxhead.model.entities.gun;

import boxhead.model.entities.Player;
import boxhead.model.entities.gun.Gun.GunType;
import boxhead.view.GameView;

/**
 * Class that is used to manage the upgrades of the weapons.
 */
public class GunUpgradeManager {
	
	private static final int PISTOL_DAMAGE_UPGRADE_1 = 60;
	private static final int PISTOL_DAMAGE_UPGRADE_2 = 90;
    private static final long PISTOL_RATE_OF_FIRE_UPGRADE_1 = 600;
    private static final long PISTOL_RATE_OF_FIRE_UPGRADE_2 = 300;
    private static final int PISTOL_MAGAZINE_UPGRADE_1 = 30;
    private static final int PISTOL_MAGAZINE_UPGRADE_2 = 60;
    
    private static final int UZI_DAMAGE_UPGRADE_1 = 40;
    private static final int UZI_DAMAGE_UPGRADE_2 = 65;
    private static final long UZI_RATE_OF_FIRE_UPGRADE_1 = 250;
    private static final long UZI_RATE_OF_FIRE_UPGRADE_2 = 100;
    private static final int UZI_MAGAZINE_UPGRADE_1 = 50;
    private static final int UZI_MAGAZINE_UPGRADE_2= 100;
    
    private static final int SHOTGUN_DAMAGE_UPGRADE_1 = 75;
    private static final int SHOTGUN_DAMAGE_UPGRADE_2 = 100;
    private static final long SHOTGUN_RATE_OF_FIRE_UPGRADE_1 = 600;
    private static final long SHOTGUN_RATE_OF_FIRE_UPGRADE_2 = 300;
    private static final int SHOTGUN_MAGAZINE_UPGRADE_1 = 20;
    private static final int SHOTGUN_MAGAZINE_UPGRADE_2 = 40;
	
	private final GameView gameView;
	private final Player player;
	
	public GunUpgradeManager(final GameView gameView, final Player player) {
		this.gameView = gameView;
		this.player = player;
	}
	
	public final void checkUpgrades(final int streak) {
		switch(streak) {
		case 3:
			this.player.getSelectedGun(GunType.PISTOL).updateRateOfFire(PISTOL_RATE_OF_FIRE_UPGRADE_1);
			gameView.renderGunUpgrade("Pistol+: Faster Fire");
		break;
		case 5:
			this.player.getSelectedGun(GunType.PISTOL).updateDamage(PISTOL_DAMAGE_UPGRADE_1);
			gameView.renderGunUpgrade("Pistol+: More Damage");
		break;
		case 8:
			this.player.getSelectedGun(GunType.PISTOL).updateMagazine(PISTOL_MAGAZINE_UPGRADE_1);
			this.gameView.renderGunUpgrade("Pistol+: Double Ammo");
		break;
		case 10:
			this.player.unlockGun(new GunFactory().getGun(this.player.getPosition(), GunType.UZI));
			this.gameView.renderGunUnlock("New Weapon: UZI");
		break;
		case 13:
			this.player.getSelectedGun(GunType.PISTOL).updateDamage(PISTOL_DAMAGE_UPGRADE_2);
			this.gameView.renderGunUpgrade("Pistol+: Even More Damage");
		break;
		case 17:
			this.player.getSelectedGun(GunType.UZI).updateMagazine(UZI_MAGAZINE_UPGRADE_1);
			this.gameView.renderGunUpgrade("UZI+: Double Ammo");
		break;
		case 18:
			this.player.getSelectedGun(GunType.UZI).updateDamage(UZI_DAMAGE_UPGRADE_1);
			this.gameView.renderGunUpgrade("UZI+: More Damage");
		break;
		case 20:
			this.player.unlockGun(new GunFactory().getGun(this.player.getPosition(), GunType.SHOTGUN));
			this.gameView.renderGunUnlock("New Weapon: Shotgun");
		break;
		case 21:
			this.player.getSelectedGun(GunType.UZI).updateRateOfFire(UZI_RATE_OF_FIRE_UPGRADE_1);
			this.gameView.renderGunUpgrade("UZI+: Faster Fire");
		break;
		case 23:
			this.player.getSelectedGun(GunType.SHOTGUN).updateMagazine(SHOTGUN_MAGAZINE_UPGRADE_1);
			this.gameView.renderGunUpgrade("Shotgun+: Double Ammo");
		break;
		case 26:
			this.player.getSelectedGun(GunType.SHOTGUN).updateRateOfFire(SHOTGUN_RATE_OF_FIRE_UPGRADE_1);
			this.gameView.renderGunUpgrade("Shotgun+: Faster Fire");
		break;
		case 30:
			this.player.getSelectedGun(GunType.PISTOL).updateMagazine(PISTOL_MAGAZINE_UPGRADE_2);
			this.gameView.renderGunUpgrade("Pistol+: Quad Ammo");
		break;
		case 32:
			this.player.getSelectedGun(GunType.SHOTGUN).updateDamage(SHOTGUN_DAMAGE_UPGRADE_1);
			this.gameView.renderGunUpgrade("Shotgun+: More Damage");
		break;
		case 35:
			this.player.getSelectedGun(GunType.PISTOL).updateRateOfFire(PISTOL_RATE_OF_FIRE_UPGRADE_2);
			this.gameView.renderGunUpgrade("Pistol+: Even Faster Fire");
		break;
		case 36:
			this.player.getSelectedGun(GunType.SHOTGUN).updateMagazine(SHOTGUN_MAGAZINE_UPGRADE_2);
			this.gameView.renderGunUpgrade("Shotgun+: Quad Ammo");
		break;
		case 38:
			this.player.getSelectedGun(GunType.UZI).updateRateOfFire(UZI_RATE_OF_FIRE_UPGRADE_2);
			this.gameView.renderGunUpgrade("UZI+: Even Faster Fire");
		break;
		case 40:
			this.player.getSelectedGun(GunType.UZI).updateMagazine(UZI_MAGAZINE_UPGRADE_2);
			this.gameView.renderGunUpgrade("UZI+: Quad Ammo");
		break;
		case 43:
			this.player.getSelectedGun(GunType.SHOTGUN).updateDamage(SHOTGUN_DAMAGE_UPGRADE_2);
			this.gameView.renderGunUpgrade("Shotgun+: Even More Damage");
		break;
		case 45:
			this.player.getSelectedGun(GunType.UZI).updateDamage(UZI_DAMAGE_UPGRADE_2);
			this.gameView.renderGunUpgrade("UZI+: Even More Damage");
		break;
		case 48:
			this.player.getSelectedGun(GunType.SHOTGUN).updateRateOfFire(SHOTGUN_RATE_OF_FIRE_UPGRADE_2);
			this.gameView.renderGunUpgrade("Shotgun+: Even Faster Fire");
		break;		
		}
	}
}
