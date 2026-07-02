package hollowmen.model.dungeon;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import hollowmen.model.Enemy;
import hollowmen.model.Hero;
import hollowmen.model.HeroClass;
import hollowmen.model.Inventory;
import hollowmen.model.Item;
import hollowmen.model.Item.ItemState;
import hollowmen.model.Modifier;
import hollowmen.model.Parameter;
import hollowmen.model.RoomEntity;
import hollowmen.model.SkillNode;
import hollowmen.model.enemy.EnemyFactory;
import hollowmen.model.enemy.EnemyPool;
import hollowmen.model.Parameter.ParamName;
import hollowmen.model.item.ItemImpl;
import hollowmen.model.item.ItemPool;
import hollowmen.model.roomentity.hero.ChallengeImpl;
import hollowmen.model.roomentity.hero.HeroClassImpl;
import hollowmen.model.roomentity.hero.HeroImpl;
import hollowmen.model.roomentity.hero.InventoryImpl;
import hollowmen.model.roomentity.hero.skilltree.SkillNodeModifier;
import hollowmen.model.roomentity.hero.skilltree.SkillTreeImpl;
import hollowmen.utilities.Pair;

/**
 * This class can be instantiate for setting:<br>
 * °{@link ItemPool}<br>
 * °{@link EnemyPool}<br>
 * °{@link Hero} directly to the {@link Dungeon}<br>
 * °{@link Dungeon} settings {@link Shop}, {@link Pokedex} and unlocked floor
 * <br><br>
 * This class was made for testing the application without any saving files
 * @author pigio
 *
 */
public class Initializer {

	public Initializer() {
		generateItem();
		generateEnemy();
		generateHero();
		generalSetting();
	}

	private void generalSetting() {
		DungeonSingleton.getInstance().setPokedex(new PokedexImpl());
		DungeonSingleton.getInstance().setUnlockedFloor(1, 8);
		Collection<Item> shop = new LinkedList<>();
		shop.add(ItemPool.getInstance().getItem("woodSword"));
		shop.add(ItemPool.getInstance().getItem("bootsBlack"));
		shop.add(ItemPool.getInstance().getItem("bootsBlue"));
		shop.add(ItemPool.getInstance().getItem("chestBronze"));
		shop.add(ItemPool.getInstance().getItem("chestSilver"));
		shop.add(ItemPool.getInstance().getItem("glovesBlack"));
		shop.add(ItemPool.getInstance().getItem("glovesBlue"));
		shop.add(ItemPool.getInstance().getItem("headBronze"));
		shop.add(ItemPool.getInstance().getItem("headSilver"));
		shop.add(ItemPool.getInstance().getItem("simpleSword"));
		shop.add(ItemPool.getInstance().getItem("redSword"));
		shop.add(ItemPool.getInstance().getItem("longSword"));
		DungeonSingleton.getInstance().setShop(new ShopImpl(shop, 0));
	}

