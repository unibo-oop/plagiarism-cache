package controller;


import controller.MainControllerImpl;
import javafx.stage.Stage;
import main.DietCreator;
import model.Meal;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import model.DCList;
import model.Diet;
import model.FoodOnDiet;
import model.FoodValues;
import model.Profile;
import view.DietsScene;
import view.EditDietStage;
import view.EditFoodValuesStage;
import view.EditProfileStage;
import view.FoodValuesScene;
import view.HelpStage;
import view.MenuScene;
import view.ModifyDietStage;
import view.AddDietStage;
import view.AddFoodOnDietStage;
import view.AddFoodValuesStage;
import view.AddMealStage;
import view.AddProfileStage;
import view.DeleteDietStage;
import view.DeleteFoodValuesStage;
import view.DeleteProfileStage;
import view.ProfilesScene;
import view.SelectProfileStage;
import view.ViewDietStage;

/**
 * La classe MainControllerImpl implementa l'interfaccia MainController
 * 
 * 
 */

public class MainControllerImpl implements MainController{
	
	private Stage stage;
	private Alert alert;
	private Optional<Profile> currentProfile;
	private String currentProfileName;
	private ResourceManager rM;

	private DCList<Profile> pList;
	private DCList<FoodValues> fVList;
	private DCList<Meal> mList;
	private DCList<FoodOnDiet> fODList;
	
	private double targetKcals;
	private double minCarbs;
	private double maxCarbs;
	private double minFats;
	private double maxFats;
	private double minProts;
	private double maxProts;
	private String target;
	private Diet newDiet;
	private int mealCounter;
	
