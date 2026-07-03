package model.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StreamImplementation<K> implements StreamModel<K> {

	@Override
	public void saveFile(String path, K object) {
		try{
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(object);
			
			oos.close();
			fos.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public K readFile(String path) {
		K result;
		FileInputStream fis;
		try{
			fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			result = (K) ois.readObject();
			
			ois.close();
			fis.close();
			return result;
		}catch (FileNotFoundException e) {

		} catch (IOException e) {

		} catch (ClassNotFoundException e) {

		}
		return null;
	}

}