	private Collection<SkillNode> genNode() {
		List<SkillNode> list = new LinkedList<>();
		list.add(new SkillNodeModifier(new InfoImpl("heavy attack"), "attack", 0, 3,
				genMod(ParamName.ATTACK.toString(), 5, true)));
		list.add(new SkillNodeModifier(new InfoImpl("more attack"), "attack", 0, 3,
				genMod(ParamName.ATTACK.toString(), 1.01, false)));
		list.add(new SkillNodeModifier(new InfoImpl("ultimate attack"), "attack", 1, 2,
				genMod(ParamName.ATTACK.toString(), 20, true)));
		list.add(new SkillNodeModifier(new InfoImpl("heavy defense"), "defense", 0, 3,
				genMod(ParamName.DEFENSE.toString(), 5, true)));
		list.add(new SkillNodeModifier(new InfoImpl("more defense"), "defense", 0, 3,
				genMod(ParamName.DEFENSE.toString(), 1.01, false)));
		list.add(new SkillNodeModifier(new InfoImpl("ultimate defense"), "defense", 1, 2,
				genMod(ParamName.DEFENSE.toString(), 5, true)));
		list.add(new SkillNodeModifier(new InfoImpl("faster attack"), "speed", 0, 3,
				genMod(ParamName.ATTACKSPEED.toString(), 1.02, false)));
		list.add(new SkillNodeModifier(new InfoImpl("faster move"), "speed", 0, 3,
				genMod(ParamName.MOVSPEED.toString(), 1.02, false)));
		list.add(new SkillNodeModifier(new InfoImpl("ultimate speed"), "speed", 1, 2,
				genMod(ParamName.MOVSPEED.toString(), 1.1, false)));
		return list;
	}
	
	
	private void generateHero() {
		Collection<Parameter> list = new LinkedList<>();
		list.add(genParam(Parameter.ParamName.HPMAX.toString(), 100));
		list.add(genParam(Parameter.ParamName.ATTACK.toString(), 10));
		list.add(genParam(Parameter.ParamName.ATTACKRANGE.toString(), 100));
		list.add(genParam(Parameter.ParamName.ATTACKSPEED.toString(), 1.5));
		list.add(genParam(Parameter.ParamName.DEFENSE.toString(), 10));
		list.add(genParam(Parameter.ParamName.MOVSPEED.toString(), 20));

		Collection<Item> initItem = new LinkedList<>();
		initItem.add(ItemPool.getInstance().getItem("woodSword"));
		Inventory inventory = new InventoryImpl();
		inventory.addItem(ItemPool.getInstance().getItem("woodSword"));
		inventory.addItem(ItemPool.getInstance().getItem("bootsBlack"));
		
		Hero hero =  new HeroImpl(1, 100, new Pair<>(0, 500), "You are the True Hero!",
				new HeroClassImpl(new InfoImpl(HeroClass.Name.WARRIOR.toString()), new SkillTreeImpl(genNode())
						, list, new ChallengeImpl()), inventory,  initItem);
		DungeonSingleton.getInstance().setHero(hero);
	}


	private Modifier genMod(String s, double value, boolean isADD) {
		return new ModifierImpl(s, value, (isADD ? Modifier.Operation.ADD : Modifier.Operation.MUL));
	}
	
	private Parameter genParam(String s, double value) {
		return new ParamImpl(new InfoImpl(s), value);
	}
	
	
	
	
	
