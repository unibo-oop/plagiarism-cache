package thatlevelagain.view.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import thatlevelagain.ScreenSize;
import thatlevelagain.character.enemies.CarImpl;
import thatlevelagain.character.enemies.GomitoloImpl;
import thatlevelagain.character.player.Player;
import thatlevelagain.sound.SoundManager;
import thatlevelagain.sound.SoundPath;
import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;
import thatlevelagain.view.panel.GamePanel;
import thatlevelagain.view.sprite.Button;
import thatlevelagain.view.sprite.Dog;
import thatlevelagain.view.sprite.Door;
import thatlevelagain.view.sprite.End;
import thatlevelagain.view.sprite.Impostazioni;
import thatlevelagain.view.sprite.Lamp;
import thatlevelagain.view.sprite.Mattoni;
import thatlevelagain.view.sprite.Rock;
import thatlevelagain.view.sprite.Scossa;
import thatlevelagain.view.sprite.Spine;
import thatlevelagain.view.sprite.SpineAlte;
import thatlevelagain.view.sprite.trophies.Trophies;
import thatlevelagain.view.sprite.trophies.Trophy1;
import thatlevelagain.view.sprite.trophies.Trophy10;
import thatlevelagain.view.sprite.trophies.Trophy11;
import thatlevelagain.view.sprite.trophies.Trophy12;
import thatlevelagain.view.sprite.trophies.Trophy2;
import thatlevelagain.view.sprite.trophies.Trophy3;
import thatlevelagain.view.sprite.trophies.Trophy4;
import thatlevelagain.view.sprite.trophies.Trophy5;
import thatlevelagain.view.sprite.trophies.Trophy6;
import thatlevelagain.view.sprite.trophies.Trophy7;
import thatlevelagain.view.sprite.trophies.Trophy8;
import thatlevelagain.view.sprite.trophies.Trophy9;
import thatlevelagain.view.state.GameStateManagerImpl;

/**
 * 
 * class that create map for the first level.
 *
 */
public abstract class MapImpl implements Map {

    private final GameStateManagerImpl manager;
    private static final int DIMM1 = GamePanel.BLOCK_WIDTH;
    private static final int DIMM2 = GamePanel.BLOCK_HEIGHT;
    private final BufferedImage base;
    private static final int VALUE = 0xff;
    private static final int DUE = 2;
    private static final int QUATTRO = 4;
    private static final int TRE = 3;
    private static final int CINQUE = 5;
    private static final int MAX_ROCK = 12;
    private static final int LVL_11  = 11;
    private static final int COUNTER = 650;
    private static final int WAIT_SCOSSA = 200;
    private static final int CHANGE_SCOSSA_IMAGE = 10;
    private static final int LVL12 = 12;
    private final List<Mattoni> list1;
    private final List<Spine> list2;
    private final List<SpineAlte> list3;
    private final List<Trophies> list4;
    private final List<Rock> list6;
    private Lamp lamp;
    private Impostazioni imp;
    private Button button;
    private End end;
    private Player cat;
    private Door door;
    private boolean trophy1, trophy2, trophy3, trophy4, trophy5, trophy6, trophy7, trophy8, trophy9, trophy10, trophy11, trophy12;
    private int level;
    private Dog dog;
    private boolean presentDog;
    private boolean lights;
    private final Image img;
    private boolean skipLevel;
    private boolean gomitoloDraw;
    private GomitoloImpl gomitolo;
    private boolean scossaPresent;
    private boolean firstScossa;
    private final List<Scossa> list5;
    private boolean doorLevel;
    private boolean procedi;
    private boolean rockPresent;
    private int counterAhah;
    private int scossaSound;
    private CarImpl ag;
    private boolean agPresent;
    private boolean viewDogZone;
    private int scossaCounter;

