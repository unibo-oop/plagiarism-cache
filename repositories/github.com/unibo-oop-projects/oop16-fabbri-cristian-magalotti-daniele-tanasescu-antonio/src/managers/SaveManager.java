package managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

import main.MainApp;

/**
 * 
 * @author Cristian
 *
 */

public class SaveManager {
    
    //this game needs a cross-platform unique path to save: this is why I use System.getProperty("user.home") !
    public static final String FIRSTDIR  = System.getProperty("user.home") + "/Javamon";
    public static final String SECONDDIR = System.getProperty("user.home") + "/Javamon/Save";
    public static final String SAVEPATH  = System.getProperty("user.home") + "/Javamon/Save/save.txt";
    private static final String CERTIFICATIONPATH  = System.getProperty("user.home") + "/Javamon/Save/certification.bin";
    
    File saveFile = null;
    File certificationFile = null;

    private GameManager gameManager;
    
    public SaveManager() {
        this.setReferences();
    }
    
    private void setReferences(){
        if(gameManager == null){
            if(GameManager.getInstance() != null){
                this.gameManager = GameManager.getInstance();
            }
        }
        
        this.saveFile = new File(SAVEPATH);
        this.certificationFile = new File(CERTIFICATIONPATH);
    }
    
    public GameManager gameManagerInstance(){
        return this.gameManager;
    }
    
    public void createSaveFile() throws IOException{
        File firstDir = new File(FIRSTDIR);
        if(!firstDir.exists()){
            firstDir.mkdir();
        }
        File secondDir = new File(SECONDDIR);
        if(!secondDir.exists()){
            secondDir.mkdir();
        }
        
        if(!this.saveFile.exists()){
            this.saveFile.createNewFile();
        }
    }
    
   //viene chiamato SOLO SE esiste un file di salvataggio non vuoto (→ esiste certification.bin)
    public void loadGame() throws IOException, ClassNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(this.saveFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        GameManager.setReference((GameManager) objectInputStream.readObject());
        objectInputStream.close();
    }
    
    public void saveGame() throws IOException{
        if(!this.certificationFile.exists()){
            this.certificationFile.createNewFile();
        }     
        
        FileOutputStream fileOutputStream = new FileOutputStream(this.saveFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        
        objectOutputStream.writeObject(this.gameManager);
        objectOutputStream.close();
    }
    
    public void newGame() throws IOException{
        if(this.certificationFile.exists()){
           Files.delete(this.certificationFile.toPath());
        }  
        GameManager.setReference(null);
        GameManager.setReference(GameManager.getInstance());
        this.gameManager = null;
        this.setReferences();
        this.gameManager.newPlayer();
        this.gameManager.setAllEnemies();
        MainApp.initializeNewEnemy();
    }
    
    public boolean existsNotEmptySaveFile(){
        boolean exists = false;
        if(this.saveFile != null && this.certificationFile != null){
            exists = this.certificationFile.exists();
        }
        
        return exists; 
    }

}
