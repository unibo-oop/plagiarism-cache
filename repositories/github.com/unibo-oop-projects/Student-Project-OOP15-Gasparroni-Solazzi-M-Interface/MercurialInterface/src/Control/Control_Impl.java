package Control;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Model.*;
import View.*;

/**
 * Classe utilizzata l'intercetto degli eventi
 * ed il controllo delle varie informazioni
 *
 * @author Filippo Solazzi, Silvia Gasparroni
 *
 */
public class Control_Impl implements Control{

	private static Merc_View merc;
	private static Login_View login;
	private static Clone_View clone;
	private static Commit_View commit;
	private static Welcome wel;
	private static Existence ex;
	private static Log_View log;
	private static Model m;
	private JFileChooser repo = new JFileChooser();
	private JFileChooser adder = new JFileChooser();
	private JFileChooser copy = new JFileChooser();
	
	/**
	* Costruttore che inizializza il model
	* e la prima View quella di Benvenuto
	* passandosi come parametro
	*/
	public Control_Impl(){
		m = new ModelImpl();
		wel = new Welcome(this);
		wel.setVisible(true);
	}

	@Override
	public void operazioni(String opera) {
		// TODO Auto-generated method stub
		switch (opera){
		
		case "Commit":
			commit = new Commit_View(this);
			commit.setVisible(true);
			break;
			
		case "Log":
			log = new Log_View(m.doLog());
			log.setVisible(true);
			break;
			
		case "Clone":
			clone = new Clone_View(this);
			clone.setVisible(true);
			break;
			
		case "Add":
			adder.setCurrentDirectory(new java.io.File("."));
			adder.setDialogTitle("Selezionare Progetto");
			adder.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		    // disable the "All files" option.
			adder.setAcceptAllFileFilterUsed(false);
			adder.setCurrentDirectory(repo.getSelectedFile());
		    if (adder.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		    	if(dir_Diff(adder.getSelectedFile().getPath())){
		    		m.doAdd("\""+adder.getSelectedFile().getName()+"\"");
		    	}
		    }
			break;
			
		case "Copy":
			copy.setCurrentDirectory(new java.io.File("."));
			copy.setDialogTitle("Selezionare Progetto");
			copy.setFileSelectionMode(JFileChooser.FILES_ONLY);
		    // disable the "All files" option.
			copy.setAcceptAllFileFilterUsed(false);
		    if (copy.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		    	if(dir_Diff(copy.getSelectedFile().getPath())){
					//m.doCopy(copy.getSelectedFile().getPath());
		    		m.doCopy("\"" + copy.getSelectedFile().getPath() + "\"");
		    	}
		    }
			break;
		
		case "Repository":
			repo.setCurrentDirectory(new java.io.File("."));
			repo.setDialogTitle("Selezionare Repository");
			repo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    // disable the "All files" option.
			repo.setAcceptAllFileFilterUsed(false);
		    
		    if (repo.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		    		m.setRepository(repo.getSelectedFile().getPath());
		    		merc.set_Repo(repo.getSelectedFile());
		    }
			break;
			
		}
	}

	@Override
	public void username(String username) {
		
		if(m.doUser(username)){
		merc = new Merc_View_Impl(username);
		merc.set_Control(this);
		} else {
			login();
		}
	}
	
	@Override
	public void set_Repository(String url){
		m.clone_Repository(url);
		merc.set_Clone(url);
	}
	
	@Override
	public void commit(String com){
		m.doCommit(com);
	}
	
	@Override
	public boolean hg_Existence(){
		if(m.check_Existence()){
			return true;
		}
		else{
			return false;
		}	
	}
	
	@Override
	public void login(){
		login = new Login_View_Impl();
		login.set_Control(this);
	}
	
	@Override
	public void exe(){
		if(!m.check_Existence()){
			ex = new Existence(this);
			ex.setVisible(true);
		}
		else{
			this.login();
		}
	}
	
	//@Override
public boolean dir_Diff(String s){
		if(s.equals(repo.getSelectedFile().getPath())){
			JOptionPane.showMessageDialog(null, "Non si pu� scegliere la repository come programma", "Errore", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else{
			return true;
		}
	}
}