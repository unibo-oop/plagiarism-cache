package zombieversity.model.world;

import java.util.Map;

import javafx.geometry.Point2D;
import javafx.util.Pair;
import zombieversity.view.world.WorldView;
/**
 * GuideLines to implement a worldGenerator to fill a terrain.
 *
 */
public interface WorldGenerator {
    /**
     * Main method to generate World and his associated WorldView. The world will be the size of the given parameters, each block of the world will be at given i, j position but multiplied by the given tilesize.
     * greater is the difficulty lesser object will be present in the world, because less object more free area to get targeted by zombies.
     * @param w - width size of the world express in blocks.
     * @param h - height size of the world express in blocks.
     * @param ts - block size to get the orginal world size.
     * @param d - difficulty enumerator to specify how hard or not will be the world
     * @return Pair<World, WorldView> - the object will be ready to be used.
     */
    Pair<World, WorldView> generate(double w, double h, double ts, Difficulty d);
    /**
     * Method to fill up with the given index all the blocks that are in the border and corners. This is could be used to create a world walkable limit.
     * @param blocks - current world approximation to be extend in this method. - 
     * @param id - index used to fill.
     * @param w - width of the world.
     * @param h - height of the world.
     */
    void fillBorder(Map<Point2D, Integer> blocks, int id, double w, double h);
    /**
     * Method to fill all the block in a row, required the position of the first block, others are auto calculated.
     * @param blocks - current world approximation to be extend in this method. - 
     * @param id - index used to fill each block of the row.
     * @param initP - position of the first block of the row.
     * @param count - for how many elements i want to fill the row.
     */
    void fillRow(Map<Point2D, Integer> blocks, int id, Point2D initP, int count);
    /**
     * Method to fill all the block in a column, required the position of the first block, others are auto calculated.
     * @param blocks - current world approximation to be extend in this method. - 
     * @param id - index used to fill each block of the column.
     * @param initP - position of the first block of the column.
     * @param count - for how many elements i want to fill the column.
     */
    void fillColumn(Map<Point2D, Integer> blocks, int id, Point2D initP, int count);
    /**
     * Method to fill only one Cell.
     * @param blocks - current world approximation to be extend in this method. - 
     * @param id - index used to fill.
     * @param p - position of the cell to fill.
     */
    void fillCell(Map<Point2D, Integer> blocks, int id,  Point2D p);
   /**
    * Method used to fill the corners of the world, given the center. With this method i can fill any corners of a given point from a given distance like in a rectangle(w, h).
     * @param blocks - current world approximation to be extend in this method. - 
     * @param id - index used to fill each corner.
    * @param center - position of the center.
    * @param w - effectively size of the map ( could be relative or absolute)
    * @param h
    */
    void fillAngles(Map<Point2D, Integer> blocks, int id, Point2D center, double w, double h);
    /**
     * Method to fill all the blocks next to the given one.
     * @param blocks - current world approximation to be extend in this method. - 
     * @param id - index used to fill.
     * @param p - point of the target block.
     */
    void fillNextTo(Map<Point2D, Integer> blocks, int id, Point2D p);
    /**
     * Method to generate random objects in random points.
     * @param blocks - current world approximation to be extend in this method. - 
     * @param rBound - limit of maximum object.
     * @param w - size of the map in blocks.
     * @param h - size of the map in blocks.
     */
    void generateRandom(Map<Point2D, Integer> blocks, int rBound, int w, int h);

    /**
     * Enum used to specify the hardness of the world, it represents and integer that harder is bigger is the integer.
     */
    enum Difficulty {
        EASY(1.5),
        MEDIUM(2.5),
        HARD(4.5);
        private double diff;
        Difficulty(final double i) {
            diff = i;
        }
        public double getDiff() {
            return this.diff;
        }
    }

}
