/**
 * classe utilizzata per gestire il log
 *
 * @author Silvia Gasparroni
 *
 */
package Model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class CommandLog{

	private static Process p;
	private static final String CD ="cd ";
	private static final String PATH=System.getProperty("user.home")
			+ System.getProperty("file.separator");
	private  static String command;

	/**
	 * 
	 * @param hg, dir
	 * 		  comando da eseguire, directory
	 * 
	 * @return List<String>
	 * 		   ritorna una lista di stringhe che corrisponde al log
	 */
	public static  List<String> print(String hg,String dir) {
		List<String> list= new ArrayList<>();
		try {
			p = Runtime.getRuntime().exec(command);
			int c=1; //intercetto errore
			int d=0; //intercetto log

			new Thread(new Thrmod(p.getErrorStream(), System.err,c)).start();
			new Thread(new Thrmod(p.getInputStream(), System.out,d)).start();
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
				JOptionPane.showMessageDialog(null, "Errore " + str, "Errore", JOptionPane.ERROR_MESSAGE);
				System.out.println("errore: "+str);
				Thrmod.ReturnString.reset();
				//lista resta uguale
			}else if(Thrmod.ReturnString.getElements().contains("hg log")){
				//lista resta uguale

			}else if(Thrmod.ReturnString.getElements().equals(" ")){
				System.out.println("uguale a stringa vuota");
		    }else{
				String sr= Thrmod.ReturnString.getElements();

				String[] log= sr.split("\\n");
				//System.out.println("log"+log[0]);
				//System.out.println("log"+ log[1]);
				int index=0;
				int index2=0;
				for (int i=0;i<log.length;i++){
					index=log[i].indexOf(":");
					index2=log[i].length();
					String bff=log[i].substring(0,index+1);
					String bff2= log[i].substring(index+1,index2);
					while(bff2.startsWith(" ")){
						bff2= bff2.substring(1,bff2.length());

					}

					list.add(bff);
					list.add(bff2);

				}
				//System.out.println(list.toString());
			}
		}
		//elimino tutti i parametri
		Thrmod.ReturnString.reset();
		return list;
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
