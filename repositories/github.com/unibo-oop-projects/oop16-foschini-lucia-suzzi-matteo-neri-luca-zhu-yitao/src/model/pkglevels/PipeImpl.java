package model.pkglevels;

import java.util.List;

import javax.swing.ImageIcon;

/**
 * Implementation of the Pipe interface.
 * 
 * 
 *
 */
public class PipeImpl implements Pipe {
    private ImageIcon style;
    private final ImageIcon emptyPipe;
    private final String type;
    private final int direction;
    private int input;
    private int output;

    private PipeImpl nextTube;
    private final List<ImageIcon> pipesOne;
    private final List<ImageIcon> pipesTwo;

    /**
     * Class constructor.
     * 
     * @param img
     *            icon to set
     * @param dir
     *            pipe direction
     * @param type
     *            pipe name type
     * @param i
     *            pipe input
     * @param o
     *            pipe output
     * @param p1
     *            image list
     * @param p2
     *            image list
     */
    public PipeImpl(final ImageIcon img, final int dir, final String type, final int i, final int o,
            final List<ImageIcon> p1, final List<ImageIcon> p2) {
        this.style = img;
        this.emptyPipe = img;
        this.direction = dir;
        this.type = type;
        this.input = i;
        this.output = o;
        this.pipesOne = p1;
        this.pipesTwo = p2;

    }

    @Override
    public void changeIconOnSolution(final int nextIn, final int dir, final int numb) {
        //For some pipes we have to choose a different map even if the input is the same
        //easiest solution
        if (dir == 1) {
            if (nextIn == 3) {
                this.style = pipesOne.get(numb);
            } else {
                this.style = pipesTwo.get(numb);
            }

        } else if (dir == 2) {
            this.style = pipesOne.get(numb);
        } else if (dir == 3) {
            this.style = pipesTwo.get(numb);

        } else {
            if ((nextIn == 0 || nextIn == 1) && !this.getType().equals("CU")) {
                this.style = pipesOne.get(numb);
            } else {
                this.style = pipesTwo.get(numb);
            }
        }

    }

    @Override
    public ImageIcon getImg() {

        return this.style;
    }

    @Override
    public ImageIcon getEmptyPipe() {
        this.style = emptyPipe;
        return this.emptyPipe;

    }

    @Override
    public int getDir() {
        return this.direction;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public Pipe getNextTube() {

        return nextTube;
    }

    @Override
    public void addPipes(final Pipe t) {

        this.nextTube = (PipeImpl) t;

    }

    @Override
    public int getInput() {
        return this.input;
    }

    @Override
    public int getOutput() {
        return this.output;
    }

    @Override
    public void switchIO() {
        final int tmp = this.getInput();
        this.input = this.getOutput();
        this.output = tmp;
    }

}
