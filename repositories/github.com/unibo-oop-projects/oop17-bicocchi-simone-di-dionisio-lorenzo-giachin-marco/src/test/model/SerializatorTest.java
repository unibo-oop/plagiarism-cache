//CHECKSTYLE:OFF
package test.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

import org.junit.Test;

import model.Model;
import model.ModelImpl;
import model.Serializator;
import model.container.Box;
import model.container.Item;

/**
 * test serializator.
 *
 */
public class SerializatorTest {

    @Test
    public void testLoading() {
        Model model = new ModelImpl();
        File initialfile = Paths.get("res/initialStats.txt").toFile();
        File testfile = Paths.get("res/test.txt").toFile();
        Item hamburger = new Item("Hamburger", 50, 5, "file: ../../imgs/hamburger.png");
        try {
            testfile.delete();
            testfile.createNewFile();
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(initialfile)));
            ObjectInputStream ois2 = new ObjectInputStream(new BufferedInputStream(new FileInputStream(testfile)));
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(testfile)));
            assertTrue(model.getShopMap().isEmpty());
            Serializator.loadInfoCharacter(ois2, (ModelImpl) model);
            fail("file test is empty");
            Serializator.loadFirstInformation(ois, (ModelImpl) model);
            assertTrue(!model.getShopMap().isEmpty());
            model.getItemMap().forEach((string, list) -> assertTrue(list != null));
            assertTrue(!model.getShopMap().get("HUNGRY").contains(new Box(hamburger)));
            model.buy("Hamburger");
            assertTrue(model.getShopMap().get("HUNGRY").contains(new Box(hamburger)));
            Serializator.serializeFile(oos, (ModelImpl) model);
            Serializator.loadInfoCharacter(ois2, (ModelImpl) model);
            assertTrue(model.getShopMap().get("HUNGRY").contains(new Box(hamburger)));
            ois.close();
            ois2.close();
            oos.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

}
