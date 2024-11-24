import com.salesDep.services.ProductService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductCreatorControllerTest {
    private final ProductService productService = new ProductService();

    @Test
    void testValidateProductFields_quantity() {
        // Тести для кількості
        assertFalse(productService.validateProductFields("ValidType", "0", "100.00"), "Quantity: 0 (Invalid)");
        assertTrue(productService.validateProductFields("ValidType", "1", "100.00"), "Quantity: 1 (Valid)");
        assertTrue(productService.validateProductFields("ValidType", "10000", "100.00"), "Quantity: 10,000 (Valid)");
        assertFalse(productService.validateProductFields("ValidType", "10001", "100.00"), "Quantity: 10,001 (Invalid)");
    }

    @Test
    void testValidateProductFields_cost() {
        // Тести для вартості
        assertFalse(productService.validateProductFields("ValidType", "10", "0.00"), "Cost: 0.00 (Invalid)");
        assertTrue(productService.validateProductFields("ValidType", "10", "0.01"), "Cost: 0.01 (Valid)");
        assertTrue(productService.validateProductFields("ValidType", "10", "10000.00"), "Cost: 10,000.00 (Valid)");
        assertFalse(productService.validateProductFields("ValidType", "10", "10000.01"), "Cost: 10,000.01 (Invalid)");
    }
}
