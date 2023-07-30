package Controller;

import Entity.Product;
import Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 新增
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // 獲得產品資料
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // 根據產品流水號獲得產品資料
    @GetMapping("/{productNo}")
    public ResponseEntity<Product> getProductByProductNo(@PathVariable String productNo) {
        Product product = productService.getProductByProductNo(productNo);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    // 更新
    @PutMapping("/{productNo}")
    public ResponseEntity<Product> updateProduct(@PathVariable String productNo, @RequestBody Product updatedProduct) {
        Product product = productService.updateProduct(productNo, updatedProduct);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    // 刪除
    @DeleteMapping("/{productNo}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productNo) {
        if (productService.deleteProduct(productNo)) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
