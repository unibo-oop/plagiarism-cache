package utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * The CryptDecryptString class crypt or decript string
 */
public class CryptDecryptString {

    /**
     * @param cipherMode - used to initialize Chiper Mode.
     * @param key        - the key material of the secret key
     * @param input      - the string to be crypted/decrypted
     * @return the crypted/decrypted string
     */
    public static String execCryptDecrypt(int cipherMode, String key, String input) {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipherMode, secretKey);

            byte[] outputBytes = cipher.doFinal(input.getBytes());

            String recovered = new String(outputBytes);
            return recovered;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
