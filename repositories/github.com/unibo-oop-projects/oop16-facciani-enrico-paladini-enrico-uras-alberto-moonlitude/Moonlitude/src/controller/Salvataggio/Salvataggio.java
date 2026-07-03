package controller.Salvataggio;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.*;
import model.Astronauta.*;
import model.Oggetti.*;
import model.Stanza.*;

public class Salvataggio implements Serializable {
	
	private static final long serialVersionUID = -3701788324358826479L;
	private static File path = new File(System.getProperty("user.home"), ".moonlitude");
	private Model model;
		
	
	
	
	public Salvataggio ( Model model){
		this.model = model;
	}
	
	
	
	
	/**
	 * Method to save the game on file
	 * 
	 * @throws FileNotFoundException 
	 * 								exception
	 * 
	 * @throws IOException
	 * 						exception
	 */
	public void salvaInteroGioco() throws FileNotFoundException, IOException{
		
		ObjectOutputStream writer = null;
		
		if ( writer == null){
			writer = new ObjectOutputStream(new FileOutputStream (path) );
		}
		
		writer.writeObject(Astronave.getLog().getPezziDanneggiati());
		writer.writeInt(Astronave.getLog().getScudo());
		writer.writeObject(this.model.getAstronauta());
		writer.writeObject(this.model.getTempo());
		writer.writeInt(Esperienza.getLog().getEsperienza());
		writer.writeInt(Esperienza.getLog().getLivello());
		writer.writeObject(Equipaggiamento.getLog().getTuta());
		writer.writeObject(Equipaggiamento.getLog().getEquipaggio());
		writer.writeObject(Equipaggiamento.getLog().getOggettiEquipaggiabili());
		writer.writeObject(Filtratore.getLog().getContenitori());
		writer.writeInt(Filtratore.getLog().getAcquaRaccolta());
		writer.writeObject(Generatore.getLog().getLuminosita());
		writer.writeDouble(Generatore.getLog().getCarica());
		writer.writeObject(Radar.getLog().getMappaMondo());
		writer.writeObject(Zaino.getLog().getOggetti());
		writer.writeInt(Giardino.getLog().getSlots());
		writer.writeObject(Giardino.getLog().getPiante());
		writer.writeObject(Laboratorio.getLog().getOggettiStudiati());
		writer.writeInt(Magazzino.getLog().getSpazio());
		writer.writeObject(Magazzino.getLog().getOggetti());
		writer.writeInt(Refrigeratore.getLog().getSpazio());
		writer.writeObject(Refrigeratore.getLog().getOggetti());
		writer.close();
		
	}
	
	
	
	
	/**
	 * Method to load the game
	 * 
	 * @throws FileNotFoundException
	 * 								exception
	 * 
	 * @throws IOException
	 * 					exception
	 * 
	 * @throws ClassNotFoundException
	 * 								exception
	 */
	public void caricamentoGioco() throws FileNotFoundException, IOException, ClassNotFoundException{
		
		ObjectInputStream reader = null;
		
		if (reader == null){
			
			reader = new ObjectInputStream(new FileInputStream(path));
			
		}
		
		//ASTRONAVE
		@SuppressWarnings("unchecked")
		Map<Materiale, Integer>  pezziDanneggiati = (Map<Materiale, Integer>) reader.readObject();
		Astronave.getLog().setPezziDanneggiati(pezziDanneggiati);
		int scudi = reader.readInt();
		Astronave.getLog().setScudo(scudi);
		
	
		//ASTRONAUTA
		Astronauta astronautaCaricamento = (Astronauta) reader.readObject();
		this.model.setAstronauta(astronautaCaricamento);
		
		
		
		//TEMPOIMPL
		TempoImpl tempo = (TempoImpl) reader.readObject();
		this.model.setTempo(tempo);
		 
		 //ESPERIENZA
		int esperienza = reader.readInt();
		int livello=reader.readInt();
		Esperienza.getLog().setEsperienza(esperienza);
		Esperienza.getLog().setLivello(livello);
	
		//EQUIPAGGIAMENTO
		TutaSpaziale tuta = (TutaSpaziale) reader.readObject();
		Equipaggiamento.getLog().cambioTuta(tuta);
		CommonEquipaggiamento equipaggio = (CommonEquipaggiamento) reader.readObject();
		Equipaggiamento.getLog().aggiuntaEquipaggiamento(equipaggio);
		
		
		@SuppressWarnings("unchecked")
		List<CommonEquipaggiamento> oggettiEquipaggiabili = (List<CommonEquipaggiamento>) reader.readObject();
		Equipaggiamento.getLog().inserisciInListaOggettiEquipaggiabili(oggettiEquipaggiabili);
		
		//FILTRATORE
		@SuppressWarnings("unchecked")
		List<ContenitoreAcqua> contenitori = (List<ContenitoreAcqua>) reader.readObject();
		int acquaRaccolta = reader.readInt();
		Filtratore.getLog().setAcquaRaccolta(acquaRaccolta);
		Filtratore.getLog().setListaContenitori(contenitori);
		
		//GENERATORE
		Luminosita lux = (Luminosita) reader.readObject();
		double carica = reader.readDouble();
		Generatore.getLog().setCarica(carica);
		Generatore.getLog().setLuminosita(lux);
	
    	//RADAR
    	@SuppressWarnings("unchecked")
    	Map<Posizione,Boolean> mappaMondo = (Map<Posizione, Boolean>) reader.readObject();
    	
    	Radar.getLog().setMappaMondo(mappaMondo);
    	
    	//ZAINO
		@SuppressWarnings("unchecked")
		Map<Oggetto,Integer> oggettiZaino =  (Map<Oggetto, Integer>) reader.readObject();
		Zaino.getLog().setMappaOggetti(oggettiZaino);
	
		 //GIARDINO
		int slots=reader.readInt();
		@SuppressWarnings("unchecked")
		List<Pianta> piante = (List<Pianta>) reader.readObject();
		Giardino.getLog().setPiante(piante);
		Giardino.getLog().aggiungiSlots(slots);
		
		//LABORATORIO
		@SuppressWarnings("unchecked")
		Set<Materiale> oggettiStudiati = (Set<Materiale>) reader.readObject();
		oggettiStudiati.forEach(e->{
			Laboratorio.getLog().ricerca(e);
		});
		
		//MAGAZZINO
		int spazio = reader.readInt();
		
		@SuppressWarnings("unchecked")
		Map<Materiale,Integer> oggetti =  (Map<Materiale, Integer>) reader.readObject();
		Magazzino.getLog().aumentaSpazio(spazio);
		Magazzino.getLog().setMappaOggetti(oggetti);
		 
		 //REFRIGERATORE
		int spazioRefrigeratore = reader.readInt();
		
		@SuppressWarnings("unchecked")
		Map<Cibo,Integer> oggettiRefrigeratore =  (Map<Cibo, Integer>) reader.readObject();
		
		Refrigeratore.getLog().aumentaSpazio(spazioRefrigeratore);
		Refrigeratore.getLog().setMappaOggetti(oggettiRefrigeratore);
			
		reader.close();
	}
}

			
/*	

public void costruttoreAstronave (Map<Materiale, Integer> pezziDanneggiati,int scudi){
	this.pezziDanneggiati = pezziDanneggiati;
	this.scudiAstronave = scudi;
}

	

public void costruttoreEsperienza(int esperienza , int livello ){
	
	this.esperienza = esperienza;
	this.livello = livello;
}





public void costruttoreEquipaggiamento(TutaSpaziale tuta , CommonEquipaggiamento equipaggio, List<CommonEquipaggiamento> oggettiEquipaggiabili ){
	
	this.tutaSpaziale = tuta;
	this.equipaggio = equipaggio;
	this.oggettiEquipaggiabili = oggettiEquipaggiabili;
	
}


public void costruttoreFiltratore(List<ContenitoreAcqua> contenitori , int acquaRaccolta){
	
	this.contenitori = contenitori;
	this.acquaRaccolta = acquaRaccolta;

}




public void costruttoreGeneratore(Luminosita lux , double carica ){
	
	this.lux = lux;
	this.carica = carica;
}










public void costruttoreRadar(Map<Posizione,Boolean> mappaMondo ) {
	
	this.mappaMondo = mappaMondo;;
}





public void costruttoreZaino(List<Slot<Materiale>> oggettiZaino ){
	
	this.oggettiZaino = oggettiZaino;
}


public void costruttoreGiardino(	 int slots, List<Pianta> piante ) throws FileNotFoundException, IOException{
			
		this.slotsGiardino = slots;
		this.piante = piante;
}


public void costruttoreLaboratorio(	Set<Materiale> oggettiStudiati  ) throws FileNotFoundException, IOException{
	
	this.oggettiStudiati = oggettiStudiati;
}




public void costruttoreMagazzino(	 int spazio, List<Slot<Oggetto>> oggetti ){
		
	this.oggettiMagazzino = oggetti;
	this.spazioMagazzino = spazio;
		
}
		


	

public void costruttoreRefrigeratore(	 int spazio, List<Slot<Cibo>> oggetti ){
	
	this.oggettiRefrigeratore = oggetti;
	this.spazioRefrigeratore = spazio;
}

	/**
	 * Method to set the parameters to save in Sopravvivenza class
	 * 
	 * @param fame
	 * 			   player's hunger
	 * 
	 * @param sete 
	 * 			   player's thirsty
	 * 
	 * @param ossigeno 
	 * 				   player's oxygen
	 
	public void costruttoreSopravvivenza(int fame,int sete,int ossigeno){
		
		this.parametri = new Sopravvivenza(fame,sete,ossigeno);

	}
	
	
	
	
	
	 *  Method to set the parameters to save in Astronauta class
	 * 
	 * 
	 * @param parametri
	 * 					 hunger, oxygen, thirsty of the Player ( Sopravvivenza )
	 * @param posizione
	 * 					player's position in the world
	 
	public void costruttoreAstronauta(Sopravvivenza parametri , Posizione posizione ){
		this.astronauta = new Astronauta(this.parametri);
		this.posizione = posizione;
	}
	

	
	

	 *  Method to set the parameters to save in TempoImpl lass
	 * 
	 *
	 * @param condizioneAtmosferica
	 * 								atmospheric condition in the world
	 * 
	 * @param oraDallUltimoCambiamento
	 * 									hours since the last atmospheric change
	 * 
	 * @param ora
	 * 			 the current time
	 * 
	 * @param data
	 * 			the current data
	 
	public void costruttoreTempoImpl(CondizioneAtmosferica condizioneAtmosferica , int oraDallUltimoCambiamento, int ora ,  String data ){
		this.condizioneAtmosferica = condizioneAtmosferica;
		this.oraDallUltimoCambiamento = oraDallUltimoCambiamento;
		this.ora = ora;
		this.data = data;
	}
	
		*
	 *  Method to get Sopravvivenza object
	 *  
	 * @return
	 * 			instance of Sopravvivenza class
	 
	public Sopravvivenza getSopravvivenza(	){
	
		return this.parametriCaricamento;	
	}
	
	
	

	
	 *  * Method to get Astronauta class
	 *  
	 * @return
	 * 			instance of Astronauta class
	
	public Astronauta getAstronauta( ){
		return this.astronautaCaricamento;
	}

		
	
	
	
	 * Method to get TempoImpl class
	 * 
	 * @return tempoImpl
	 * 					instance of TempoImpl class
		
	public TempoImpl getTempoImpl( ){
			
			return this.tempoImplCaricamento;
	}
	
	

	
	*
	 * Method to get ContenitoreAcqua class
	 * 
	 * @return contenitoreAcqua
	 * 							instance of ContenitoreAcqua class
	
	public ContenitoreAcqua getContenitoreAcqua( ){
		
		return this.contenitoreAcquaCaricamento;
	}

	
	

	/**
	 * Method to load Pianta class
	 * 
	 * @return pianta
	 * 				instance of Pianta class
	 
	public Pianta getPianta(){

			return this.piantaCaricamento;
	}		
*/