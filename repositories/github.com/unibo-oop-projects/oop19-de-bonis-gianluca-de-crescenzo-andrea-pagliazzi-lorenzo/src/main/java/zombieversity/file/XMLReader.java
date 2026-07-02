package zombieversity.file;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * Classe realizzata con lo scopo di leggere file XML, successivamente non è stata poi utilizzata pertanto il comportamento non è descritto.
 *
 */
public class XMLReader {
 
        private final Document document;
 
        public XMLReader(final String url) throws SAXException, IOException, ParserConfigurationException {
                this.document = DocumentBuilderFactory.newInstance()
                                                .newDocumentBuilder().parse(new File(url));
                this.document.getDocumentElement().normalize();
        }
        /**
         * 
         * @param root -> that i want to know the sub-node(tags) name
         * @return set of tags name as Set<String>
         */
        public Set<String> getTagsName(final Node root) {
               final Set<String> tags = new HashSet<>();
               final NodeList tagNode = root.getChildNodes();
                for (int i = 0; i < tagNode.getLength(); i++) {
                        if (tagNode.item(i).hasChildNodes()) {
                            tags.addAll(this.getTagsName(tagNode.item(i)));
                        }
                       final String tagName = tagNode.item(i).getNodeName();
                        if (!tagName.contains("#")) {
                            tags.add(tagName);
                        }
                }
                return tags;
        }
        /**
         * 
         * @return get the rootElement = the source of the xml file
         */
        public Node getRoot() {
                return this.document.getDocumentElement();
        }
        /**
         * 
         * @param tag 
         * @return set of attributes relative to the given tag
         */
        public Set<String> getAttributesName(final Node tag) {
                final Set<String> attributes = new HashSet<>();
                final NamedNodeMap y = tag.getAttributes();
                for (int i = 0; i < y.getLength(); i++) {
                    attributes.add(y.item(i).getNodeName());
                }
                return attributes;
        }
        /**
         * 
         * @param tagname
         * @return List of specified tag, with relative "attributes" : "value" 
         */
        public List<Map<String, String>> getTagsLoaded(final String tagname) {
               final List<Map<String, String>> attrAndValues = new LinkedList<>();
                final NodeList elements = this.document.getElementsByTagName(tagname);
                for (int i = 0; i < elements.getLength(); i++) {
                    attrAndValues.add(this.getTagLoaded(elements.item(i)));
                }
                return attrAndValues;
        }
        public final Map<String, String> getTagLoaded(final Node tagname) {
               final Map<String, String> result = this.getAttributesValue(tagname);
                        result.put("content", tagname.getTextContent());
                return result;
        }

        public final Map<String, String> getAttributesValue(final Node tagname) {
                return this.getAttributesName(tagname)
                                        .stream()
                                        .collect(Collectors.toMap(
                                                        t -> t,
                                                        t ->  tagname.getAttributes()
                                                        .getNamedItem(t).getNodeValue()));
        }
        public final int getChildCount(final String tagname, final int index, final String childName) {
                int result = childName.contains("#") ? -1 : 0;
                final NodeList list = this.document.getElementsByTagName(tagname).item(index).getChildNodes();
                for (int i = 0; i < list.getLength(); i++) {
                    if (list.item(i).getNodeName().contains(childName)) {
                        result++;
                    }
                }
                return result;
        }
        /**
         * 
         * @param tagname
         * @param index -> -1 means i want all
         * @return the content of the specified tag and index
         */
        public String getNodeContent(final String tagname, final int index) {
                final NodeList tags = this.document.getElementsByTagName(tagname);
                String result = "";
                if (index >= 0) {
                    return tags.item(index).getTextContent();
                } else {
                    for (int i = 0; i < tags.getLength(); i++) {
                        result = result.concat(this.getNodeContent(tagname, i));
                    }
                }
                return result;
        }

        public final int getNodeTypeCount(final String name) {
            return this.document.getElementsByTagName(name).getLength();
        }

        public final List<Map<String, String>> getChildLoaded(final String name, final int index, final String childName) {

            final List<Map<String, String>> result = new LinkedList<>();
            final NodeList childNodes = this.document.getElementsByTagName(name).item(index).getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                final Node child = childNodes.item(i);
                if (child.getNodeName().equals(childName)) {
                    result.add(this.getTagLoaded(child));
                }
            }
            return result;
        } 
}
