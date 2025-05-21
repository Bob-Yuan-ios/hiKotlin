package domain.product.respository;

import java.util.List;
import domain.product.model.Product;

public interface ProductRepository {
     List<Product> getProductList();
}
