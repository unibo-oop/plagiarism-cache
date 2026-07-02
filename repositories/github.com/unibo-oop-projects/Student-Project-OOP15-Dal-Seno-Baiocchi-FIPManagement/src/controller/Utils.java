package controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.IModel;
import model.Model;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

/**
 * class used to save and load the data model into a file "Salvataggio.txt"
 * @author francesco
 *
 */

public class Utils {
	
        public static final String SAVENAME = "saveFIP.txt";
	public static final String FILENAME = "Salvataggio.txt";
        private static ObjectInputStream oi; 

	public static void save(IModel model) {
		try {
			FileOutputStream fo = new FileOutputStream(SAVENAME);
			ObjectOutputStream dos = new ObjectOutputStream(fo);
			dos.writeObject(model);
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static IModel loading(){
		try {
		    File f = new File(SAVENAME);
		    if(f.exists() && !f.isDirectory()) { 
		        FileInputStream fo = new FileInputStream(SAVENAME);
		        oi = new ObjectInputStream(fo);
		        IModel model = (IModel) oi.readObject();
		        return model == null ? new Model(): model;
		    } else {
		        InputStream in = new ClassLoader().getResourceAsStream(FILENAME);
                        ObjectInputStream objI = new ObjectInputStream(in);
                        IModel model = (IModel) objI.readObject();
                        objI.close();
                        return model == null ? new Model() : model;
		    }
		} catch (Exception e) {
			return new Model();
		}		
	}
}
