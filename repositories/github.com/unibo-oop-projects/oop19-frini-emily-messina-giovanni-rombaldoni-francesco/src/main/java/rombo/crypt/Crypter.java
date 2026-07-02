package rombo.crypt;

public interface Crypter {
	
	/*this method is usefull for crypt a string (the CryptKey will be into the the class, and it will be static).*/
	 public  String Crypt(String s);
	 
	 /*this method is usefull for decrypt a String previously crypted, using a static CryptKey.*/
	 public  String Decrypt(String s);

}
