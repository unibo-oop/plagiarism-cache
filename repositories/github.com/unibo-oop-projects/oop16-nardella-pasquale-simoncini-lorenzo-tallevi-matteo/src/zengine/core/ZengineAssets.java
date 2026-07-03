package zengine.core;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.imageio.ImageIO;
import zengine.interfaces.GameFunctionsAssets;

final class ZengineAssets implements ZengineComponent, GameFunctionsAssets {

    private static ZengineAssets assets = new ZengineAssets();

    private final Map<String, GameSprite> spriteList = new HashMap<String, GameSprite>();
    private final Map<String, GameFont> fontList = new HashMap<String, GameFont>();
    private final Map<String, ArrayList<GameSound>> soundList = new HashMap<String, ArrayList<GameSound>>();
    // string not containing the full path only when passed as argument
    // by user
    // getresourceAsStream always wants a / before the string

    private ZengineAssets() {
    }

    public static ZengineAssets getAssets() {
        return assets;
    }

    @Override
    public void initialize() {
    }

    @Override
    public ZengineComponent getComponent() {
        return assets;
    }

    @Override
    public void reinitialize() {
        flushResources();
        initialize();
    }

    @Override
    public void restoreDefaultValues() {
        // do nothing
        // maybe flush some resources like audio? better no, for performances.
    }

    @Override
    public void link() {
        // build some refs? Not necessary now, maybe in future releases
    }

    @Override
    public void update() {
        // this component does not update
    }

    private void flushResources() {
        ZengineLogger.getLogger().loggerMessage("clearing fonts...");
        fontList.clear();
    }

    @Override
    public boolean spriteExists(final String sprite) {
        return spriteExistsExt(Zengine.USR_SPRITE_PATH + sprite);
    }

    @Override
    public int spriteGetNumber(final String sprite) {
        return spriteGet(Zengine.USR_SPRITE_PATH + sprite).getNumber();
    }

    @Override
    public int spriteGetXoffset(final String sprite) {
        return spriteGet(Zengine.USR_SPRITE_PATH + sprite).getxOffset();
    }

    @Override
    public int spriteGetYoffset(final String sprite) {
        return spriteGet(Zengine.USR_SPRITE_PATH + sprite).getyOffset();
    }

    @Override
    public int spriteGetWidth(final String sprite) {
        return spriteGet(Zengine.USR_SPRITE_PATH + sprite).getWidth();
    }

    @Override
    public int spriteGetHeight(final String sprite) {
        return spriteGet(Zengine.USR_SPRITE_PATH + sprite).getHeight();
    }

    private boolean spriteExistsExt(final String sprite) {
        if (spriteList.containsKey(sprite)) {
            return true;
        } else {
            return createEntryForSprite(sprite);
        }
    }

    private GameSprite spriteLoad(final String fullName) {
        // given a pathname, returns a GameSprite built on that
        // pathname. If path is invalid or does not exist, returns an empty
        // sprite

        GameSprite ret = null;
        // list in here possible fotograms
        List<String> subimagesPaths = null;
        // put in here images relatife to fotograms
        final List<Image> subimages = new ArrayList<>();
        // populate strings relatife to fotograms
        subimagesPaths = listResourceFiles(fullName, Zengine.USR_SPRITE_EXT);
        if (subimagesPaths != null) {
            // sort alphabetically
            subimagesPaths.sort((s1, s2) -> s1.compareTo(s2));
            for (final String s : subimagesPaths) {
                // create an image for each fotogram
                subimages.add(loadImage(s));
            }
            // builds a new sprite with the given subimages
            ret = new GameSprite(subimages);
            // calculate his offset based on info retrieved inside
            // spriteInfo.txt
            int xOffset = 0;
            int yOffset = 0;
            final OffsetInformations info = readSpriteOffset(fullName);
            if (info.isXRelative()) {
                xOffset = (int) (((double) info.getX()) / 100 * ret.getWidth());
            } else {
                xOffset = info.getX();
            }
            if (info.isYRelative()) {
                yOffset = (int) (((double) info.getY()) / 100 * ret.getHeight());
            } else {
                yOffset = info.getY();
            }
            ret.setxOffset(xOffset);
            ret.setyOffset(yOffset);
        }
        // ZengineLogger.getLogger().loggerMessage("loaded " + fullName);
        return ret;
    }

