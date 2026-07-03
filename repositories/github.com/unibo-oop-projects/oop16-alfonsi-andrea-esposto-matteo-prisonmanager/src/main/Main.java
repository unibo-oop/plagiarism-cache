package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.Implementations.LoginControllerImpl;
import model.Implementations.CellImpl;
import model.Implementations.GuardImpl;
import view.Interfaces.LoginView;

/**
 * The main of the application.
 */
public final class Main {
	
	/**
     * Program main, this is the "root" of the application.
     * @param args
     * unused,ignore
     */
	 public static void main(final String... args){
		 //creo cartella in cui mettere i dati da salvare
		 String Dir = "res";
		 new File(Dir).mkdir();
		 //creo file per le guardie
		 File fg = new File("res/GuardieUserPass.txt");
		 //se il file non è stato inizializzato lo faccio ora
		 if(fg.length()==0){
			 try {
				initializeGuards(fg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
		 //leggo il file contenente le celle
		 File f = new File("res/Celle.txt");
		 //se il file non è ancora stato inizializzato lo faccio ora
		 if(f.length()==0){
			 try {
				initializeCells(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
		 
		 //chiamo controller e view del login
		 new LoginControllerImpl(new LoginView());
	 }
	 
	 /**
	  * metodo che inizializza le celle
	  * @param f file in cui creare le celle
	  * @throws IOException
	  */
	 static void initializeCells(File f) throws IOException{

		 List<CellImpl>list=new ArrayList<>();
		 CellImpl c;
		 for(int i=0;i<50;i++){
			 if(i<20){
				  c = new CellImpl(i, "Primo piano", 4);
			 }
			 else
				 if(i<40){
					  c = new CellImpl(i, "Secondo piano", 3);
				 }
				 else
					 if(i<45){
					  c = new CellImpl(i, "Terzo piano", 4);
				 }
					 else{
						  c = new CellImpl(i, "Piano sotterraneo, celle di isolamento", 1);
					 }
			 list.add(c);
		 }
			FileOutputStream fo = new FileOutputStream(f);
			ObjectOutputStream os = new ObjectOutputStream(fo);
			os.flush();
			fo.flush();
			for(CellImpl c1 : list){
				os.writeObject(c1);
			}
			os.close();
	 }
	 
	 /**
	  * metodo che inizializza le guardie
	  * @param fg file in cui creare le guardie
	  * @throws IOException
	  */
	 static void initializeGuards(File fg) throws IOException{

		 String pattern = "MM/dd/yyyy";
		 SimpleDateFormat format = new SimpleDateFormat(pattern);
		 Date date = null;
		try {
			date = format.parse("01/01/1980");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<GuardImpl>list=new ArrayList<>();
		GuardImpl g1=new GuardImpl("Oronzo","Cantani",date,1,"0764568",1,"ciao01");
		list.add(g1);
		GuardImpl g2=new GuardImpl("Emile","Heskey",date,2,"456789",2,"asdasd");
		list.add(g2);
		GuardImpl g3=new GuardImpl("Gennaro","Alfieri",date,3,"0764568",3,"qwerty");
		list.add(g3);
		FileOutputStream fo = new FileOutputStream(fg);
		ObjectOutputStream os = new ObjectOutputStream(fo);
		os.flush();
		fo.flush();
		for(GuardImpl g : list){
			os.writeObject(g);
		}
		os.close();
	 }
	 
}
