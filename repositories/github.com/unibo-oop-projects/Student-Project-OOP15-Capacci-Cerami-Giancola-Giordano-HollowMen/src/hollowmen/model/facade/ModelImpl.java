package hollowmen.model.facade;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import hollowmen.enumerators.ActorState;
import hollowmen.enumerators.Difficulty;
import hollowmen.enumerators.Values;
import hollowmen.model.Enemy;
import hollowmen.model.Item;
import hollowmen.model.Modifier;
import hollowmen.model.Hero;
import hollowmen.model.Interactable;
import hollowmen.model.Parameter;
import hollowmen.model.dungeon.DungeonSingleton;
import hollowmen.model.dungeon.Initializer;
import hollowmen.model.utils.GameOverException;
import hollowmen.utilities.Pair;

/**
 * 
 * @author Giordo
 *
 */
public class ModelImpl implements Model{
	
	private DungeonSingleton dungeon;
	private Hero hero;
	
	public void setup(){
		new Initializer();
		this.dungeon=DungeonSingleton.getInstance();
		this.hero=dungeon.getHero();
	}
	
	public void goTo(boolean lobby){
	    if(lobby){
	        this.dungeon.goTo(0);
	        try{
	            this.dungeon.update(1);
	        }catch(GameOverException e){}
	    }else{
		this.dungeon.goTo(this.dungeon.getFloors().getX());
	    }
	}
	
	public void update(long deltaTime) throws GameOverException {
		this.dungeon.update(deltaTime);
	}
	
	public void moveHero(String move){
		this.hero.move(move);
	}
	
	public void heroAction(String action) {
		this.hero.performAction(action);
	}

	public List<DrawableRoomEntity> getDrawableRoomEntity() {
		List<DrawableRoomEntity> drawable=new LinkedList<>();
		Values.LIFE.setValue((int)this.hero.getParameters().get("hp").getValue());
		Values.MAXLIFE.setValue((int)this.hero.getParameters().get("hpmax").getValue());
		Values.GOLD.setValue(this.hero.getGold());
		Values.LEVEL.setValue(this.hero.getLevel());
		Values.EXP.setValue(this.hero.getExp().getX());
		Values.EXPNEEDE.setValue(this.hero.getExp().getY());
		Values.TIMER.setValue((int)((this.dungeon.getTimer().getLimit()
		        -this.dungeon.getTimer().getValue())/1000));
		Values.FLOOR.setValue(this.dungeon.getFloorNumber());
		/*actually bugged, actor state don change to moving so i give that to the view*/
		//ActorState sta=ActorState.STANDING;
		ActorState sta=ActorState.MOVING;// need to be changed
		if(this.hero.getState().equals("move")){
		    sta=ActorState.MOVING;
		}
		String name;
		drawable.add(new DrawableRoomEntityImpl(this.hero.getInfo().getName(),
		        new Point2DImpl((int)(this.hero.getBody().getWorldCenter().x-this.hero.getLength()/2),
		                		(int)(this.hero.getBody().getWorldCenter().y-this.hero.getHeight()/2)),
		        this.hero.isFacingRight(),
		        sta));
		for(Enemy re: this.dungeon.getCurrentRoom().getEnemies()){
			name=new String(re.getInfo().getName());
			if(re.getLevel()<5){
				name+=re.getLevel();
			}else{
				name+="Boss";
			}
			
			drawable.add(new DrawableRoomEntityImpl(
					name,
					new Point2DImpl((int)(re.getBody().getWorldCenter().x-re.getLength()/2),
									(int)(re.getBody().getWorldCenter().y-re.getHeight()/2)),
					re.isFacingRight(),
					ActorState.STANDING));/*STANDING state, beacuse only one image is available for now*/
		}
		for(Interactable re: this.dungeon.getCurrentRoom().getInteractable()){
			if(!re.getInfo().getName().equals("door_back")){
                    drawable.add(new DrawableRoomEntityImpl(
                                    re.getInfo().getName(),
                                    new Point2DImpl((int)(re.getBody().getWorldCenter().x-re.getLength()/2),
                                                    (int)(re.getBody().getWorldCenter().y-re.getHeight()/2)),
                                    false,
                                    ActorState.STANDING));/*STANDING state, beacuse it's a door or a chest*/
			}  
            }
		return drawable;
	}

