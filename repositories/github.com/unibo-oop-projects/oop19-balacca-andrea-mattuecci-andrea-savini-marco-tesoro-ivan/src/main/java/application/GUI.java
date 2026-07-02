package application;

import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.FileHandler;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class GUI extends JFrame{
    
    private int x;
    private int y;
    private int width;
    private int height;
    
    public static JFrame frame;    
    protected static int gradoAccesso;
    protected static String utenteAccesso;
    
    protected static Catena catenaAccesso;
    protected static Hotel hotelAccesso;
    protected static Dispensa dispensaAccesso;
    
    static boolean control=false;
   
    //-------------GUI INVENTARIO----------------
    
    	WarehouseAddUtilityImpl w = new WarehouseAddUtilityImpl();
    	WarehouseModifyUtilityImpl wm = new WarehouseModifyUtilityImpl();
    	WarehouseDeleteUtilityImpl wd = new WarehouseDeleteUtilityImpl();
    	UtilityReadWriteCatena u = new UtilityReadWriteCatena();
    	
    //-------------------------------------------
    	
    	StoricFilter s = new StoricFilterImpl();
    
    	
    //-------------------------------------------	
    	
    public GUI() throws HeadlessException {
    	
    	
    	try {
    		catenaAccesso = UtilityReadWriteCatena.getCatena();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	Runtime.getRuntime().addShutdownHook(new Thread()
    	{
    	    @Override
    	    public void run()
    	    {
    	    	try {
					UtilityBackupAndRestore.makeBackup(GUI.catenaAccesso);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
    	});
    	
    	/*
    	try {
    		
    		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(WarehouseAddUtilityImpl.file.getFile())));
            out.writeObject(new Catena());
            out.close();
			catenaAccesso = w.openFile();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    	//catenaAccesso.getAlberghi().add(hotelAccesso);
    	//hotelAccesso.aggiungiUnaDispensa(new Dispensa("Macelleria", catenaAccesso));
	}
    
    

	GUI_Registrazione_LogicsImpl r = new GUI_Registrazione_LogicsImpl();   
	 
 
	protected void save(int x, int y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
    
    protected boolean controlloCredenziali(String username, char[] password){
    	
    	 GUI_Registrazione_LogicsImpl r = new  GUI_Registrazione_LogicsImpl();
    	
    	 r.leggiElencoConMAPPA();
    	 
    	 if(r.getMappa().containsKey(username)){
    		
    		if (Arrays.equals(r.getMappa().get(username).getPassword(), password)) {
    			
    			 GUI.gradoAccesso = r.getMappa().get(username).getGrado();
    			 
    			 GUI.utenteAccesso = r.getMappa().get(username).getUsername();    			 
    			 
    			 return true;
    		}else {
    			 JOptionPane.showMessageDialog(null, "Password errata", "ERRORE", JOptionPane.ERROR_MESSAGE);
    		}
    	 }else {
    		 JOptionPane.showMessageDialog(null, "Utente non presente, registati", "ERRORE", JOptionPane.ERROR_MESSAGE);   		 		 
    	 }
    	 
    	 return false;
    }
    
    protected boolean controlloGradoAlLogin(String username, char[] password) {
    	
    	if(this.controlloCredenziali(username, password)) {
	    	if(GUI.gradoAccesso>3) {
	    		return true;
	    	}
    	}
    	
    	JOptionPane.showMessageDialog(null, "Non ti è permesso eseguire questa operazione", "ERRORE", JOptionPane.ERROR_MESSAGE);   		 		 
		return false;  	
    }
    
    protected boolean soloAdminAlLogin(String username, char[] password) {
    	
    	if(this.controlloCredenziali(username, password)) {
	    	if(GUI.gradoAccesso==1) {
	    		return true;
	    	}
    	}
    	
    	JOptionPane.showMessageDialog(null, "Non ti è permesso eseguire questa operazione", "ERRORE", JOptionPane.ERROR_MESSAGE);   		 		 
		return false; 
    }
    
    protected boolean controlloGrado() {
    	if(GUI.gradoAccesso>3) {
    		return true;
    	}
    	
    	JOptionPane.showMessageDialog(null, "Non ti è permesso eseguire questa operazione", "ERRORE", JOptionPane.ERROR_MESSAGE);   		 		 
		return false;  	
    }
    
    protected boolean soloAdmin() {
    	if(GUI.gradoAccesso==1) {
    		return true;
    	}
    	
    	JOptionPane.showMessageDialog(null, "Non ti è permesso eseguire questa operazione", "ERRORE", JOptionPane.ERROR_MESSAGE);   		 		 
		return false; 
    }
    
    protected void logATutti() {
    	
    	//Aggiorno la lista degli utenti leggendoli dal file
    	r.leggiElencoConMAPPA(); 
    	
    	for(var x : r.getMappa().values()) {
    		
    		MyLogger.AggiungiUnLogger(x.getUsername());
    		System.out.println("Log creato per: "+x.getUsername());
    	}
	
    }
    
    protected void stampaLog(JTextPane p, String username) {
    	
    	r.leggiElencoConMAPPA();
    	String buffer = "";
    	
    	try{
    		   FileInputStream fstream = new FileInputStream(username+".log");
    		   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
    		   String strLine;
    		   /* read log line by line */
    		   while ((strLine = br.readLine()) != null)   {
    		     /* parse strLine to obtain what you want */
    		     System.out.println ("---> "+strLine);
    		     buffer = buffer+strLine+"\n";  
    		     
    		   }
    		   p.setText(buffer);
    		   fstream.close();
		} catch (Exception e) {
		     System.err.println("Error: " + e.getMessage());
		}
    	
    }
    
    protected boolean nomeHotelPresente(String tx) {
    	
    	for(Hotel x : (ArrayList<Hotel>)GUI.catenaAccesso.getAlberghi()) {
    		if(x.getNome().equals(tx)){
    			return true;
    		}
    	}
    	
  
    	return false;
    }

    
    //-------------GUI INVENTARIO----------------
    
    			//----- TIPOLOGIA ----
   		
    /**
     * @param id_tipologia
     * @param id_info
     * @param val_info
     * @param tipologia
     * @param informazioni
     */
    protected void setTipologia(String id_tipologia, String id_info,  String val_info, JComboBox tipologia, JComboBox informazioni) {

			w.addNewTipologia(GUI.catenaAccesso, id_tipologia, id_info, val_info);
			try {
				UtilityReadWriteCatena.setCatena(catenaAccesso);
			} catch (Exception e) {
				// TODO: handle exception
			}

    }
    
    protected void modificaTipologia(String id_tipologia, String id_info,  String val_info) {
    	
    	
    	
		wm.modifyTipo(GUI.catenaAccesso, id_tipologia, id_info, val_info);
		try {
			UtilityReadWriteCatena.setCatena(catenaAccesso);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
		
    }
    
    protected void rimuoviTipologia(String id_tipologia,  String id_info) {
    	

		wd.deleteTipo(GUI.catenaAccesso, id_tipologia, id_info);	
		try {
			UtilityReadWriteCatena.setCatena(catenaAccesso);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
    			
    			//----- PRODOTTO ----	
    
   protected void setProdotto(String id_prodotto, String padre, String id_scarto, String val_scarto, String id_info, String val_info) {

		w.addNewProdotto(GUI.catenaAccesso, id_prodotto, padre, id_scarto, val_scarto, id_info, val_info);
		//System.out.println("Aggiunto");
		try {
			UtilityReadWriteCatena.setCatena(catenaAccesso);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
   protected void modificaProdotto(String id_prodotto,String id_scarto,String val_scarto, String id_info,String val_info) {
	   
	   	wm.modifyProdotto(GUI.catenaAccesso, id_prodotto, id_scarto, val_scarto, id_info, val_info);
	   	try {
			UtilityReadWriteCatena.setCatena(catenaAccesso);
		} catch (Exception e) {
			// TODO: handle exception
		}
   }
   
   protected void rimuoviProdotto(final String idProd, final String key, final String idScarto) {
	   	
	   	wd.deleteProdotto(catenaAccesso, idProd, key, idScarto);
	   	try {
			UtilityReadWriteCatena.setCatena(catenaAccesso);
		} catch (Exception e) {
			// TODO: handle exception
		}
   }
   
   				//----- PRODOTTO CONCRETO----
   
   protected void setProdottoConcreto( final String idProdCon, final String idProdotto, final String idScarto, final String valoreScarto, final String key, final String value) {
	   w.addNewProdConcreto(GUI.catenaAccesso, idProdCon, idProdotto, idScarto, valoreScarto, key, value);
	   try {
			UtilityReadWriteCatena.setCatena(catenaAccesso);
		} catch (Exception e) {
			// TODO: handle exception
		}
   }
   
   protected void modificaProdottoConcreto(final String idProdCon, final String idScarto,
   		final String valoreScarto, final String key, final String value) {
	   wm.modifyProdConcreto(GUI.catenaAccesso, idProdCon, idScarto, valoreScarto, key, value);
	   try {
			UtilityReadWriteCatena.setCatena(catenaAccesso);
		} catch (Exception e) {
			// TODO: handle exception
		}
   }
   
   protected void rimuoviProdottoConcreto(final String idProdCon, final String key, final String idScarto) {
	   wd.deleteProdCon(GUI.catenaAccesso, idProdCon, key, idScarto);
	   try {
			UtilityReadWriteCatena.setCatena(catenaAccesso);
		} catch (Exception e) {
			// TODO: handle exception
		}
   }
   				
   				//----- PRODOTTO FORNITO----
   
   protected void setProdottoFornito (final String idProdFor, final String idProdCon, final String idScarto,
			final String valoreScarto, final String key, final String value, final String idForn, final String prezzo,
			final String valoreAssoluto) {
	   w.addNewProdFornito(GUI.catenaAccesso, idProdFor, idProdCon, idScarto, valoreScarto, key, value, idForn, prezzo, valoreAssoluto);
	   try {
			UtilityReadWriteCatena.setCatena(catenaAccesso);
		} catch (Exception e) {
			// TODO: handle exception
		}
   }
   
   protected void modificaProdottoFornito(final String idProdFor, 
           final String idScarto, final String valoreScarto, final String key, final String value, 
           final String iForn, final String prezzo) {
	   wm.modifyProdFornito(GUI.catenaAccesso, idProdFor, idScarto, valoreScarto, key, value, iForn);
	   try {
			UtilityReadWriteCatena.setCatena(catenaAccesso);
		} catch (Exception e) {
			// TODO: handle exception
		}
   }
   
   protected void rimuoviProdottoFornito(final String idProdFor, final String key, final String idScarto) {
	   wd.deleteProdFor(GUI.catenaAccesso, idProdFor, key, idScarto);
	   try {
			UtilityReadWriteCatena.setCatena(catenaAccesso);
		} catch (Exception e) {
			// TODO: handle exception
		}
   }
   
    //-------------------------------------------
    
}



