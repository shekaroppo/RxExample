package com.rx.activity.basic;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.rx.R;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Sekhar on 4/3/15.
 */
public class RxAndroidPart2UsingAction1 extends Activity {

    Action1<String> textViewSubscriber = new Action1<String>() {
        @Override
        public void call(String s) {
            mTextView.setText(s);
        }
    };

    Action1<String> toastSubscriber = new Action1<String>() {
        @Override
        public void call(String s) {
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }
    };

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
        observable.subscribe(textViewSubscriber);
        observable.subscribe(toastSubscriber);
    }
}
