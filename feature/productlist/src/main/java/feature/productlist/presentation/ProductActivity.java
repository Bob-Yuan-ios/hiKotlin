package feature.productlist.presentation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.productlist.databinding.ActivityProductBinding;

import data.product.impl.ProductRepositoryImpl;
import domain.product.respository.ProductRepository;

public class ProductActivity extends AppCompatActivity {

    private ProductAdapter adapter;
    private ProductViewModel viewModel;
    private ActivityProductBinding productBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productBinding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(productBinding.getRoot());
        productBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductAdapter();
        productBinding.recyclerView.setAdapter(adapter);

        ProductRepository repository = new ProductRepositoryImpl();
        ProductViewModelFactory factory = new ProductViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(ProductViewModel.class);
        viewModel.getProduct().observe(this,
                products -> adapter.setData(products));
    }
}
