package com.example.bmi;

public class BMICalculator {

    public double calculateBMI(double weight, double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Рост должен быть положительным числом.");
        }
        double bmi = weight / (height * height);
        return Math.round(bmi * 10.0) / 10.0;
    }

    public double calculateBMIWithCm(double weightKg, double heightCm) {
        if (heightCm <= 0) {
            throw new IllegalArgumentException("Рост должен быть положительным числом.");
        }
        double heightInMeters = heightCm / 100.0;
        return calculateBMI(weightKg, heightInMeters);
    }

    public String getBMICategory(double bmi) {
        if (bmi < 16) {
            return "Выраженный дефицит массы тела";
        } else if (bmi < 18.5) {
            return "Недостаточная масса тела";
        } else if (bmi < 25) {
            return "Норма";
        } else if (bmi < 30) {
            return "Избыточная масса тела";
        } else if (bmi < 35) {
            return "Ожирение 1 степени";
        } else if (bmi < 40) {
            return "Ожирение 2 степени";
        } else {
            return "Ожирение 3 степени";
        }
    }

    public String getDetailedResult(double weight, double height) {
        double bmi = calculateBMI(weight, height);
        String category = getBMICategory(bmi);
        return String.format("ИМТ: %.1f. Категория: %s", bmi, category);
    }

    public boolean isNormalWeight(double weight, double height) {
        double bmi = calculateBMI(weight, height);
        return bmi >= 18.5 && bmi < 25;
    }
}