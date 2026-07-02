package model.entities.components;
import javafx.scene.text.Text;
import model.entities.GameEntityTypes;
import model.entities.components.enemies.EnemyBehaviour;
import model.entities.components.enemies.EnemyController;
import view.ui.MessagesUtil;
import javafx.scene.paint.Color;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;

/**
 *
 */
public class HealthComponent extends Component {

    private final Text text;
    private int health; 

    /**
     * Returns a new health component.
     * 
     * @param health
     *                  the entity's health.
     */
    public HealthComponent(final int health) {
        super();
        text = new Text();
        text.setFill(Color.GREEN);
        this.health = health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpdate(final double tpf) {
        text.setTranslateX(entity.getX());
        text.setTranslateY(entity.getY());
        text.setText("HEALTH:\t" + health);

        if (health <= 0) {

            FXGL.getApp().getGameScene().removeUINode(text);
            if (entity.getType().equals(GameEntityTypes.ENEMY)) {
                entity.getComponent(EnemyController.class).setBehaviour(EnemyBehaviour.DYING);
            } else if (entity.getType().equals(GameEntityTypes.PLAYER)) {
                MessagesUtil.youLose();
            } else if (entity.getType().equals(GameEntityTypes.BOSS)) {
                MessagesUtil.youWon();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAdded() {
        FXGL.getApp().getGameScene().addUINode(text);
    }

    /**
     * @return the health.
     */
    public int getHealth() {
        return health;
    }


    /**
     * @param quantity
     *                   the quantity to increment/decrement the health.
     */
    public void incrementHealth(final int quantity) {
        this.health += quantity;
    }
}
