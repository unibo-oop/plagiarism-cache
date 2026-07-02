
/**
 * Classe utilizzata per creare una repository
 *
 * @author Silvia Gasparroni
 *
 */
package Model;

import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ChoiseRepository {
	private static String directory;
	private static Process p;
	private static String command;

	/**
	 * 
	 * @param dir
	 * 		  imposta la directory
	 */
	public static void setDirectory(String dir){
		directory=dir;
	}

	/**
	 * Crea la repository
	 */
	public static void CreateRepository(){
		try{

			p = Runtime.getRuntime().exec(command);
			int c=1;
			new Thread(new Thrmod(p.getErrorStream(), System.err,c)).start();
			new Thread(new Thrmod(p.getInputStream(), System.out)).start();
			PrintWriter stdin = new PrintWriter(p.getOutputStream());

			stdin.flush();
			stdin.println("cd "+directory);
			stdin.flush();
			stdin.println("hg init");
			stdin.flush();
			stdin.close();

			p.waitFor();
			//int value=p.waitFor();
			//System.out.println("value"+value);

			p.waitFor();
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			//String str=Thrmod.ReturnString.getList().toString();
			if(Thrmod.ReturnString.state()){
				JOptionPane.showMessageDialog(null, "La repository esiste già" , "Attenzione", JOptionPane.WARNING_MESSAGE);
				//System.out.println("errore: " + str);
				Thrmod.ReturnString.reset();
			} else {
				JOptionPane.showMessageDialog(null, "Repository creata" , "Creato", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * 
	 * @param str
	 * 		  imposta il comando
	 */
	public static void setCommand(String str) {
		command=str;
	}
}
