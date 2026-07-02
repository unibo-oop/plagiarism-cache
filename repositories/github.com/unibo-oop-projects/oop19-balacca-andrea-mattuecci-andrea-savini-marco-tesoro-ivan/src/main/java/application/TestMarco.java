package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class TestMarco {

	
	
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
             
    	 Date dt1 = parseDate("01/01/2020");
         
         Date dt2 = parseDate("01/08/2020");
                    
        WarehouseAddUtility wh = new WarehouseAddUtilityImpl();
        WarehouseModifyUtility wh1 = new WarehouseModifyUtilityImpl();
        WarehouseDeleteUtility wh2 = new WarehouseDeleteUtilityImpl();
        StoricFilter sf = new StoricFilterImpl();
                  
        Catena catena =  new Catena();//UtilityReadWriteCatena.getCatena();
        
       // Catena catena1 = new Catena();
        
        wh.addNewHotel(catena, "aragosta", "hotel bellissimo");
        wh1.modifyHotel(catena, "aragosta", "brutto", "medusa");
        wh.addNewDispensa(catena, "medusa", "pescheria");
        wh.addNewFornitore(catena, "Ice");
        wh.addNewTipologia(catena , "pesce" ,"razza", "spada");
        wh.addNewProdotto(catena, "spigola", "pesce", "testa", "10", "colore","giallo");
        wh.addNewProdConcreto(catena, "spigola200g", "spigola", "lisca", "2.0", "occhio", "verde");
        wh.addNewProdFornito(catena, "spigola200gIce", "spigola200g", "pene", "1.0", "nazione", "italia", "Ice", "2.0", "1");
        wh.addNewProdFornito(catena, "spigola200gIceB", "spigola200g", "pene", "1.0", "nazione", "italia", "Ice", "2.0", "1");
         
        //sf.search(catena,"aragosta", "pescheria", new Date(), new Date());
        //sf.search(catena,"aragosta", "pescheria", new Date(), new Date());
       
       
       wh1.modifyTipo(catena, "pesce", "cazzo", "piccola", "porcodio", "ciao");
      
       wh1.modifyTipoDispensa(catena, "pesce", "medusa", "pescheria", "pesceeee");
       
      wh1.modifyProdotto(catena, "spigola", "testa", "12", "squame1", "piccole", "porcamadonna", "cane", "prova");
      wh1.modifyFornitore(catena,"Ice", "spigola200Ice", "10", "nuovoooo");
      	//wh1.modifyProdotto(catena, "branzino11", "dss", "12", "squame1", "piccole", "porcogesu");
       wh1.modifyProdConcreto(catena, "spigola200g", "lisca", "12.0", "provenienza", "italia", "porcaladrodio" ,"dio", "prova1");
       wh1.modifyProdFornito(catena, "spigola200gIce", "pene", "12.0", "morto", "male", "Ice", "madonnalaida", "porco", "prova2");
       
   
       
      /*
       wh1.load(catena, "aragosta", "pescheria", "spigola200gIce", "10");  
       wh1.load(catena, "aragosta", "pescheria", "spigola200gIceB", "10");  
       wh1.load(catena, "aragosta", "pescheria", "spigola200gIce", "10");
       wh1.dump(catena, "aragosta", "pescheria", "spigola200gIce", "8");
       System.out.println("CARICHI" + " " + sf.searchload(catena, "aragosta", "pescheria", dt1, dt2));
       System.out.println("SCARICHI" + " " + sf.searchdump(catena, "aragosta", "pescheria", dt1, dt2));
      
       wh2.deleteTipo(catena, "pesce", "razza");
       
       wh2.deleteProdotto(catena, "spigola", "", "testa");
       wh2.deleteProdCon(catena, "spigola200g", "", "lisca");
       
       wh2.deleteProdFor(catena, "spigola200gIce", "morto", "pene");
       */
      // wh2.deleteProdCon(catena, "spigola200g", "occhio", "lisca");
       
       
       
      // UtilityReadWriteCatena.setCatena(catena);
       
       

    }
       
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
