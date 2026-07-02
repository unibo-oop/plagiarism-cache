package model.enumeration;

import org.junit.jupiter.api.Test;

import model.enums.Orientation;
import model.util.Pair;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TestEnumeration {

    @Test
    public void testEnumerationHorizontal() {
        final int cellsNumber = 3;
        final List<Pair<Integer, Integer>> cellUsed = new ArrayList<Pair<Integer, Integer>>();

        IntStream.range(0, cellsNumber).forEach(i -> cellUsed.add(new Pair<Integer, Integer>(0, i)));

        final Orientation orientation1 = Orientation.HORIZONTAL;

        final List<Pair<Integer, Integer>> cellUsedOrientation1 = orientation1.cellsUsedList(new Pair<Integer, Integer>(0, 0), cellsNumber);

        IntStream.range(0, cellsNumber)
                 .forEach(i -> assertTrue(
                         cellUsedOrientation1.get(i).getX() == cellUsed.get(i).getX() && cellUsedOrientation1.get(i).getY() == cellUsed.get(i).getY()));
    }

}
