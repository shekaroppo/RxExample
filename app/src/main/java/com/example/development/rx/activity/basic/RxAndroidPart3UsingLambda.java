package com.example.development.rx.activity.basic;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.development.rx.R;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Sekhar on 4/3/15.
 */
public class RxAndroidPart3UsingLambda extends Activity {

    private TextView mTextView;
    private Observable<String> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rx_android_main);
        mTextView = (TextView) findViewById(R.id.rx_textview);
        observable = Observable.just("Hello World");
        observable.subscribeOn(Schedulers.io());
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(s -> mTextView.setText(s));
        observable.subscribe(s -> Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show());
    }
}
