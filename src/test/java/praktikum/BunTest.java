package praktikum;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class BunTest {
    String expectedName;
    float expectedPrice;
    Bun bun;

    @Before
    public void setUp() {
        expectedName = UUID.randomUUID().toString();
        expectedPrice = new Random().nextFloat();
        bun = new Bun(expectedName, expectedPrice);
    }

    @Test
    public void getName() {
        assertEquals(expectedName, bun.getName());
    }

    @Test
    public void getPrice() {
        float DELTA = 0.0001f;
        assertEquals(expectedPrice, bun.getPrice(), DELTA);
    }
}
