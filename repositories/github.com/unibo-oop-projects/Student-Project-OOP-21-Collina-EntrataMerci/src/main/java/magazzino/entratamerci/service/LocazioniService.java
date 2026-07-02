package magazzino.entratamerci.service;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import magazzino.entratamerci.controller.StorageController;
import magazzino.entratamerci.models.area;
import magazzino.entratamerci.models.locazione;

public class LocazioniService {

    /*
    * Retrieve di tutte le locazioni persistite
    * */
    public ArrayList<locazione> getLocazioni(){
        try {
        Gson gson = new Gson();
        String locazioniSerialized = new String(Files.readAllBytes(Paths.get(StorageController.getLocazionePath())));
        Type locazioniType = new TypeToken<ArrayList<locazione>>(){}.getType();
        return gson.fromJson(locazioniSerialized, locazioniType);
        }catch (IOException ex){
            return  new ArrayList<>();
        }

    }
    /*
    * Salvataggio di tutte le locazioni, per aggiornare la nuova struttura
    *
    * @param locazioni locazioni da salvare su file
    * */
    public void setLocazioni(ArrayList<locazione> locazioni) throws IOException{
        Gson gson = new Gson();
        String jsonArr = gson.toJson(locazioni);
        try (PrintWriter out = new PrintWriter(StorageController.getLocazionePath())) {
            out.println(jsonArr);
            out.close();
        }
    }
    /*
    * retrieve di tutte le locazioni filtrate
    * @param filter filtro testuale
    * */
    public ArrayList<locazione> getLocazioniFiltered(String filter){
        ArrayList<locazione> locazioni = getLocazioni();
        return getLocazioniFiltered(locazioni,filter);
    }
    /*
     * retrieve di tutte le locazioni filtrate
     * @param locazioni subset di locazioni
     * @param filter filtro testuale
     *
     * */
    public ArrayList<locazione> getLocazioniFiltered(ArrayList<locazione> locazioni,String filter){
        final String filterLower = filter.toLowerCase();
        return locazioni.stream().filter(
                x -> x.getCodice().toLowerCase().contains(filterLower) || x.getDescrizione().toLowerCase()
                        .contains(filterLower)).collect(Collectors.toCollection(ArrayList::new));
    }

    /*
    * Retrieve di tutte le locazioni di un'area
    *
    * @param area area per cui cercare le locazioni
    * */
    public ArrayList<locazione> getLocazioniByArea(area area) throws IOException{
        ArrayList<locazione> locazioni = getLocazioni();
        return getLocazioniByArea(locazioni,area);
    }
    /*
     * Retrieve di tutte le locazioni di un'area
     *
     * @param locazioni subset di locazioni
     * @param area area per cui cercare le locazioni
     * */
    public ArrayList<locazione> getLocazioniByArea(ArrayList<locazione> locazioni, area area){
        return locazioni.stream().filter(
                x -> x.getArea().equals(area)).collect(Collectors.toCollection(ArrayList::new));
    }
    /*
    * retrieve della locazione by codice (PK)
    *
    * @param codice locazione codice
    * */
    public  locazione getLocazioneByCodice(String codice) {
		ArrayList<locazione> aree = getLocazioni();
		return getLocazioneByCodice(aree, codice);
	}

    /*
     * retrieve della locazione by codice (PK)
     *
     * @param loc subset di locazioni
     * @param codice locazione codice
     * */
	public  locazione getLocazioneByCodice(ArrayList<locazione> loc, String codice){
		return  loc.stream().filter(x-> x.getCodice().equals(codice)).findFirst().get();
	}
}
