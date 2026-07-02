package model.updateLevel.loadEntity;

import java.awt.image.BufferedImage;
import java.util.Objects;

import controllers.texture.GetTexture;
import model.Door;
import model.ID;
import model.Lava;
import model.Portal;
import model.Wall;
import model.enemy.AdvanceEnemy;
import model.enemy.AvarageEnemy;
import model.enemy.BaseEnemy;
import model.enemy.DragonEnemy;
import model.enemy.SupportEnemy;
import model.enemy.TowerEnemy;
import model.getPowerUPDebuff.GetPowerUPDebuff;
import model.handler.Handler;
import model.player.Player;
import other.Pair;

public class LoadEntities implements LoadEntitiesInterface {

    private final Handler handler;
    private final GetTexture getTexture;
    private final BufferedImage levelImage;
    private final int level;
    private final int dimensionPixel;
    private final GetPowerUPDebuff powerUPDebuff;

    /**
     * Constructor for LoadEntities.
     * 
     * @param handler
     * @param level
     */
    public LoadEntities(final Handler handler, final int level) {
        this.level = Objects.requireNonNull(level);
        this.getTexture = new GetTexture();
        this.dimensionPixel = 64;
        this.handler = Objects.requireNonNull(handler);
        this.levelImage = getTexture.getLevel(this.level);
        this.powerUPDebuff = new GetPowerUPDebuff(getTexture);
    }

