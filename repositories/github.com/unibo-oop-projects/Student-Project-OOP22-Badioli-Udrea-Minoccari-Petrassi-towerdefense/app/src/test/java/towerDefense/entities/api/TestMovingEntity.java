package towerDefense.entities.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import towerDefense.Constants;
import towerDefense.entities.impl.Archer;
import towerDefense.entities.impl.Barbarian;
import towerDefense.entities.impl.Goblin;
import towerDefense.entities.impl.Knight;
import towerDefense.entities.impl.Turret;
import towerDefense.entities.impl.Wizard;

public class TestMovingEntity {

    private MovingEntity entity;
    
    @Test
    public void createMovingEntity(){
        this.entity = new Barbarian();
        assertEquals(entity.getNameEntity(), Constants.barbarian);
        this.entity = new Knight();
        assertEquals(entity.getNameEntity(), Constants.knight);
        this.entity = new Goblin();
        assertEquals(entity.getNameEntity(), Constants.goblin);
        this.entity = new Wizard();
        assertEquals(entity.getNameEntity(), Constants.wizard);
    }
    
    /**
     * This test check the correct update of position 
    */
    @Test
    public void testUpdatePosition() {
        this.entity = new Barbarian();
        this.entity.updatePosition();
        assertEquals((int)entity.getPosition().getX(), 50 + entity.getSpeed());
        for(int i = 0; i < 5; i++){
            this.entity.updatePosition();
        }
        assertEquals((int)entity.getPosition().getX(), 50 + entity.getSpeed()*6);
        this.entity = new Knight();
        this.entity.updatePosition();
        assertEquals((int)entity.getPosition().getX(), 50 + entity.getSpeed());
        for(int i = 0; i < 5; i++){
            this.entity.updatePosition();
        }
        assertEquals((int)entity.getPosition().getX(), 50 + entity.getSpeed()*6);
        this.entity = new Goblin();
        this.entity.updatePosition();
        assertEquals((int)entity.getPosition().getX(), (int)Constants.width  + entity.getSpeed());
        for(int i = 0; i < 5; i++){
            this.entity.updatePosition();
        }
        assertEquals((int)entity.getPosition().getX(), (int)Constants.width + entity.getSpeed()*6);
        this.entity = new Wizard();
        this.entity.updatePosition();
        assertEquals((int)entity.getPosition().getX(), (int)Constants.width  + entity.getSpeed());
        for(int i = 0; i < 5; i++){
            this.entity.updatePosition();
        }
        assertEquals((int)entity.getPosition().getX(), (int)Constants.width + entity.getSpeed()*6);
        this.entity = new Archer();
        this.entity.updatePosition();
        assertEquals((int)entity.getPosition().getX(), 50 + entity.getSpeed());
        for(int i = 0; i < 5; i++){
            this.entity.updatePosition();
        }
        assertEquals((int)entity.getPosition().getX(), 50 + entity.getSpeed()*6);
    }
    /**
     * This test check for the correct loop of sprites
     */
    @Test
    public void testUpdateSprite(){
        this.entity = new Barbarian();
        try {
            synchronized(entity){
                entity.wait(126);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.entity.updateSprite(Constants.walk);
        assertEquals(1, this.entity.getCurrentSpriteWalk());
        for(int i = 0; i < 8; i++){
            try {
                synchronized(entity){
                    entity.wait(126);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.entity.updateSprite(Constants.walk);
        }
        assertEquals(entity.getCurrentSpriteWalk(), 1);
        this.entity = new Knight();
        try {
            synchronized(entity){
                entity.wait(126);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.entity.updateSprite(Constants.walk);
        assertEquals(1, this.entity.getCurrentSpriteWalk());       
        for(int i = 0; i < 8; i++){
            try {
                synchronized(entity){
                    entity.wait(126);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.entity.updateSprite(Constants.walk);
        }
        assertEquals(entity.getCurrentSpriteWalk(), 1);
        this.entity = new Goblin();
        try {
            synchronized(entity){
                entity.wait(126);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.entity.updateSprite(Constants.walk);
        assertEquals(1, this.entity.getCurrentSpriteWalk());
        for(int i = 0; i < 6; i++){
            try {
                synchronized(entity){
                    entity.wait(126);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.entity.updateSprite(Constants.walk);
        }
        assertEquals(1, this.entity.getCurrentSpriteWalk());
        this.entity = new Wizard();
        try {
            synchronized(entity){
                entity.wait(126);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.entity.updateSprite(Constants.walk);
        assertEquals(1, this.entity.getCurrentSpriteWalk());
        for(int i = 0; i < 6; i++){
            try {
                synchronized(entity){
                    entity.wait(126);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.entity.updateSprite(Constants.walk);
        }
        assertEquals(1, this.entity.getCurrentSpriteWalk());
        this.entity = new Archer();
        try {
            synchronized(entity){
                entity.wait(126);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.entity.updateSprite(Constants.walk);
        assertEquals(1, this.entity.getCurrentSpriteWalk());
        for(int i = 0; i < 8; i++){
            try {
                synchronized(entity){
                    entity.wait(126);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.entity.updateSprite(Constants.walk);
        }
        assertEquals(entity.getCurrentSpriteWalk(), 1);
        this.entity = new Turret();
        try {
            synchronized(entity){
                entity.wait(126);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.entity.updateSprite(Constants.walk);
        assertEquals(1, this.entity.getCurrentSpriteWalk());
    }

    @Test
    public void testAttack(){
        this.entity = new Barbarian();
        try {
            synchronized(entity){
                entity.wait(250);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.entity.updateSprite(Constants.attack);
        assertEquals(1, this.entity.getCurrentSpriteAttack());
        for(int i = 0; i < 30; i++){
            try {
                synchronized(entity){
                    entity.wait(250);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.entity.updateSprite(Constants.attack);
        }
        assertEquals(entity.getCurrentSpriteAttack(), 1);
        this.entity = new Knight();
        try {
            synchronized(entity){
                entity.wait(250);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.entity.updateSprite(Constants.attack);
        assertEquals(1, this.entity.getCurrentSpriteAttack());
        for(int i = 0; i < 9; i++){
            try {
                synchronized(entity){
                    entity.wait(250);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.entity.updateSprite(Constants.attack);
        }
        assertEquals(entity.getCurrentSpriteAttack(), 1);
        this.entity = new Goblin();
        try {
            synchronized(entity){
                entity.wait(250);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.entity.updateSprite(Constants.attack);
        assertEquals(1, this.entity.getCurrentSpriteAttack());
        for(int i = 0; i < 7; i++){
            try {
                synchronized(entity){
                    entity.wait(250);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.entity.updateSprite(Constants.attack);
        }
        assertEquals(entity.getCurrentSpriteAttack(), 1);
        this.entity = new Wizard();
        try {
            synchronized(entity){
                entity.wait(250);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.entity.updateSprite(Constants.attack);
        assertEquals(1, this.entity.getCurrentSpriteAttack());
        for(int i = 0; i < 10; i++){
            try {
                synchronized(entity){
                    entity.wait(250);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.entity.updateSprite(Constants.attack);
        }
        assertEquals(entity.getCurrentSpriteAttack(), 1);
        this.entity = new Archer();
        try {
            synchronized(entity){
                entity.wait(250);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.entity.updateSprite(Constants.attack);
        assertEquals(1, this.entity.getCurrentSpriteAttack());
        for(int i = 0; i < 17; i++){
            try {
                synchronized(entity){
                    entity.wait(250);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.entity.updateSprite(Constants.attack);
        }
        assertEquals(entity.getCurrentSpriteAttack(), 1);
        this.entity = new Turret();
        try {
            synchronized(entity){
                entity.wait(250);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.entity.updateSprite(Constants.attack);
        assertEquals(1, this.entity.getCurrentSpriteAttack());
        for(int i = 0; i < 6; i++){
            try {
                synchronized(entity){
                    entity.wait(250);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.entity.updateSprite(Constants.attack);
        }
        assertEquals(entity.getCurrentSpriteAttack(), 1);
    }
}

