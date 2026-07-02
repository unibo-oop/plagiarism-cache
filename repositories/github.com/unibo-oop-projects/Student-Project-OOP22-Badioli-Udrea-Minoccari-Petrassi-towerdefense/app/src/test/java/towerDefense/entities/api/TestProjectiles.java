package towerDefense.entities.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import towerDefense.Constants;
import towerDefense.entities.impl.Goblin;

public class TestProjectiles {

    private Projectile projectile;
    
    @Test
    public void testHit(){
        MovingEntity target = new Goblin();
        this.projectile = new Projectile(10, new Point(50, 500), Constants.arrow, target);
        this.projectile.hit(target);
        assertEquals(2490, target.getHp());
        this.projectile = new Projectile(490, new Point(50, 500), Constants.boulder, target);
        this.projectile.hit(target);
        assertEquals(2000, target.getHp());
    }

    @Test
    public void testMove(){
        MovingEntity target = new Goblin();
        this.projectile = new Projectile(10, new Point(50, 500), Constants.arrow, target);
        this.projectile.move(target);
        assertEquals((int)this.projectile.getPosition().getX(), 50 + this.projectile.getSpeed());
        assertEquals((int)this.projectile.getHitbox().getX(), 50 + this.projectile.getSpeed());
        this.projectile = new Projectile(10, new Point(50, 500), Constants.boulder, target);
        this.projectile.move(target);
        assertEquals((int)this.projectile.getPosition().getX(), 50 + this.projectile.getSpeed());
        assertEquals((int)this.projectile.getHitbox().getX(), 50 + this.projectile.getSpeed());
    }

    @Test
    public void testCheckCollide(){
        MovingEntity target = new Goblin();
        this.projectile = new Projectile(10, new Point((int)Constants.width ,540), Constants.arrow, target);
        assertEquals(this.projectile.checkCollide(target), true);
        this.projectile = new Projectile(10, new Point(50 ,500), Constants.arrow, target);
        assertEquals(this.projectile.checkCollide(target), false);
        this.projectile = new Projectile(10, new Point((int)Constants.width ,540), Constants.boulder, target);
        assertEquals(this.projectile.checkCollide(target), true);
        this.projectile = new Projectile(10, new Point(50 ,500), Constants.boulder, target);
        assertEquals(this.projectile.checkCollide(target), false);
    }

    @Test
    public void testCheckDistance(){
        MovingEntity target = new Goblin();
        this.projectile = new Projectile(10, new Point(50, 500), Constants.arrow, target);
        for(int i = 0; i < 200; i++){
            this.projectile.move(target);
        }
        assertEquals(this.projectile.checkDistance(), true);
        this.projectile = new Projectile(10, new Point(50, 500), Constants.arrow, target);
        assertEquals(this.projectile.checkDistance(), false);
    }
}
