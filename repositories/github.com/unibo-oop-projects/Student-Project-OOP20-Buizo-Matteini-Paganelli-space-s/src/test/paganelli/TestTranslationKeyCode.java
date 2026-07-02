package test.paganelli;

import static org.junit.Assert.assertEquals;
import java.util.Optional;
import org.junit.Test;
import spacesurvival.utilities.CommandKey;
import spacesurvival.utilities.CommandType;

public class TestTranslationKeyCode {

    @Test
    public void testTranslationNotExist() {

        final Optional<CommandKey> expectedValue = Optional.empty();
        final Optional<CommandKey> currentValue = CommandKey.getValue(1, CommandType.PRESSED);
        assertEquals(expectedValue, currentValue);
    }

    @Test
    public void testTranslationExist() {

        final Optional<CommandKey> expectedValue = Optional.of(CommandKey.KEY_A);
        final Optional<CommandKey> currentValue = CommandKey.getValue(CommandKey.KEY_A.getKeyCode(), CommandType.PRESSED);
        assertEquals(expectedValue.get(), currentValue.get());
    }

}
