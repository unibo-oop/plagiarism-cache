package application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;


class WarehouseAddUtilityImplTest {
	
	private ArrayList<Typology> typology = new ArrayList<>();
	WarehouseAddUtility wAdd = new WarehouseAddUtilityImpl();	
	Catena catena =  new Catena();
	Tipologia t;
	Prodotto p;
	

	@Test
	void testAddTipologia() {		
		String idTipologia = "Pesce";
		String idInfor = "Colore";
		String valoreInfo = "Marrone";
		typology.add(wAdd.addNewTipologia(catena, idTipologia, idInfor, valoreInfo));
		catena.aggiungiAllInventario(typology);
		t = (Tipologia)catena.ottieniDallInventario(idTipologia).get();
		assertEquals(t, (Tipologia)catena.ottieniDallInventario(idTipologia).get());		
	}
	
	@Test
	void testAddProdotto() {
		String idProdotto = "Branzino";
		String idTipo = t.getID();
		String idInfor = "Provenienza";
		String valoreInfo = "Italia";
		String idScarto = "";
		String valoreScarto = "";
		typology.add(wAdd.addNewProdotto(catena, idProdotto, idTipo, idScarto, valoreScarto, idInfor, valoreInfo));
		catena.aggiungiAllInventario(typology);
		p = (Prodotto)catena.ottieniDallInventario(idProdotto).get();
		assertEquals(true, catena.getInventario().contains(p));		
	}
	
	
	

}
