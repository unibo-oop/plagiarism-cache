package controller;

import java.util.Optional;
import model.DCList;
import model.Diet;
import model.FoodOnDiet;
import model.FoodValues;
import model.Meal;
import model.Profile;

/**
 * L'interfaccia MainController dichiara i metodi del controller
 * 
 * 
 */

public interface MainController {
	
    /**
     * Il metodo changeScene permette al controller di cambiare la scene dello stage principale
     * o di avviare nuovi stage. 
     * 
     * @param name
     *      il parametro stringa passato permette di selezionare l'opzione da seguire
     * 
     */
    public void changeScene(String name) throws Exception;
    
    /**
     * Il metodo getCurrentProfile permette è un getter dell'attuale profilo usato dall'utente
     * 
     * @return 
     *      il profilo corrente
     * 
     */
	public Profile getCurrentProfile();
	
	/**
     * Permette al controller di impostare il profilo corrente selezionandolo da quelli disponibili 
     * in base al nome fornito
     * 
     * @param name
     *      il nome del profilo da selezionare
     * 
     */
	public void setCurrentProfile(String name);
	
	/**
     * il metodo getCurrentProfile restituisce il nome del profilo corrente
     * 
     * @return
     *      il nome del profilo corrente
     * 
     */
	public String getCurrentProfileName();
	
	//Profiles section
	/**
     * Il metodo saveProfile permette al controller salvare un profilo e i relativi dati
     * 
     * @param profile
     *      viene passato il profilo da salvare
     * 
     */
	public void saveProfile(Profile profile) throws Exception;
	
	/**
     * Il metodo loadProfiles permette di caricare i profili salvati su un file 
     * 
     */
	public void loadProfiles() throws Exception;
	
	/**
     * Il metodo editProfile permette di sostituire un profilo modificato a quello originario
     * e di salvarlo 
     * 
     * @param profile
     *      viene passato il profilo modificato da salvare
     * 
     */
	public void editProfile(Profile profile) throws Exception;
	
	/**
     * Il metodo deleteProfile permette di eliminare un profilo a scelta 
     * 
     * @param name
     *      viene passato il nome del profilo da eliminare
     * 
     */
	public void deleteProfile(String name) throws Exception;
	
	/**
     * Il metodo getPList è un getter per la lista di profili
     * 
     * @return  
     *      ritorna la lista di profili 
     * 
     */
	public DCList<Profile> getPList();
	
	//Food values section
	/**
     * Il metodo saveFoodValues permette di salvare un cibo e i relativi valori nutrizionali 
     * 
     * @param food
     *      il parametro stringa passato è l'oggetto cibo da salvare
     * 
     */
	public void saveFoodValues(FoodValues food) throws Exception;	
	
	/**
     * Il metodo loadFoodValues carica l'eventuale lista di cibi salvati
     * 
     * @throws
     *      lancia eccezione nel caso non vi sia il file
     * 
     */
	public void loadFoodValues() throws Exception;
	
	/**
     * Il metodo editFoodValues permette di modificare un cibo e di salvare la sua versione modificata
     * al posto di quella originaria 
     * 
     * @param oldFood
     *      la versione originaria del cibo
     * 
     * @param oldFood
     *      la versione modificata del cibo
     *      
     * @throws
     *      lancia eccezione nel caso non sia trovato il file o sia impossibile salvare
     * 
     */
	public void editFoodValues(Optional<FoodValues> oldFood, FoodValues food) throws Exception;
	
	/**
     * Il metodo deleteFoodValues permette di eliminare un cibo dalla lista 
     * 
     * @param name
     *      il parametro stringa passato permette di selezionare il cibo da eliminare
     * 
     */
	public void deleteFoodValues(String name) throws Exception;
	
	/**
     * Getter della lista di cibi
     * 
     * @return 
     *      ritorna la lista di cibi
     * 
     */
	public DCList<FoodValues> getFVList();
	
	/**
     * Il metodo dietTargetKcals calcola l'obiettivo in termini di kcal giornaliere 
     * a seconda dell'obiettivo voluto. 
     * Inoltre calcola i valori dei macronutrienti minimi e massimi per raggiungere 
     * l'obietivo desiderato in una dieta. Il calcolo dei valori è effettuato in base 
     * al somatotipo del profilo corrente, l'obiettivo desiderato e la soglia di kcal 
     * fornita.
     * 
     * @param target
     *      il parametro target indica l'obiettivo
     * 
     * @param kcals
     *      il parametro kcals indica l'obiettivo in termini di kcals voluto dalla dieta
     * 
     */
	public void dietTargetKcals(String target, double kcals);
	
