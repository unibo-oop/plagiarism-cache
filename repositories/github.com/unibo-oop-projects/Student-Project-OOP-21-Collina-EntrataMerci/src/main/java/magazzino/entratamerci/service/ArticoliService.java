package magazzino.entratamerci.service;


import java.io.File;
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
import magazzino.entratamerci.models.articolo;

public class ArticoliService {

	/*
	* retrieve di tutti gli articoli da file, includendo gli obsoleti
	* */
	public ArrayList<articolo> getArticoli(){
		return getArticoli(true);
	}
	/*
	* Retrieve di tutti gli articoli non obsoleti, utile in fase di inserimento
	* */
	public ArrayList<articolo> getArticoliNonObsoleti(){
		return getArticoli(false);
	}

	/*
	* Retrieve degli articoli, con scelta se includere obsoleti o meno
	*  @param includiObsoleti boolean che indica o meno se includere anche gli articoli obsoleti al risultato
	* */
	public ArrayList<articolo> getArticoli(boolean includiObsoleti){
		try{
			String articoliSerialized = new String(Files.readAllBytes(Paths.get(StorageController.getArticoliPath())));
			Gson gson = new Gson();
			Type articoliType = new TypeToken<ArrayList<articolo>>(){}.getType();
			ArrayList<articolo> articoli = gson.fromJson(articoliSerialized, articoliType);

			if(!includiObsoleti){
				articoli = articoli.stream().filter(x-> x.isObsoleto() == false).collect(Collectors.toCollection(ArrayList::new));
			}

			return articoli;
		}catch (IOException ex){
			return  new ArrayList<>();
		}

	}

	/*
	* Salvataggio degli articoli su file per persistere le modifiche
	*
	* @param articoli lista di articoli da salvare
	* */
	public void setArticoli(ArrayList<articolo> articoli) throws IOException{
		Gson gson = new Gson();
		String jsonArr = gson.toJson(articoli);
		try (PrintWriter out = new PrintWriter(StorageController.getArticoliPath())) {
			out.println(jsonArr);
			out.close();
		}
	}
	/*
	* Retrieve degli articoli filtrati scegliendo se includere o meno gli obsoleti. Retrieve da file
	*  @param filter filtro testuale
	*  @param includeObsolete booleano che indica se includere o meno gli articoli obsoleti
	* */
	public ArrayList<articolo> getArticoliFiltered(String filter, boolean includeObsolete){
		ArrayList<articolo> articoli = getArticoli();
		return getArticoliFiltered(articoli,filter, includeObsolete);
	}
	/*
	* Retrieve degli articoli filtrati
	*
	* @param articoli lista di articoli
	* @param filter filtro testuale
	* @param includiObsoleti booleano che indica se includere o meno gli articoli obsoleti
	* */
	public ArrayList<articolo> getArticoliFiltered(ArrayList<articolo> articoli,String filter, boolean includiObsoleti){
		final String filterLower = filter.toLowerCase();

		Stream<articolo> articoliFiltered = articoli.stream().filter(x-> x.getCodice().toLowerCase().contains(filterLower) || x.getDescrizione().toLowerCase().contains(filterLower));

		if(!includiObsoleti){
			articoliFiltered = articoliFiltered.filter(x-> !x.isObsoleto());
		}

		return articoliFiltered.collect(Collectors.toCollection(ArrayList::new));
	}

	/*
	* Retrieve di singolo articolo per codice
	*
	* @param codice codice articolo (PK)
	* */
	public articolo getArticoloByCodice(String codice){
		ArrayList<articolo> articoli = getArticoli();
		return getArticoloByCodice(articoli, codice);
	}
	/*
	 * Retrieve di singolo articolo per codice dato un subset di articoli
	 * @param articoli subset di articoli
	 * @param codice codice articolo (PK)
	 * */
	public  articolo getArticoloByCodice(ArrayList<articolo> articoli, String codice){
		return  articoli.stream().filter(x-> x.getCodice().equals(codice)).findFirst().get();
	}

	/*
	 * Verifica se articolo esistente o meno gia in anagrafica
	 *
	 * @param codice codice articolo (PK)
	 * */
	public boolean isArticoloEsistente(String codice) {
		ArrayList<articolo> articoli = getArticoli();
		return isArticoloEsistente(articoli,codice);
	}

	/*
	 * Retrieve di singolo articolo per codice
	 * @param articoli subset di articoli
	 * @param codice codice articolo (PK)
	 * */
	public boolean isArticoloEsistente(ArrayList<articolo> articoli, String codice) {
		return articoli.stream().filter(x-> x.getCodice().equals(codice)).count() > 0;
	}
}
