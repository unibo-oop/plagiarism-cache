package maingame.game;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import editor.Editor;
import editor.EditorImpl;
import maingame.cursor.CursorImpl;
import maingame.entity.item.Item;
import maingame.entity.mob.Mob;
import maingame.entity.mob.enemy.Enemy;
import maingame.entity.mob.player.Player;
import maingame.graphics.ScreenImpl;
import maingame.input.ModelInput;
import maingame.input.ModelInputImpl;
import maingame.input.keyboardinput.KeyBoardInputImpl;
import maingame.input.mouseinput.MouseInputImpl;
import maingame.level.Level;
import maingame.level.LevelEnum;
import maingame.level.LevelImpl;
import maingame.menu.menu.SimpleFactoryMenuImpl;
import maingame.menu.menu.MenuImpl;
import maingame.sound.SoundImpl;
import maingame.statistics.StatisticsImpl;
import util.Vector2;
import util.Vector2Impl;

/**
 * Creazione oggetto statico game e game loop.
 */

public final class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    /**
     * campo statico per la larghezza del gioco.
     * 
     */
    public static final int WIDTH = 300;
    /**
     * Campo statico per l' altezza del gioco.
     * 
     */
    public static final int HEIGHT = WIDTH * 9 / 16;
    /**
     * scale del gioco.
     * 
     */
    public static final int SCALE = 3;
    private static final Game SINGLETON_GAME = new Game();

    private Thread thread;
    private boolean running;

    private final JFrame frame;
    private final JPanel panelMain;

    // immagine finale da mostrare
    private final BufferedImage image;
    private int[] pixels;
    private Level level;
    private Player player;
    private final ModelInput modelInput;
    private final MenuImpl menuavvio;
    private final MenuImpl menugioco;
    private Vector2<Integer> lastPosition;
    private int lastBrightness;
    private boolean lastDay;
    private final Vector2<Integer> lastMovement;
    private final List<List<Mob>> oldMobs;
    private final List<List<Item>> oldItems;
    private LevelEnum nameLevel;
    private final Editor ed;
    private boolean editor;
    private boolean gameOver;
    private boolean gameWin;
    private boolean gameEnabled;
    private static final int NIGHT_LUMINOSITY = 150;

    private Game() {

        this.running = false;
        this.frame = new JFrame();
        this.panelMain = new JPanel();

        this.menuavvio = new SimpleFactoryMenuImpl().startMenu();
        this.menugioco = new SimpleFactoryMenuImpl().settingsMenu();

        this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.lastMovement = new Vector2Impl<Integer>(0, 0);
        this.oldMobs = new ArrayList<>();
        this.oldItems = new ArrayList<>();
        this.ed = EditorImpl.getEditor();
        final Cursor cursor = CursorImpl.getCursorSpinner();
        this.gameOver = false;
        this.gameWin = false;
        this.gameEnabled = false;
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        final Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setPreferredSize(size);
        modelInput = new ModelInputImpl();

        final KeyBoardInputImpl keyboard = new KeyBoardInputImpl(modelInput);
        addKeyListener(keyboard);
        for (int i = 0; i < LevelEnum.values().length; i++) {
            oldMobs.add(null);
            oldItems.add(null);
        }
        setLevel(LevelEnum.MAIN, false, true);
        final MouseInputImpl mouse = new MouseInputImpl(modelInput);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        menuavvio.enableCommand(true);
        panelMain.add(this);
        panelMain.add(menuavvio);
        panelMain.add(menugioco);
        menugioco.setVisible(false);
        menuavvio.setVisible(true);
        setVisible(false);
        frame.addKeyListener(keyboard);
        frame.add(panelMain);
        this.panelMain.setPreferredSize(this.getPreferredSize());
        this.frame.pack();
        this.setCursor(cursor);
    }

    /**
     * start del thread.
     */
    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    /**
     * stop del thread.
     */
    public synchronized void stop() {
        try {
            running = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        openmenu();
        menugioco.setVisible(false);
        menuavvio.setVisible(false);
        setVisible(true);
        menuavvio.enableCommand(false);
        if (!editor) {
            SoundImpl.BACKGROUND.play(true);
        }
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        int timeToSave = 0;
        final int cost60 = 60;
        boolean limitFps;
        long now;
        int updateStat = 0;
        while (running) {
            limitFps = true;
            now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (ScreenImpl.getScreen().isEndGame()) {
                ScreenImpl.getScreen().setEndGame(false);
                gameOver = false;
                gameWin = false;
                gameReset();
                panelMain.setPreferredSize(menuavvio.getPreferredSize());
                menuavvio.setVisible(true);
                setVisible(false);
                menuavvio.enableCommand(true);
                openmenu();
                now = System.nanoTime();
                lastTime = now;
                menuavvio.setVisible(false);
                setVisible(true);
                menuavvio.enableCommand(false);
                modelInput.resetKeyBoard();
                requestFocus();
            }
            if (modelInput.isPressedEsc()) {
                panelMain.setPreferredSize(menugioco.getPreferredSize());
                menugioco.setVisible(true);
                setVisible(false);
                menugioco.enableCommand(true);
                SoundImpl.BACKGROUND.stop();
                SoundImpl.RAIN.stop();
                SoundImpl.HEART.stop();
                SoundImpl.CAVE.stop();
                SoundImpl.HOUSE.stop();
                SoundImpl.NIGHT.stop();
                SoundImpl.WATERFALL.stop();
                openmenu();
                if ((ScreenImpl.getScreen().isRain() ? level.getBrightness() - ScreenImpl.RAIN_BRIGHTNESS
                        : level.getBrightness()) > NIGHT_LUMINOSITY && nameLevel == LevelEnum.MAIN && !gameOver) {
                    SoundImpl.NIGHT.play(true);
                }
                if (!gameOver) {
                    if (nameLevel == LevelEnum.MAIN) {
                        SoundImpl.BACKGROUND.play(true);
                    } else if (nameLevel == LevelEnum.PIT) {
                        SoundImpl.CAVE.play(true);
                    } else if (nameLevel == LevelEnum.HOUSE) {
                        SoundImpl.HOUSE.play(true);
                    }
                }
                now = System.nanoTime();
                lastTime = now;
                menugioco.setVisible(false);
                setVisible(true);
                menugioco.enableCommand(false);
                modelInput.resetKeyBoard();
                requestFocus();
            }
            if (gameEnabled) {
                gameEnabled = false;
                gameReset();
                panelMain.setPreferredSize(menuavvio.getPreferredSize());
                menuavvio.setVisible(true);
                setVisible(false);
                menuavvio.enableCommand(true);
                openmenu();
                now = System.nanoTime();
                lastTime = now;
                menuavvio.setVisible(false);
                setVisible(true);
                menuavvio.enableCommand(false);
                modelInput.resetKeyBoard();
                requestFocus();
            }
            while (delta >= 1) {
                if (editor) {
                    ed.update();
                } else {
                    update();
                    updateStat++;
                }
                updates++;
                delta--;
                limitFps = false;
            }
            if (updateStat >= cost60) {
                updateStat = 0;
                StatisticsImpl.getStatistics().incrementTime(1);
            }
            if (!limitFps) {
                if (editor) {
                    ed.render();
                } else {
                    render();
                }
                frames++;
            }
            if (Game.SINGLETON_GAME.getPlayer().getHealth() <= 0 && !isGamewin() && !gameOver) {

                SoundImpl.HEART.stop();
                SoundImpl.BACKGROUND.stop();
                SoundImpl.RAIN.stop();
                SoundImpl.CAVE.stop();
                SoundImpl.NIGHT.stop();
                SoundImpl.GAMEOVER.play(false);

                gameOver = true;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer = timer + 1000;
                if (editor) {
                    frame.setTitle("");
                } else {
                    frame.setTitle("Jgame ups " + updates + "| frame " + frames);
                }
                updates = 0;
                frames = 0;
                timeToSave++;
                if (timeToSave >= 10) {
                    timeToSave = 0;
                    StatisticsImpl.getStatistics().save();
                }
            }
        }
        stop();
    }

    /**
     * 
     * @return la lunghezza della finestra
     */
    public int getWindowWidth() {
        return WIDTH * SCALE;
    }

    /**
     * 
     * @return l altezza della finestra
     */
    public int getWindowHeight() {
        return HEIGHT * SCALE;
    }

    /**
     * update del gioco.
     */
    public void update() {
        level.update(level.getLuminosity());
    }

    /**
     * render del gioco.
     */
    public void render() {

        final BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            requestFocus();
            createBufferStrategy(3);
            return;
        }

        final Vector2<Integer> scroll = new Vector2Impl<>(
                player.getPosition().getX() - (int) ScreenImpl.getScreen().getDimension().getWidth() / 2,
                player.getPosition().getY() - (int) ScreenImpl.getScreen().getDimension().getHeight() / 2);

        level.render(scroll);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = ScreenImpl.getScreen().getPixels()[i];
        }

        // creo link tra graphics e buffer
        final Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        ScreenImpl.getScreen().renderDraws(g);

        // rilascio le grafiche vecchie
        g.dispose();

        bs.show();

    }

    /**
     * 
     * @return del level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * 
     * @return dimensioni della scermata di gioco
     */
    public Dimension getDimension() {
        return new Dimension(WIDTH, HEIGHT);
    }

    /**
     * sveglia il game dal wait.
     */
    public void wakeUp() {
        synchronized (SINGLETON_GAME) {
            try {
                SINGLETON_GAME.notifyAll();
            } catch (Exception e) {
                System.err.println("Caught IOException: " + e.getMessage());
            }
            panelMain.setPreferredSize(getPreferredSize());
            SINGLETON_GAME.frame.pack();
            SINGLETON_GAME.frame.setLocationRelativeTo(null);
        }
    }

    private void openmenu() {

        synchronized (SINGLETON_GAME) {
            try {
                SINGLETON_GAME.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 
     * @return il game statico
     */
    public static Game getGame() {
        return SINGLETON_GAME;
    }

    /**
     * 
     * @return il thread
     */
    public Thread getThread() {
        return this.thread;
    }

    /**
     * setta il lever iniziale.
     * 
     * @param level
     *            livello
     * 
     * @param reset
     *            resetta il lever iniziale
     * @param init
     *            Indica l'inizializzazione del livello.
     */
    public void setLevel(final LevelEnum level, final boolean reset, final boolean init) {

        if (this.level != null) {
            oldMobs.set(nameLevel.ordinal(), this.level.getMobs());
            oldItems.set(nameLevel.ordinal(), this.level.getItems());
        }

        setNamelevel(level);
        if (level != LevelEnum.MAIN) {
            lastPosition = this.level.getPlayer().getPosition();
            lastBrightness = this.level.getBrightness();
            lastDay = this.level.isDay();
            lastMovement.set(player.getLastMovement());
        }

        if (level == LevelEnum.PIT && !player.isInjured()) {
            SoundImpl.RAIN.stop();
            SoundImpl.NIGHT.stop();
            SoundImpl.BACKGROUND.stop();
            SoundImpl.CAVE.play(true);
        } else if (level == LevelEnum.HOUSE && !player.isInjured()) {
            SoundImpl.RAIN.stop();
            SoundImpl.NIGHT.stop();
            SoundImpl.BACKGROUND.stop();
            SoundImpl.HOUSE.play(true);
        }
        this.level = new LevelImpl(level.getlevelPath(), level.getmobsItemsPath(), level.getLuminosity());
        if (level == LevelEnum.MAIN && !init) {
            SoundImpl.HOUSE.stop();
            SoundImpl.CAVE.stop();
            SoundImpl.BACKGROUND.play(true);
            if ((ScreenImpl.getScreen().isRain() ? getLevel().getBrightness() - ScreenImpl.RAIN_BRIGHTNESS
                    : getLevel().getBrightness()) > NIGHT_LUMINOSITY) {
                SoundImpl.NIGHT.play(true);
            }
        }
        if (!reset) {
            if (oldMobs.get(nameLevel.ordinal()) != null) {
                this.level.setMobs(oldMobs.get(nameLevel.ordinal()));
            }
            if (oldItems.get(nameLevel.ordinal()) != null) {
                this.level.setItems(oldItems.get(nameLevel.ordinal()));
            }
        }
        if (level != LevelEnum.MAIN) {
            player.setPosition(this.level.getPlayerPos());
        } else {
            if (player == null) {
                player = new Player(this.level.getPlayerPos(), modelInput);
            } else {
                player.setPosition(new Vector2Impl<Integer>(lastPosition.getX() - lastMovement.getX() * 10,
                        lastPosition.getY() - lastMovement.getY() * 10));
                this.level.setBrightness(lastBrightness);
                this.level.setDay(lastDay);
            }
        }

        if (level == LevelEnum.PIT) {
            for (final Mob m : this.level.getMobs()) {
                if (m instanceof Enemy) {
                    m.setSkin(1);
                    m.setDamage(LevelImpl.getDifficulty().getDamage() + 10);
                }
            }
        }
        this.level.add(player);
    }

    /**
     * @param level
     *            setter del level
     */
    public void setLevel(final Level level) {
        this.level = level;
    }

    /**
     * @return se l'editor � attivo
     */
    public boolean isEditor() {
        return editor;
    }

    /**
     * @param editor
     *            setta il timer attivo
     */
    public void setEditor(final boolean editor) {
        this.editor = editor;
        if (!editor) {
            gameEnabled = true;
            EditorImpl.getEditor().reset();
        }
    }

    /**
     * @return getter il player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * main .
     * 
     * @param args
     *            main
     */
    public static void main(final String[] args) {
        // non ridimensionabile dall'utente
        SINGLETON_GAME.frame.setResizable(false);
        SINGLETON_GAME.frame.setTitle("Jgame");
        // schermata impostata a valori preferiti

        SINGLETON_GAME.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // centro la schermata
        SINGLETON_GAME.frame.setLocationRelativeTo(null);
        SINGLETON_GAME.frame.setVisible(true);

        SINGLETON_GAME.start();
    }

    /**
     * @return il nome del liverllo
     */
    public LevelEnum getNamelevel() {
        return nameLevel;
    }

    /**
     * @param namelevel
     *            setta il nome del level
     */
    public void setNamelevel(final LevelEnum namelevel) {
        this.nameLevel = namelevel;
    }

    /**
     * @return se � game over
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * @return se game win
     */
    public boolean isGamewin() {
        return gameWin;
    }

    /**
     * @param win
     *            setter se game è win
     */
    public void setGameWin(final boolean win) {
        SoundImpl.HEART.stop();
        SoundImpl.RAIN.stop();
        SoundImpl.NIGHT.stop();
        SoundImpl.BACKGROUND.stop();
        // Dopo aver controllato che tutti i suoni in loop sono stati stoppati,
        // avvio il sound GAMEWIN.
        SoundImpl.GAMEWIN.play(false);
        if (player.getExp() > StatisticsImpl.getStatistics().getMaxScore()) {
            StatisticsImpl.getStatistics().updateMaxScore(player.getExp());
        }
        StatisticsImpl.getStatistics().save();
        gameWin = win;
    }

    /**
     * resetta il game per iniziare una nuova partita.
     */
    public void gameReset() {
        this.level = null;
        player = null;
        SoundImpl.RAIN.stop();
        SoundImpl.NIGHT.stop();
        SoundImpl.BACKGROUND.stop();

        oldMobs.clear();
        oldItems.clear();
        for (int i = 0; i < LevelEnum.values().length; i++) {
            oldMobs.add(null);
            oldItems.add(null);
        }
        ScreenImpl.getScreen().reset();
        LevelImpl.setTime(0);
        setLevel(LevelEnum.MAIN, true, true);
    }

    /**
     * Setta il danno dei nemici.
     * 
     * @param damage
     *            dannoda assegnare.
     */
    public void setDamage(final int damage) {
        level.getMobs().forEach(new Consumer<Mob>() {

            @Override
            public void accept(final Mob mob) {
                mob.setDamage(damage);
            }
        });
    }

    /**
     * Setta la fine del gioco.
     * 
     * @param end
     *            true se finito
     */
    public void setEnd(final boolean end) {
        this.gameOver = end;
        if (end) {
            SoundImpl.HEART.stop();
            SoundImpl.BACKGROUND.stop();
            SoundImpl.RAIN.stop();
            SoundImpl.CAVE.stop();
            SoundImpl.NIGHT.stop();
            SoundImpl.HOUSE.stop();
            SoundImpl.WATERFALL.stop();
            SoundImpl.GAMEOVER.play(false);
        }
    }

    /**
     * Ritorna la fine del giocp.
     * 
     * @return end true se finito
     */
    public boolean isEnd() {
        return gameOver;
    }

}