package giocoscudetto.model.api;

/**
 * This Class Board is for rappresent the board of the game with all his boxes.
 */
public interface Board {

    /**
     * This method is for obtain the type of boxes in a certain index.
     * 
     * @param index the positon in the board.
     * @return the associated Box.
     */
    Box getBox(int index);

    /**
     * This method is for obtain the image of the box in a certain index.
     * 
     * @param i the position on the board.
     * @return the path of the image.
     */
    String getBoxImage(int i);

} 
