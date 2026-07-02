package powpaw.player.view.api;

import java.util.List;

import javafx.scene.text.Text;
import powpaw.player.model.api.Player;

/**
 * DamageMeterRender. The costructor create a list of Text with the players
 * damage and
 * multiply it x10 in order to be more user friendly.
 * 
 * @author Simone Collorà
 */
public interface DamageMeterRender {

    /**
     * Return list of players' damage.
     * 
     * @return list of players' damage
     */
    List<Text> getDamage();

    /**
     * Update render.
     * 
     * @param players
     */
    void update(List<Player> players);

}
