package view;

import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;

import controller.EntityFactory;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.bonus.Bonus;
import model.enemy.Enemy;
import model.entities.Character;
import model.palace.Palace;
import model.palace.Window.StatusOfWindow;

/**
 * 
 * @author mannaro
 *
 */
public class GameView extends Pane {

    private long cicleCount = 0;
    private final ElementsViewFactory factoryView = new ElementsViewFactory();
    private final Canvas canvas = new Canvas(Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
            Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    private final Hud hud = new Hud(canvas.getWidth(), canvas.getHeight());
    private final GraphicsContext gc = canvas.getGraphicsContext2D();

    /**
     * 
     * @param stage main scene of the game.
     */
    public GameView(final Stage stage) {
        super();
        this.setWidth(stage.getWidth());
        this.setHeight(stage.getHeight());
        this.getChildren().add(canvas);
        this.getChildren().add(hud);
        this.getStylesheets().add("style.css");
        stage.getScene().setRoot(this);
    }

    /**
     * 
     * @param factory all entities.
     */
    public void update(final EntityFactory factory) {
        cicleCount++;
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        hud.clearHUD();
        hud.drawLife(factoryView.getHeart(), factory.getStuntman().getLife().getValue());
        hud.drawPoints(factory.getStuntman().getCounterFloors().getValue());
        gc.drawImage(factoryView.getBackground().getSprites().get(1), 0, 0);
        updatePalace(factory.getPalace());
        updateStuntman(factory.getStuntman());
        updateHawks(factory.getHawks());
        updateVases(factory.getVases());
        updateBonus(factory.getBonus());

        if (cicleCount % 10 == 0) {
            factoryView.getHawk().incSpriteCounter();
        }
    }

    private void updatePalace(final Palace palace) {
        for (int i = 0; i < palace.getFloors().size(); i++) {
            for (int j = 0; j < palace.getFloors().get(0).getWindows().size(); j++) {
                gc.drawImage(
                        factoryView.getWindow().getSprites()
                                .get(Arrays.asList(StatusOfWindow.values())
                                        .indexOf(palace.getFloors().get(i).getWindows().get(j).getStatus())),
                        palace.getFloors().get(i).getWindows().get(j).getPosition().getX()
                                - (factoryView.getWindow().getDimension().getWidth() / 2),
                        palace.getFloors().get(i).getWindows().get(j).getPosition().getY()
                                - (factoryView.getWindow().getDimension().getHeight() / 2));
            }
        }
    }

    private void updateStuntman(final Character stuntman) {
        gc.drawImage(factoryView.getStuntman().getSprites().get(0),
                stuntman.getPosition().getX() - (factoryView.getStuntman().getDimension().getWidth() / 2),
                this.getHeight() - factoryView.getStuntman().getDimension().getHeight());
    }

    private void updateHawks(final List<Enemy> hawks) {
        hawks.forEach(hawk -> {
            if (hawk.isInGame()) {
                final int index = hawks.indexOf(hawk);
                gc.drawImage(factoryView.getHawk().getSprites().get(factoryView.getHawk().getSpriteCounter()),
                        hawks.get(index).getPosition().getX() - (hawks.get(index).getWidth() / 2),
                        hawks.get(index).getPosition().getY() - (hawks.get(index).getHeight() / 2));
            }
        });
    }

    private void updateVases(final List<Enemy> vases) {
        if (vases.get(0).isInGame()) {
            gc.drawImage(factoryView.getVase().getSprites().get(0),
                    vases.get(0).getPosition().getX() - (vases.get(0).getWidth() / 2),
                    vases.get(0).getPosition().getY() - (vases.get(0).getHeight() / 2));
        }
        if (vases.get(1).isInGame()) {
            gc.drawImage(factoryView.getVase().getSprites().get(1),
                    vases.get(1).getPosition().getX() - (vases.get(1).getWidth() / 2),
                    vases.get(1).getPosition().getY() - (vases.get(1).getHeight() / 2));
        }
    }

    private void updateBonus(final List<Bonus> bonus) {
        if (bonus.get(0).isInGame()) {
            gc.drawImage(factoryView.getBonus().getSprites().get(0),
                    bonus.get(0).getPosition().getX() - (bonus.get(0).getWidth() / 2),
                    bonus.get(0).getPosition().getY() - (bonus.get(0).getHeight() / 2));
        }
        if (bonus.get(1).isInGame()) {
            gc.drawImage(factoryView.getBonus().getSprites().get(1),
                    bonus.get(1).getPosition().getX() - (bonus.get(1).getWidth() / 2),
                    bonus.get(1).getPosition().getY() - (bonus.get(1).getHeight() / 2));
        }
    }

}
