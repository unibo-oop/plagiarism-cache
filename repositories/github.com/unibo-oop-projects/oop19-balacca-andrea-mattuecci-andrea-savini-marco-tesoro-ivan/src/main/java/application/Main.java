package application;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import com.google.common.base.Optional;

import application.Consumi.Cliente;
import application.Consumi.Pasto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main extends Application {

    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 300;

    public void prove(Stage stage, Scene scene, Parent root) throws FileNotFoundException, IOException {
        // prove generali
        Catena catena = new Catena();
        Hotel Aragosta = new Hotel("Aragosta");
        // Catena.setMYLOGGER(new MyLogger());

        MyLogger.AggiungiUnLogger("Admin");
        MyLogger.OttieniUnLogger("Admin").info("Aggiunto Hotel Aragosta");

        catena.getAlberghi().add(Aragosta);
        Aragosta.aggiungiUnaDispensa(new Dispensa("Pesceria", catena));

        MyLogger.AggiungiUnLogger("Dipendente");
        MyLogger.OttieniUnLogger("Dipendente").info("Aggiunta dispensa nell'Hotel Aragosta");

        MyLogger.OttieniUnLogger("Admin").info("ProvaAdmin");

        MyLogger.OttieniUnLogger("Dipendente").info("ProvaDipen");

        HashMap<String, String> mappa = new HashMap<String, String>();
        HashMap<String, String> mappa2 = new HashMap<String, String>();
        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<String> lista2 = new ArrayList<String>();
        Tipologia pesce = new Tipologia("Pesce"); // creo il pesce
        Prodotto branzino = new Prodotto("Branzino", pesce); // creo il branzino dicendogli che è di tipo pesce
        ProdConcreto branzino200g = new ProdConcreto("Branzino200g", branzino);
        ProdFornito branzino200gGeloService = new ProdFornito("Branzino200gGeloService", branzino200g);
        // catena.inventario.add(branzino200gGeloService);
        mappa.put("Colore", "Rosso");
        mappa.put("Provenienza", ""); //
        pesce.aggiungiInfo(mappa); // aggiungo al pesce le info "Colore" = "Rosso" e "Provenienza" = ""
        // mappa2.put("Colore", "Verde");
        // branzino.modificaInfo(mappa2);
        lista.add("Colore");
        lista2.add(branzino200gGeloService.ottieniInfo(lista).get(0).get()); // richiedo l'informazione "Colore" al branzino
        lista2.get(0); // = "Rosso", cioè il branzino ha restituito il valore che presente a livello
                       // superiore

        mappa2.put("Provenienza", "Italia");
        branzino200g.modificaInfo(mappa2); // definisco la provenienza (a livello superiore vale "") a livello branzino200g
        
        mappa2.clear();
        
        mappa2.put("Quality", "Good");
        
        branzino.aggiungiInfo(mappa2);
        
        System.out.println(branzino.ottieniInfoTotali().toString()); // stampa {Colore=Rosso, Provenienza=, Quality=Good}
        
        ArrayList<Scarto> listas = new ArrayList<>();
        
        listas.add(new Scarto("ScartoA"));
        
        branzino.aggiungiScarti(listas); //così aggiungo
        
        Scarto scartoa = new Scarto("ScartoA");
        scartoa.setQuantita(Float.valueOf(0));
        scartoa.setQuantita((float) 50);
        scartoa.setPercentuale(true);
        
        listas.clear();
        listas.add(scartoa);
        
        branzino200gGeloService.modificaScarti(listas); //così modifico (ovviamente devo passare un oggetto scartoa diverso da quello precedente altrimenti mantiene i riferimenti)
        
        scartoa.setQuantita(Float.valueOf(0));
        
        System.out.println(branzino200gGeloService.ottieniScartiTotali().get(0).getQuantita().toString());
        
        lista.clear();
        lista.add("ScartoA");
        
        System.out.println(branzino200g.ottieniScarti(lista).get(0).get().getQuantita().toString()); 
        
        lista.add("Provenienza");
        lista2.add(branzino200gGeloService.ottieniInfo(lista).get(1).get()); // chiedo l'informazione al livello più basso
        lista2.get(1); // = "Italia", cioè ha prelevato l'informazione definita dal livello superiore

        mappa2.clear();
        
        mappa2.put("Taglio", "Intero");
        
        branzino200gGeloService.aggiungiInfo(mappa2);
        
        System.out.println(branzino200gGeloService.ottieniInfoTotali().toString());
        
        branzino200gGeloService.setPrezzo((float)4);
        branzino200gGeloService.setValoreAssoluto((float)0.2);
        branzino200gGeloService.setIDFornitore("Gelo");
        
        System.out.println(branzino200gGeloService.getPrezzoEffettivo().toString() + " -- " + branzino200gGeloService.getValoreNetto());
        
        ProdFornito branzino200gBestForn = new ProdFornito("Branzino200gBestForn", branzino200g);
        branzino200gBestForn.setPrezzo((float)3.5);
        branzino200gBestForn.setValoreAssoluto((float)0.2);
        branzino200gBestForn.setIDFornitore("Best");
        
        catena.aggiungiUnFornitore(new Fornitore("Best"));
        catena.aggiungiUnFornitore(new Fornitore("Gelo"));
        
        ArrayList<Typology> ll = new ArrayList<Typology>();
        ll.add(pesce); ll.add(branzino); ll.add(branzino200g); ll.add(branzino200gBestForn); ll.add(branzino200gGeloService);
        catena.aggiungiAllInventario(ll);
        
        //non metto alcuno scarto
        System.out.println(branzino200gBestForn.getPrezzoEffettivo().toString() + " -- " + branzino200gBestForn.getValoreNetto());
        
        System.out.println("migliori prodotti gelo: - " + catena.ottieniUnFornitore("Gelo").get().getMiglioriProdotti(catena).keySet().toString());
        System.out.println("migliori prodotti best forn: - " + catena.ottieniUnFornitore("Best").get().getMiglioriProdotti(catena).keySet().toString());
        
        System.out.println("miglior fornitore di branzino 200g: - " + branzino200g.getIDMigliorFornitore(catena));
        
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("file.dat"))));
        // Persona p = new Persona("Mario", "Rossi");
        out.writeObject(catena);
        out.close();
        
        System.out.println(System.getProperty("java.home"));

        // ObjectOutputStream outlog = new ObjectOutputStream(new
        // BufferedOutputStream(new FileOutputStream(new File("filelog.dat"))));
        // outlog.writeObject(MyLogger.class);
        // outlog.close();

    }

    public void leggi(Stage stage, Scene scene, Parent root) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("file.dat"))));
        Catena c = (Catena) in.readObject(); // Eventualmente inserire un casting esplicito
        Hotel arag = (Hotel) c.ottieniUnAlbergo("Aragosta").get(); // faccio la get sull'optional
        stage.setTitle(arag.ottieniUnaDispensa("Pesceria").get().getNome()); // ---> = Pesceria
        in.close();

        // ObjectInputStream inlog = new ObjectInputStream(new BufferedInputStream(new
        // FileInputStream(new File("filelog.dat"))));
        // Catena.setMYLOGGER((MyLogger) inlog.readObject());
        // inlog.close();

        // I logger non sono serializzabili, perciò ad ogni avvio vanno creati i logger
        // associati ad ogni utente
        MyLogger.AggiungiUnLogger("Admin");
        MyLogger.OttieniUnLogger("Admin").info(
                "ProvaAdmin in realtà senza serializzazione, ho semplicemente rigenerato il logger passandogli il nome utente");

        // MyLogger.OttieniUnLogger("Dipendente").info("ProvaDipen con
        // serializzazione");
    }

    @Override
    public void start(final Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/main.fxml"));
        final Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        // stage.setTitle("ciao");

        prove(stage, scene, root);
        //leggi(stage, scene, root);

        // Stage configuration
        // stage.setTitle("ciao"); //qui c'era il titolo originale, ho usato l'outup del
        // titolo per alcune verifiche
        stage.setScene(scene);
        stage.show();
    }
    
    static Catena catena;
    public static void testBala() {
        Date dt1 = parseDate("01/08/2020");
        
        Date dt2 = parseDate("02/08/2020");
        
        Date dt3 = parseDate("10/08/2020");
        
        Date dt4 = parseDate("10/10/2030");
        
        catena = new Catena(); 
        Hotel Aragosta = new Hotel("Aragosta");
        
        catena.getAlberghi().add(Aragosta);
        
        //UtilityCatenaTemp.setCatena(catena);
        
        //
        Date d = parseDate("01/08/2020");
        Date d2 = parseDate("02/08/2020");
        Date d3 = parseDate("03/08/2020");
        Date d4 = parseDate("04/08/2020");
        Date d5 = parseDate("05/08/2020");
        Date d6 = parseDate("06/08/2020");
        Date d7 = parseDate("07/08/2020");
        Date d8 = parseDate("08/08/2020");
        Date d9 = parseDate("09/08/2020");
        Date d10 = parseDate("10/08/2020");
        Date d11 = parseDate("11/08/2020");
        Date d12 = parseDate("12/08/2020");
        Date d13 = parseDate("13/08/2020");
        Date d14 = parseDate("14/08/2020");
        Date d15 = parseDate("15/08/2020");
        Date d16 = parseDate("16/08/2020");
        Date d17 = parseDate("17/08/2020");
        Date d18 = parseDate("18/08/2020");
        Date d19 = parseDate("19/08/2020");
        Date d20 = parseDate("20/08/2020");
        
        Date d30 = parseDate("21/08/2020");
        Date d31 = parseDate("22/08/2020");

        
        catena.ottieniUnAlbergo("Aragosta").get().aggiungiUnNPranzo(d, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d2, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d3, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d4, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d5, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d6, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d7, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d8, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d9, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d10, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d11, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d12, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d13, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d14, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d15, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d16, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d17, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d18, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d19, 2, 4); 
        Aragosta.aggiungiUnNPranzo(d20, 2, 4); 


        
        
        //
        
        Aragosta.aggiungiUnNPranzo(dt1, 2, 4); 
        Aragosta.aggiungiUnNPranzo(dt2, 2, 4);
        Aragosta.aggiungiUnNPranzo(dt3, 2, 4);
        
        
        Aragosta.aggiungiUnaDispensa(new Dispensa("Pesceria", catena));
        
        HashMap<String,String> mappa = new HashMap<String, String>();
        
        Tipologia pesce = new Tipologia("Pesce"); //creo il pesce Prodotto branzino =
        
        ProdFornito tonno200gGeloService = 
                new ProdFornito("Tonno200gGeloService",
                        new ProdConcreto("Tonno200g", 
                                new Prodotto("Tonno", pesce)));
        tonno200gGeloService.setValoreAssoluto((float) 0.2);
                
        ProdFornito branzino200gGeloService = 
                new ProdFornito("Branzino200gGeloService",
                        new ProdConcreto("Branzino200g", 
                                new Prodotto("Branzino", pesce)));
        branzino200gGeloService.setValoreAssoluto((float) 0.2);

        Aragosta.aggiungiUnaDispensa(new Dispensa("Carne", catena));
        Tipologia carne = new Tipologia("Carne");
        
        
        ProdFornito vitello100gMacelleria = 
                new ProdFornito("Vitello100gMacelleria",
                        new ProdConcreto("Vitello100g", 
                                new Prodotto("Vitello", carne)));
        vitello100gMacelleria.setValoreAssoluto((float) 0.1);
        
        ProdFornito pollo100gMacelleria = 
                new ProdFornito("Pollo100gMacelleria", 
                        new ProdConcreto("Pollo100g", 
                                new Prodotto("Pollo", carne)));
        pollo100gMacelleria.setValoreAssoluto((float) 0.1);

        
        ProdFornito branzino150gpescedelivery = 
        		new ProdFornito("Branzino150gpescedelivery", 
        				new ProdConcreto("Branzino150g", 
        						new Prodotto("Branzino", pesce)));
        branzino150gpescedelivery.setValoreAssoluto((float) 0.15);
        
        ProdFornito cavallo200gcarnedelivery = 
        		new ProdFornito("Cavallo200gcarnedelivery", 
        				new ProdConcreto("Cavallo200g", 
        						new Prodotto("Cavallo", carne)));
        cavallo200gcarnedelivery.setValoreAssoluto((float) 0.20);

        
        ArrayList<Typology> t = new ArrayList<Typology>();
        t.add(pesce);
        t.add(carne);
        t.add(tonno200gGeloService);
        t.add(branzino200gGeloService);
        t.add(vitello100gMacelleria);
        t.add(pollo100gMacelleria);
        t.add(branzino150gpescedelivery);
        t.add(cavallo200gcarnedelivery);
        catena.aggiungiAllInventario(t);
        
        
        System.out.println("inventario: " + catena.getInventario());
        TreeMap<Date,HashMap<String,Float>> consumiGiornAdultiPranzo = new TreeMap<Date,HashMap<String,Float>>();
        TreeMap<Date,HashMap<String,Float>> consumiGiornBambiniPranzo = new TreeMap<Date,HashMap<String,Float>>();
        
        //
        for (Date dd = d; dd.before(d20) || dd.equals(d20); dd = UtilityDate.sumDay(dd, 1)) {
        	//System.out.println("data: " + dd);
        	HashMap<String,Float> c = new HashMap<String, Float>();
            Random rnd = new Random();
            int rand = rnd.nextInt(100) + 5;
            for (int i = 0; i < rand; i++) {
            	c.put(vitello100gMacelleria.getID(), (float) rnd.nextInt(1000) + 6);
            	c.put(branzino200gGeloService.getID(), (float) rnd.nextInt(1000) + 6);
            	c.put(tonno200gGeloService.getID(), (float) rnd.nextInt(1000) + 6);
            	c.put(pollo100gMacelleria.getID(), (float) rnd.nextInt(1000) + 6);
            }
            consumiGiornAdultiPranzo.put(dd, c); 
        }
        Aragosta.getConsumiAdultiPranzo().putAll(consumiGiornAdultiPranzo);
        //Aragosta.setConsumiAdultiPranzo(consumiGiornAdultiPranzo);
        
        //
        /*
        consumi.put(vitello100gMacelleria.getID(), (float) 10);
        consumi.put(branzino200gGeloService.getID(), (float) 20);
        consumi.put(tonno200gGeloService.getID(), (float) 200);
        consumi.put(cavallo200gcarnedelivery.getID(), (float) 2000);*/
        /*
        HashMap<String,Float> consumi2 = new HashMap<String, Float>();
        consumi2.put(pollo100gMacelleria.getID(), (float) 50);
        consumi2.put(tonno200gGeloService.getID(), (float) 200);
        consumi2.put(branzino150gpescedelivery.getID(), (float) 1000);
        
        
        HashMap<String,Float> consumi3 = new HashMap<String, Float>();
        //consumi3.put(vitello100gMacelleria.getID(), (float) 35);
        consumi3.put(branzino200gGeloService.getID(), (float) 500);
        consumi3.put(vitello100gMacelleria.getID(), (float) 1000);
        consumi3.put(pollo100gMacelleria.getID(), (float) 50);
        
        
        consumiGiornAdultiPranzo.put(dt2, consumi2);
        consumiGiornAdultiPranzo.put(dt4, consumi3);
        //consumiGiornBambiniPranzo.put(dt1, consumi);
        
        Aragosta.setConsumiAdultiPranzo(consumiGiornAdultiPranzo);
        Aragosta.setConsumiBimbiPranzo(consumiGiornBambiniPranzo);
        */
        Consumi cons = new ConsumiImpl(Aragosta);
        
        Previsioni prev = new PrevisioniImpl(cons);
        
        DrawGraphImpl dg = new DrawGraphImpl(cons, catena);
        DrawGraphImpl dg2 = new DrawGraphImpl(prev, catena);
        
        
        try {
            //System.out.println(cons.getConsumiRangeDate(dt1, dt2, Pasto.PRANZO, Cliente.ADULTO));
            //System.out.println(cons.getConsumoProCapiteDataSingola(dt2, Pasto.PRANZO, Cliente.ADULTO));
        	/*System.out.println(tmp.get(tonno200gGeloService.getID()));
        	System.out.println(tonno200gGeloService.getID());
        	System.out.println(tonno200gGeloService.getPadre().getID());
        	System.out.println(tonno200gGeloService.getPadre().getPadre().getID());
        	System.out.println(tonno200gGeloService.getPadre().getPadre().getPadre().getID());*/
        	//System.out.println(((Tipologia)catena.getInventario().get(0)).getID());
        	//System.out.println("rd ad" + cons.getConsumi(Pasto.PRANZO, Cliente.ADULTO, d, d20));
        	/*for(var a: cons.getConsumi(Pasto.PRANZO, Cliente.ADULTO, d, d20).entrySet()) {
        		for (var b: a.getValue().entrySet()) {
        			if(b.getKey().contains("Vitello")) {
        				System.out.println(b);
        			}
        		}
        	}
        	///System.out.println("rd bm" + cons.getConsumiRangeDate(dt1, dt4, Pasto.PRANZO, Cliente.BAMBINO));
        	System.out.println("------");
        	for(var a: prev.getPrevisioni(Pasto.PRANZO, Cliente.ADULTO, 2, d2, d20).entrySet()) {
        		for (var b: a.getValue().entrySet()) {
        			if(b.getKey().contains("Vitello")) {
        				System.out.println(b);
        			}
        		}
        	}*/
        	//System.out.println("prev" + prev.getPrevisioni(Pasto.PRANZO, Cliente.ADULTO, 2, d30));
        	System.out.println("consumi" + cons.getConsumi(Pasto.PRANZO, Cliente.ADULTO, d, d20));
        	System.out.println("graf consumi " + dg.getGraphConsumi(d, d20, "Vitello"));
        	//DisplayGraph tes = new DisplayGraph(cons, catena, d2, d20, "Vitello", "Branzino", "Tonno", "Pollo");
        	//tes.setVisible(true);
        	/*System.out.println(cons.getConsumiRangeDate(dt1, dt3, Pasto.PRANZO, Cliente.ADULTO));
            System.out.println(prev.getPrevisioniDataSingola(dt3, Pasto.PRANZO, Cliente.ADULTO, 5));*/
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        }
        try {
			UtilityReadWriteCatena.setCatena(catena);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * 
     * @param args
     *                 unused
     */
    public static void main(final String[] args) {
    	////roba di bala
    	/*
    	try {
			UtilityReadWriteCatena.setCatena(new Catena());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	try {
			UtilityReadWriteCatena.getCatena();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	try {
			UtilityBackupAndRestore.makeBackup(new Catena());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	UtilityBackupAndRestore.restoreBackup();
    	*/
        //Main.testBala();
        /*
         * Tipologia pesce = new Tipologia("Pesce"); //creo il pesce Prodotto branzino =
         * new Prodotto("Branzino", pesce);
         * 
         * FileUtility f = new FileUtilityImpl("prova1"); f.createFile();
         * 
         * try { WarehouseUtility stampa1 = new WarehouseUtilityImpl(f.getFile());
         * stampa1.loads(f.getFile(), branzino); } catch (IOExmeception e) { // TODO
         * Auto-generated catch block e.printStackTrace(); }
         * 
         * /* FileUtility f1 = new FileUtilityImpl("prova2");
         * 
         * try { WarehouseUtility stampa1 = new WarehouseUtilityImpl(f1.getFile());
         * stampa1.loads(f1.getFile(), branzino); } catch (IOException e) { // TODO
         * Auto-generated catch block e.printStackTrace(); }
         * 
         * /* FileUtility f2 = new FileUtilityImpl("prova2"); f2.createFile();
         * f2.removeFile(f2.getFile()); //al secondo tentativo deve dire che non può
         * eliminare il file f2.removeFile(f2.getFile());
         * 
         * f.removeFile(f.getFile());
         */

        //launch();
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
