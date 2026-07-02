package javarogue.playablecharacter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javarogue.behaviourmodules.StatNames;
import javarogue.objects.GameObject;
import javarogue.utility.Position;

/**
 *	Class for the playable character. It is made using the Component pattern. Model class in MVC
 */
public class PlayableCharacter {

	private Position position;
	private final CharacterLevel characterLevel; //livello del personaggio
	private final List<BodyPart> bodyParts;
	private Optional<BodyPart> selectedBodyPart;
	private final Skill skill;
	private final Inventory inventory;
	private final List<GameObject> inventoryList;
	private final Map<StatNames, Integer> statistics;
	private final Condition condition;
	private final Hunger hunger;
	private Optional<GameObject> equippedObject;
	
	// -- level --
	private final javarogue.level.Level level;
	
	public PlayableCharacter(javarogue.level.Level level) {
		this.level = level;
		this.characterLevel = new CharacterLevelImpl();
		this.bodyParts = new ArrayList<>();
		addBodyParts();
		this.selectedBodyPart = Optional.empty();
		this.skill = Skill.NONE;
		this.inventory = new InventoryImpl();
		this.equippedObject = Optional.empty();
		this.statistics = new HashMap<StatNames, Integer>();
		calculateStats();
		this.condition = Condition.HEALTHY;
		this.hunger = new HungerImpl();
		this.characterLevel.addCharacter(this);
		this.inventory.addCharacter(this);
		this.inventoryList = new ArrayList<GameObject>();
	}
	/**
	 * private method that calculates the total stats for the character
	 */
	private void calculateStats() {
		for (final BodyPart part : this.bodyParts) {
			final Map<StatNames, Integer> partStats = part.getStatistics();
			partStats.forEach((key, value) -> this.statistics.merge(key, value, Integer::sum));
		}
		this.raiseWithEquippedObject();
	}
	
	/**
	 * method that raises character's statistics.
	 */
	public void raiseStats() {
		this.statistics.clear();
		this.calculateStats();
	}
	/**
	 * the list of objects in the inventory.
	 * @return the list of objects in the inventory.
	 */
	public List<GameObject> getInventoryList(){
		return Collections.unmodifiableList(this.inventoryList);
	}
	/**
	 * Equips an object.
	 * @param toEquip the object to be equipped.
	 */
	public void equipObject(GameObject toEquip) {
		this.equippedObject = Optional.of(toEquip);
		this.inventoryList.remove(this.inventoryList.indexOf(toEquip));
		this.raiseWithEquippedObject();
	}
	/**
	 * raises statistics if equipped object is present
	 */
	private void raiseWithEquippedObject() { 
		if (equippedObject.isPresent()) {
			final Map<StatNames, Integer> increments = equippedObject.get().GetStatsIncrements();
			increments.forEach((key, value) -> this.statistics.merge(key, value, Integer::sum));
		}
	}
	/**
	 * the equipped object.
	 * @return the object.
	 */
	public Optional<GameObject> getEquippedObject(){
		return this.equippedObject;
	}
	/**
	 * private method that adds the body parts to the character
	 */
	private void addBodyParts() {
		final Map<StatNames, Integer> baseStats = new HashMap<StatNames, Integer>();		
		baseStats.put(StatNames.HP, 20);
		baseStats.put(StatNames.ATTACK, 5);
		baseStats.put(StatNames.DEFENSE, 5);
		this.bodyParts.add(new BodyPartImpl("HEAD", new HashMap<StatNames, Integer>(baseStats)));
		this.bodyParts.add(new BodyPartImpl("BODY", new HashMap<StatNames, Integer>(baseStats)));
		this.bodyParts.add(new BodyPartImpl("LEGS", new HashMap<StatNames, Integer>(baseStats)));
		this.bodyParts.add(new BodyPartImpl("HANDS", new HashMap<StatNames, Integer>(baseStats)));
		this.bodyParts.add(new BodyPartImpl("FEET", new HashMap<StatNames, Integer>(baseStats)));
	}
	/**
	 * The position
	 * @return the position the character is in
	 */
	public Position getPosition() {
		return this.position;
	}
	/**
	 * The level
	 * @return the level of the character
	 */
	public CharacterLevel getLevel() {
		return this.characterLevel;
	}
	/**
	 * The list of body parts
	 * @return the list of body parts
	 */
	public List<BodyPart> getBodyParts() {
		return Collections.unmodifiableList(this.bodyParts);
	}
	/**
	 * Selects a bodyParts
	 * @param toSelect the part to be selected
	 */
	public void selectBodyPart(BodyPart toSelect) {
		for (final BodyPart bp : this.bodyParts) {
			if (bp.equals(toSelect)) {
				this.selectedBodyPart = Optional.of(toSelect);
			}
		}
	}
	/**
	 * The selected body part
	 * @return the selected body part
	 */
	public Optional<BodyPart> getSelectedBodyPart(){
		return this.selectedBodyPart;
	}
	/**
	 * The skill
	 * @return the skill of the character
	 */
	public Skill getSkill() {
		return this.skill;
	}
	/**
	 * The statistics.
	 * @return the overall statistics of the character
	 */
	public Map<StatNames, Integer> getStatistics(){
		return Collections.unmodifiableMap(this.statistics);
	}
	/**
	 * The condition
	 * @return the condition which affects the character
	 */
	public Condition getCondition() {
		return this.condition;
	}
	/**
	 * The hunger
	 * @return the hunger level of the character
	 */
	public Hunger getHunger() {
		return this.hunger;
	}
	/**
	 * The character
	 * @return the character
	 */
	public PlayableCharacter getCharacter() {
		return this;
	}
	/**
	* Sets a position as the current
	*/
	public void setPosition(Position position) {
		this.position = position;
	}
	/**
	 * adds an object to inventory. Triggered by inventory.
	 * @param looted the object to be added.
	 */
	public void addObjectToInventory(GameObject looted) {
		this.inventoryList.add(looted);
	}
	
}
