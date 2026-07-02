package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import pro_ristorante_oop.Pair;
import pro_ristorante_oop.Persona;
import pro_ristorante_oop.Piatti;
import pro_ristorante_oop.persistence.FilePersistenceService;
import pro_ristorante_oop.persistence.PersistenceService;

public class TestFilePersistence {

		public static void main(String[] args)
		{
			List<Persona> persone = new ArrayList<Persona>();
			Map<Pair<Integer,Integer>,List<Piatti>> ordine = new HashMap<>();
			/*Persona persona = new Proprietario("Christian", "Schepp", true);
			persone.add(persona);
			persona = new Proprietario("Oleksandr", "Melnychuk", true);
			persone.add(persona);*/
			
			PersistenceService service = new FilePersistenceService();
			service.save(persone);
			List<Piatti> piatti  = service.readPiatti();
			List<Persona> list = service.readPersone();
			ordine.put(new Pair<Integer, Integer>(1,1),piatti);
			service.saveOrder(ordine);
			Map<Pair<Integer,Integer>,List<Piatti>> ordine12 = service.readOrdine();
			for(Iterator<Pair<Integer, Integer>> i = ordine12.keySet().iterator();i.hasNext();){
				Pair<Integer, Integer> item = i.next();
				System.out.println(item.getX()+","+item.getY());
			}
			for (Piatti p1 : piatti){
				System.out.println(p1.getname());
			}
			for (Persona p: list )
			{
				System.out.println(p.getCognome());
			}
		}
	}