	private void generateItem() {
		Collection<Modifier> mod = new LinkedList<>();
		mod.add(genMod(Parameter.ParamName.MOVSPEED.toString(), 10, true));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("bootsBlack", "They're Black...amazing"))
				.rarity(1)
				.slot(Item.SlotName.BOOTS.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(50)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.MOVSPEED.toString(), 25, true));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("bootsBlue", "They're Blue...quite fashion"))
				.rarity(2)
				.slot(Item.SlotName.BOOTS.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(200)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.MOVSPEED.toString(), 50, true));
		mod.add(genMod(Parameter.ParamName.DEFENSE.toString(), 10, true));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("bootsBrown", "They're Brown... and old."))
				.rarity(3)
				.slot(Item.SlotName.BOOTS.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(500)
				.modifier(mod)
				.build());
		
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.DEFENSE.toString(), 10, true));
		mod.add(genMod(Parameter.ParamName.HPMAX.toString(), 15, true));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("chestBronze", "It looks like Gold at first sight! \n But actually... it's not."))
				.rarity(1)
				.slot(Item.SlotName.CHEST.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(250)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.DEFENSE.toString(), 30, true));
		mod.add(genMod(Parameter.ParamName.HPMAX.toString(), 50, true));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("chestSilver", "It looks like Silver at first sight... wait...it is!"))
				.rarity(2)
				.slot(Item.SlotName.CHEST.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(500)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.DEFENSE.toString(), 50, true));
		mod.add(genMod(Parameter.ParamName.HPMAX.toString(), 100, true));
		mod.add(genMod(Parameter.ParamName.ATTACK.toString(), 10, true));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("chestGold", "It looks like Gold at first sight... Holy!... It is!"))
				.rarity(3)
				.slot(Item.SlotName.CHEST.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(800)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.ATTACKSPEED.toString(), 0.9, false));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("glovesBlack", "Because black is my happy color."))
				.rarity(1)
				.slot(Item.SlotName.GLOVES.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(75)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.ATTACKSPEED.toString(), 0.8, false));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("glovesBlue", "Blue!"))
				.rarity(2)
				.slot(Item.SlotName.GLOVES.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(150)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.ATTACKSPEED.toString(), 0.6, false));
		mod.add(genMod(Parameter.ParamName.ATTACK.toString(), 1.05, false));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("glovesGrey", "Mmh... nice."))
				.rarity(3)
				.slot(Item.SlotName.GLOVES.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(400)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.DEFENSE.toString(), 5, true));
		mod.add(genMod(Parameter.ParamName.HPMAX.toString(), 10, true));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("headBronze", "You are not gold..."))
				.rarity(1)
				.slot(Item.SlotName.HEAD.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(150)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.DEFENSE.toString(), 10, true));
		mod.add(genMod(Parameter.ParamName.HPMAX.toString(), 20, true));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("headSilver", "You are Silver..."))
				.rarity(2)
				.slot(Item.SlotName.HEAD.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(300)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.DEFENSE.toString(), 20, true));
		mod.add(genMod(Parameter.ParamName.HPMAX.toString(), 50, true));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("headGold", "I can give you 5 dollars for this."))
				.rarity(3)
				.slot(Item.SlotName.HEAD.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(500)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.ATTACKSPEED.toString(), 1.1, false));
		mod.add(genMod(Parameter.ParamName.ATTACK.toString(), 10, true));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("woodSword", "This is... just for training, I guess."))
				.rarity(1)
				.slot(Item.SlotName.WEAPON.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(30)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.ATTACK.toString(), 50, true));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("simpleSword", "From the wood to the iron!"))
				.rarity(2)
				.slot(Item.SlotName.WEAPON.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(100)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.ATTACKSPEED.toString(), 0.7, false));
		mod.add(genMod(Parameter.ParamName.ATTACK.toString(), 100, true));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("redSword", "From the iron to the iron... but with fashionable red details."))
				.rarity(3)
				.slot(Item.SlotName.WEAPON.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(350)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.ATTACKRANGE.toString(), 1.3, false));
		mod.add(genMod(Parameter.ParamName.ATTACK.toString(), 100, true));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("longSword", "From the iron to the, mh, longer iron (?)"))
				.rarity(3)
				.slot(Item.SlotName.WEAPON.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(350)
				.modifier(mod)
				.build());
		
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.ATTACKSPEED.toString(), 1.25, false));
		mod.add(genMod(Parameter.ParamName.ATTACK.toString(), 300, true));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("scimitar", "From the iron to the iron... heavier."))
				.rarity(4)
				.slot(Item.SlotName.WEAPON.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(500)
				.modifier(mod)
				.build());
		
		mod.clear();
		mod.add(genMod(Parameter.ParamName.ATTACKSPEED.toString(), 0.5, false));
		mod.add(genMod(Parameter.ParamName.ATTACK.toString(), 1000, true));
		mod.add(genMod(Parameter.ParamName.ATTACK.toString(), 2, false));
		mod.add(genMod(Parameter.ParamName.MOVSPEED.toString(), 1.5, false));
		ItemPool.getInstance().addItem(ItemImpl.builder()
				.info(new InfoImpl("excalibur", "This LEGENDARY sword has NEVER been seen in any other RPG game..."))
				.rarity(5)
				.slot(Item.SlotName.WEAPON.toString())
				.state(ItemState.UNEQUIPPED)
				.heroClass(HeroClass.Name.WARRIOR.toString())
				.value(1000)
				.modifier(mod)
				.build());
		
	}
	
	
	public void generateEnemy() {
		Collection<Parameter> list = new LinkedList<>();
		list.add(genParam(Parameter.ParamName.HPMAX.toString(), 20));
		list.add(genParam(Parameter.ParamName.ATTACK.toString(), 20));
		list.add(genParam(Parameter.ParamName.ATTACKRANGE.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACKSPEED.toString(), 1));
		list.add(genParam(Parameter.ParamName.DEFENSE.toString(), 5));
		list.add(genParam(Parameter.ParamName.MOVSPEED.toString(), 10));
		EnemyPool.getInstance().addEnemy(EnemyFactory.getInstance().getBuilderFor(RoomEntity.RoomEntityName.BAT.toString())
				.description("This cutie... will bite you!")
				.level(1)
				.param(list)
				.title(Enemy.EnemyTitle.ORDINARY.toString())
				.build());
		
		list.clear();
		list.add(genParam(Parameter.ParamName.HPMAX.toString(), 50));
		list.add(genParam(Parameter.ParamName.ATTACK.toString(), 50));
		list.add(genParam(Parameter.ParamName.ATTACKRANGE.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACKSPEED.toString(), 1));
		list.add(genParam(Parameter.ParamName.DEFENSE.toString(), 20));
		list.add(genParam(Parameter.ParamName.MOVSPEED.toString(), 30));
		EnemyPool.getInstance().addEnemy(EnemyFactory.getInstance().getBuilderFor(RoomEntity.RoomEntityName.BAT.toString())
				.description("This bat will bite you hard!")
				.level(2)
				.param(list)
				.title(Enemy.EnemyTitle.ORDINARY.toString())
				.build());
		
		list.clear();
		list.add(genParam(Parameter.ParamName.HPMAX.toString(), 100));
		list.add(genParam(Parameter.ParamName.ATTACK.toString(), 110));
		list.add(genParam(Parameter.ParamName.ATTACKRANGE.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACKSPEED.toString(), 1));
		list.add(genParam(Parameter.ParamName.DEFENSE.toString(), 30));
		list.add(genParam(Parameter.ParamName.MOVSPEED.toString(), 50));
		EnemyPool.getInstance().addEnemy(EnemyFactory.getInstance().getBuilderFor(RoomEntity.RoomEntityName.BAT.toString())
				.description("This bat will bite you and become Dracula.")
				.level(3)
				.param(list)
				.title(Enemy.EnemyTitle.ORDINARY.toString())
				.build());
		
		list.clear();
		list.add(genParam(Parameter.ParamName.HPMAX.toString(), 300));
		list.add(genParam(Parameter.ParamName.ATTACK.toString(), 250));
		list.add(genParam(Parameter.ParamName.ATTACKRANGE.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACKSPEED.toString(), 1));
		list.add(genParam(Parameter.ParamName.DEFENSE.toString(), 40));
		list.add(genParam(Parameter.ParamName.MOVSPEED.toString(), 70));
		EnemyPool.getInstance().addEnemy(EnemyFactory.getInstance().getBuilderFor(RoomEntity.RoomEntityName.BAT.toString())
				.description("Edward Cullen -who ? BAT BOSS")
				.level(5)
				.param(list)
				.title(Enemy.EnemyTitle.BOSS.toString())
				.build());
		
		list.clear();
		list.add(genParam(Parameter.ParamName.HPMAX.toString(), 50));
		list.add(genParam(Parameter.ParamName.ATTACK.toString(), 10));
		list.add(genParam(Parameter.ParamName.ATTACKRANGE.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACKSPEED.toString(), 1));
		list.add(genParam(Parameter.ParamName.DEFENSE.toString(), 30));
		list.add(genParam(Parameter.ParamName.MOVSPEED.toString(), 5));
		EnemyPool.getInstance().addEnemy(EnemyFactory.getInstance().getBuilderFor(RoomEntity.RoomEntityName.SLIME.toString())
				.description("This Slime is so slow and resistance... \n so watch out the time!")
				.level(1)
				.param(list)
				.title(Enemy.EnemyTitle.ORDINARY.toString())
				.build());
		
		list.clear();
		list.add(genParam(Parameter.ParamName.HPMAX.toString(), 200));
		list.add(genParam(Parameter.ParamName.ATTACK.toString(), 20));
		list.add(genParam(Parameter.ParamName.ATTACKRANGE.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACKSPEED.toString(), 1));
		list.add(genParam(Parameter.ParamName.DEFENSE.toString(), 60));
		list.add(genParam(Parameter.ParamName.MOVSPEED.toString(), 15));
		EnemyPool.getInstance().addEnemy(EnemyFactory.getInstance().getBuilderFor(RoomEntity.RoomEntityName.SLIME.toString())
				.description("This Slime is slow and more resistance... \n It's about time, mate!")
				.level(2)
				.param(list)
				.title(Enemy.EnemyTitle.ORDINARY.toString())
				.build());
		
		list.clear();
		list.add(genParam(Parameter.ParamName.HPMAX.toString(), 500));
		list.add(genParam(Parameter.ParamName.ATTACK.toString(), 20));
		list.add(genParam(Parameter.ParamName.ATTACKRANGE.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACKSPEED.toString(), 1));
		list.add(genParam(Parameter.ParamName.DEFENSE.toString(), 120));
		list.add(genParam(Parameter.ParamName.MOVSPEED.toString(), 25));
		EnemyPool.getInstance().addEnemy(EnemyFactory.getInstance().getBuilderFor(RoomEntity.RoomEntityName.SLIME.toString())
				.description("A powerful slime. T-I-M-E !")
				.level(3)
				.param(list)
				.title(Enemy.EnemyTitle.ORDINARY.toString())
				.build());
		
		list.clear();
		list.add(genParam(Parameter.ParamName.HPMAX.toString(), 750));
		list.add(genParam(Parameter.ParamName.ATTACK.toString(), 50));
		list.add(genParam(Parameter.ParamName.ATTACKRANGE.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACKSPEED.toString(), 1));
		list.add(genParam(Parameter.ParamName.DEFENSE.toString(), 300));
		list.add(genParam(Parameter.ParamName.MOVSPEED.toString(), 5));
		EnemyPool.getInstance().addEnemy(EnemyFactory.getInstance().getBuilderFor(RoomEntity.RoomEntityName.SLIME.toString())
				.description("Throw away any defense item and hit \nthis BOSS, HARD!")
				.level(5)
				.param(list)
				.title(Enemy.EnemyTitle.BOSS.toString())
				.build());
		
		
		list.clear();
		list.add(genParam(Parameter.ParamName.HPMAX.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACK.toString(), 50));
		list.add(genParam(Parameter.ParamName.ATTACKRANGE.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACKSPEED.toString(), 1));
		list.add(genParam(Parameter.ParamName.DEFENSE.toString(), 100));
		list.add(genParam(Parameter.ParamName.MOVSPEED.toString(), 30));
		EnemyPool.getInstance().addEnemy(EnemyFactory.getInstance().getBuilderFor(RoomEntity.RoomEntityName.PUPPET.toString())
				.description("They are well balanced: high attack, \nhigh defense but low HP.")
				.level(1)
				.param(list)
				.title(Enemy.EnemyTitle.ORDINARY.toString())
				.build());
		
		list.clear();
		list.add(genParam(Parameter.ParamName.HPMAX.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACK.toString(), 120));
		list.add(genParam(Parameter.ParamName.ATTACKRANGE.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACKSPEED.toString(), 1));
		list.add(genParam(Parameter.ParamName.DEFENSE.toString(), 200));
		list.add(genParam(Parameter.ParamName.MOVSPEED.toString(), 50));
		EnemyPool.getInstance().addEnemy(EnemyFactory.getInstance().getBuilderFor(RoomEntity.RoomEntityName.PUPPET.toString())
				.description("This puppet is so... hollow?")
				.level(2)
				.param(list)
				.title(Enemy.EnemyTitle.ORDINARY.toString())
				.build());
		
		list.clear();
		list.add(genParam(Parameter.ParamName.HPMAX.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACK.toString(), 220));
		list.add(genParam(Parameter.ParamName.ATTACKRANGE.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACKSPEED.toString(), 1));
		list.add(genParam(Parameter.ParamName.DEFENSE.toString(), 300));
		list.add(genParam(Parameter.ParamName.MOVSPEED.toString(), 70));
		EnemyPool.getInstance().addEnemy(EnemyFactory.getInstance().getBuilderFor(RoomEntity.RoomEntityName.PUPPET.toString())
				.description("Don't trust this little cutiepie... ")
				.level(3)
				.param(list)
				.title(Enemy.EnemyTitle.ORDINARY.toString())
				.build());
		
		list.clear();
		list.add(genParam(Parameter.ParamName.HPMAX.toString(), 3));
		list.add(genParam(Parameter.ParamName.ATTACK.toString(), 500));
		list.add(genParam(Parameter.ParamName.ATTACKRANGE.toString(), 1));
		list.add(genParam(Parameter.ParamName.ATTACKSPEED.toString(), 1));
		list.add(genParam(Parameter.ParamName.DEFENSE.toString(), 3000));
		list.add(genParam(Parameter.ParamName.MOVSPEED.toString(), 90));
		EnemyPool.getInstance().addEnemy(EnemyFactory.getInstance().getBuilderFor(RoomEntity.RoomEntityName.PUPPET.toString())
				.description("Here we are! Fight this BIG-ONE!")
				.level(5)
				.param(list)
				.title(Enemy.EnemyTitle.BOSS.toString())
				.build());
	}
	
	
	
	
	
	
}
