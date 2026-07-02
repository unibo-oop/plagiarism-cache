package record;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import interfaces.TableRecordInterface;

/**
 * gestice la tabella dei record
 * 
 * @author Alessandro
 *
 */
public class TableRecord implements TableRecordInterface{
  
  private String filePath;
	
	private PlayerTime[] playerTime;
	
	private ArrayList<String> listRecord;
	
	private String tableDifficult;
	
	/**
	 * costruttore, viene chiesto per quale difficolt� creare la tabella
	 * 
	 * @param tableDifficult
	 *     la difficolt� della tabella dei record
	 */
	public TableRecord(String tableDifficult) {
		this.tableDifficult = tableDifficult;
		this.readFileRecord();
	}
	
	/**
	 * legge i record e crea la tabella in base alla difficolta' richiesta
	 */
	private void readFileRecord() {
		switch(this.tableDifficult) {
		case "Easy":  
		    this.createFilePath("record.txt");
		    this.listRecord = new ArrayList<String>();
		    
		    try {
		      FileReader fr = new FileReader(new File(this.filePath));
		      BufferedReader br = new BufferedReader(fr);
		 
		      String line;
		      if((line = br.readLine()) != null) {
		        this.listRecord.add(line);
		        while((line = br.readLine()) != null) {
	            this.listRecord.add(line);
	          }
	          br.close();
	          fr.close();
		      } else {
		        this.writeDefaultRecord();
		        
		        this.listRecord.add("Vuoto");
            while((line = br.readLine()) != null) {
              this.listRecord.add(line);
            }
	          br.close();
	          fr.close();
		      }
		      this.fillTableRecord();
		    } catch(IOException e) {
		      e.printStackTrace();
		    }
		break;
		case "Medium":
		  this.createFilePath("recordMedium.txt");
      this.listRecord = new ArrayList<String>();
      
      try {
        FileReader fr = new FileReader(new File(this.filePath));
        BufferedReader br = new BufferedReader(fr);
        
        String line;
        if((line = br.readLine()) != null) {
          this.listRecord.add(line);
          while((line = br.readLine()) != null) {
            this.listRecord.add(line);
          }
          br.close();
          fr.close();
        } else {
          this.writeDefaultRecord();
          
          this.listRecord.add("Vuoto");
          while((line = br.readLine()) != null) {
            this.listRecord.add(line);
          }
          br.close();
          fr.close();
        }
        this.fillTableRecord();
      } catch(IOException e) {
        e.printStackTrace();
      }
		break;
		case "Hard":
		  this.createFilePath("recordHard.txt");
      this.listRecord = new ArrayList<String>();
      
      try {
        FileReader fr = new FileReader(new File(this.filePath));
        BufferedReader br = new BufferedReader(fr);
        
        String line;
        if((line = br.readLine()) != null) {
          this.listRecord.add(line);
          while((line = br.readLine()) != null) {
            this.listRecord.add(line);
          }
          br.close();
          fr.close();
        } else {
          this.writeDefaultRecord();
          
          this.listRecord.add("Vuoto");
          while((line = br.readLine()) != null) {
            this.listRecord.add(line);
          }
          br.close();
          fr.close();
        }
        this.fillTableRecord();
      } catch(IOException e) {
        e.printStackTrace();
      }
		default:
		break;
		}
	}
	
	/**
	 * rimpie la classifica con i tempi letti
	 * una riga indica il nome del record, la sucessiva il suo tempo
	 */
	private void fillTableRecord() {
	  if(this.playerTime != null) {
	    this.playerTime = null;
	  }
		this.playerTime = new PlayerTime[10];
		String[] arrayListRecord = new String[20];
		arrayListRecord = this.listRecord.toArray(arrayListRecord);
		int y = 1;
		int i = 0;
		for(int x = 0; x < 10; x++) {
			this.playerTime[x] = new PlayerTime(arrayListRecord[i], Integer.parseInt(arrayListRecord[y]));
			y += 2;
			i += 2;	
		}
	}

