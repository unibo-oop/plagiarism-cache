package launcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.scene.FXGLMenu;
import com.almasb.fxgl.scene.menu.MenuType;
import com.almasb.fxgl.util.Supplier;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import launcher.MowerApp;
import scoreboard.Scoreboard;


/**
 * this class creates MainMenu and GameMenu
 *
 * @author Daniele
 *
 *
 */

public class Menu extends FXGLMenu {



    private VBox vbox = new VBox(50);

    private Node menuBody;

    private static String profile;


    /**
    * constructor class
    *
    * @param app the GameApplication app we need to launch from menu
    * @param type the type of menu, main or game
    *
    */

    public Menu(GameApplication app, MenuType type) {

        super(app, type);



        menuBody = type == MenuType.MAIN_MENU

                ? createMenuBodyMainMenu()

                : createMenuBodyGameMenu();



        vbox.getChildren().addAll(new Pane(), new Pane());

        vbox.setTranslateX(50);

        vbox.setTranslateY(50);



        contentRoot.setTranslateX(280);

        contentRoot.setTranslateY(154);



        menuRoot.getChildren().add(vbox);

        contentRoot.getChildren().add(EMPTY);



        vbox.getChildren().set(0, makeMenuBar());



        activeProperty().addListener((observable, wasActive, isActive) -> {

            if (!isActive) {

                // the scene is no longer active so reset everything

                // so that next time scene is active everything is loaded properly

                switchMenuTo(menuBody);

                switchMenuContentTo(EMPTY);

            }

        });

    }

    /**
    *
    * @param width the width of background's texture
    * @param height the height of background's texture
    * @return the label lableWeed
    *
    */

    @Override

    protected Node createBackground(double width, double height) {

        return FXGL.getAssetLoader().loadTexture("menu.png");

    }

    /**
    * @param title app's name
    * @return the text for the title
    *
    */

    @Override

    protected Node createTitleView(String title) {

        Text titleView = FXGL.getUIFactory().newText(app.getSettings().getTitle(), 60);

        titleView.setTranslateY(50);
        titleView.setTranslateX(400);
        titleView.setFill(Color.WHITE);

        return titleView;

    }

    /**
    *
    * @param version app's version
    * @return the text for the version
    *
    */

    @Override

    protected Node createVersionView(String version) {

        Text view = FXGL.getUIFactory().newText(version, 16);

        view.setTranslateX(app.getWidth() - view.getLayoutBounds().getWidth());

        view.setTranslateY(20);

        view.setVisible(false);

        return view;

    }

    /**
    *
    * @return the profile's name
    *
    */

    public static String getProfileName(){

    	return profile;

    }

    /**
    *
    * @param profileName profile's name
    * @return the text for profiles's name
    *
    */

    @Override

    protected Node createProfileView(String profileName) {

    	profile=profileName;
        Text view = FXGL.getUIFactory().newText(profileName, 24);

        view.setTranslateX(50);

        view.setTranslateY(35);

        view.setVisible(true);

        return view;

    }

    /**
    *
    * set the view of menuBox
    *
    * @param menuBox selected menuBox for switch
    *
    */

    @Override

    protected void switchMenuTo(Node menuBox) {

        vbox.getChildren().set(1, menuBox);

    }


    /**
    *
    * set the view of menuContent
    *
    * @param content selected views to be placed it in menuContent
    *
    */

    @Override

    protected void switchMenuContentTo(Node content) {

        contentRoot.getChildren().set(0, content);

    }


    /**
    *
    * method for create main menu
    *
    * @return hBox that contains Menu
    *
    */

    private HBox makeMenuBar() {

        ToggleButton tb1 = new ToggleButton("MAIN MENU",FXGL.getAssetLoader().loadTexture("menuimage.png"));

        ToggleButton tb2 = new ToggleButton("OPTIONS", FXGL.getAssetLoader().loadTexture("option.png"));


        tb1.setText("menu");
        tb2.setText("options");
        tb1.setTextFill(Color.WHITE);
        tb2.setTextFill(Color.WHITE);


        tb1.setFont(FXGL.getUIFactory().newFont(25));

        tb2.setFont(FXGL.getUIFactory().newFont(25));
        tb1.setStyle("-fx-base: #32CD32;");
        tb2.setStyle("-fx-base: #32CD32;");

        ToggleGroup group = new ToggleGroup();

        tb1.setToggleGroup(group);

        tb2.setToggleGroup(group);

        tb1.setUserData(menuBody);

        tb2.setUserData(makeOptionsMenu());

        tb1.setOnAction(e -> switchMenuContentTo(EMPTY));
        tb2.setOnAction(e -> switchMenuContentTo(EMPTY));


        group.selectedToggleProperty().addListener((obs, old, newToggle) -> {

            if (newToggle == null) {

                group.selectToggle(old);

                return;

            }

            switchMenuTo((Node)newToggle.getUserData());

        });

        group.selectToggle(tb1);

        HBox hbox = new HBox(10, tb1, tb2);

        hbox.setAlignment(Pos.TOP_CENTER);

        return hbox;

    }


    /**
    *
    * set the view of Menu toogleBar
    *
    * @return VBox that contains menu buttons
    *
    */

