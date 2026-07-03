package controller;

import java.util.HashMap;
import java.util.Map;

import controller.Salvataggio.Salvataggio;
import model.Astronave;
import model.BaseSpaziale;
import model.Model;
import model.ModelImpl;
import model.Oggetti.Cibo;
import model.Oggetti.Materiale;
import model.Oggetti.Oggetto;
import model.Stanza.CommonPianta;
import model.Stanza.Filtratore;
import model.Stanza.Giardino;
import model.Stanza.Magazzino;
import model.Stanza.Pianta;
import view.ViewImpl;

public class Controller implements ControllerInterface {
	
	private Model model = new ModelImpl();
	private ViewImpl view;
	private Salvataggio salvataggio = new Salvataggio(this.model);
	private ControllerAstronave controllerAstronave;
	private ControllerAstronauta controllerAstronauta;
	private ControllerFiltratore controllerFiltratore;
	private ControllerCucina controllerCucina;
	private ControllerEsplorazione controllerEsplorazione;
	private ControllerGeneratore controllerGeneratore;
	private ControllerGiardinoPianta controllerGiardinoPianta;
	private ControllerLaboratorio controllerLaboratorio;
	private ControllerMagazzino controllerMagazzino;
	private ControllerRadar controllerRadar;
	private ControllerRefrigeratore controllerRefrigeratore;
	private ControllerZaino controllerZaino;
	private ControllerUtility controllerUtility;
	private Map<Oggetto,Integer> mappaEsplorazioneZaino = new HashMap<>();
	private int carica = 0 ;
	private static final Integer UNITA_MISURA_BASE = 1;
	
	
	/**
	 * Create the Controller singleton
	 */
	private static class LazyHolder {
        private static final Controller SINGLETON = new Controller();
    }
    
	
    private Controller() {
    }
    
 
    public static Controller getLog() {
        return LazyHolder.SINGLETON;
    }
    
    
    
    
    public int getCostante(){
    	return UNITA_MISURA_BASE;
    }
    
    
    

    public void setCarica(int carica){
    	this.carica = carica;
    }
    
    
    
 
    public int getCarica(){
    	return this.carica;
    }
    
    
    
 
    public void setMap(Map<Oggetto,Integer> map){
    	this.mappaEsplorazioneZaino = map;
    }
    
    
    

    public Map<Oggetto,Integer> getMap(){
    	return this.mappaEsplorazioneZaino;
    }
    
    
    

     public void getPassaOre(int numero){
 
    	  this.model.getTempo().passaOre(numero);
    	  if(this.model.getAstronauta().getParametri().getFame() <=0 || this.model.getAstronauta().getParametri().getSete() <= 0|| 
    			  this.model.getAstronauta().getParametri().getOssigeno() <= 0){
    		  this.view.SetFinale(-Controller.getLog().getCostante());
    	  }
    }
    
     
     
   
    public void winGame(){
    	if(Astronave.getLog().getScudo() == 3600 && Astronave.getLog().getPezziDanneggiati().size() == 0){
    		this.view.SetFinale(Controller.getLog().getCostante());
    	}
    } 
     
     
    
   
     public String getCondizioneAtmosferica(){
    	 return this.model.getTempo().getCondizioneAtmosferica().getNome();
     }
    
     
     
   
    public void setGioco(){
    	BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Materiale.MATTONE, 100);
        Magazzino.getLog().aumentaSpazio();
        Magazzino.getLog().aumentaSpazio();
        Magazzino.getLog().aumentaSpazio();
        Magazzino.getLog().aumentaSpazio();
        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Materiale.COMPUTER, 1);
        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Materiale.GENERATORE, 1);
        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Materiale.BATTERIA, 7);
        Magazzino.getLog().aumentaSpazio();
        Magazzino.getLog().aumentaSpazio();
        Magazzino.getLog().aumentaSpazio();
        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Materiale.CHEROSENE, 90);
        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Materiale.ALLUMINIO, 90);
        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Materiale.FERRO, 38);
        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Materiale.MOTORE_PRINCIPALE, 2);
        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Cibo.BANANA, 5);
        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Cibo.FAGIOLO, 3);
        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Cibo.POMODORO, 10);
        Giardino.getLog().aggiungiPianta(new Pianta(CommonPianta.BANANO));
        Giardino.getLog().aggiungiPianta(new Pianta(CommonPianta.MELO));
        this.model.getTempo().passaOre(1);
        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Materiale.DRONE,1 );
        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Materiale.BATTERIA, 2);
        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Materiale.CONTENITORE_VUOTO, 1);
        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(Materiale.SFERA_ENERGIA, 10);
        Filtratore.getLog().aggiungiContenitore();
        this.model.getTempo().passaOre(Controller.getLog().getCostante());
    }
    
    
    

	public void setView(ViewImpl view){
		
		this.view = view;
		this.controllerAstronave  = new ControllerAstronave(this.view);
		this.controllerAstronauta = new ControllerAstronauta(this.view,this.model);
		this.controllerFiltratore = new ControllerFiltratore(this.view, this.model);
		this.controllerCucina = new ControllerCucina(this.view, this.model);
		this.controllerEsplorazione = new ControllerEsplorazione(this.view);
		this.controllerGeneratore = new ControllerGeneratore(this.view);
		this.controllerGiardinoPianta = new ControllerGiardinoPianta(this.view, this.model);
		this.controllerLaboratorio = new ControllerLaboratorio(this.view, this.model);
		this.controllerMagazzino = new ControllerMagazzino(this.view);
		this.controllerRadar = new ControllerRadar(this.view);
		this.controllerRefrigeratore = new ControllerRefrigeratore(this.view, this.model);
		this.controllerZaino = new ControllerZaino(this.view);
		this.controllerUtility = new ControllerUtility(this.view,this.salvataggio);
	}
	
	
    
   
	public ControllerAstronave getControllerAstronave() {
		return controllerAstronave;
	}
	
	
	

	public ControllerAstronauta getControllerAstronauta() {
		return controllerAstronauta;
	}
	
	
	

	public ControllerFiltratore getControllerFiltratore() {
		return controllerFiltratore;
	}
	
	
	

	public ControllerCucina getControllerCucina() {
		return controllerCucina;
	}
	
	
	

	public ControllerEsplorazione getControllerEsplorazione() {
		return controllerEsplorazione;
	}
	
	
	

	public ControllerGeneratore getControllerGeneratore() {
		return controllerGeneratore;
	}
	
	
	

	public ControllerGiardinoPianta getControllerGiardinoPianta() {
		return controllerGiardinoPianta;
	}
	
	
	

	public ControllerLaboratorio getControllerLaboratorio() {
		return controllerLaboratorio;
	}
	
	
	

	public ControllerMagazzino getControllerMagazzino() {
		return controllerMagazzino;
	}
	
	
	

	public ControllerUtility getControllerUtility() {
		return controllerUtility;
	}
	
	
	

	public ControllerRadar getControllerRadar() {
		return controllerRadar;
	}
	
	
	

	public ControllerRefrigeratore getControllerRefrigeratore() {
		return controllerRefrigeratore;
	}
	
	
	

	public ControllerZaino getControllerZaino() {
		return controllerZaino;
	}
}
