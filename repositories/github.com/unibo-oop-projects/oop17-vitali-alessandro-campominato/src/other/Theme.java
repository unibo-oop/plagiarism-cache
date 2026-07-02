package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import interfaces.ThemeInterface;

/**
 * classe che gestice il tema
 * 
 * @author Alessandro
 *
 */
public class Theme implements ThemeInterface{
  
  private static String filePath;

	private String actuallyTheme;
	
	private String firstColor = new String();
	private String secondColor = new String();
	private String thirdColor = new String();
	
	private boolean themeChanged = false;
	
	/**
	 * costruttore che legge il tema da file e lo imposta
	 */
	public Theme() {
		this.readActuallyTheme();
		switch(this.actuallyTheme) {
		case "Standard":
			this.setStandardTheme();
		break;
		case "White":
			this.setWhiteTheme();
		break;
		case "Black":
			this.setBlackTheme();
		break;
		default:
		break;
		}
	}
	
	/**
	 * legge il tema attuale, imposta la directory del file dove è contenuto il tema preferito e lo applica
	 */
	private void readActuallyTheme() {
	  
	  final String myDirectory = ".campominato";
	  final String usersHomeDir = System.getProperty("user.home");
	  //usersHomeDir.replace("\\", "/");
	  final String pathFolder = usersHomeDir + File.separator + myDirectory;
	  
	  if(new File(pathFolder).mkdir()) {
	    System.out.println("Directory creata");
	  } else {
	    System.out.println("Directory già esistente");
	  }
	  
	  final String nameFile = "theme.txt";
	  final String pathFile = usersHomeDir + File.separator + myDirectory + File.separator + nameFile;
	  try {
      if(new File(pathFile).createNewFile()) {
          System.out.println("File creato");
        } else {
          System.out.println("File già esistente");
        }
    } catch (IOException e) {
      e.printStackTrace();
    }
    filePath = pathFile;
    String lineFile;
	  try {
	    FileReader fr = new FileReader(new File(filePath));
	    BufferedReader br = new BufferedReader(fr);

	    if((lineFile = br.readLine()) != null) {
	      this.actuallyTheme = lineFile;
	    } else {
	      this.actuallyTheme = "Standard";  
	      FileWriter fw = new FileWriter(new File(filePath));
	      BufferedWriter bw = new BufferedWriter(fw);
	      bw.write(this.actuallyTheme);
	      bw.close();
	      fw.close();   
	    }
	    br.close();
	    fr.close();
	  } catch (IOException e) {
	    e.printStackTrace();
	  }
	}
	
	/**
	 * imposta il tema chiaro
	 */
	public void setWhiteTheme() {
		if(this.actuallyTheme != "White") {
			this.actuallyTheme = "White";
			this.firstColor = "#D1D3D1";
			this.secondColor = "#778ECA";
			this.thirdColor = "#E4DFD8";
			this.themeChanged = true;
			this.savePreferredTheme();
		} else {
      this.firstColor = "#D1D3D1";
      this.secondColor = "#778ECA";
      this.thirdColor = "#E4DFD8";
		}
	}
	
	/**
	 * imposta il tema scuro
	 */
	public void setBlackTheme() {
		if(this.actuallyTheme != "Black") {
			this.actuallyTheme = "Black";
			this.firstColor = "#C1BC93";
			this.secondColor = "#362828";
			this.thirdColor = "#668067";
			this.themeChanged = true;
			this.savePreferredTheme();
		} else {
      this.firstColor = "#C1BC93";
      this.secondColor = "#362828";
      this.thirdColor = "#668067";
		}
	}
	
	/**
	 * imposta il tema standard
	 */
	public void setStandardTheme() {
		if(this.actuallyTheme != "Standard") {
			this.actuallyTheme = "Standard";
			this.firstColor = "#f9ba32";
			this.secondColor = "#2f3131";
			this.thirdColor = "#f8f1e5";
			this.themeChanged = true;
			this.savePreferredTheme();
		} else {
		  this.firstColor = "#f9ba32";
      this.secondColor = "#2f3131";
      this.thirdColor = "#f8f1e5";
		}
	}
	
	/**
	 * sovrascrive le preferenze in un file
	 */
	private void savePreferredTheme() {
	  
	  try {
	    FileWriter fw = new FileWriter(new File(filePath));
	    BufferedWriter bw = new BufferedWriter(fw);
	    
	    bw.write(this.actuallyTheme);
	    bw.close();
	    fw.close();
	    } catch(IOException e) {
	      e.printStackTrace();
	    }
	}
	
	/**
	 * @return il tema attuale come stringa
	 */
	public String getActuallyTheme() {
		return this.actuallyTheme;
	}
	
	/**
	 * @return il colore primario
	 */
	public String getFirstColor() {
		return this.firstColor;
	}
	
	/**
	 * @return il colore secondario
	 */
	public String getSecondColor() {
		return this.secondColor;
	}
	
	/**
	 * @return il colore terziario
	 */
	public String getThirdColor() {
		return this.thirdColor;
	}
	
	/**
	 * imposta il tema come non modificato recentemente
	 */
	public void themeNotChanged() {
		this.themeChanged = false;
	}
	
	/**
	 * @return se il tema � stato recentemente modificato
	 */
	public boolean isThemeChanged() {
		return this.themeChanged;
	}
	
	
}
