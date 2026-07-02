package it.unibo.oop.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.unibo.oop.model.BasicEnemyBehavior;
import it.unibo.oop.model.BasicMonster;
import it.unibo.oop.model.Bullet;
import it.unibo.oop.model.Factory;
import it.unibo.oop.model.GameStateImpl;
import it.unibo.oop.model.Health;
import it.unibo.oop.model.HealthBonus;
import it.unibo.oop.model.InvisibleEnemyBehavior;
import it.unibo.oop.model.InvisibleMonster;
import it.unibo.oop.model.MainCharacter;
import it.unibo.oop.model.ScoreBonus;
import it.unibo.oop.utilities.Direction;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;

public class TestClass {

    @Test
    public void testUtilities() {
    	Position testPos = new Position(0,0);
    	Position secondPos = new Position(4,5);
    	
    	Vector2 testVector = new Vector2(1,1);
    	Vector2 testVector2 = new Vector2(0,1);
    	
    	assertEquals(testPos.sumVector(testVector),new Position(1,1));
    	assertEquals(testVector.add(new Vector2(2,2)), new Vector2(3,3));
    	
    	assertEquals(Direction.UP.getVector2(), new Vector2(0,-1));
    	assertEquals(Direction.DOWN.getVector2(), new Vector2(0,1)); 
    	assertEquals(Direction.LEFT.getVector2(), new Vector2(-1,0)); 
    	assertEquals(Direction.RIGHT.getVector2(), new Vector2(1,0)); 
    	
    	assertEquals(testVector.scl(5), new Vector2(5,5));    	
    	assertEquals(testVector2.setLength(10), new Vector2(0,10));
    	assertEquals(testVector2.add(testVector), new Vector2(1,2));    	
    	
    	assertEquals((int)Position.pointsDistance(testPos, secondPos), 6);
    }
    @Test
    public void testCollectables(){
    	MainCharacter testHero = Factory.MainCharacterFactory.generateStillCharacter(0, 0);
    	ScoreBonus bonusTest = new ScoreBonus(0, 0);    	
    	
    	bonusTest.collect(testHero);
    	
    	assertEquals(testHero.getScore().getValue() > 0, true);    	
    	assertEquals(bonusTest.getPosition(), new Position(0,0));
    	assertEquals(bonusTest.intersecate(testHero), true);
    	
    	MainCharacter testHero2 = new MainCharacter(5, 5, new Vector2(), new Health(0,3,2));
    	HealthBonus healthTest = new HealthBonus(0, 0);    	
    	
    	healthTest.collect(testHero2);
    	
    	assertEquals(testHero2.getHealth().getCurrentHealth() == 3, true);    	
    	assertEquals(bonusTest.getPosition(), new Position(0,0));
    	assertEquals(bonusTest.intersecate(testHero2), true);
    }
    @Test
    public void testEnemies(){
    	BasicMonster testBasic = Factory.EnemiesFactory.generateStillBasicEnemy(0, 0);
    	BasicEnemyBehavior testBasicBehavior = new BasicEnemyBehavior(testBasic);
    	
    	testBasic.attachBehavior(testBasicBehavior);
    	assertEquals(testBasic.getBehavior().get(), testBasicBehavior);
    	
    	testBasic.killEntity();
    	assertEquals(testBasic.isDead(), true);    	
    	assertEquals(testBasic.getBounds().contains(1,1),true);
    	
    	assertEquals(testBasicBehavior.getNextMove(new Position(1000,0)).getIntX() >0, true);
    	assertEquals(testBasicBehavior.getNextMove(new Position(0,1000)).getIntX() >0, false);

    	
    	InvisibleMonster testInvisible = Factory.EnemiesFactory.generateStillInvisibleEnemy(0, 0);
    	InvisibleEnemyBehavior testInvisibleBehavior = new InvisibleEnemyBehavior(testInvisible);
    	
    	testInvisible.attachBehavior(testInvisibleBehavior);
    	assertEquals(testInvisible.getBehavior().get(), testInvisibleBehavior);
    	
    	testInvisible.killEntity();
    	assertEquals(testInvisible.isDead(), true);    	
    	assertEquals(testInvisible.getBounds().contains(1,1),true);
    	
    	assertEquals(testInvisibleBehavior.getNextMove(new Position(1000,0)).getIntX() >0, false);
    	assertEquals(testInvisibleBehavior.getNextMove(new Position(100,100)).getIntX() >0, true);
    	
    }
    @Test
    public void testMainCharacter(){
    	MainCharacter testHero = Factory.MainCharacterFactory.generateStillCharacter(0, 0);
    	
    	GameStateImpl.getInstance().initialize(1);
    	GameStateImpl.getInstance().getMainChar().get().shoot();
    	assertEquals(GameStateImpl.getInstance().getMovableList().stream().filter(x->x instanceof Bullet).count(),0);
    	
    	assertEquals(testHero.getHealth().getCurrentHealth(),3);
    	assertEquals(testHero.getMovement(), new Vector2());
    	assertEquals(testHero.getFaceDirection(),Direction.UP);
    	
    	testHero.setInput(Direction.DOWN, false);
    	assertEquals(testHero.getLastDirection(), Direction.DOWN);
    	testHero.getHealth().decreaseHealth(3);
    	assertEquals(testHero.isDead(), true);
    }
}