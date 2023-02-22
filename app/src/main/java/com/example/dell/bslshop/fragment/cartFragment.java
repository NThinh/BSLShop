package com.example.dell.bslshop.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dell.bslshop.CustomerActivity;
import com.example.dell.bslshop.DetailProductActivity;
import com.example.dell.bslshop.MainActivity;
import com.example.dell.bslshop.R;
import com.example.dell.bslshop.adapter.CartAdapter;
import com.example.dell.bslshop.model.Cart;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class cartFragment extends Fragment {

    private View mView;
    private ListView mLvCart;
    public static TextView mTxtTotalPrice;
    private TextView mTxtNotify;
    private TextView mTxtNote;
    private Button mBtnChooseAll, mBtnClear, mBtnPay, mBtnPayContinue;

    public CartAdapter cartAdapter;
    public static boolean CHECK_CHOOSE_LINE = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       mView = inflater.inflate(R.layout.fragment_cart, container, false);

        initUI();
        checkData();
        totalPrice ();
       mBtnPayContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new homeFragment());
                MainActivity.mCurrentFragment = MainActivity.FRAGMENT_HOME;
                MainActivity.mBottomNavigationView.getMenu().findItem(R.id.nav_bottom_home).setChecked(true);
                MainActivity.navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                MainActivity.mTitleActionBar.setText(getResources().getString(R.string.nav_home));
            }
        });

        mBtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.carts.size() > 0){
                    Intent intent = new Intent(getContext().getApplicationContext(), CustomerActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext().getApplicationContext(), "Giỏ hàng không có sản phẩm !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBtnChooseAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!CHECK_CHOOSE_LINE) {
                    CHECK_CHOOSE_LINE = true;
                }else {
                    CHECK_CHOOSE_LINE = false;
                }
                replaceFragment(new cartFragment());

            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Cart> listTemp = new ArrayList<>();
                for (int i = 0 ; i < MainActivity.carts.size(); i++) {
                    if(MainActivity.carts.get(i).isCheck() == true) {
                        listTemp.add(MainActivity.carts.get(i));
                    }
                }
                MainActivity.carts.removeAll(listTemp);
                replaceFragment(new cartFragment());
                CHECK_CHOOSE_LINE = false;
            }
        });

        return mView;
    }
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();

    }

    public static void totalPrice() {
        long totalAmount = 0;
        for (int i = 0 ; i < MainActivity.carts.size(); i ++) {

            totalAmount += MainActivity.carts.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        mTxtTotalPrice.setText(decimalFormat.format(totalAmount) + " Đ");
    }

    private void checkData() {
        if(MainActivity.carts.size() <= 0){
            cartAdapter.notifyDataSetChanged();
            mTxtNotify.setVisibility(View.VISIBLE);
            mLvCart.setVisibility(View.INVISIBLE);
        }else {
            cartAdapter.notifyDataSetChanged();
            mTxtNotify.setVisibility(View.INVISIBLE);
            mLvCart.setVisibility(View.VISIBLE);
        }
    }

    private void initUI() {
//        private ListView mLvCart;
//        private TextView mTxtTotalPrice, mTxtNotify, mTxtNote;
//        private Button mBtnChooseAll, mBtnClear, mBtnPay, mBtnPayContinue;

        mBtnChooseAll = (Button)mView.findViewById(R.id.btn_chose_all_cart);
        mBtnClear = (Button) mView.findViewById(R.id.btn_clear_cart);

        mBtnPay = (Button) mView.findViewById(R.id.btn_pay_cart);
        mBtnPayContinue = (Button)mView.findViewById(R.id.btn_continue_cart);

        mTxtNotify = (TextView) mView.findViewById(R.id.txt_null_cart);
        mTxtTotalPrice = (TextView) mView.findViewById(R.id.txt_total_price_cart);

        mLvCart = (ListView) mView.findViewById(R.id.lv_cart);
        cartAdapter = new CartAdapter(getContext().getApplicationContext(), MainActivity.carts);
        mLvCart.setAdapter(cartAdapter);



    }
}
