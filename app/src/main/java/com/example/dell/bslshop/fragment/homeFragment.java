package com.example.dell.bslshop.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.bslshop.MainActivity;
import com.example.dell.bslshop.R;
import com.example.dell.bslshop.adapter.ProductAdapter;
import com.example.dell.bslshop.model.Product;
import com.example.dell.bslshop.ultil.SeverLocal;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class homeFragment extends Fragment {

    public static View mView;
    private ViewFlipper mViewFlipper;
    private EditText mEditTextSearch;
    private RecyclerView mRecyclerViewProduct;
    private ArrayList<Product> products;
    private ProductAdapter productAdapter;

    private EditText mEdtSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        initUI();
        ActionViewFlipper();
        getProduct();

        mEdtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                productAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return mView;
    }


    private void getProduct() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SeverLocal.pathProduct
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int ID = 0;
                    String nameProduct = "";
                    int price = 0;
                    String imgProduct = "";
                    String descript = "";
                    int idProduct = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            nameProduct = jsonObject.getString("tensp");
                            price = jsonObject.getInt("giasp");
                            imgProduct = jsonObject.getString("hinhanhsp");
                            descript = jsonObject.getString("mota");
                            idProduct = jsonObject.getInt("idsanpham");

                            products.add(new Product(ID, nameProduct, price, imgProduct, descript, idProduct));
                            productAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext().getApplicationContext()
                        , error.getMessage() + "\n đọc không đz", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void initUI() {
        mViewFlipper = mView.findViewById(R.id.viewflipper);
        mEditTextSearch = mView.findViewById(R.id.edt_search);
        mRecyclerViewProduct = mView.findViewById(R.id.rcv_product);
        products = new ArrayList<>();
        productAdapter = new ProductAdapter(getContext().getApplicationContext(), products);
        mRecyclerViewProduct.setHasFixedSize(true);
        mRecyclerViewProduct.setLayoutManager(new GridLayoutManager(getContext().getApplicationContext(), 2));
        mRecyclerViewProduct.setAdapter(productAdapter);

        mEdtSearch = (EditText) mView.findViewById(R.id.edt_search);
    }

    private void ActionViewFlipper() {
        ArrayList<String> viewFlipperList = new ArrayList<>();
        viewFlipperList.add("https://file.hstatic.net/200000043184/file/web_cc6126ae777646199842cd00c54032f3.jpg");
        viewFlipperList.add("https://hoctienghan.com/uploads/1(5).jpg");
        viewFlipperList.add("https://theme.hstatic.net/1000191031/1000280552/14/slide_index_4.jpg?v=51");
        viewFlipperList.add("https://reviewnao.com/wp-content/uploads/2019/02/shop-thoi-trang-nu-o-nha-trang.jpg");
        for (int i = 0; i < viewFlipperList.size(); i++) {
            ImageView imageView = new ImageView(getContext().getApplicationContext());
            //đọc dường dẫn thành image
            Picasso.with(getContext().getApplicationContext()).load(viewFlipperList.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mViewFlipper.addView(imageView);
        }
        mViewFlipper.setFlipInterval(5000);
        mViewFlipper.setAutoStart(true);
        Animation animation_lide_in = AnimationUtils.loadAnimation(getContext().getApplicationContext()
                , R.anim.slide_in_right);
        Animation animation_lide_out = AnimationUtils.loadAnimation(getContext().getApplicationContext()
                , R.anim.slide_out_right);
        mViewFlipper.setInAnimation(animation_lide_in);
        mViewFlipper.setOutAnimation(animation_lide_out);
    }


}
