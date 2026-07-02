package powpaw.player.view.impl;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.text.Text;
import powpaw.player.model.api.Player;
import powpaw.player.view.api.DamageMeterRender;
import powpaw.world.controller.ScreenController;

/**
 * DamageMeterRender implementation.
 * 
 * @author Simone Collorà
 */
public final class DamageMeterRenderImpl implements DamageMeterRender {

    private static final double WIDTHP1 = 6;
    private static final double WIDTHP2 = 1.3;
    private static final double HEIGHT = 6;

    private final List<Text> damage = new ArrayList<>();

    /**
     * DamageMeterRender costructor.
     * 
     * @param players for get damage
     */
    public DamageMeterRenderImpl(final List<Player> players) {
        for (int i = 0; i < 2; i++) {
            damage.add(new Text((int) (players.get(i).getCurrentHealth().getDamage() * 10) + "%"));
            damage.get(i).setStyle("-fx-font: 50 arial;");
            damage.get(i).setY(ScreenController.SIZE_HD_H / HEIGHT);
        }
        damage.get(0).setX(ScreenController.SIZE_HD_W / WIDTHP1);
        damage.get(1).setX(ScreenController.SIZE_HD_W / WIDTHP2);
    }

    @Override
    public List<Text> getDamage() {
        return this.damage;
    }

    @Override
    public void update(final List<Player> players) {
        for (int i = 0; i < 2; i++) {
            damage.get(i).setText((int) (players.get(i).getCurrentHealth().getDamage() * 10) + "%");
        }
    }

}
