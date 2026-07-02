package magazzino.entratamerci.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import magazzino.entratamerci.controller.StorageController;
import magazzino.entratamerci.models.ordine;

public class OrdiniService {

    /*
    * Retrieve di tutti gli ordini persistiti
    * */
    public ArrayList<ordine> getOrdini(){
        try{
            Gson gson = new Gson();
            String ordiniSerialized = new String(Files.readAllBytes(Paths.get(StorageController.getOrdiniPath())));

            Type ordiniType = new TypeToken<ArrayList<ordine>>(){}.getType();
            return gson.fromJson(ordiniSerialized, ordiniType);
        }catch (IOException ex) {
            return  new ArrayList<>();
        }

    }

    /*
     * Salvataggio degli ordini su file
     *
     * @param ordine ordini da salvare su file
     * */
    public void setOrdini(ArrayList<ordine> ordine) throws IOException{
        Gson gson = new Gson();
        String jsonArr = gson.toJson(ordine);
        try (PrintWriter out = new PrintWriter(StorageController.getOrdiniPath())) {
            out.println(jsonArr);
        }
    }
    /*
     * Salvataggio dell'aggiunta dell'ordine su file
     *
     * @param ordine ordine da aggiungere
     * */
    public void addOrdine(ordine ordine) throws IOException {
        ArrayList<ordine> ordini = getOrdini();
        ordini.add(ordine);
        setOrdini(ordini);
    }
    /*
    * retrieve degli ordini filtrati
    *
    * @param filter testuale
    * */
    public ArrayList<ordine> getOrdiniFiltered(String filter){
        ArrayList<ordine> ordine = getOrdini();
        return getOrdiniFiltered(ordine,filter);
    }
    /*
     * retrieve degli ordini filtrati
     *
     * @param ordine subset di ordini
     * @param filter testuale
     * */
    public ArrayList<ordine> getOrdiniFiltered(ArrayList<ordine> ordine, String filter){
        final String filterLower = filter.toLowerCase();
        return ordine.stream().filter(x -> x.getAnnoNumero().toLowerCase().contains(filterLower) ||
                x.getFornitore().getCodice().toLowerCase().contains(filterLower) ||
                x.getFornitore().getDescrizione().toLowerCase().contains(filterLower)).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
    * Retrieve del progressivo prossimo dell'ordine.
    * Progressivo basato su anno e sequenziale numero
    * */
    public int getProgressivoNextOrdine() {
        ArrayList<ordine> ordini = getOrdini();
        return getProgressivoNextOrdine(ordini);
    }

    /*
     * Retrieve del progressivo prossimo dell'ordine.
     * Progressivo basato su anno e sequenziale numero
     *
     * @param ordini subset di tutti gli ordini
     * */
    public int getProgressivoNextOrdine(ArrayList<ordine> ordini) {
        int currentYear = LocalDateTime.now().getYear();
        return ordini.stream().mapToInt(x-> x.getNumero()).max().getAsInt() + 1;
    }

}
