package controller.input;

import java.util.LinkedList;
import java.util.Optional;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import model.Attack;
import model.Direction;
import model.Fighter;
import model.Status;
import utility.KeyMapper;
import view.stage.FighterView;

/**
 * Controls the logic of a Fighter's moveset
 */
public class MovesetManager {

    private Fighter body;
    private KeyMapper keyMap;
    private Timer.Task destroy;
    private Integer next = -1;
    private Integer served = -1;
    private FighterView view;
    private LinkedList<Integer> buffer;
    private Optional<Attack> atk1;
    private final KeyboardListener keyboardListener;

    private static final float JUMP_HIGH = 100;
    private static final float ZERO_MOVEMENT = 0;
    private static final float VELOCITY = 40;
    private static final float WIDTH_ATTACK = 10;
    private static final float HEIGHT_ATTACK = 10;
    private static final float DAMAGE = 10;
    private static final float END_ATTACK = 0.4f;

    /**
     * @param keyboardListener
     *          manages the input from keyboard
     * @param body
     *          the fighter's body
     * @param keyMap
     *          the keyMap of the Fighter
     * @param view
     *          the view to update
     */
    public MovesetManager(final KeyboardListener keyboardListener, final Fighter body, final FighterView view,
                          final KeyMapper keyMap) {
        this.body = body;
        this.keyMap = keyMap;
        this.view = view;
        this.keyboardListener = keyboardListener;
        this.atk1 = Optional.empty();
        this.buffer = new LinkedList<Integer>();

        this.destroy = new Task() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void run() {
                atk1.get().getBody().getWorld().destroyBody(atk1.get().getBody());
                atk1 = Optional.empty();
                setFlag(false);
                keyUp(-1);
            }
        };
    }

    /**
     * Calls the KeyboardListener's method keyUp()
     * @param i
     */
    public void keyUp(final int i) {
        this.keyboardListener.keyUp(i);
    }

    /**
     * Calls the KeyboardListener's method setFlagAttack()
     * @param set
     */
    public void setFlag(final boolean set) {
        this.keyboardListener.setFlagAttack(set);
    }

    /**
     * Calls the KeyboardListener's method jump() and updates the Fighter's Status
     */
    public void jump() {
        this.body.getBody().setLinearVelocity(this.body.getBody().getLinearVelocity().x, JUMP_HIGH);
        this.served = this.keyMap.getJump();
        this.body.setCurrentStatus(Status.JUMP);
    }

    /**
     * Calls the KeyboardListener's method attack(), manages the repulsion and updates the Fighter's Status
     */
    public void attack() {
        this.body.setCurrentStatus(Status.ATTACK1);
        this.body.getBody().setLinearVelocity(ZERO_MOVEMENT,this.body.getBody().getLinearVelocity().y);
        this.tryAttack();
        this.served = this.keyMap.getAttack();
        this.setFlag(true);
        this.body.setCurrentStatus(Status.ATTACK1);
        this.view.setElapsedTime(0);
    }

    /**
     * Adds the key pressed to the buffer
     * @param keycode
     *          the key pressed
     * @return whether it was able to enter the keycode to the buffer
     */
    public boolean enter(final int keycode) {
        if (keycode == this.keyMap.getLeft() || keycode == this.keyMap.getRight()) {
            this.buffer.add(keycode);
            return true;
        }
        return false;
    }
    /**
     * Removes the key pressed from the buffer
     * @param keycode
     *          the key pressed
     * @return whether it was able to remove the keycode from the buffer
     */
    public boolean exit(final int keycode) {
        if (this.buffer.contains((Integer) keycode)) {
            this.buffer.remove((Integer) keycode);
            this.served = -1;
            return true;
        }
        return false;
    }

    private float impulse(final int direction) {
        if (direction == this.keyMap.getLeft()) {
            this.body.setCurrentDirection(Direction.LEFT);
            return -VELOCITY;
        }
        this.body.setCurrentDirection(Direction.RIGHT);
        return VELOCITY;
    }

    /**
     * Sets the Fighter's velocity
     */
    public void doMovement() {
        this.next = this.buffer.getFirst();
        if (this.next != this.served) {
            body.getBody().setLinearVelocity(this.impulse(next),body.getBody().getLinearVelocity().y);
            this.served = this.next;
            this.body.setCurrentStatus(Status.WALK);
        }
    }

    private void manageJoint(final Body playerBody, final Body attackBody) {
        WeldJointDef def = new WeldJointDef();

        def.collideConnected = false;
        def.bodyA = playerBody;
        def.bodyB = attackBody;

        def.initialize(playerBody, attackBody, playerBody.getPosition());
        playerBody.getWorld().createJoint(def);
    }

    private void tryAttack() {
        if (!this.atk1.isPresent()) {
            this.atk1 = Optional.ofNullable(new Attack(this.body, WIDTH_ATTACK, HEIGHT_ATTACK, DAMAGE));
            manageJoint(this.body.getBody(), this.atk1.get().getBody());
            Timer.schedule(this.destroy, END_ATTACK);
        }
    }

    /**
     * @return the buffer
     */
    public LinkedList<Integer> getBuffer() {
    	this.served = -1;
        return this.buffer;
    }
}