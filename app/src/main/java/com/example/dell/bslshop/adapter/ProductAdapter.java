package com.example.dell.bslshop.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dell.bslshop.DetailProductActivity;
import com.example.dell.bslshop.R;
import com.example.dell.bslshop.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;



public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> implements Filterable {

    Context context;
    ArrayList<Product> products ;
    ArrayList<Product> myLists;


    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
        this.myLists = products;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_product, null);
        ItemHolder itemHolder = new ItemHolder(mView);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Product product = products.get(position);
        holder.txtNameProduct.setText(product.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPriceProduct.setText(decimalFormat.format(product.getGiasp())+ " Đ");
        Picasso.with(context).load(product.getHinhanh())
                .placeholder(R.drawable.ic_home)
                .error(R.drawable.ic_error)
                .into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                String str = constraint.toString().toLowerCase().trim();

                if(!TextUtils.isEmpty(str)){
                    final ArrayList<Product> list = new ArrayList<>();
                    for (Product product : myLists) {
                        if (product.getTensp().toLowerCase().contains(str.toLowerCase())){
                            list.add(product);
                        }
                    }
                    filterResults.values = list;
                    filterResults.count = list.size();

                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results != null && results.count > 0) {
                    products = (ArrayList<Product>) results.values;
                    notifyDataSetChanged();
                }else if(TextUtils.isEmpty(constraint.toString())){
                    products = myLists;
                    notifyDataSetChanged();
                }else {
                    notifyDataSetChanged();
                }
            }
        };
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgProduct;
        public TextView txtNameProduct, txtPriceProduct;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            txtNameProduct = itemView.findViewById(R.id.txt_name_product);
            txtPriceProduct = itemView.findViewById(R.id.txt_price_product);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailProductActivity.class);
                    intent.putExtra("product",products.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }



    }

}
