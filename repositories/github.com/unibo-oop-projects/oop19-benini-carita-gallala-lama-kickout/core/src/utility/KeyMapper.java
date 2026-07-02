package utility;

import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
/**
 *  Loads from an asset file the specified controls for the players
 */
public class KeyMapper {
    private int left;
    private int jump;
    private int right;
    private int attack;

    /**
     * Sets the controls specified by the file
     * @param path
     *          the name of the asset
     */
    public KeyMapper(final String path) {
        this.keyGetter(path);
    }

    /**
     * Reads the file and links the given controls to MovesetManager
     * @param path
     */
    private void keyGetter(final String path) {
        BufferedReader reader = null;
        
        reader = new BufferedReader(Gdx.files.internal("keyfile/" + path + ".txt").reader());
        try {
            this.left = Keys.valueOf(reader.readLine());
            this.jump = Keys.valueOf(reader.readLine());
            this.right = Keys.valueOf(reader.readLine());
            this.attack = Keys.valueOf(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @return the left key
     */
    public int getLeft() {
        return this.left;
    }

    /**
     * @return the jump key
     */
    public int getJump() {
        return this.jump;
    }

    /**
     * @return the right key
     */
    public int getRight() {
        return this.right;
    }
    /**
     * @return the attack key
     */
    public int getAttack() {
        return this.attack;
    }
}
