package towerDefense.gameLogic.impl;

import java.util.Random;

import towerDefense.Constants;
import towerDefense.entities.api.Entity;
import towerDefense.entities.api.MovingEntity;
import towerDefense.entities.api.RangedEntity;
import towerDefense.entities.impl.Archer;
import towerDefense.entities.impl.Barbarian;
import towerDefense.entities.impl.Goblin;
import towerDefense.entities.impl.Knight;
import towerDefense.entities.impl.TowerSingleton;
import towerDefense.entities.impl.Wizard;
import towerDefense.gameLogic.api.GameLogic;


public class GameLogicImpl implements GameLogic {

    TowerSingleton tower = TowerSingleton.getInstance();
    AI ai = new AI();

    /**
     * {@inheritDoc}
     */
    @Override
    public <X extends MovingEntity> boolean checkCollision(X a, Entity b){
        if((double)a.getSpeed()/(double)b.getSpeed() < 0){
            if(a.getNameEntity() == Constants.archer ||a.getNameEntity() == Constants.turret){
                return ((RangedEntity)a).getRangeBox().intersects(b.getHitbox());
            }
            return a.getHitbox().intersects(b.getHitbox());
        }else{
            return a.getHitbox().intersects(b.getHitbox());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(){
        this.getWaveManager().spawnWave();
        tower.removeDeads();
        ai.useAI();
        tower.updateScoreMoney();
    }

    /**
     * {@inheritDoc}
     */
    public void summonEntity(final int cost, final int type) {
		final MovingEntity entity;
            if(type==1){
                entity = new Barbarian();
            }else if(type==2){
                entity = new Knight();
            }else {
                entity = new Archer();
            }
            if(cost <= tower.getMoney())  {
                tower.addEntity(entity);
                tower.removeMoney(cost);
            } 
	}

    /**
     * {@inheritDoc}
     */
    public void summonfreeEntity(final int type) {
		summonEntity(0, type); 
	}   

    /**
     * {@inheritDoc}
     */
    public void summonEnemy(){
        Random random = new Random();
        int seed = random.nextInt(100) + 1;
        if(seed < 70) {
            tower.addEnemy(new Goblin());
        }
        else {
            tower.addEnemy(new Wizard());
        }    
    }

    /**
     * 
     * @return
     *        The only instance of WaveManager
     */
    public WaveManagerSingleton getWaveManager() {
        return WaveManagerSingleton.getInstance();
    }

}
