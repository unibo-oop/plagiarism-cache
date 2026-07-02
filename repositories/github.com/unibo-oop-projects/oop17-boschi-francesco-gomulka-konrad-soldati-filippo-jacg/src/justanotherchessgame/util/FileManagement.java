package justanotherchessgame.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import justanotherchessgame.model.MoveInfoImpl;

/**
 * Class used to save and load information from a file.
 */
public final class FileManagement {

    /**
     * Private constructor for the static utility class.
     */
    private FileManagement() {
    };

    /**
     * Function used to save the game state on a file.
     * @param file is the output file.
     * @param moves is the list of moves which represent the game.
     */
    public static void saveOnFile(final File file, final List<MoveInfoImpl> moves) {
        final Element root = new Element("Moves");
        final Document doc = new Document();
        for (final MoveInfoImpl x : moves) {
            final Point from = x.getFrom();
            final Point to = x.getTo();
            final Element parent = new Element("Move");
            final Element child = new Element("StartX");
            child.addContent(Integer.toString(from.getX()));
            final Element child1 = new Element("StartY");
            child1.addContent(Integer.toString(from.getY()));
            final Element child2 = new Element("FinalX");
            child2.addContent(Integer.toString(to.getX()));
            final Element child3 = new Element("FinalY");
            child3.addContent(Integer.toString(to.getY()));
            final Element child4 = new Element("Promotion");
            child4.addContent(x.getPromotion() == null ? "" : x.getPromotion().getSimpleName());
            parent.addContent(child);
            parent.addContent(child1);
            parent.addContent(child2);
            parent.addContent(child3);
            parent.addContent(child4);
            root.addContent(parent);
        }
        doc.setRootElement(root);
        final XMLOutputter out = new XMLOutputter();
        out.setFormat(Format.getPrettyFormat());
        try {
            out.output(doc, new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Function used to load a game state from a file.
     * @param xmlFile is the input file.
     * @return the list of moves which represent the game state.
     */
    public static List<MoveInfoImpl> loadFromFile(final File xmlFile) {
        final SAXBuilder builder = new SAXBuilder();
        final List<MoveInfoImpl> result = new ArrayList<MoveInfoImpl>();
        try {
            final Document document = (Document) builder.build(xmlFile);
            final Element rootNode = document.getRootElement();
            final List<Element> list = rootNode.getChildren("Move");
            int oldX, oldY, newX, newY;
            String promotion;
            for (int i = 0; i < list.size(); i++) {
                final Element node = (Element) list.get(i);
                oldX = Integer.parseInt(node.getChildText("StartX"));
                oldY = Integer.parseInt(node.getChildText("StartY"));
                newX = Integer.parseInt(node.getChildText("FinalX"));
                newY = Integer.parseInt(node.getChildText("FinalY"));
                promotion = node.getChildText("Promotion");
                MoveInfoImpl move = null;
                if (promotion.isEmpty()) {
                    move = new MoveInfoImpl(new Point(oldX, oldY), new Point(newX, newY));
                } else {
                    move = new MoveInfoImpl(new Point(oldX, oldY), new Point(newX, newY), PieceUtility.generateClass(promotion));
                }
                result.add(move);
            }
        } catch (Exception io) {
            System.out.println("Exception" + io.getMessage());
        }
        return result;
    }
}
