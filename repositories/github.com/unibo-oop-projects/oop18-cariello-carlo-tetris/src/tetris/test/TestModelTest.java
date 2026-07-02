package tetris.test;

import static org.junit.Assert.assertEquals;

import javax.swing.ImageIcon;

import org.junit.Assert;
import org.junit.Test;

import tetris.models.Shape;
import tetris.models.ShapeImpl;
import tetris.views.TetrisFrameImpl;

/**
 * Test class
 * @author Carlo
 *
 */
public class TestModelTest {
    private final Shape shape0 = new ShapeImpl();
    private final Shape shape1 = new ShapeImpl();
    
    private TetrisFrameImpl frameZero=new TetrisFrameImpl();
    
    private ImageIcon imageTest= new ImageIcon();
    /**
     * This method checks if some shapes are created
     */
    @Test
    public void testShape() {
        this.shape0.setPieceShape(ShapeImpl.Tetrominoes.SquareShape);
        this.shape1.setPieceShape(ShapeImpl.Tetrominoes.ZShape);
        Assert.assertEquals("Something gone bad", this.shape0.getPieceShape(),
                ShapeImpl.Tetrominoes.SquareShape);
        Assert.assertEquals("something gone bad", this.shape1.getPieceShape(),
                ShapeImpl.Tetrominoes.ZShape);
        
        
    }
    
    /**
     * Tetramino rotation test
     */
    @Test
    public void testRotation() {
    	this.shape0.rotateLeft();
        this.shape1.rotateRight();
        Assert.assertNotEquals("Error: Test rotation", this.shape0, this.shape0.rotateLeft());
        Assert.assertNotEquals("Error: Test rotation", this.shape1, this.shape1.rotateRight());
    }
    
    /**
     * This method checks if the nickname field is valid
     */
    @Test
    public void testInsertName() {
    	 this.frameZero.insertName();
        	Assert.assertNotNull("Error: TestInsertName", TetrisFrameImpl.name);
    }
    
    @Test
    public void testNextPieceImage() {
    	imageTest = new ImageIcon("res/image/ZShape.jpg");
    	this.frameZero.setNextPieceImage(imageTest);
    	assertEquals("Error: testNextPieceImage", imageTest.getDescription(), frameZero.nextPieceImage.getDescription());
    }
    
}
