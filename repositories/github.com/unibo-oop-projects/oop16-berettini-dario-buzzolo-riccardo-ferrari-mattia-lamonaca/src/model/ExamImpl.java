package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExamImpl implements Exam, Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Master> listMaster;
	private ArrayList<Athlete> listAthlete ;
	//private ExamDoneImpl exam;
	//private ArrayList<ExamDoneImpl> listaEsami = new ArrayList<>();
	private String dataS;	
	public ExamImpl(){
		
		this.listAthlete = new ArrayList<>();
		this.listMaster = new ArrayList<>();
		//this.listAthlete.add(new AthleteImpl("DARIO", "BERETTINI", Belt.BIANCA));
		//this.listAthlete.add(new AthleteImpl("SEBASTIANO", "BERETTINI", Belt.BIANCA));
		//this.listMaster.add(new MasterImpl("MASSIMO","PECORARI",4));
		//this.listMaster.add(new MasterImpl("MARCO","CICCIO",4));
		//this.listMaster.add(new MasterImpl("SIG","BERETTA",4));	
		this.dataS = setDate();
	}	
	
	public List<Master> getListMaster() {
		return this.listMaster;
	}
	
	public List<Athlete> getListAthlete() {
		return this.listAthlete;
	}
	
	public void addMaster(Master m) {

			this.listMaster.add(m);		
	}
	
	public void addAthlete(Athlete a) {

			this.listAthlete.add(a);
	}
	
	public Master getLastMaster(){
		
		return this.listMaster.get(this.listMaster.size()-1);
	}
	
	public Athlete getLastAthlete(){
		
		return this.listAthlete.get(this.listAthlete.size()-1);
	}
	
	public int checkDone(Athlete atleta, Integer index){
		
		return atleta.getVoto(index);			
	}
	
	public boolean checkPassed(Athlete atleta){
	
		int sommaVoti=0;
		if(atleta.getAvanzamento()==3){
			for(int i=0; i<3; i++){
				sommaVoti+=atleta.getVoto(i);
			}
			if(sommaVoti>=18){
				return true;
			}
			return false;
		}
		return false;
	}
	
	/*public void addExamDone(){
	
		exam = new ExamDoneImpl(listMaster, listAthlete,dataS);
		this.listaEsami.add(exam);
		insertEsameFile();
	}
	
	public ArrayList<ExamDoneImpl> getListaEsami(){
		
		return this.listaEsami;
	}
	
	public void insertEsameFile() {

		try {

			FileOutputStream stream = new FileOutputStream("Esami.dat");

			ObjectOutputStream osStream = new ObjectOutputStream(stream);

			osStream.writeObject(listaEsami);
			
			osStream.flush();

			osStream.close();

		} catch (Exception e) {

			System.out.println("I/O errore"+e);
		}
	}
	
	public ArrayList<ExamDoneImpl> getEsamiFile() {

		try {

			FileInputStream stream = new FileInputStream("Esami.dat");

			ObjectInputStream osStream = new ObjectInputStream(stream);

			@SuppressWarnings("unchecked")
			ArrayList<ExamDoneImpl> listaEsamiFile = (ArrayList<ExamDoneImpl>) osStream.readObject();

			osStream.close();
			
			for(ExamDoneImpl esame : listaEsamiFile){
				this.listaEsami.add(esame);
			}
			
			return listaEsamiFile;

		} catch (Exception e) {

			System.out.println("I/O errore di stampa");

		}

		ArrayList<ExamDoneImpl> arrayEmpty = new ArrayList<ExamDoneImpl>();
		return arrayEmpty;
	}*/
	
	public String openGuidaTecnica(String prova,int valueBelt){
		
		valueBelt++;
		return "resource/guidatecnica/"+valueBelt+""+prova+".png";	
	}
	
	public String setDate(){
		
		Date data = new Date();
		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-YYYY");
		return dt1.format(data);
	}
	
	public String getDate(){
		
		return this.dataS;
	}
	
	public void deleteMaster(int nMaster){
		
		this.listMaster.remove(nMaster);
	}
	public void deleteAthlete(int nAthlete){
		
		this.listAthlete.remove(nAthlete);
	}
}
	
	
	

