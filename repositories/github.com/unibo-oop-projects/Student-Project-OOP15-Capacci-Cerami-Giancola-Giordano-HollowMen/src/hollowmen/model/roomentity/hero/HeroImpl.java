package hollowmen.model.roomentity.hero;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.FixtureDef;

import hollowmen.model.Actor;
import hollowmen.model.Hero;
import hollowmen.model.HeroClass;
import hollowmen.model.Inventory;
import hollowmen.model.Item;
import hollowmen.model.Item.ItemState;
import hollowmen.model.LimitedCounter;
import hollowmen.model.Lootable;
import hollowmen.model.Parameter;
import hollowmen.model.RoomEntity;
import hollowmen.model.TargetPointSystem;
import hollowmen.model.dungeon.DungeonSingleton;
import hollowmen.model.dungeon.FilterType;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.item.ItemPool;
import hollowmen.model.roomentity.ActorAbs;
import hollowmen.model.utils.Actors;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.model.utils.Constants;
import hollowmen.model.utils.SimpleLimitedCounter;
import hollowmen.model.utils.UpperLimitReachException;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

/**
 * This class extends {@link ActorAbs} and implements {@link Hero}
 * @author pigio
 *
 */
public class HeroImpl extends ActorAbs implements Hero{

	private final static Pair<Float, Float> BODY_PROP = new Pair<>(0.4f, 0.45f);
	private final static float HEAD_PROP = 0.75f;
	
	private LimitedCounter exp;
	
	private int level;
	
	private int gold;
	
	private Inventory inventory = new InventoryImpl();
		
	private HeroClass heroClass;
	
	private TargetPointSystem<Parameter> uppableParam;
	
	private Map<String, Optional<Item>> slots = new HashMap<>();
	
	public HeroImpl(int level, int gold, Pair<Integer, Integer> exp, String description, 
			HeroClass heroClass, Inventory inventory, Collection<Item> equippedItems) {
		super(new InfoImpl(RoomEntityName.HERO.toString(), description), 
				Constants.HERO_SIZE, heroClass.getBaseParam());
		this.initSlot();
		this.initMoreAction();
		this.inventory = inventory;
		this.uppableParam = new StatPointSystem(upgradableParam(heroClass));
		this.level = level;
		this.gold = gold;
		this.exp = new SimpleLimitedCounter(exp.getX(), exp.getY());
		this.heroClass = heroClass;
		equippedItems.stream().forEach(x -> {
			Actors.addAllModifier(this, x.getModifiers().entries().stream()
					.map(i -> i.getValue())
					.collect(Collectors.toList()));
			x.setState(ItemState.EQUIPPED);
			this.slots.put(x.getSlot(), Optional.of(x));
		});
	}
	
	private void initSlot() {
		for(Item.SlotName s : Item.SlotName.values()) {
			this.slots.put(s.toString(), Optional.empty());
		}
	}
	
	
	private Collection<Parameter> upgradableParam(HeroClass heroClass) {
		Collection<String> list = new LinkedList<>();
		list.add(Parameter.ParamName.HPMAX.toString());
		list.add(Parameter.ParamName.ATTACK.toString());
		list.add(Parameter.ParamName.DEFENSE.toString());
		return heroClass.getBaseParam().stream()
				.filter(x -> list.contains(x.getInfo().getName()))
				.collect(Collectors.toList());
	}
	
	/**
	 * {@inheritDoc Hero}
	 */
	@Override
	public void equipItem(Item item) throws IllegalStateException, IllegalArgumentException, NullPointerException {
		ExceptionThrower.checkNullPointer(item);
		ExceptionThrower.checkIllegalState(item, i -> !i.getState().equals(ItemState.UNEQUIPPED));
		ExceptionThrower.checkIllegalArgument(item, i -> !i.getHeroClassEquippable().equals(this.getHeroClass().getInfo().getName()));
		ExceptionThrower.checkIllegalArgument(item, i -> !this.inventory.getAllItem().stream()
				.map(p -> p.getX()).collect(Collectors.toList()).contains(i));
		Item itemFrom = this.inventory.getItem(item.getInfo().getName());
		this.inventory.removeItem(itemFrom);
		itemFrom.setState(ItemState.EQUIPPED);
		this.slots.get(itemFrom.getSlot()).ifPresent(x -> this.unequipItem(x));
		this.slots.put(itemFrom.getSlot(), Optional.of(itemFrom));
		itemFrom.getModifiers().entries().forEach(e -> Actors.addModifier(this, e.getValue()));
	}

	/**
	 * {@inheritDoc Hero}
	 */
	@Override
	public void unequipItem(Item item) throws IllegalStateException, NullPointerException {
		ExceptionThrower.checkNullPointer(item);
		ExceptionThrower.checkIllegalState(item, i -> !this.slots.get(i.getSlot()).isPresent());
		Item unequipItem = this.slots.get(item.getSlot()).get();
		ExceptionThrower.checkIllegalState(unequipItem, x -> !x.getState().equals(ItemState.EQUIPPED));
		unequipItem.getModifiers().entries().stream().forEach(e -> Actors.removeModifier(this, e.getValue()));
		this.inventory.addItem(unequipItem);
		this.slots.put(item.getSlot(), Optional.empty());
	}

	
	/**
	 * {@inheritDoc Hero}
	 */
	@Override
	public void sellItem(Item item) throws IllegalStateException, IllegalArgumentException, NullPointerException {
		ExceptionThrower.checkNullPointer(item);
		ExceptionThrower.checkIllegalState(item, i -> i.getState().equals(ItemState.BUYABLE));
		if(item.getState().equals(ItemState.EQUIPPED)) {
			this.unequipItem(item);
		}
		this.inventory.removeItem(item);
		this.gold += item.getGoldValue();
	}

