package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import model.car.Car;
import model.car.CarImpl;
import model.PositionImpl;
import model.exception.EndRaceException;
import model.ranking.RankingManager;
import model.ranking.RankingManagerImpl;
import utility.Driver;
import utility.TyreType;

/**
 *
 */
public class TestRank {

    private static final String TEXT_FAIL = "I giri non devono finire";

    /**
     * 
     */
    @Test
    public void testClass() {
        final Car car1 = new CarImpl(Driver.ALO);
        final Car car2 = new CarImpl(Driver.BOT);
        final List<Car> carList = new ArrayList<>();
        carList.addAll(Arrays.asList(car1, car2));
        final RankingManager rank = new RankingManagerImpl(carList);
        assertEquals(Arrays.asList(Driver.ALO, Driver.BOT), rank.getRank());

        car1.setPosition(new PositionImpl());
        car2.setPosition(new PositionImpl());
        car1.move(3, 2);
        rank.update(car1);
        car2.move(1, 1);
        rank.update(car2);
        assertEquals(Arrays.asList(Driver.ALO, Driver.BOT), rank.getRank());

        final Car car3 = new CarImpl(Driver.HAM);
        carList.add(car3);
        car3.setPosition(new PositionImpl());
        car3.changeTrack();
        car3.changeTrack();
        car3.move(4, 2);
        rank.update(car3);
        assertEquals(Arrays.asList(Driver.HAM, Driver.ALO, Driver.BOT), rank.getRank());
       /* assertEquals(car3, rank.prevCarAligned(car1));
        assertEquals(car2, rank.prevCarAligned(car2));
        assertEquals(car3, rank.prevCarAligned(car3));*/

    }

    /**
     * 
     */
    @Test
    public void testMovement() {
        final Car car1 = new CarImpl(Driver.ALO);
        final Car car2 = new CarImpl(Driver.BOT);
        car1.setPosition(new PositionImpl());
        car2.setPosition(new PositionImpl());
        final List<Car> carList = new ArrayList<>();
        carList.addAll(Arrays.asList(car1, car2));
        final RankingManager rank = new RankingManagerImpl(carList);
        assertEquals(Arrays.asList(Driver.ALO, Driver.BOT), rank.getRank());

        car1.setLaps(100);
        car2.setLaps(100);

        car1.move(3, 2);
        car2.move(1, 1);
        rank.update(car1);
        rank.update(car2);
        assertEquals(Arrays.asList(Driver.ALO, Driver.BOT), rank.getRank());

        car2.move(4, 1);
        rank.update(car2);
        assertEquals(Arrays.asList(Driver.BOT, Driver.ALO), rank.getRank());

        car1.changeTrack();
        rank.update(car1);
        assertEquals(Arrays.asList(Driver.ALO, Driver.BOT), rank.getRank());

        try {
            car2.lapEnd();
            car2.move(2, 1);
            rank.update(car2);
            car1.lapEnd();
            car1.move(4, 1);
            rank.update(car1);
            assertEquals(Arrays.asList(Driver.ALO, Driver.BOT), rank.getRank());
        } catch (EndRaceException e) {
            fail(TEXT_FAIL);
        }

        try {
            car2.lapEnd();
            car2.move(2, 1);
            rank.update(car2);
            car1.changeTrack();
            car1.move(8, 1);
            rank.update(car1);
            assertEquals(Arrays.asList(Driver.BOT, Driver.ALO), rank.getRank());
        } catch (EndRaceException e) {
            fail(TEXT_FAIL);
        }

        try {
            car1.setLaps(2);
            car2.setLaps(1);
            car2.box(TyreType.H);
            rank.update(car2);
            car1.lapEnd();
            car1.move(3, 1);
            rank.update(car1);
            assertEquals(Arrays.asList(Driver.ALO, Driver.BOT), rank.getRank());
        } catch (EndRaceException e) {
            fail(TEXT_FAIL);
        }

        while (car2.decLapsInBox()) {
            car1.changeTrack();
        }

        try {
            car1.setLaps(10);
            car2.setLaps(10);
            car2.lapEnd();
            car2.box(TyreType.H);
            rank.update(car2);
            car1.move(3, 1);
            rank.update(car1);
            assertEquals(Arrays.asList(Driver.BOT, Driver.ALO), rank.getRank());
        } catch (EndRaceException e) {
            fail(TEXT_FAIL);
        }


    }

}
