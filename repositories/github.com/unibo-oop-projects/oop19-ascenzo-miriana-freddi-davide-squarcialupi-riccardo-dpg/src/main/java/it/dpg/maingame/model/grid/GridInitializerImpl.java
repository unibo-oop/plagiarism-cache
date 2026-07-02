package it.dpg.maingame.model.grid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class GridInitializerImpl implements GridInitializer {

    private Map<Cell, Pair<Integer, Integer>> gridMap = new HashMap<>();
    private Grid grid;
    private String jsonString;

    /**
     * this methods is used by the GridInitializer to set the json file
     */
    private void setJson(GridType gridType) {

        InputStream path;

        /*The json is set based on the grid type*/
        if (gridType.equals(GridType.GRID_ONE)) {
            path = ClassLoader.getSystemResourceAsStream("json/grid1.json");
        } else {
            path = null;
        }
        if (path != null) {
            jsonString =  new BufferedReader(new InputStreamReader(path)).lines().collect(Collectors.joining());
        }
    }

    @Override
    public Grid makeGrid(GridType gridType) {

        setJson(gridType); //the json is set based on the grid Type

        Map<Integer, CellImpl> tempList = new HashMap<>();   //temporary List of Cells
        Map<Integer, int[]> tempNext = new HashMap<>();  //temporary list of references to next cells
        Map<Integer, Integer> tempPrev = new HashMap<>(); //list of references to previous Cell

        ObjectMapper mapper = new ObjectMapper();        //mapper class from jackson is used to extract elements from the json

        CellParser[] mp;    //Cell Parser array

        {
            try {
                //fills Cell Parser array with the elements in the json file
                mp = mapper.readValue(jsonString, CellParser[].class);

                //generates a temporary Array of Cells; it's missing the connections between cells.
                for (var i : mp) {
                    boolean isFork;
                    isFork = i.getNext().length > 1;            //checks if the cell leads to a fork
                    tempList.put(i.getId(), new CellImpl(isFork, new ImmutablePair<>(i.getX_coordinate(), i.getY_coordinate()), CellType.valueOf(i.getCell_type())));
                    tempNext.put(i.getId(), i.getNext());
                    tempPrev.put(i.getId(), i.getPrev());
                }

                //this cycle sets the next Cells linked to a Cell and puts the Cells in the Grid
                tempList.forEach((key, value) -> {
                    Set<Cell> next = new HashSet<>();

                    //Set Next Cells
                    if (tempNext.get(key).length > 0) {
                        //every linked Cell is put in the "next" field of cell
                        //finds the next Cells connected to a Cell in TempList and saves them in "next" Set
                        next = Arrays.stream(tempNext.get(key))
                                .mapToObj(tempList::get)
                                .collect(Collectors.toSet());
                    }
                    value.setNext(next);

                    //Set Previous Cell
                    tempPrev.forEach((key1, value1) -> {
                        if (key1.equals(key)) {
                            value.setPrevious(tempList.get(value1));
                        }
                    });

                    //puts Cell from tempList that is now completed in gridMap
                    gridMap.put(value, value.getCoordinates());
                });

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        this.grid = new GridImpl(this.getFirst(), this.getLast(), this.gridMap);
        return this.grid;

    }

    @Override
    public Grid getGrid() {
        //this exception is thrown if the grid hasn't been created yet
        if (this.grid == null) {
            throw new IllegalStateException();
        }
        return this.grid;
    }

    @Override
    public Cell getFirst() {
        //searches for the cell of type "START" and returns it
        return gridMap.entrySet().stream()
                .filter(i -> i.getKey().getType().equals(CellType.START))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    @Override
    public Cell getLast() {
        //searches for the cell of type "END" and returns it
        return gridMap.entrySet().stream()
                .filter(i -> i.getKey().getType().equals(CellType.END))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public String getJsonString() {
        return this.jsonString;
    }
}