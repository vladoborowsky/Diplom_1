package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

//    Булку надо бы проверить как следует. Выделить хотя бы основные критичные и граничные значения.
//    Для названия -- пустая строка, null, очень длинная строка, спецсимволы в строке, и т.д.
//    Для цены -- отрицательная цена, ноль, минимальная положительная цена (дробная), максимально возможная положительная, и т.д.

@RunWith(Parameterized.class)
public class BunAdditionalTest {
    private final String expectedName;
    private final float expectedPrice;

    public BunAdditionalTest(String name, float price) {
        this.expectedName = name;
        this.expectedPrice = price;
    }

    @Parameterized.Parameters
    public static Object[] getData() {
        return new Object[][]{
                {"", -100},
                {null, 0},
                {"очень длинная строка, очень длинная строка, очень длинная строка", 0.01f},
                {"$пец$имвлы_в_$троке", 9999}
        };
    }

    @Test
    public void getName() {
        Bun bun = new Bun(expectedName, expectedPrice);
        assertEquals(expectedName, bun.getName());
    }

    @Test
    public void getPrice() {
        float DELTA = 0.0001f;
        Bun bun = new Bun(expectedName, expectedPrice);
        assertEquals(expectedPrice, bun.getPrice(), DELTA);
    }
}