package Model;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;


/**
 * Classe utilizzata l'intercetto degli eventi
 * ed il controllo delle varie informazioni
 *
 * @author Silvia Gasparroni
 *
 */
class Thrmod implements Runnable
{
	private final OutputStream ostrm_;
	private final InputStream istrm_;
	private final int err;

	
	public Thrmod(InputStream istrm, OutputStream ostrm) {
		istrm_ = istrm;
		ostrm_ = ostrm;
		err=-1;
	}

	
	public Thrmod(InputStream istrm, OutputStream ostrm,int o) {
		istrm_ = istrm;
		ostrm_ = ostrm;
		err=o;

	}
	
	public void run() {
		try
		{
			byte[] buffer = new byte[1024];
			for (int length = 0; (length = istrm_.read(buffer)) != -1; )
			{
				ostrm_.write(buffer, 0, length);
				if(err>0){
					String bff= new String(buffer);
					//System.out.println("stampare:"+bff);
					buffer= new byte[1024];
					ReturnString.setString(bff);
				}else if (err==0){
					String bff= new String(buffer);
					buffer= new byte[1024];
					ReturnString.setLog(bff);
				}
			}



		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * la seguente classe mi permette di gestire le informazioni
	 *
	 * @return informazioni 
	 */
	public static  class ReturnString {
		private static List<String> lista= new ArrayList<>();
		private static int index=0;
		private static List<String> log = new ArrayList<>();
		private static int i=0;
		
		/**
		 * Il seguente metodo permette di aggiungere le informazioni sugli errori su una lista
		 * 
		 * @param  informazioni  
		 */
		public static void setString(String str){
			System.out.println("sono entrato sono unerrore"+str);
			lista.add(str);
			index++;
		}

		/**
		 * Il seguente metodo permette di aggiungere le informazioni dei comandi in una lista
		 *  @param  informazioni   
		 */
		public static void setLog(String str){
			
			System.out.println("sto aggiundo la riga "+i + str);
			System.out.println("fine");
			log.add(str);
			i++;

		}

		/**
		 * Il seguente metodo restituisce informazioni su mercurial
		 * 
		 * @return true se non è installato mercurial
		 */
		static boolean state(){
			return index>0?true:false;
		}

		/**
		 * Il seguente metodo restituisce una lista contente gli eventuali errori
		 *         
		 * @return lista
		 */
		public static List<String> getList(){
			return lista;
		}

		/**
		 * Il seguente metodo restituisce una lista contente la riposta ai comandi inviati
		 * @return log
		 */
		public static List<String> getLista(){
			return log;
		}
		
		
		/**
		 * Il seguente metodo restituisce un elemento della lista
		 *         
		 * @return elemento
		 */
		public static String  getElements(){

            System.out.println("sono in get elemensts :");
			int size=log.size();
			System.out.println("size:"+size);
           // String str= log.isEmpty()? " ": log.get(size-2);
			//return log.get(size-2);
            String str;
            if(log.isEmpty()){
           	 str=" ";
            }else if(size >1){
           	 str=log.get(size-2);
            }else{
           	 str=log.get(size-1);
            }

            System.out.println("sono in get elemensts 2:"+ str);
            return str;
		}
		
		/**
		 * Il seguente metodo mi permette di restituire l'user
		 *         
		 * @return user
		 */
		public static String getUser(){
			System.out.println(""+getLista().size());
			int size= log.size();
			String str;
			str= log.isEmpty()? " ": log.get(size/2);// mi restituisce  l'user
			//str=log.get(size/2);
			return str;
		}
		
		/**
		 * Il seguente metodo mi permette di intercettare se l'user è presente
		 *         
		 * @return true se l'user è presente
		 */
		public static boolean stateUser(){
			
			//return log.size()>4? true :false; //se maggiore di 4 c'è un user

			return log.size()%2==0? false :true; //pari-->non c'è user
		}

		/**
		 * Il seguente metodo mi permette di resettare i parametri
		 *         
		 */
		public static void reset() {
			i=0;
			index=0;
			lista.clear();
			log.clear();	
		}
	}
}
 