    private boolean createEntryForSprite(final String fullName) {
        // creates an entry for the sprite fullName. returns true if successfull
        if (pathExists(fullName)) {
            // creates a new sprite. if path is invalid (for example it does not
            // exist or it exists but contains no images) in this branch of the
            // if, sprite is empty
            spriteList.put(fullName, spriteLoad(fullName));
            ZengineLogger.getLogger().loggerMessage("loaded " + fullName);
            return true;
        } else {
            ZengineLogger.getLogger().loggerWarning("invalid path " + fullName);
            return false;
        }
    }

    // used by ZengineGUI
    // should have been package private, but public is fine as well since the
    // class is package private
    /**
     * returns the gameSprite memorized with the given name. If no sprite exists
     * it returns null
     * 
     * @param fullName
     *            name of the sprite to get
     * @return reference of the GameSprite or null if the name is invalid
     */
    public GameSprite spriteGet(final String fullName) {

        // name=name.substring(Ploggy.userSpritesPath.length());
        GameSprite ret = null;
        if (spriteExistsExt(fullName)) {
            ret = spriteList.get(fullName);
        } else {
            ZengineLogger.getLogger().loggerError("nonexistent sprite " + fullName);
        }
        return ret;
    }

    private Image loadImage(final String fileName) {
        // returns image contained in fileName

        BufferedImage buff = null;
        final InputStream input = pathGetStream(fileName);
        if (input != null) {
            try {
                buff = ImageIO.read(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ZengineLogger.getLogger().loggerError("Cannot find image " + fileName);
        }
        return buff;
    }

    // should have been package private, but public is fine as well since the
    // class is package private
    /**
     * checks if the given path is valid. Path is relative to the application's
     * internal root folder.
     * 
     * @param path
     *            name of the path to test
     * @return true if given path is valid
     */
    public boolean pathExists(final String path) {
        // returns true if path exists
        final InputStream in = pathGetStream(path);
        return in != null;
    }

    // should have been package private, but public is fine as well since the
    // class is package private
    /**
     * returns an InputStream built on the given path. Path is relative to the
     * application's internal root folder. if path is invalid, it returns null.
     * 
     * @param path
     *            name of the path to transform into a stream
     * @return InputStream built on that path, or null if path is invalid
     */
    public InputStream pathGetStream(final String path) {
        // returns the stream associated to the given path. if path is invalid,
        // it returns null
        return getClass().getResourceAsStream("/" + normalizePath(path));
    }

    // should have been package private, but public is fine as well since the
    // class is package private
    /**
     * removes every / at the beginning of the path described by specified
     * string. for example, "/res/sprites/pacman/" will become
     * "res/sprites/pacman/", "/////res/sprites/winky/" will become
     * "res/sprites/winky/"
     * 
     * @param path
     *            path to normalize
     * @return normalized path
     */
    public String normalizePath(final String path) {
        // removes every / at the beginning
        String ret = path;
        while (ret.startsWith("/")) {
            ret = ret.substring(1);
        }
        return ret;
    }

    private List<String> listResourceFiles(final String path, final String extension) {
        // lists every file inside "path" whose filename ends with "extension",
        // as a list of strings. If path is invalid, returns an empty list
        final List<String> filenames = new ArrayList<>();
        String normPath = normalizePath(path);
        if (normPath.endsWith(extension) && !extension.equals("")) {
            filenames.add(normPath);
        } else {
            final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());

            if (jarFile.isFile()) { // You are in the JAR
                try {
                    final JarFile jar = new JarFile(jarFile);
                    // all entries of the jar
                    final Enumeration<JarEntry> entries = jar.entries();
                    // remove trailing /
                    if (normPath.endsWith("/")) {
                        normPath = normPath.substring(0, normPath.length() - 1);
                    }
                    while (entries.hasMoreElements()) {
                        final String name = entries.nextElement().getName();
                        // filter according to path
                        if (name.startsWith(normPath + "/") && name.endsWith(extension)) {
                            filenames.add(name);
                        }
                    }
                    jar.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else { // You are in the IDE
                final InputStream in = pathGetStream(normPath);
                if (in != null) {
                    final BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String resource;
                    try {
                        // while ((resource = br.readLine()) != null) {
                        resource = br.readLine();
                        while (resource != null) {
                            if (resource.endsWith(extension)) {
                                if (normPath.endsWith("/")) {
                                    // System.out.println(path.concat(resource));
                                    filenames.add(normPath.concat(resource));
                                } else {
                                    // System.out.println(path.concat("/").concat(resource));
                                    filenames.add(normPath.concat("/").concat(resource));
                                }
                            }
                            resource = br.readLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    ZengineLogger.getLogger().loggerWarning(normPath + " is invalid");
                }
            }
        }
        return filenames;
    }

    private OffsetInformations readSpriteOffset(final String fullName) {
        // reads sprite xoffset and yoffset written inside spriteInfo.txt
        // located inside this folder
        // if path is invalid, file is malformed or nonexistent returns 0, 0
        final InputStream in = pathGetStream(normalizePath(fullName) + "/" + Zengine.USR_SPRITEINFO_FILENAME);
        int x = 0;
        boolean xrel = false;
        int y = 0;
        boolean yrel = false;

        if (in != null) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            try {
                // read first two lines
                String line1 = reader.readLine();
                String line2 = reader.readLine();
                if (line1 != null && line2 != null) {
                    // check if they are percent numbers
                    if (line1.endsWith("%")) {
                        xrel = true;
                        line1 = line1.substring(0, line1.length() - 1);
                    }
                    if (line2.endsWith("%")) {
                        yrel = true;
                        line2 = line2.substring(0, line2.length() - 1);
                    }
                    // parse the two lines
                    x = Integer.parseInt(line1);
                    y = Integer.parseInt(line2);
                }
            } catch (IOException e) {
                return new OffsetInformations(0, 0, false, false);
            } catch (NumberFormatException e) {
                ZengineLogger.getLogger().loggerWarning("malformed " + Zengine.USR_SPRITEINFO_FILENAME + " in " + fullName);
                return new OffsetInformations(0, 0, false, false);
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // returns the built information
        return new OffsetInformations(x, y, xrel, yrel);
    }

    /**
     * this class stores informations about a pair of offset coordinates for
     * sprites.
     */
    private class OffsetInformations {
        private final int x;
        private final int y;
        private final boolean xRelative;
        private final boolean yRelative;

        OffsetInformations(final int x, final int y, final boolean xRelative, final boolean yRelative) {
            this.x = x;
            this.y = y;
            this.xRelative = xRelative;
            this.yRelative = yRelative;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean isXRelative() {
            return xRelative;
        }

        public boolean isYRelative() {
            return yRelative;
        }
    }

    @Override
    public boolean fontDefine(final String fontName, final String alias, final float size) {
        if (ZengineSystem.getSystem().isInitializeEvent()) {
            if (!fontAliasExists(alias)) {
                return createEntryForFont(Zengine.USR_FONT_PATH + fontName + Zengine.USR_FONT_EXT, alias, size);
            } else {
                ZengineLogger.getLogger().loggerWarning("Warning: trying to redefine a font named " + alias);
                return false;
            }
        } else {
            ZengineLogger.getLogger().loggerWarning("Warning: cannot define fonts outside initializate event");
            return false;
        }
    }

    @Override
    public boolean fontExists(final String fontName) {
        return pathExists(Zengine.USR_FONT_PATH + fontName + Zengine.USR_FONT_EXT);
    }

    @Override
    public boolean fontAliasExists(final String alias) {
        return fontList.containsKey(alias);
    }

    @Override
    public double fontGetHeight(final String alias) {
        if (fontAliasExists(alias)) {
            return fontGet(alias).getHeight();
        } else {
            ZengineLogger.getLogger().loggerWarning("nonexistent font alias " + alias);
            return 0;
        }
    }

    private GameFont fontLoad(final String fullName, final String alias, final float size) {
        final GameFont ret = new GameFont(fullName, alias, size);
        if (ret.isValid()) {
            ZengineLogger.getLogger().loggerMessage("loaded " + fullName);
        } else {
            ZengineLogger.getLogger().loggerWarning("could not load " + fullName);
        }
        return ret;
    }

    private boolean createEntryForFont(final String fullName, final String alias, final float size) {
        // try to create the font
        final GameFont f = fontLoad(fullName, alias, size);
        if (f.isValid()) {
            if (!fontAliasExists(alias)) {
                fontList.put(alias, f);
                ZengineLogger.getLogger().loggerMessage("defined font alias \"" + alias + "\" from " + fullName);
                return true;
            } else {
                ZengineLogger.getLogger().loggerWarning("cannot redefine font with alias " + alias);
                return false;
            }
        } else {
            ZengineLogger.getLogger().loggerWarning("cannot define invalid font " + fullName);
            return false;
        }
    }

    // should have been package private, but public is fine as well since the
    // class is package private
    /**
     * returns the GameFont associated with the specified alias, if existing.
     * Otherwise, it returns null.
     * 
     * @param alias
     *            name of the alias of the GameFont to get
     * @return reference to the desiredGameFont, or null if no such font exists
     */
    public GameFont fontGet(final String alias) {
        if (fontAliasExists(alias)) {
            return fontList.get(alias);
        } else {
            return null;
        }
    }

    // should have been package private, but public is fine as well since the
    // class is package private
    /**
     * returns the number of the fonts that the user has defined.
     * 
     * @return the number of defined fonts
     */
    public int fontGetNumberOfDefinedFonts() {
        return fontList.size();
    }

    @Override
    public boolean soundExists(final String soundName) {
        if (soundList.containsKey(soundName)) {
            return true;
        } else {
            final String fullName = "/" + Zengine.USR_SOUND_PATH + soundName + Zengine.USR_SOUND_EXT;
            if (pathExists(fullName)) {
                soundList.put(soundName, new ArrayList<>());
                ZengineLogger.getLogger().loggerMessage("created entry for sound " + soundName);
                return true;
            } else {
                return false;
            }
        }
    }

    // allocates a new clip for the sound soundName and adds it to the list,
    // returns the added sound.
    private GameSound addNewSound(final String soundName) {
        if (soundExists(soundName)) {
            final GameSound toAdd = new GameSound(soundName);
            soundList.get(soundName).add(toAdd);
            return toAdd;
        } else {
            return null;
        }
    }

    // should have been package private, but public is fine as well since the
    // class is package private
    /**
     * returns a GameSound that is ready to play the sound soundName. This means
     * that:
     * <p>
     * <ul>
     * <li>if no sound has ever been played, a new GameSound is allocated and
     * returned
     * <li>if it has already been played, the first idle GameSound found is
     * returned
     * <li>if all GameSound are busy, a new one is allocated and returned
     * </ul>
     * <p>
     * this will allow to reuse GameSounds that have been previously allocated,
     * in order to avoid opening a new AudioStream for every sound clip that the
     * user requests and thus improving performance.
     * 
     * @param soundName
     *            name of the sound to get
     * @return reference of the gameSound ready to play soundName, or null if
     *         soundName is an invalid name.
     */
    public GameSound soundGet(final String soundName) {
        if (soundExists(soundName)) {
            final ArrayList<GameSound> specificList = soundList.get(soundName);
            if (specificList.isEmpty()) {
                return addNewSound(soundName);
            } else {
                boolean found = false;
                GameSound target = null;
                int i = 0;

                while (!found && i < specificList.size()) {
                    if (!specificList.get(i).isBusy()) {
                        found = true;
                        target = specificList.get(i);
                    }
                    i++;
                }
                if (target == null) {
                    return addNewSound(soundName);
                } else {
                    return target;
                }
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean musicExists(final String musicName) {
        if (musicName.equals("")) {
            return false;
        } else {
            return pathExists("/" + Zengine.USR_MUSIC_PATH + musicName + Zengine.USR_MUSIC_EXT);
        }
    }

    // should have been package private, but public is fine as well since the
    // class is package private
    /**
     * counts how many GameSounds are currently playing the given sound effect.
     * 
     * @param soundName
     *            name of the sound to count
     * @return how many GameSounds are currently playing the given sound effect
     */
    public int numberOfPlayingSounds(final String soundName) {
        if (soundExists(soundName)) {
            final ArrayList<GameSound> specificList = soundList.get(soundName);
            int count = 0;
            for (final GameSound s : specificList) {
                if (s.isBusy()) {
                    count++;
                }
            }
            return count;
        } else {
            return 0;
        }
    }

    // should have been package private, but public is fine as well since the
    // class is package private
    /**
     * stops all GameSounds associated with the given sound effect.
     * 
     * @param soundName
     *            name of all the sound effects to stop
     */
    public void stopAllSounds(final String soundName) {
        if (soundExists(soundName)) {
            final ArrayList<GameSound> specificList = soundList.get(soundName);
            if (!specificList.isEmpty()) {
                for (final GameSound s : specificList) {
                    s.stop();
                }
            }
        }
    }
}
