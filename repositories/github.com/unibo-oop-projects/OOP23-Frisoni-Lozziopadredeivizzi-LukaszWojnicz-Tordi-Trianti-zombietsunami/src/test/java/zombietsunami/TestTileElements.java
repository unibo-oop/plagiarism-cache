package zombietsunami;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import zombietsunami.model.mapmodel.api.TileElement;
import zombietsunami.model.mapmodel.impl.TileElementImpl;

/**
 * This class is the test to check if the List that contains all the elements'
 * names are right and in the right index.
 */
class TestTileElements {
    // CPD-OFF
    /*
     * CPD suppressed because tests are naturally repetitive and their purpose
     * should be clear enough.
     */
    private final TileElement tileElement = new TileElementImpl();
    private final List<String> elements = new ArrayList<>();

    /**
     * Checks all the elements and their indexes.
     */
    @Test
    void checkAllElements() {
        int pos = 0;
        checkElement(pos++, "dirt.png");
        checkElement(pos++, "street.png");
        checkElement(pos++, "sky.png");
        checkElement(pos++, "buldingLeft.png");
        checkElement(pos++, "buldingNorthLeft.png");
        checkElement(pos++, "buldingNorth.png");
        checkElement(pos++, "buldingNorthRight.png");
        checkElement(pos++, "buldingRight.png");
        checkElement(pos++, "buldingDoor.png");
        checkElement(pos++, "buldingWindow.png");
        checkElement(pos, "flag.png");
    }

    /**
     * This method checks that there is the right elmenet in the right index of the
     * list.
     * 
     * @param index   is the index of the list
     * @param element is the string of the element
     */
    void checkElement(final int index, final String element) {
        this.elements.add(index, element);
        assertEquals(this.elements.get(index), tileElement.getTileElement().get(index));
    }

}
