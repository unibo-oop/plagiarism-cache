/**
 *
 *
 * @author  Silvia Gasparroni
 *
 */
package Model;

import java.util.List;

public interface Model {
	
	/**
	 * Imposta il cmd
	 */
	void setCmd();
	
	/**
	 * 
	 * @param file da copiare
	 */
	void doCopy(String path2);
	/**
	 * 
	 * @param repository
	 */
	void setRepository(String repo);
	/**
	 * 
	 * @return true se Mercurial è istallato
	 */
	boolean check_Existence();
	/**
	 * 
	 * @param commit
	 */
	void doCommit(String commento);
	/**
	 * 
	 * @return lista contenente log
	 */
	List<String> doLog();
	/**
	 * 
	 * @param file
	 */
	void doAdd(String dir);
	/**
	 * 
	 * @param Urlrepository
	 */
	void clone_Repository(String url);
	/**
	 * 
	 * @param userName
	 */
	boolean doUser(String user);
}
