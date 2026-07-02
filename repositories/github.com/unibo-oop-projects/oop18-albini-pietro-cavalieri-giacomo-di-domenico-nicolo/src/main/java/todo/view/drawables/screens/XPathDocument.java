package todo.view.drawables.screens;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import todo.utils.Checks;

class XPathDocument {
    private final Document document;
    private final String rootName;

    XPathDocument(final Document document, final String rootName) {
        this.document = document;
        this.rootName = rootName;
    }

    public String getStringFromXPath(final String xpath) {
        try {
            final XPathExpression expr = XPathFactory.newInstance().newXPath().compile(this.rootName + xpath);
            Checks.require(exists(expr), NoXPathResultsException.class, "no xpath results for " + xpath);
            return (String) expr.evaluate(this.document, XPathConstants.STRING);
        } catch (final XPathExpressionException e) {
            throw new IllegalStateException(
                    "An error occurred when parsing the XPath. Is the syntax correct and is the XML correctly formed?",
                    e);
        }
    }

    public int getIntegerFromXPath(final String xpath) {
        return Integer.parseInt(getStringFromXPath(xpath));
    }

    private boolean exists(final XPathExpression expr) throws XPathExpressionException {
        final NodeList nodes = (NodeList) expr.evaluate(this.document, XPathConstants.NODESET);
        return nodes != null && nodes.getLength() > 0;
    }
}
