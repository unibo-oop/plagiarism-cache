package controller.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.games.Game;

public class PersistentFileSystem extends PersistentBase {
	
	public static final String LOCAL_DIR = System.getProperty("user.home") + System.getProperty("file.separator") + "MastermindPro";
	public static final String PENDING_FILEPATH = LOCAL_DIR + System.getProperty("file.separator") + "PendingGame.sb";
    public static final String ARCHIVE_FILEPATH = LOCAL_DIR + System.getProperty("file.separator") + "Archive.sb";
    
	@Override
	public void savePendingGame(Game game) throws PersistentException {

		try {
			
			ensureFileExists(PENDING_FILEPATH);
			
			try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(PENDING_FILEPATH))) {
				writer.writeObject(game);
			}
			
			/*
			try (Writer writer = new FileWriter(PENDING_FILEPATH)) {
			    Gson gson = createGson();
			    gson.toJson(game, writer);
			    
			    
			    //List<Game> games = new ArrayList<Game>();
			    //games.add(game);
			    //gson.toJson(game, writer);
			}
			*/
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
		
	}

	@Override
	public Optional<Game> loadPendingGame() throws PersistentException {
		
		Game game;
		
		try {
			
			/*
			try (Reader reader = new FileReader(PENDING_FILEPATH)) {
			    Gson gson = createGson();
			    
			    game = (Game) gson.fromJson(reader, Game.class);
			}
			*/
			
			if(!checkFileExists(PENDING_FILEPATH)) {
				return Optional.empty();
			}
			
			try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(PENDING_FILEPATH))) {
				game = (Game) reader.readObject();
			}
			
			return Optional.of(game);
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}

	@Override
	public void archiveGame(Game game) throws PersistentException {
		
		try {
			
			List<Game> archive;
			
			archive = loadArchivedGames();
			
			if(archive.size() == 0) {
				//non esiste, lo creo
				ensureFileExists(ARCHIVE_FILEPATH);
				
			}

			//aggiungo il game da archiviare in coda
			archive.add(game);
			
			try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(ARCHIVE_FILEPATH))) {
				writer.writeObject(archive);
			}
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}

	@Override
	public List<Game> loadArchivedGames() throws PersistentException {

		try {
			
			List<Game> games;
			
			if(!checkFileExists(ARCHIVE_FILEPATH)) {
				//il file non esiste, ritorno lista vuota
				return new ArrayList<Game>();
			}
			
			try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(ARCHIVE_FILEPATH))) {
				games = (List<Game>) reader.readObject();
			}
			
			return games;
		}
		catch(Exception e) {
			throw new PersistentException(e);
		}
	}

	@Override
	public void clearPendingGame() {
		File f = new File(PENDING_FILEPATH);
		if(f.exists()) {
			f.delete();
		}
	}

	private boolean checkFileExists(String filePath) {
		File f = new File(filePath);
		return f.exists();
	}
	
	private void ensureFileExists(String filePath) throws IOException {
		File file = new File(filePath);
		
		//creo la cartella se non esiste già
		file.getParentFile().mkdirs();
		
		//creo il file se non esiste già
		if(!file.exists()) {
			file.createNewFile();
		}
	}
}
