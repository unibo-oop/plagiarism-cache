package controller.gamecontroller;

import java.util.ArrayList;
import model.GameModel;
import view.gameview.GameScreen;
import view.input.Input;
import utilityclasses.Pair;
/**
 * The GameLoop of the program.
 */
public class GameLoop implements Runnable {
    private static final int REFRESH_RATE = 500;
    private final GameModel gm;
    private final GameScreen v;
    private boolean running;
    private int i;

    private ArrayList<Input> inputList;

    /**
     * Constructor of GameLoop, creates the GameLoop.
     * @param gm GameModel of the program
     * @param v View of the program
     */
    public GameLoop(final GameModel gm, final GameScreen v) {
        this.gm = gm;
        this.v = v;
        this.inputList = new ArrayList<>();
        this.running = true;
    }

    @Override
    public final void run() {
        if (running) {
            //input process
            this.processInput();
            //model update
            gm.update();
            System.out.println("update N-" + i);
            System.out.println("oggetti nella mappa : " + gm.getMap().getEntityList().stream().count());
            if (!gm.getMap().getEntityList().isEmpty()) {
                gm.getMap().getEntityList().stream()
                .forEach(e -> System.out.println(e));
            }
            //render view
            v.render(gm.getMap().getEntityList());
            i++; //variabili di debug
            }
        else {
            try {
                Thread.sleep(REFRESH_RATE);
                System.out.println("PAUSED");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    }
            }
        }
    /**
     * Resumes the GameLoop.
     */
    public void resume() {
        this.running = true;
        }

    /**
     * Pauses the GameLoop.
     */
    public void pause() {
        this.running = false;
        }

    /**
     * Adds inputs to the GameLoop's input queue.
     * @param input the Input {@link View.Input.Input}
     */
    public void addInput(final Input input) {
            this.inputList.add(input);
            }

    private void processInput() {
        if (!inputList.isEmpty()) {
            inputList.forEach(e -> {
            switch (e.getInputType()) {
            case ADD_TOWER :
                gm.placeTower(new Pair<Integer, Integer>(e.getX(), e.getY()), e.getTowerType());
                break;
            case REMOVE_TOWER :
                gm.removeTower(new Pair<Integer, Integer>(e.getX(), e.getY()));
                break;
            case START_WAVE :
                gm.setReadyToSpawn(true);
                break;
            default:
                 break;
                 }
            });
            inputList.clear();
            }
        }
}
