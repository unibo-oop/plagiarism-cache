package javagotchi.test.infomanager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import javagotchi.controller.menu.MenuController;
import javagotchi.controller.menu.MenuControllerImpl;
import javagotchi.controller.menu.InformationManager;
import javagotchi.controller.menu.InformationManagerImpl;
import javagotchi.model.Javagotchi;
import javagotchi.model.JavagotchiImpl;
import javagotchi.model.information.Avatar;
import javagotchi.model.information.Gender;

/**
 * Test for saving.
 * @author giulia
 *
 */
public class TestSavingDataBase {
    private final InformationManager db = new InformationManagerImpl("prova2");
    private final MenuController controller = new MenuControllerImpl();
    private List<Javagotchi> list;
    private Javagotchi jv1;
    private Javagotchi jv2;
    private Javagotchi jv3;
    /**
    * initialize.
    */
    @Before
    public void init() {
        controller.setInfoManager(db);
        this.jv1 = new JavagotchiImpl("Crystal", Gender.MALE, Avatar.CAT);
        this.jv2 = new JavagotchiImpl("Stella", Gender.FEMALE, Avatar.FOX);
        this.jv3 = new JavagotchiImpl("Milù", Gender.FEMALE, Avatar.PANDA);
    }

    /**
     * test the efficiency of writing, reading and removing from files.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testOne() {
        this.controller.deleteAvatar(jv1.getInformation().getName());
        this.list = (List<Javagotchi>) db.resumeFile();
        final int initialSize = list.size();
        this.controller.addAvatarToList(jv1);
        assertTrue("Checking if jv1 has been added to the file", list.contains(jv1));
        assertEquals("Checking list size", list.size(), initialSize + 1);
    }
    /**
     * test the efficiency of writing, reading and removing from files.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testTwo() {
        this.controller.deleteAvatar(jv1.getInformation().getName());
        list = (List<Javagotchi>) db.resumeFile();
        final int initialSize = list.size();
        this.controller.addAvatarToList(jv2);
        assertTrue("Checking if jv2 has been added to the file", list.contains(jv2));
        assertEquals("Checking list size", list.size(), initialSize + 1);
    }
    /**
     * test the efficiency of writing, reading and removing from files.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testThree() {
        this.controller.deleteAvatar(jv3.getInformation().getName());
        list = (List<Javagotchi>) db.resumeFile();
        final int initialSize = list.size();
        this.controller.addAvatarToList(jv3);
        assertTrue("Checking if jv3 has been added to the file", list.contains(jv3));
        assertEquals("Checking list size", list.size(), initialSize + 1);
    }
}
