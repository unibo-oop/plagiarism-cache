package model.utili;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public final class Admin {
	
	private static final String LOGIN_FILE_PATH = "logindata.json";
	private static String user;
	private static String password;
	
	/**
	 * get sets the fileUser and filePassword fields, which will be used for login, by reading the logindata.json file
	 */
	public static String getUser () {
		final InputStream res = ClassLoader.getSystemResourceAsStream(LOGIN_FILE_PATH);
		final Reader reader = new BufferedReader(new InputStreamReader(res));//creo il file reader per il file logindata.json
		final JsonObject jobj = new Gson().fromJson(reader,JsonObject.class);//creo il JsonObject da cui andro a leggere i dati presenti su file 
		user = jobj.get("utente").getAsString();//uso il metodo getAsString invece di toString in modo che la stringa restituita non abbia i quote marks

         return user;
	}
	
	public static String getPassword () {
		final InputStream res = ClassLoader.getSystemResourceAsStream(LOGIN_FILE_PATH);
		final Reader reader = new BufferedReader(new InputStreamReader(res));
		final JsonObject jobj = new Gson().fromJson(reader,JsonObject.class); 
		password = jobj.get("password").getAsString();
         return password;
	}

}
