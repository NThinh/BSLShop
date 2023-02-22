package com.example.dell.bslshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.bslshop.adapter.CartAdapter;
import com.example.dell.bslshop.model.Cart;
import com.example.dell.bslshop.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class DetailProductActivity extends AppCompatActivity {

    private ImageButton mIBtnPrevious;
    private Button mBtnAddCart, mBtnMinus, mBtnValue, mBtnAdd;
    private TextView mTxtName, mTxtDescript, mTxtPrice;
    private ImageView mImgProduct;
    private CheckBox mCbkM, mCbkL, mCbkXl;

    private int priceDetail = 0;
    private String name = "";
    private int id = 0;
    private String imgProduct = "";
    private String size = "M";

    private int value = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        initUI();
        getDataProduct();
        mCbkM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    size = mCbkM.getText().toString();
                    mCbkL.setChecked(false);
                    mCbkXl.setChecked(false);
                }
            }
        });
        mCbkL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    size = mCbkL.getText().toString();
                    mCbkM.setChecked(false);
                    mCbkXl.setChecked(false);
                }
            }
        });
        mCbkXl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    size = mCbkXl.getText().toString();
                    mCbkL.setChecked(false);
                    mCbkM.setChecked(false);
                }
            }
        });
        mIBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.carts.size() > 0) {
                    int sl = Integer.parseInt(mBtnValue.getText().toString());
                    boolean exists = false;
                    for (int i = 0; i < MainActivity.carts.size(); i++) {
                        if (MainActivity.carts.get(i).getIdsp() == id && MainActivity.carts.get(i).getSize() == size) {
                            MainActivity.carts.get(i).setSoluong(
                                    MainActivity.carts.get(i).getSoluong() + sl);
                            MainActivity.carts.get(i).setGiasp(
                                    priceDetail * MainActivity.carts.get(i).getSoluong());
                            exists = true;
                        }
                    }
                    if (exists == false) {
                        int soluong = Integer.parseInt(mBtnValue.getText().toString());
                        long gia = soluong * priceDetail;
                        MainActivity.carts.add(new Cart(id, name, gia, imgProduct, soluong, size,priceDetail,false));

                    }
                } else {
                    int soluong = Integer.parseInt(mBtnValue.getText().toString());
                    long gia = soluong * priceDetail;
                    MainActivity.carts.add(new Cart(id, name, gia, imgProduct, soluong, size, priceDetail,false));
                }


                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                MainActivity.mCurrentFragment = MainActivity.FRAGMENT_CART;
                startActivity(intent);
                finish();
            }
        });

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = Integer.parseInt(mBtnValue.getText().toString().trim());
                value += 1;
                mBtnValue.setText(value + "");
                if (value != 1) {
                    mBtnMinus.setEnabled(true);
                }
            }
        });
        mBtnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = Integer.parseInt(mBtnValue.getText().toString().trim());
                if (value != 1) {
                    value -= 1;
                    mBtnValue.setText(value + "");
                    mBtnMinus.setEnabled(true);
                } else {
                    mBtnMinus.setEnabled(false);
                }

            }
        });
    }


    private void getDataProduct() {

        Product item = (Product) getIntent().getSerializableExtra("product");
        priceDetail = item.getGiasp();
        name = item.getTensp();
        id = item.getId();
        imgProduct = item.getHinhanh();

        mTxtName.setText(name);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        mTxtPrice.setText(decimalFormat.format(priceDetail) + " Đ");
        mTxtDescript.setText(item.getMota());
        Picasso.with(getApplicationContext()).load(imgProduct)
                .placeholder(R.drawable.ic_home)
                .error(R.drawable.ic_error)
                .into(mImgProduct);
    }

    private void initUI() {
        mIBtnPrevious = findViewById(R.id.ibtn_previous);
        mImgProduct = findViewById(R.id.img_product_detail);
        mTxtName = findViewById(R.id.txt_tittle);
        mTxtDescript = findViewById(R.id.txt_descript);
        mTxtPrice = findViewById(R.id.txt_price);
        mBtnAdd = findViewById(R.id.btn_plus);
        mBtnAddCart = findViewById(R.id.btn_add);
        mBtnMinus = findViewById(R.id.btn_minus);
        mBtnValue = findViewById(R.id.btn_value);
        mCbkM = findViewById(R.id.cbk_m);
        mCbkL = findViewById(R.id.cbk_l);
        mCbkXl = findViewById(R.id.cbk_xl);
    }
}