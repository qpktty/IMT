class BMICalculator:
    """
    Калькулятор индекса массы тела (ИМТ)
    """
    
    def calculate_bmi(self, weight: float, height: float) -> float:
        """
        Расчет ИМТ по весу и росту
        """
        if height <= 0:
            raise ValueError("Рост должен быть положительным числом")
        
        bmi = weight / (height ** 2)
        return round(bmi, 1)
    
    def calculate_bmi_with_cm(self, weight_kg: float, height_cm: float) -> float:
        """
        Расчет ИМТ с ростом в сантиметрах
        """
        if height_cm <= 0:
            raise ValueError("Рост должен быть положительным числом")
        
        height_m = height_cm / 100
        return self.calculate_bmi(weight_kg, height_m)
    
    def get_bmi_category(self, bmi: float) -> str:
        """
        Определение категории веса по ИМТ
        """
        if bmi < 16:
            return "Выраженный дефицит массы тела"
        elif bmi < 18.5:
            return "Недостаточная масса тела"
        elif bmi < 25:
            return "Норма"
        elif bmi < 30:
            return "Избыточная масса тела"
        elif bmi < 35:
            return "Ожирение 1 степени"
        elif bmi < 40:
            return "Ожирение 2 степени"
        else:
            return "Ожирение 3 степени"
    
    def get_detailed_result(self, weight: float, height: float) -> str:
        """
        Форматированный результат с ИМТ и категорией
        """
        bmi = self.calculate_bmi(weight, height)
        category = self.get_bmi_category(bmi)
        return f"ИМТ: {bmi}. Категория: {category}"
    
    def is_normal_weight(self, weight: float, height: float) -> bool:
        """
        Проверка нормального веса
        """
        bmi = self.calculate_bmi(weight, height)
        return 18.5 <= bmi < 25
    
    def get_health_recommendation(self, weight: float, height: float) -> str:
        """
        Рекомендация по здоровью
        """
        bmi = self.calculate_bmi(weight, height)
        if bmi < 18.5:
            return "Рекомендуется увеличить потребление калорий"
        elif bmi < 25:
            return "Поддерживайте текущий вес"
        else:
            return "Рекомендуется снизить вес"
