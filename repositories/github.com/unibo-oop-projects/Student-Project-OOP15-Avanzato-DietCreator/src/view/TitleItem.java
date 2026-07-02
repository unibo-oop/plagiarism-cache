package view;

import controller.MainController;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.DietCreator;

/**
 * classe TitleItem, rappresenta l'oggetto titolo, con la visualizzazione dell'eventuale profilo corrente selezionato
 * offre la possibilità di cambiare il profilo corrente
 * estende VBox
 * 
 */
public class TitleItem extends VBox{
	
	private Text title;
	private Text selProfile;
	private StackPane titlePane;
	private StackPane profilePane;
	
	/**
     * costruttore
     * 
     * @param title
     *     il titolo
     *  
     * @param changeProfile
     *      se vi è la possibilità di cambiare profilo
     *      
     * @param controller
     *      il riferimento al controller
     */
	public TitleItem(String title, boolean changeProfile, MainController controller){
		
		this.title = new Text(title);
		this.title.setFill(Color.BLACK);
		this.title.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 30));
		
		this.selProfile = new Text("");
		this.selProfile.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 18));
		
		if(controller.getCurrentProfileName().equals("")){
			this.selProfile.setText("No selected profile");
			this.selProfile.setFill(Color.RED);
		} else {
			this.selProfile.setText("Profile: " + controller.getCurrentProfile().getName());
		}
		
		this.titlePane = new StackPane();
		this.titlePane.getChildren().add(this.title);
		this.profilePane = new StackPane();
		this.profilePane.getChildren().add(this.selProfile);
		
		this.getChildren().add(titlePane);
		this.getChildren().add(profilePane);
		if(changeProfile){
			this.getChildren().add(new SelProfileItem(controller));
		}
		
	}
	
	/**
     * classe innestata, rappresenta l'oggetto per la selezione del profilo
     * estende StackPane
     *  
     */
	private static class SelProfileItem extends StackPane {

		private Text text;
		
		/**
	     * costruttore
	     * 
	     * @param controller
	     *     riferimento al controller
	     *  
	     */
		public SelProfileItem(MainController controller){
			
			this.text = new Text("select profile");
			if(!controller.getCurrentProfileName().equals("")){
				this.text.setText("change profile");
			}
			this.text.setFill(Color.BLACK);
			this.text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 16));
			
			setOnMouseEntered(e -> text.setFill(Color.RED));
			
			setOnMouseExited(e -> text.setFill(Color.BLACK));
			
			setOnMousePressed(e -> {
				try {
					controller.changeScene(DietCreator.SELECTPROFILE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			
			this.getChildren().add(text);
		}
		
	}
	
}


