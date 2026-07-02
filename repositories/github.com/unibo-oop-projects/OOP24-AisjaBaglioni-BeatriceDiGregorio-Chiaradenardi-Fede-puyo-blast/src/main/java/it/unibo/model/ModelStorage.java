package it.unibo.model;

public class ModelStorage {
    public final Scale scale;
    public final BulletModel bulletModel;
    public final CannonModel cannonModel;
    public final ExitModel exitModel;
    public final Grid grid;
    public final KeyboardModel keyboardModel;
    public final PauseModel pauseModel;
    public final ProgressBarModel progressBarModel;
    public final ScoreModel scoreModel;
    public final StatusModel statusModel;
    public final TryAgainModel tryAgainModel;

    public ModelStorage(Scale scale) {
        this.scale = scale;

        this.bulletModel = new BulletModel();
        this.cannonModel = new CannonModel();
        this.exitModel = new ExitModel();
        this.grid = new Grid(8, 8);
        this.keyboardModel = new KeyboardModel();
        this.pauseModel = new PauseModel();
        this.progressBarModel = new ProgressBarModel();
        this.scoreModel = new ScoreModel();
        this.statusModel = new StatusModel();
        this.tryAgainModel = new TryAgainModel();
    };
}