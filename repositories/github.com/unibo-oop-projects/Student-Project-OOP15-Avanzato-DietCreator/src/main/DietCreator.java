package main;

import controller.MainController;
import controller.MainControllerImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MenuScene;

/**
 * La classe DietCreator implementa il metodo main
 * 
 */

public class DietCreator extends Application{
	
	public final static String TITLE = "Diet Creator";
	public final static String REFMENU = "REFRESH MENU";
	public final static String REFPROFILES = "REFRESH PROFILES";
	public final static String REFDIETS = "REFRESH DIETS";
	public final static String REFRESHFOODS = "REFRESH FOODS";
	public final static String MENU = "MENU";
	public final static String PROFILES = "PROFILES";
	public final static String DIETS = "DIETS";
	public final static String FOODS = "FOODS";
	public final static String ADDPROFILE = "ADD PROFILE";
	public final static String ADDFOOD = "ADD FOOD";
	public final static String ADDDIET = "ADD DIET";
	public final static String ADDMEAL = "ADD MEAL";
	public final static String INSERTFOOD = "INSERT FOOD";
	public final static String SETDIETNAME = "SET DIET NAME";
	public final static String SELECTPROFILE = "SELECT PROFILE";
	public final static String EDITPROFILE = "EDIT PROFILE";
	public final static String EDITFOOD = "EDIT FOOD";
	public final static String EDITDIET = "EDIT DIET";
	public final static String VIEWDIET = "VIEW DIET";
	public final static String DELETEMEAL = "DELETE MEAL";
	public final static String DELETEFOOD = "DELETE FOOD";
	public final static String DELETEPROFILE = "DELETE PROFILE";
	public final static String DELETEDIET = "DELETE DIET";
	public final static String MODIFYDIET = "MODIFY DIET";
	public final static String HELP = "HELP";
	public final static int WIDTH = 450;
	public final static int HEIGHT = 580;
	
	//images
	public final static String TITLEIMG = "/dietCreatorTitle.jpg";
	public final static String ECTOIMG = "/ecto.jpg";
	public final static String MESOIMG = "/meso.jpg";
	public final static String ENDOIMG = "/endo.jpg";

	//targets
	public final static String GAIN = "GAIN";
	public final static String MAINTAIN = "MAINTAIN";
	public final static String LOSE = "LOSE";
	
	//generic
	public final static String PROFILENAME = "profile name";
	public final static String DIETNAME = "diet name";
	public final static String FOODNAME = "food name";
	public final static String TAKEN = " already taken";
	
	//file path
	public final static String PROFILESFILE = "profiles.bin";
	public final static String FOODSFILE = "foods.bin";
	
	private MainController controller;
	
	private Stage stage;
	private Scene menu;
	
	public static void main(String[] args){
		launch(args);
	}
	
	/**
	 * Questo metodo viene chiamato subito ed inizializza lo stage principale 
	 * e il controller dell'applicazione
	 * 
	 * @throws
	 *     eccezioni lanciate in caso di errori nel caricamento dei dati
	 * 
	 */
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		this.stage.setTitle(TITLE);
		this.controller = new MainControllerImpl(stage);
		this.menu = new MenuScene(controller, null, WIDTH, HEIGHT);
		this.stage.setScene(menu);
		this.stage.setResizable(false);
		this.stage.show();
	}
	
}
