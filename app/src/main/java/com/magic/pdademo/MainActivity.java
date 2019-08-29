package com.magic.pdademo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements ScanGunKeyEventHelper.OnScanSuccessListener{
    private ScanGunKeyEventHelper mScanGunKeyEventHelper;
    int a=0;

    private MyHandler handler ;

    ScannedProductDialog dialog;
    private EditText ettt1;

     class MyHandler extends Handler {
        WeakReference weakReference;
        public MyHandler(Activity activity) {
            weakReference = new WeakReference(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            ettt1.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new MyHandler(this);
        dialog =new ScannedProductDialog(this,0,mScanGunKeyEventHelper);
        mScanGunKeyEventHelper = new ScanGunKeyEventHelper(this);
        registerBoradcastReceiver();
         ettt1=findViewById(R.id.ettt1);

    }

    private void registerBoradcastReceiver() {
        IntentFilter filter1 = new IntentFilter(
                BluetoothAdapter.ACTION_STATE_CHANGED);
        IntentFilter filter2 = new IntentFilter(
                BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        registerReceiver(stateChangeReceiver, filter1);
        registerReceiver(stateChangeReceiver, filter2);
    }
    private BroadcastReceiver stateChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                Toast.makeText(MainActivity.this, "蓝牙设备连接状态已变更", Toast.LENGTH_SHORT).show();
            } else if (action.equals(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)) {
                Toast.makeText(MainActivity.this, "蓝牙设备连接状态已变更", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void click(View v){
        a++;
        Log.e("button","click"+a);
//        startActivity(new Intent(this,Main1Activity.class));
//        showNormalDialog();
//        清空消息队列



        /*handler.removeCallbacksAndMessages(null);
        handler.sendEmptyMessageDelayed(0,2000);
        ettt1.setVisibility(View.VISIBLE);
        ettt1.setText(""+a);*/






   /*     if(dialog!=null&&!dialog.isShowing()){
            dialog.show();
            dialog.settitle(a);
            handler.sendEmptyMessageDelayed(0,2000);

        }
        if(dialog!=null&&dialog.isShowing()){
            dialog.settitle(a);
            handler.sendEmptyMessageDelayed(0,2000);

        }*/




    }

    private void showNormalDialog(){
        ScannedProductDialog dialog=new ScannedProductDialog(this,0,mScanGunKeyEventHelper);
//        dialog.setCancelable(false);

        dialog.show();



    }


    /**
     * 截获按键事件.发给ScanGunKeyEventHelper
     *
     * @param event
     * @return
     */


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
//        Log.e("dispatchKeyEvent","触发event事件！！");
        if (mScanGunKeyEventHelper.isScanGunEvent(event)) {
//            Log.e("xxx","dispatchKeyEvent"+"isScanGunEvent=true");
            mScanGunKeyEventHelper.analysisKeyEvent(event);
            Log.e("main00 dispatchKeyEvent","触发扫码枪event事件！！");
            return true;
        }else{
//            Log.e("xxx","dispatchKeyEvent"+"isScanGunEvent=false");
        }
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
        unregisterReceiver(stateChangeReceiver);
        mScanGunKeyEventHelper.onDestroy();
    }
}
