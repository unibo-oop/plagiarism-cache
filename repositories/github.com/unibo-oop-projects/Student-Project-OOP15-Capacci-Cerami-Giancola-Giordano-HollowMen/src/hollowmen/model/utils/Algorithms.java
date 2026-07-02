package hollowmen.model.utils;

import java.util.Collection;
import java.util.LinkedList;

import org.jbox2d.common.Vec2;

import hollowmen.enumerators.EnemyTitle;
import hollowmen.enumerators.ParamName;
import hollowmen.model.Actor;
import hollowmen.model.Enemy;
import hollowmen.model.Interactable;
import hollowmen.model.Lootable;
import hollowmen.model.Modifier;
import hollowmen.model.Room;
import hollowmen.model.dungeon.DungeonSingleton;
import hollowmen.model.dungeon.LootableImpl;
import hollowmen.model.dungeon.ModifierImpl;
import hollowmen.model.enemy.EnemyPool;
import hollowmen.model.item.ItemPool;
import hollowmen.model.roomentity.interactable.TreasureChest;
import hollowmen.utilities.RandomSelector;

public class Algorithms {

	private final static int MIN_DIGIT = 100;
	private final static double ITEM_CHANCE_ORDINARY = 0.1 * MIN_DIGIT; 
	private final static double ITEM_CHANCE_COMMANDER = 0.3 * MIN_DIGIT; 
	private final static double ITEM_CHANCE_BOSS = 1 * MIN_DIGIT; 
	private final static double FLAT_ENEMY_EXP = 50;
	private final static double FLAT_ENEMY_GOLD = 20;
	private final static Vec2 FORCE_BACK = new Vec2(100f, -100f);
	
	
	public static void populateRoom(Room room) {
		Interactable chest = new TreasureChest();
		Box2DUtils.lowerCorner(chest, false);
	}
	
	public static Lootable treasureLoot() {
		int floorNum = DungeonSingleton.getInstance().getFloorNumber();
		int roomNum = DungeonSingleton.getInstance().getCurrentRoom().getRoomNumber();
		int expAndGold = floorNum * Constants.TREASURE_FLATFLOOR + roomNum * Constants.TREASURE_FLATROOM;
		if(RandomSelector.getIntFromRange(0, 100) < Constants.TREASURE_ITEMCHANCE) {
			return new LootableImpl(expAndGold, expAndGold, ItemPool.getInstance().getCompletelyRandom());
		} else {
			return new LootableImpl(expAndGold, expAndGold, null);
		}
	}
	
	public static Collection<Enemy> generateEnemy() {
		int maxLevel = (DungeonSingleton.getInstance().getFloorNumber()) 
				+ DungeonSingleton.getInstance().getCurrentRoom().getRoomNumber() / Constants.ROOM_TO_VISIT;
		int maxPower = DungeonSingleton.getInstance().getCurrentRoom().getRoomNumber() + 
				DungeonSingleton.getInstance().getFloorNumber();
		Collection<Enemy> retValue = new LinkedList<>();
		if(DungeonSingleton.getInstance().getCurrentRoom().getRoomNumber() == Constants.ROOM_TO_VISIT) {
			Enemy boss = EnemyPool.getInstance().getRandomForTitle(p -> p.equals(EnemyTitle.BOSS.toString()));
			retValue.add(boss);
			Box2DUtils.enemyPositioning(boss);
			return retValue;
		}
		while(maxPower > 0) {
			Enemy e = EnemyPool.getInstance().getRandomForLevelTitle(p -> p <= ((maxLevel == 0) ? 1 : maxLevel),
					s -> s.equals(EnemyTitle.ORDINARY.toString()));
			maxPower -= e.getLevel();
			retValue.add(e);
			Box2DUtils.enemyPositioning(e);
//			e.getBody().setTransform(new Vec2(RandomSelector.getIntFromRange(0, 1) == 0 ?
//					RandomSelector.getIntFromRange(Algorithms.MIN_ENEMY_DISTANCE_FROM_WALL, Algorithms.MAX_ENEMY_DISTANCE_FROM_WALL)
//					: RandomSelector.getIntFromRange((int)(Constants.WORLD_SIZE.getWidth() - Algorithms.MAX_ENEMY_DISTANCE_FROM_WALL),
//							(int) Constants.WORLD_SIZE.getWidth() - Algorithms.MIN_ENEMY_DISTANCE_FROM_WALL),
//					300f), 0);
		}
		return retValue;
	}
	
	public static Lootable genLootEnemy(Enemy en) {
		double titleMul = 1;
		double titleRarity = 0;
		double itemChance = 0;
		EnemyTitle title = EnemyTitle.valueOf(en.getTitle().toUpperCase());
		
		switch(title) {
		case ORDINARY:
			titleMul = 1;
			titleRarity = 0;
			itemChance = Algorithms.ITEM_CHANCE_ORDINARY;
			break;
		case COMMANDER:
			titleMul = 1.5;
			titleRarity = 1;
			itemChance = Algorithms.ITEM_CHANCE_COMMANDER;
			break;
		case BOSS:
			titleMul = 2;
			titleRarity = 3;
			itemChance = Algorithms.ITEM_CHANCE_BOSS;
			break;
		}
		int gold = (int) (Algorithms.FLAT_ENEMY_GOLD * titleMul);
		int exp = (int) (Algorithms.FLAT_ENEMY_EXP * titleMul);
		
		if(RandomSelector.getIntFromRange(0, MIN_DIGIT) > itemChance) {
			final int rarity = (int) (en.getLevel() + titleRarity);
			return new LootableImpl(gold, exp, ItemPool.getInstance()
					.getRandom(x -> x.getRarity() >= en.getLevel() || x.getRarity() <= rarity));
		}
		return new LootableImpl(gold, exp, null);
	}
	
	public static void combat(Actor hitter, Actor subj) {
		double atkValue = hitter.getParameters().get(ParamName.ATTACK.toString()).getValue();
		double defValue = subj.getParameters().get(ParamName.DEFENSE.toString()).getValue();
		double res = ((atkValue - defValue) < 1) ? 1 : atkValue - defValue;
		subj.getParameters().get(ParamName.HP.toString())
			.removeModifier(new ModifierImpl(ParamName.HP.toString(), res, Modifier.Operation.ADD));
		subj.getBody().applyForceToCenter(new Vec2(hitter.isFacingRight() ? FORCE_BACK.x : -FORCE_BACK.x, FORCE_BACK.y));
	}
	
}
