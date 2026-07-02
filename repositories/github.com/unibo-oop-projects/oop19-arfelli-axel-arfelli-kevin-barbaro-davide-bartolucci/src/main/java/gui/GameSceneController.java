package gui;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import entity.CollisionBox;
import entity.CollisionBoxInt;
import entity.UUIDActor;
import entity.UUIDEntity;
import entity.UUIDProjectile;
import gameengine.*;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import resourcemanager.ResourceManagerAlpha;
import virtualworld.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

public class GameSceneController implements Initializable {
	
	private GameEngine gameEngine = new GameEnginePrototype(10 ,new GameLoggerAdaptor());
	private Stage stage = new GenericStage();
	private Image imgDrone = ResourceManagerAlpha.getIstance().getImage("drone_64x64.png");
	private Image imgTurret = ResourceManagerAlpha.getIstance().getImage("turret_64x64.png");
	private Image imgMuncher = ResourceManagerAlpha.getIstance().getImage("muncher_64x64.png");
	private Image imgBoss = ResourceManagerAlpha.getIstance().getImage("boss_512x512.png");
	private Image imgPlayer = ResourceManagerAlpha.getIstance().getImage("player_64x64.png");
	private Image bullet = ResourceManagerAlpha.getIstance().getImage("bullet_64x64_8x8.png");
	private Image notfound = ResourceManagerAlpha.getIstance().getImage("not_found.png");
	private ImageView player = new ImageView(this.imgPlayer);
	private Map<UUIDProjectile, ImageView> projectiles = new HashMap<>();
	private Map<UUIDActor, ImageView> enemies = new HashMap<>();
	private Set<UUIDProjectile> bulletstoadd=Set.of();
	private Set<UUIDActor> actortoadd=Set.of();
	private Map<UUIDEntity, Rectangle> hitboxs = new HashMap<>();
	private AnimationTimer timer;
	private AnimationTimer timer2;
	
	@FXML
	private Pane pane = new Pane();
	
	@FXML
	private Label health = new Label();
	
	@FXML
	private Label ready = new Label();
	
	@FXML
	private StackPane sp = new StackPane();
	