	/**
	 * {@inheritDoc Hero}
	 */
	@Override
	public void buyItem(Item item) throws IllegalStateException, NullPointerException {
		ExceptionThrower.checkNullPointer(item);
		ExceptionThrower.checkIllegalState(item, i -> (i.getGoldValue() - this.getGold()) > 0);
		this.inventory.addItem(ItemPool.getInstance().getItem(item.getInfo().getName()));
		this.gold -= item.getGoldValue();
	}

	/**
	 * {@inheritDoc Hero}
	 */
	@Override
	public Pair<Integer, Integer> getExp() {
		return new Pair<Integer, Integer>((int)this.exp.getValue(), (int)this.exp.getLimit());
	}

	/**
	 * {@inheritDoc Hero}
	 */
	@Override
	public int getLevel() {
		return this.level;
	}

	/**
	 * {@inheritDoc Hero}
	 */
	@Override
	public int getGold() {
		return this.gold;
	}

	/**
	 * {@inheritDoc Hero}
	 */
	@Override
	public Inventory getInventory() {
		return this.inventory;
	}

	/**
	 * {@inheritDoc Hero}
	 */
	@Override
	public HeroClass getHeroClass() {
		return this.heroClass;
	}

	/**
	 * {@inheritDoc Hero}
	 */
	@Override
	public void pick(Lootable loot) throws NullPointerException {
		ExceptionThrower.checkNullPointer(loot);
		
		try {
			this.exp.addToValue(loot.getExp());
		} catch (UpperLimitReachException e) {
			levelUp();
		}
		
		this.gold += loot.getGold();
		loot.getItem().ifPresent(i -> this.inventory.addItem(i));
	}

	private void levelUp() {
		double previousLimit = this.exp.getLimit();
		this.exp = new SimpleLimitedCounter(0, previousLimit + 500);
		this.level ++;
		this.uppableParam.addPoint(Constants.STATPOINTS_ONLEVELUP);
		this.heroClass.getSkillTree().addPoint(Constants.SKILLPOINTS_ONLEVELUP);;
	}

	/**
	 * {@inheritDoc Hero}
	 */
	@Override
	public TargetPointSystem<Parameter> getUpgradableParameter() {
		return this.uppableParam;
	}

	@Override
	public BodyDef defBody() {
		return Box2DUtils.bodyDefBuilder()
					.type(BodyType.DYNAMIC)
					.build();
	}

	@Override
	public Collection<FixtureDef> defFixture() {
		Collection<FixtureDef> retValue = new LinkedList<>();
		Filter filter = Box2DUtils.filterBuilder()
						.addCategory(FilterType.HERO.getValue())
						.addMask(FilterType.GROUND.getValue())
						.addMask(FilterType.ENEMY.getValue())
						.addMask(FilterType.WALL.getValue())
						.addMask(FilterType.INTERACTABLE.getValue())
						.addMask(FilterType.ENEMYATTACK.getValue())
						.build();
		PolygonShape underBody = new PolygonShape();
		float bodyX = (Constants.HERO_SIZE.getX() / 2) * HeroImpl.BODY_PROP.getX();
		float bodyY = (Constants.HERO_SIZE.getY() / 2) * HeroImpl.BODY_PROP.getY();
		underBody.setAsBox(bodyX, bodyY , new Vec2(0,bodyY), 0f);
		CircleShape head = new CircleShape();
		head.getVertex(0).set(0, -((Constants.HERO_SIZE.getY() / 2) - (Constants.HERO_SIZE.getX() / 2) * HeroImpl.HEAD_PROP));
		head.setRadius((Constants.HERO_SIZE.getX() / 2) * HeroImpl.HEAD_PROP);
		retValue.add(Box2DUtils.fixDefBuilder().shape(underBody).friction(0).filter(filter).build());
		retValue.add(Box2DUtils.fixDefBuilder().shape(head).friction(0).filter(filter).build());
		return retValue;
	}

	/**
	 * {@inheritDoc Hero}
	 */
	@Override
	public Collection<Item> getEquippedItem() {
		return this.slots.entrySet().stream()
				.filter(e -> e.getValue().isPresent())
				.map(e -> e.getValue().get())
				.collect(Collectors.toList());
	}
	
	private void initMoreAction() {
		super.actionAllowed.getActionAllowed().put(Actor.Action.BACK.toString(), () -> {
			DungeonSingleton.getInstance()
			.getCurrentRoom().getInteractable().stream()
				.filter(i -> i.isInteractAllowed() && i.getInfo().getName().equals(RoomEntity.RoomEntityName.DOOR_BACK.toString()))
				.findFirst().ifPresent(c -> c.interact());
		});
		super.actionAllowed.getActionAllowed().put(Actor.Action.INTERACT.toString(), () -> {
			DungeonSingleton.getInstance()
			.getCurrentRoom().getInteractable().stream()
				.filter(i -> i.isInteractAllowed() && !i.getInfo().getName().equals(RoomEntity.RoomEntityName.DOOR_BACK.toString()))
				.findFirst().ifPresent(c -> c.interact());
		});
				
		super.actionAllowed.getActionAllowed().put(Actor.Action.ABILITY1.toString(), () -> {});
		super.actionAllowed.getActionAllowed().put(Actor.Action.ABILITY2.toString(), () -> {});
		super.actionAllowed.getActionAllowed().put(Actor.Action.ABILITY3.toString(), () -> {});
		super.actionAllowed.getActionAllowed().put(Actor.Action.CONSUMABLE.toString(), () -> {});
	}
}
