package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    Bun bun;
    @Mock
    Ingredient firstIngredient;
    @Mock
    Ingredient secondIngredient;
    @Spy
    Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
    }

    @Test
    public void setBuns() {
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredient() {
        assertEquals(secondIngredient, burger.ingredients.get(1));
    }

    @Test
    public void removeIngredient() {
        burger.removeIngredient(0);
        assertEquals(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredient() {
        burger.moveIngredient(0, 1);
        assertEquals(secondIngredient, burger.ingredients.get(0));
        assertEquals(firstIngredient, burger.ingredients.get(1));
    }

    @Test
    public void getPrice() {
        float DELTA = 0.0001f;
        float bunPrice = new Random().nextFloat();
        float firstIngredientPrice = new Random().nextFloat();
        float secondIngredientPrice = new Random().nextFloat();
        double expectedBurgerPrice = bunPrice + firstIngredientPrice + secondIngredientPrice + bunPrice;

        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(firstIngredient.getPrice()).thenReturn(firstIngredientPrice);
        Mockito.when(secondIngredient.getPrice()).thenReturn(secondIngredientPrice);

        assertEquals(expectedBurgerPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void getReceipt() {
        String bunName = UUID.randomUUID().toString();
        String firstIngredientName = UUID.randomUUID().toString();
        String secondIngredientName = UUID.randomUUID().toString();
        IngredientType[] ingredientTypes = IngredientType.values();
        IngredientType firstIngredientType = ingredientTypes[new Random().nextInt(ingredientTypes.length)];
        IngredientType secondIngredientType = ingredientTypes[new Random().nextInt(ingredientTypes.length)];
        float burgerPrice = new Random().nextFloat();

        String expectedReceipt = new StringBuilder()
                .append(String.format("(==== %s ====)%n", bunName))
                .append(String.format("= %s %s =%n", firstIngredientType.toString().toLowerCase(), firstIngredientName))
                .append(String.format("= %s %s =%n", secondIngredientType.toString().toLowerCase(), secondIngredientName))
                .append(String.format("(==== %s ====)%n", bunName))
                .append(String.format("%nPrice: %f%n", burgerPrice))
                .toString();

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(firstIngredient.getName()).thenReturn(firstIngredientName);
        Mockito.when(secondIngredient.getName()).thenReturn(secondIngredientName);
        Mockito.when(firstIngredient.getType()).thenReturn(firstIngredientType);
        Mockito.when(secondIngredient.getType()).thenReturn(secondIngredientType);
        Mockito.when(burger.getPrice()).thenReturn(burgerPrice);

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
