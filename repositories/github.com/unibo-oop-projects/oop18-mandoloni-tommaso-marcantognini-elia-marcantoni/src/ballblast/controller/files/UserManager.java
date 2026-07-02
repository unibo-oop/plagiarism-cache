package ballblast.controller.files;

import java.io.IOException;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import ballblast.model.data.UserData;

/**
 * The manager for handling {@link UserData}s.
 */
public interface UserManager {

    /**
     * Tries to login a user.
     * 
     * @param userName the user name.
     * @param password the user password.
     * @return an {@link Optional} {@link UserData} object, empty if the login
     *         failed.
     * @throws XPathExpressionException XPathExpressionException 
     * @throws IOException IOException
     * @throws SAXException SAXException
     * @throws ParserConfigurationException ParserConfigurationException 
     */
    Optional<UserData> login(String userName, String password) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException;

    /**
     * Tries to register a new user.
     * 
     * @param userName the user name.
     * @param password the user password.
     * @return an {@link Optional} {@link UserData} object, empty if the
     *         registration failed.
     * @throws ParserConfigurationException Parser exception.
     * @throws IOException                  IO exception.
     * @throws TransformerException         Transformer exception.
     * @throws SAXException                 SAX exception.
     */
    Optional<UserData> register(String userName, String password) throws ParserConfigurationException, IOException, TransformerException, SAXException;

    /**
     * Updates the user game datas at the end of a game session.
     * 
     * @param user the {@link UserData} to update
     * @return true if save operation done successfully, false otherwise.
     */
    boolean updateUserData(UserData user);

}
