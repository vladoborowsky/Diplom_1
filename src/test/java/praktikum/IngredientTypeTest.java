package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class IngredientTypeTest {
    private final IngredientType expectedType;
    private final String expectedName;
    public IngredientTypeTest(IngredientType type, String name) {
        this.expectedType = type;
        this.expectedName = name;
    }

    @Parameterized.Parameters()
    public static Object[][] getParameters() {
        return new Object[][]{
                {IngredientType.SAUCE, "SAUCE"},
                {IngredientType.FILLING, "FILLING"}
        };
    }

    @Test
    public void shouldReturnsCorrectEnumType() {
        Assert.assertEquals(expectedType, IngredientType.valueOf(expectedName));
    }
}