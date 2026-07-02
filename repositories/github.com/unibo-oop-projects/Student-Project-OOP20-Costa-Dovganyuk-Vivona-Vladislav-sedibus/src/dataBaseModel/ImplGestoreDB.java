package dataBaseModel;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.piantina.Prenotazione;
import model.utili.Periodo;
import model.piantina.PrenotazioneEstesa;

public final class ImplGestoreDB implements GestoreDB {

	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static final String PRANZO_FILE_PATH = System.getProperty("user.home") + System.getProperty("file.separator") + "pranzo.json";  
	private static final String CENA_FILE_PATH = System.getProperty("user.home") + System.getProperty("file.separator") + "cena.json";
	
	
	public ImplGestoreDB() {
		createFiles();
	}

	/**
	 * Create lunch and dinner files if they are not present
	 */
	private void createFiles() {
		if(!new File(PRANZO_FILE_PATH).exists()) {
			createNewFile(PRANZO_FILE_PATH);
		}else if(!new File(CENA_FILE_PATH).exists()) {
			createNewFile(CENA_FILE_PATH);
		}
	}
	
	private void createNewFile(String path) {
		try {
			new File(path).createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private String getPath(Periodo p) {
		return p.equals(Periodo.PRANZO) ? PRANZO_FILE_PATH : CENA_FILE_PATH;
	}

	
	// ritorna la mappa dove la chiave e la data(in formato Stringa) e la lista di prenotazioni associati alla data
	@Override
	public Map<String, List<Prenotazione>> getMapPrenotazioni(Periodo p) {
		// variabile "nuova" che conterra la mappa aggiornata
		Map<String, List<Prenotazione>> mappa = new HashMap<>();
		try {
			final Reader reader = Files.newBufferedReader(Paths.get(getPath(p)));
			
			final Type type = new TypeToken<Map<String, List<Prenotazione>>>(){}.getType();
			final Map<String, List<Prenotazione>> map =  gson.fromJson(reader, type) ;
			
			//se il file e vuoto il map sara null e non empty
			mappa = map == null ? mappa : map;

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return mappa;
	}

	@Override
	public void addToFile(PrenotazioneEstesa prenotazione) {
		// viene prelevata la mappa dal file giusto, cosi per poi aggiungere il nuovo elemento
		final var map = getMapPrenotazioni(prenotazione.getPeriodo());
		
		if (map.isEmpty() || !map.keySet().contains(prenotazione.getLocalData())) {
			map.put(prenotazione.getLocalData(), Arrays.asList(prenotazione.getPrenotazione()));
		} else {
			map.get(prenotazione.getLocalData()).add(prenotazione.getPrenotazione());
		}
		
		loadMapOnFile(map, prenotazione.getPeriodo());

	}

	@Override
	public void loadMapOnFile(Map<String, List<Prenotazione>> map, Periodo p) {
		try {
			final Writer writer = Files.newBufferedWriter(Paths.get(getPath(p)));
			gson.toJson(map, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
