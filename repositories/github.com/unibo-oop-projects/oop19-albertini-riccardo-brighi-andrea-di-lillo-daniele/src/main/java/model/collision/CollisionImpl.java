package model.collision;

import model.ball.Ball;
import model.block.Block;
import element.Point2D;
import element.Point2DImpl;
import javafx.util.Pair;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Class that implement the Collision
 */
public class CollisionImpl implements Collision {
    protected Map<Pair<Integer, Integer>, Block> blocks;
    protected double top, left, bottom, right;
    private BlocksCollision blocksCollision;

    //TODO Fix variable
    protected List<Block> blocksList;

    /**
     *Default empty Constructor
     */
    public CollisionImpl() {
        this((List<Block>) null, 0d,0d,0d,0d);
    }

    /**
     *  Constructor of CollisionImpl with 2 point
     *
     * @param blocks all the current blocks
     * @param topLeftField the current top left position of the field
     * @param bottomRightField the current bottom right position of the field
     */
    public CollisionImpl(final Map<Pair<Integer, Integer>, Block> blocks,
                         final Point2D topLeftField, final Point2D bottomRightField){
        this(blocks, topLeftField.getY(), topLeftField.getX(), bottomRightField.getY(), bottomRightField.getX());
    }

    /**
     * Constructor of CollisionImpl with the 4 different location (top, left, bottom, right)
     *
     * @param blocks all the current blocks
     * @param top the current position of top of the filed
     * @param left the current position of left of the filed
     * @param bottom the current position of bottom of the filed
     * @param right the current position of right of the filed
     */
    public CollisionImpl(final Map<Pair<Integer, Integer>, Block> blocks,
                         final double top, final double left, final double bottom, final double right){
        this.blocks = blocks;
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        //TODO blocksCollision = new BlocksCollision(blocksList, top, left, bottom, right);
    }

    /**
     * Constructor of CollisionImpl with List<Block> and the 4 different location (top, left, bottom, right)
     *
     * @param blocksList all the current blocks
     * @param top the current position of top of the filed
     * @param left the current position of left of the filed
     * @param bottom the current position of bottom of the filed
     * @param right the current position of right of the filed
     */
    public CollisionImpl(final List<Block> blocksList,
                         final double top, final double left, final double bottom, final double right){
        this.blocksList = blocksList;
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    @Override
    public void updateBlocks(final Map<Pair<Integer, Integer>, Block> blocks){
        this.blocks = blocks;
        //TODO this.blocksCollision.convertBlockToJavaFXRectangle(blocks);
    }

    @Override
    public void updateBlocks(List<Block> blocks) {
        this.blocksList = blocks;
        this.blocksCollision.convertBlockToJavaFXRectangle(blocks);
    }

    @Override
    public Optional<CollisionDetectedImpl> checkCollision(final Ball ball) {

        if(ball.getSpeed() == 0) {
            return Optional.empty();
        }

        Point2D correctPosition = ball.getPosition();

        //at the moment i check only if the ball is out of field
        if(checkBallInField(ball)){
            //check collision with blocks
            this.blocksCollision = new BlocksCollision(blocksList, top, left, bottom, right);
            return this.blocksCollision.checkCollisionWithBlocks(ball, correctPosition);
        }
        else{
            BordersCollision bordersCollision = new BordersCollision(new Point2DImpl(left, top), new Point2DImpl(right, bottom));
            //reallocate the ball
            correctPosition = bordersCollision.relocateBall(ball);
            //calculate the new direction
            return bordersCollision.collisionWithBorders(ball, correctPosition);
        }
    }

    private boolean checkBallInField(final Ball ball){
        return(!(checkBallOutYComponent(ball) || checkBallOutXComponent(ball)));
    }

    /**
     * Method to check if the ball is out of top or bottom
     *
     * @param ball current ball
     * @return true or false
     */
    protected boolean checkBallOutYComponent(final Ball ball){
        return(ball.getPosition().getY() - ball.getRadius() <= bottom ||
                ball.getPosition().getY() + ball.getRadius() >= top);
    }

    /**
     * Method to check if the ball is out of left or right
     *
     * @param ball current ball
     * @return true or false
     */
    protected boolean checkBallOutXComponent(final Ball ball){
        return (ball.getPosition().getX() - ball.getRadius() <= left ||
                ball.getPosition().getX() + ball.getRadius() >= right);
    }
}
