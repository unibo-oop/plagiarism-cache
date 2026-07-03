package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.interfaces.Data;

public class ReadWriteData {
	
	public ReadWriteData(Data data) {

	}
	
	public static void write(Data data) {
		ObjectOutputStream output = null;
		try {
			output = new ObjectOutputStream(new FileOutputStream("dataProva.dat"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			output.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			output.close();
		} catch (Exception e) {
			
		}
	}
	
	public static Data read() {
		ObjectInputStream input = null;
		try {
			input = new ObjectInputStream(new FileInputStream("dataProva.dat"));
		} catch (Exception e) {
		}
		
		Data data = null;
		try {
			data = (Data) input.readObject();
		} catch (Exception e) {
		}
		return data;
	}
}
