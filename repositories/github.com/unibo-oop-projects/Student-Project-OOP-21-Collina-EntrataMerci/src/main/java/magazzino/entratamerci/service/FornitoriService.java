package magazzino.entratamerci.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import magazzino.entratamerci.controller.StorageController;
import magazzino.entratamerci.models.fornitore;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FornitoriService {

    /*
    * Retrieve di tutti i fornitori persistiti
    * */
    public ArrayList<fornitore> getFornitori() {
        try {
            String fornitoriSerialized = new String(Files.readAllBytes(Paths.get(StorageController.getFornitoriPath())));
            Gson gson = new Gson();
            Type fornitoreType = new TypeToken<ArrayList<fornitore>>(){}.getType();
            return gson.fromJson(fornitoriSerialized, fornitoreType);
        }catch (IOException ex){
            return  new ArrayList<>();
        }
    }
    /*
    *   Salvataggio in memoria di tutti i fornitori
    * @param fornitori lista dei fornitori da salvare su file
    * */
    public void setFornitori(ArrayList<fornitore> fornitori) throws IOException{
        Gson gson = new Gson();
        String jsonArr = gson.toJson(fornitori);
        try (PrintWriter out = new PrintWriter(StorageController.getFornitoriPath())) {
            out.println(jsonArr);
            out.close();
        }
    }
    /*
    * Retrieve di tutti i fornitori filtrati
    *
    * @param filter filtro testuale
    * */
    public ArrayList<fornitore> getFornitoriFiltered(String filter){
        ArrayList<fornitore> fornitori = getFornitori();
        return getFornitoriFiltered(fornitori,filter);
    }

    /*
     * Retrieve di tutti i fornitori filtrati
     *
     * @param fornitori subset di fornitori
     * @param filter filtro testuale
     * */
    public ArrayList<fornitore> getFornitoriFiltered(ArrayList<fornitore> fornitori, String filter){
        final String filterLower = filter.toLowerCase();
        return fornitori.stream().filter(x -> x.getCodice().toLowerCase().contains(filterLower) ||
                x.getDescrizione().toLowerCase().contains(filterLower)).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
     * Get del singolo fornitore filtrato per codice (PK)
     *
     * @param codice codice fornitore
     * */
    public fornitore getFornitoreByCodice(String codice){
        ArrayList<fornitore> fornitori = getFornitori();
        return getFornitoreByCodice(fornitori, codice);
    }
    /*
     * Get del singolo fornitore filtrato per codice (PK)
     *
     * @param fornitori subset di fornitori
     * @param codice codice del fornitore
     * */
    public  fornitore getFornitoreByCodice(ArrayList<fornitore> fornitori, String codice){
        return  fornitori.stream().filter(x-> x.getCodice().equals(codice)).findFirst().get();
    }

    /*
    * get se fornitore sia persistito o meno in anagrafica
    * @param codice codice fornitore
    * */
    public boolean isFornitoreEsistente(String codice) {
		ArrayList<fornitore> Fornitori = getFornitori();
		return isFornitoreEsistente(Fornitori,codice);
	}

    /*
     * get se fornitore sia persistito o meno in anagrafica
     * @param fornitori subset di fornitori
     * @param codice codice fornitore
     * */
	public boolean isFornitoreEsistente(ArrayList<fornitore> fornitori, String codice) {
		return fornitori.stream().filter(x-> x.getCodice().equals(codice)).count() > 0;
	}
}
