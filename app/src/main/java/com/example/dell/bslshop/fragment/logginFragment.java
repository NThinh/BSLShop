package com.example.dell.bslshop.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.bslshop.MainActivity;
import com.example.dell.bslshop.R;
import com.example.dell.bslshop.model.Customer;
import com.example.dell.bslshop.model.Product;
import com.example.dell.bslshop.ultil.SeverLocal;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class logginFragment extends Fragment {

    private View mView;
    private Button mBtnLoggin;
    private EditText mEdtPhone, mEdtPassword;

    public ArrayList<Customer> customers;


    // public View nav_headerlayout;
    // private TextView navNameTitle, navPhoneTitle, navAddressTittle;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_loggin, container, false);
        initUI();
        getDataCustomer();

        mBtnLoggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mEdtPhone.getText().toString().trim();
                String password = mEdtPassword.getText().toString().trim();


                for (int i = 0; i < customers.size(); i++) {

                    String phone1 = customers.get(i).getPhone().toString().trim();
                    String password1 = customers.get(i).getPassword().toString().trim();

                    if (phone.equals(phone1) && password.equals(password1)) {
                        MainActivity.CHECK_LOGGIN = true;
                        MainActivity.customer = customers.get(i);
                        break;
                    }
                }
                if (MainActivity.CHECK_LOGGIN) {
                    // set ẩn hiện icon navigation
                    MainActivity.navigationView.getMenu().findItem(R.id.nav_my_loggin).setEnabled(false);
                    MainActivity.navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);

                    //quay về trang chủ
                    replaceFragment(new homeFragment());
                    MainActivity.navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                    MainActivity.mBottomNavigationView.getMenu().findItem(R.id.nav_bottom_home).setChecked(true);
                    MainActivity.mCurrentFragment = MainActivity.FRAGMENT_HOME;


                    MainActivity.customerShareRefence(MainActivity.CHECK_LOGGIN); // luu tai khoan local
                    //set ten , dia chỉ cho navigation
                    MainActivity.getCusShare();

                } else {
                    Toast.makeText(getContext().getApplicationContext()
                            , "Sai thông tin tài khoản hoặc mật khẩu !"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });


        return mView;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();

    }

    private void getDataCustomer() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SeverLocal.pathCustomer
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int ID = 0;
                    String nameCustomer = "";
                    String address = "";
                    String phone = "";
                    String password = "";

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            nameCustomer = jsonObject.getString("tenkhachhang");
                            phone = jsonObject.getString("sodienthoai");
                            address = jsonObject.getString("diachi");
                            password = jsonObject.getString("password");

                            customers.add(new Customer(ID, nameCustomer, phone, address, password));

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
        mBtnLoggin = (Button) mView.findViewById(R.id.btn_loggin);
        //  mBtnRegister = (TextView) mView.findViewById(R.id.txt_register_loggin);
        mEdtPhone = (EditText) mView.findViewById(R.id.txt_phone_loggin);
        mEdtPassword = (EditText) mView.findViewById(R.id.txt_password_loggin);
        customers = new ArrayList<>();

//        nav_headerlayout = (View) MainActivity.navigationView.getHeaderView(0);
//        navNameTitle = (TextView) nav_headerlayout.findViewById(R.id.nav_name_header);
//        navPhoneTitle = (TextView) nav_headerlayout.findViewById(R.id.nav_phone_header);
//        navAddressTittle = (TextView) nav_headerlayout.findViewById(R.id.nav_address_header);


    }

}