	private Boolean isReady = false;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    this.pane.setPrefHeight(ResourceManagerAlpha.getIstance().getSettingsAsObject().getHeight());
	    this.pane.setPrefWidth(ResourceManagerAlpha.getIstance().getSettingsAsObject().getWidth());
	    this.sp.setPrefHeight(ResourceManagerAlpha.getIstance().getSettingsAsObject().getHeight());
	    this.sp.setPrefWidth(ResourceManagerAlpha.getIstance().getSettingsAsObject().getWidth());
	    this.health.setText("Health: " + Integer.toString(this.stage.getplayer().getLife()));
		timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				if(!stage.isEnded()) {
					update();
					stage.resume();
				}else {
					gameEngine.stop();
					postRun();
				}
				
			}
		};
		timer2 = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				if(!stage.isEnded()) {
					updateProjectiles();
				}
			}
		};
		
		this.pane.setOnMouseClicked(e -> {
			this.pane.requestFocus();
			this.ready.setText("");
			
			if(isReady==false) {
				/*Rectangle rect = new Rectangle(stage.getMap().getWidth(), stage.getMap().getHeigth());
				rect.setFill(new ImagePattern(this.notfound));
				rect.setVisible(true);
				this.pane.getChildren().add(rect);*/			
				
				this.isReady = true;
				this.gameEngine.start(this.stage);
				while(!this.stage.isReady());
				//this.pane.getChildren().add(this.player);
				this.player.setLayoutY(this.stage.getplayer().getBody().getCollisionBox().getY());
				preRun();
				timer.start();
				//timer2.start();
				new Thread(() ->  {
					while (!stage.isEnded()) {
						//addNewSpawned
						List<UUIDActor> tempa = new LinkedList<>();
			            for (Entry<UUIDActor, CollisionBox<Integer>> entry : this.stage.getMap().getActors().entrySet()) {
			                if (!this.enemies.containsKey(entry.getKey()) && entry.getKey().isAlive()) {
			                	tempa.add(entry.getKey());
			                }
			            }
			            actortoadd = Set.copyOf(tempa);
						//addNewSpawned
			            List<UUIDProjectile> tempb = new LinkedList<>();
					    for (Entry<UUIDProjectile, CollisionBox<Integer>> entry : this.stage.getMap().getProjectiles().entrySet()) {
					        if (!this.projectiles.containsKey(entry.getKey())&& entry.getKey().isAlive()) {
					            Platform.runLater(()->{
					                ImageView imgView = new ImageView(bullet);
					                this.pane.getChildren().add(imgView);
					                this.projectiles.put(entry.getKey(), imgView);
					            });
					        }
				        }
					    bulletstoadd = Set.copyOf(tempb);
					    try {
							Thread.sleep(1000/20);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }
				} ).start();
				//Thread t = new Thread(() -> this.run());
				//t.start();
			}
		});
	}
	
	private void preRun() {
		this.pane.setOnKeyPressed(e -> {
			if(e.getCode()==KeyCode.E) {
				this.shoot();
			}
		});
		
		this.pane.setOnMouseMoved(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				stage.getplayer().move((int)event.getSceneX(), stage.getplayer().getBody().getCollisionBox().getY());
			}
		});
	}
	
	private void update() {
		this.health.setText("Health: " + Integer.toString(this.stage.getplayer().getLife()));
		/*for(UUIDActor a : this.getActors()) {
			if(!a.isAlive()) {
				this.pane.getChildren().remove(enemies.get(a));
			}
		}*/
		//this.updateActors();
		this.stage.resume();
		this.updateHitbox();
	}
	
	private void postRun() {
		if(MainMenuController.getUsername()!=null) {
			ResourceManagerAlpha.getIstance().getLeaderboardAsObject().addRecord(new Pair<>(MainMenuController.getUsername().get(), this.stage.getScore()));
			ResourceManagerAlpha.getIstance().saveLeaderboard(ResourceManagerAlpha.getIstance().getLeaderboardAsObject());
		}
		timer.stop();
		javafx.stage.Stage stage = (javafx.stage.Stage) pane.getScene().getWindow();
		Utilities.load("MainMenu.fxml", stage);
	}
	
	public void run() {
		preRun();
		update();
		postRun();	
	}
	
	public void shoot() {
		this.stage.getplayer().fire();
	}
	
	private ImageView load(UUIDActor a) {
		CollisionBox<Integer> box = new CollisionBoxInt(a.getBody().getCollisionBox());
		ImageView image;
		switch (a.getType()) {
                    case "Drone":
                    	image = new ImageView(this.imgDrone);
                    	break;
                    case "Turret":
                        image = new ImageView(this.imgTurret);
                        break;
                    case "Muncher":
                    	image = new ImageView(this.imgMuncher);
                    	break;
                    case "Boss":
                    	image = new ImageView(this.imgBoss);
                    	break;
                    case "Player":
                    	image = new ImageView(this.imgPlayer);
                    	break;
                    default:
                    	image = new ImageView(this.notfound);
		}
		//image.setTranslateX(-(box.getWidth()));
		//image.setTranslateY(-(box.getHeight()));
		image.resize(box.getWidth(),box.getHeight());
		return image;
	}
	
	private Image load(UUIDEntity a,int i) {
            CollisionBox<Integer> box = new CollisionBoxInt(a.getBody().getCollisionBox());
            switch (a.getType()) {
                case "Drone":
                    return this.imgDrone;

                case "Turret":
                    return this.imgTurret;

                case "Muncher":
                    return this.imgMuncher;

                case "Boss":
                    return this.imgBoss;

                case "Player":
                    return this.imgPlayer;
                case "Generic-Projectile":
                    return this.bullet;

                default:
                    return this.notfound;
            }
    }
	
	/*private Rectangle load(UUIDEntity a) {
		CollisionBox<Integer> box = new CollisionBoxInt(a.getBody().getCollisionBox());
		Rectangle rect = new Rectangle(box.getWidth(),box.getHeight());
		rect.setFill(new ImagePattern(this.notfound));
		rect.setVisible(true);
		return rect;
	}*/
	
	private Rectangle load(UUIDEntity a) {
            CollisionBox<Integer> box = new CollisionBoxInt(a.getBody().getCollisionBox());
            Rectangle rect = new Rectangle(box.getWidth(),box.getHeight());
            rect.setFill(new ImagePattern(load(a, 0)));
            rect.setVisible(true);
            return rect;
    }
	
	
	private void updateHitbox() {
		//removeDeads
        Set<UUIDEntity> deads = Set.copyOf(hitboxs.keySet());
        synchronized (hitboxs) {
            for (UUIDEntity dead : deads) {
                    this.pane.getChildren().remove(this.hitboxs.remove(dead));
                }
        }
        
        //addNewSpawned         
        for (Entry<UUIDActor, CollisionBox<Integer>> entry : this.stage.getMap().getActors().entrySet()) {
            if (!this.hitboxs.containsKey(entry.getKey())) {
                Rectangle imgView = load((UUIDEntity)entry.getKey());
                this.pane.getChildren().add(imgView);
                this.hitboxs.put(entry.getKey(), imgView);                  
            }
        }
        for (Entry<UUIDProjectile, CollisionBox<Integer>> entry : this.stage.getMap().getProjectiles().entrySet()) {
            if (!this.hitboxs.containsKey(entry.getKey())) {
            	Rectangle imgView = load((UUIDEntity)entry.getKey());
                this.pane.getChildren().add(imgView);
                this.hitboxs.put(entry.getKey(), imgView);                  
            }
        }
        //updatePositions
        for (Entry<UUIDEntity,Rectangle> entry: this.hitboxs.entrySet()) {
            entry.getValue().relocate(entry.getKey().getBody().getCollisionBox().getX(),
                    stage.getMap().getHeigth()-entry.getKey().getBody().getCollisionBox().getY());
        }
	}
	
	private void updateActors() {
            //removeDeads
            Set<UUIDActor> deads = Set.copyOf(enemies.keySet());
            synchronized (deads) {
                for (UUIDActor dead : deads) {
                        this.pane.getChildren().remove(this.enemies.remove(dead));
                    }
            }
            
            //add new actors
            synchronized (actortoadd) {
            	actortoadd.forEach(x -> {
            		if (!this.hitboxs.containsKey(x)) {
                        ImageView imgView = load(x);
                        this.pane.getChildren().add(imgView);
                        this.enemies.put(x, imgView);                  
                    }
            	});
			}
            
            
            
            //updatePositions
            for (Entry<UUIDActor,ImageView> entry: this.enemies.entrySet()) {
                entry.getValue().relocate(entry.getKey().getBody().getCollisionBox().getX(),
                        stage.getMap().getHeigth()-entry.getKey().getBody().getCollisionBox().getY());
            }
        }
	
	
	
	private void deleteDeadProjectile() {
	  //removeDeads
	    Set<UUIDProjectile> deads = Set.copyOf(projectiles.keySet());
            synchronized (projectiles) {
                for (UUIDProjectile dead : deads) {
                        this.pane.getChildren().remove(this.projectiles.remove(dead));          
                    }
            }
	}
	
	private void addNewProjectiles() {
	    
            bulletstoadd.forEach(x -> {
                    if (!this.hitboxs.containsKey(x)) {
                    ImageView imgView = new ImageView(bullet);
                    this.pane.getChildren().add(imgView);
                    this.projectiles.put(x, imgView);                  
                }
            });
            
	}
	
	private void updateProjectiles() {
	    
	    Platform.runLater(()->deleteDeadProjectile());
	    //addNewProjectiles();
	    	    
	    
	    //updatePositions	    
	    for (Entry<UUIDProjectile,ImageView> entry: this.projectiles.entrySet()) {
                entry.getValue().relocate(entry.getKey().getBody().getCollisionBox().getX()-32,
                		stage.getMap().getHeigth()-entry.getKey().getBody().getCollisionBox().getY()+32);
	    }
	}

}
