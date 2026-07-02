package towerDefense.entities.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import towerDefense.Constants;
import towerDefense.entities.impl.Archer;
import towerDefense.entities.impl.Goblin;
import towerDefense.entities.impl.Turret;

public class TestRangedEntity {
    
    private RangedEntity entity;

    @Test
    public void testCreateRangedEntity(){
        this.entity = new Archer();
        assertEquals(Constants.archer, entity.getNameEntity());
        this.entity = new Turret();
        assertEquals(Constants.turret, entity.getNameEntity());
    }

    @Test
    public void testUpdateRangeBox(){
        this.entity = new Archer();
        this.entity.updateRangeBox();
        assertEquals(50 + this.entity.getSpeed(), (int)this.entity.getRangeBox().getX());
        for(int i = 0; i < 5; i++){
            this.entity.updateRangeBox();
        }
        assertEquals(50 + this.entity.getSpeed()*6, (int)this.entity.getRangeBox().getX());
    }

    @Test
    public void testAttack(){
        MovingEntity target = new Goblin();
        this.entity = new Archer();
        try {
            synchronized(entity){
                entity.wait(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.entity.attack(target);
        assertEquals(this.entity.getProjectiles().size(), 1);
        for(int i = 0; i < 10; i++){
            try {
                synchronized(entity){
                    entity.wait(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.entity.attack(target);
        }
        assertEquals(this.entity.getProjectiles().size(), 5);
        this.entity = new Turret();
        try {
            synchronized(entity){
                entity.wait(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.entity.attack(target);
        assertEquals(this.entity.getProjectiles().size(), 1);
        for(int i = 0; i < 10; i++){
            try {
                synchronized(entity){
                    entity.wait(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.entity.attack(target);
        }
        assertEquals(this.entity.getProjectiles().size(), 5);
    }




}
