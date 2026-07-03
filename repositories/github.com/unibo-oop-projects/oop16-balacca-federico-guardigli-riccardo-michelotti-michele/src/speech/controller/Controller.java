package speech.controller;

import java.io.IOException;

import speech.model.Model;

//come collegare la prima parte del bottone
//quando parte l'aplicazione quello che ha la view dovr� chiamare la riga 11 del main, poi con il pulsante abilita, dovr� fare le righe 12-13-14
/**
 * 
 *Controller, that implements the control logic. Use of the pattern singleton
 *
 */
//controller con pattern SINGLETON
public class Controller {
	public void enableVoiceRecognition(){
		model.startRecognition();
	}
	private static Controller instance;
	private Model model;
	private Controller() throws IOException{
		//model=Model.getInstance();
	}
	/**
	 * method for creating the singleton at the first call
	 * @return instance
	 * @throws IOException
	 */
	public static Controller getInstance()  throws IOException{
		if(instance==null){
			instance=new Controller();
		}
		return instance;
	}

	public void setModel(Model model){
		this.model=model;
	}
}








