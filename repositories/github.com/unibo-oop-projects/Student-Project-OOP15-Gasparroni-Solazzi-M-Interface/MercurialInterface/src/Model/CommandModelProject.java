/**
 * Classe utilizzata per passare i comandi ricevuti al prompt
 *
 * @author Silvia Gasparroni
 *
 */
package Model;

import java.io.PrintStream;

import javax.swing.JOptionPane;

public class CommandModelProject{
	private static String CD="cd ";
	private static final String PATH = System.getProperty("user.home")
			+ System.getProperty("file.separator");
	private  static String command;
	private static Process p;

	/**
	 * 
	 * @param hg, dir
	 * 		  comando da eseguire, directory
	 */
	public static  void print(String hg,String dir) {
		try {
			p = Runtime.getRuntime().exec(command);
			int c=1;
			new Thread(new Thrmod(p.getErrorStream(), System.err,c)).start();
			new Thread(new Thrmod(p.getInputStream(), System.out)).start();
			//System.out.println("startMerc");
			PrintStream stdin = new PrintStream(p.getOutputStream());
			stdin.println(CD+PATH);
			stdin.flush();
			stdin.println(CD+dir);
			stdin.flush();
			stdin.println(hg);
			stdin.flush();
			stdin.close();
			p.waitFor();
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			String str=Thrmod.ReturnString.getList().toString();
			if(Thrmod.ReturnString.state()){
				JOptionPane.showMessageDialog(null, "Errore: " + str, "Errore", JOptionPane.ERROR_MESSAGE);
				//System.out.println("errore: "+str);
				Thrmod.ReturnString.reset();
			} else {
				JOptionPane.showMessageDialog(null, "Operazione Eseguita", "Eseguito", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * 
	 * @param str
	 * 		  imposta il comando
	 */
	public static  void setCommand(String str) {
		command=str;
	}
}
