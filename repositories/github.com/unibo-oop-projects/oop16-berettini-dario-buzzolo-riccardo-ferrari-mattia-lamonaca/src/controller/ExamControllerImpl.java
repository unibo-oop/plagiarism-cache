package controller;

import java.util.List;

import model.*;
import view.*;


public class ExamControllerImpl implements ExamController {
	
	
	private Exam modelExam;
	private ExamPanel examView;
		
	public  ExamControllerImpl(Exam examModel,ExamPanel examView2){
	
			this.modelExam = examModel;
			this.examView = examView2;
			this.examView.addObserver(this);			
	}
	
	public void insertAthleteView(){	
		
		InsertAthlete insertAthletePanel = new InsertAthleteImpl();
		insertAthletePanel.addObserver(this);
	}
	public void insertMasterView(){
		
		InsertMaster insertMasterPanel = new InsertMasterImpl();
		insertMasterPanel.addObserver(this);
	}
	public void startExamView(){
		
		ExamStarted ExamPanel = new ExamStartedImpl(modelExam.getListAthlete());
		ExamPanel.addObserver(this);
	}

	public void votesAthleteView(Athlete atleta) {
		
		VotiEsame votiPanel = new VotiEsameImpl(modelExam.getListMaster(), atleta);
		votiPanel.addObserver(this);

				
	}
	public void insertMaster(String name, String surname, Integer dan) {
		
		Master newMaster = new MasterImpl(name,surname,dan);
		modelExam.addMaster(newMaster);	
		
	}
	
	public void insertAthlete(String name, String surname, Belt belt) {

		Athlete newAthlete = new AthleteImpl(name, surname, belt);
		modelExam.addAthlete(newAthlete);
		
	}

	public List<Athlete> getAthletes() {		
		
		return modelExam.getListAthlete();
	}
	public List<Master> getMasters(){
		return modelExam.getListMaster();
	}
	public String getMastersString(){
		
		return modelExam.getListMaster().toString();
	}

	public String printAthletes(){
		
		return modelExam.getListAthlete().toString();
	}
	/*public ArrayList<ExamDoneImpl> getListaEsami(){
		return modelExam.getListaEsami();
	}
	public void addExamFile(){
		
		modelExam.insertEsameFile();
	}
	public ArrayList<ExamDoneImpl> getEsamiFile(){
		
		return modelExam.getEsamiFile();
	}

	@Override
	public void addExameDone() {

			modelExam.addExamDone();
	}*/
	public String openGuidaTecnica(String prova, int valueBelt){
		
			return modelExam.openGuidaTecnica(prova, valueBelt);
	}
	public String getDate(){
		
		return modelExam.getDate();
	}
	
	public Master getLastMaster(){
		
		return modelExam.getLastMaster();
	}
	public Athlete getLastAthlete(){
		return modelExam.getLastAthlete();
	}
	
	public void updateAthlete(){
		
		examView.updateAthlete();
	}
	public void updateMaster(){
		
		examView.updateMaster();
	}
	public void deleteMaster(int nMaster){
		
		modelExam.deleteMaster(nMaster);
	}
	public void deleteAthlete(int nAthlete){
		
		modelExam.deleteAthlete(nAthlete);
	}
}
