package ch.netzwerg.viz.scale;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScaleLinearTest {

    @Test
    public void toScreen() {
        ScaleLinear s = new ScaleLinear(10, 20, 100, 200);
        assertEquals(100, s.toScreen(10), 0.1);
        assertEquals(200, s.toScreen(20), 0.1);
        assertEquals(150, s.toScreen(15), 0.1);
    }

}