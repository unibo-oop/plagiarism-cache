package utility;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Utility class to authenticate the credentials of an existing user.
 */
public final class UtilsPassword {

    private static final String PASS_ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final String RAND_ALGORITHM = "SHA1PRNG";
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;

    private UtilsPassword() {
    }

    /**
     * @param attemptedPassword : a string of the password input to check.
     * @param encryptedPassword : a string of the correct password.
     * @param salt              : a string that is need to recreate the encrypted
     *                          password from the attemptedPassword.
     * @return true if the encrypted input password is the same of the real
     *         encrypted password, false otherwise.
     * @throws NoSuchAlgorithmException .
     * @throws InvalidKeySpecException .
     */
    public static boolean authenticate(final String attemptedPassword, final String encryptedPassword,
            final String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        final String encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
        return encryptedPassword.equals(encryptedAttemptedPassword);

    }

    /**
     * @param password : the string that will be converted to the encrypted
     *                 password.
     * @param salt     : the string to create a secure encrypted password through an
     *                 algorithm.
     * @return : the encrypted password generated as a string.
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeySpecException 
     */
    public static String getEncryptedPassword(final String password, final String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        final char[] chars = password.toCharArray();
        final byte[] bytes = salt.getBytes();

        final PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        final SecretKeyFactory fac = SecretKeyFactory.getInstance(PASS_ALGORITHM);
        final byte[] securePassword = fac.generateSecret(spec).getEncoded();
        spec.clearPassword();
        return Base64.getEncoder().encodeToString(securePassword);

    }

    /**
     * @return the salt, to generate a unique encrypted password, as a string
     * @throws NoSuchAlgorithmException .
     */
    public static String generateSalt() throws NoSuchAlgorithmException {
        // VERY important to use SecureRandom instead of just Random
        final SecureRandom random = SecureRandom.getInstance(RAND_ALGORITHM);
        final byte[] salt = new byte[16];
        random.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);

    }
}
