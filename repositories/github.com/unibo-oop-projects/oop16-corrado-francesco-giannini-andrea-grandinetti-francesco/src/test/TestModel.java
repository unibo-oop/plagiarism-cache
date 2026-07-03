package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import model.PositionImpl;
import model.car.Car;
import model.car.CarImpl;
import model.ranking.RankingManager;
import model.ranking.RankingManagerImpl;
import utility.Driver;
import utility.TyreType;
/**
 * 
 *
 */
public class TestModel {
    /**
     * 
     */
    @Test
    @Ignore
    public void testToLap() {
        final Car car1 = new CarImpl(Driver.ALO);
        final Car car2 = new CarImpl(Driver.HAM);
        car1.setInitialType(TyreType.SS);
        car2.setInitialType(TyreType.SS);
        car1.setPosition(new PositionImpl(1, 1, 4));
        car2.setPosition(new PositionImpl(2, 2, 4));
        car1.setLaps(3);
        car2.setLaps(4);
        car1.move(3, 1);
        System.out.println(car1.getPosition().getX());
        System.out.println(car1.getPosition().getY());
        System.out.println(car1.getPosition().getTrackNumber());
        System.out.println();
        assertEquals(car1.getPosition(), new PositionImpl(4, 1, 4));
        assertEquals(car2.getPosition(), new PositionImpl(2, 2, 4));
    }
    /**
     * 
     */
    @Test
    @Ignore
    public void testToLap1() {
        final Car car1 = new CarImpl(Driver.ALO);
        final Car car2 = new CarImpl(Driver.HAM);
        car1.setInitialType(TyreType.SS);
        car2.setInitialType(TyreType.SS);
        car1.setPosition(new PositionImpl(1, 1, 4));
        car2.setPosition(new PositionImpl(2, 2, 4));
        car1.setLaps(3);
        car2.setLaps(4);
        car1.move(3, 2);
        System.out.println(car1.getPosition().getX());
        System.out.println(car1.getPosition().getY());
        System.out.println(car1.getPosition().getTrackNumber());
        System.out.println();
        assertEquals(car1.getPosition(), new PositionImpl(4, 2, 4));
        assertEquals(car2.getPosition(), new PositionImpl(2, 2, 4));
    }
    /**
     * 
     */
    @Test
    @Ignore
    public void testToLap2() {
        System.out.println("test2");
        final Car car1 = new CarImpl(Driver.ALO);
        final Car car2 = new CarImpl(Driver.HAM);
        car1.setInitialType(TyreType.SS);
        car2.setInitialType(TyreType.SS);
        car1.setPosition(new PositionImpl(1, 1, 8));
        car2.setPosition(new PositionImpl(2, 2, 8));
        car1.setLaps(3);
        car2.setLaps(4);
        final List <Car> cars = new ArrayList<Car>();
        cars.add(car1);
        cars.add(car2);
        final RankingManager rank = new RankingManagerImpl(cars);
        System.out.println(rank.getRank());
        car1.move(3, 2);
        System.out.println(rank.getRank());
        System.out.println("x" + car1.getPosition().getX());
        System.out.println("y" + car1.getPosition().getY());
        System.out.println(car1.getPosition().getTrackNumber());
        assertEquals(car1.getPosition(), new PositionImpl(4, 2, 8));
        assertEquals(car2.getPosition(), new PositionImpl(2, 2, 8));
    }
    /**
     * 
     */
    @Test
    @Ignore
    public void testToLap3() {
        System.out.println("test3");
        final Car car1 = new CarImpl(Driver.ALO);
        final Car car2 = new CarImpl(Driver.HAM);
        car1.setInitialType(TyreType.SS);
        car2.setInitialType(TyreType.SS);
        car1.setPosition(new PositionImpl(1, 1, 8));
        car2.setPosition(new PositionImpl(2, 2, 8));
        car1.setLaps(3);
        car2.setLaps(4);
        final List <Car> cars = new ArrayList<Car>();
        cars.add(car1);
        cars.add(car2);
        final RankingManager rank = new RankingManagerImpl(cars);
        System.out.println(rank.getRank());
        car1.move(3, 1);
        System.out.println(rank.getRank());
        System.out.println("x" + car1.getPosition().getX());
        System.out.println("y" + car1.getPosition().getY());
        System.out.println(car1.getPosition().getTrackNumber());
        assertEquals(car1.getPosition(), new PositionImpl(4, 1, 8));
        assertEquals(car2.getPosition(), new PositionImpl(2, 2, 8));
    }
    /**
     * 
     */
    @Test
    @Ignore
    public void testToLap4() {
        System.out.println("test4");
        final Car car1 = new CarImpl(Driver.ALO);
        final Car car2 = new CarImpl(Driver.HAM);
        car1.setInitialType(TyreType.SS);
        car2.setInitialType(TyreType.SS);
        car1.setPosition(new PositionImpl(1, 2, 8));
        car2.setPosition(new PositionImpl(2, 2, 8));
        car1.setLaps(3);
        car2.setLaps(4);
        final List <Car> cars = new ArrayList<Car>();
        cars.add(car1);
        cars.add(car2);
        final RankingManager rank = new RankingManagerImpl(cars);
        System.out.println(rank.getRank());
        car1.move(3, 2);
        System.out.println(rank.getRank());
        System.out.println("x" + car1.getPosition().getX());
        System.out.println("y" + car1.getPosition().getY());
        System.out.println(car1.getPosition().getTrackNumber());
        assertEquals(car1.getPosition(), new PositionImpl(1, 2, 8));
        assertEquals(car2.getPosition(), new PositionImpl(2, 2, 8));
    }
    /**
     * 
     */
    @Test
    @Ignore
    public void testToLap5() {
        System.out.println("test5");
        final Car car1 = new CarImpl(Driver.ALO);
        final Car car2 = new CarImpl(Driver.HAM);
        car1.setInitialType(TyreType.SS);
        car2.setInitialType(TyreType.SS);
        car1.setPosition(new PositionImpl(1, 2, 8));
        car2.setPosition(new PositionImpl(2, 2, 8));
        car1.setLaps(3);
        car2.setLaps(4);
        final List <Car> cars = new ArrayList<Car>();
        cars.add(car1);
        cars.add(car2);
        final RankingManager rank = new RankingManagerImpl(cars);
        System.out.println(rank.getRank());
        car1.move(3, 1);
        System.out.println(rank.getRank());
        System.out.println("x" + car1.getPosition().getX());
        System.out.println("y" + car1.getPosition().getY());
        System.out.println(car1.getPosition().getTrackNumber());
        assertEquals(car1.getPosition(), new PositionImpl(4, 1, 8));
        assertEquals(car2.getPosition(), new PositionImpl(2, 2, 8));
    }
}
