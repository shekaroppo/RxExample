package com.example.development.rx.activity.operators;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import com.example.development.rx.R;
import com.jakewharton.rxbinding.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import timber.log.Timber;

/**
 * Created by Shekar on 8/10/15.
 */
public class O4Buffers extends Activity {

    @Bind(R.id.buffertext)
    TextView mTextView;
    @Bind(R.id.button)
    Button mButton;
    private Subscription mSubscription;
    String TAG = "HH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rx_buffer);
        ButterKnife.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mSubscription = getBufferedSubscription();
    }

    @Override
    public void onPause() {
        super.onPause();
        mSubscription.unsubscribe();
    }

    private Subscription getBufferedSubscription() {

        return RxView.clicks(mButton).map(new Func1<Object, Integer>() {
                @Override
                public Integer call(Object o) {
                        return 1;
                 }})
                .buffer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onCompleted() {
                        // This will never be called as stream is always open
                        Timber.d("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "onError");
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Timber.d("onNext");
                        if (integers.size() > 0) {
                            setUI(String.format("%d taps", integers.size()));
                        } else {
                            setUI("No taps received");
                        }
                    }
                });

    }

    public void setUI(String msg) {
        Timber.d(msg);
        if (isCurrentlyOnMainThread()) {
            mTextView.setText(msg);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    mTextView.setText(msg);
                }
            });
        }
    }

    private boolean isCurrentlyOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}

