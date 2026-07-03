package pvz.controller.data;

public enum Path {

	HIGHSCORES_DIR(System.getProperty("user.home") + System.getProperty("file.separator")
    + "PVZ-Backups" + System.getProperty("file.separator") + "Highscores"),
	PLAYERSINFO_DIR(System.getProperty("user.home") + System.getProperty("file.separator")
    + "PVZ-Backups" + System.getProperty("file.separator") + "Players"),
	PVZ_DIR(System.getProperty("user.home") + System.getProperty("file.separator")
    + "PVZ-Backups"),
	HISTORY_HIGHSCORES_FILE(HIGHSCORES_DIR.getPath() + System.getProperty("file.separator")
    + "history_highscores.json"),
	ARCADE_HISHSCORES_FILE(HIGHSCORES_DIR.getPath() + System.getProperty("file.separator")
    + "arcade_highscores.json");
	
	private String path;
	
	private Path(String path){
		this.path = path; 
	}
	
	public String getPath(){
		return this.path;
	}
	
	
}
