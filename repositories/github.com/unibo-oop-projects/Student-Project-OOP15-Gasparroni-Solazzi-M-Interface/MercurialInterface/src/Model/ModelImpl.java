/**
 * Classe utilizzata per verificare l'esistenza di mercurial e gestire i vari comandi
 *
 * @author  Silvia Gasparroni
 *
 */
package Model;

import java.io.PrintStream;
import java.util.List;

import javax.swing.JOptionPane;


public class ModelImpl implements Model {
	
	private static String cmd;
	private static Process p;

	private String dir;
	private String repo;
	static boolean va=true; 
	private static boolean bool;
	
	/**
	* metodo che permette di impostare il comando adeguato a seconda del sistema
	*
	* @return comando
	*/
	private static String cmdArray(){
		String cmd;
		if (!System.getProperty("os.name").contains("Windows")){
			cmd="bash";
		}else{
			cmd="cmd";
		}
		return cmd;
	}
	
	@Override
	public void setCmd(){
		String str=cmdArray();
		cmd=str;
		CommandModelProject.setCommand(str);
		ChoiseRepository.setCommand(str);
		CommandLog.setCommand(str);
	}
	
	@Override
	 public void doCopy(String elem) {
		 if (!System.getProperty("os.name").contains("Windows")){
				CommandModelProject.print("cp "+elem +" "+repo," ");
			}else{
				CommandModelProject.print("copy "+elem +" \""+repo+"\"" , " ");
				
			}
	}
	
	@Override 
	public void setRepository(String repo){
		this.repo = repo;
		ChoiseRepository.setDirectory(repo);
    	ChoiseRepository.CreateRepository();
	}
	
	@Override 
	public boolean check_Existence() {
    setCmd();
    return check("hg","");
	}
		
	/**
	* 
	* @param comando hg , username 
	*
	* @return true se rispetta i requisiti
	*/	
	private boolean check( String s, String username){	
		bool=false;
		try {
			p = Runtime.getRuntime().exec(cmd);
			
			int c= s=="hg"? 1 : -1;
			//int d= s=="hg config ui.username"?0:-1;
			int d=0;
			new Thread(new Thrmod(p.getErrorStream(), System.err,c)).start();
			new Thread(new Thrmod(p.getInputStream(), System.out,d)).start();
			PrintStream stdin = new PrintStream(p.getOutputStream());
			stdin.println(""+s);
		    stdin.flush();
		    stdin.close();
		    p.waitFor();
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally{
		   if(s=="hg"){ 
			    if(!Thrmod.ReturnString.state()){
				 bool = true;
				 System.out.println("lista"+Thrmod.ReturnString.getLista().toString());
			     }	
		   }else{
			   
		   String str=Thrmod.ReturnString.getUser();
		    int index=str.contains(">")? (str.indexOf(">")+1): str.indexOf("");
		    System.out.println("index"+index);
			String si=str.substring(0, index);
			System.out.println( "user:"+si+"fine");
			System.out.println("stateUser:"+Thrmod.ReturnString.stateUser());
			if(Thrmod.ReturnString.stateUser() & index>0){
				bool=true;
				if(!si.equals(username)){
				JOptionPane.showMessageDialog(null, "inserire nome utente corretto:" + str, "login", JOptionPane.INFORMATION_MESSAGE);
				bool=false;
				}
			 }else{
				 System.out.println("sono entrato");
				 String comando;
				 JOptionPane.showMessageDialog(null, "inserire username=" + username + "<nome@hotmail.it>", "login", JOptionPane.INFORMATION_MESSAGE);
				 
					if (System.getProperty("os.name").contains("Windows")){
				     comando="hg config --edit";
				     CommandModelProject.print(comando,"");
				    }else if(System.getProperty("os.name").contains("Mac")){
				     comando="open "+System.getProperty("user.home") + System.getProperty("file.separator")+".hgrc -t";
					 
					 CommandModelProject.print(comando,"");
					 
				    }else{
				     comando="xdg-open "+System.getProperty("user.home")+System.getProperty("file.separator")+".hgrc ";
					 CommandModelProject.print(comando,"");
			         }
			 } 
			
		}
		   }
				

		//JOptionPane.showMessageDialog(null, "lista:"+Thrmod.ReturnString.getLista(), ""+Thrmod.ReturnString.getLista().size(), JOptionPane.INFORMATION_MESSAGE);
		Thrmod.ReturnString.reset();
		//JOptionPane.showMessageDialog(null, "s"+bool, "login", JOptionPane.INFORMATION_MESSAGE);
		return bool;
}

	@Override 
	public void doCommit(String commento)  {
		CommandModelProject.print("hg commit -m" + " " + commento , this.repo);
	}
	
	@Override 
	public List<String> doLog()  {
		return CommandLog.print("hg log", this.repo);
	}
	
	@Override 
	public void doAdd(String dir)  {
		CommandModelProject.print("hg add "+ dir, this.repo);
		
	}
	
	@Override 
	public void clone_Repository(String url) {
		CommandModelProject.print("hg clone "+url, this.dir);
		
	}
	
	@Override 
	public boolean doUser(String username){
		setCmd();
	   return check("hg config ui.username",username);
	
	}
	
}