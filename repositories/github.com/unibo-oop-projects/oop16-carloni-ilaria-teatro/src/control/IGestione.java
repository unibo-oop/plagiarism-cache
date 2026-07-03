package control;

import java.util.Collection;

/**
 * Interfaccia che permette di gestire i metodi relativi al
 * salvataggio/caricamento dei file
 * 
 * @author Ilaria Carloni
 * 
 */
public interface IGestione {

	public interface IGestore<E> {

		public E get(int chiave);

		public Collection<E> getLista();

		public int assegnaCod();

		public String toString();

		public void salva();

		public void carica();

		public abstract String getPercorso();

	}
}
