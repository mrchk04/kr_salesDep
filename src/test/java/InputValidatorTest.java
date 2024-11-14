import com.salesDep.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    private InputValidator inputValidator;

    @BeforeEach
    void setUp() {
        inputValidator = new InputValidator();
    }

    // Тестування номера телефону
    @Test
    void testPhoneNumberValidation() {
        // Клас 1: Коректний формат
        assertTrue(inputValidator.validatePhoneNumber("+380123456789"), "Expected valid phone number");

        // Клас 2: Неправильний код країни
        assertFalse(inputValidator.validatePhoneNumber("+390123456789"), "Expected invalid phone number due to incorrect country code");
        assertFalse(inputValidator.validatePhoneNumber("0123456789"), "Expected invalid phone number missing country code");

        // Клас 3: Неправильна довжина
        assertFalse(inputValidator.validatePhoneNumber("+38012345678"), "Expected invalid phone number due to length < 13");
        assertFalse(inputValidator.validatePhoneNumber("+3801234567890"), "Expected invalid phone number due to length > 13");

        // Клас 4: Неправильний формат з буквами та символами
        assertFalse(inputValidator.validatePhoneNumber("+38012A45678"), "Expected invalid phone number with alphabetic character");
        assertFalse(inputValidator.validatePhoneNumber("+3801234*6789"), "Expected invalid phone number with special character");
    }

    // Тестування стажу
    @Test
    void testExperienceValidation() {
        // Клас 1: Допустимі значення
        assertTrue(inputValidator.validateExperience(5), "Expected valid experience within range");
        assertTrue(inputValidator.validateExperience(10), "Expected valid experience within range");
        assertTrue(inputValidator.validateExperience(30), "Expected valid experience within range");

        // Клас 2: Негативні значення
        assertFalse(inputValidator.validateExperience(-1), "Expected invalid experience: negative number");
        assertFalse(inputValidator.validateExperience(-5), "Expected invalid experience: negative number");

        // Клас 3: Значення, що перевищують максимальний ліміт
        assertFalse(inputValidator.validateExperience(51), "Expected invalid experience: exceeds maximum limit");
        assertFalse(inputValidator.validateExperience(100), "Expected invalid experience: exceeds maximum limit");

        // Клас 4: Некоректний формат (не числові значення)
        assertFalse(inputValidator.validateExperience("п’ять"), "Expected invalid experience: non-numeric string");
        assertFalse(inputValidator.validateExperience("123abc"), "Expected invalid experience: alphanumeric string");
    }
}
