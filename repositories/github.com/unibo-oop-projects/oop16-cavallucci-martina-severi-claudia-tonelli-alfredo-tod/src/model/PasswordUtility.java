package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class implements an hash function to protect user's password. The
 * hashing algorithm result is a fingerprint that is stored instead the
 * password.
 */
public final class PasswordUtility {

    private static final int VALUE = 0xff;

    private PasswordUtility() {
    }

    /**
     * This method creates a fingerprint from a password.
     * 
     * @param password
     *            the password of the calendar user
     * @return the fingerprint of the password
     * @throws NoSuchAlgorithmException
     *             this exception is thrown when a particular cryptographic
     *             algorithm is requested but is not available in the
     *             environment
     */
    public static String hashPassword(final String password) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        final byte[] b = md.digest();
        final StringBuffer sb = new StringBuffer();
        for (final byte b1 : b) {
            sb.append(Integer.toHexString(b1 & VALUE).toString());
        }
        return sb.toString();

    }
}