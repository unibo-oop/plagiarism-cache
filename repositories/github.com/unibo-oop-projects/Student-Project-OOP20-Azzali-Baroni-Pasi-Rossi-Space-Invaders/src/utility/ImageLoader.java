package utility;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import model.ID;
import model.SpecialEffect.SpecialEffectT;
import model.powerup.PowerUpT;
import view.ArenaView;
import view.GameOverView;
import view.MenuP;

/**
 * The Class ImageLoader load all images of the game.
 */
public class ImageLoader {

    /** The Constant IMAGE. */
    private static final ImageLoader IMAGE = new ImageLoader();

    /** The Constant DIMENSION_SPRITE. */
    private static final int DIMENSION_SPRITE = 192;
    
    /** The entity images. */
    private final Map<ID, List<Image>> entityImages = new HashMap<>();

    /** The background images. */
    private final Map<String, List<Image>> backgroundImages = new HashMap<>();
    
    /** The bullet images. */
    private final Map<Pair<ID, ID>, List<Image>> bulletImages = new HashMap<>();
    
    /** The power up images. */
    private final Map<Pair<ID, PowerUpT>, List<Image>> powerUpImages = new HashMap<>();
    
    /** The Animations power up. */
    private final Map<Pair<ID, PowerUpT>, List<Image>> AnimationsPowerUp = new HashMap<>();
    
    /** The Animations effect. */
    private final Map<Pair<ID, SpecialEffectT>, List<Image>> AnimationsEffect = new HashMap<>();



    /**
     * Instantiates a new image loader.
     */
    private ImageLoader() {}

    /**
     * Find images in the folder.
     */
    public void findImages() {
        URL imgURL = ImageLoader.class.getResource("/BackgroundMenu.jpg");
        this.backgroundImages.put(MenuP.TITLE, loadImage(imgURL));

        imgURL = ImageLoader.class.getResource("/backgroundGame.png");
        this.backgroundImages.put(ArenaView.TITLE, loadImage(imgURL));
        
        imgURL = ImageLoader.class.getResource("/gameOver.jpg");
        this.backgroundImages.put(GameOverView.TITLE, loadImage(imgURL));
        
        imgURL = ImageLoader.class.getResource("/Player.png");
        this.entityImages.put(ID.PLAYER, loadImage(imgURL));
        
        imgURL = ImageLoader.class.getResource("/Enemy1.png");
        this.entityImages.put(ID.BASIC_ENEMY, loadImage(imgURL));
        
        imgURL = ImageLoader.class.getResource("/boss.png");
        this.entityImages.put(ID.BOSS_ENEMY, loadImage(imgURL));
        
        imgURL = ImageLoader.class.getResource("/meteorBrown_big1.png");
        this.entityImages.put(ID.METEOR, loadImage(imgURL));
        
        imgURL = ImageLoader.class.getResource("/BlueBullet.png");
        this.bulletImages.put(new Pair<>(ID.PLAYER_BULLET, ID.PLAYER), loadImage(imgURL));
        
        imgURL = ImageLoader.class.getResource("/redBullet.png");
        this.entityImages.put(ID.ENEMY_BULLET, loadImage(imgURL));
        
        imgURL = ImageLoader.class.getResource("/powerup_FireBoost.png");
        this.powerUpImages.put(new Pair<>(ID.POWER_UP, PowerUpT.FIRE_BOOST), loadImage(imgURL));
        
        imgURL = ImageLoader.class.getResource("/powerup_Freeze.png");
        this.powerUpImages.put(new Pair<>(ID.POWER_UP, PowerUpT.FREEZE), loadImage(imgURL)); 
        
        imgURL = ImageLoader.class.getResource("/powerup_Health.png");
        this.powerUpImages.put(new Pair<>(ID.POWER_UP, PowerUpT.HEALTH), loadImage(imgURL));
        
        imgURL = ImageLoader.class.getResource("/powerup_Shield.png");
        this.powerUpImages.put(new Pair<>(ID.POWER_UP, PowerUpT.SHIELD), loadImage(imgURL));
        
        imgURL = ImageLoader.class.getResource("/bulletSpeedUp.png");
        this.AnimationsPowerUp.put(new Pair<>(ID.POWER_UP, PowerUpT.FIRE_BOOST), loadEffect(imgURL));
        
        imgURL = ImageLoader.class.getResource("/Explosion.png");
        this.AnimationsEffect.put(new Pair<>(ID.EFFECT, SpecialEffectT.EXPLOSION), loadEffect(imgURL));

        imgURL = ImageLoader.class.getResource("/freeze.png");
        this.AnimationsPowerUp.put(new Pair<>(ID.POWER_UP, PowerUpT.FREEZE), loadImage(imgURL));
    }

    /**
     * Gets the image loader.
     *
     * @return the image loader
     */
    public static ImageLoader getImageLoader() {
        return ImageLoader.IMAGE;
    }
    
    /**
     * Load effect.
     *
     * @param url the url
     * @return the list
     */
    private List<Image> loadEffect(final URL url){
    	final List<Image> list = new ArrayList<>();
        final int width;
        final int height;
        final int rows;
        final int cols;
        if (url.toString().endsWith("Explosion.png")) {
            width = 100;
            height = 100;
            rows = 8;
            cols = 8;
        } else  {
            width = DIMENSION_SPRITE;
            height = DIMENSION_SPRITE;
            rows = 1;
            cols = 4;
        }
        try {
            final BufferedImage img = ImageIO.read(url);
            IntStream.rangeClosed(0, rows).forEach(i -> IntStream.rangeClosed(0, cols).forEach(j -> {
                list.add(img.getSubimage(j * width, i * height, width, height));
            }));
        } catch (final IOException e) {
            System.out.println("Error loading Effect");
        }
        return list;
    }
    
    /**
     * Load image.
     *
     * @param url the url
     * @return the list
     */
    private List<Image>loadImage(final URL url){
        return Arrays.asList(new ImageIcon(url).getImage());
    }
    
    /**
     * Gets the background images.
     *
     * @return the background images
     */
    public Map<String, List<Image>> getBackgroundImages(){
        return backgroundImages;
    }
    
    /**
     * Gets the entity images.
     *
     * @return the entity images
     */
    public Map<ID, List<Image>> getEntityImages() {
        return entityImages;
    }
    
    /**
     * Gets the bullet images.
     *
     * @return the bullet images
     */
    public Map<Pair<ID, ID>, List<Image>> getBulletImages() {
        return bulletImages;
    }
    
    /**
     * Gets the power up images.
     *
     * @return the power up images
     */
    public Map<Pair<ID, PowerUpT>, List<Image>> getPowerUpImages() {
        return powerUpImages;
    }
    
    /**
     * Gets the animations power up.
     *
     * @return the animations power up
     */
    public Map<Pair<ID, PowerUpT>, List<Image>> getAnimationsPowerUp() {
        return AnimationsPowerUp;
    }

    /**
     * Gets the animations effect.
     *
     * @return the animations effect
     */
    public Map<Pair<ID, SpecialEffectT>, List<Image>> getAnimationsEffect() {
        return AnimationsEffect;
    }
    
}