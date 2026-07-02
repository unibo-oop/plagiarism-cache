package model.mapeditor;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Path of the standard levels.
 *
 */
public enum LevelStandard implements Iterator<LevelStandard> {

    /**
     * Level 1 location and the input to fit the iterator interface.
     */
    LEVEL1("standardLevel/default", "default", 0, false),

    /**
     * Level 2 location and the input to fit the iterator interface.
     */
    LEVEL2("standardLevel/arkanoid", "arkanoid", 1, false),

    /**
     * Level 3 location and the input to fit the iterator interface.
     */
    LEVEL3("standardLevel/galaga", "galaga", 2, false),

    /**
     * Level 4 location and the input to fit the iterator interface.
     */
    LEVEL4("standardLevel/pacman", "pacman", 3, false),

    /**
     * Level 5 location and the input to fit the iterator interface.
     */
    LEVEL5("standardLevel/mario", "mario", 4, false),

    /**
     * Level 6 location and the input to fit the iterator interface.
     */
    LEVEL6("standardLevel/crash", "crash", 5, true);

    private String path;
    private String name;
    private int index;
    private boolean isLast;

    LevelStandard(final String path, final String name, final int index, final boolean isLast) {
        this.path = path;
        this.name = name;
        this.index = index;
        this.isLast = isLast;
    }

    /**
     * 
     * @return path of level
     */
    public String getPath() {
        return path;
    }

    /**
     * 
     * @return name of level
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return index of level
     */
    public int getIndex() {
        return index;
    }

    /**
     * 
     * @return true if it's the last, false otherwise
     */
    public boolean isLast() {
        return isLast;
    }

    /**
     * 
     * @return load the standard level saved in the resources
     */
    public Level getLevel() {
        Level level = null;
        try (ObjectInputStream istream = new ObjectInputStream(
        new BufferedInputStream(ClassLoader.getSystemResourceAsStream(path)))) {
            level = (Level) istream.readObject();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (ClassCastException e3) {
            e3.printStackTrace();
        } 
        return level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return !this.isLast;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LevelStandard next() {
        return Arrays.asList(LevelStandard.values()).get(index + 1);
    }

    /**
     * 
     * @param nameLvl the name level to search
     * @return if the level is one of the default
     */
    public static boolean isStandardLevel(final String nameLvl) {
        return Arrays.asList(LevelStandard.values()).stream()
                                                     .map(j -> j.getName())
                                                     .anyMatch(j -> j.equals(nameLvl));
    }

    /**
     * 
     * @param level to search in this enum
     * @return the corresponding enumeration linked to the standard level, if is null return the first level.
     */
    public static LevelStandard getSelectionFromLevel(final Level level) {
        return  Arrays.asList(LevelStandard.values()).stream()
                                                     .filter(i -> i.getLevel().equals(level))
                                                     .findFirst()
                                                     .orElse(LEVEL1);
    }
}
