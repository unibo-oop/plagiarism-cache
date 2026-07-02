package hollowmen.view.ale;

import java.util.LinkedList;
import java.util.List;

/**
 * The {@code SingletonNameList} class uses the design pattern Singleton
 * in order to create just one instance of this Class. It's thread-safe.
 * 
 * @author Alessia
 *
 */
public class SingletonNameList {
	private static final SingletonNameList singleton=new SingletonNameList();
	private List<String> nameList;
	
	//Private constructor avoid the instance of objects from external classes.
	private SingletonNameList(){
		nameList=new LinkedList<>();
		nameList.add("bat1");
		nameList.add("bat2");
		nameList.add("bat3");
		nameList.add("batBoss");
		nameList.add("slimeBossSecret");
		nameList.add("puppet1");
		nameList.add("puppetBoss");
		nameList.add("puppet2");
		nameList.add("puppet3");
		nameList.add("slime2");
		nameList.add("slime1");
		nameList.add("slime3");
		nameList.add("slime4");
		nameList.add("slimeBoss");
		nameList.add("ability1");
		nameList.add("ability2");
		nameList.add("ability3");
		nameList.add("consumable");
		nameList.add("inventory");
		nameList.add("skillTree");
		nameList.add("door");
		nameList.add("treasure");
		nameList.add("game");
		nameList.add("warriorWalkSword");
		nameList.add("hero");
		nameList.add("blueSlime");
		nameList.add("coinBag");
		nameList.add("inventoryLobby");
		nameList.add("skillTreeLobby");
	}
	
	/**
	 * The method {@code SingletonNameList} returns the instance of this Singleton.
	 * 
	 * @return
	 */
	public static SingletonNameList getSingletonNameList(){
		return singleton;
	}
	/**
	 * The method {@code getNameList} gets the name of all the images contained 
	 * in the {@link SingletonNameList} 
	 * 
	 * @return
	 */
	public List<String> getNameList(){
		return this.nameList;
	}
}
