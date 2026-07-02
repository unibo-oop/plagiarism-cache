package application;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sun.javafx.collections.MappingChange.Map;

public class GUI_Registrazione_LogicsImpl {
	
	private Utente user;	
	private ArrayList<JCheckBox> checkBoxOnPanel;
	private HashMap<String, Utente> mappa;
	
	GUI_Registrazione_LogicsImpl(){
		GUI.control=false; 
		checkBoxOnPanel = new ArrayList<JCheckBox>();	
		mappa = new HashMap<>();
	}
	
	
	

    protected HashMap<String, Utente> getMappa() {
		return mappa;
	}

	protected void setMappa(HashMap<String, Utente> mappa) {
		this.mappa = mappa;
	}

	

	protected void leggiElencoConMAPPA() {

    	try {
			
			ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream("Elenco.bin")));
			this.mappa = (HashMap) is.readObject();
			
			System.out.println("chiave     valore");
			
			for(var x: mappa.keySet()) {
				System.out.println(x+"        "+mappa.get(x).getUsername()+"        "+mappa.get(x).getGrado());
			} 
			
			/*
			for(var x : this.elencoUtenti.getEu()) {
				System.out.println(x.getUsername()+ "    "+x.getPassword().toString());
			}
			*/
			
			//System.out.println((Utente)is.readObject());
			is.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    protected void scriviNuovoUtenteConMAPPA(String tx, char[] p) {
	   
	   	this.user = new Utente(tx, p);	
	   	//user.setGrado(1); solo per creare l'admin
	   	this.mappa.put(user.getUsername(), user);

		try {
			
			ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Elenco.bin")));
			os.writeObject(this.mappa);
			os.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Utente aggiunto, scrittura avvenuta");
	   
   }
    
    protected void writeSulFileConMAPPA() {
    	
    	try {
			
			ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Elenco.bin")));
			os.writeObject(this.mappa);
			os.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Scrittura utenti aggiornata");
    	
    }
    
    protected void writePannelloConMAPPA(JPanel panel) {
    	
    	JCheckBox cb;
		panel.removeAll();
		panel.repaint();
		
    	this.leggiElencoConMAPPA();
    	
		for(var x : this.mappa.keySet()) {
			
			cb = new JCheckBox(mappa.get(x).getUsername());
			this.checkBoxOnPanel.add(cb);
			panel.add(cb);		
			panel.revalidate();
		}
    	
    }
    
    protected void removeSelectedCheckboxConMAPPA() {
    	  	
    	for(var y : this.checkBoxOnPanel) {
    		
    		if(y.isSelected()) {
	    		
    			if(this.mappa.get(y.getText()).getGrado()!=1) {
	    			this.mappa.remove(y.getText());  		
    			}
	    		
    		}
    	}
    	
    	this.writeSulFileConMAPPA();
    }
    
    protected void promozioneConMAPPA() {
    	
    	this.leggiElencoConMAPPA();
    	
    	for(var y : this.checkBoxOnPanel) {
    		
    		if(y.isSelected()) {
	    		
    			if(this.mappa.get(y.getText()).getGrado()!=1) {
	    			this.mappa.get(y.getText()).setGrado(2);
	    			y.setSelected(false);
    			}
    		}
    	}
    	
    	this.writeSulFileConMAPPA();
    	
    	System.out.println("Utenti selezionati promossi");
    	
    }
    
    protected void declassamentoConMAPPA() {
    	
    	this.leggiElencoConMAPPA();
    	
    	for(var y : this.checkBoxOnPanel) {
    		
    		if(y.isSelected()) {
	    		
    			if(this.mappa.get(y.getText()).getGrado()!=1) {
	    			this.mappa.get(y.getText()).setGrado(3);
	    			y.setSelected(false);
    			}
    		}
    	}
    	
    	this.writeSulFileConMAPPA();
    	
    	System.out.println("Utenti selezionati declassati");
    }
    
    protected boolean soloUnoSelezionato() {
    	
    	int cont=0;
    	
    	for(var x : this.checkBoxOnPanel) {
    		
    		if(x.isSelected()) {
    			cont++;
    		}
    		
    	}	
    	
    	if(cont==1) {
    		return true;
    	}
    	
    	cont = 0;
    	return false;
    }
    
    protected String getFirstSelected() {
    	for(var x : this.checkBoxOnPanel) {
    		
    		if(x.isSelected()) {
    			return this.mappa.get(x.getText()).getUsername();
    		}
    		
    	}
    	
    	return null;
    }
    
    protected boolean UtenteGiaPresente(String tx) {
    	
    	ObjectInputStream is;
		try {
			is = new ObjectInputStream(new BufferedInputStream(new FileInputStream("Elenco.bin")));
			this.mappa = (HashMap) is.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("chiave     valore");
		
		for(var x: mappa.keySet()) {
			if(mappa.get(x).getUsername().equals(tx)) {
				return true;
			}
		} 
    	
    	return false;
    }

}
