package hollowmen.controller;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * {@code LoaderClass} is a Singleton used to load images
 * 
 * @author Giordo
 */
public class LoaderClass {
    static final ClassLoader loader = LoaderClass.class.getClassLoader();
	private LoaderClass(){}
	
	private static List<String> nameList(){
		LinkedList<String> nameList=new LinkedList<>();
		/*
		 * Aggiungere percorso per tutte le immagini. Se rimane tempo va cambiato
		 */
		nameList.add("images/titles/title.png");
		nameList.add("images/titles/skilltree.png");
		nameList.add("images/titles/shop.png");
		nameList.add("images/titles/pauseMenu.png");
		nameList.add("images/titles/inventoryT.png");
		nameList.add("images/titles/helpSheet2.png");
		nameList.add("images/titles/helpSheet1.png");
		nameList.add("images/titles/help.png");
		nameList.add("images/titles/difficulty.png");
		nameList.add("images/titles/credits.png");
		nameList.add("images/titles/class.png");
		nameList.add("images/titles/bestiary.png");
		nameList.add("images/titles/achievements.png");
		nameList.add("images/room/treasure.png");
		nameList.add("images/room/game.png");
		nameList.add("images/room/door.png");
		nameList.add("images/mobs/slimeBoss.gif");
		nameList.add("images/mobs/slime4.gif");
		nameList.add("images/mobs/slime3.gif");
		nameList.add("images/mobs/slime1.gif");
		nameList.add("images/mobs/slime2.gif");
		nameList.add("images/mobs/puppet3.gif");
		nameList.add("images/mobs/puppet2.gif");
		nameList.add("images/mobs/puppet1.gif");
		nameList.add("images/mobs/slimeBossSecret.gif");
		nameList.add("images/mobs/slimeBoss.gif");
		nameList.add("images/mobs/bat3.gif");
		nameList.add("images/mobs/bat2.gif");
		nameList.add("images/mobs/bat1.gif");
		nameList.add("images/items/bootsBlack.png");
		nameList.add("images/items/bootsBlue.png");
		nameList.add("images/items/bootsBrown.png");
		nameList.add("images/items/bootsGreen.png");
		nameList.add("images/items/bootsPurple.png");
		nameList.add("images/items/chestBronze.png");
		nameList.add("images/items/chestGold.png");
		nameList.add("images/items/chestSilver.png");
		nameList.add("images/items/excalibur.png");
		nameList.add("images/items/glovesBlack.png");
		nameList.add("images/items/glovesBlue.png");
		nameList.add("images/items/glovesGrey.png");
		nameList.add("images/items/glovesRed.png");
		nameList.add("images/items/goldSword.png");
		nameList.add("images/items/headBronze.png");
		nameList.add("images/items/headGold.png");
		nameList.add("images/items/headSilver.png");
		nameList.add("images/items/longSword.png");
		nameList.add("images/items/potionBlue.png");
		nameList.add("images/items/potionGreen.png");
		nameList.add("images/items/potionRed.png");
		nameList.add("images/items/redSword.png");
		nameList.add("images/items/scimitar.png");
		nameList.add("images/items/simpleSword.png");
		nameList.add("images/items/spell1.png");
		nameList.add("images/items/spell2.png");
		nameList.add("images/items/spell3.png");
		nameList.add("images/items/star.jpg");
		nameList.add("images/items/woodSword.png");
		nameList.add("images/gameButtons/skillTree.png");
		nameList.add("images/gameButtons/inventory.png");
		nameList.add("images/gameButtons/consumable.png");
		nameList.add("images/gameButtons/ability3.png");
		nameList.add("images/gameButtons/ability2.png");
		nameList.add("images/gameButtons/ability1.png");
		nameList.add("images/character/assassin.png");
		nameList.add("images/character/hero.png");
        nameList.add("images/character/warriorWalkSword.gif");	
		nameList.add("images/character/mage.png");
		nameList.add("images/buttons/RArrowOver.png");
		nameList.add("images/buttons/RArrowNA.png");
		nameList.add("images/buttons/RArrow.png");
		nameList.add("images/buttons/pButtonOver.png");
		nameList.add("images/buttons/pButtonNA.png");
		nameList.add("images/buttons/pButton.png");
		nameList.add("images/buttons/nodeUnlocked.png");
		nameList.add("images/buttons/nodeOver.png");
		nameList.add("images/buttons/nodeNA.png");
		nameList.add("images/buttons/nodeAvailable.png");
		nameList.add("images/buttons/LArrowOver.png");
		nameList.add("images/buttons/LArrowNA.png");
		nameList.add("images/buttons/LArrow.png");
		nameList.add("images/backgrounds/bodyTemplate.png");
		nameList.add("images/backgrounds/castle.jpg");
		nameList.add("images/backgrounds/castleBG.jpg");
		nameList.add("images/backgrounds/pergamena.jpg");
		nameList.add("images/dialog.jpg");
		nameList.add("images/gameButtons/blueSlime.gif");
		nameList.add("images/gameButtons/coinBag.png");
		nameList.add("images/gameButtons/inventoryLobby.png");
		nameList.add("images/gameButtons/skillTreeLobby.png");
		nameList.add("images/mobs/batBoss.gif");
		nameList.add("images/mobs/puppetBoss.gif");
		return nameList;
	}
	
	/**
	 * method used to load images
	 * @return {@code List<Pair<String, byte[]>>} the list of pair of
	 * name and image (as {@code byte[]})
	 */
	public static Map<String,byte[]> load(){
		Map<String,byte[]> imageMap=new HashMap<>();
		BufferedInputStream bf;
		List<String> nameList=nameList();
		List<Byte> list2;
		String name;
		String[] tmp;
		Byte[] a;
		byte[] b;
		try{
			for(String elem:nameList){
				bf=new BufferedInputStream(loader.getResourceAsStream(elem));
				list2 = new LinkedList<>();
				int c;
				while((c=bf.read())!=-1){
					list2.add((byte)c);
				}
				a=list2.toArray(new Byte[0]);
				b=new byte[a.length];
				for(int i=0;i<a.length;i++){
					b[i]=a[i].byteValue();
				}
				tmp=elem.split("/");
				name=tmp[tmp.length-1];
				name=name.substring(0, name.length()-4);
				imageMap.put(name, b);
			}
		}catch(Exception e){
			System.out.println("Impossibile caricare alcuni o tutti i file.");
			System.exit(0);
		}
		return imageMap;
	}
}
