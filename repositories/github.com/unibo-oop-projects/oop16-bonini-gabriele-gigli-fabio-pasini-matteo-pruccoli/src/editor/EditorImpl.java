package editor;

import static editor.GUIEditorImpl.getGUIEditor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import editor.model.ModelEditor;
import editor.model.ModelEditorImpl;
import editor.model.ModelEditorImpl.CMB;
import editor.model.ModelEditorImpl.Tool;
import maingame.entity.mob.player.Player;
import maingame.game.Game;
import maingame.graphics.ScreenImpl;
import maingame.level.Level;
import maingame.level.LevelImpl;
import maingame.level.tile.TileImpl;
import util.Color;
import util.Vector2;
import util.Vector2Impl;

/** Classe Editor. */
public final class EditorImpl implements Editor {

    private static final Dimension DIMENSION = new Dimension(Game.WIDTH, Game.HEIGHT);
    private static final int SCALE = Game.SCALE;
    private final BufferedImage image = new BufferedImage((int) DIMENSION.getWidth(), (int) DIMENSION.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private int[] tiles;
    private int[] mobsItems;
    private Level level;
    private Vector2<Integer> player = new Vector2Impl<Integer>(DIMENSION.width / 2, DIMENSION.height / 2);
    private Dimension dimension = new Dimension();
    private List<int[]> tilesHistory = new ArrayList<>();
    private List<int[]> mobsItemsHistory = new ArrayList<>();
    private int historyIndex = -1;
    private int undoIndex;
    private boolean undoed;
    private int playerRemoved;
    private static final EditorImpl EDITOR = new EditorImpl();
    private static final int VOID_COLOR = 0xFF000000;
    private static final int LIGHT_BLUE_COLOR = 0x0000AA;
    private static final int ALPHA = 80;
    private static final int MAX_LUMINOSITY_VALUE = 21;
    private final ModelEditor model = ModelEditorImpl.getModelEditor();
    private String lastPath;

    private EditorImpl() {

    }

    @Override
    public void newLevel(final Dimension dimension) {
        this.dimension.setSize(dimension);
        tiles = new int[(int) (dimension.getWidth() * dimension.getHeight())];
        mobsItems = new int[(int) (dimension.getWidth() * dimension.getHeight())];
        Arrays.fill(tiles, VOID_COLOR);
        Arrays.fill(mobsItems, VOID_COLOR);
        createLevel();
        updateHistory();
        if (playerRemoved == 1) {
            playerRemoved = 0;
            getGUIEditor().addPlayer();
        }
    }

    @Override
    public void loadLevel(final File file) {
        BufferedImage image;
        BufferedImage image1;
        lastPath = file.getPath();
        final String path = file.getPath().substring(0, file.getPath().lastIndexOf(".")) + "_MI"
                + file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
        try {
            image = ImageIO.read(file);
            dimension.setSize(image.getWidth(), image.getHeight());
            tiles = new int[(int) (dimension.getWidth() * dimension.getHeight())];
            mobsItems = new int[(int) (dimension.getWidth() * dimension.getHeight())];
            image.getRGB(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight(), tiles, 0,
                    (int) dimension.getWidth());
            if (new File(path).exists()) {
                image1 = ImageIO.read(new File(path));
                image1.getRGB(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight(), mobsItems, 0,
                        (int) dimension.getWidth());
            } else {
                Arrays.fill(mobsItems, VOID_COLOR);
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
        createLevel();
        updateHistory();
        if (level.getPlayer() != null) {
            if (playerRemoved == 0) {
                getGUIEditor().removePlayer();
                playerRemoved = 1;
            }
        } else {
            if (playerRemoved == 1) {
                getGUIEditor().addPlayer();
                playerRemoved = 0;
            }
        }
    }

    @Override
    public void undo() {
        undoIndex = historyIndex;
        historyIndex--;
        tiles = tilesHistory.get(historyIndex).clone();
        mobsItems = mobsItemsHistory.get(historyIndex).clone();
        createLevel();
        if (historyIndex == 0) {
            getGUIEditor().enableMenuItemUndo(false);
        }
        undoed = true;
    }

    @Override
    public void redo() {
        historyIndex++;
        tiles = tilesHistory.get(historyIndex).clone();
        mobsItems = mobsItemsHistory.get(historyIndex).clone();
        createLevel();
        if (historyIndex == tilesHistory.size() - 1) {
            getGUIEditor().enableMenuItemRedo(false);
        }
        undoed = false;
    }

    @Override
    public void update() {
        if (level != null) {
            level.setBrightness((MAX_LUMINOSITY_VALUE - model.getLumos()) * 10);
            level.update((MAX_LUMINOSITY_VALUE - model.getLumos()) * 10);
        }
    }

    @Override
    public void render() {
        BufferStrategy bs = null;
        Graphics g = null;
        if (level != null) {
            bs = getGUIEditor().getCanvas().getBufferStrategy();
            if (bs == null) {
                getGUIEditor().getCanvas().createBufferStrategy(3);
                return;
            }
            final Vector2<Integer> scroll = new Vector2Impl<>(
                    player.getX() - (int) ScreenImpl.getScreen().getDimension().getWidth() / 2,
                    player.getY() - (int) ScreenImpl.getScreen().getDimension().getHeight() / 2);
            level.render(scroll);
            if (model.isSelected()) {
                for (int i = 0; i < pixels.length; i++) {
                    pixels[i] = ScreenImpl.getScreen().getOriginalPixels()[i];
                    pixels[i] = Color.alphaBlending(pixels[i], ALPHA, LIGHT_BLUE_COLOR);
                }
            } else {
                for (int i = 0; i < pixels.length; i++) {
                    pixels[i] = ScreenImpl.getScreen().getPixels()[i];
                }
            }
            g = bs.getDrawGraphics();
            g.drawImage(image, 0, 0, getGUIEditor().getCanvas().getWidth(), getGUIEditor().getCanvas().getHeight(),
                    null);
            if (model.isShowGrid()) {
                for (int i = 0; i < (int) DIMENSION.getWidth() / TileImpl.TILE_SIZE + 1; i++) {
                    final int x = i * TileImpl.TILE_SIZE * SCALE
                            - (Math.abs((int) DIMENSION.getWidth() * SCALE / 2 - player.getX() * SCALE)
                                    % (TileImpl.TILE_SIZE * SCALE));
                    g.drawLine(x, 0, x, (int) DIMENSION.getHeight() * SCALE);
                }
                for (int i = 0; i < (int) DIMENSION.getHeight() / TileImpl.TILE_SIZE + 1; i++) {
                    final int y = i * TileImpl.TILE_SIZE * SCALE
                            - (Math.abs((int) DIMENSION.getHeight() * SCALE / 2 - player.getY() * SCALE)
                                    % (TileImpl.TILE_SIZE * SCALE));
                    g.drawLine(0, y, (int) DIMENSION.getWidth() * SCALE, y);
                }
            }
            g.dispose();
            bs.show();
        }
        if (model.isExit()) {
            if (bs != null) {
                bs.dispose();
            }
            if (g != null) {
                g.dispose();
            }
            getGUIEditor().disposeFrame();
            Game.getGame().setEditor(false);
        }
    }

    @Override
    public void createLevel() {
        level = new LevelImpl(tiles, mobsItems, dimension, -1);
        level.setBrightness((MAX_LUMINOSITY_VALUE - model.getLumos()) * 10);
        Game.getGame().setLevel(level);
    }

    @Override
    public void updateHistory() {
        tilesHistory.add(Arrays.copyOf(tiles, tiles.length));
        mobsItemsHistory.add(Arrays.copyOf(mobsItems, mobsItems.length));
        historyIndex++;
        if (historyIndex > 0) {
            getGUIEditor().enableMenuItemUndo(true);
        }
        if (undoed) {
            tilesHistory.removeAll(tilesHistory.subList(undoIndex, undoIndex + tilesHistory.size() - historyIndex - 1));
            mobsItemsHistory.removeAll(
                    mobsItemsHistory.subList(undoIndex, undoIndex + mobsItemsHistory.size() - historyIndex - 1));
        }
        model.setModifiedAfterSave(true);
    }

    @Override
    public void save(final File file) {
        final BufferedImage image = new BufferedImage((int) dimension.getWidth(), (int) dimension.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        final BufferedImage image1 = new BufferedImage((int) dimension.getWidth(), (int) dimension.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        int[] array = tiles.clone();
        for (int i = 0; i < mobsItems.length; i++) {
            if (mobsItems[i] != VOID_COLOR) {
                array[i] = mobsItems[i];
            }
        }
        for (int y = 0; y < (int) dimension.getHeight(); y++) {
            for (int x = 0; x < (int) dimension.getWidth(); x++) {
                final int argb = tiles[x + y * (int) dimension.getWidth()];
                final int argb1 = array[x + y * (int) dimension.getWidth()];
                image.setRGB(x, y, argb);
                image1.setRGB(x, y, argb1);
            }
        }
        try {
            ImageIO.write(image, "png", file);
            ImageIO.write(image1, "png", new File(file.getPath().substring(0, file.getPath().lastIndexOf(".")) + "_MI"
                    + file.getName().substring(file.getName().lastIndexOf("."), file.getName().length())));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void toolsActions(final Tool tool, final Vector2<Integer> mousePosition, final CMB type, final int index,
            final boolean dragging) {
        if (mousePosition.getX() >= 0 && mousePosition.getX() < getGUIEditor().getCanvas().getWidth()
                && mousePosition.getY() >= 0 && mousePosition.getY() < getGUIEditor().getCanvas().getHeight()) {
            final int[] tilesCopy = tiles.clone();
            final int[] mobsItemsCopy = mobsItems.clone();
            final int mouseTileIndex = (player.getX() + (mousePosition.getX() / SCALE - (int) DIMENSION.getWidth() / 2))
                    / TileImpl.TILE_SIZE
                    + ((player.getY() + (mousePosition.getY() / SCALE - (int) DIMENSION.getHeight() / 2))
                            / TileImpl.TILE_SIZE) * (int) dimension.getWidth();
            if (tool == Tool.DRAW) {
                if (type == CMB.TILE) {
                    if (model.isSelected()) {
                        Arrays.fill(tiles, TileImpl.getTiles().get(index).getLevelColor());
                        createLevel();
                        getGUIEditor().deselect();
                    } else {
                        tiles[mouseTileIndex] = TileImpl.getTiles().get(index).getLevelColor();
                        createLevel();
                    }
                } else if (type == CMB.MOB) {
                    mobsItems[mouseTileIndex] = LevelImpl.MOBS.get(index + playerRemoved).getLevelColor();
                    createLevel();
                    if (LevelImpl.MOBS.get(index + playerRemoved) instanceof Player) {
                        getGUIEditor().removePlayer();
                        playerRemoved = 1;
                    }
                } else if (type == CMB.ITEM) {
                    mobsItems[mouseTileIndex] = LevelImpl.ITEMS.get(index).getLevelColor();
                    createLevel();
                }
            } else {
                if (model.isSelected()) {
                    Arrays.fill(tiles, VOID_COLOR);
                    Arrays.fill(mobsItems, VOID_COLOR);
                    createLevel();
                    getGUIEditor().deselect();
                    getGUIEditor().addPlayer();
                    playerRemoved = 0;
                } else {
                    boolean b = false;
                    for (int i = 0; i < LevelImpl.MOBS.size(); i++) {
                        if (mobsItems[mouseTileIndex] == LevelImpl.MOBS.get(i).getLevelColor()) {
                            b = true;
                        }
                    }
                    for (int i = 0; i < LevelImpl.ITEMS.size(); i++) {
                        if (mobsItems[mouseTileIndex] == LevelImpl.ITEMS.get(i).getLevelColor()) {
                            b = true;
                        }
                    }
                    if (b) {
                        if (mobsItems[mouseTileIndex] == LevelImpl.MOBS.get(0).getLevelColor()) {
                            getGUIEditor().addPlayer();
                            playerRemoved = 0;
                        }
                        mobsItems[mouseTileIndex] = VOID_COLOR;
                        createLevel();
                    } else {
                        tiles[mouseTileIndex] = VOID_COLOR;
                        createLevel();
                    }
                }
            }
            if (!dragging) {
                boolean b = false;
                for (int i = 0; i < tiles.length; i++) {
                    if (tiles[i] != tilesCopy[i] || mobsItems[i] != mobsItemsCopy[i]) {
                        b = true;
                        break;
                    }
                }
                if (b) {
                    updateHistory();
                }

            }
        }
    }

    @Override
    public void mapMovement(final Vector2<Integer> mousePosition, final Vector2<Integer> mouseStartPosition,
            final Vector2<Integer> playerStartPosition, final int scrollVelocity) {
        player.setX((int) (playerStartPosition.getX()
                + (mouseStartPosition.getX() - mousePosition.getX()) * (scrollVelocity / 10.0)));
        player.setY((int) (playerStartPosition.getY()
                + (mouseStartPosition.getY() - mousePosition.getY()) * (scrollVelocity / 10.0)));
        if (player.getX() < (int) DIMENSION.getWidth() / 2) {
            player.setX((int) DIMENSION.getWidth() / 2);
        } else if (player.getX() > (int) dimension.getWidth() * TileImpl.TILE_SIZE - (int) DIMENSION.getWidth() / 2) {
            player.setX((int) dimension.getWidth() * TileImpl.TILE_SIZE - (int) DIMENSION.getWidth() / 2);
        }
        if (player.getY() < (int) DIMENSION.getHeight() / 2) {
            player.setY((int) DIMENSION.getHeight() / 2);
        } else if (player.getY() > (int) dimension.getHeight() * TileImpl.TILE_SIZE - (int) DIMENSION.getHeight() / 2) {
            player.setY((int) dimension.getHeight() * TileImpl.TILE_SIZE - (int) DIMENSION.getHeight() / 2);
        }
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public Vector2<Integer> getPlayer() {
        return player;
    }

    @Override
    public void reset() {
        tiles = null;
        mobsItems = null;
        level = null;
        player = new Vector2Impl<Integer>(DIMENSION.width / 2, DIMENSION.height / 2);
        dimension = new Dimension();
        tilesHistory = new ArrayList<>();
        mobsItemsHistory = new ArrayList<>();
        historyIndex = -1;
        undoed = false;
        getGUIEditor().reset();
    }

    @Override
    public void confirmSave() {
        if (EditorImpl.EDITOR.getLevel().getPlayer() == null) {
            JOptionPane.showMessageDialog(null, "Nel livello non è presente un player, inseriscilo prima di continuare",
                    "Errore di salvataggio", JOptionPane.WARNING_MESSAGE);
            model.setExit(false);
        } else {
            final JFileChooser j = new JFileChooser(lastPath);
            j.setFileFilter(new FileNameExtensionFilter("Image files", "png"));
            final int input = j.showSaveDialog(null);
            if (input == JFileChooser.APPROVE_OPTION) {
                String s = j.getSelectedFile().getPath();
                if (!s.endsWith(".png")) {
                    s += ".png";
                }
                EditorImpl.EDITOR.save(new File(s));
            }
            model.setModifiedAfterSave(false);
        }
    }

    @Override
    public int[] getTiles() {
        return tiles.clone();
    }

    @Override
    public int[] getMobsItems() {
        return mobsItems.clone();
    }

    @Override
    public List<int[]> getTilesHistory() {
        return new ArrayList<>(tilesHistory);
    }

    @Override
    public List<int[]> getMobsItemsHistory() {
        return new ArrayList<>(mobsItemsHistory);
    }

    /**
     * @return singleton dell'editor
     */
    public static Editor getEditor() {
        return EDITOR;
    }
}
