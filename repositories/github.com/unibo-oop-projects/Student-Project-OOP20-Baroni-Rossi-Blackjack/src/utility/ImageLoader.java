package utility;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import model.CardImpl;
import model.Suit;
import model.Values;
/**
 * this method reload the image
 *
 */
public class ImageLoader {
	
	private static final ImageLoader IMAGE = new ImageLoader();
	private static final int NCard = 15;
	private static final int NCHIPS = 4;
	/*The front image*/
	private ImageIcon backCard = new ImageIcon();
	/*The menu image*/
	private ImageIcon menu = new ImageIcon();
	/*The chips images*/
	private ImageIcon chip[] = new ImageIcon[NCHIPS];
	/*The Spades images*/
	private final Map<CardImpl,ImageIcon> spades = new HashMap<>();
	/*The diamonds images*/
	private final Map<CardImpl,ImageIcon> diamonds = new HashMap<>();
	/*The clubs images*/
	private final Map<CardImpl,ImageIcon> clubs = new HashMap<>();
	/*The hearts images*/
	private final Map<CardImpl,ImageIcon> hearts = new HashMap<>();
	/**
	 * put the images in each map or ImageIcon
	 */
	public void findImages() {
		URL imgURL = ImageLoader.class.getResource("/back.png");
		this.backCard = loadImage(imgURL);
		
		imgURL = ImageLoader.class.getResource("/menuImage.png");
		this.menu = loadImage(imgURL);
		
		for(int i = 0; i< NCHIPS;i++) {
			imgURL = ImageLoader.class.getResource("/"+i+"_chip.png");
			this.chip[i] = loadImage(imgURL);
		}
		
		for(int i = 1; i<NCard;i++) {
			imgURL = ImageLoader.class.getResource("/"+i+"_spades.png");
			this.spades.put(new CardImpl(Suit.spades,Values.getValue(i)),loadImage(imgURL));
		}
		for(int i = 1; i<NCard;i++) {
			imgURL = ImageLoader.class.getResource("/"+i+"_diamonds.png");
			this.diamonds.put(new CardImpl(Suit.diamods,Values.getValue(i)),loadImage(imgURL));
		}
		for(int i = 1; i<NCard;i++) {
			imgURL = ImageLoader.class.getResource("/"+i+"_plub.png");
			this.clubs.put(new CardImpl(Suit.clubs,Values.getValue(i)),loadImage(imgURL));
		}
		for(int i = 1; i<NCard;i++) {
			imgURL = ImageLoader.class.getResource("/"+i+"_heart.png");
			this.hearts.put(new CardImpl(Suit.heart,Values.getValue(i)),loadImage(imgURL));
		}
	}
	/**
	 * 
	 * @param url
	 * @return
	 */
	private ImageIcon loadImage(final URL url){
        return new ImageIcon(url);
    }
	/**
	 * 
	 * @return Map of Spades
	 */
	public Map<CardImpl,ImageIcon> getSpades(){
		return this.spades;
	}
	/**
	 * 
	 * @return imageIcon of menu
	 */
	public ImageIcon getMenuImage() {
		return this.menu;
	}
	/**
	 * 
	 * @return ImageIcons of chips
	 */
	public ImageIcon[] getChipImage() {
		return this.chip;
	}
	/**
	 * 
	 * @return Map of heart
	 */
	public Map<CardImpl,ImageIcon> getHeart(){
		return this.hearts;
	}
	/**
	 * 
	 * @return map of clubs
	 */
	public Map<CardImpl,ImageIcon> getClubs(){
		return this.clubs;
	}
	/**
	 * 
	 * @return map of diamonds
	 */
	public Map<CardImpl,ImageIcon> getDiamonds(){
		return this.diamonds;
	}
	/**
	 * 
	 * @return ImageIcon of back Card
	 */
	public ImageIcon getBackCard() {
		return this.backCard;
	}
	/**
	 * 
	 * @return imageLoader
	 */
	public static ImageLoader getImageLoader() {
        return ImageLoader.IMAGE;
    }
}
