package towerDefense.gameLogic.impl;

import towerDefense.Constants;
import towerDefense.entities.api.Entity;
import towerDefense.entities.api.MovingEntity;
import towerDefense.entities.api.Projectile;
import towerDefense.entities.api.RangedEntity;
import towerDefense.entities.impl.TowerSingleton;
import towerDefense.entities.impl.Turret;

public class AI {
    
    public void useAI(){
        final TowerSingleton tower = TowerSingleton.getInstance();
        MovingEntity entity;
        Entity target;
        MovingEntity ally;
        final GameLogicImpl gameLogicImpl = new GameLogicImpl();
        
        for(int i = 0; i< tower.getEntitiesNumber(); i++){
            entity = tower.getEntities().get(i);
                if(i != 0){
                    ally = tower.getEntities().get(i - 1);
                    if(entity.getNameEntity() == Constants.archer){
                        if(tower.getEnemies().size() > 0){
                            target = tower.getEnemies().getFirst();
                            if(gameLogicImpl.checkCollision(entity, target)){
                                entity.attack(target);
                                entity.updateSprite(Constants.attack);
                            }else{
                                checkAllyAhead(entity, ally);
                            }
                        }else{
                            checkAllyAhead(entity, ally);
                        }
                    }else{
                        checkAllyAhead(entity, ally);
                    }
                }else{
                    if(tower.getEnemies().size() > 0){
                        target = tower.getEnemies().getFirst();
                        checkEnemyAhead(entity, target);
                    }else{
                        if(entity.getPosition().getX() < 800){
                            entity.updatePosition();
                        }else{
                            entity.updateSprite(Constants.walk);
                        }      
                    }
                }
                if(entity.getNameEntity() == Constants.archer){
                    moveProjectiles((RangedEntity)entity, tower);
                }
        }


        for(int i = 0; i< tower.getEnemies().size(); i++){
            entity = tower.getEnemies().get(i);
            if(i != 0){
                ally = tower.getEnemies().get( i - 1);
                checkAllyAhead(entity, ally);
            }else{
                if(tower.getEntitiesNumber() > 0){
                    target = tower.getEntities().getFirst();
                    //entity.attack(entity.getTarget(tower.getEntities()));
                    checkEnemyAhead(entity, target);
                }else{
                    if(tower.getTurret() != null){
                        target = tower.getTurret();
                    }else{
                        target = TowerSingleton.getInstance();
                    }
                    checkEnemyAhead(entity, target);
                }
            }
        }


        if(tower.getTurret() != null){
            Turret turret = tower.getTurret();
            if(tower.getEnemies().size() > 0){
                target = tower.getEnemies().getFirst();
                checkEnemyAhead(turret, target);
            }else{
               turret.updateSprite(Constants.walk);
            }
            //for(Projectile arrow : turret.getProjectiles()){
            moveProjectiles(turret, tower);
        }
    }

    //-------------------------------

    private void checkAllyAhead(MovingEntity entity, MovingEntity ally){
        GameLogicImpl gameLogicImpl = new GameLogicImpl();
        if(!gameLogicImpl.checkCollision(entity, ally)){
            entity.updatePosition();
            if(entity.getNameEntity() == Constants.archer){
                ((RangedEntity)entity).updateRangeBox();
            }
        }else{
            entity.updateSprite(Constants.walk);
        }
    }

    private void checkEnemyAhead(MovingEntity entity, Entity target){
        GameLogicImpl gameLogicImpl = new GameLogicImpl();
        if(gameLogicImpl.checkCollision(entity, target)){
            entity.attack(target);
            entity.updateSprite(Constants.attack);
        }else{
            if(entity.getSpeed() == 0){
                entity.updateSprite(Constants.walk);
            }else if(entity.getSpeed() > 0){
                if(entity.getPosition().getX() < 800){
                    entity.updatePosition();
                    if(entity.getNameEntity() == Constants.archer){
                        ((RangedEntity)entity).updateRangeBox();
                    }
                }else{
                    entity.updateSprite(Constants.walk);
                }
            }else{
                entity.updatePosition();
            }
        }
    }

    private void moveProjectiles(RangedEntity ranged, TowerSingleton tower){
        Entity target;
        for(int j = 0; j < ranged.getProjectiles().size(); j ++){
            Projectile arrow = ranged.getProjectiles().get(j);
            if(tower.getEnemies().size()>0){
                target = tower.getEnemies().getFirst();
                arrow.move(target);
                if(arrow.checkCollide(target)){
                    arrow.hit(target);
                    ranged.getProjectiles().remove(arrow);
                }
                if(arrow.checkDistance()){
                    ranged.getProjectiles().remove(arrow);
                }
            }else{
                ranged.getProjectiles().remove(arrow);
            }
        }
    }
}
