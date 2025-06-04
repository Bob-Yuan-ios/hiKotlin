package feature.productlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import domain.product.model.Product;
import domain.product.respository.ProductRepository;

public class ProductViewModel extends ViewModel {

    private final MutableLiveData<List<Product>> production = new MutableLiveData<>();
    private final ProductRepository productRepository;

    public ProductViewModel(ProductRepository productRepository){
        this.productRepository = productRepository;
        loadProduct();
    }

    public LiveData<List<Product>> getProduct(){
        return production;
    }

    private void loadProduct(){
        production.setValue(productRepository.getProductList());
    }
}
