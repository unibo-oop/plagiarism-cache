package towerDefense.gameLogic.impl;
import towerDefense.entities.api.MovingEntity;
import towerDefense.entities.impl.TowerSingleton;
import towerDefense.game.impl.MenuPanel;
import towerDefense.game.impl.RulePanel;
import towerDefense.game.impl.EndPanel;
import towerDefense.game.impl.GameImpl;
import towerDefense.game.impl.GamePanel;

import java.awt.Graphics;

public class GameLoop extends Thread{

    private GameImpl game;
    private TowerSingleton tower = TowerSingleton.getInstance();
    private GameLogicImpl gameLogicImpl = new GameLogicImpl();

    /**
     * Builds a new gameloop
     */
    public GameLoop(GameImpl game) {
        this.game = game;
        this.run();
    }

    public GameLoop() {

    }
    
    /**
     * Method called to run the gameloop and update the game state
     */
    @Override
    public void run() {
       
        while(true) {
            if(!(game.getCurrentPanel() instanceof MenuPanel || game.getCurrentPanel() instanceof RulePanel)) {
                while(TowerSingleton.getInstance().getHp() >= 0 && game.getCurrentPanel() instanceof GamePanel) {
                    update();  
                }
                GameImpl.setCurrentPanel(new EndPanel());
                break;
            }
        }
    }

    private void update() {
        this.gameLogicImpl.update();
        this.game.getCurrentPanel().update();
        this.game.getCurrentPanel().repaint();
        try {
            GameLoop.sleep((long)(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Calls for the graphic methods to draw them on screen
     * @param g
     *        graphic object used to draw all components
     */
    public void draw(Graphics g){
        for(MovingEntity entity : tower.getEntities()){
            entity.draw(g);
        }
        for(MovingEntity enemy : tower.getEnemies()){
            enemy.draw(g);
        }
        if(tower.getTurret() != null){
            tower.getTurret().draw(g);
        }

        g.drawImage(tower.getSprite(), (int)tower.getPosition().getX(), (int)tower.getPosition().getY(), null);
    }


}