package view.gameview;

import java.util.ArrayList;

import constants.GameConstants;
import controller.gamecontroller.GameController;
import model.enemy.Enemy;
import model.entity.Entity;
import model.map.MapTile;
import model.map.MapTile.Status;
import model.projectile.Projectile;
import model.tower.Tower;
import model.tower.TowerType;
import view.input.InputImpl;
import view.input.InputType;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utilityclasses.Pair;

public class GameScreen extends Region {
	private static final Image logo = new Image("path.png");
	private static final Image towerlogo = new Image("hierophant.png");
	private static final Image towerlogo1 = new Image("hierophant.png");
	private static final Image towerlogo2 = new Image("star.png");
	private static final Image grasslogo = new Image("grass.jpg");
	private static final Image emerald = new Image("1.png");
	private static final Image enemy = new Image("dio.png");
	private ArrayList<GridButton> btList = new ArrayList<>();
	private ArrayList<MapTile> mappa;
	private GameController gc;
	private boolean type1=false;
	private boolean type2=false;
	private boolean type3=false;
	private boolean remove = false;
	
	 private Text text = new Text("COINS"+"  "+ 0);
	 private Text text1 = new Text("HP" +"  "+ 0);
	 private Text text2 = new Text("WAVE" + "  " + 0);
	 private Text text3 = new Text("NAME" + "  " + 0);
	private ArrayList<PathButton> btlist2 = new ArrayList<>();
	
    private static final double buttonSize = GameConstants.buttonSize;
        
	//MainMenu test = new MainMenu();    
        
