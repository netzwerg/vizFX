package ch.netzwerg.viz.scale;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ScaleOrdinalTest {

    @Test
    public void toScreen() {
        ScaleOrdinal<String, String> s = new ScaleOrdinal<>("a", "b", "c");

        assertEquals("a", s.toScreen("one"));
        assertEquals("b", s.toScreen("two"));

        assertEquals("Keep state", "b", s.toScreen("two"));
        assertEquals("Keep state", "a", s.toScreen("one"));

        assertEquals("c", s.toScreen("three"));

        assertEquals("Cycle range values", "a", s.toScreen("four"));
    }

}
