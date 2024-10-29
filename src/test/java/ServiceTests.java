import com.salesDep.services.ContractService;
import com.salesDep.services.ProductService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceTests {

    private ContractService contractService = new ContractService();
    private ProductService productService = new ProductService();

    @Test
    public void testValidateContractTerm() {
        assertTrue(contractService.validateContractTerm("12"));
        assertFalse(contractService.validateContractTerm("-1"));
        assertFalse(contractService.validateContractTerm("invalid"));
    }

    @Test
    public void testValidateProductFields() {
        assertTrue(productService.validateProductFields("Laptop", "5", "1000"));
        assertFalse(productService.validateProductFields("", "5", "1000"));
        assertFalse(productService.validateProductFields("Laptop", "-1", "1000"));
        assertFalse(productService.validateProductFields("Laptop", "5", "invalid"));
    }
}
