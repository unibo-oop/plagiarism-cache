package pro_ristorante_oop.persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;

import pro_ristorante_oop.Ordine;
import pro_ristorante_oop.Pair;
import pro_ristorante_oop.Persona;
import pro_ristorante_oop.Piatti;
import pro_ristorante_oop.Proprietario;
import pro_ristorante_oop.User;

public class TransientPersistenceService implements PersistenceService
{

	public static HashMap PERSONE = new HashMap<String, List<Persona>>();
	public static HashMap USERS = new HashMap<String, User>();

	public TransientPersistenceService()
	{
		this.initialize();
	}

	private String MD5(String md5)
	{
		try
		{
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i)
			{
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e)
		{
		}
		return null;
	}

	/**
	 * Only the transient persistence service has this initializer
	 */
	private void initialize()
	{
		//Add persone
		List propietarioList = new ArrayList<Persona>();
		Persona p = new Proprietario("Schepp", "Christian", true);
		propietarioList.add(p);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String dateFormatAsString = dateFormat.format(new Date());
		String key = this.MD5(p.getNome() + p.getCognome() + dateFormatAsString);
		TransientPersistenceService.PERSONE.put(key, propietarioList);

		// Add Users
		User user = new User("test", "test");
		TransientPersistenceService.USERS.put(user.getUsername(), user);
	}

	@Override
	public void save(List<Persona> persona)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public List<Persona> readPersone()
	{
		List<Persona> persone = new ArrayList<Persona>();
		return null;
	}

	@Override
	public List<User> readUser(String[] usernames)
	{
		// TODO Auto-generated method stub
		List userList = new ArrayList<User>();
		userList.add((User) USERS.get(usernames[0]));
		return userList;
	}

	@Override
	public Map<Pair<Integer,Integer>,List<Piatti>> readOrdine()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Piatti> readPiatti()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrder(Map<Pair<Integer, Integer>, List<Piatti>> orders) {
		// TODO Auto-generated method stub
		
	}

}
