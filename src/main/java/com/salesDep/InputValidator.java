package com.salesDep;

public class InputValidator {
    public static String validateNotEmpty(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            return "Не коректне поле: " + fieldName + " не може бути порожнім!\n";
        }
        return "";
    }

    public static String validatePositiveInteger(String input, String fieldName) {
        try {
            int value = Integer.parseInt(input);
            if (value <= 0) {
                return fieldName + " повинен бути додатнім числом!\n";
            }
        } catch (NumberFormatException e) {
            return fieldName + " має бути цілим числом!\n";
        }
        return "";
    }

    public static String validatePositiveDouble(String input, String fieldName) {
        try {
            double value = Double.parseDouble(input);
            if (value <= 0) {
                return fieldName + " повинен бути додатнім числом!\n";
            }
        } catch (NumberFormatException e) {
            return fieldName + " має бути числом з плаваючою крапкою!\n";
        }
        return "";
    }
}