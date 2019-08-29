package com.magic.pdademo;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

/**
 * create by zj on 2019/6/5
 * 已扫描商品
 */
public class ScannedProductDialog extends Dialog implements ScanGunKeyEventHelper.OnScanSuccessListener{
    private Context context;

    TextView tv_title;
    LinearLayout llcontainer;
    private ScanGunKeyEventHelper mScanGunKeyEventHelper;
    EditText et1,et2;

    int style;//0:分拣 1：销退
    public ScannedProductDialog(@NonNull Context context, int style,ScanGunKeyEventHelper sc) {
        super(context, R.style.NoFrameDialog);
        this.context=context;
        this.style=style;
        mScanGunKeyEventHelper=sc;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_scanned_product);
        tv_title=findViewById(R.id.tv_title);
        llcontainer=findViewById(R.id.llcontainer);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);

        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

       /* llcontainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.e("llcontainer onTouch","触发event事件！！");
                return true;
            }
        });*/

        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        Log.e("width",params.width+"");
        Log.e("height",params.height+"");

/*
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
//                Log.e("dialog onKey","触发event事件！！");
                if (mScanGunKeyEventHelper.isScanGunEvent(keyEvent)) {
//            Log.e("xxx","dispatchKeyEvent"+"isScanGunEvent=true");
                    Log.e("dialog OnKeyListener","触发扫码枪event事件！！");
                    mScanGunKeyEventHelper.analysisKeyEvent(keyEvent);
                    return true;
                }
                return false;
            }
        });

*/



        et1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    Log.e("et10","enter click!");
                    return true;
                }
              /*  if (actionId == EditorInfo.IME_FLAG_NO_ENTER_ACTION) {
                    //EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
                    //TODO回车键按下时要执行的操作
                    Log.e("et11","enter click!");
                }*/

                return false;
            }
        });

    }

/*    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
//        Log.e("dialog dispatchKeyEvent","触发event事件66！！");
        if (mScanGunKeyEventHelper.isScanGunEvent(event)) {
//            Log.e("xxx","dispatchKeyEvent"+"isScanGunEvent=true");
            Log.e("dialog dispatchKeyEvent","触发扫码枪event事件！！");
            mScanGunKeyEventHelper.analysisKeyEvent(event);

            return true;
        }else{
//            Log.e("xxx","dispatchKeyEvent"+"isScanGunEvent=false");
        }
        return super.dispatchKeyEvent(event);

    }*/



public void settitle(int a){
    tv_title.setText(""+a);

}


    @Override
    public void onScanSuccess(String barcode) {

    }
}
