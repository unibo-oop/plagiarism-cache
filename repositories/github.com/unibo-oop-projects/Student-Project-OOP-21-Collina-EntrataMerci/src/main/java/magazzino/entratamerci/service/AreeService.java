package magazzino.entratamerci.service;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import magazzino.entratamerci.controller.StorageController;
import magazzino.entratamerci.models.area;

public class AreeService {

	/*
	* Retrieve di tutte le aree da file formattate in JSON
	*
	* */
	public ArrayList<area> getAree(){
		try {
			String areeSerialized = new String(Files.readAllBytes(Paths.get(StorageController.getAreePath())));
			Gson gson = new Gson();
			Type areeType = new TypeToken<ArrayList<area>>(){}.getType();

			return gson.fromJson(areeSerialized, areeType);
		}catch (IOException ex){
			return new ArrayList<>();
		}
	}
	/*
	* Salvataggio su file della nuova struttura di magazzino
	* */
	public void setAree(ArrayList<area> aree) throws IOException{
		Gson gson = new Gson();
		String areeSerialized = gson.toJson(aree);
		try (PrintWriter out = new PrintWriter(StorageController.getAreePath())) {
			out.println(areeSerialized);
		}
	}
	/*
	* Get delle aree filtrate per per stringa, retrieve da file e chiamata al metodo con aree in memoria
	* */
	public ArrayList<area> getAreeFiltered(String filter){
		ArrayList<area> area = getAree();
		return getAreeFiltered(area,filter);
	}
	/*
	* Dato un subset di aree applica il filtro per codice/descrizione. Utilizzato per filtrare le maschere
	* */
	public ArrayList<area> getAreeFiltered(ArrayList<area> area,String filter){
		final String filterLower = filter.toLowerCase();
		return area.stream().filter(
				x -> x.getCodice().toLowerCase().contains(filterLower) || x.getDescrizione().toLowerCase()
						.contains(filterLower)).collect(Collectors.toCollection(ArrayList::new));
	}
	/*
	* Retrieve di singola area per codice area (PK), con lettura su file
	* */
	public  area getAreaByCodice(String codice){
		ArrayList<area> aree = getAree();
		return getAreaByCodice(aree, codice);
	}
	/*
	* Retrieve di singola area per codice area (PK), con lettura su file, dato un subset di aree in memoria
	* */
	public  area getAreaByCodice(ArrayList<area> aree, String codice){
		return  aree.stream().filter(x-> x.getCodice().equals(codice)).findFirst().get();
	}
}
