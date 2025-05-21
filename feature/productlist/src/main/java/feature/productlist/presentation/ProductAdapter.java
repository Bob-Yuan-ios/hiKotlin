package feature.productlist.presentation;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productlist.R;

import java.util.List;

import domain.product.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Product> products){

        this.productList = products;
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);
        }
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        @SuppressLint("ResourceType")
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_product,
                parent,
                false);
        return new ProductViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.textName.setText(p.getName());
        holder.textPrice.setText("$" + p.getPrice());
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }
}