    @Override
    public void createEntities() {

        for (int x = 0; x < levelImage.getWidth(); x++) {
            for (int y = 0; y < levelImage.getHeight(); y++) {

                final int rgb = levelImage.getRGB(x, y);
                final String hex = "#" + Integer.toHexString(rgb).substring(2);

                // Spiegazione: Le nostre texture sono generalmente di dimensione 64*64, quando
                // andiamo ad inizializzare una nuova entità (ad esempio il Player)
                // gli passiamo le cordinate x ed y ottenute dal ciclo for annidato che cicla
                // pixel per pixel lo schema del livello. Quando andiamo a fare il rendering di
                // queste texture (ad esempio la texture del Player)
                // richiamiamo la funzione drawImage() che prende come argomenti le coordinate
                // x,y DELL'APICE ALL'ESTREMO SUPERIORE SINISTRO da cui poi caricare l'intera
                // immagine della texture.
                // Il problema si pone perchè facendo così avremo che, dato che ogni entità
                // nello schema del livello è distanziata di un pixel, le relative immagini
                // saranno distanziate soltanto da un pixel andando a sovrapporsi tra loro
                // (essendo 64*64)
                // La soluzione consiste nel moltiplicare *64 (la dimensione della nostra
                // texture) le coordinate che passeremo al momento dell'inizializzazione della
                // nuova entità. In tal modo è come se andassimo a considerare ogni pixel di
                // dimensione 64*64 senza pertanto provocare il fenomeno della sovrapposizione
                // di texture

                // ESEMPIO CASO ERRATO: Supponiamo di essere nella prima iterazione del ciclo
                // annidato, quindi X=0 e Y=0, mettiamo di entrare nella clausola if che andrà a
                // creare l'entità Player che di conseguenza avrà coordinate (0,0)
                // La sua texture sarà renderizzata dall'apice superiore sinistro (0,0) fino
                // all'apice inferiore destro (64,64)
                // Supponiamo di andare a renderizzare un'altra entità ricavata alle coordinate
                // (0,1), seguendo il medesimo ragionamento, avremo che la texture avrà come
                // apice superiore sinistro (0,1), ma già ora possiamo affermare che vi è una
                // sovrapposizione con la precedente texture del Player

                // ESEMPIO CASO CORRETTO: Andiamo a moltiplicare le coordinate che passiamo per
                // la creazione delle entità *64, il primo passaggio del caso errato (ossia
                // corrispondenza trovata all'apice (0,0)) non subisce mutamenti, andando ad
                // occupare lo spazio da (0,0) a (64,64) con la relativa texture
                // Supponiamo ora di trovare una corrispondenza per la creazione di un'entità a
                // coordinate (0,1), in questo caso perciò le coordinate passate all'entità
                // saranno (0,64), che ricordiamo essere le coordinate dell'apice superiore
                // sinistro da cui partirà la texture. Al momento del rendering avremo che la
                // texture andrà ad occupare le coordinate da (0,64) fino a (64,128) senza
                // quindi nessuno problema di sovrapposizione con la texture precedente

                // PLAYER -> BLU
                if ("#0000ff".equals(hex)) {
                    this.handler.addGameObject(new Player(ID.PLAYER, this.handler, x * dimensionPixel,
                            y * dimensionPixel, 0, 0, this.getTexture.getPlayerListTexture()));
                } else if ("#4cff00".equals(hex)) { // NEMICO DI SUPPORTO -> VERDE MOLTO CHIARO
                    this.handler.addGameObject(new SupportEnemy(ID.ENEMY, this.handler, x * dimensionPixel,
                            y * dimensionPixel, 0, 0, this.getTexture.getSupportEnemyListTexture()));
                } else if ("#00ff00".equals(hex)) { // NEMICO BASE -> VERDE CHIARO
                    this.handler.addGameObject(new BaseEnemy(ID.ENEMY, this.handler, x * dimensionPixel,
                            y * dimensionPixel, 0, 0, this.getTexture.getBaseEnemyListTexture(),
                            this.getTexture.getBaseEnemyRayListTexture()));
                } else if ("#007400".equals(hex)) { // NEMICO MEDIO -> VERDE MEDIO
                    this.handler.addGameObject(new AvarageEnemy(ID.ENEMY, this.handler, x * dimensionPixel,
                            y * dimensionPixel, 0, 0, this.getTexture.getAvarageEnemyListTexture(),
                            this.getTexture.getComplexRayImage()));
                } else if ("#004e00".equals(hex)) { // NEMICO AVANZATO -> VERDE SCURO
                    this.handler.addGameObject(new AdvanceEnemy(ID.ENEMY, this.handler, x * dimensionPixel,
                            y * dimensionPixel, 0, 0, this.getTexture.getAdvanceEnemyListTexture(),
                            this.getTexture.getComplexRayImage()));
                } else if ("#002d04".equals(hex)) { // NEMICO DRAGO -> VERDE MOLTO SCURO
                    this.handler.addGameObject(new DragonEnemy(ID.ENEMY, x * dimensionPixel, y * dimensionPixel, 0, 0,
                            this.getTexture.getDragonEnemyListTexture(),
                            this.getTexture.getDragonEnemyRayListTexture()));
                } else if ("#001c08".equals(hex)) { // NEMICO TORRE -> VERDE MOLTO MOLTO SCURO
                    this.handler.addGameObject(new TowerEnemy(ID.ENEMY, x * dimensionPixel, y * dimensionPixel, 0, 0,
                            this.getTexture.getTowerEnemyImage(), this.getTexture.getTowerEnemyRayImage()));
                } else if ("#00ffff".equals(hex)) { // POWERUP -> CELESTE
                    this.handler.addGameObject(
                            this.powerUPDebuff.getRandomPowerUP(new Pair<Integer, Integer>(x * 64, y * 64)));
                } else if ("#ff00ff".equals(hex)) { // DEBUFF -> MAGENTA
                    this.handler.addGameObject(
                            this.powerUPDebuff.getRandomDebuff(new Pair<Integer, Integer>(x * 64, y * 64)));
                } else if ("#ffd800".equals(hex)) { // CHEST -> GIALLO
                    this.handler.addGameObject(new Wall(ID.WALL, x * 64, y * 64, this.getTexture.getChest(level)));
                } else if ("#7f3300".equals(hex)) { // PORTA FINALE -> MARRONE
                    this.handler.addGameObject(new Door(ID.DOOR, x * dimensionPixel, y * dimensionPixel, 0, 0,
                            this.getTexture.getFinalDoorImage()));
                } else if ("#ff0000".equals(hex)) { // MURI -> ROSSO
                    this.handler.addGameObject(new Wall(ID.WALL, x * dimensionPixel, y * dimensionPixel,
                            this.getTexture.getWall(this.level)));
                } else if ("#ffffff".equals(hex)) { // PORTA INIZIALE -> BIANCO
                    this.handler.addGameObject(new Wall(ID.WALL, x * dimensionPixel, y * dimensionPixel,
                            this.getTexture.getInitialDoorImage()));
                } else if (hex.contains("#8080")) { // PORTALE -> Grigio
                    final Portal p = new Portal(ID.PORTAL, hex, x * dimensionPixel, y * dimensionPixel, 0, 0,
                            this.getTexture.getPortalImage(), handler);
                    this.handler.addGameObject(p);
                } else if ("#7f0000".equals(hex)) { // MURO SCURO -> Rosso Scuro
                    this.handler.addGameObject(new Wall(ID.WALL, x * dimensionPixel, y * dimensionPixel,
                            this.getTexture.getDarkWallImage()));
                } else if ("#4800ff".equals(hex)) { // LAVA -> Viola
                    this.handler.addGameObject(new Lava(ID.LAVA, x * dimensionPixel, y * dimensionPixel, 0, 0,
                            this.getTexture.getLavaImage()));
                }
            }
        }
    }
}
