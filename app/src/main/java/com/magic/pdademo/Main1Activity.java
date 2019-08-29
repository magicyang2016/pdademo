package com.magic.pdademo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.time.Year;

public class Main1Activity extends AppCompatActivity implements ScanGunKeyEventHelper.OnScanSuccessListener{
    private ScanGunKeyEventHelper mScanGunKeyEventHelper;
    private EditText ettt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        mScanGunKeyEventHelper = new ScanGunKeyEventHelper(this);
    }

    public void click(View v){
        Log.e("text1","click");
        showNormalDialog();
    }
    private void showNormalDialog(){
        ScannedProductDialog dialog=new ScannedProductDialog(this,0,mScanGunKeyEventHelper);
//        dialog.setCancelable(false);
        dialog.show();

    }



    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

//        Log.e("Main1-->dispatchEvent","触发event事件！！");

        if (mScanGunKeyEventHelper.isScanGunEvent(event)) {

//            Log.e("xxx","dispatchKeyEvent"+"isScanGunEvent=true");
            mScanGunKeyEventHelper.analysisKeyEvent(event);

            Log.e("main1 dispatchKeyEvent","触发扫码枪event事件！！");
            return true;
        }else{
//            Log.e("xxx","dispatchKeyEvent"+"isScanGunEvent=false");
        }
//        return true;
        return super.dispatchKeyEvent(event);

    }


    @Override
    public void onScanSuccess(String barcode) {
        Log.e("onScanSuccess","扫描结果："+barcode);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!mScanGunKeyEventHelper.hasScanGun()) {
            Toast.makeText(this, "未检测到扫码枪设备", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mScanGunKeyEventHelper.onDestroy();
    }
}