	public Parent createContent(){
		
		 String style3 = "-fx-background-color: rgba(75, 250, 30, 0.5);"; //verde
		 String style2 = "-fx-background-color: rgba(50, 100, 200, 0.5);"; //trasparente
		 String style4 = "-fx-background-color: rgba(200, 50, 100, 0.5);"; //rosso
		 String style = "-fx-background-color: rgba(150, 100, 50, 0.5);"; //marrone
		
	/* FINESTRA GENERALE */
        Pane root = new Pane(); 
        root.setPrefSize(buttonSize*38, buttonSize*25);
	Image img = new Image("gameMenuImage.png");
	ImageView imgv = new ImageView(img);
	imgv.setFitWidth(buttonSize*40);
	imgv.setFitHeight(buttonSize*26);
	root.setOpacity(1);
	root.getChildren().add(imgv);
        
	/* SIDEBAR */
        FlowPane flow = new FlowPane();
        flow.setPrefSize(buttonSize*15, buttonSize*20);
        flow.setTranslateX(buttonSize*22);
        flow.setTranslateY(buttonSize);
        flow.setBackground(Background.EMPTY);
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setStyle(style);
        
        /* MENU1: START AND OPTION */
        HBox menu1 = new HBox(buttonSize);
        menu1.setPrefSize(buttonSize, buttonSize);
        menu1.setTranslateY(buttonSize);
        menu1.setTranslateX(buttonSize);
        menu1.setSpacing(buttonSize);
        MenuButton start = new MenuButton("start");
        menu1.getChildren().add(start);
        MenuButton pause = new MenuButton("pause");
        pause.setOnMouseClicked(event -> {
        	gc.pauseGame();
        });
        menu1.getChildren().add(pause);
        MenuButton resume = new MenuButton("resume");
        menu1.getChildren().add(resume);
        resume.setOnMouseClicked(event -> {
        	gc.resumeGame();
        });
        flow.getChildren().add(menu1);
        
        /* MENU2: INFO HP, COINS, WAVE */
        VBox menu2 = new VBox();
        
        menu2.setSpacing(buttonSize/3);
        menu2.setPrefSize(buttonSize*13, buttonSize*8);
        menu2.setTranslateY(buttonSize*2);
        menu2.setTranslateX(buttonSize);
        menu2.setBackground(Background.EMPTY);
        
    	text.setFont(Font.loadFont("file:res/JOJO____.ttf", 25));
		text.setFill(Color.GOLD);
        text.setTranslateY(buttonSize);
        
    	text1.setFont(Font.loadFont("file:res/JOJO____.ttf", 25));
		text1.setFill(Color.LAWNGREEN);
        text1.setTranslateY(buttonSize);
        
    	text2.setFont(Font.loadFont("file:res/JOJO____.ttf", 25));
		text2.setFill(Color.DARKRED);
        text2.setTranslateY(buttonSize);
        
        text3.setFont(Font.loadFont("file:res/JOJO____.ttf", 25));
		text3.setFill(Color.DARKBLUE);
        text3.setTranslateY(buttonSize);
        
        menu2.getChildren().addAll(text3,text,text1,text2);
       
        menu2.setStyle(style2);
        flow.getChildren().add(menu2);
        
        /* MENU3: TOWERS */
        FlowPane menu3 = new FlowPane();
        menu3.setPrefSize(buttonSize*14,buttonSize* 6);
        menu3.setTranslateX(buttonSize*2.125);
        menu3.setTranslateY(buttonSize*2.5);
        //menu3.setSpacing(buttonSize); //se usi hbox
        menu3.setBackground(Background.EMPTY);
        //menu3.setPadding(new Insets(buttonSize,buttonSize,buttonSize,buttonSize));
        menu3.setHgap(buttonSize);
        menu3.setVgap(buttonSize);
        PlaceTowerButton tower1 = new PlaceTowerButton("type 1");
        menu3.getChildren().add(tower1);
        PlaceTowerButton tower2 = new PlaceTowerButton("type 2");
        menu3.getChildren().add(tower2);
        PlaceTowerButton tower3 = new PlaceTowerButton("Type 3");
        menu3.getChildren().add(tower3);
        PlaceTowerButton remove = new PlaceTowerButton("remove");
        menu3.getChildren().add(remove);
        flow.getChildren().add(menu3);

        /* GRIGLIA DI GIOCO */
        GridPane grid = new GridPane();
        grid.setPrefSize(buttonSize*20, buttonSize*20);

        for(MapTile m:this.mappa){
        	int x = m.getPosition().getX();
    		int y = m.getPosition().getY();
    		/*se è un percorso*/
        	if(m.getStatus()==Status.PATH) {
				PathButton b2 = new PathButton("");
				b2.setPosition(new Pair<Integer,Integer>(x,y));
				btlist2.add(b2);
            	grid.add(b2,x,y);
        	}
        	else { /*se non è percorso*/
        		GridButton bt = new GridButton("");
        		bt.setPosition(new Pair<Integer,Integer>(x, y));
        		bt.setOnMouseClicked(event -> {
        			if(gc == null) {
        				throw new NullPointerException();
        			}
        			else {
        				if(this.type1) {
        					ImageView imgt = new ImageView(towerlogo);
        					imgt.setFitWidth(buttonSize);
        					imgt.setFitHeight(buttonSize);
        					bt.getChildren().add(imgt);
        					gc.handleInput(new InputImpl(InputType.ADD_TOWER,TowerType.BASIC, x, y));
        				System.out.println("droppo torre 1 in");
        				}
        				else if(this.type2) {
        					ImageView imgt = new ImageView(towerlogo1);
        					imgt.setFitWidth(buttonSize);
        					imgt.setFitHeight(buttonSize);
        					bt.getChildren().add(imgt);
        					
        					gc.handleInput(new InputImpl(InputType.ADD_TOWER,TowerType.RANGED, x, y));
        					System.out.println("droppo torre 2");
        				}   
        				
        				else if(this.type3) {
        					ImageView imgt = new ImageView(towerlogo2);
        					imgt.setFitWidth(buttonSize);
        					imgt.setFitHeight(buttonSize);
        					bt.getChildren().add(imgt);
        					
        					gc.handleInput(new InputImpl(InputType.ADD_TOWER,TowerType.CANNON, x, y));
        					System.out.println("droppo torre 3");
        				}
        				
        				else if(this.remove) {
        					ImageView imgt = new ImageView(grasslogo);
        					imgt.setFitWidth(buttonSize);
        					imgt.setFitHeight(buttonSize);
        					bt.getChildren().setAll(imgt);
        					
        					gc.handleInput(new InputImpl(InputType.REMOVE_TOWER, null, x, y));
        					System.out.println("rimuovo torre");
        				}
        				
        				for(GridButton n:this.btList) {  
        					n.setEffect(null);
        				}
        				this.type1 = false;
        				this.type2 = false;
        				this.type3 = false;
        				this.remove = false;
        			}
        		});
        		btList.add(bt);
            	grid.add(bt,x,y);
        	}
        }
        grid.setTranslateX(buttonSize);
        grid.setTranslateY(buttonSize);
        root.getChildren().addAll(grid,flow);
        
        tower1.setOnMouseClicked(event -> {
        	this.type1 = true;
        	for(GridButton b:this.btList) {
        		 DropShadow drop = new DropShadow(10, Color.WHITE); 
 	            drop.setInput(new Glow());
 	            b.setEffect(drop); 
        	}
        });
        
        tower2.setOnMouseClicked(event -> {
        	this.type2 = true;
        	for(GridButton b:this.btList) {
        		 DropShadow drop = new DropShadow(10, Color.WHITE);
 	            drop.setInput(new Glow());
 	            b.setEffect(drop); 
        	}
        });
        
        tower3.setOnMouseClicked(event -> {
        	this.type3 = true;
        	for(GridButton b:this.btList) {
        		 DropShadow drop = new DropShadow(10, Color.WHITE); 
 	            drop.setInput(new Glow());
 	            b.setEffect(drop); 
        	}
        });
        
        remove.setOnMouseClicked(event -> {
        	this.remove = true;
        	for(GridButton b:this.btList) {
        		 DropShadow drop = new DropShadow(10, Color.WHITE); 
 	            drop.setInput(new Glow());
 	            b.setEffect(drop); 
        	}
        });
        
        start.setOnMouseClicked(event -> {
        	gc.handleInput(new InputImpl(InputType.START_WAVE,null,1,2));
        	this.gc.getModel().nextWave();
        });

        return root;
	}
	
