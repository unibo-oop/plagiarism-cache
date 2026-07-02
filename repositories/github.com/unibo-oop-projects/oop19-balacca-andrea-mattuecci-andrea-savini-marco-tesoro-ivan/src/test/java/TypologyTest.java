package java;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.*;

import application.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TypologyTest {

	@Test
	void testOttieniInfo() {
		HashMap<String, String> mappa = new HashMap<String, String>();
        HashMap<String, String> mappa2 = new HashMap<String, String>();
        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<String> lista2 = new ArrayList<String>(); //strutture d'appoggio
        
        Tipologia pesce = new Tipologia("Pesce"); // creo il pesce
        Prodotto branzino = new Prodotto("Branzino", pesce); // creo il branzino dicendogli che è di tipo pesce
        ProdConcreto branzino200g = new ProdConcreto("Branzino200g", branzino); //...
        ProdFornito branzino200gGeloService = new ProdFornito("Branzino200gGeloService", branzino200g);
        
        mappa.put("Colore", "Rosso");
        mappa.put("Provenienza", "");
        pesce.aggiungiInfo(mappa); // aggiungo al pesce le info "Colore" = "Rosso" e "Provenienza" = ""
        
        lista.add("Colore");
        lista2.add(branzino200gGeloService.ottieniInfo(lista).get(0).get()); // richiedo l'informazione "Colore" al ProdFornito
        
        assertEquals("Rosso", lista2.get(0)); // = "Rosso", cioè il branzino ha restituito il valore presente a livello superiore
	}
	
	@Test
	void testModificaInfo() {
		HashMap<String, String> mappa = new HashMap<String, String>();
        HashMap<String, String> mappa2 = new HashMap<String, String>();
        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<String> lista2 = new ArrayList<String>(); //strutture d'appoggio
        
        Tipologia pesce = new Tipologia("Pesce"); // creo il pesce
        Prodotto branzino = new Prodotto("Branzino", pesce); // creo il branzino dicendogli che è di tipo pesce
        ProdConcreto branzino200g = new ProdConcreto("Branzino200g", branzino); //...
        ProdFornito branzino200gGeloService = new ProdFornito("Branzino200gGeloService", branzino200g);
        
        mappa.put("Colore", "Rosso");
        mappa.put("Provenienza", "");
        pesce.aggiungiInfo(mappa); // aggiungo al pesce le info "Colore" = "Rosso" e "Provenienza" = ""
        
        mappa2.put("Provenienza", "Italia");
        branzino200g.modificaInfo(mappa2); // modifico nel branzino la Provenienza dichiarata ma non definita a livello superiore
        
        lista.add("Provenienza");
        lista2.add(branzino200gGeloService.ottieniInfo(lista).get(0).get()); // richiedo l'informazione "Provenienza" al ProdFornito
        lista2.get(0); // = "Italia", cioè il branzino ha restituito il valore che presente a livello superiore
        
        assertEquals("Italia", lista2.get(0)); // = "Italia", cioè il branzino ha restituito il valore presente a livello superiore
	}
	
	@Test
	void testOttieniInfoTotali() {
		HashMap<String, String> mappa = new HashMap<String, String>();
        HashMap<String, String> mappa2 = new HashMap<String, String>();
        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<String> lista2 = new ArrayList<String>(); //strutture d'appoggio
        
        Tipologia pesce = new Tipologia("Pesce"); // creo il pesce
        Prodotto branzino = new Prodotto("Branzino", pesce); // creo il branzino dicendogli che è di tipo pesce
        ProdConcreto branzino200g = new ProdConcreto("Branzino200g", branzino); //...
        ProdFornito branzino200gGeloService = new ProdFornito("Branzino200gGeloService", branzino200g);
        
        mappa.put("Colore", "Rosso");
        mappa.put("Provenienza", "");
        pesce.aggiungiInfo(mappa); // aggiungo al pesce le info "Colore" = "Rosso" e "Provenienza" = ""
        
        mappa2.put("Provenienza", "Italia");
        branzino200g.modificaInfo(mappa2); // modifico nel branzino la Provenienza dichiarata ma non definita a livello superiore
        
        lista.add("Provenienza");
        lista2.add(branzino200gGeloService.ottieniInfo(lista).get(0).get()); // richiedo l'informazione "Provenienza" al ProdFornito
        lista2.get(0); // = "Italia", cioè il branzino ha restituito il valore che presente a livello superiore
        
        mappa2.clear();
        
        mappa2.put("Quality", "Good");
        
        branzino.aggiungiInfo(mappa2);
        
        assertEquals("{Colore=Rosso, Provenienza=, Quality=Good}", branzino.ottieniInfoTotali().toString());
        //mi aspetto che Provenienza non sia definita considerato il lookup (verso l'alto)
        
        assertEquals("{Colore=Rosso, Provenienza=Italia, Quality=Good}", branzino200gGeloService.ottieniInfoTotali().toString());
        //mi aspetto che Provenienza sia definita considerato il lookup (verso l'alto)
	}

	@Test
	void testModificaEOttieniScartiTotali() {
		HashMap<String, String> mappa = new HashMap<String, String>();
        HashMap<String, String> mappa2 = new HashMap<String, String>();
        ArrayList<Scarto> listas = new ArrayList<>();
        ArrayList<String> lista = new ArrayList<String>(); //strutture d'appoggio
        
        Tipologia pesce = new Tipologia("Pesce"); // creo il pesce
        Prodotto branzino = new Prodotto("Branzino", pesce); // creo il branzino dicendogli che è di tipo pesce
        ProdConcreto branzino200g = new ProdConcreto("Branzino200g", branzino); //...
        ProdFornito branzino200gGeloService = new ProdFornito("Branzino200gGeloService", branzino200g);
        
        listas.add(new Scarto("ScartoA")); //creo uno scarto (si autoinizializza a 0)
        
        branzino.aggiungiScarti(listas); //lo aggiungo a branzino
        
        Scarto scartoa = new Scarto("ScartoA"); //creo un ALTRO scarto con lo stesso nome
        scartoa.setQuantita((float) 50);
        scartoa.setPercentuale(true); // lo definisco
        
        listas.clear();
        listas.add(scartoa);
        branzino200gGeloService.modificaScarti(listas); //così modifico (ovviamente devo passare un oggetto scarto diverso da quello precedente altrimenti mantiene i riferimenti)
        
        assertEquals("50.0",branzino200gGeloService.ottieniScartiTotali().get(0).getQuantita().toString());
        //dato che è stato modificato il valore, viene restituito quello a livello base ed il lookup non serve
        
        lista.clear();
        lista.add("ScartoA");
        
        assertEquals("0.0",branzino200g.ottieniScartiTotali().get(0).getQuantita().toString());
        //col lookup che parte da branzino200g non ho il valore definito più in basso
        
        listas.clear();
        listas.add(new Scarto("ScartoB"));
        listas.get(0).setQuantita((float) 2);
        
        branzino200g.aggiungiScarti(listas); //aggiungo un nuovo scarto a branzino 200g
        
        assertEquals("2.0",branzino200gGeloService.ottieniScartiTotali().get(1).getQuantita().toString());
        //col lookup prelevo il valore dal piano superiore
	}
	
	@Test
	void testGetDerivabili() {
		HashMap<String, String> mappa = new HashMap<String, String>();
        HashMap<String, String> mappa2 = new HashMap<String, String>();
        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<Scarto> listas = new ArrayList<>();
        ArrayList<String> lista2 = new ArrayList<String>(); //strutture d'appoggio
        
        Catena catena = new Catena();
        Tipologia pesce = new Tipologia("Pesce"); // creo il pesce
        Prodotto branzino = new Prodotto("Branzino", pesce); // creo il branzino dicendogli che è di tipo pesce
        ProdConcreto branzino200g = new ProdConcreto("Branzino200g", branzino); //...
        ProdFornito branzino200gGeloService = new ProdFornito("Branzino200gGeloService", branzino200g);
        
        listas.add(new Scarto("ScartoA")); //creo uno scarto (si autoinizializza a 0)
        
        branzino.aggiungiScarti(listas); //lo aggiungo a branzino
        
        Scarto scartoa = new Scarto("ScartoA"); //creo un ALTRO scarto con lo stesso nome
        scartoa.setQuantita((float) 25);
        scartoa.setPercentuale(true); // lo definisco
        
        listas.clear();
        listas.add(scartoa);
        branzino200gGeloService.modificaScarti(listas); //così modifico (ovviamente devo passare un oggetto scarto diverso da quello precedente altrimenti mantiene i riferimenti)
        
        assertEquals("25.0",branzino200gGeloService.ottieniScartiTotali().get(0).getQuantita().toString());
        //dato che è stato modificato il valore, viene restituito quello a livello base ed il lookup non serve
        
        lista.clear();
        lista.add("ScartoA");
        
        assertEquals("0.0",branzino200g.ottieniScartiTotali().get(0).getQuantita().toString());
        //col lookup che parte da branzino200g non ho il valore definito più in basso
        
        listas.clear();
        listas.add(new Scarto("ScartoB"));
        listas.get(0).setQuantita((float) 25);
        listas.get(0).setPercentuale(true); // lo definisco
        
        branzino200g.aggiungiScarti(listas); //aggiungo il nuovo scarto a branzino 200g
        
        branzino200gGeloService.setPrezzo((float)4);
        branzino200gGeloService.setValoreAssoluto((float)0.2);
        branzino200gGeloService.setIDFornitore("Gelo");
        
        System.out.println(branzino200gGeloService.getPrezzoEffettivo().toString() + " -- " + branzino200gGeloService.getValoreNetto());
        
        ProdFornito branzino200gBestForn = new ProdFornito("Branzino200gBestForn", branzino200g);
        branzino200gBestForn.setPrezzo((float)4);
        branzino200gBestForn.setValoreAssoluto((float)0.2);
        branzino200gBestForn.setIDFornitore("Best");
        
        catena.aggiungiUnFornitore(new Fornitore("Best"));
        catena.aggiungiUnFornitore(new Fornitore("Gelo"));
        
        ArrayList<Typology> ll = new ArrayList<Typology>(); //struttura d'appoggio
        ll.add(pesce); ll.add(branzino); ll.add(branzino200g); ll.add(branzino200gBestForn); ll.add(branzino200gGeloService);
        catena.aggiungiAllInventario(ll); //aggiungo all'inventario le typology
        
        assertEquals("5.0 -- 0.15",branzino200gBestForn.getPrezzoEffettivo().toString() + " -- " + branzino200gBestForn.getValoreNetto());
        //mi aspetto quindi un prezzo effettivo più grande del 25% e un velore netto effettivo più piccolo del 25%
        
        System.out.println("migliori prodotti gelo: - " + catena.ottieniUnFornitore("Gelo").get().getMiglioriProdotti(catena).keySet().toString());
        System.out.println("migliori prodotti best forn: - " + catena.ottieniUnFornitore("Best").get().getMiglioriProdotti(catena).keySet().toString());
        
        System.out.println("miglior fornitore di branzino 200g: - " + branzino200g.getIDMigliorFornitore(catena));
	}
}
