package controller.Implementations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import model.Interfaces.Guard;
import view.Interfaces.LoginView;
import view.Interfaces.MainView;

/**
 * controller della login view
 */
public class LoginControllerImpl {
	
	LoginView loginView;

	/**
	 * costruttore
	 * @param loginView la view
	 */
	public LoginControllerImpl(LoginView loginView){
		this.loginView=loginView;
		loginView.addLoginListener(new LoginListener());
		loginView.displayErrorMessage("accedere con i profili: \n id:3 , password:qwerty \n id:2 , password:asdasd ");
	}
	
	/**
	 * listener che si occupa di effettuare il login
	 */
	public class LoginListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
						
			List<Guard> guards = null;
			try {
				//salvo la lista delle guardie nel file
				guards = getGuards();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			boolean isInside=false;

			//controllo che non ci siano errori
			if(loginView.getUsername().isEmpty() || loginView.getPassword().isEmpty()){
				loginView.displayErrorMessage("Devi inserire username e password");
			}
			else{
				for (Guard g : guards){
					if(loginView.getUsername().equals(String.valueOf(g.getUsername())) && loginView.getPassword().equals(g.getPassword())){
						//effettuo il login
						isInside=true;
						loginView.displayErrorMessage("Benvenuto Utente "+ loginView.getUsername());	
						loginView.dispose();
						new MainControllerImpl(new MainView(g.getRank()));
					}
				}
				if(isInside==false){
					loginView.displayErrorMessage("Combinazione username/password non corretta");
				}
				isInside = false;
			}
		}
		
	}
	
	/**
	 * metodo che restutuisce la lista di guardie salvata su file
	 * @return lista di guardie
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Guard> getGuards() throws IOException, ClassNotFoundException{
		File f = new File("res/GuardieUserPass.txt");
		//se il file è vuoto restituisco un file vuoto
		if(f.length()!=0){
			FileInputStream fi = new FileInputStream(f);
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			List<Guard> guards = new ArrayList<>();
			
			try{
				while(true){
					//salvo ogni guardia in una lista
					Guard s = (Guard) oi.readObject();
					guards.add(s);
				}
			}catch(EOFException eofe){}
			
			fi.close();
			oi.close();
			return guards;
		}
		return new ArrayList<Guard>();
		
	}
}