	/**
     * Il costruttore carica tutti i dati(se presenti) e inizializza le liste
     * 
     * @param stage
     *      viene passato come parametro lo stage principale
     *      
     * @throws 
     *      lancia eccezione in caso di errori nel salvataggio o caricamento dei dati
     */
	public MainControllerImpl(Stage stage) throws Exception{
		this.stage = stage;
		this.rM = new ResourceManagerImpl();
		this.pList = new DCList<>();
		this.fVList = new DCList<>();
		this.currentProfileName = "";
		try {
			this.loadProfiles();
			this.loadFoodValues();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changeScene(String name) throws Exception {
		switch (name){
			case DietCreator.PROFILES:
				stage.setScene(new ProfilesScene(this, null, DietCreator.WIDTH, DietCreator.HEIGHT));
				break;
			case DietCreator.ADDPROFILE:
				new AddProfileStage(this);
				break;
			case DietCreator.SELECTPROFILE:
				new SelectProfileStage(this);
				break;
			case DietCreator.EDITPROFILE:
			    if(this.currentProfileName.equals("")){
                    this.buildAlert("You have to select a profile first!");
                } else {
                    new EditProfileStage(this);
                }
				break;
			case DietCreator.DIETS:
				if(this.currentProfileName.equals("")){
					this.buildAlert("You have to select a profile first!");
				} else {
					stage.setScene(new DietsScene(this, null, DietCreator.WIDTH, DietCreator.HEIGHT));
				}
				break;
			case DietCreator.ADDDIET:
				new AddDietStage(this);
				break;
			case DietCreator.EDITDIET:
				new EditDietStage(this);
				break;
			case DietCreator.VIEWDIET:
				new ViewDietStage(this);
				break;
			case DietCreator.ADDMEAL:
				this.fODList.clear();
				new AddMealStage(this);
				break;
			case DietCreator.DELETEMEAL:
			    if(this.buildConfirmDialog()){
			        this.deleteMeal();
			        break;
			    }
				break;
			case DietCreator.INSERTFOOD:
				new AddFoodOnDietStage(this);
				break;
			case DietCreator.FOODS:
				stage.setScene(new FoodValuesScene(this, null, DietCreator.WIDTH, DietCreator.HEIGHT));
				break;
			case DietCreator.ADDFOOD:
				new AddFoodValuesStage(this);
				break;
			case DietCreator.EDITFOOD:
				new EditFoodValuesStage(this);
				break;
			case DietCreator.DELETEFOOD:
				new DeleteFoodValuesStage(this);
				break;
			case DietCreator.DELETEPROFILE:
				new DeleteProfileStage(this);
				break;
			case DietCreator.MODIFYDIET:
				new ModifyDietStage(this);
				break;
			case DietCreator.DELETEDIET:
				new DeleteDietStage(this);
				break;
			case DietCreator.MENU:
				stage.setScene(new MenuScene(this, null, DietCreator.WIDTH, DietCreator.HEIGHT ));
				break;
			case DietCreator.HELP:
				new HelpStage(this);
				break;
			default:
				break;
		}		
	}

	@Override
    public void loadProfiles() throws Exception {
        this.pList = (DCList<Profile>) rM.loadProfiles(DietCreator.PROFILESFILE);
    }
	
    @Override
    public void loadFoodValues() throws Exception {
        this.fVList = (DCList<FoodValues>) rM.loadFoodValues(DietCreator.FOODSFILE);
    }
	
	@Override
	public Profile getCurrentProfile() {
		if(currentProfile.isPresent()){
			return this.currentProfile.get();
 		}
		else{
			System.out.println("No selected profile!");
			return null;
		}
	}
	
	@Override
	public String getCurrentProfileName(){
		return this.currentProfileName;
	}

	@Override
	public void setCurrentProfile(String name) {
		this.currentProfile = this.pList.stream().filter(p -> p.getName().equals(name)).findFirst();
		this.currentProfileName = name;
	}

	@Override
	public void saveProfile(Profile profile) throws Exception {
		if(this.pList.stream().anyMatch(p -> p.getName().equals(profile.getName()))){
			this.buildAlert("Profile already saved!");
		} else {
			this.pList.add(profile);
			this.rM.save(pList, DietCreator.PROFILESFILE);
		}
	}
	
	@Override
	public void deleteProfile(String name) throws Exception{
		Optional<Profile> profileToDelete = this.pList.stream().filter(p -> p.getName().equals(name)).findFirst();
		if(this.currentProfileName.equals(name)){
			this.buildAlert("You cannot delete the profile you're using!");
		} else {
			this.pList.remove(profileToDelete.get());
			this.rM.save(pList, DietCreator.PROFILESFILE);
		}
	}
	
    @Override
    public void editProfile(Profile profile) throws Exception {
        this.pList.set(this.pList.indexOf(currentProfile.get()), profile);
        this.currentProfile = Optional.of(profile);
        this.rM.save(pList, DietCreator.PROFILESFILE);
    }	
	
	@Override
    public void saveFoodValues(FoodValues food) throws Exception {
        if(this.fVList.stream().anyMatch(fv -> fv.getName().equals(food.getName()))){
            this.buildAlert("Food already saved!");
        } else {
            this.fVList.add(food);
            this.rM.save(fVList, DietCreator.FOODSFILE);
        }       
    }

    @Override
    public void deleteFoodValues(String name) throws Exception {
        Optional<FoodValues> foodVToDelete = this.fVList.stream().filter(fv -> fv.getName().equals(name)).findFirst();
        this.fVList.remove(foodVToDelete.get());
        this.rM.save(fVList, DietCreator.FOODSFILE);
    }

	@Override
	public void editFoodValues(Optional<FoodValues> oldFood, FoodValues food) throws Exception {
		this.fVList.set(this.fVList.indexOf(oldFood.get()), food);
		this.rM.save(fVList, DietCreator.FOODSFILE);
	}

	@Override
	public DCList<Profile> getPList() {
		return this.pList;
	}

	@Override
	public DCList<FoodValues> getFVList() {
		return this.fVList;
	}

	@Override
	public void dietTargetKcals(String target, double kcals) {
		if(target.equals(DietCreator.LOSE)){
			this.target = DietCreator.LOSE;
			this.targetKcals = this.currentProfile.get().getMaintainingKCals() - kcals;
		}
		if(target.equals(DietCreator.MAINTAIN)){
			this.target = DietCreator.MAINTAIN;
			this.targetKcals = this.currentProfile.get().getMaintainingKCals();
		}
		if(target.equals(DietCreator.GAIN)){
			this.target = DietCreator.GAIN;
			this.targetKcals = this.currentProfile.get().getMaintainingKCals() + kcals;
		}	
		this.buildDietMinMaxValues(target);
	}
	
	/**
     * Questo metodo privato serve per il calcolo dei valori min e max dei tre macronutrienti
     * come soglia giornaliera, calcolandoli in base alle kcal target giornaliere e il somatotipo
     * del profilo corrente.
     * 
     * @param target
     *      viene passato l'obiettivo voluto
     */
	private void buildDietMinMaxValues(String target){
		if(target.equals(DietCreator.LOSE)){
			this.minCarbs = ((this.currentProfile.get().getSomatotype().getLoseMinCarbs() * this.targetKcals) / 100) / 4;
			this.maxCarbs = ((this.currentProfile.get().getSomatotype().getLoseMaxCarbs() * this.targetKcals) / 100) / 4;
			this.minFats = ((this.currentProfile.get().getSomatotype().getLoseMinFats() * this.targetKcals) / 100) / 9;
			this.maxFats = ((this.currentProfile.get().getSomatotype().getLoseMaxFats() * this.targetKcals) / 100) / 9;
			this.minProts = ((this.currentProfile.get().getSomatotype().getLoseMinProts() * this.targetKcals) / 100) / 4;
			this.maxProts = ((this.currentProfile.get().getSomatotype().getLoseMaxProts() * this.targetKcals) / 100) / 4;
		}
		if(target.equals(DietCreator.MAINTAIN)){
			this.minCarbs = ((this.currentProfile.get().getSomatotype().getMaintainMinCarbs() * this.targetKcals) / 100) / 4;
			this.maxCarbs = ((this.currentProfile.get().getSomatotype().getMaintainMaxCarbs() * this.targetKcals) / 100) / 4;
			this.minFats = ((this.currentProfile.get().getSomatotype().getMaintainMinFats() * this.targetKcals) / 100) / 9;
			this.maxFats = ((this.currentProfile.get().getSomatotype().getMaintainMaxFats() * this.targetKcals) / 100) / 9;
			this.minProts = ((this.currentProfile.get().getSomatotype().getMaintainMinProts() * this.targetKcals) / 100) / 4;
			this.maxProts = ((this.currentProfile.get().getSomatotype().getMaintainMaxProts() * this.targetKcals) / 100) / 4;		
		}
		if(target.equals(DietCreator.GAIN)){
			this.minCarbs = ((this.currentProfile.get().getSomatotype().getGainMinCarbs() * this.targetKcals) / 100) / 4;
			this.maxCarbs = ((this.currentProfile.get().getSomatotype().getGainMaxCarbs() * this.targetKcals) / 100) / 4;
			this.minFats = ((this.currentProfile.get().getSomatotype().getGainMinFats() * this.targetKcals) / 100) / 9;
			this.maxFats = ((this.currentProfile.get().getSomatotype().getGainMaxFats() * this.targetKcals) / 100) / 9;
			this.minProts = ((this.currentProfile.get().getSomatotype().getGainMinProts() * this.targetKcals) / 100) / 4;
			this.maxProts = ((this.currentProfile.get().getSomatotype().getGainMaxProts() * this.targetKcals) / 100) / 4;		
		}
	}
   
    @Override
    public void deleteDiet(Diet diet) throws Exception{
        this.getCurrentProfile().getDiets().remove(diet);
        this.editProfile(this.currentProfile.get());
    }
    
    @Override
    public void modifyDiet(Diet oldDiet, Diet modDiet) throws Exception{
        this.getCurrentProfile().getDiets().remove(oldDiet);
        this.getCurrentProfile().getDiets().add(modDiet);
        this.editProfile(this.currentProfile.get());
    }
    
	@Override
	public void addDiet() {
		this.newDiet = new Diet("", this.target, this.getCurrentProfile().getWeight());
		this.mList = new DCList<>();
		this.fODList = new DCList<>();
		this.mealCounter = 1;
	}

	@Override
	public void addMeal() {
		Meal meal = new Meal(mealCounter, fODList);
		this.mList.add(meal);
		this.mealCounter++;
		this.fODList.clear();
		this.newDiet.setMealList(mList);
	}
	
	@Override
	public void deleteMeal(){
		mList.remove(mList.size()-1);
		mealCounter--;
		this.newDiet.setMealList(mList);
	}

    @Override
    public Diet getNewDiet(){
        return this.newDiet;
    }
    
	@Override
	public DCList<Meal> getMList() {
		return this.mList;
	}

	@Override
	public DCList<FoodOnDiet> getFODList() {
		return this.fODList;
	}
	
	/**
     * getter dei valori minimi di carboidrati precedentemente calcolati
     * 
     * @return
     *      il valore minCarbs
     */
	public double getMinCarbs() {
		return minCarbs;
	}
	
	/**
     * getter dei valori massimi di carboidrati precedentemente calcolati
     * 
     * @return
     *      il valore maxCarbs
     */
	public double getMaxCarbs() {
		return maxCarbs;
	}
	
	/**
     * getter dei valori minimi di grassi precedentemente calcolati
     * 
     * @return
     *      il valore minFats
     */
	public double getMinFats() {
		return minFats;
	}
	
	/**
     * getter dei valori massimi di grassi precedentemente calcolati
     * 
     * @return
     *      il valore maxFats
     */
	public double getMaxFats() {
		return maxFats;
	}
	
	/**
     * getter dei valori minimi di proteine precedentemente calcolati
     * 
     * @return
     *      il valore minProts
     */
	public double getMinProts() {
		return minProts;
	}
	
	/**
     * getter dei valori massimi di proteine precedentemente calcolati
     * 
     * @return
     *      il valore maxProts
     */
	public double getMaxProts() {
		return maxProts;
	}
	
	/**
     * getter della soglia target di calorie della dieta che si sta creando
     * 
     * @return
     *      targetKcals
     */
	public double getTargetKcals(){
		return this.targetKcals;
	}

	@Override
	public void addFoodOnDiet(String name, int quantity) {
		Optional<FoodValues> food = this.fVList.stream().filter(fv -> fv.getName().equals(name)).findFirst();
		FoodOnDiet fOD = new FoodOnDiet(food.get(), quantity);
		this.fODList.add(fOD);
	}

	@Override
	public void saveDiet() throws Exception {
		this.newDiet.setMealList(this.mList);
		this.currentProfile.get().addDiet(newDiet);
		this.editProfile(this.currentProfile.get());
	}
	
	@Override
	public void buildAlert(String text){
		this.alert = new Alert(AlertType.INFORMATION);
		this.alert.initStyle(StageStyle.UTILITY);
		this.alert.setTitle("Info");
		this.alert.setHeaderText(null);
		this.alert.setContentText(text);
		this.alert.showAndWait();
	}

	@Override
	public boolean checkEmptyField(String text) {
		if(text.equals("")){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkIfPresent(String name, String type) {
		switch(type){
			case DietCreator.PROFILENAME:
				return this.pList.stream().anyMatch(p -> p.getName().equals(name));
			case DietCreator.DIETNAME:
				return this.currentProfile.get().getDiets().stream().anyMatch(d -> d.getName().equals(name));
			case DietCreator.FOODNAME:
				return this.fVList.stream().anyMatch(fv -> fv.getName().equals(name));
			default:
				return false;
		}
	}

	@Override
	public boolean buildConfirmDialog() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Are you sure?");
		alert.setHeaderText(null);
		alert.setContentText("The selected object will be completely deleted");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    return true;
		} else {
			return false;
		}
	}

    @Override
    public boolean checkValue(String text) {
        if(!text.matches("^[^A-Za-z]+$")){
          return true;  
        } else if(Double.parseDouble(text) < 0){
            return true;
        } else {
            return false;
        }
    }
	
}
