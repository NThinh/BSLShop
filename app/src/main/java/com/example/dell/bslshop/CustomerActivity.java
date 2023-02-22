package com.example.dell.bslshop;

import static com.example.dell.bslshop.fragment.cartFragment.mTxtTotalPrice;
import static com.example.dell.bslshop.fragment.cartFragment.totalPrice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.bslshop.adapter.CartAdapter;
import com.example.dell.bslshop.fragment.homeFragment;
import com.example.dell.bslshop.fragment.inforCustomerFragment;
import com.example.dell.bslshop.fragment.paymentFragment;

import java.text.DecimalFormat;

public class CustomerActivity extends AppCompatActivity {

    public static final int FRAGMENT_INFOR_CUSTOMER = 1;
    public static final int FRAGMENT_PAYMENT = 2;
    public static int mCurrentFragment2 = FRAGMENT_INFOR_CUSTOMER;

    private TextView mTxtTotal, mTxtInforOrder, mTxtResultVoucher;
    private Button mBtnUseVoucher;
    private EditText mEdtVoucher;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        initUI();
        mTxtTotal.setText(mTxtTotalPrice.getText());
        if (MainActivity.sharedPreferences.getBoolean("checkloggin", false) == true) {
            replaceFragment(new paymentFragment());
        }else {
            replaceFragment(new inforCustomerFragment());
        }

        
        mTxtInforOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrderDialog();


                Toast.makeText(getApplicationContext(), "Thông tin đơn hàng", Toast.LENGTH_SHORT).show();
            }
        });
        mBtnUseVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String voucher = mEdtVoucher.getText().toString();
                if(voucher.trim().toUpperCase().equals("VOUCHER")){
                    mTxtResultVoucher.setText("Ưu đãi 5% giá trị hóa đơn");
                    mTxtResultVoucher.setVisibility(View.VISIBLE);
                    totalPriceVoucher(5);

                }else {
                    mTxtTotal.setText(mTxtTotalPrice.getText());
                    mTxtResultVoucher.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),  mEdtVoucher.getText()+" không hợp lệ !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openOrderDialog() {
        dialog.setContentView(R.layout.my_dialog_order);

        ListView mLvDialog = (ListView) dialog.findViewById(R.id.lv_cart_detail_dialog);
        ImageButton mIBtnClose = (ImageButton) dialog.findViewById(R.id.ibtn_close_dialog);
        CartAdapter cartAdapter = new CartAdapter(this,MainActivity.carts);
        mLvDialog.setAdapter(cartAdapter);

        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        mIBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPrice();
                mTxtTotal.setText(mTxtTotalPrice.getText());
                dialog.dismiss();
            }
        });


        dialog.show();

    }

    private void totalPriceVoucher(int percent) {
        double totalAmount = 0;
        for (int i = 0 ; i < MainActivity.carts.size(); i ++) {
            totalAmount += MainActivity.carts.get(i).getGiasp();
        }
        totalAmount -= (double)( totalAmount * percent ) / 100;
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        mTxtTotal.setText(decimalFormat.format(totalAmount) + " Đ");
    }
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame_customer, fragment);
        transaction.commit();
    }

    private void initUI() {
        mTxtTotal = (TextView) findViewById(R.id.txt_total_price_customer);
        mTxtInforOrder = (TextView) findViewById(R.id.txt_infor_order_customer);
        mTxtResultVoucher = (TextView) findViewById(R.id.txt_voucher_result_customer);
        mTxtResultVoucher.setVisibility(View.INVISIBLE);
        mEdtVoucher = (EditText) findViewById(R.id.edt_voucher_customer);
        mBtnUseVoucher = (Button) findViewById(R.id.btn_voucher_customer);

        dialog = new Dialog(this);
    }

    @Override
    public void onBackPressed() {
        if(mCurrentFragment2 == FRAGMENT_PAYMENT){
            replaceFragment(new inforCustomerFragment());
            mCurrentFragment2 = FRAGMENT_INFOR_CUSTOMER;
        }else {
            super.onBackPressed();
        }
    }
}