	/**
     * Il metodo addDiet permette di aggiungere una dieta alla lista di diete del profilo corrente
     * 
     */
	public void addDiet();  // create a new diet in controller
	
	/**
     * Il metodo savediet salva l'attuale lista di diete del profilo corrente,aggiornando poi il 
     * profilo. 
     * 
     * @throws
     *      lancia eccezione in caso di errori nel salvataggio o ritrovamento del file
     */
	public void saveDiet() throws Exception;
	
	/**
     * Il metodo deleteDiet consente di cancellare una data dieta dall'elenco diete del profilo corrente
     * e poi di aggiornarlo 
     * 
     * @param diet
     *      il parametro stringa passato permette di selezionare l'opzione da seguire
     * 
     * @throws
     *      lancia eccezione in caso di errore nel salvataggio o ritrovamento del file
     * 
     */
	public void deleteDiet(Diet diet) throws Exception;
	
	/**
     * Il metodo sostituisce la dieta modificata a quella non modificata e la salva
     * 
     * @param oldDiet
     *      il parametro oldDiet rappresenta la versione non modificata della dieta 
     * 
     * @param modDiet
     *      il parametro modDiet rappresenta la dieta modificata
     *      
     * @throws
     *      lancia eccezione in caso di errore nel salvataggio o ritrovamento del file
     */
	public void modifyDiet(Diet oldDiet, Diet modDiet) throws Exception;
	
	/**
     * Il metodo addMeal permette di aggiungere un pasto alla lista di pasti della nuova
     * dieta da creare
     * 
     */
	public void addMeal();  // create a new meal
	
	/**
     * Il metodo deleteMeal permette di cancellare l'ultimo pasto inserito dalla lista di pasti 
     * della nuova dieta da creare
     * 
     */
	public void deleteMeal();
	
	/**
     * Il metodo addFoodOnDiet permette di inserire un alimento e la relativa quantità in un pasto
     * 
     * @param name
     *      il parametro name indica il nome del cibo da recuperare dalla lista dei cibi
     * 
     * @param quantity
     *      il parametro quantity indica la quantità
     */
	public void addFoodOnDiet(String name, int quantity);
	
	/**
     * Il metodo getNewDiet è il getter della nuova dieta
     * 
     * @return
     *      restituisce la nuova dieta che si sta creando
     */
	public Diet getNewDiet(); // getter for the new diet
	
	/**
     * Questo metodo è il getter della lista dei pasti della nuova dieta che si sta creando
     * 
     * @return
     *      restituisce la lista dei pasti della nuova dieta
     */
	public DCList<Meal> getMList();
	
	/**
     * Questo metodo è il getter della lista dei cibi inseriti nel pasto che si sta creando
     * 
     * @return
     *      restituisce la lista dei cibi inseriti nel pasto che si sta creando
     */
	public DCList<FoodOnDiet> getFODList();
	
	/**
     * Questo metodo crea un alert con un testo dato
     * 
     * @param text
     *      il testo dell'alert
     */
	public void buildAlert(String text);
	
	/**
     * Crea una dialog di conferma 
     * 
     * @return
     *      restituisce true se si clicca ok, altrimenti restituisce false
     */
	public boolean buildConfirmDialog();
	
	/**
     * Controlla se una stringa data è vuota
     * 
     * @param text
     *      la stringa da controllare
     * 
     * @return
     *      true se vuota, altrimenti false
     */
	public boolean checkEmptyField(String text);
	
	/**
     * Controlla se il nome passato è presente nella lista di profili, diete, cibi
     * 
     * @param name
     *      name è il nome da controllare
     *      
     * @param type
     *      type indica il tipo di lista da controllare(profili, diete di un profilo, cibi)
     *      
     * @return
     *      true se presente, altrimenti false
     */
	public boolean checkIfPresent(String name, String type);
	
	/**
     * Controlla se una stringa contiene lettere e se il valore numerico è <0
     * 
     * @param string
     *      la stringa da controllare
     * 
     * @return
     *      true se contiene lettere o se il valore è < 0, altrimenti false
     */
	public boolean checkValue(String text);
	
}
