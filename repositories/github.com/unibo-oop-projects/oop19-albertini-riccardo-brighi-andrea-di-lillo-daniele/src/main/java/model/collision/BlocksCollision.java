package model.collision;

import element.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.ball.Ball;
import model.block.Block;
import element.Point2DImpl;
import model.block.BlockImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class BlocksCollision extends CollisionImpl {
    private ArrayList<Rectangle> nodes;
    private Circle currBall;

    /**
     * Constructor of BlockCollision
     *
     * @param blocks all current blocks
     * @param topLeft position of top left angle
     * @param bottomRight position of bottom right angle
     */
    protected BlocksCollision(final List<Block> blocks,
                                    Point2DImpl topLeft, Point2DImpl bottomRight) {
        this(blocks, topLeft.getY(), topLeft.getX(), bottomRight.getY(), bottomRight.getX());
    }

    /**
     * Constructor of BlockCollision
     *
     * @param blocks all current blocks
     * @param top position of top
     * @param left position of left
     * @param bottom position of bottom
     * @param right position of right
     */
    protected BlocksCollision(final List<Block> blocks,
                              final double top, final double left, final double bottom, final double right) {
        super(blocks, top, left, bottom, right);
        convertBlockToJavaFXRectangle(blocks);
    }

    /**
     * Method to Convert List of Block into List of Rectangle with lambda
     * @param blocks all new current blocks
     */
    protected void convertBlockToJavaFXRectangle(List<Block> blocks){
        nodes = new ArrayList<>();
        if(blocks != null && !blocks.isEmpty()) {
            blocks.forEach(a -> nodes.add(new Rectangle(a.getPosx() * a.getBlockSize(),
                    this.top - a.getPosy() * a.getBlockSize(),
                    a.getBlockSize(), a.getBlockSize())));
        }
    }

    private void setCurrBall(Ball ball){
        currBall = new Circle(ball.getPosition().getX(), ball.getPosition().getY(), ball.getRadius());
    }

    //check ball intersect with a Rectangle
    private Optional<Rectangle> checkShapeIntersection() {
        if(nodes == null || nodes.isEmpty()){
            return Optional.empty();
        }
        for (Rectangle static_bloc : nodes) {
            if (static_bloc != null) {
                Shape intersect = Shape.intersect(currBall, static_bloc);
                if (intersect.getBoundsInLocal().getWidth() != -1) {
                    System.out.println("eiiiiiiiiiiiiiiiiiii" + intersect.getBoundsInLocal().getHeight());
                    return Optional.of(static_bloc);
                }
            }
        }
        System.out.println("No collisioni");
        return Optional.empty();
    }

    /**
     * Method to check if there is any collision with block
     *
     * @param ball current ball
     * @param correctPosition correct position of ball
     * @return empty if there isn't any collision or Optional<CollisionDetectedImpl> if there is one
     */
    protected Optional<CollisionDetectedImpl> checkCollisionWithBlocks(Ball ball, Point2D correctPosition){
        this.setCurrBall(ball);
        var blockWithCollision = checkShapeIntersection();
        if(blockWithCollision.isPresent()){
            System.out.println("Collision");
            var tmpBlockList = new ArrayList<Block>();
            tmpBlockList.add(new BlockImpl(((int) blockWithCollision.get().getX()), (int) blockWithCollision.get().getY()));
            return Optional.of(new CollisionDetectedImpl(tmpBlockList, ball.getDirection(), correctPosition));
        }
        else{
            System.out.println("No Collisions");
            return Optional.empty();
        }
    }

    protected Optional<CollisionDetectedImpl> collisionWithBlocks(final Ball ball){
        //TODO collisionWithBlocks

        //blocks.get(new Pair<>(0,0)).
        return Optional.empty();
    }

    protected Optional<CollisionDetectedImpl> collisionWithBlocksNormal(final Ball ball){
        //TODO collisionWithBlocksNormal
        return Optional.empty();
    }

    protected Optional<CollisionDetectedImpl> collisionWithBlocksBonus(final Ball ball){
        //TODO collisionWithBlocksBonus
        return Optional.empty();
    }
}
