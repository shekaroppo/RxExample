package com.rx.activity.basic;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.rx.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Sekhar on 3/28/15.
 */
public class RxAndroidPart1 extends Activity {

    Observable.OnSubscribe observableAction = new Observable.OnSubscribe<String>() {
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello, World!");
            subscriber.onCompleted();
        }
    };
    Subscriber<String> textViewSubscriber = new Subscriber<String>() {
        public void onCompleted() {
        }

        public void onError(Throwable e) {
        }

        public void onNext(String s) {
            mTextView.setText(s);
        }
    };
    Subscriber<String> toastSubscriber = new Subscriber<String>() {
        public void onCompleted() {
        }

        public void onError(Throwable e) {
        }

        public void onNext(String s) {
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
        observable = Observable.create(observableAction);
        observable.subscribeOn(Schedulers.io());
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(textViewSubscriber);
        observable.subscribe(toastSubscriber);
    }
}
