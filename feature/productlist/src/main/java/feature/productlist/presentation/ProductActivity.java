package feature.productlist.presentation;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productlist.R;

import java.util.logging.Logger;

import data.product.impl.ProductRepositoryImpl;
import domain.product.respository.ProductRepository;

public class ProductActivity extends AppCompatActivity {

    private ProductAdapter adapter;
    private ProductViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);

        ProductRepository repository = new ProductRepositoryImpl();
        ProductViewModelFactory factory = new ProductViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(ProductViewModel.class);
        viewModel.getProduct().observe(this,
                products -> adapter.setData(products));
    }
}
