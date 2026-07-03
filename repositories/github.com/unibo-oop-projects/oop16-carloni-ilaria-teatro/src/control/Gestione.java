package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;

/**
 * Classe astratta per caricare e salvare
 * 
 * @author Ilaria Carloni
 * 
 * @param <E>
 */
public abstract class Gestione<E> implements IGestione {
	protected HashMap<Integer, E> mappa;

	protected Gestione() {
		this.mappa = new HashMap<Integer, E>(50);
	}

	/**
	 * Metodo che ritorna un cliente/operatore/spettacolo/prenotazione contenuto
	 * nella mappa
	 * 
	 * @param chiave
	 * @return E
	 */
	public E get(int chiave) {
		return this.mappa.get(chiave);
	}

	/**
	 * Metodo che ritorna una collezione che contiene gli oggetti della mappa
	 * 
	 * @return Collection<E>
	 */
	public Collection<E> getLista() {
		return (Collection<E>) this.mappa.values();
	}

	/**
	 * Assegna un codice univoco ad ogni elemento della mappa
	 * 
	 * @return i
	 */
	public int assegnaCod() {
		int i = this.mappa.values().size();
		while (this.mappa.get(i) != null) { // se l'ultimo elemento non è vuoto
											// incremento il contatore
			i++;
		}
		return i;
	}

	/**
	 * Metodo che stampa la collezione di oggetti nella console
	 * 
	 * @return String
	 */
	public String toString() {
		String str = "";
		for (E object : this.getLista()) {
			str += object.toString() + "\n";
		}
		return str;
	}

	/**
	 * Metodo che salva la mappa su file
	 * 
	 */
	public void salva() {
		try {
			FileOutputStream fileOut = new FileOutputStream(getPercorso());
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this.mappa);
			out.close();
			fileOut.close();
		} catch (Exception i) {
			i.printStackTrace();
		}
	}

	/**
	 * Metodo che carica la mappa da file
	 */
	@SuppressWarnings("unchecked")
	protected void carica() {
		try {
			File f = new File(getPercorso());
			if (!f.exists()) {
				return;
			}

			FileInputStream fileIn = new FileInputStream(f);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Object o = in.readObject();
			this.mappa = (HashMap<Integer, E>) o;
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo astratto che ritorna il percorso in cui salvare/caricare il file
	 * 
	 * @return String
	 */
	public abstract String getPercorso();

}
