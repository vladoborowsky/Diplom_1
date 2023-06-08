package praktikum;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class IngredientTest {
    IngredientType expectedType;
    String expectedName;
    float expectedPrice;
    Ingredient ingredient;

    @Before
    public void setUp() {
        IngredientType[] ingredientTypes = IngredientType.values();
        expectedType = ingredientTypes[new Random().nextInt(ingredientTypes.length)];
        expectedName = UUID.randomUUID().toString();
        expectedPrice = new Random().nextFloat();
        ingredient = new Ingredient(expectedType, expectedName, expectedPrice);
    }

    @Test
    public void getType() {
        assertEquals(expectedType, ingredient.getType());
    }

    @Test
    public void getName() {
        assertEquals(expectedName, ingredient.getName());
    }

    @Test
    public void getPrice() {
        float DELTA = 0.0001f;
        assertEquals(expectedPrice, ingredient.getPrice(), DELTA);
    }

}
