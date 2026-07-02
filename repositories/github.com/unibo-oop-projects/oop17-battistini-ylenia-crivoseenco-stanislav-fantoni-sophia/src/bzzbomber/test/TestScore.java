package bzzbomber.test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import bzzbomber.model.Score;
import bzzbomber.model.TopScoreImpl;
import org.junit.Assert;

/**
 * Test to save, read, add, get the Score.
 */
public class TestScore {

    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator")
            + "testReadWrite.txt";

    private static final int SCORE1 = 100;
    private static final int SCORE2 = -5;
    private static final int SCORE3 = 1;
    private static final int SCORE4 = 2;
    private static final int SCORE5 = 5;
    private static final int SCORE6 = 50;
    private static final int SCORE7 = 200;
    private static final int SCORE8 = 250;
    private static final int SCORE9 = 300;
    private static final int SCORE10 = 500;

    private Score s;
    private final TopScoreImpl list = new TopScoreImpl();

    /**
     * Test for add score.
     * 
     * @throws IllegalStateException
     *             Exception of illegal state.
     * @throws IOException
     *             Exception of I/O operation..
     * 
     */
    @Test
    public void testAdd() throws IllegalStateException, IOException {
        list.addScore(new Score("Giovanni", TestScore.SCORE3));
        s = new Score("Marco", TestScore.SCORE4);
        list.addScore(s);
        list.addScore(new Score("Luca", TestScore.SCORE5));
        final List<Score> list2 = new LinkedList<>();
        list2.add(new Score("Luca", TestScore.SCORE5));
        list2.add(new Score("Marco", TestScore.SCORE4));
        list2.add(new Score("Giovanni", TestScore.SCORE3));

        Assert.assertEquals(list2, (list.getScore()));

    }

    /**
     * Test for limit ten score.
     * 
     * @throws IllegalStateException
     *             Exception of illegal state.
     * @throws IOException
     *             Exception of I/O operation..
     * 
     */
    @Test
    public void testLimitSave() throws IllegalStateException, IOException {
        list.addScore(new Score("Giova", TestScore.SCORE7));
        list.addScore(new Score("Marc", TestScore.SCORE5));
        list.addScore(new Score("Lucas", TestScore.SCORE9));
        list.addScore(new Score("Marco", TestScore.SCORE1));
        list.addScore(new Score("Francesco", TestScore.SCORE10));
        list.addScore(new Score("Claudio", TestScore.SCORE6));
        list.addScore(new Score("Nicola", TestScore.SCORE8));
        list.addScore(new Score("Alessio", TestScore.SCORE8 + TestScore.SCORE7));
        list.addScore(new Score("Michele", TestScore.SCORE4));
        list.addScore(new Score("Mirco", TestScore.SCORE5 + TestScore.SCORE3));
        list.addScore(new Score("Andrea", TestScore.SCORE10 + TestScore.SCORE8));
        list.saveOnFile(TestScore.PATH);
        list.getScore();
        System.out.println(list.getScore().size());

        Assert.assertEquals("List get only ten elements", list.getScore().size(), TopScoreImpl.MAXNUM);
    }

    /**
     * Test for exception and read from file.
     */
    @Test
    public void testExceptionRead() {
        System.out.println("Try to create Score with wrong argument");
        try {
            s = new Score("", TestScore.SCORE1);
            System.out.println("The name is empty!");
        } catch (final Exception e) {
            System.out.println("First failed, OK!");
        }
        try {
            s = new Score("Win", TestScore.SCORE2);
            System.out.println("The score is negative!");
        } catch (final Exception e) {
            System.out.println("Second failed, OK!");
        }
        list.readFromFile(TestScore.PATH);

        Assert.assertFalse(list.getScore().isEmpty());
    }

    /**
     * Test for remove score.
     */
    @Test
    public void testRemove() {
        this.list.getScore().clear();

        Assert.assertTrue("List is empty!", list.getScore().isEmpty());
    }

}
