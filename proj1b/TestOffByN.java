import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {


    @Test
    public void testOffBy5() {
        CharacterComparator offBy5 = new OffByN(5);
        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('f', 'a'));
        assertTrue(offBy5.equalChars('b', 'g'));
        assertFalse(offBy5.equalChars('a', 'b'));
        assertFalse(offBy5.equalChars('r', 't'));
        assertFalse(offBy5.equalChars('a', 'e'));
    }

    @Test
    public void testOffBy3() {
        CharacterComparator offBy3 = new OffByN(3);
        assertTrue(offBy3.equalChars('a', 'd'));
        assertTrue(offBy3.equalChars('d', 'a'));
        assertTrue(offBy3.equalChars('b', 'e'));
        assertFalse(offBy3.equalChars('a', 'b'));
        assertFalse(offBy3.equalChars('r', 't'));
        assertFalse(offBy3.equalChars('a', 'e'));
    }

}
