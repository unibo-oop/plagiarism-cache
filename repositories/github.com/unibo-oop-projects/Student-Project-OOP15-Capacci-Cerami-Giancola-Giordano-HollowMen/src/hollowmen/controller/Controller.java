package hollowmen.controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import hollowmen.enumerators.ClassType;
import hollowmen.enumerators.Difficulty;
import hollowmen.enumerators.InputCommand;
import hollowmen.enumerators.InputMenu;
import hollowmen.model.facade.DrawableRoomEntity;
import hollowmen.model.facade.InformationDealer;
import hollowmen.model.facade.Model;
import hollowmen.model.facade.ModelImpl;
import hollowmen.model.utils.GameOverException;
import hollowmen.utilities.Pair;
import hollowmen.view.View;
import hollowmen.view.ViewImpl;

/**
 * 
 * @author Giordo
 *
 */
public class Controller implements ViewObserver {
	
	private LinkedList<InputMenu> inputMenuList=new LinkedList<>();
	private LinkedList<InputCommand> inputCommandList=new LinkedList<>();
	private List<InputCommand> inputCommandListThread;
	private Pair<InputCommand,InformationDealer> mapInputCommand=null;
	private View view;
	private Model model;
	private boolean difficultyPicked=false;
	private boolean classPicked=false;
	private boolean gameRunning=false;
	private InputMenu last=InputMenu.MAIN;
	
	public Controller(){
	}
	
	public void setup(){
		//need to use thread safe list due to concurrency
		this.inputCommandListThread=Collections.synchronizedList(this.inputCommandList);
		this.view=new ViewImpl(800,600,this);
		this.view.takeFile(LoaderClass.load());
		this.view.drawMenu(InputMenu.MAIN, Optional.empty());
		this.model=new ModelImpl();
		this.model.setup();
		menuInputLoop();
	}
	
	private void menuChoice(){
		switch(this.inputMenuList.getFirst()){
		case MAIN:{
			this.last=InputMenu.MAIN;
			this.gameRunning=false;
			this.view.drawMenu(InputMenu.MAIN, Optional.empty());
			this.model.goTo(true);//needed to reset hero life
			this.model.setup();//needed to create new game
			this.inputMenuList.clear();
			break;
		}case CLASS:{
			this.view.drawMenu(InputMenu.CLASS, Optional.empty());
			this.model.setup();
			this.inputMenuList.clear();
			break;
		}case DIFFICULTY:{
			this.view.drawMenu(InputMenu.DIFFICULTY, Optional.empty());
			this.inputMenuList.clear();
			break;
		}case HELP:{
			this.view.drawMenu(InputMenu.HELP, Optional.empty());
			this.inputMenuList.clear();
			break;
		}case PAUSE:{
			this.inputMenuList.clear();
			if(this.gameRunning){
				this.view.drawMenu(InputMenu.PAUSE, Optional.empty());
				menuInputLoop();
			}
			break;
		}case INVENTORY:{
			this.view.drawMenu(InputMenu.INVENTORY, Optional.of(model.getInventory()));
			this.inputMenuList.clear();
			itemInputLoop();
			break;
		}case SKILL_TREE:{
			//actually menu missing
			this.inputMenuList.clear();
			gameLoop();
			break;
		}case POKEDEX:{
			this.view.drawMenu(InputMenu.POKEDEX, Optional.of(model.getPokedex()));
			this.inputMenuList.clear();
			menuInputLoop();
			break;
		}case SHOP:{
			List<InformationDealer> shop=new LinkedList<>();
			for(InformationDealer item:model.getInventory()){
				if(item.getState().equals("UNEQUIPPED")){
					shop.add(item);
				}
			}
			shop.addAll(model.getShop());
			this.view.drawMenu(InputMenu.SHOP, Optional.of(shop));
			this.inputMenuList.clear();
			itemInputLoop();
			break;
		}case LOBBY:{
			this.last=InputMenu.LOBBY;
			this.inputMenuList.clear();
			this.view.drawLobby();
			this.model.goTo(true);//needed to reset hero life
			menuInputLoop();
			break;
		}case START:{
			this.last=InputMenu.START;
			this.inputMenuList.clear();
			this.model.goTo(false);
			gameLoop();
			break;
		}case ACHIEVEMENTS:{
			//actually no menu 
			this.inputMenuList.clear();
			gameLoop();
			break;
		}case RESUME:{
			this.inputMenuList.clear();
			if(this.last!=InputMenu.START){
				if(this.last==InputMenu.MAIN){
					this.last=InputMenu.MAIN;
					this.view.drawMenu(InputMenu.MAIN, Optional.empty());
				}else{
					this.last=InputMenu.LOBBY;
					this.view.drawLobby();
				}
				menuInputLoop();
			}else{
				gameLoop();
			}
			break;
		}case BACK:{
			this.inputMenuList.clear();
			if(!this.gameRunning){
				this.view.drawMenu(InputMenu.MAIN, Optional.empty());
			}
			break;
		}
		}
	}
	
