package magazzino.entratamerci.test;

import magazzino.entratamerci.models.area;
import magazzino.entratamerci.models.articolo;
import magazzino.entratamerci.models.fornitore;
import magazzino.entratamerci.models.locazione;
import magazzino.entratamerci.service.ArticoliService;
import magazzino.entratamerci.service.FornitoriService;
import magazzino.entratamerci.service.LocazioniService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMagazzinoMerci {
	
	@Test 
	@DisplayName("Test filtro su articoli obsoleti")
	void assertNewArticoloObsolete(){
		ArrayList<articolo> artList= new ArrayList<articolo>();
		ArrayList<articolo> validArtList= new ArrayList<articolo>();
		articolo a1= new articolo("01","banana",false);
		articolo a2= new articolo("02","mela",true);
		articolo a3= new articolo("02","pera",true);
		artList.add(a1);
		artList.add(a2);
		artList.add(a3);
		validArtList.add(a1);
		artList = new ArticoliService().getArticoliFiltered(artList,"", false);
		assertEquals(artList, validArtList);
	}
	
	@Test 
	@DisplayName("Test filtro locazioni usando oggetto area")
	void assertLocazioneByArea(){
		ArrayList<locazione> locazioneList= new ArrayList<locazione>();
		ArrayList<locazione> validLocazioneList= new ArrayList<locazione>();
		area a1= new area("A1","AREA 1");
		area a2= new area("A2","AREA 2");
		locazione loc1= new locazione(a1,"A1L1","LOCAZIONE 1");
		locazione loc2= new locazione(a1,"A1L2","LOCAZIONE 2");
		locazione loc3= new locazione(a2,"A2L1","LOCAZIONE 1");
		locazioneList.add(loc1);
		locazioneList.add(loc2);
		locazioneList.add(loc3);
		locazioneList.add(loc2);
		validLocazioneList.add(loc3);
		locazioneList = new LocazioniService().getLocazioniByArea(locazioneList, a2);
		assertEquals(locazioneList, validLocazioneList);
	}
	
	@Test 
	@DisplayName("Test getFornitore by codice")
	void assertGetFornitoreByCodice(){
		ArrayList<fornitore> fornitoreList= new ArrayList<fornitore>();
		String validFornitoreName = "Mario";
		fornitore f1= new fornitore("01","Fruttivendolo","Mario","Rossi");
		fornitore f2= new fornitore("02","Contadino","Michele","Maraldi");
		fornitoreList.add(f1);
		fornitoreList.add(f2);
		fornitore validFornitore = new FornitoriService().getFornitoreByCodice(fornitoreList, "01");
		assertEquals(validFornitore.getNome(), validFornitoreName);
	}
}
