package com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.roomdata.Recode;
import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.roomdata.RecodeDataBase;
import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.scanlist.ScanListFragment;
import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.util.ActivityUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.util.BackPressCloseSystem;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener {


    private IntentIntegrator intentIntegrator;
    private BottomNavigationView navigation;
    ScanListFragment scanListFragment;

    private BackPressCloseSystem backPressCloseSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.setSelectedItemId(R.id.navigation_list);

        backPressCloseSystem = new BackPressCloseSystem(this);


        intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setBeepEnabled(true);

        if(scanListFragment == null){
            scanListFragment = ScanListFragment.newInstance();
        }
        ActivityUtil.getInstance().replaceToActivityFragment(getSupportFragmentManager() , scanListFragment , R.id.main_container);



    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_camera:
                intentIntegrator.initiateScan();
                navigation.setSelectedItemId(R.id.navigation_list);
                return false;
            case R.id.navigation_list:
//                ScanListFragment scanListFragment = ScanListFragment.newInstance();
                if(scanListFragment == null){
                    scanListFragment = ScanListFragment.newInstance();
                }
                ActivityUtil.getInstance().replaceToActivityFragment(getSupportFragmentManager() , scanListFragment , R.id.main_container);
                return true;

        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {

            } else {
                Recode recode = new Recode();
                recode.setData(result.getContents());
                Date date = new Date();
                recode.setTimestamp(date.getTime());
                RecodeDataBase.getInstance(getApplicationContext()).recodeDao().insertRecode(recode);
                scanListFragment.onUpdateQrText();


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        backPressCloseSystem.onBackPressed();
    }
}
