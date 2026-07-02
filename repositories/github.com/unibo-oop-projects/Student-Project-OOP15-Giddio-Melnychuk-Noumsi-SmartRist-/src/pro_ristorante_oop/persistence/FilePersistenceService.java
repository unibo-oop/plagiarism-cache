package pro_ristorante_oop.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import pro_ristorante_oop.Pair;
import pro_ristorante_oop.Persona;
import pro_ristorante_oop.Piatti;
import pro_ristorante_oop.Proprietario;
import pro_ristorante_oop.TypePlate;
import pro_ristorante_oop.User;

public class FilePersistenceService implements PersistenceService
{

	@Override
	public void save(List<Persona> persona)
	{
		FileWriter fw = null;
		BufferedWriter writer = null;
		File file = null;
		try
		{
			file = new File(getClass().getResource("/files/persone.txt").toURI());
			writer = new BufferedWriter(new PrintWriter(file));
			for (Persona p : persona)
			{
				writer.write(p.getNome() + "," + p.getCognome() + "," + p.getPassword() + ","
						+ (p.isSesso() ? "Uomo" : "Donna"));
				writer.newLine();
			}
		} catch (Exception e1)
		{
			// TODO
		}
		finally
		{
			if (writer != null)
				try
				{
					writer.close();
				} catch (IOException e)
				{
				}
		}

	}
	@Override
	public void saveOrder(Map<Pair<Integer,Integer>,List<Piatti>> orders)
	{
		FileWriter fw = null;
		BufferedWriter writer = null;
		File file = null;
		try
		{
			file = new File(getClass().getResource("/files/ordini.txt").toURI());
			writer = new BufferedWriter(new PrintWriter(file));
			for(Iterator<Pair<Integer, Integer>> i = orders.keySet().iterator();i.hasNext();){
					Pair<Integer, Integer> item = i.next();	
					writer.write(item.getX() +","+item.getY());
					for(Iterator<Piatti> i2 = orders.get(item).iterator();i2.hasNext();){
						Piatti item2 = i2.next();
						writer.write(","+item2.toString());
					}
					writer.newLine();
				}
		} catch (Exception e1)
		{
			e1.printStackTrace();
		} finally
		{
			if (writer != null)
				try
				{
					writer.close();
				} catch (IOException e)
				{
				}
		}

	}

	@Override
	public List<Persona> readPersone()
	{
		List<Persona> persone = new ArrayList<Persona>();

		BufferedReader in = null;
		try
		{
			in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/files/persone.txt")));
			String row = null;
			// read comma separated file, e.g. Oleksandr,Melnychuk,OM42520
			while ((row = in.readLine()) != null)
			{
				System.out.println("Read row: " + row);
				StringTokenizer st = new StringTokenizer(row, ",");
				ArrayList<String> sParam = new ArrayList<String>();
				while (st.hasMoreTokens())
				{
					sParam.add(st.nextToken());
				}
				Persona p = new Proprietario(sParam.get(0), sParam.get(1), sParam.get(3).equals("Uomo") ? true : false);
				p.setPassword(sParam.get(2));
				persone.add(p);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (in != null)
				try
				{
					in.close();
				} catch (IOException e)
				{
				}
		}
		return persone;
	}

	@Override
	public Map<Pair<Integer,Integer>,List<Piatti>> readOrdine() // don't work go in exception
	{
		// TODO Auto-generated method stub
		Map<Pair<Integer,Integer>,List<Piatti>> ord = new HashMap<>();
		List<Piatti> piat = new LinkedList<>();
		
		
		BufferedReader in = null;
		try{
			in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/files/ordini.txt")));
			Integer i=0;
			String row = null;
			while ((row = in.readLine()) != null)
			{
				i=0;
				System.out.println("Read row: " + row);
				StringTokenizer st = new StringTokenizer(row, ",");
				ArrayList<String> sParam = new ArrayList<String>();
				while (st.hasMoreTokens())
				{	
					sParam.add(st.nextToken());
					if(i>1){
						piat.add(new Piatti(sParam.get(i),new Double(0), new Integer (1), TypePlate.PRIMO));
					}
					i++;
				}
				ord.put(new Pair<Integer, Integer>(Integer.valueOf(sParam.get(0)),Integer.valueOf(sParam.get(1))),piat);
				piat.clear();
			}
			
		}catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (in != null)
				try
				{
					in.close();
				} catch (IOException e)
				{
				}
		}
		return ord;
	}
	@Override
	public List<Piatti> readPiatti()
	{
		// TODO Auto-generated method stub
		List<Piatti> piatti = new ArrayList<>();

		BufferedReader in = null;
		try
		{
			in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/files/piatti.txt")));
			String row = null;
			while ((row = in.readLine()) != null)
			{
				System.out.println("Read row: " + row);
				StringTokenizer st = new StringTokenizer(row, ",");
				ArrayList<String> sParam = new ArrayList<String>();
				while (st.hasMoreTokens())
				{
					sParam.add(st.nextToken());
				}
				Piatti piat = new Piatti (sParam.get(0), Double.valueOf(sParam.get(1)), Integer.valueOf(sParam.get(2)), TypePlate.valueOf(sParam.get(3)));
				piatti.add(piat);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (in != null)
				try
				{
					in.close();
				} catch (IOException e)
				{
				}
		}
		return piatti;
	}
	
	@Override
	public List<User> readUser(String[] username)/*i don't know what i have to do */
	{
		// TODO Auto-generated method stub
		List<User> user = new ArrayList<>();

		BufferedReader in = null;
		try
		{
			in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/files/user.txt")));
			String row = null;
			while((row = in.readLine())!=null)
			{
				System.out.println("Read row: " + row);
				StringTokenizer st = new StringTokenizer(row, ",");
				ArrayList<String> sParam = new ArrayList<String>();
				while(st.hasMoreTokens())
				{
					sParam.add(st.nextToken());
				}
				user.add(User.class.cast(sParam.get(0)));
			}
		}catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (in != null)
				try
				{
					in.close();
				} catch (IOException e)
				{
				}
		}
		return null;
	}

}
