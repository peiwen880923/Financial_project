package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repository.ProductRepository;
import Entity.Product;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByProductNo(String productNo) {
        return productRepository.findByProductNo(productNo);
    }

    public Product updateProduct(String productNo, Product updatedProduct) {
        Product product = productRepository.findByProductNo(productNo);
        if (product != null) {
            product.setProductName(updatedProduct.getProductName());
            product.setPrice(updatedProduct.getPrice());
            product.setFeeRate(updatedProduct.getFeeRate());
            return productRepository.save(product);
        }
        return null;
    }

    public boolean deleteProduct(String productNo) {
        Product product = productRepository.findByProductNo(productNo);
        if (product != null) {
            productRepository.delete(product);
            return true;
        }
        return false;
    }
}

