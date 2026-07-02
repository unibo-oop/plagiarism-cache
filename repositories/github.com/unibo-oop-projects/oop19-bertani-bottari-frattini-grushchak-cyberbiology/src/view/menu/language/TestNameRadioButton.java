package view.menu.language;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import data.Languages;

class TestNameRadioButton {

    @Test
    void test() {
        Arrays.asList(Languages.values()).forEach(l -> {
            if (l.equals(Languages.ENGLISH)) {
                assertEquals(1, (l.getValue() + 1));
            }
            if (l.equals(Languages.ITALIAN)) {
                assertEquals(2, (l.getValue() + 1));
            }

        });
    }

}
