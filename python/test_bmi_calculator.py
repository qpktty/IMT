import pytest
from bmi_calculator import BMICalculator

class TestBMICalculator:
    
    def setup_method(self):
        self.calculator = BMICalculator()
    
    @pytest.mark.basic
    @pytest.mark.calculation
    def test_calculate_bmi(self):
        weight = 70.0
        height = 1.75
        expected_bmi = 22.9
        
        actual_bmi = self.calculator.calculate_bmi(weight, height)
        assert actual_bmi == expected_bmi

    @pytest.mark.convenience
    @pytest.mark.calculation  
    def test_calculate_bmi_with_cm(self):
        weight = 70.0
        height_cm = 175.0
        expected_bmi = 22.9
        
        actual_bmi = self.calculator.calculate_bmi_with_cm(weight, height_cm)
        assert actual_bmi == expected_bmi

    @pytest.mark.categories
    def test_get_bmi_category_normal(self):
        bmi_value = 22.0
        expected_category = "Норма"
        
        actual_category = self.calculator.get_bmi_category(bmi_value)
        assert actual_category == expected_category

    @pytest.mark.categories
    def test_get_bmi_category_overweight(self):
        bmi_value = 27.0
        expected_category = "Избыточная масса тела"
        
        actual_category = self.calculator.get_bmi_category(bmi_value)
        assert actual_category == expected_category

    @pytest.mark.validation
    def test_is_normal_weight_true(self):
        weight = 65.0
        height = 1.75
        
        result = self.calculator.is_normal_weight(weight, height)
        assert result

    @pytest.mark.validation
    def test_is_normal_weight_false(self):
        weight = 100.0
        height = 1.75
        
        result = self.calculator.is_normal_weight(weight, height)
        assert not result

    @pytest.mark.output
    def test_get_detailed_result(self):
        weight = 70.0
        height = 1.75
        
        result = self.calculator.get_detailed_result(weight, height)
        assert "ИМТ: 22.9" in result
        assert "Норма" in result

    @pytest.mark.recommendations
    def test_get_health_recommendation(self):
        weight = 70.0
        height = 1.75
        
        recommendation = self.calculator.get_health_recommendation(weight, height)
        assert "поддерживайте" in recommendation.lower()

    @pytest.mark.exceptions
    def test_calculate_bmi_zero_height_raises_exception(self):
        weight = 70.0
        height = 0.0
        
        with pytest.raises(ValueError, match="Рост должен быть положительным числом"):
            self.calculator.calculate_bmi(weight, height)

    @pytest.mark.exceptions
    def test_calculate_bmi_with_cm_negative_height_raises_exception(self):
        weight = 70.0
        height_cm = -175.0
        
        with pytest.raises(ValueError, match="Рост должен быть положительным числом"):
            self.calculator.calculate_bmi_with_cm(weight, height_cm)

    @pytest.mark.parametrize("weight,height,expected_bmi", [
        (50.0, 1.60, 19.5),
        (80.0, 1.80, 24.7),
        (90.0, 1.75, 29.4),
        (60.0, 1.70, 20.8)
    ])
    @pytest.mark.data_driven
    def test_calculate_bmi_with_parameters(self, weight, height, expected_bmi):
        actual_bmi = self.calculator.calculate_bmi(weight, height)
        assert actual_bmi == expected_bmi