	private void menuInputLoop(){
		boolean loop=true;
		while(loop){
			try{
				if(this.difficultyPicked==true){
				    this.difficultyPicked=false;
					this.view.drawMenu(InputMenu.CLASS, Optional.empty());
				}
				if(this.classPicked==true){
					this.classPicked=false;
					this.last=InputMenu.LOBBY;
					this.view.drawLobby();
				}
				if(this.inputMenuList.isEmpty()){
					java.lang.Thread.sleep(100);
				}else{
					menuChoice();
				}
			}catch(Exception e){
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	private void itemInputLoop(){
		boolean loop=true;
		while(loop){
			try{
				if(this.mapInputCommand==null && this.inputMenuList.isEmpty()){
					java.lang.Thread.sleep(100);
				}else{
					if(!this.inputMenuList.isEmpty()){
						menuChoice();
						menuInputLoop();
					}else{
						switch(this.mapInputCommand.getX()){
						case EQUIP:{
							this.model.itemEquip(this.mapInputCommand.getY());
							this.mapInputCommand=null;
							this.view.drawMenu(InputMenu.INVENTORY, Optional.of(model.getInventory()));
							break;
						}case BUY:{
							this.model.itemBuy(this.mapInputCommand.getY());
							this.mapInputCommand=null;
							
							List<InformationDealer> shop=new LinkedList<>();
							for(InformationDealer item:model.getInventory()){
								if(item.getState().equals("UNEQUIPPED")){
									shop.add(item);
								}
							}
							shop.addAll(model.getShop());
							this.view.drawMenu(InputMenu.SHOP, Optional.of(shop));
							
							break;
						}case UNEQUIP:{
							this.model.itemUnequip(this.mapInputCommand.getY());
							this.mapInputCommand=null;
							this.view.drawMenu(InputMenu.INVENTORY, Optional.of(model.getInventory()));
							break;
						}case SELL:{
							this.model.itemSell(this.mapInputCommand.getY());
							this.mapInputCommand=null;
							
							List<InformationDealer> shop=new LinkedList<>();
							for(InformationDealer item:model.getInventory()){
								if(item.getState().equals("UNEQUIPPED")){
									shop.add(item);
								}
							}
							shop.addAll(model.getShop());
							this.view.drawMenu(InputMenu.SHOP, Optional.of(shop));
							
							break;
						}default:{
							this.mapInputCommand=null;
							break;
						}
						}
						
					}
					this.mapInputCommand=null;
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	private void gameInputManager(){
		try{
			if(!this.inputMenuList.isEmpty()){
				menuChoice();
			}else{
				boolean left=false;
				boolean right=false;
				for(InputCommand command:this.inputCommandListThread){
					switch(command){
					case LEFT:{
						if(!left){
							left=true;
							this.model.moveHero("left");
						}
						break;
					}case RIGHT:{
						if(!right){
							right=true;
							this.model.moveHero("right");
						}
						break;
					}case JUMP:{
						this.model.heroAction("jump");
						break;
					}case ATTACK:{
						this.model.heroAction("attack");
						break;
					}case INTERACT:{
						this.model.heroAction("interact");
						break;
					}case BACKHERO:{
						this.model.heroAction("back");
					}default:{
						break;
					}
					}
				}
				this.inputCommandListThread.clear();
			}
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private void gameLoop(){
		
		//nanosec used
		final long skipTick=20000000;//model and view update frequency (50 update per sec)
		//more accurate than millisec in my opinion
		//didn't find a real answer on the Internet
		final int convert=1000000;
		final int skipTickMillisec=(int)(skipTick/convert);//max frame skipped between each model update
		List<DrawableRoomEntity> drawable;
		this.gameRunning=true;
		long sleep;
		this.inputMenuList.clear();
		long tick=System.nanoTime();
		try{
		while(this.gameRunning){
			try{
				this.model.update(skipTickMillisec);
			}catch(GameOverException e){
				this.gameRunning=false;
				this.last=InputMenu.LOBBY;
				this.view.drawLobby();
				menuInputLoop();
				break;
			}
			drawable=this.model.getDrawableRoomEntity();
			this.view.drawGame(drawable);
			gameInputManager();
			tick+=skipTick;
			sleep=(tick-System.nanoTime())/convert;
			if(sleep>0){
				java.lang.Thread.sleep(sleep);
			}
			
		}
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	public void addInput(InputCommand input) {
		this.inputCommandListThread.add(input);
	}

	public void addInput(InputMenu menu) {
		this.inputMenuList.add(menu);
	}

	public void addInput(InputCommand input, InformationDealer item) {
		this.mapInputCommand=new Pair<>(input,item);
	}

	public void addInput(ClassType classType) {
		//actually there is only one class
		this.classPicked=true;
	}
	
	public void addInput(Difficulty difficulty){
		this.difficultyPicked=true;
		this.model.setDifficulty(difficulty);
	}
	
}
