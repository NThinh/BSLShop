package com.example.dell.bslshop.fragment;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.bslshop.CustomerActivity;
import com.example.dell.bslshop.MainActivity;
import com.example.dell.bslshop.R;
import com.example.dell.bslshop.ultil.SeverLocal;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class paymentFragment extends Fragment {

    private View mView;
    private Button mBtnCommitPayment, mBtnCustomerPayment;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_payment, container, false);
        initUI();
        mBtnCustomerPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new inforCustomerFragment());
                CustomerActivity.mCurrentFragment2 = CustomerActivity.FRAGMENT_PAYMENT;

            }
        });
        mBtnCommitPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, SeverLocal.pathOrder,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String responseMDH) {
                                Log.d("madonhang",responseMDH);
                                if (Integer.parseInt(responseMDH) > 0) {
                                    RequestQueue requestQueue1 = Volley.newRequestQueue(getContext()
                                            .getApplicationContext());
                                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST,SeverLocal.pathOrderDetail
                                            , new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.d("ma",response);
                                            if(response.equals("1")){
                                                MainActivity.carts.clear();
                                                Toast.makeText(getContext().getApplicationContext()
                                                        , "Bạn đã đặt hàng thành công"
                                                        , Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getContext().getApplicationContext()
                                                        ,MainActivity.class);
                                                MainActivity.mCurrentFragment = MainActivity.FRAGMENT_HOME;
                                                // set share
                                                MainActivity.CHECK_LOGGIN = true;
                                                MainActivity.customerShareRefence(MainActivity.CHECK_LOGGIN);
                                                getActivity().finish();
                                                startActivity(intent);
                                                Toast.makeText(getContext().getApplicationContext()
                                                        ,"Mời bạn tiếp tục mua hàng !" , Toast.LENGTH_SHORT).show();

                                            }else {
                                                Toast.makeText(getActivity().getApplicationContext(),
                                                        "Lỗi dữ liệu giỏ hàng", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    }){
                                        @Override
                                        // chuyển thành một json array
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            JSONArray jsonArray = new JSONArray();
                                            for(int i = 0; i < MainActivity.carts.size(); i++){
                                                JSONObject jsonObject = new JSONObject();
                                                try {
                                                    jsonObject.put("madonhang",responseMDH);
                                                    jsonObject.put("masanpham",MainActivity.carts.get(i).getIdsp());
                                                    jsonObject.put("tensanpham",MainActivity.carts.get(i).getTensp());
                                                    jsonObject.put("giasanpham",MainActivity.carts.get(i).getGiasp());
                                                    jsonObject.put("soluongsanpham",MainActivity.carts.get(i).getSoluong());
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                jsonArray.put(jsonObject);
                                            }
                                            HashMap<String,String> hashMap = new HashMap<>();
                                            hashMap.put("json", jsonArray.toString()); // key trong php code gửi lên đoạn chuỗi
                                            return hashMap;
                                        }
                                    };
                                    requestQueue1.add(stringRequest1);

                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext().getApplicationContext(), "Lỗi thanh toán", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("tenkhachhang", MainActivity.customer.getName());
                        hashMap.put("sodienthoai", MainActivity.customer.getPhone());
                        hashMap.put("diachi", MainActivity.customer.getAddress());
                        hashMap.put("password", MainActivity.customer.getPassword());
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        return mView;
    }


    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame_customer, fragment);
        transaction.commit();
    }

    private void initUI() {
        mBtnCustomerPayment = (Button) mView.findViewById(R.id.btn_customer_payment);
        mBtnCommitPayment = (Button) mView.findViewById(R.id.btn_commit_payment);

        bottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.bottom_navigation);
        navigationView = (NavigationView) getActivity().findViewById(R.id.navigation_view);
    }
}
