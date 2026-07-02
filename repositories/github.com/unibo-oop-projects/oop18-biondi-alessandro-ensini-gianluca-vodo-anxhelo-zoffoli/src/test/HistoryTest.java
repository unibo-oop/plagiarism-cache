package test;

import org.junit.Assert;
import org.junit.Test;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import model.effects.convolution.Emboss;
import model.effects.convolution.FastBlur;
import model.effects.convolution.Sharpen;
import model.util.history.Component;
import model.util.history.ComponentImpl;
import model.util.history.History;
import model.util.history.HistoryImpl;

/**
 * Class to test history.
 */
public class HistoryTest {

    private final Image testImage1 = new WritableImage(2, 2);
    private final Image testImage2 = new WritableImage(3, 3);
    private final Image testImage3 = new WritableImage(4, 4);

    private final Component component = new ComponentImpl(testImage1, new Emboss());
    private final Component component2 = new ComponentImpl(testImage2, new Sharpen());
    private final Component component3 = new ComponentImpl(testImage3, new FastBlur(10));

    /**
     * Test of constructor HistoryImpl.
     */
    @Test
    public void testHistoryImpl() {
        final History history = new HistoryImpl();
        Assert.assertEquals(0, history.getCurrentIndex());
        Assert.assertTrue(((HistoryImpl) history).toList().isEmpty());
    }

    /**
     * Test of method getPrevious.
     */
    @Test
    public void testGetPrevious() {
        final History history = new HistoryImpl();
        history.addChange(component);
        history.addChange(component2);
        history.addChange(component3);
        Assert.assertEquals(component2, history.getPrevious());
        ((HistoryImpl) history).setIndex(1);
        Assert.assertEquals(component, history.getPrevious());
        ((HistoryImpl) history).setIndex(0);
        Assert.assertEquals(component, history.getPrevious());
        ((HistoryImpl) history).setIndex(1);
        ((HistoryImpl) history).toList().remove(1);
        Assert.assertEquals(component, history.getPrevious());
    }

    /**
     * Test of method getCurrent.
     */
    @Test
    public void testGetCurrent() {
        final History history = new HistoryImpl();
        history.addChange(component);
        history.addChange(component2);
        history.addChange(component3);
        Assert.assertEquals(component3, history.getCurrent());
        ((HistoryImpl) history).setIndex(0);
        Assert.assertEquals(component, history.getCurrent());
        ((HistoryImpl) history).setIndex(1);
        Assert.assertEquals(component2, history.getCurrent());
        ((HistoryImpl) history).setIndex(0);
        ((HistoryImpl) history).toList().remove(0);
        Assert.assertEquals(component2, history.getCurrent());
    }

    /**
     * Test of method getCurrentIndex.
     */
    @Test
    public void testGetCurrentIndex() {
        final History history = new HistoryImpl();
        history.addChange(component);
        history.addChange(component2);
        history.addChange(component3);
        Assert.assertEquals(2, history.getCurrentIndex());
        ((HistoryImpl) history).setIndex(0);
        Assert.assertEquals(0, history.getCurrentIndex());
        ((HistoryImpl) history).setIndex(1);
        Assert.assertEquals(1, history.getCurrentIndex());
        ((HistoryImpl) history).setIndex(0);
        ((HistoryImpl) history).toList().remove(0);
        Assert.assertEquals(0, history.getCurrentIndex());
    }

    /**
     * Test of method getNext.
     */
    @Test
    public void testGetNext() {
        final History history = new HistoryImpl();
        history.addChange(component);
        history.addChange(component2);
        history.addChange(component3);
        Assert.assertEquals(component3, history.getNext());
        ((HistoryImpl) history).setIndex(0);
        Assert.assertEquals(component2, history.getNext());
        ((HistoryImpl) history).setIndex(1);
        Assert.assertEquals(component3, history.getNext());
        ((HistoryImpl) history).setIndex(0);
        ((HistoryImpl) history).toList().remove(1);
        Assert.assertEquals(component3, history.getNext());
    }

    /**
     * Test of method getOriginal.
     */
    @Test
    public void testGetOriginal() {
        final History history = new HistoryImpl();
        history.addChange(component);
        history.addChange(component2);
        history.addChange(component3);
        Assert.assertEquals(component, history.getOriginal());
        ((HistoryImpl) history).setIndex(1);
        Assert.assertEquals(component, history.getOriginal());
        ((HistoryImpl) history).setIndex(0);
        ((HistoryImpl) history).toList().remove(0);
        Assert.assertEquals(component2, history.getOriginal());
    }

    /**
     * Test of method getLast.
     */
    @Test
    public void testGetLast() {
        final History history = new HistoryImpl();
        history.addChange(component);
        history.addChange(component2);
        history.addChange(component3);

        Assert.assertEquals(component3, history.getLast());
    }

    /**
     * Test of method addChange.
     */
    @Test
    public void testAddChange() {
        final History history = new HistoryImpl();
        history.addChange(component);
        Assert.assertEquals(component, history.getCurrent());
        Assert.assertEquals(0, history.getCurrentIndex());
        history.addChange(component2);
        Assert.assertEquals(component2, history.getLast());
        Assert.assertEquals(1, history.getCurrentIndex());
        history.addChange(component3);
        Assert.assertEquals(component3, history.getLast());
        Assert.assertEquals(2, history.getCurrentIndex());
    }

    /**
     * Test of method getLastIndex.
     */
    @Test

    public void testGetLastIndex() {
        final History history = new HistoryImpl();
        history.addChange(component);
        history.addChange(component2);
        history.addChange(component3);

        Assert.assertEquals(2, ((HistoryImpl) history).getLastIndex());
    }

    /**
     * Test of method setIndex.
     */
    @Test
    public void testSetIndex() {
        final History history = new HistoryImpl();
        history.addChange(component);
        history.addChange(component2);
        history.addChange(component3);

        ((HistoryImpl) history).setIndex(1);
        Assert.assertEquals(1, history.getCurrentIndex());
        ((HistoryImpl) history).setIndex(2);
        Assert.assertEquals(2, history.getCurrentIndex());
    }

    /**
     * Test of method livesInThePast.
     */
    @Test
    public void testLivesInThePast() {
        final History history = new HistoryImpl();
        history.addChange(component);
        history.addChange(component2);
        history.addChange(component3);

        Assert.assertEquals(false, history.livesInThePast());
        ((HistoryImpl) history).setIndex(1);
        Assert.assertEquals(true, history.livesInThePast());
    }

}
