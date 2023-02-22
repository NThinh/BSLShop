package com.example.dell.bslshop.fragment;

import static com.example.dell.bslshop.fragment.cartFragment.mTxtTotalPrice;
import static com.example.dell.bslshop.fragment.cartFragment.totalPrice;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.example.dell.bslshop.CustomerActivity;
import com.example.dell.bslshop.MainActivity;
import com.example.dell.bslshop.R;
import com.example.dell.bslshop.adapter.CartAdapter;

public class inforCustomerFragment extends Fragment {

    private View mView;
    private Button mBtnCartCustomer, mBtnPayCustomer;
    private EditText mEdtName, mEdtPhone, mEdtAddress, mEdtPassword;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_info_customer, container, false);
        initUI();
        loadGetContent();
        mBtnPayCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEdtName.getText().toString().trim();
                String address = mEdtAddress.getText().toString().trim();
                String phone = mEdtPhone.getText().toString().trim();
                String password = mEdtPassword.getText().toString().trim();
                if (name.trim().equals("") || address.trim().equals("")
                        || phone.trim().equals("") || password.trim().equals("")) {
                    Toast.makeText(getContext().getApplicationContext(), "Điền đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                } else {
                    MainActivity.customer.setName(mEdtName.getText().toString().trim());
                    MainActivity.customer.setPhone(mEdtPhone.getText().toString().trim());
                    MainActivity.customer.setAddress(mEdtAddress.getText().toString().trim());
                    MainActivity.customer.setPassword(mEdtPassword.getText().toString().trim());
                    replaceFragment(new paymentFragment());
                    CustomerActivity.mCurrentFragment2 = CustomerActivity.FRAGMENT_PAYMENT;

                }
            }
        });

        mBtnCartCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), MainActivity.class);
                MainActivity.mCurrentFragment = MainActivity.FRAGMENT_CART;
                getActivity().finish();
                startActivity(intent);
            }
        });

        return mView;
    }


    private void loadGetContent() {
        if (MainActivity.customer != null) {
            mEdtName.setText(MainActivity.customer.getName());
            mEdtPhone.setText(MainActivity.customer.getPhone());
            mEdtAddress.setText(MainActivity.customer.getAddress());
            mEdtPassword.setText(MainActivity.customer.getPassword());
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame_customer, fragment);
        transaction.commit();
    }

    private void initUI() {
        mBtnCartCustomer = (Button) mView.findViewById(R.id.btn_cart_customer);
        mBtnPayCustomer = (Button) mView.findViewById(R.id.btn_pay_cart_customer);
        mEdtName = (EditText) mView.findViewById(R.id.txt_name_account);
        mEdtPhone = (EditText) mView.findViewById(R.id.txt_phone_account);
        mEdtAddress = (EditText) mView.findViewById(R.id.txt_address_account);
        mEdtPassword = (EditText) mView.findViewById(R.id.txt_password_account);

    }
}