	public List<InformationDealer> getPokedex() {
		List<InformationDealer> info=new LinkedList<>();
		Map<String,Double> param;
		String name;
		for(Enemy en: this.dungeon.getPokedex().getEnemyMet()){
		    param=new HashMap<>();
			for(Map.Entry<String, Parameter> map:en.getParameters().entrySet()){
				param.put(map.getKey(),(Double)map.getValue().getValue());
			}
			name=new String(en.getInfo().getName());
			if(en.getLevel()<5){
				name+=en.getLevel();
			}else{
				name+="Boss";
			}
			info.add(new InformationDealerImpl(name,
					en.getInfo().getDescription().orElse(""),
					param,
					en.getState(),
					1,
					""));
		}
		return info;
	}
	
	public List<InformationDealer> getInventory() {
		List<InformationDealer> info=new LinkedList<>();
		Map<String,Double> param;
		String desc;
		Values.GOLD.setValue(this.hero.getGold());
		for(Pair<Item,Integer> it: this.hero.getInventory().getAllItem()){
		    param=new HashMap<>();
		    for(Modifier m:it.getX().getModifiers().values()){
				param.put(m.getParameter().getInfo().getName(),m.getParameter().getValue());
			}
		    desc=it.getX().getInfo().getDescription().orElse("")+"\nValues: "+it.getX().getGoldValue();
			info.add(new InformationDealerImpl(it.getX().getInfo().getName(),
					desc,
					param,
					it.getX().getState().name(),
					it.getY(),
					it.getX().getSlot()));
		}
		for(Item it:this.hero.getEquippedItem()){
			param=new HashMap<>();
		    for(Modifier m:it.getModifiers().values()){
				param.put(m.getParameter().getInfo().getName(),m.getParameter().getValue());
			}
		    desc=it.getInfo().getDescription().orElse("")+"\nValues: "+it.getGoldValue();
		    info.add(new InformationDealerImpl(it.getInfo().getName(),
		    		desc,
		    		param,
		    		it.getState().name(),
		    		1,
		    		it.getSlot()));
		}
		return info;
	}
	
	public List<InformationDealer> getShop() {
		List<InformationDealer> info=new LinkedList<>();
		Map<String,Double> param;
		String desc;
		Values.GOLD.setValue(this.hero.getGold());
		for(Item it: this.dungeon.getShop().getShopItem()){
		    param=new HashMap<>();
		    for(Modifier m:it.getModifiers().values()){
				param.put(m.getParameter().getInfo().getName(),m.getParameter().getValue());
			}
		    desc=it.getInfo().getDescription().orElse("")+"\nValues: "+it.getGoldValue();
			info.add(new InformationDealerImpl(it.getInfo().getName(),
					desc,
					param,
					it.getState().name(),
					1,
					it.getSlot()));
		}
		return info;
	}

	public void itemEquip(InformationDealer item) {
		try{
			for(Pair<Item,Integer> it: this.hero.getInventory().getAllItem()){
				if(it.getX().getInfo().getName()==item.getName()){
					this.hero.equipItem(it.getX());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void itemUnequip(InformationDealer item) {
		try{
			for(Item it: this.hero.getEquippedItem()){
				if(it.getInfo().getName()==item.getName()){
					this.hero.unequipItem(it);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void itemBuy(InformationDealer item) {
		try{
			for(Item it: this.dungeon.getShop().getShopItem()){
				if(it.getInfo().getName()==item.getName()){
					this.hero.buyItem(it);
				}
			}
			Values.GOLD.setValue(this.hero.getGold());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void itemSell(InformationDealer item){
		try{
			for(Pair<Item,Integer> it: this.hero.getInventory().getAllItem()){
				if(it.getX().getInfo().getName()==item.getName()){
					this.hero.sellItem(it.getX());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setDifficulty(Difficulty difficulty){
		this.dungeon.setDifficulty(difficulty);
	}
	
}
