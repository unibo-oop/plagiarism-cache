package laterunner.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JLabel;

import laterunner.core.GameEngine;
import laterunner.model.user.User;
import laterunner.model.vehicle.Car;
import laterunner.model.vehicle.Obstacle;
import laterunner.model.vehicle.Vehicle;
import laterunner.model.vehicle.Vehicles;
import laterunner.model.world.GameState;
import laterunner.model.world.World;

/**
 * Road is the class that contains the real game. It displays all the vehicles and its elements are constantly repainted.
 *
 */
public class Road extends PanelImpl implements Runnable {

    private static final long serialVersionUID = 1L;
    private static final float SIZE = 60;
    private static final int STEP = -96;
    private static final int LCROSS = 372;
    private static final int RCROSS = 572;
    private static final int SPEEDFACTOR = 21;
    private static int y = STEP;
    private Font fontChalkDash;
    private static JLabel label = new JLabel();
    private Audio audio = new Audio();

    private GameEngine gameEngine;
    private GameState gameState;

    /**
     * Common Road constructor: sets the GameEngine.
     * 
     * @param gmEngine
     *          an instance of GameEngine class
     */
    public Road(final GameEngine gmEngine) {
        this.gameEngine = gmEngine;
        fontChalkDash = super.createFont("Digital Dot Roadsign.otf", SIZE);
        label.setFont(fontChalkDash);
        label.setForeground(Color.BLACK);
        this.add(label, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        World scene = gameState.getWorld();
        List<Vehicle> entities = scene.getSceneEntities();
        g.drawImage(super.getPics().getImage(Icons.ROAD), 0, 0, null);
        this.updateCross();
        g.drawImage(super.getPics().getImage(Icons.CROSS), LCROSS, y, null);
        g.drawImage(super.getPics().getImage(Icons.CROSS), RCROSS, y, null);

        entities.stream().forEach(e -> {
            if (this.gameEngine.isSurvival()) {
                label.setText("<html><div style='text-align: center;'>" + "Score: " + gameState.getScore() + "</div></html>");
            } else {
            label.setText("<html><div style='text-align: center;'>" + "Lives: " + User.getUser().getUserLives()
                + "&#160; &#160; &#160; &#160; &#160;" + "Score: " + gameState.getScore() + "</div></html>");
            }
            if (e instanceof Car) {
                Car c = (Car) e;
                g.drawImage(super.getPics().getImage(Icons.CAR), (int) c.getCurrentPos().getX(),
                        (int) c.getCurrentPos().getY(), null);
            } else if (e instanceof Obstacle) {
                Obstacle pick = (Obstacle) e;
                if (pick.getType() == Vehicles.MOTORBIKE) {
                    g.drawImage(super.getPics().getImage(Icons.MOTORBIKE), (int) pick.getCurrentPos().getX(),
                            (int) pick.getCurrentPos().getY(), null);
                } else if (pick.getType() == Vehicles.BUS) {
                    g.drawImage(super.getPics().getImage(Icons.BUS), (int) pick.getCurrentPos().getX(),
                            (int) pick.getCurrentPos().getY(), null);
                } else if (pick.getType() == Vehicles.OBSTACLE_CAR) {
                    g.drawImage(super.getPics().getImage(Icons.JEEP), (int) pick.getCurrentPos().getX(),
                            (int) pick.getCurrentPos().getY(), null);
                }
            }
        });
    }

    /**
     * Sets the GameState.
     * @param gmState
     *          an instance of GameState class
     */
    public void setGameState(final GameState gmState) {
        this.gameState = gmState;
    }

    @Override
    public void run() {
        gameEngine.mainLoop();
    }

    private void updateCross() {
        y += SPEEDFACTOR;
        if (y >= 0) {
                y = STEP;
        }
    }

    /**
     * Returns an instance of Audio class.
     * @return
     *          an instance of Audio class
     */
    public Audio getAudio() {
        return this.audio;
    }

}