    /**
     * @param image
     *         image 
     * @param manager
     *         set actual manager
     */
    public MapImpl(final BufferedImage image, final GameStateManagerImpl manager) {
        this.manager = manager;
        list1 = new LinkedList<>();
        list2 = new LinkedList<>();
        list3 = new LinkedList<>();
        list4 = new LinkedList<>();
        list5 = new LinkedList<>();
        list6 = new LinkedList<>();
        this.viewDogZone = false;
        this.scossaSound = 0;
        this.scossaCounter = 0;
        this.procedi = true;
        this.counterAhah = 1;
        this.presentDog = false;
        this.firstScossa = true;
        this.scossaPresent = false;
        this.gomitoloDraw = false;
        img = ImageManager.getListLoader().get(ImagePath.MATTONE.getPosition());
        base = image;
    }
    @Override
    public final void creaMappa() {
        final int width = base.getWidth();
        final int height = base.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int pixel = base.getRGB(x, y);
                final int rosso = (pixel >> 16) & VALUE;
                final int verde = (pixel >> 8) & VALUE;
                final int blu = (pixel) & VALUE;
                final Pixel pixelAtt = new Pixel(rosso, verde, blu);
                if (pixelAtt.compare(Colore.NERO)) {
                    this.list1.add(new Mattoni(x * DIMM1, y * DIMM2, DIMM1, DIMM2));
                }
                if (pixelAtt.compare(Colore.FUXIA)) {
                    this.imp = new Impostazioni(x * DIMM1 - DIMM1 / QUATTRO, y * DIMM2 - DIMM2 / QUATTRO, DIMM1 + DIMM1 / DUE, DIMM2 + DIMM2 / DUE);
                }
                if (pixelAtt.compare(Colore.ROSSO)) {
                    this.list2.add(new Spine(x * DIMM1, y * DIMM2, DIMM1, DIMM2 / DUE, false));
                }
                if (pixelAtt.compare(Colore.GIALLO)) {
                    this.lamp = new Lamp(x * DIMM1 - DIMM1 / QUATTRO, y * DIMM2 - DIMM2 / QUATTRO, DIMM1 + DIMM1 / DUE, DIMM2 + DIMM2 / DUE);
                }
                if (pixelAtt.compare(Colore.GRIGIO)) {
                    this.list3.add(new SpineAlte(x * DIMM1, y * DIMM2 + DIMM2 / 2, DIMM1 * ScreenSize.NUMBER_BLOCK_WIDTH - DIMM2 * DUE, DIMM2 / DUE));
                }
                if (pixelAtt.compare(Colore.BLU)) {
                    this.button = new Button(x * DIMM1 - DIMM1, y * DIMM2 + DIMM2 / DUE, TRE * DIMM1, DIMM2 - DIMM2 / DUE);
                }
                if (pixelAtt.compare(Colore.ARANCIONE)) {
                    this.end = new End(x * DIMM1 - DIMM1 / DUE, y * DIMM2, DIMM1 + DIMM1 / DUE, DIMM2, level);
                }
                if (pixelAtt.compare(Colore.VERDE)) {
                    this.cat = new Player(x * DIMM1, y * DIMM2 - DIMM2, DIMM1 + DIMM1, DIMM2 + DIMM2 / DUE, this);
                }
                if (pixelAtt.compare(Colore.CIANO)) {
                    this.door = new Door(x * DIMM1 - DIMM1, y * DIMM2 - DUE * DIMM2, DIMM1, TRE * DIMM2);
                    if (this.getLevel() == 4) {
                        this.getDoor().setFalseOpen(true);
                    }
                }
                if (pixelAtt.compare(Colore.BLUGRIGIO)) {
                    list1.add(new Mattoni(x * DIMM1, y * DIMM2, DIMM1 * ScreenSize.NUMBER_BLOCK_WIDTH, DIMM2 * TRE));
                }
                if (pixelAtt.compare(Colore.ROSSOCHIARO)) {
                    list1.add(new Mattoni(x * DIMM1, y * DIMM2, DIMM1 * ScreenSize.NUMBER_BLOCK_WIDTH - DIMM2 * DUE, DIMM2));
                }
                if (pixelAtt.compare(Colore.VERDEGRIGIO)) {
                    list1.add(new Mattoni(x * DIMM1, y * DIMM2, DIMM1, DIMM2 * ScreenSize.NUMBER_BLOCK_HEIGHT - DIMM2 * TRE));
                }
                if (pixelAtt.compare(Colore.VIOLA)) {
                    list2.add(new Spine(x * DIMM1, y * DIMM2, DIMM1 * ScreenSize.NUMBER_BLOCK_WIDTH - DIMM2 * DUE, DIMM2 / DUE, true));
                }
                if (pixelAtt.compare(Colore.GRIGIO1)) {
                    list4.add(new Trophy1(x * DIMM1 - DIMM1 / QUATTRO, y * DIMM2 - DIMM2 / QUATTRO, DIMM1 + DIMM1 / DUE, DIMM2 + DIMM2 / DUE, trophy1));
                }
                if (pixelAtt.compare(Colore.GRIGIO2)) {
                    list4.add(new Trophy2(x * DIMM1 - DIMM1 / QUATTRO, y * DIMM2 - DIMM2 / QUATTRO, DIMM1 + DIMM1 / DUE, DIMM2 + DIMM2 / DUE, trophy2));
                }
                if (pixelAtt.compare(Colore.GRIGIO3)) {
                    list4.add(new Trophy3(x * DIMM1 - DIMM1 / QUATTRO, y * DIMM2 - DIMM2 / QUATTRO, DIMM1 + DIMM1 / DUE, DIMM2 + DIMM2 / DUE, trophy3));
                }
                if (pixelAtt.compare(Colore.GRIGIO4)) {
                    list4.add(new Trophy4(x * DIMM1 - DIMM1 / QUATTRO, y * DIMM2 - DIMM2 / QUATTRO, DIMM1 + DIMM1 / DUE, DIMM2 + DIMM2 / DUE, trophy4));
                }
                if (pixelAtt.compare(Colore.GRIGIO5)) {
                    list4.add(new Trophy5(x * DIMM1 - DIMM1 / QUATTRO, y * DIMM2 - DIMM2 / QUATTRO, DIMM1 + DIMM1 / DUE, DIMM2 + DIMM2 / DUE, trophy5));
                }
                if (pixelAtt.compare(Colore.GRIGIO6)) {
                    list4.add(new Trophy6(x * DIMM1 - DIMM1 / QUATTRO, y * DIMM2 - DIMM2 / QUATTRO, DIMM1 + DIMM1 / DUE, DIMM2 + DIMM2 / DUE, trophy6));
                }
                if (pixelAtt.compare(Colore.GRIGIO7)) {
                    list4.add(new Trophy7(x * DIMM1 - DIMM1 / QUATTRO, y * DIMM2 - DIMM2 / QUATTRO, DIMM1 + DIMM1 / DUE, DIMM2 + DIMM2 / DUE, trophy7));
                }
                if (pixelAtt.compare(Colore.GRIGIO8)) {
                    list4.add(new Trophy8(x * DIMM1 - DIMM1 / QUATTRO, y * DIMM2 - DIMM2 / QUATTRO, DIMM1 + DIMM1 / DUE, DIMM2 + DIMM2 / DUE, trophy8));
                }
                if (pixelAtt.compare(Colore.GRIGIO9)) {
                    list4.add(new Trophy9(x * DIMM1 - DIMM1 / QUATTRO, y * DIMM2 - DIMM2 / QUATTRO, DIMM1 + DIMM1 / DUE, DIMM2 + DIMM2 / DUE, trophy9));
                }
                if (pixelAtt.compare(Colore.GRIGIO10)) {
                    list4.add(new Trophy10(x * DIMM1 - DIMM1 / QUATTRO, y * DIMM2 - DIMM2 / QUATTRO, DIMM1 + DIMM1 / DUE, DIMM2 + DIMM2 / DUE, trophy10));
                }
                if (pixelAtt.compare(Colore.GRIGIO11)) {
                    list4.add(new Trophy11(x * DIMM1 - DIMM1 / QUATTRO, y * DIMM2 - DIMM2 / QUATTRO, DIMM1 + DIMM1 / DUE, DIMM2 + DIMM2 / DUE, trophy11));
                }
                if (pixelAtt.compare(Colore.GRIGIO12)) {
                    list4.add(new Trophy12(x * DIMM1 - DIMM1 / QUATTRO, y * DIMM2 - DIMM2 / QUATTRO, DIMM1 + DIMM1 / DUE, DIMM2 + DIMM2 / DUE, trophy12));
                }
                if (pixelAtt.compare(Colore.MAGENTA)) {
                    dog = new Dog(x * DIMM1 - DIMM1 / DUE, y * DIMM2 - DIMM2, DIMM1 + DIMM1, DIMM2 + DIMM2);
                    this.presentDog = true;
                }
                if (pixelAtt.compare(Colore.GIALLINO)) {
                    gomitolo = new GomitoloImpl(x * DIMM1, y * DIMM2, DIMM1, DIMM2, this);
                    this.gomitoloDraw = true;
                }
                if (pixelAtt.compare(Colore.VERDINO)) {
                    this.list5.add(new Scossa(x * DIMM1 + DIMM1 / QUATTRO, y * DIMM2, DIMM1 / DUE, DIMM2, this.firstScossa, this));
                    this.scossaPresent = true;
                    if (this.firstScossa) {
                        this.firstScossa = false;
                    } else {
                        this.firstScossa = true;
                    }
                }
                if (this.getLevel() == LVL_11 && this.procedi) {
                    for (int i = 0; i < MAX_ROCK; i++) {
                        list6.add(new Rock(0, 0, DIMM1, DIMM2 * 3 / 2));
                    }
                    this.rockPresent = true;
                    this.procedi = false;
                }
                if (pixelAtt.compare(Colore.MARRONE)) {
                    ag = new CarImpl(x * DIMM1 - DIMM1 * 3 / 2, y * DIMM2 - DIMM2 / 3 * 2, DIMM1 + DIMM1 * 3 / 2, DIMM2 + DIMM2 / 3 * 2, this);
                    this.agPresent = true;
                }
            }
        }
    }

    @Override
    public final void eliminaElementi() {
        this.list1.clear();
        this.list2.clear();
        this.list3.clear();
        this.list4.clear();
    }
    /**
     * 
     * @return
     *         AccalappiaGatti
     */
    public CarImpl getAccalappiaGatti() {
        return this.ag;
    }

    @Override
    public final void draw(final Graphics2D g) {
        for (final SpineAlte sA : this.list3) {
            sA.disegna(g);
            sA.reduce();
        }
        for (final Spine s : this.list2) {
            s.disegna(g);
            s.reduce();
        }
        for (final Mattoni m : this.list1) {
            m.disegna(g);
        }
        for (final Trophies t : this.list4) {
            t.disegna(g);
        }
        if (this.agPresent) {
            this.ag.disegna(g);
        }
        this.imp.disegna(g);
        this.lamp.disegna(g);
        this.button.disegna(g);
        this.end.disegna(g);
        this.cat.disegna(g);
        this.door.disegna(g);
        if (this.presentDog) {
            this.dog.disegna(g);
            if (!this.dog.isSleeping() && this.viewDogZone) {
                g.drawRect((int) dog.getRectZone().getX(), (int)  dog.getRectZone().getY(), (int)  dog.getRectZone().getWidth(), (int)  dog.getRectZone().getHeight());
            }
        }
        if (this.lights) {
            g.drawImage(img, 0, GamePanel.BLOCK_HEIGHT * 3, GamePanel.WIDTH, GamePanel.HEIGHT - GamePanel.BLOCK_HEIGHT * 3, null);
        }
        if (this.gomitoloDraw) {
            gomitolo.disegna(g);
        }
        if (this.getLevel() == LVL12) {
             if (this.scossaPresent) {
                scossaCounter++;
                this.scossaSound++;
                if (this.scossaSound == 1) {
                    SoundManager.getListLoader().get(SoundPath.ELECTRICITYPATH.getPosition()).play();
                }
                for (final Scossa t : this.list5) {
                    if (t.isFisrstimage() && this.scossaSound % CHANGE_SCOSSA_IMAGE == 0) {
                        t.setFisrtImage(false);
                        t.setImage(t.getImg2());
                    } else if (!t.isFisrstimage() && this.scossaSound % CHANGE_SCOSSA_IMAGE == 0) {
                        t.setFisrtImage(true);
                        t.setImage(t.getImg1());
                    }
                    t.disegna(g);
                }
                if (scossaCounter == WAIT_SCOSSA) {
                     scossaPresent = false;
                     scossaCounter = 0;
                }
            } else {
                this.scossaCounter++;
                if (scossaCounter == WAIT_SCOSSA) {
                    scossaPresent = true;
                    scossaSound = 0;
                    scossaCounter = 0;
               }
            }
        }
        g.setColor(Color.RED);
        g.setFont(new Font("Helvetica", Font.BOLD, GamePanel.BLOCK_HEIGHT * 2));
        if (this.getManager().getStates().size() <= GameStateManagerImpl.LEVEL2) {
            g.drawString(this.getManager().getStates().get(GameStateManagerImpl.START).getHint().getMessage(), GamePanel.BLOCK_WIDTH * CINQUE, GamePanel.HEIGHT - 3 * GamePanel.BLOCK_HEIGHT);
        } else {
            g.drawString(this.getManager().getStates().get(this.getManager().getStates().size() - 1).getHint().getMessage(), GamePanel.BLOCK_WIDTH * CINQUE, GamePanel.HEIGHT - 3 * GamePanel.BLOCK_HEIGHT);
        }
        if (this.rockPresent) {
            for (final Rock r : this.list6) {
                r.changePosition();
                r.disegna(g);
            }
            this.counterAhah--;
            if (this.counterAhah == 0) {
                SoundManager.getListLoader().get(SoundPath.EVILLAUGHPATH.getPosition()).play();
                this.counterAhah = COUNTER;
            }
        }
    }

    /**
     * 
     * @return
     *         Impostazioni instance.
     */
    public Impostazioni getImp() {
        return this.imp;
    }

    /**
     * 
     * @return
     *         Lamp instance.
     */
    public Lamp getLamp() {
        return this.lamp;
    }

    /**
     * 
     * @return
     *         Lamp instance.
     */
    public Button getButton() {
        return this.button;
    }

    /**
     * 
     * @return
     *         End instance.
     */
    public End getEnd() {
        return this.end;
    }

    /**
     * 
     * @return
     *         Cat instance.
     */
    public Player getCat() {
        return this.cat;
    }

    /**
     * 
     * @return
     *         door instance.
     */
    public Door getDoor() {
        return this.door;
    }
    /**
     * 
     * @return
     *         list of mattoni instance.
     */
    public List<Mattoni> getMattoni() {
        return this.list1;
    }
    /**
     * 
     * @return
     *         list of rocks instance.
     */
    public List<Rock> getRock() {
        return this.list6;
    }
    /**
     * 
     * @return
     *         list of spine instance.
     */
    public List<Spine> getSpine() {
        return this.list2;
    }
    /**
     * 
     * @return
     *         list of spine alte instance.
     */
    public List<SpineAlte> getSpineAlte() {
        return this.list3;
    }
    /**
     * 
     * @return
     *         if trophy was found.
     */
    public boolean isTrophy1() {
        return trophy1;
    }
    /**
     * 
     * @param trophy1
     *         set if trophy is found
     */
    protected void setTrophy1(final boolean trophy1) {
        this.trophy1 = trophy1;
    }
    /**
     * 
     * @return
     *         if trophy was found.
     */
    protected boolean isTrophy2() {
        return trophy2;
    }
    /**
     * 
     * @param trophy2
     *         set if trophy is found
     */
    protected void setTrophy2(final boolean trophy2) {
        this.trophy2 = trophy2;
    }
    /**
     * 
     * @return
     *         if trophy was found.
     */
    protected boolean isTrophy3() {
        return trophy3;
    }
    /**
     * 
     * @param trophy3
     *         set if trophy is found
     */
    protected void setTrophy3(final boolean trophy3) {
        this.trophy3 = trophy3;
    }
    /**
     * 
     * @return
     *         if trophy was found.
     */
    protected boolean isTrophy4() {
        return trophy4;
    }
    /**
     * 
     * @param trophy4
     *         set if trophy is found
     */
    protected void setTrophy4(final boolean trophy4) {
        this.trophy4 = trophy4;
    }
    /**
     * 
     * @return
     *         if trophy was found.
     */
    protected boolean isTrophy5() {
        return trophy5;
    }
    /**
     * 
     * @param trophy5
     *         set if trophy is found
     */
    protected void setTrophy5(final boolean trophy5) {
        this.trophy5 = trophy5;
    }
    /**
     * 
     * @return
     *         if trophy was found.
     */
    protected boolean isTrophy6() {
        return trophy6;
    }
    /**
     * 
     * @param trophy6
     *         set if trophy is found
     */
    protected void setTrophy6(final boolean trophy6) {
        this.trophy6 = trophy6;
    }
    /**
     * 
     * @return
     *         if trophy was found.
     */
    protected boolean isTrophy7() {
        return trophy7;
    }
    /**
     * 
     * @param trophy7
     *         set if trophy is found
     */
    protected void setTrophy7(final boolean trophy7) {
        this.trophy7 = trophy7;
    }
    /**
     * 
     * @return
     *         if trophy was found.
     */
    protected boolean isTrophy8() {
        return trophy8;
    }
    /**
     * 
     * @param trophy8
     *         set if trophy is found
     */
    protected void setTrophy8(final boolean trophy8) {
        this.trophy8 = trophy8;
    }
    /**
     * 
     * @return
     *         if trophy was found.
     */
    protected boolean isTrophy9() {
        return trophy9;
    }
    /**
     * 
     * @param trophy9
     *         set if trophy is found
     */
    protected void setTrophy9(final boolean trophy9) {
        this.trophy9 = trophy9;
    }
    /**
     * 
     * @return
     *         if trophy was found.
     */
    protected boolean isTrophy10() {
        return trophy10;
    }
    /**
     * 
     * @param trophy10
     *         set if trophy is found
     */
    protected void setTrophy10(final boolean trophy10) {
        this.trophy10 = trophy10;
    }
    /**
     * 
     * @return
     *         if trophy was found.
     */
    protected boolean isTrophy11() {
        return trophy11;
    }
    /**
     * 
     * @param trophy11
     *         set if trophy is found
     */
    protected void setTrophy11(final boolean trophy11) {
        this.trophy11 = trophy11;
    }
    /**
     * 
     * @return
     *         if trophy was found.
     */
    protected boolean isTrophy12() {
        return trophy12;
    }
    /**
     * 
     * @param trophy12
     *         set if trophy is found
     */
    protected void setTrophy12(final boolean trophy12) {
        this.trophy12 = trophy12;
    }
    /**
     * 
     * @return
     *         if lights is on.
     */
    public boolean isLights() {
        return this.lights;
    }
    /**
     * 
     * @param lights
     *         set if lights is on
     */
    public void setLights(final boolean lights) {
        this.lights = lights;
    }
    /**
     * 
     * @return
     *         level.
     */
    public int getLevel() {
        return this.level;
    }
    /**
     * 
     * @param level
     *         set actual level
     */
    public void setLevel(final int level) {
        this.level = level;
    }
    /**
     * 
     * @return
     *          manager
     */
    public GameStateManagerImpl getManager() {
        return this.manager;
    }
    /**
     * 
     * @return
     *         dog.
     */
    public Dog getDog() {
        return this.dog;
    }
    /**
     * 
     * @return
     *        skipLevel
     */
    public boolean isSkipLevel() {
        return this.skipLevel;
    }
    /**
     * 
     * @param skipLevel
     *         set skipLevel
     */
    public void setSkipLevel(final boolean skipLevel) {
        this.skipLevel = skipLevel;
    }
    /**
     * 
     */
    public abstract void nextLevel();
    /**
     * 
     * @return
     *         if gomitolo is present
     */
    public boolean isGomitoloDraw() {
        return this.gomitoloDraw;
    }
    /**
     * 
     * @return
     *         if accalappiagatti is present
     */
    public boolean isAgPresent() {
        return this.agPresent;
    }
    /**
     * 
     * @return
     *         gomitolo
     */
    public GomitoloImpl getGomitolo() {
        return this.gomitolo;
    }
    /**
     * 
     * @return
     *         if scossa present
     */
    public boolean isScossaPresent() {
        return this.scossaPresent;
    }
    /**
     * 
     * @return
     *         list of scossa
     */
    public List<Scossa> getScossa() {
        return this.list5;
    }
    /**
     * 
     * @return
     *         if is door level
     */
    public boolean isDoorLevel() {
        return this.doorLevel;
    }
    /**
     * 
     * @param doorLevel
     *         set doorLevel
     */
    public void setDoorLevel(final boolean doorLevel) {
        this.doorLevel = doorLevel;
    }
    /**
     * 
     * @param scossaPresent
     *         scossa is present
     */
    public void setScossaPresent(final boolean scossaPresent) {
       this.scossaPresent = scossaPresent;
    }
    /**
     * 
     * @param b
     *         set if user want to see dog zone 
     */
    public void setViewDogZone(final boolean b) {
        this.viewDogZone = b;
    }
}
