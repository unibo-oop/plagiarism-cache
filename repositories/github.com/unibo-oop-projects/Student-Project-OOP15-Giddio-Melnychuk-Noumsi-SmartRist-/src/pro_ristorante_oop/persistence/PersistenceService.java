package pro_ristorante_oop.persistence;

import java.util.List;
import java.util.Map;

import pro_ristorante_oop.Pair;
import pro_ristorante_oop.Persona;
import pro_ristorante_oop.Piatti;
import pro_ristorante_oop.User;

public interface PersistenceService {

	public void save(List<Persona> persona);
	public void saveOrder(Map<Pair<Integer,Integer>,List<Piatti>> orders);
	public List<Persona> readPersone();
	public Map<Pair<Integer, Integer>, List<Piatti>> readOrdine();
	public List<Piatti> readPiatti();
	public List<User> readUser(String[] usernames);
	}
