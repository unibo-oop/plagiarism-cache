package speech.main;

import java.io.IOException;

import speech.controller.Controller;
import speech.model.Model;
// to insert in ClientController(), startVoice()
public class Main {
	public static void main(String args[]){
		try {
			Model model=Model.getInstance();
			Controller controller= Controller.getInstance();
			controller.setModel(model);
			controller.enableVoiceRecognition();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
