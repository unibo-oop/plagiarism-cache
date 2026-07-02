package view.mainmenu;

import java.io.File;
import java.io.IOException;
import constants.GameConstants;
import view.gameview.GameScreen;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu extends Application{
	
	private PlayerName name = new PlayerName();
	private Difficulty avviso = new Difficulty();;
	
    static final double buttonSize = GameConstants.buttonSize;
    static final double width = GameConstants.gameWidth;
    static final double height = GameConstants.gameHeight;
    private double vol;
    
	
	 public Parent createContent() throws IOException {
		 
	        String musicFile = "res/Jojo.mp3";
			Media sound = new Media(new File(musicFile).toURI().toString());
			MediaPlayer mediaPlayer1 = new MediaPlayer(sound);
			this.vol=0;
			mediaPlayer1.setVolume(0.1);	//SISTEMA QUI IL VOLUME
			
			Pane root = new Pane();
			root.setPrefSize(width, height);
			
			LayoutImages im = new LayoutImages();
			
			root.getChildren().add(im.getImgv());
			
			/* Effetto sonoro per i bottoni */
			String musicFile1 = "res/ZA WARUDO.mp3";
			Media sound1 = new Media(new File(musicFile1).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound1);
			mediaPlayer.setVolume(1);
			mediaPlayer.setStopTime(Duration.seconds(1.5));
			
			
            VBox menu0 = new VBox(buttonSize);	// box bottoni start-options-exit
            menu0.setTranslateX(buttonSize*19.5-buttonSize*5); 
            menu0.setTranslateY(buttonSize*11.5-buttonSize*2);
            
            VBox menu1 = new VBox(buttonSize);	// box bottoni back-sound-difficoulty
            menu1.setTranslateX(buttonSize*19.5-buttonSize*5); 
            menu1.setTranslateY(buttonSize*11.5-buttonSize*2);
            
            VBox menu3 = new VBox(buttonSize);	//box bottoni back-easy-medium-hard
            menu3.setTranslateX(buttonSize*19.5-buttonSize*5); 
            menu3.setTranslateY(buttonSize*11.5-buttonSize*2);
            
            VBox menu8 = new VBox(buttonSize); //box bottoni volume low-medium-high
            menu8.setTranslateX(buttonSize*19.5-buttonSize*5); 
            menu8.setTranslateY(buttonSize*11.5-buttonSize*2);
            
            VBox menu4 = new VBox(buttonSize);	// sfondo giorno
            VBox menu2 = new VBox(buttonSize);	// logo prima schermata
            VBox menu5 = new VBox(buttonSize);	// rettangolo grigio per rendere bello tutti gli sfondi
            VBox menu6 = new VBox(buttonSize);	// sfondo jotaro
            VBox menu7 = new VBox(buttonSize); //sfondo sound

            
            Rectangle bg = new Rectangle(width+buttonSize,height+buttonSize);
            bg.setFill(Color.GREY);
            bg.setOpacity(0.4);
            
    		
            menu2.getChildren().add(im.getimgv1());
            menu4.getChildren().add(im.getImgv2());
            menu5.getChildren().add(bg);
            menu6.getChildren().add(im.getImgv3());
            menu7.getChildren().add(im.getImgv7());
            
            menu4.setTranslateX(buttonSize*30);
            menu6.setTranslateX(buttonSize*30);
            menu3.setTranslateX(buttonSize*30);
            menu7.setTranslateX(-buttonSize*2);
            menu7.setTranslateY(buttonSize*30);
            menu8.setTranslateY(buttonSize*30);
            
            VolumeButton soundback = new VolumeButton("Back");
            VolumeButton low = new VolumeButton("Low");
            VolumeButton medium = new VolumeButton("Medium");
            VolumeButton high = new VolumeButton("High");
            VolumeButton mute = new VolumeButton("Mute");
            
            low.setOnMouseClicked(event ->{
            	
            	mediaPlayer.setVolume(0.1);
            });
            
            medium.setOnMouseClicked(event ->{
            	
            	mediaPlayer.setVolume(0.5);
            });
            
            high.setOnMouseClicked(event ->{
            	
            	mediaPlayer.setVolume(1);
            });
            
            mute.setOnMouseClicked(event ->{
            	
            	mediaPlayer.setVolume(0);
            });
            
            soundback.setOnMouseClicked(event -> {
            	mediaPlayer.stop();
            	mediaPlayer.play();
                root.getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu7);
                tt.setToY(menu3.getTranslateY() + height);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToY(menu3.getTranslateY());
                
                TranslateTransition tt0 = new TranslateTransition(Duration.seconds(0.25), menu8);
                tt0.setToY(menu6.getTranslateY() - height);

                tt0.play();
                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    root.getChildren().remove(menu7);
                    root.getChildren().remove(menu8);
                });
            });
            
            MenuButton btnSound = new MenuButton("sound");
            btnSound.setOnMouseClicked(event -> {
            	mediaPlayer.stop();
            	mediaPlayer.play();
            	root.getChildren().remove(menu5);
            	root.getChildren().add(menu7);
            	root.getChildren().add(menu5);
                root.getChildren().add(menu8);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToY(menu1.getTranslateY() - height);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu7);
                tt1.setToY(menu1.getTranslateY()-height*0.439);
                
                TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.5), menu8);
                tt2.setToY(menu1.getTranslateY()-buttonSize*3);
                
                tt.play();
                tt2.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    root.getChildren().remove(menu1); 
                });
            	
            });
            MenuButton btnResume = new MenuButton("play");
            btnResume.setOnMouseClicked(event -> {
            	//gc.startGame();
            	//game = new GameScreen(gc);
            	try {
					root.getChildren().setAll(name.createContent());
				} catch (IOException e) {
					
					e.printStackTrace();
				}
            	mediaPlayer1.stop();
            });

            /* ANIMAZIONI BOTTONI */
            MenuButton btnOptions = new MenuButton("options");
            btnOptions.setOnMouseClicked(event -> {
            	mediaPlayer.stop();
            	mediaPlayer.play();
            	root.getChildren().remove(menu5);
            	root.getChildren().add(menu4);
            	root.getChildren().add(menu5);
                root.getChildren().add(menu1);
                
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - width);
                
                TranslateTransition t0 = new TranslateTransition(Duration.seconds(0.25), menu2);
                t0.setToX(menu2.getTranslateX() - width);
                
                TranslateTransition t4 = new TranslateTransition(Duration.seconds(0.5), menu4);
                t4.setToX(menu0.getTranslateX() - width*0.439);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                t0.play();
                t4.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    root.getChildren().remove(menu0);
                    root.getChildren().remove(menu2);
                });
            });

            MenuButton btnExit = new MenuButton("exit");
            btnExit.setOnMouseClicked(event -> {
                System.exit(0);
            });

            MenuButton btnBack = new MenuButton("back");
            btnBack.setOnMouseClicked(event -> {
            	mediaPlayer.stop();
            	mediaPlayer.play();
            	root.getChildren().add(menu2);
                root.getChildren().add(menu0);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() + width);
                
                TranslateTransition t4 = new TranslateTransition(Duration.seconds(0.25), menu4);
                t4.setToX(menu4.getTranslateX() + width);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(menu1.getTranslateX());
                
                TranslateTransition tt0 = new TranslateTransition(Duration.seconds(0.5), menu2);
                tt0.setToX(menu1.getTranslateX() - width/2.25);
                
                t4.play();
                tt.play();
                tt1.play();
                tt0.play();

                tt.setOnFinished(evt -> {
                   root.getChildren().remove(menu1);
                   root.getChildren().remove(menu4);
                });
            });
            
            MenuButton btnDiff = new MenuButton("difficulty");
            btnDiff.setOnMouseClicked(event -> {
            	mediaPlayer.stop();
            	mediaPlayer.play();
            	root.getChildren().add(menu6);
                root.getChildren().add(menu3);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() - width);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu3);
                tt1.setToX(menu1.getTranslateX());
                
                TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.5), menu6);
                tt2.setToX(menu1.getTranslateX() - width/2.265);

                tt.play();
                tt2.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    root.getChildren().remove(menu1);                 
                    
                });
            });
            
            MenuButton btnEasy = new MenuButton("easy");
            MenuButton btnMedium = new MenuButton("medium");
            MenuButton btnHard = new MenuButton("hard");
            MenuButton btnBack1 = new MenuButton("back");
            
            btnBack1.setOnMouseClicked(event -> {
            	mediaPlayer.stop();
            	mediaPlayer.play();
                root.getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu3);
                tt.setToX(menu3.getTranslateX() + width);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menu3.getTranslateX());
                
                TranslateTransition tt0 = new TranslateTransition(Duration.seconds(0.25), menu6);
                tt0.setToX(menu6.getTranslateX() + width);

                tt0.play();
                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    root.getChildren().remove(menu3);
                    root.getChildren().remove(menu6);
                });
            });
            

            btnEasy.setOnMouseClicked(event -> {
            	name.getgc().setDifficulty(1);
            	System.out.println("facile");
            	Stage stage = new Stage();
            	try {
					avviso.start(stage);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
            });

            btnMedium.setOnMouseClicked(event -> {
            	name.getgc().setDifficulty(2);
            	System.out.println("normale");
            	Stage stage = new Stage();
            	try {
					avviso.start(stage);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
            });
            
            btnHard.setOnMouseClicked(event -> {
            	name.getgc().setDifficulty(3);
            	System.out.println("difficile");
            	Stage stage = new Stage();
            	try {
					avviso.start(stage);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
            });
            
            menu8.getChildren().addAll(soundback,low,medium,high,mute);
            menu0.getChildren().addAll(btnResume, btnOptions, btnExit);
            menu1.getChildren().addAll(btnBack, btnSound, btnDiff);
            menu3.getChildren().addAll(btnBack1,btnEasy,btnMedium,btnHard);

            root.getChildren().addAll(menu5,menu2, menu0);
        
    
			
			
			mediaPlayer1.play();
	        return root;
	    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		 Scene scene = new Scene(createContent());
	        primaryStage.setTitle("Jojo Menu");
	        primaryStage.setScene(scene);
	        primaryStage.setResizable(false);
	        primaryStage.show();
		
	}
	
	public void setVol(double volume) {
		this.vol=volume;
	}
	

}
