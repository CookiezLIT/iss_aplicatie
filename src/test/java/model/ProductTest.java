package model;
import com.example.iss_management_vanzari.models.Product;
import com.example.iss_management_vanzari.service.ProductService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import java.util.List;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductTest {

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

    @Test
    public void testSaveProduct() {
        Product product = new Product("Product 2", 200, "Romania", 200.0);
        productService.save(product);

        Product savedProduct = productService.getProductById(product.getId());
        Assertions.assertEquals(product.getId(), savedProduct.getId());
        productService.deleteProduct(product);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product("Product 2", 200, "Romania", 200.0);
        productService.save(product);

        Product savedProduct = productService.getProductById(product.getId());
        savedProduct.setName("Product 2 Updated");
        productService.updateProduct(savedProduct);

        Product updatedProduct = productService.getProductById(savedProduct.getId());
        Assertions.assertEquals(savedProduct.getName(), updatedProduct.getName());

        productService.deleteProduct(product);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product("Product 2", 200, "Romania", 200.0);
        productService.save(product);

        productService.deleteProduct(product);

        Product deletedProduct = productService.getProductById(product.getId());
        Assertions.assertNull(deletedProduct);

    }

    @Test
    public void testGetProductById() {
        Product product = new Product("Product 2", 200, "Romania", 200.0);
        productService.save(product);

        Product savedProduct = productService.getProductById(product.getId());
        Assertions.assertEquals(product.getId(), savedProduct.getId());

        productService.deleteProduct(product);
    }

    @Test
    public void testSubtractQuantityFromProduct() {
        Product product = new Product("Product 2", 200, "Romania", 200.0);
        productService.save(product);

        productService.subtractQuantityFromProduct(product.getId(), 100);

        Product updatedProduct = productService.getProductById(product.getId());
        Assertions.assertEquals(100, updatedProduct.getQuantity());

        productService.deleteProduct(product);
    }

    @Test
    public void testGetProductByName() {
        Product product = new Product("Product 2", 200, "Romania", 200.0);
        productService.save(product);

        Product savedProduct = productService.getProductByName(product.getName());
        Assertions.assertEquals(product.getName(), savedProduct.getName());

        productService.deleteProduct(product);
    }
}