	/**
	 * aggiunge i record e si ferma una volta trovata la posizione corretta
	 * 
	 * @param name
	 *     il nominativo del record
	 * @param seconds
	 *     il tempo in secondi del record
	 */
	public void addRecord(String name, int seconds) {
		for(int x = 0; x < this.playerTime.length; x++) {
			if(seconds < this.playerTime[x].getTime()) {
				scaleRecord(name, seconds, x);
				break;
			}
		}
	}
	
	/**
	 * scala tutta la classifica per inserire il nuovo record, rimuove l'ultimo in classifica
	 * 
	 * @param name
	 *     il nominativo del record
	 * @param seconds
	 *     il tempo in secondi del record
	 * @param position
	 *     la posizione in cui va inserito il record
	 */
	private void scaleRecord(String name, int seconds, int position) {
		for(int x = this.playerTime.length-1; x >= position; x--) {
			if(x == position) {
				this.playerTime[x] = null;
				this.playerTime[x] = new PlayerTime(name, seconds);
			} else {
				this.playerTime[x] = null;
				this.playerTime[x] = this.playerTime[x-1];
			}
		}
		this.saveFileRecord();
	}
	
	/**
	 * salva la classifica in un file una volta aggiornata
	 * una riga viene inserito il nominativo e nell'altra il suo tempo
	 */
	private void saveFileRecord() {
	  try {
      FileWriter fw = new FileWriter(new File(this.filePath));
      BufferedWriter bw = new BufferedWriter(fw);
      
      for(int x = 0; x < 10; x++) { 
        bw.write(this.playerTime[x].getName());
        bw.newLine();
        bw.write(Integer.toString(this.playerTime[x].getTime()));
        bw.newLine();
      }
      bw.close();
      fw.close();
    } catch(IOException e) {
      e.printStackTrace();
    }
	}
	
	/**
	 * @param seconds
	 *     il tempo in secondi del record
	 * 
	 * @return se il tempo � un record
	 */
	public boolean isRecord(int seconds) {
		if(this.playerTime == null) {
			return true;
		} else {
			for(int x = 0; x < this.playerTime.length; x++) {
				if(this.playerTime[x].getTime() > seconds) {
					return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * @return la lista dei record
	 */
	public PlayerTime[] getListRecord() {
		return this.playerTime;
	}
	
	/**
	 * @return la difficolt� della tabella dei record
	 */
	public String getTableDifficult() {
		return this.tableDifficult;
	}
	
	/**
	 * 
	 * @param nameFile
	 *   il nome del file dove sono contenuti i record
	 */
	private void createFilePath(String nameFile) {
	  final String myDirectory = ".campominato";
    final String usersHomeDir = System.getProperty("user.home");
    //usersHomeDir.replace("\\", "/");
    final String pathFolder = usersHomeDir + File.separator + myDirectory;
    
    if(new File(pathFolder).mkdir()) {
      System.out.println("Directory è stata creata");
    } else {
      System.out.println("Directory è già esistente");
    }

    final String pathFile = usersHomeDir + File.separator + myDirectory + File.separator + nameFile;
    try {
      if(new File(pathFile).createNewFile()) {
          System.out.println("Il file è stato creato");
        } else {
          System.out.println("Il file esiste gia'");
        }
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.filePath = pathFile;
    
	}
	
	/**
	 * funzione che scrive i record di default
	 */
	public void writeDefaultRecord() { 
	  try { 
	    FileWriter fw = new FileWriter(new File(this.filePath));
	    BufferedWriter bw = new BufferedWriter(fw);
	     
	    for(int x = 0; x < 10; x++) {
	      bw.write("Vuoto");
	      bw.newLine();
	      bw.write(Integer.toString(3599999));
	      bw.newLine(); 
	    }
	    bw.close();
	    fw.close();
	    
	    this.readFileRecord();
	  } catch(IOException e) {
	    e.printStackTrace();
	  }  
	}
}
