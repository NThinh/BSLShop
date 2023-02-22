package com.example.dell.bslshop.adapter;
import static com.example.dell.bslshop.fragment.cartFragment.CHECK_CHOOSE_LINE;
import static com.example.dell.bslshop.fragment.cartFragment.totalPrice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.bslshop.MainActivity;
import com.example.dell.bslshop.R;
import com.example.dell.bslshop.model.Cart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    Context context;
    ArrayList<Cart> carts;

    public CartAdapter(Context context, ArrayList<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    @Override
    public int getCount() {
        return carts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder {
        CheckBox cbkChooseLine;
        ImageView imgCartLine;
        TextView txtNameCartLine, txtPriceCartLine, txtSizeCartLine, txtTotalPriceCartLine;
        Button btnMinusCartLine, btnValueCartLine, btnPlusCartLine;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.line_cart, null);

            viewHolder.txtNameCartLine = (TextView) convertView.findViewById(R.id.txt_line_name_cart);
            viewHolder.txtPriceCartLine = (TextView) convertView.findViewById(R.id.txt_line_price_cart);
            viewHolder.txtSizeCartLine = (TextView) convertView.findViewById(R.id.txt_line_size_cart);
            viewHolder.txtTotalPriceCartLine = (TextView) convertView.findViewById(R.id.txt_line_total_price_cart);
            viewHolder.imgCartLine = (ImageView) convertView.findViewById(R.id.img_line_cart);
            viewHolder.cbkChooseLine = (CheckBox) convertView.findViewById(R.id.cbk_line_choose_cart);
            viewHolder.btnValueCartLine = (Button) convertView.findViewById(R.id.btn_value_line_cart);
            viewHolder.btnMinusCartLine = (Button) convertView.findViewById(R.id.btn_minus_line_cart);
            viewHolder.btnPlusCartLine = (Button) convertView.findViewById(R.id.btn_add_line_cart);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Cart cart = carts.get(position);

        viewHolder.txtNameCartLine.setText(cart.getTensp());
        viewHolder.txtSizeCartLine.setText(cart.getSize());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtPriceCartLine.setText(decimalFormat.format(cart.getDongiale()) + " Đ");
        viewHolder.txtTotalPriceCartLine.setText(decimalFormat.format(cart.getGiasp()) + " Đ");

        Picasso.with(context).load(cart.getHinhanh())
                .placeholder(R.drawable.ic_home)
                .error(R.drawable.ic_error)
                .into(viewHolder.imgCartLine);

        viewHolder.btnValueCartLine.setText(String.valueOf(cart.getSoluong()));

        // event
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnPlusCartLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = Integer.parseInt(finalViewHolder.btnValueCartLine.getText().toString().trim());
                value += 1;
                finalViewHolder.btnValueCartLine.setText(value + "");
                if (value != 1) {
                    finalViewHolder.btnMinusCartLine.setEnabled(true);
                }
                long giaMoi = value * cart.getDongiale();
                finalViewHolder.txtTotalPriceCartLine.setText(decimalFormat.format(giaMoi) + " Đ");
                cart.setSoluong(value);
                cart.setGiasp(giaMoi);
                totalPrice();

            }
        });
        viewHolder.btnMinusCartLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(finalViewHolder.btnValueCartLine.getText().toString().trim());
                if (value != 1) {
                    value -= 1;
                    finalViewHolder.btnValueCartLine.setText(value + "");
                    finalViewHolder.btnMinusCartLine.setEnabled(true);
                } else {
                    finalViewHolder.btnMinusCartLine.setEnabled(false);
                }
                long giaMoi = value * cart.getDongiale();
                finalViewHolder.txtTotalPriceCartLine.setText(decimalFormat.format(giaMoi) + " Đ");
                cart.setSoluong(value);
                cart.setGiasp(giaMoi);
                totalPrice();
            }
        });
        // set check
        for (int i = 0; i < carts.size(); i++) {
            if(CHECK_CHOOSE_LINE == true) {
                finalViewHolder.cbkChooseLine.setChecked(true);
                carts.get(i).setCheck(true);
            }else {
                carts.get(i).setCheck(false);
            }
        }
        // set check don
        finalViewHolder.cbkChooseLine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked == true) {
                        carts.get(position).setCheck(true);
                    }else {
                        carts.get(position).setCheck(false);
                    }

            }
        });

        return convertView;
    }
}
