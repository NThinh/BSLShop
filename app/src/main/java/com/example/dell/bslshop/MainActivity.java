package com.example.dell.bslshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dell.bslshop.fragment.aboutFragment;
import com.example.dell.bslshop.fragment.cartFragment;
import com.example.dell.bslshop.fragment.homeFragment;
import com.example.dell.bslshop.fragment.logginFragment;
import com.example.dell.bslshop.model.Cart;
import com.example.dell.bslshop.model.Customer;
import com.example.dell.bslshop.ultil.CheckConnection;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    public static final int FRAGMENT_HOME = 3;
    public static final int FRAGMENT_CART = 4;
    public static final int FRAGMENT_ABOUT = 5;
    public static final int FRAGMENT_PHONE = 6;
    public static final int FRAGMENT_LOGGIN = 7;

    public static boolean CHECK_LOGGIN = false;
    public static SharedPreferences sharedPreferences;

    public static ArrayList<Cart> carts;
    public static Customer customer;


    public static NavigationView navigationView;
    public static int mCurrentFragment = FRAGMENT_HOME;

    private FloatingActionButton mFab;
    public static BottomNavigationView mBottomNavigationView;
    public static TextView mTitleActionBar;

    public View nav_headerlayout;
    public static TextView navNameTitle, navPhoneTitle, navAddressTittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initUI();
        if (sharedPreferences.getBoolean("checkloggin", false) == true) {
            getCusShare();
            MainActivity.navigationView.getMenu().findItem(R.id.nav_my_loggin).setEnabled(false);
            MainActivity.navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
        }
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotURL("https://m.facebook.com/messages/read/?fbid=100016012534008&entrypoint=profile_message_button&eav=AfYIUlcfYQ3ussjNxNuKUGYYoH1ivYm0CNF5K7WIXXt_zI07xw394WBK2wo_4csv1wk&paipv=0&src=msite_fallback");
            }
        });

        //noinspection deprecation
        mBottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_bottom_home:
                                if (mCurrentFragment != FRAGMENT_HOME) {
                                    replaceFragment(new homeFragment());
                                    navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                                    mCurrentFragment = FRAGMENT_HOME;
                                }
                                break;
                            case R.id.nav_bottom_phone:
                                dialPhoneNumber("0384657154");
                                mCurrentFragment = FRAGMENT_PHONE;
                                break;
                            case R.id.nav_bottom_about:
                                if (mCurrentFragment != FRAGMENT_ABOUT) {
                                    replaceFragment(new aboutFragment());
                                    navigationView.getMenu().findItem(R.id.nav_about).setChecked(true);
                                    mCurrentFragment = FRAGMENT_ABOUT;
                                }
                                break;

                        }
                        if (mCurrentFragment != FRAGMENT_PHONE) setTitleToobar();
                        return true;
                    }
                });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        navigationView.setNavigationItemSelectedListener(this);

        // start app open fragment home
        if (mCurrentFragment == FRAGMENT_HOME) {
            replaceFragment(new homeFragment());
            navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
            mBottomNavigationView.getMenu().findItem(R.id.nav_bottom_home).setChecked(true);
            setTitleToobar();
        } else if (mCurrentFragment == FRAGMENT_CART) {
            replaceFragment(new cartFragment());
            navigationView.getMenu().findItem(R.id.nav_cart).setChecked(true);
            mBottomNavigationView.getMenu().findItem(R.id.nav_bottom_phone).setChecked(true);
            setTitleToobar();
        }



    }

    private void initUI() {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (carts != null) {
        } else {
            carts = new ArrayList<>();
        }
        customer = new Customer();
        sharedPreferences = getSharedPreferences("loggin", Context.MODE_PRIVATE);

        nav_headerlayout = (View) MainActivity.navigationView.getHeaderView(0);
        navNameTitle = (TextView) nav_headerlayout.findViewById(R.id.nav_name_header);
        navPhoneTitle = (TextView) nav_headerlayout.findViewById(R.id.nav_phone_header);
        navAddressTittle = (TextView) nav_headerlayout.findViewById(R.id.nav_address_header);
    }

    // btn call
    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //btn messenger
    private void gotURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_toolbar) {
            mDrawerLayout.openDrawer(GravityCompat.END);
        } else if (id == R.id.menu_toolbar_cart) {
            replaceFragment(new cartFragment());
            navigationView.getMenu().findItem(R.id.nav_cart).setChecked(true);
            mBottomNavigationView.getMenu().findItem(R.id.nav_bottom_phone).setChecked(true);
            mCurrentFragment = FRAGMENT_CART;
            setTitleToobar();
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            if (mCurrentFragment != FRAGMENT_HOME) {
                replaceFragment(new homeFragment());
                mCurrentFragment = FRAGMENT_HOME;
                mBottomNavigationView.getMenu().findItem(R.id.nav_bottom_home).setChecked(true);
            }
        } else if (id == R.id.nav_cart) {
            if (mCurrentFragment != FRAGMENT_CART) {
                replaceFragment(new cartFragment());
                mBottomNavigationView.getMenu().findItem(R.id.nav_bottom_phone).setChecked(true);
                mCurrentFragment = FRAGMENT_CART;
            }
        } else if (id == R.id.nav_about) {
            if (mCurrentFragment != FRAGMENT_ABOUT) {
                replaceFragment(new aboutFragment());
                mCurrentFragment = FRAGMENT_ABOUT;
                mBottomNavigationView.getMenu().findItem(R.id.nav_bottom_about).setChecked(true);
            }
        } else if (id == R.id.nav_my_loggin) {
            if (mCurrentFragment != FRAGMENT_LOGGIN) {
                replaceFragment(new logginFragment());
                mCurrentFragment = FRAGMENT_LOGGIN;
                mBottomNavigationView.getMenu().findItem(R.id.nav_bottom_phone).setChecked(true);
            }
        } else if (id == R.id.nav_logout) {
            // set ẩn hiện icon navigation
            CHECK_LOGGIN = false;
            removeCustomer();
            getCusShare();
            MainActivity.navigationView.getMenu().findItem(R.id.nav_my_loggin).setEnabled(true);
            MainActivity.navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
            // clear customer to default
        }

        setTitleToobar();
        mDrawerLayout.closeDrawer(GravityCompat.END);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();

    }

    public static void getCusShare() {
        String name = sharedPreferences.getString("name", "BSL Shop");
        String phone = sharedPreferences.getString("phone", "0384657154");
        String address = sharedPreferences.getString("address", "BSL Shop, 13 Hoàn Hoa Thám Quận 1");
        String password = sharedPreferences.getString("password", "admin");

        customer.setName(name);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setPassword(password);

        navNameTitle.setText(customer.getName());
        navPhoneTitle.setText(customer.getPhone());
        navAddressTittle.setText(customer.getAddress());


    }

    public static void customerShareRefence(boolean check) {
        //lưu
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", customer.getName());
        editor.putString("phone", customer.getPhone());
        editor.putString("address", customer.getAddress());
        editor.putString("password", customer.getPassword());
        editor.putBoolean("checkloggin", check);
        editor.commit();
    }

    public static void removeCustomer() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("name");
        editor.remove("phone");
        editor.remove("address");
        editor.remove("password");
        editor.remove("checkloggin");
        editor.commit();
    }

    public void setTitleToobar() {
        mTitleActionBar = findViewById(R.id.title);
        String title = "";
        switch (mCurrentFragment) {
            case FRAGMENT_HOME:
                title = getString(R.string.nav_home);
                break;
            case FRAGMENT_CART:
                title = getString(R.string.nav_cart);
                break;
            case FRAGMENT_ABOUT:
                title = getString(R.string.nav_about);
                break;
            case FRAGMENT_PHONE:
                title = getString(R.string.nav_phone);
                break;
            case FRAGMENT_LOGGIN:
                title = getString(R.string.app_name);
                break;
        }

        if (mTitleActionBar != null) {
            mTitleActionBar.setText(title);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
            mDrawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }
}