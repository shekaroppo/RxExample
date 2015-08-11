package com.example.development.rx.activity.operators;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.development.rx.R;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Sekhar on 4/3/15.
 */
public class O2MapEmitingDiffrentType extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rx_android_main);
        mTextView = (TextView) findViewById(R.id.rx_textview);
        Observable.just("Hello World")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .subscribe(s -> setUI(Integer.toString(s)));
    }

    private void setUI(String s) {
        mTextView.setText(s);
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
