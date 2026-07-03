package controller;

import model.Exam;
import model.ExamImpl;
import model.Fight;
import model.FightImpl;
import model.Form;
import model.FormImpl;
import model.TaekwondoPlatform;
import view.ExamPanel;
import view.ExamPanelImpl;
import view.FightPanel;
import view.FightPanelImpl;
import view.FormPanel;
import view.FormPanelImpl;
import view.MainPanel;

public class MainControllerImpl implements MainController {

	private TaekwondoPlatform modelTkd;
	private MainPanel panel;

	public MainControllerImpl(TaekwondoPlatform modelTkd, MainPanel panel) {
		//super();
		this.modelTkd = modelTkd;
		this.panel = panel;
		this.panel.addObserver(this);
	}
	
	public boolean logIn(String username, String password) {
		
		return  this.modelTkd.logIn(username, password);
	}
	
	public void examView() {
		
		Exam examModel = new ExamImpl(); 
		ExamPanel examView = new ExamPanelImpl();
		  
		@SuppressWarnings("unused")
		ExamController examController = new ExamControllerImpl(examModel,examView);  
	}
	
	public void fightView(){
		
		Fight modelFight = new FightImpl();
		FightPanel fightPanel = new FightPanelImpl();
		
		@SuppressWarnings("unused")
		FightController fightController = new FightControllerImpl(modelFight, fightPanel);
	}

	@Override
	public void formView() {
		
		Form modelForm = new FormImpl();
		FormPanel formPanel = new FormPanelImpl();
		
		@SuppressWarnings("unused")
		FormController formController = new FormControllerImpl(modelForm, formPanel);	
	}
}