	public GameScreen(GameController gc) {

		this.gc = gc;
		this.mappa = gc.getModel().getMap().getTileList();
		gc.startLoop(this);

	}
	
	
	public void render(ArrayList<Entity> entityList)  {	
		ArrayList<Enemy> p = new ArrayList<Enemy>();
		ArrayList<Projectile> j = new ArrayList<Projectile>();
		ArrayList<Tower> t = new ArrayList<Tower>();
		Platform.runLater(() -> {
		
			for(Entity e:entityList) {
				if(e instanceof Tower) {
					t.add((Tower) e);
				}
				if(e instanceof Enemy) {
					p.add((Enemy) e);
					for(PathButton b:btlist2) {
//						Rectangle r = new Rectangle();
//						r.setFill(Color.RED);
//						r.setHeight(buttonSize);
//						r.setWidth(buttonSize);
						
						ImageView r = new ImageView(enemy);
						r.setFitWidth(buttonSize);
						r.setFitHeight(buttonSize);
						
						ImageView img = new ImageView(logo);
						img.setFitWidth(buttonSize);
						img.setFitHeight(buttonSize);
						
						if(e.getLocation().equals(b.getPosition())) {
							b.getChildren().add(r);
							
						}
						else {
							b.getChildren().setAll(img);
							for(Enemy q:p) {
								if(q.getLocation().equals(b.getPosition())){
									b.getChildren().add(r);
								}
							}
						}
					}			
				}
				if(e instanceof Projectile) {
					j.add((Projectile) e);
					for(GridButton b:btList) {
						
						ImageView img = new ImageView(emerald);
						img.setFitWidth(buttonSize);
						img.setFitHeight(buttonSize);					
						if(e.getLocation().getX() == b.position.getX() && e.getLocation().getY() == b.position.getY()) {
							b.getChildren().add(img);			
						}	
						else {
							ImageView img1 = new ImageView(grasslogo);
							img1.setFitWidth(buttonSize);
							img1.setFitHeight(buttonSize);
							
							ImageView img3= new ImageView(towerlogo);
							img3.setFitWidth(buttonSize);
							img3.setFitHeight(buttonSize);						
							b.getChildren().setAll(img1);
							
							for(Tower v:t) {
								if(v.getLocation().equals(b.getPosition())){								
									if(v.getType() == TowerType.BASIC) {
										b.getChildren().add(img3);
									}
									if(v.getType() == TowerType.CANNON) {
										ImageView img4 = new ImageView(towerlogo2);
										img4.setFitWidth(buttonSize);
										img4.setFitHeight(buttonSize);
										b.getChildren().add(img4);
									}									
								}
							}
							for(Projectile q:j) {
								if(q.getLocation().equals(b.getPosition())){
								
									b.getChildren().add(img);
								}
							}
						}
					}					
				}
				}
			if (entityList.isEmpty() || (entityList.get(entityList.size()-1) instanceof Tower)) {
				for(PathButton b:btlist2) {
					ImageView img = new ImageView(logo);
					img.setFitWidth(buttonSize);
					img.setFitHeight(buttonSize);
					b.getChildren().setAll(img);
				}

				for(GridButton d:btList) {
					for(Tower v:t) {
						if(v.getLocation().equals(d.getPosition())){
							ImageView img2 = new ImageView(towerlogo);
							img2.setFitWidth(buttonSize);
							img2.setFitHeight(buttonSize);
							
							ImageView img3 = new ImageView(towerlogo2);
							img3.setFitWidth(buttonSize);
							img3.setFitHeight(buttonSize);
							
							ImageView img5 = new ImageView(towerlogo1);
							img5.setFitWidth(buttonSize);
							img5.setFitHeight(buttonSize);
							
							ImageView img4 = new ImageView(grasslogo);
							img4.setFitWidth(buttonSize);
							img4.setFitHeight(buttonSize);
							d.getChildren().setAll(img4);					
							if(v.getType() == TowerType.BASIC) {
								d.getChildren().add(img2);
							}
							if(v.getType() == TowerType.CANNON) {
								d.getChildren().add(img3);
							}
							if(v.getType() == TowerType.RANGED) {
								d.getChildren().add(img5);
							}
						}
					}
				}
				
			}
		text.setText("COINS"+"  " + this.gc.getModel().getPlayer().getCoins());
		text1.setText("HP"+"  " + this.gc.getModel().getPlayer().getHp());
		text2.setText("WAVE"+"  " + this.gc.getModel().getPlayer().getWave());
		text3.setText("NAME"+"  " + this.gc.getModel().getPlayer().getName());
		});
	}
}
