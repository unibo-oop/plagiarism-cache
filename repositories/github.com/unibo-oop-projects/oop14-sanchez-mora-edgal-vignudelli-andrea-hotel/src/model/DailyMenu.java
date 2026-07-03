/*
 * 
 */
package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.interfaces.IDailyMenu;

// TODO: Auto-generated Javadoc
/**
 * The Class DailyMenu.
 */
public class DailyMenu implements IDailyMenu,Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3073898469525778363L;
	
	/** The menu map. */
	private HashMap<String, Set<String>> menuMap;
	
	/** The all rooms. */
	private HashMap<Room, Boolean> allRooms;
	
	/** The sb. */
	private StringBuilder sb;

	/**
	 * Instantiates a new daily menu.
	 */
	public DailyMenu() {
		this.menuMap = new HashMap<>();
		Set<String> antipasti = new HashSet<>();
		Set<String> primi = new HashSet<>();
		Set<String> secondi = new HashSet<>();
		Set<String> dessert = new HashSet<>();
		this.menuMap.put("Antipasti", antipasti);
		this.menuMap.put("Primi", primi);
		this.menuMap.put("Secondi", secondi);
		this.menuMap.put("Dessert", dessert);
		this.allRooms = new HashMap<>();
		sb = new StringBuilder("[ORDINI]: ");
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IDailyMenu#addPiatto(java.lang.String, java.lang.String)
	 */
	public void addPiatto(final String key, final String value) {
		Set<String> tmp = new HashSet<String>();
		if (this.menuMap.get(key).size()>0) {
			tmp = this.menuMap.get(key);
		}
		if(!tmp.add(value)) {
			
		}
		this.menuMap.put(key, tmp);
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IDailyMenu#removePiatto(java.lang.String, java.lang.String)
	 */
	public void removePiatto(final String key, final String value) {
		if (this.menuMap.get(key).size()>0) {
			Set<String> tmp = this.menuMap.get(key);
			tmp.remove(value);
			this.menuMap.put(key, tmp);
		}
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IDailyMenu#cleanAllandUpdate()
	 */
	public void cleanAllandUpdate() {
		this.menuMap.clear();
		this.menuMap.put("Antipasti", new HashSet<>());
		this.menuMap.put("Primi", new HashSet<>());
		this.menuMap.put("Secondi", new HashSet<>());
		this.menuMap.put("Dessert", new HashSet<>());
		sb = new StringBuilder("[ORDINI]: ");
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IDailyMenu#addRoomMeal(model.Room, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	public void addRoomMeal(final Room room, final  List<Pair<String, Integer>> starter, final List<Pair<String, Integer>> first,
			List<Pair<String, Integer>> second, List<Pair<String, Integer>> dessert) {
		room.getActualCustomer().getBooking().eat();
		sb.append("[STANZA " + room.getNumber() + "]\n" + "[ANTIPASTI]: \n");
		for (Pair<String, Integer> p : starter) {
			sb.append(p.getValue() + " " + p.getKey() + "\n");
		}
		sb.append("[PRIMI]: \n");
		for (Pair<String, Integer> p : first) {
			sb.append(p.getValue() + " " + p.getKey() + "\n");
		}
		sb.append("[SECONDI]: \n");
		for (Pair<String, Integer> p : second) {
			sb.append(p.getValue() + " " + p.getKey() + "\n");
		}
		sb.append("[DOLCI]: \n");
		for (Pair<String, Integer> p : dessert) {
			sb.append(p.getValue() + " " + p.getKey() + "\n");
		}
		sb.append("\n\n");
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IDailyMenu#getOrdersString()
	 */
	public String getOrdersString() {
		return new String(sb);
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IDailyMenu#getMenuString()
	 */
	public String getMenuString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ANTIPASTI]: ");
		for (String s : menuMap.get("Antipasti")) {
			sb.append(s + "\n");
		}
		sb.append("\n[PRIMI]: ");
		for (String s : menuMap.get("Primi")) {
			sb.append(s + "\n");
		}
		sb.append("\n[SECONDI]: ");
		for (String s : menuMap.get("Secondi")) {
			sb.append(s + "\n");
		}
		sb.append("\n[DESSERTS]: ");
		for (String s : menuMap.get("Dessert")) {
			sb.append(s + "\n");
		}
		return new String(sb);
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IDailyMenu#computeRoomMealAvaliable(java.util.List)
	 */
	public void computeRoomMealAvaliable(final List<Room> allRooms) {
		for (Room r : allRooms) {
			if (!r.isBusy()) {
				this.allRooms.put(r, false);
			} else {
				this.allRooms.put(r, r.getActualCustomer().getBooking().canEat());
			}
		}
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IDailyMenu#getMealList(java.lang.String)
	 */
	public Set<String> getMealList(final String type) {
		if (this.menuMap.get(type).size()>0)
			return new HashSet<String>(this.menuMap.get(type));
		else
			return null;
	}

}