package spacesurvival.view.game.utilities;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import spacesurvival.model.EngineImage;
import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.utilities.dimension.Screen;
import spacesurvival.utilities.path.bullet.BulletHUD;
import spacesurvival.utilities.path.Icon;
import spacesurvival.utilities.path.Weapon;
import spacesurvival.view.utilities.JImage;


public class Bullet extends JPanel {
    private static final long serialVersionUID = 8962529427424782971L;
    private final JImage ammoTypeImage;
    private final JImage bulletImage;

    public Bullet() {
        super(new FlowLayout());
        super.setOpaque(false);

        final Dimension dimension = EngineImage.getSizeImageFromScale(
                Icon.HEART, ScaleOf.ICON_FULL, Screen.WIDTH_FULLSCREEN);

        this.ammoTypeImage = new JImage(Weapon.NORMAL, dimension);
        this.bulletImage = new JImage(BulletHUD.NORMAL, dimension);

        super.add(this.ammoTypeImage);
        super.add(this.bulletImage);
    }

    /**
     * Set the image in the HUD for the type of ammo.
     * 
     * @param ammoTypeImagePath path image for the ammo icon
     */
    public void setAmmoTypeImage(final String ammoTypeImagePath) {
        this.ammoTypeImage.setImage(ammoTypeImagePath);
    }

    /**
     * Set the image in the HUD for the type of bullet.
     * 
     * @param bulletImagePath path image for the bullet icon
     */
    public void setBulletImage(final String bulletImagePath) {
        this.bulletImage.setImage(bulletImagePath);
    }
}
