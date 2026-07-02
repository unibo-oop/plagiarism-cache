package resources;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.Score;
public class Serializer {
	public void saveScore(Score object,String filename) {
	    List<Score> list = null;
			try
	        {   
	           list = getScoreHistory(filename);
	           if(list == null) {
	        	  list = new ArrayList<>();
	           }
	         
	           list.add(object);
	            FileOutputStream file = new FileOutputStream(filename);
	            ObjectOutputStream out = new ObjectOutputStream(file);
	            out.writeObject(list);          
	            out.close();
	            file.close();    
	            System.out.println("Object has been serialized");
	 
	        }    
	        catch(IOException ex)
	        {
	         ex.printStackTrace();
	        }
	}

	@SuppressWarnings("unchecked")
	public List<Score> getScoreHistory(String filename) {
	    List<Score>list = null;
		try
	        {   
	      
	            FileInputStream file = new FileInputStream(filename);
	            ObjectInputStream in = new ObjectInputStream(file);	             
	            list = (List<Score>)in.readObject();	             
	            in.close();
	            file.close();
	            
	        }
	         
	        catch(IOException ex)
	        {
	            System.out.println(ex.getMessage());
	            return null;
	        }
	         
	        catch(ClassNotFoundException ex)
	        {
	            System.out.println(ex.getMessage());
	        }
		return list;
	}
	
}
