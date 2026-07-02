package model.bird;

public final class BirdImpl implements Bird {

    private static final double INITIAL_POSITION = 50.0;
    private static final int WIDTH_BIRD = 45;
    private static final int HEIGHT_BIRD = 32;

    private final double posX;
    private double posY;
    private final String birdImagePath;
    //private static BirdImpl birdInstance;

    //Singleton implementation
    /*private BirdImpl() {
        this.posY = INITIAL_POSITION;
        this.posX = INITIAL_POSITION;
        birdImagePath = "bird.png"; 
        }*/

    /**
     * Create an instance of BirdImpl using Singleton Pattern.
     * @return bird instance
     */
    //Singleton implementation
    /*public static BirdImpl getInstance() {
        if (birdInstance == null) {
            birdInstance = new BirdImpl();
        }
        return birdInstance;
    }*/

    public BirdImpl() {
        this.posY = INITIAL_POSITION;
        this.posX = INITIAL_POSITION;
        birdImagePath = "bird.png"; 
    }

    @Override
    public void birdUpdate(final double n) {
        setPosY(getPosY() + n);
    }

    @Override
    public String getBirdImagePath() {
        return birdImagePath;
    }

    @Override
    public int getWidthBird() {
        return WIDTH_BIRD;
    }

    @Override
    public int getHeightBird() {
        return HEIGHT_BIRD;
    }

    @Override
    public double getPosY() {
        return posY;
    }

    @Override
    public double getPosX() {
        return posX; }

    @Override
    public void setPosY(final double posY) {
        this.posY = posY;
    }


    /*@Override
    public void setBirdInstance() {
        birdInstance = new BirdImpl();
    }*/
}
