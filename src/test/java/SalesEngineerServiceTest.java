import com.salesDep.model.SalesEngineer;
import com.salesDep.services.SalesEngineerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesEngineerServiceTest {

    private SalesEngineerService service;

    @BeforeEach
    void setUp() {
        service = new SalesEngineerService();
    }

    @Test
    void testValidateSalesEngineerData_AllFieldsFilled() {
        String result = service.validateSalesEngineerData("John Doe", "TechCorp", "123456789", "123 Main St", "5");
        assertEquals("", result, "Validation should pass when all fields are filled.");
    }

    @Test
    void testValidateSalesEngineerData_MissingFullName() {
        String result = service.validateSalesEngineerData("", "TechCorp", "123456789", "123 Main St", "5");
        assertEquals("Не коректне поле: Повне ім'я не може бути порожнім!\n", result, "Validation should fail when full name is missing.");
    }

    @Test
    void testValidateSalesEngineerData_MissingCompanyName() {
        String result = service.validateSalesEngineerData("John Doe", "", "123456789", "123 Main St", "5");
        assertEquals("Не коректне поле: Назва компанії не може бути порожнім!\n", result, "Validation should fail when company name is missing.");
    }

    @Test
    void testValidateSalesEngineerData_MissingPhoneNumber() {
        String result = service.validateSalesEngineerData("John Doe", "TechCorp", "", "123 Main St", "5");
        assertEquals("Не коректне поле: Номер телефону не може бути порожнім!\n", result, "Validation should fail when phone number is missing.");
    }

    @Test
    void testValidateSalesEngineerData_MissingAddress() {
        String result = service.validateSalesEngineerData("John Doe", "TechCorp", "123456789", "", "5");
        assertEquals("Не коректне поле: Адреса не може бути порожнім!\n", result, "Validation should fail when address is missing.");
    }

    @Test
    void testValidateSalesEngineerData_MissingExperience() {
        String result = service.validateSalesEngineerData("John Doe", "TechCorp", "123456789", "123 Main St", "");
        assertEquals("Не коректне поле: Стаж не може бути порожнім!\n", result, "Validation should fail when experience is missing.");
    }

    @Test
    void testUpdateSalesEngineer() {
        SalesEngineer engineer = new SalesEngineer();
        service.updateSalesEngineer(engineer, "John Doe", "TechCorp", "123456789", "123 Main St", 5);

        assertEquals("John Doe", engineer.getFullName().get(), "Full name should be set.");
        assertEquals("TechCorp", engineer.getNameOfCompany().get(), "Company name should be set.");
        assertEquals("123456789", engineer.getPhoneNum().get(), "Phone number should be set.");
        assertEquals("123 Main St", engineer.getAddress().get(), "Address should be set.");
        assertEquals(5, engineer.getExperience().get(), "Experience should be set.");
    }
}