    private VBox createMenuBodyMainMenu() {



        Button btnNew = createActionButton("go!", this::fireNewGame);
        Button btnGamemode = createContentButton("select mode", () -> new MenuContent(createGamemodeMenu()));
        Button btnScore = createContentButton("scoreboard CM", () -> new MenuContent(createScoreMenu()));
        Button btnScoreHard = createContentButton("scoreboard HM", () -> new MenuContent(createScoreHardMenu()));
        Button btnExit = createActionButton("exit", this::fireExit);


        return new VBox(10, btnNew, btnGamemode, btnScore, btnScoreHard, btnExit);

    }


    /**
    *
    * create the view of classic scoreboard
    *
    * @return VBox that contains classic scoreboard
    *
    */

    private VBox createScoreMenu() {


    	Gson gson = new Gson();
    	String scr = null;
		try {

		   BufferedReader br = new BufferedReader(
		     new FileReader("score.json"));
		   //convert the json string back to object

		   Type ScoreboardListType = new TypeToken<ArrayList<Scoreboard>>(){}.getType();
		   List<Scoreboard> scoreboardlist = gson.fromJson(br, ScoreboardListType);
		   for(Scoreboard s : scoreboardlist){

			   if (scr==null){
				   scr = s.getName()+"       "+s.getHighScore();
			   }else{
				   scr = scr+"\n"+s.getName()+"       "+s.getHighScore();
			   }

		   }

		} catch (IOException e) {
		   e.printStackTrace();
		}

		Text text = new Text(scr);
		text.setFont(Font.loadFont("file:src/assets/ui/fonts/sewer.ttf", 17));
		text.setFill(Color.WHITE);

		Text text2 = new Text("TOP10 scores classic");
		text2.setFont(Font.loadFont("file:src/assets/ui/fonts/sewer.ttf", 17));
		text2.setFill(Color.WHITE);

        return new VBox(10, text2, text);

    }


    /**
    *
    * create the view of hard scoreboard
    *
    * @return VBox that contains hard scoreboard
    *
    */

    private VBox createScoreHardMenu() {


    	Gson gson = new Gson();
    	String scr = null;
		try {

		   BufferedReader br = new BufferedReader(
		     new FileReader("scoreHard.json"));
		   //convert the json string back to object

		   Type ScoreboardListType = new TypeToken<ArrayList<Scoreboard>>(){}.getType();
		   List<Scoreboard> scoreboardlist = gson.fromJson(br, ScoreboardListType);
		   for(Scoreboard s : scoreboardlist){

			   if (scr==null){
				   scr = s.getName()+"       "+s.getHighScore();
			   }else{
				   scr = scr+"\n"+s.getName()+"       "+s.getHighScore();
			   }

		   }

		} catch (IOException e) {
		   e.printStackTrace();
		}

		Text text = new Text(scr);
		text.setFont(Font.loadFont("file:src/assets/ui/fonts/sewer.ttf", 17));
		text.setFill(Color.LIGHTGREEN);

		Text text2 = new Text("TOP10 scores hard");
		text2.setFont(Font.loadFont("file:src/assets/ui/fonts/sewer.ttf", 17));
		text2.setFill(Color.LIGHTGREEN);

        return new VBox(10, text2, text);

    }

    /**
    *
    * create the VBox with selection of game modes
    *
    * @return VBox that contains buttons for game mode selection
    *
    */

    private VBox createGamemodeMenu() {
    	Button btnClassic = FXGL.getUIFactory().newButton("classic mode");
    	btnClassic.setOnAction(e -> MowerApp.gameMode=false);

        Button btnHard = FXGL.getUIFactory().newButton("hard mode");
        btnHard.setOnAction(e -> MowerApp.gameMode=true);

        return new VBox(10, btnClassic, btnHard);

    }

    /**
    *
    * create the view of in-game menu
    *
    * @return VBox that contains in-game menu
    *
    */

    private VBox createMenuBodyGameMenu() {

        Button btnResume = createActionButton("resume", this::fireResume);

        Button btnExit = createActionButton("exit", this::fireExitToMainMenu);



        return new VBox(10, btnResume, btnExit);

    }

    /**
    *
    * create the view of options toogleBar
    *
    * @return VBox that contains options' buttons
    *
    */

    private VBox makeOptionsMenu() {



        Button btnControls = createContentButton("controls", this::createContentControls);

        Button btnAudio = createContentButton("audio", this::createContentAudio);



        return new VBox(10, btnControls, btnAudio);

    }



    /**

     * Creates a new button with given name that performs given action on click/press.

     *

     * @param name  button name

     * @param action button action

     * @return new button

     */

    protected final Button createActionButton(String name, Runnable action) {

        Button btn = FXGL.getUIFactory().newButton(name);

        btn.setOnAction(e -> action.run());

        return btn;

    }


    /**

     * Creates a new button with given name that sets given content on click/press.

     *

     * @param name  button name

     * @param contentSupplier content supplier

     * @return new button

     */

    @SuppressWarnings("unchecked")

    protected final Button createContentButton(String name, Supplier<MenuContent> contentSupplier) {

        Button btn = FXGL.getUIFactory().newButton(name);

        btn.setUserData(contentSupplier);

        btn.setOnAction(e -> switchMenuContentTo(((Supplier<MenuContent>)btn.getUserData()).get()));

        return btn;

    }

}
