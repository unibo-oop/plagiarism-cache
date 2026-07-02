package model.block;

public class BlockImpl implements Block{

    private final double BlockSize = 50;
    private int posx, posy;

    public BlockImpl() {}

    public BlockImpl(int x, int y) { this.posx = x; this.posy = y; }

    public double getBlockSize() { return this.BlockSize; }

    public int getPosx() {
        return this.posx;
    }

    public void incPosx() { this.posx++; }

    public int getPosy() { return posy; }

    public void incPosY() { this.posy++;}

}
