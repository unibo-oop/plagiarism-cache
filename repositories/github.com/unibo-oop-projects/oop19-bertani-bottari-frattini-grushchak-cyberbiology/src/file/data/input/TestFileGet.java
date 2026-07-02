package file.data.input;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import color.filter.Filters;

class TestFileGet {

    @Test
    void test() {
        final FileSet setterTest = new FileSetImpl();
        setterTest.addtoFile(10, 20, 30, 40, 50, 2);

        FileGetSetting getterTest = new FileGetSettingImpl();
        assertEquals(10, getterTest.getMaxEnery());
        assertEquals(20, getterTest.getSunLight());
        assertEquals(30, getterTest.getHeightWorld());
        assertEquals(40, getterTest.getWidthWorld());
        assertEquals(50, getterTest.getUpDateFrame());
        assertEquals(Filters.NUTRITION, getterTest.getColorFilter());

        FileSet setterHueTest = new FileSetImpl();

        setterHueTest.addtoFile(60, 70, 80, 90, 100, 0, Optional.of((float) 0.2));

        FileGetSetting getterHueTest = new FileGetSettingImpl();

        assertEquals(60, getterHueTest.getMaxEnery());
        assertEquals(70, getterHueTest.getSunLight());
        assertEquals(80, getterHueTest.getHeightWorld());
        assertEquals(90, getterHueTest.getWidthWorld());
        assertEquals(100, getterHueTest.getUpDateFrame());
        assertEquals(Filters.AGE, getterHueTest.getColorFilter());
        assertEquals((float) 0.2, getterHueTest.getHue());
    }

}
