import com.example.iss_management_vanzari.models.Product;
import com.example.iss_management_vanzari.service.ProductService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest {
    private ProductService productService;

    @BeforeAll
    public void setup() {
        // Create a Hibernate session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        // Create an instance of ProductService
        productService = new ProductService(sessionFactory);
    }

//    @AfterAll
//    public void cleanup() {
//        // Close the session factory
//        productService.closeSessionFactory();
//    }

    @Test
    public void testInsertProduct() {
        // Create a new Product object
        Product product = new Product("Apple", 10, "USA", 1.5);

        // Add the product to the database
        productService.save(product);

        // Retrieve the product from the database
        Product retrievedProduct = productService.getProductByName("Apple");

        // Check if the product was retrieved correctly
        Assertions.assertEquals(product.getName(), retrievedProduct.getName());
        Assertions.assertEquals(product.getQuantity(), retrievedProduct.getQuantity());
        Assertions.assertEquals(product.getOrigin(), retrievedProduct.getOrigin());
        Assertions.assertEquals(product.getPrice(), retrievedProduct.getPrice(), 0.001);
    }
}
