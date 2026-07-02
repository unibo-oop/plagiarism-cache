package data;

import static org.junit.jupiter.api.Assertions.*;

class Test {

    @org.junit.jupiter.api.Test
    void test() {
        assertEquals(Languages.ENGLISH, Languages.getEnum(Languages.ENGLISH.getValue()));
        assertEquals(Languages.ITALIAN, Languages.getEnum(Languages.ITALIAN.getValue()));
    }

}
