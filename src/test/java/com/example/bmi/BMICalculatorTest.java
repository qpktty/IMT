package com.example.bmi;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class BMICalculatorTest {
    private BMICalculator calculator = new BMICalculator();

    // 1. Основной тест расчета ИМТ
    @Test(groups = {"basic", "calculation"})
    public void testCalculateBMI() {
        // ARRANGE
        final double WEIGHT = 70.0;
        final double HEIGHT = 1.75;
        final double EXPECTED_BMI = 22.9;
        double actualBMI;

        // ACT
        actualBMI = calculator.calculateBMI(WEIGHT, HEIGHT);

        // ASSERT
        Assert.assertEquals(actualBMI, EXPECTED_BMI, 0.1, "ИМТ для 70кг/1.75м должен быть 22.9");
    }

    // 2. Тест расчета с ростом в см
    @Test(groups = {"convenience", "calculation"})
    public void testCalculateBMIWithCm() {
        // ARRANGE
        final double WEIGHT = 70.0;
        final double HEIGHT_CM = 175.0;
        final double EXPECTED_BMI = 22.9;
        double actualBMI;

        // ACT
        actualBMI = calculator.calculateBMIWithCm(WEIGHT, HEIGHT_CM);

        // ASSERT
        Assert.assertEquals(actualBMI, EXPECTED_BMI, 0.1, "ИМТ для 70кг/175см должен быть 22.9");
    }

    // 3. Тест категории "Норма"
    @Test(groups = {"categories"})
    public void testGetBMICategory_Normal() {
        // ARRANGE
        final double BMI_VALUE = 22.0;
        final String EXPECTED_CATEGORY = "Норма";
        String actualCategory;

        // ACT
        actualCategory = calculator.getBMICategory(BMI_VALUE);

        // ASSERT
        Assert.assertEquals(actualCategory, EXPECTED_CATEGORY, "ИМТ 22.0 должен быть категории 'Норма'");
    }

    // 4. Тест категории "Избыточный вес"
    @Test(groups = {"categories"})
    public void testGetBMICategory_Overweight() {
        // ARRANGE
        final double BMI_VALUE = 27.0;
        final String EXPECTED_CATEGORY = "Избыточная масса тела";
        String actualCategory;

        // ACT
        actualCategory = calculator.getBMICategory(BMI_VALUE);

        // ASSERT
        Assert.assertEquals(actualCategory, EXPECTED_CATEGORY, "ИМТ 27.0 должен быть категории 'Избыточная масса тела'");
    }

    // 5. Тест нормального веса (true)
    @Test(groups = {"validation"})
    public void testIsNormalWeight_True() {
        // ARRANGE
        final double WEIGHT = 65.0;
        final double HEIGHT = 1.75;
        boolean actualResult;

        // ACT
        actualResult = calculator.isNormalWeight(WEIGHT, HEIGHT);

        // ASSERT
        Assert.assertTrue(actualResult, "Вес 65кг при росте 1.75м должен быть нормальным");
    }

    // 6. Тест нормального веса (false)
    @Test(groups = {"validation"})
    public void testIsNormalWeight_False() {
        // ARRANGE
        final double WEIGHT = 100.0;
        final double HEIGHT = 1.75;
        boolean actualResult;

        // ACT
        actualResult = calculator.isNormalWeight(WEIGHT, HEIGHT);

        // ASSERT
        Assert.assertFalse(actualResult, "Вес 100кг при росте 1.75м НЕ должен быть нормальным");
    }

    // 7. Тест детального результата
    @Test(groups = {"output"})
    public void testGetDetailedResult() {
        // ARRANGE
        final double WEIGHT = 70.0;
        final double HEIGHT = 1.75;
        String actualResult;

        // ACT
        actualResult = calculator.getDetailedResult(WEIGHT, HEIGHT);

        // ASSERT - исправленные проверки
        Assert.assertTrue(actualResult.contains("ИМТ"), "Результат должен содержать 'ИМТ'");
        Assert.assertTrue(actualResult.contains("22,9") || actualResult.contains("22.9"),
                "Результат должен содержать значение ИМТ 22.9 или 22,9");
        Assert.assertTrue(actualResult.contains("Норма"), "Результат должен содержать категорию 'Норма'");
    }

    // 8. ТЕСТ ИСКЛЮЧЕНИЙ - при нулевом росте
    @Test(groups = {"exceptions"}, expectedExceptions = IllegalArgumentException.class)
    public void testCalculateBMI_ZeroHeight_ThrowsException() {
        // ARRANGE
        final double WEIGHT = 70.0;
        final double HEIGHT = 0.0;

        // ACT & ASSERT (в тестах исключений действие и проверка объединены)
        calculator.calculateBMI(WEIGHT, HEIGHT);
    }

    // 9. ТЕСТ ИСКЛЮЧЕНИЙ - при отрицательном росте в см
    @Test(groups = {"exceptions"}, expectedExceptions = IllegalArgumentException.class)
    public void testCalculateBMIWithCm_NegativeHeight_ThrowsException() {
        // ARRANGE
        final double WEIGHT = 70.0;
        final double HEIGHT_CM = -175.0;

        // ACT & ASSERT
        calculator.calculateBMIWithCm(WEIGHT, HEIGHT_CM);
    }

    // 10. DataProvider для параметризованного теста
    @DataProvider(name = "bmiTestData")
    public Object[][] bmiTestData() {
        return new Object[][] {
                {50.0, 1.60, 19.5},   // вес, рост, ожидаемый ИМТ
                {80.0, 1.80, 24.7},
                {90.0, 1.75, 29.4},
                {60.0, 1.70, 20.8}
        };
    }

    // 11. Параметризованный тест с использованием DataProvider
    @Test(groups = {"data-driven"}, dataProvider = "bmiTestData")
    public void testCalculateBMI_WithDataProvider(double weight, double height, double expectedBMI) {
        // ARRANGE
        double actualBMI;

        // ACT
        actualBMI = calculator.calculateBMI(weight, height);

        // ASSERT
        Assert.assertEquals(actualBMI, expectedBMI, 0.1,
                String.format("ИМТ для %.0fкг/%.2fм должен быть %.1f", weight, height, expectedBMI));
    }
}
