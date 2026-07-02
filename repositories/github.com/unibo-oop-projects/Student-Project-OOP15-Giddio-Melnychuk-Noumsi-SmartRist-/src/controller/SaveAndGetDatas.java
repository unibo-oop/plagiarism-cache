package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import pro_ristorante_oop.Cameriere;
import pro_ristorante_oop.Cuoco;
import pro_ristorante_oop.IPiatti;
import pro_ristorante_oop.Ordine;
import pro_ristorante_oop.Persona;
import pro_ristorante_oop.Piatti;
import pro_ristorante_oop.TypePlate;



public class SaveAndGetDatas implements ISaveAndGetDatas,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static File currentDirectory = new File(new File("").getAbsolutePath());
    private static String path =currentDirectory.getAbsolutePath()+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"controller"+System.getProperty("file.separator");
	public static void save (Object o) {
		//System.getProperty("file.separator")
	   
		String i;
		if(o.getClass().getAnnotatedInterfaces()[0].getType().getTypeName()== IPiatti.class.getName())
		  i=path+"Piatti.txt";
		else if(o.getClass().getAnnotatedInterfaces()[0].getType().getTypeName()==Persona.class.getName())
			i=path+"Persone.txt";
		else if(o.getClass().getAnnotatedInterfaces()[0].getType().getTypeName()==Ordine.class.getName())
			i=path+"Ordini.txt";
		else
			throw new IllegalArgumentException();
		
	    try {
			FileOutputStream p = new FileOutputStream(i);
			ObjectOutputStream ob = new ObjectOutputStream(p);
			ob.writeObject(o);
			ob.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	public static void save(List<Object> o) {
		o.forEach(x->save(x));
	}
	
	public static List<Persona>  loadPersone(Class<?> o) {
		String i;
		//if(o.getName()== IPiatti.class.getName())
		  //i=path+"Piatti.txt";
		if(o.getName()==Persona.class.getName())
			i=path+"Persone.txt";
		else
			throw new IllegalArgumentException();
        
		List<Persona> po =new LinkedList<>();
		FileInputStream p=null;
		ObjectInputStream ob=null;
		try {
			 p = new FileInputStream(i);
			//BufferedInputStream uo =new BufferedInputStream(p);
			 ob = new ObjectInputStream(p);
		    Persona p1 =null;
			do{
				 p1 = (Persona)ob.readObject();
				if(p1.getClass().getName() == Cuoco.class.getName()){
					Cuoco cu = (Cuoco)p1;
				    po.add(cu);
				}
				else if(p1.getClass().getName() == Cameriere.class.getName()){
					Cameriere ci= (Cameriere)p1;
					po.add(ci);
				}
				
			}while(p1 != null);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
			    if(ob  != null){
			        try {
						ob.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block

						e.printStackTrace();
					}
			    } 
			}
		return po;
	}
	public static List<Ordine>  loadOrdine(Class<?> o) {
		String i;
		if(o.getName()==Ordine.class.getName())
			i=path+"Ordine.txt";
		else
			throw new IllegalArgumentException();
        
		List<Ordine> po =new LinkedList<>();
		FileInputStream p=null;
		ObjectInputStream ob=null;
		try {
			 p = new FileInputStream(i);
			//BufferedInputStream uo =new BufferedInputStream(p);
			 ob = new ObjectInputStream(p);
		    Ordine p1 =null;
			do{
				 p1 = (Ordine)ob.readObject();
				if(p1.getClass().getName() == Ordine.class.getName()){
					Ordine cu = (Ordine)p1;
				    po.add(cu);
				}
				
			}while(p1 != null);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
			    if(ob  != null){
			        try {
						ob.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block

						e.printStackTrace();
					}
			    } 
			}
		return po;
	}
	public static List<Piatti>  loadPiatti(Class<?> o) {
		String i;
		//if(o.getName()== IPiatti.class.getName())
		  //i=path+"Piatti.txt";
		if(o.getName()==Piatti.class.getName())
			i=path+"Piatti.txt";
		else
			throw new IllegalArgumentException();
        
		List<Piatti> po =new LinkedList<>();
		FileInputStream p=null;
		ObjectInputStream ob=null;
		try {
			 p = new FileInputStream(i);
			//BufferedInputStream uo =new BufferedInputStream(p);
			 ob = new ObjectInputStream(p);
		    Piatti p1 =null;
			do{
				 p1 = (Piatti)ob.readObject();
				if(p1.getClass().getName() == Piatti.class.getName()){
				    po.add(p1);
				}
				
			}while(p1 != null);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
			    if(ob  != null){
			        try {
						ob.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block

						e.printStackTrace();
					}
			    } 
			}
		return po;
	}
	
	
	
	public static void main(String[] args){
	 Piatti p = new Piatti("name",11.0,10001,TypePlate.ANTIPASTO);
	 Persona pe= new Cameriere("nome","cognome",true);
	 Persona po=new Cuoco("nome1","cognome1",false);
	 Ordine ord = new Ordine();
	 List<Piatti> pl = new LinkedList<>();
	 System.out.println(path);
	 pl.add(p);
	 pl.add(new Piatti("pasta", 20.9, 01010,TypePlate.PRIMO));
	 ord.addOrd(pl,1);
	 Ordine o = new Ordine();
	 List<Piatti> lp = new LinkedList<>();
	 lp.add(p);
     o.addOrd(lp, 1);
     System.out.println(o.toString());
	 save(p);
	 save(pe);
	 save(po);
	 save(o);
	 List<Persona> ioul = loadPersone(Persona.class);
	 System.out.println(ioul.size());
	 //load(Persona.class,ioul);
	 ioul.forEach(x->System.out.println(x.toString()));
		//System.out.println(path+"Piatti.txt");
	}

}
