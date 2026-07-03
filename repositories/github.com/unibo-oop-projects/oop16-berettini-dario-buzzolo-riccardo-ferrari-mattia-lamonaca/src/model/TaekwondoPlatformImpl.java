package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

public class TaekwondoPlatformImpl implements TaekwondoPlatform,Serializable{

	private static final long serialVersionUID = 1L;
 
	public boolean logIn(final String username, final String password) {
		try {
		
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/User.txt")));
			
			String line;
			while((line = bufferRead.readLine())!= null) {
				
				String[] m = line.split(" ");
				
				for (int i = 0; i < m.length; i++){
					if(username.equals(m[i]) && password.equals(m[i+1])) {

						return true;
					}
				}
			}
		
		}catch (Exception e) {
			
		}
		
		return false;
	}
}
