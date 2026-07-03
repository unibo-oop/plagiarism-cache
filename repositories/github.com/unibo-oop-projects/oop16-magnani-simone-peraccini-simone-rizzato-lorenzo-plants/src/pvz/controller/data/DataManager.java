package pvz.controller.data;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import pvz.controller.Mode;


public class DataManager implements DataManagerInterface {
	
	private final File playersDir = new File(Path.PLAYERSINFO_DIR.getPath());
	private final File highScoresDir = new File(Path.HIGHSCORES_DIR.getPath());
	private final File backUpDir = new File(Path.PVZ_DIR.getPath());
	private final File arcadeHighScores = new File(Path.ARCADE_HISHSCORES_FILE.getPath());

	private Map<Mode, List<Score>> allHighScores;
	private Map<Mode, List<Score>> highScoresCache = new HashMap<>();
	private Optional<Player> currentPlayer;
	private String playerName;

	public DataManager() throws Exception{
		  this.setDirectories();
		  
		  for(Mode mode: Mode.getAvailableModes()){
			  
			  try(JsonReader reader = new JsonReader(new FileReader(mode.getModePath()))){
					 final Type type =  new TypeToken<List<Score>>(){}.getType();
					 Gson gson = new GsonBuilder().create();
					 
					 List<Score> scoreList = gson.fromJson(reader,type);
					 
					 this.allHighScores.put(mode,scoreList);
					 
				 } catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			  
			  
		  }
		  

	}

	private Player getPlayer() {
		return this.currentPlayer.get();
	}

	@Override

	public void addScore(Score score, Mode mode) {
		if (!(this.allHighScores.get(mode).contains(score))) {
			this.allHighScores.get(mode).add(score);
			this.allHighScores.get(mode).sort((s1, s2) -> s1.getScore() - s2.getScore());
			this.highScoresCache.get(mode).add(score);
		}
	}

	@Override

	public void setPlayer(String playerName) {
		if (this.playerAlreadyExist(playerName)) {

			this.playerName = playerName;

			try (JsonReader reader = new JsonReader(new FileReader(this.currentPlayerPath()))) {
				Gson gson = new GsonBuilder().create();
				this.currentPlayer = Optional.of(gson.fromJson(reader, PlayerImpl.class));

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			throw new IllegalStateException();
		}

	}

	@Override
	public boolean registerAndSetPlayer(String playerName) {
		this.checkDirectories();

		if (this.playerAlreadyExist(playerName)) {
			return false;
		} else {

			this.playerName = playerName;
			File newPlayerFile = new File(this.currentPlayerPath());

			try (final Writer writer = new FileWriter(this.currentPlayerPath());) {

				if (!newPlayerFile.createNewFile()) {
					throw new Exception();
				}

				this.currentPlayer = Optional.of(new PlayerImpl(playerName));
				this.playerName = playerName;

				Gson gson = new GsonBuilder().create();
				gson.toJson(this.currentPlayer, writer);

			} catch (final Exception e) {
				e.printStackTrace();
			}
			return true;
		}

	}

	@Override
	public Map<Mode, List<Score>> getPlayerHighScores(final String playerName) {

		Map<Mode, List<Score>> allScoreMap = new HashMap<>();

		for (Mode mode : this.allHighScores.keySet()) {
			List<Score> list = this.allHighScores.get(mode).stream().filter(e -> e.getPlayer().equals(playerName))
					.collect(Collectors.toList());
			allScoreMap.put(mode, list);
		}

		return allScoreMap;
	}

	@Override
	public Map<Mode, List<Score>> getAllPlayersHighScores() {
		return Collections.unmodifiableMap(this.allHighScores);
	}

	@Override
	public void saveData() {
		this.checkDirectories();
		if (this.currentPlayer.isPresent()) {

			final File currentPlayerFile = new File(this.currentPlayerPath());

			try (final Writer writer = new FileWriter(this.currentPlayerPath());) {

				Gson gson = new GsonBuilder().create();
				gson.toJson(this.getPlayer(), writer);

				for (Mode mode : this.allHighScores.keySet()) {

					Writer scoreWriter = new FileWriter(mode.getModePath());
					Gson gson2 = new GsonBuilder().create();
					gson2.toJson(this.allHighScores.get(mode), scoreWriter);

				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			throw new IllegalStateException();
		}

	}

	@Override
	public boolean playerAlreadyExist(String playerName) {
		this.checkDirectories();

		return  new File(Path.PLAYERSINFO_DIR.getPath()+ System.getProperty("file.separator")+playerName+".json").exists();

	}

	@Override
	public List<String> registeredPlayers() {
		this.checkDirectories();

		List<File> allPlayers = Arrays.asList(this.playersDir.listFiles());

		List<String> allPlayersNames = allPlayers.stream().map(e -> e.getName().replaceAll(".json", ""))
				.collect(Collectors.toList());
		return allPlayersNames;

	}

	@Override
	public Optional<Player> getCurrentPlayer() {
		return this.currentPlayer;
	}

	private void setDirectories() throws Exception {

		if (!backUpDir.exists()) {
			if (!backUpDir.mkdir() || !playersDir.mkdir() || !highScoresDir.mkdir()
					|| !arcadeHighScores.createNewFile()) {
				throw new Exception();
			}
		} else {

			if (!playersDir.exists()) {
				if (!playersDir.mkdir()) {
					throw new Exception();
				}
			}

			if (!highScoresDir.exists()) {
				if (!highScoresDir.mkdir() || !arcadeHighScores.createNewFile()) {
					throw new Exception();
				}
			} else {
				if (!arcadeHighScores.createNewFile()) {
					throw new Exception();
				}
			}

		}

	}

	private String currentPlayerPath() {
		this.checkDirectories();
		if (!this.currentPlayer.isPresent()) {
			throw new IllegalStateException();
		}
		return Path.PLAYERSINFO_DIR.getPath() + System.getProperty("file.separator") + this.playerName + ".json";
	}

	private void checkDirectories() {
	        if(!this.arcadeHighScores.exists() || !this.playersDir.exists()){
	            throw new IllegalStateException();
	        }
	    }
}
