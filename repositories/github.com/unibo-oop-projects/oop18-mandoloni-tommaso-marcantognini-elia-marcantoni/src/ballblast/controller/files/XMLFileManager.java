package ballblast.controller.files;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import ballblast.controller.DirectoryManager;

/**
 * Class with static methods to handle xml files.
 */
public final class XMLFileManager {

    private XMLFileManager() {
        // Cannot create an XMLFileManager object.
    }

    /**
     * Check if the input password of the user is correct.
     * 
     * @param userName the user name.
     * @param pwd      the password to verify.
     * @return true if the password is correct, false otherwise.
     * @throws ParserConfigurationException Parser exception.
     * @throws SAXException                 SAX exception.
     * @throws IOException                  IO exception.
     * @throws XPathExpressionException     XPathExpression exception.
     */
    public static boolean checkUserPassword(final String userName, final String pwd)
            throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        final Document doc = getDocument(DirectoryManager.USERS_LIST_FILE);

        final XPathFactory xPathFactory = XPathFactory.newInstance();
        final XPath xPath = xPathFactory.newXPath();
        final XPathExpression expression = xPath.compile("//user[@id='" + userName + "']");

        final Element root = (Element) expression.evaluate(doc, XPathConstants.NODE);
        final Element user = (Element) root.cloneNode(true);

        final Element password = (Element) user.getFirstChild();

        return password.getTextContent().equals(pwd);
    }

    /**
     * Creates an empty xml file.
     * 
     * @param filePath the new file route path in the file system.
     * @param rootName the name of the xml file root.
     * @throws ParserConfigurationException Parser exception.
     * @throws TransformerException         Transformer exception.
     */
    public static void createEmptyFile(final String filePath, final String rootName)
            throws ParserConfigurationException, TransformerException {
        final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        final Document doc = docBuilder.newDocument();
        final Element rootElement = doc.createElement(rootName);
        doc.appendChild(rootElement);

        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();

        final DOMSource source = new DOMSource(doc);

        final StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);

    }

    /**
     * Add a new UserData informations to the users list file..
     * 
     * @param userName the user name to add in the file.
     * @param password the password associated with the user.
     * @throws ParserConfigurationException Parser exception.
     * @throws IOException                  IO exception.
     * @throws TransformerException         Transformer exception.
     * @throws SAXException                 SAX exception.
     */
    public static void submitUser(final String userName, final String password)
            throws ParserConfigurationException, IOException, TransformerException, SAXException {
        final Document doc = getDocument(DirectoryManager.USERS_LIST_FILE);
        final Element root = doc.getDocumentElement();

        final Element user = doc.createElement("user");
        final Attr id = doc.createAttribute("id");
        id.setValue(userName);
        user.setAttributeNode(id);
        user.setIdAttributeNode(id, true);
        root.appendChild(user);

        final Element pwd = doc.createElement("password");
        pwd.appendChild(doc.createTextNode(password));
        user.appendChild(pwd);

        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();

        final DOMSource source = new DOMSource(doc);

        final StreamResult result = new StreamResult(new File(DirectoryManager.USERS_LIST_FILE));
        transformer.transform(source, result);
    }

    private static Document getDocument(final String path)
            throws ParserConfigurationException, SAXException, IOException {
        final File file = new File(path);
        final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(file);
    }

}
